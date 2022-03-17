package google.basepages;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Page;
import util.SystemUtil;
import util.TestCaseBase;
import util.Waiting;

public class GoogleTranslatePage extends Page {

	public static String TITLE = "Google Translate";
	
	@FindBy(xpath = "//textarea[@aria-label='Source text']")
	public WebElement input;
	
	@FindBy(id = "gt-tl-gms")
	public WebElement selectLanguage;
	
	@FindBy(id = "overridelink")
	public WebElement overidelink;
	
	// //div[@class='gt-cd-c']//tr[2]//td[3]
	@FindBy(xpath = "//span[@jsname='W297wb']")
	public WebElement result;

	@FindBy(id = "i12")
	public WebElement translate;
	
	//@FindBy(xpath = "//i[@title='Recurring event']")
	@FindBy(xpath = "//div[@id='gt-tl-gms-menu']//td[2]/div[1]/div[8]/div[1]/div[1]")
	public WebElement english;
	
	public GoogleTranslatePage open() throws Exception {
//		GoogleHomePage googleHomePage=new GoogleHomePage();
//		String browserFlag = TestCaseBase.browserFlag;
		//log.info("browserFlag="+browserFlag);
//		if (browserFlag.equals("ie")) {
			Properties PROPERTIES_RESOURCES = SystemUtil
					.loadPropertiesResources("/testdata_google.properties");
			String URL = PROPERTIES_RESOURCES.getProperty("google.translate.url");
			TestCaseBase.threadDriver.get().navigate().to(URL);
//		}else{
//			googleHomePage.open();
//			googleHomePage.gotoGoogleTranslate();
//		}
		return this;
	}

	public GoogleTranslatePage waitPageLoad() throws InterruptedException {
		Waiting.until(input);
		return this;
	}

	public GoogleTranslatePage translateInput(String input2) {
		input.click();
		input.sendKeys(input2);

		translate.click();
		return this;
	}

	public GoogleTranslatePage waitResultLoad() throws InterruptedException {
		Waiting.until(result);
		return this;	
	}



	public boolean contain(String expectedResult) {
		log.info("actual translate result="+result.getText());
		log.info("expected translate result=" + expectedResult);	
		return result.getText().contains(expectedResult);
	}



}
