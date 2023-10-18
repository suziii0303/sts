package com.minu.merong.utiles;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.SocketException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // Bean 생성   value==기본값 생략 가능
@PropertySource(value="classpath:config/props/ftpConf.properties")
public class FtpUtils {

	@Value("${myFtp.server}")
	private String server;

	@Value("${myFtp.port}")
	private int port;

	@Value("${myFtp.username}")
	private String username;

	@Value("${myFtp.password}")
	private String password;

	private FTPClient ftp;

	// FTPClient 객체를 통한 ftp 서버 연결
	public void open() throws SocketException, IOException {
		log.debug("server:" + server);
		log.debug("port:" + port);
		log.debug("username:" + username);
		log.debug("password:" + password);

		ftp = new FTPClient();
		ftp.setControlEncoding("UTF-8");
		// 로그로 서버와 주고 받은 명령어 결과들을 출력받을 수 있음
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out), true));

		ftp.connect(server, port);
		int reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			log.error("FTPClient:: server connection failed.");
		}

		ftp.setSoTimeout(1000);
		ftp.login(username, password);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
	}

	public void close() throws IOException {
		ftp.logout();
		ftp.disconnect();
	}
	
	//ftp 서버에 전송받은 파일 UPLOAD
	public void upload(MultipartFile file) throws IOException {
		open();
		InputStream inputStream = null;
		inputStream = file.getInputStream();
		//아래 라인이 핵심, put과 같음
		ftp.storeFile(file.getOriginalFilename(), inputStream);
		inputStream.close();
		close();
	}
	// ftp download
	// 다운로드 꼭 ftp 클라이언트를 쓰지 않아도됨
	// upload만 ftp를 쓰고, 다운로드는 해당폴더를 외부 파일맵핑 했을 경우
	// 이전에 배웠던 a 태그에 download 속성을 부여하는 방식으로도 
	// 충분히 사용할 수 있음(맘대로)
	 public void download(String fName,HttpServletResponse resp) throws IOException {
		 
		 
	    	String fileName = URLEncoder.encode("사자2.jpg", "UTF-8");
	    	//String fileName2 = "사자2.jpg";
	    	
	    	// 브라우저 별 기본 세팅이 조금 다름(구글 참고, 여기선 크롬기준으로)
	    	resp.setContentType("application/octet-stream");
	        resp.setHeader("Content-Disposition", "attachment; filename=\""+ fileName + "\"");
	        
	    	open(); // ftp 연결
	    	
	        OutputStream outputStream = new BufferedOutputStream(resp.getOutputStream());
	    	InputStream inputStream = null;
	    	
	    	// get에 해당
	    	inputStream =ftp.retrieveFileStream("/"+fName);
	        
	    	byte[] bytesArray = new byte[4096];
	        int bytesRead = -1;
	        while ((bytesRead = inputStream.read(bytesArray)) != -1) {
	            outputStream.write(bytesArray, 0, bytesRead);
	        }

	        boolean success  = ftp.completePendingCommand();
	        log.debug("check: " + success);
	        outputStream.close();
	        inputStream.close();    
	        
	        //ftp 닫는거
	         close();         
	    }

}
