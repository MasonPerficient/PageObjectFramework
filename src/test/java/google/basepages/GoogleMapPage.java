package google.basepages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import util.Page;
import util.SystemUtil;
import util.TestCaseBase;
import util.Waiting;

public class GoogleMapPage extends Page {

	public static String TITLE = "Google Maps";
	
	// .//*[@class='widget-pane-section-listbox']//*[@class='widget-pane-section-directions-trip clearfix selected'][1]//h1/span
	@FindBy(xpath = ".//*[@class='widget-pane-section-listbox']/*[1]//h1/span")
	public WebElement suggestedRoute;
	
	@FindBy(xpath = ".//*[@id='directions-searchbox-0']/button[1]")
	public WebElement routeButton;
	
	@FindBy(xpath = ".//*[@id='sb_ifc51']/input")
	public WebElement fromDirectionsInput;
	
	@FindBy(xpath = ".//*[@id='pane']/div/div[1]/div/div[1]/button[2]")
	public WebElement directionsButton;
	
	@FindBy(xpath = "//*[@id=\'pane\']/div/div[1]/div/div/div[2]/div[1]/div[3]/div/a")
	public WebElement firstRestaurant;

	//@FindBy(xpath = "//span[@class='pp-place-title']")
	@FindBy(xpath = "(//div[@class='suggest-left-content']/span)[1]/span")
	public WebElement searchResultTitle;
	
	@FindBy(className = "searchbox-searchbutton")
	public WebElement searchMapButton;
	
	@FindBy(name = "q")
	public WebElement searchInput;
	
	//Sign in button on the right top corner of the Google Map page
	//@FindBy(xpath = "//div[@class='gb_fa']/a[text()='Sign in']")
	@FindBy(id = "gb_70")
	public WebElement button_SignIn;
	
	@FindBy(xpath = "//*[@id=\'pane\']/div/div[1]/div/div/div[9]/div[1]/button/div[1]/div[2]/div[1]")
	public WebElement restaurantAddress;
	
	
	//open this page
	public GoogleMapPage open() throws Exception {
		//read the url from property file
		Properties PROPERTIES_RESOURCES = SystemUtil
				.loadPropertiesResources("/testdata_google.properties");
		String URL = PROPERTIES_RESOURCES.getProperty("google.map.url");
		TestCaseBase.threadDriver.get().navigate().to(URL);
		// The language for google homepage
//		if ( FunctionUtil.isExist(english) ) {
//			if (TestCaseBase.browserFlag.equals("ie")) {
//				english.sendKeys("\n");
//				english.click();
//			}
//			else{
//			english.click();
//			}
//		}
		//return this means browser stays on GoogleHomePage
		return this;
	}

	public GoogleMapPage search(String searchTerm) throws Exception {
		
		searchInput.sendKeys(searchTerm + Keys.ENTER);
//		searchMapButton.click();
		//searchInput.submit();
		//searchInput.sendKeys(Keys.SPACE);
		return this;
	}
	
	public WebElement findRestaurant() throws Exception
	{
		Thread.sleep(3000);
		
		Waiting.until(searchInput);
		TestCaseBase.threadDriver.get().findElement(By.xpath("//button[@aria-label='Search nearby Perficient Inc.']")).click();
		searchInput.clear();
		searchInput.sendKeys("Restaurants nearby" + Keys.ENTER);
		
//		Waiting.until(searchMapButton);
//		searchMapButton.click();
		
		Waiting.until(firstRestaurant);
		
		return firstRestaurant; // .//*[@class='widget-pane-section-listbox widget-pane-section-scrollbox scrollable-y scrollable-show']/*[@class="widget-pane-section-result"][1]//*[@class="widget-pane-section-result-title"]/span
	}

	public GoogleMapPage directionsToRestaurant(String from, WebElement restaurant) throws InterruptedException {
		Waiting.until(restaurant);
		restaurant.click();
		
		Waiting.until(directionsButton);
		Thread.sleep(1500);
		
		Actions actions = new Actions(TestCaseBase.threadDriver.get());
		actions.moveToElement(directionsButton).click().perform();
		
		Waiting.until(fromDirectionsInput);
		fromDirectionsInput.sendKeys(from);
		Waiting.until(routeButton);
		routeButton.click();
		
		Waiting.until(suggestedRoute);
		
		return this;
	}
	
	public GoogleMapPage waitPageLoad() throws InterruptedException {
		Thread.sleep(3000);
		Waiting.until(button_SignIn);
		
		return this;
	}
	
	public boolean searchResultTitleContains(String mapSearchResultPerficient) throws Exception {
		log.info("expected title="+mapSearchResultPerficient);

		log.info("actual title="+this.getTitle());
		return this.titleIs(mapSearchResultPerficient);
	}
	
	public boolean restaurantAddressContains(String mapSearchResultZipcode) throws Exception {
		log.info("expected zipcode="+mapSearchResultZipcode);
		log.info("actual address="+restaurantAddress.getText());
		return restaurantAddress.getText().contains(mapSearchResultZipcode);
	}
}
