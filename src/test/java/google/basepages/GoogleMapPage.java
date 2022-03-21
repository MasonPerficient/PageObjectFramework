package google.basepages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import util.Page;
import util.SystemUtil;
import util.TestCaseBase;
import util.Waiting;

public class GoogleMapPage extends Page {

	public static String TITLE = "Google Maps";
	
	@FindBy(xpath = "(//div[@class='V0h1Ob-haAclf OPZbO-KE6vqe o0s21d-HiaYvf'])[1]")
	public WebElement firstRestaurant;

	@FindBy(xpath = "(//div[@class='suggest-left-content']/span)[1]/span")
	public WebElement searchResultTitle;
	
	@FindBy(className = "searchbox-searchbutton")
	public WebElement searchMapButton;
	
	@FindBy(name = "q")
	public WebElement searchInput;
	
	@FindBy(xpath = "(//div[@class='QSFF4-text gm2-body-2'])[1]")
	public WebElement restaurantAddress;
	
	
	//open this page
	public GoogleMapPage open() throws Exception {
		//read the url from property file
		Properties PROPERTIES_RESOURCES = SystemUtil
				.loadPropertiesResources("/testdata_google.properties");
		String URL = PROPERTIES_RESOURCES.getProperty("google.map.url");
		TestCaseBase.threadDriver.get().navigate().to(URL);
		//return this means browser stays on GoogleHomePage
		return this;
	}

	public GoogleMapPage search(String searchTerm) throws Exception {
		
		searchInput.sendKeys(searchTerm + Keys.ENTER);
		return this;
	}
	
	public WebElement findRestaurant() throws Exception
	{
		Thread.sleep(3000);
		
		Waiting.until(searchInput);
		TestCaseBase.threadDriver.get().findElement(By.xpath("//button[@aria-label='Search nearby Perficient Inc.']")).click();
		searchInput.clear();
		searchInput.sendKeys("Restaurants nearby" + Keys.ENTER);
		
		Waiting.until(firstRestaurant);
		
		return firstRestaurant; 
	}
	
	public GoogleMapPage waitPageLoad() throws InterruptedException {
		Thread.sleep(3000);
		Waiting.until(searchInput);
		
		return this;
	}
	
	public boolean searchResultTitleContains(String mapSearchResultPerficient) throws Exception {
		log.info("expected title="+mapSearchResultPerficient);

		log.info("actual title="+this.getTitle());
		return this.titleIs(mapSearchResultPerficient);
	}
	
	public boolean restaurantAddressContains(String mapSearchResultZipcode) throws Exception {
		log.info("expected address city="+mapSearchResultZipcode);
		log.info("actual address="+restaurantAddress.getText());
		return restaurantAddress.getText().contains(mapSearchResultZipcode);
	}
}
