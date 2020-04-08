package com.hk.culture.mini.program;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hk.culture.mini.program.mapping")
public class CultureMiniProgramApplication {

	public static void main(String[] args) {
		SpringApplication.run(CultureMiniProgramApplication.class, args);
	}

}
