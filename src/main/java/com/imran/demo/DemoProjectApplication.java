package com.imran.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoProjectApplication implements CommandLineRunner {

	// CommandLineRunner:-  It create another main method but not static method name is run
	@Autowired // it connects with bean
	DB db;

	// Entry point of project
	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}


	// it comes from CommandLineRunner
	@Override
	public void run(String... args) throws Exception {
		System.out.println(db.getData());
	}
}
