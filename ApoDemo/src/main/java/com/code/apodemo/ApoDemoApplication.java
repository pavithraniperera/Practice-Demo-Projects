package com.code.apodemo;

import com.code.apodemo.Dao.AccountDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApoDemoApplication.class, args);
	}

	@Bean
	
	public CommandLineRunner commandLineRunner(AccountDao theAccountDAO) {

		return runner -> {

			demoTheBeforeAdvice(theAccountDAO);
		};
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDAO) {
		//call the business method
		theAccountDAO.AddAccount();
	}


}
