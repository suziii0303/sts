package com.suji.merong.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class SujiVO {
	private int sujiNum;
	private String sujiName;
	private String sujiContent;
	private String sujiFile;
	private MultipartFile sujiFile2;
}