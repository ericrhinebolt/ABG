package com.ericrhinebolt.abg.app;

import com.ericrhinebolt.abg.app.utils.JsonParserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

//	@Autowired
//	JsonParserController jsonParserController;

	public static void main(String[] args)  {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	// This adds 141,451 rows to database. Ran once, took approx. 60 mins.
//	@Override
//	public void run(String... args) throws Exception {
//		jsonParserController.createGames();
//	}
}