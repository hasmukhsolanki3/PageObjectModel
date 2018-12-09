package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

public class AccountsPage extends Page {
	
	public void verifyAccountPage(){
		
		
	}

	public CreateAccountPage gotoCreateAnAccount(){
		
		click("CreateAnAccountBtn_CSS");
		
		return new CreateAccountPage();
	}
	
	public void gotoImportAccounts(){
		
		click("ImportAccountsBtn_CSS");
		
	}
}
