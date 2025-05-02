package com.code.apodemo;

import com.code.apodemo.Dao.AccountDao;
import com.code.apodemo.Dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ApoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApoDemoApplication.class, args);
	}

	@Bean
	
	public CommandLineRunner commandLineRunner(AccountDao theAccountDAO, MembershipDAO theMembershipDao) {

		return runner -> {

		//	demoTheBeforeAdvice(theAccountDAO,theMembershipDao);
			//demoTheAfterReturningAdvice(theAccountDAO,theMembershipDao);
			demoTheAfterThrowingAdvice(theAccountDAO);
		};
	}

	private void demoTheAfterThrowingAdvice(AccountDao theAccountDAO) {

		// call method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}
		catch (Exception exc) {
			System.out.println("\n\nMain Program: ... caught exception: " + exc);
		}

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");

		System.out.println(theAccounts);

		System.out.println("\n");

	}



	private void demoTheAfterReturningAdvice(AccountDao theAccountDAO, MembershipDAO theMembershipDao) {
		//call the method to find accounts
		List<Account> accounts = theAccountDAO.findAccounts();
		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");

		System.out.println(accounts);

		System.out.println("\n");

	}

	private void demoTheBeforeAdvice(AccountDao theAccountDAO, MembershipDAO theMembershipDao) {
		//call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount,true);
		theMembershipDao.addSillyMember();
		//call the accountDao getter /setter methods
		theAccountDAO.setName("pavi");
		theAccountDAO.setServiceCode("silver");
		theAccountDAO.getName();
		theAccountDAO.getServiceCode();


	}


}
