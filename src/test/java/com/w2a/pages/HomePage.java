package com.w2a.pages;

import com.w2a.base.Page;

public class HomePage extends Page {
	
	public void gotoCustomers(){
		
		click("CustomersLink_CSS");
		
	}
	
	public void gotoSupport(){
		
		click("SupportLink_CSS");
		
	}
	
	public void gotoContactSales(){
		
		click("ContactSalesLink_CSS");
		
	}
	
	public LoginPage gotoLogin(){
		
		click("LoginLink_CSS");
		
		return new LoginPage();
	}
	
	public void gotoSignUpNow(){
		
		click("SignUpNowLink_CSS");
		
	}
	
}
