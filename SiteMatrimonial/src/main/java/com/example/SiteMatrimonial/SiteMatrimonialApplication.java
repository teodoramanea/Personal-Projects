package com.example.SiteMatrimonial;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SiteMatrimonialApplication{

	//private final UserRepo userRepository;
	public static void main(String[] args) {

		SpringApplication.run(SiteMatrimonialApplication.class, args);
	}

}
