package com.divineagro.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.divineagro.common.entity", "com.divineagro.admin.user"})
public class DivineAgroBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivineAgroBackEndApplication.class, args);
	}

}
