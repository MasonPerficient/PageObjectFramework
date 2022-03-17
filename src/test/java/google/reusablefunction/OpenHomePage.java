package google.reusablefunction;

import google.basepages.GoogleHomePage;

//this is an reusable function, which methods would take actions on several pages
public class OpenHomePage {
	
	public static GoogleHomePage goToGoogleHome () throws Exception {
		GoogleHomePage googleHomePage = new GoogleHomePage();
		return googleHomePage.open();
	}

}
