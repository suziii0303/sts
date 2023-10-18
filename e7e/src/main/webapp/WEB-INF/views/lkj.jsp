<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta charset="UTF-8">
<div id="disp"></div>
<script>
const myDisp = document.querySelector("#disp");
let imgArr1,imgArr2;
function fGetWeather(){
    //let schURL = `https://weather.naver.com`;
    let schURL = "/merong/jbj/test";

    let xhr = new XMLHttpRequest();
    xhr.open("get",schURL,true); //외부 URL을 직접요청 안하고
                                 //내 서버를 경유하면 CROSS-ORIGIN을 회피할 수 있당!!!

    xhr.onreadystatechange = ()=>{
        if(xhr.readyState == 4 && xhr.status == 200){
            let cont = xhr.responseText;
            console.log(cont);
            myDisp.innerHTML = "";

           // myDisp.innerHTML=cont;

            
            // 날씨 정보
            let startIndex = cont.indexOf("weather_area");
            let endIndex = startIndex;
            let content="";
            for(let i=1; i<=2;i++){
                endIndex = cont.indexOf("</div>",endIndex)+6;
              //  console.log("체킁:",cont.substring(startIndex+14,endIndex));
                content = cont.substring(startIndex+14,endIndex);
            }
            myDisp.innerHTML = content;

            // 위치정보
            startIndex = cont.indexOf("location_name")+15;
            endIndex = cont.indexOf("</strong>",startIndex);
            myDisp.innerHTML += "<div>" + cont.substring(startIndex,endIndex) + "</div>";
            
            // 위성사진1
            startIndex = cont.indexOf('["https://search.pstatic.net/common?');
            endIndex = cont.indexOf('"]',startIndex);
            imgArr1 = JSON.parse(cont.substring(startIndex,endIndex+2));
            console.log(imgArr1);
            myDisp.innerHTML += `<img id="img1" width=400 height=400 src="\${imgArr1[0]}">`;

            // 위성사진2
            startIndex = cont.indexOf('["https://search.pstatic.net/common?',endIndex);
            endIndex = cont.indexOf('"]',startIndex);
            imgArr2 = JSON.parse(cont.substring(startIndex,endIndex+2));
            console.log(imgArr2);
            myDisp.innerHTML += `<img  id="img2" width=400 height=400 src="\${imgArr2[0]}">`;
            imgRotate();
        }
    }
    xhr.send();
}
fGetWeather();

let pIndex1=0,pIndex2=0;
function imgRotate(){
     myDisp.querySelector("#img1").src=imgArr1[pIndex1];
     myDisp.querySelector("#img2").src=imgArr2[pIndex2];
     pIndex1++;
     if(pIndex1 > (imgArr1.length -1)) pIndex1 =0;
     pIndex2++;
     if(pIndex2 > (imgArr2.length -1)) pIndex2 =0;
    setTimeout(imgRotate,500);
}
</script>