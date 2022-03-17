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
	
	@FindBy(xpath = "//span[@jsname='W297wb']")
	public WebElement result;
	
	public GoogleTranslatePage open() throws Exception {
			Properties PROPERTIES_RESOURCES = SystemUtil
					.loadPropertiesResources("/testdata_google.properties");
			String URL = PROPERTIES_RESOURCES.getProperty("google.translate.url");
			TestCaseBase.threadDriver.get().navigate().to(URL);
		return this;
	}

	public GoogleTranslatePage waitPageLoad() throws InterruptedException {
		Waiting.until(input);
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
