package com.code.apodemo;

import com.code.apodemo.Dao.AccountDao;
import com.code.apodemo.Dao.MembershipDAO;
import com.code.apodemo.service.TrafficFortuneService;
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
	
	public CommandLineRunner commandLineRunner(TrafficFortuneService theTrafficFortuneService,AccountDao theAccountDAO, MembershipDAO theMembershipDao) {

		return runner -> {

		//	demoTheBeforeAdvice(theAccountDAO,theMembershipDao);
			//demoTheAfterReturningAdvice(theAccountDAO,theMembershipDao);
			//demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfterAdvice(theAccountDAO);
			//demoTheAroundAdvice(theTrafficFortuneService);
			demoTheAroundAdviceHandleException(theTrafficFortuneService);
		};

	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");

	}
	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = theTrafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");
	}
	private void demoTheAfterAdvice(AccountDao theAccountDAO) {

		// call method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}
		catch (Exception exc) {
			System.out.println("\n\nMain Program: ... caught exception: " + exc);
		}

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterAdvice");
		System.out.println("----");

		System.out.println(theAccounts);

		System.out.println("\n");
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
