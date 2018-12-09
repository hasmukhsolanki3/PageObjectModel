package com.w2a.pages;

import com.w2a.base.Page;

public class LoginPage extends Page {
	
	public ZohoAppsPage doLogin(String username, String password){
		
		type("Username_CSS", username);
		type("Password_CSS", password);
		click("SignInBtn_CSS");
		
		return new ZohoAppsPage();
	}
	
	public void gotoSignInWithGoogleOrOtherIDPs(){
		
		
	}
	
	public void gotoSignUpNow(){
		
		
	}

}
