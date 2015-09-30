package others.helper;

import java.io.Serializable;
import java.sql.Date;

import model.entity.Accounts;
import model.entity.Usertype;
import model.service.AccountsSerivice;

public class FormHelper implements Serializable {

	public static Accounts generateAccount (Usertype usertype) {
		Accounts acc = new Accounts();
		acc.setCreated_on(new Date(0));
		acc.setPassword("123");
		acc.setUsername(FormHelper.randomName());
		acc.setUsertype(usertype);
		
		return acc;
	}
	
	private static String randomName () {
		AccountsSerivice service = new AccountsSerivice();
		
		int min = 1;
		int max = 1000000000;
		boolean isExist = false;
		String random = "";
		
		while(! isExist) {
			random = min + (int)(Math.random() * ((max - min) + 1)) + ""; 
			isExist = service.isUnique(random);
		}
		
		return random;
	}
}
