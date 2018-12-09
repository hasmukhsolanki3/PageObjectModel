package com.w2a.pages.crm;

import com.w2a.base.Page;

public class CRMHomePage extends Page {
	
	public void verifyCRMHomePage(){
		
		String title = driver.getTitle();
		
		if(title.equalsIgnoreCase("Zoho CRM - Home Page")){
			System.out.println("CRM Home Page verified");
		} 
		else{
			System.out.println("CRM Home Page loading failed");
		}
		
	}

}
