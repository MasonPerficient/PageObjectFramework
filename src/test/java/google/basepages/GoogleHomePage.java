package google.basepages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import util.FunctionUtil;
import util.Page;
import util.SystemUtil;
import util.TestCaseBase;
import util.Waiting;

public class GoogleHomePage extends Page {
	//some text which would be used as expected result
	//this also could be move to a property file 
	public static String TITLE="Google";
	
	//web elements
	@FindBy(name = "q")//it can be located by name
	public WebElement searchInput;

	@FindBy(xpath = "//a[@href='https://maps.google.com/maps?hl=en']")
	public WebElement maps;

	@FindBy(id = "gb_2")
	public WebElement images;

	@FindBy(xpath = "//a[@class='gb_1 gb_2 gb_6d gb_6c']")
	public WebElement loginLink;
    
	//signedInUser for all browsers,including chrome,firefox and IE
	@FindBy(className = "gb_A gb_Ka gb_f")
	public WebElement signedInUser;
	
	@FindBy(xpath="//input[contains(@value,'Google') and @type='submit']")
	public WebElement button_googleSearch;
	
	@FindBy(xpath = "//a[@class='gb_A']")
	public WebElement moreLink;
	
	@FindBy(id = "gb51")
	public WebElement googleTranslateLink;
	
	
	//open this page
	public GoogleHomePage open() throws Exception {
		//read the url from property file
		Properties PROPERTIES_RESOURCES = SystemUtil
				.loadPropertiesResources("/testdata_google.properties");
		String URL = PROPERTIES_RESOURCES.getProperty("google.url");
		TestCaseBase.threadDriver.get().navigate().to(URL);
		//return this means browser stays on GoogleHomePage
		return this;
	}

	//do search
	public GoogleWebSearchResultPage search(String searchTerm) throws Exception {
		//input some search term and submit
		searchInput.sendKeys(searchTerm);
		searchInput.submit();
		//return "new GoogleWebSearchResultPage();" means browser goes to GoogleWebSearchResultPage
		return new GoogleWebSearchResultPage();
	}

	//goto google map
	public GoogleMapPage gotoGoogleMap() throws Exception {
		
		Actions action=new Actions(TestCaseBase.threadDriver.get());
		//clickAndHold() is used instead of click() as a workaround for firefox problems with certain links
		action.moveToElement(moreLink).click().perform();
		TestCaseBase.threadDriver.get().switchTo().frame(TestCaseBase.threadDriver.get().findElement(By.xpath("//iframe")));
		Waiting.until(maps);
		action.moveToElement(maps).click().perform();
		TestCaseBase.threadDriver.get().switchTo().defaultContent();
		
	    
		//here it returns a google map page, in a word, return the page which the browser will be directed to
		return new GoogleMapPage();
	}

	//go to google translate
	public void gotoGoogleTranslate() throws InterruptedException {
		Actions action=new Actions(TestCaseBase.threadDriver.get());
		//clickAndHold() is used instead of click() as a workaround for firefox problems with certain links
		action.moveToElement(moreLink).clickAndHold().release().perform();
		Waiting.until(googleTranslateLink);
		action.moveToElement(googleTranslateLink).click().perform();
		

 
	}

	//this is for assertion. checking if user is signed in
	public boolean isUserSignedIn() {
		String browserFlag = TestCaseBase.browserFlag;
		boolean result;
		if (browserFlag.equals("chrome")) {
			result=FunctionUtil.isExist(signedInUserChrome);
			log.info("actual Signed In="+result);
		} else {
			result=FunctionUtil.isExist(signedInUser);
			log.info("actual Signed In="+result);
		}
		return result;
	}
	
	//use this method to wait until the page loads. 
	//usually it is not the page loads but a specific element loads
	//here we use loginLink, because our test cases need to use this link
	//so if this link is visible, the test cases can continue
	public GoogleHomePage waitPageLoad() throws InterruptedException {
		Thread.sleep(1500);
		Waiting.until(button_googleSearch);
		//Waiting.until(loginLink);
		return this;
	}


}
