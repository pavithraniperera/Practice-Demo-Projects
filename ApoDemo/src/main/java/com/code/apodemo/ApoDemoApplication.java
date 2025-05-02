package com.code.apodemo;

import com.code.apodemo.Dao.AccountDao;
import com.code.apodemo.Dao.MembershipDAO;
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
	
	public CommandLineRunner commandLineRunner(AccountDao theAccountDAO, MembershipDAO theMembershipDao) {

		return runner -> {

			demoTheBeforeAdvice(theAccountDAO,theMembershipDao);
		};
	}

	private void demoTheBeforeAdvice(AccountDao theAccountDAO, MembershipDAO theMembershipDao) {
		//call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount);
		theMembershipDao.addSillyMember();
	}


}
