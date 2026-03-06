package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.repository") // ← これを追加
public class SpringDemo9Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemo9Application.class, args);
	}

}
