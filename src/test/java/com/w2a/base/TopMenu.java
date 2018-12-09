package com.w2a.base;

import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.accounts.AccountsPage;

public class TopMenu {
	
	WebDriver driver;
	
	public TopMenu(WebDriver driver){
		
		this.driver = driver;
	}

	public void gotoHome() {

		Page.click("HomeLink_CSS");
		
	}

	public void gotoLeads() {

		Page.click("LeadsLink_CSS");
		
	}

	public void gotoContacts() {
		
		Page.click("ContactsLink_CSS");
		
	}

	public AccountsPage gotoAccounts() {

		Page.click("AccountsLink_CSS");
		
		return new AccountsPage();
	}

	public void doSignOut() {
		
		Page.click("SignOutDrpdn_CSS");
		Page.click("SignOutLink_CSS");

	}

}
