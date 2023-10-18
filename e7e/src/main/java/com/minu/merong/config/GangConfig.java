package com.minu.merong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration	// root-context에서 annotation-config
@EnableScheduling	// 기본제공 스케줄러 사용하겠다, xml로(task어쩌구) 하면 불편함
public class GangConfig {

}
