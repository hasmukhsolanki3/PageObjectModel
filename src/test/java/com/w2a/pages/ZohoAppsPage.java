package com.w2a.pages;

import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;

public class ZohoAppsPage extends Page {

	public CRMHomePage gotoCRM(){
		
		click("CRMLink_CSS");
		
		return new CRMHomePage();
	}
	
	public void gotoAllApps(){
		
		click("AllAppsLink_CSS");
		
	}
	
}
