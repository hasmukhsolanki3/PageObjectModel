package com.w2a.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.w2a.base.Page;
import com.w2a.pages.ZohoAppsPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;
import com.w2a.utilities.Utilities;

public class CreateAccountTest extends TestBase{
	
	@Test(dataProviderClass=Utilities.class, dataProvider="dp")
	public void createAccountTest(Hashtable<String, String> data){
		
		ZohoAppsPage zp = new ZohoAppsPage();
				
		zp.gotoCRM();
		
		AccountsPage account = Page.menu.gotoAccounts();
		
		CreateAccountPage ca = account.gotoCreateAnAccount();
		
		ca.createAccount(data.get("accountname"));
		
	}

}
