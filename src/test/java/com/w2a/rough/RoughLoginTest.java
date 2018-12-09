package com.w2a.rough;

import com.w2a.base.Page;
import com.w2a.pages.HomePage;
import com.w2a.pages.LoginPage;
import com.w2a.pages.ZohoAppsPage;
import com.w2a.pages.crm.accounts.AccountsPage;
import com.w2a.pages.crm.accounts.CreateAccountPage;

public class RoughLoginTest {

	public static void main(String[] args) {

		
		HomePage home = new HomePage();
		LoginPage login = home.gotoLogin();
		
		ZohoAppsPage zp = login.doLogin("hasmukhsolanki3@gmail.com", "@v41pvbH");
		
		zp.gotoCRM();
		
		AccountsPage account = Page.menu.gotoAccounts();
		
		CreateAccountPage ca = account.gotoCreateAnAccount();
		
		ca.createAccount("Hasmukh");
		
	}

}
