package google.testcase.search;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import google.basepages.GoogleMapPage;
import util.TestCaseBase;
import util.TestData;

public class TC_02_GoogleMapSearch extends TestCaseBase {
	
	@Test(groups = { "ChromeWin32" })
	public void testSearchMap() throws Exception {
		String mapSearchResultPerficient = TestData.get("result.perficient");
		String mapSearchInput = TestData.get("input");
		String restaurantZip = TestData.get("result.zipcode");
		
		
		GoogleMapPage googleMapPage = new GoogleMapPage();
		googleMapPage.open();
		
		assert (googleMapPage.titleIs(GoogleMapPage.TITLE));
		
		googleMapPage.search(mapSearchInput);
		Thread.sleep(1500);
		assert (googleMapPage.searchResultTitleContains(mapSearchResultPerficient));
		
		WebElement restaurant = googleMapPage.findRestaurant();
		restaurant.click();
		Thread.sleep(1500);
		assert (googleMapPage.restaurantAddressContains(restaurantZip));
	}
}
