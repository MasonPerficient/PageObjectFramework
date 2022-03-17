package google.testcase.search;

import org.testng.Assert;
import org.testng.annotations.Test;
import google.basepages.GoogleHomePage;
import google.basepages.GoogleWebSearchResultPage;
import google.basepages.PerficientHomePage;
import google.reusablefunction.OpenHomePage;
import util.FunctionUtil;
import util.TestCaseBase;
import util.TestData;

public class TC_01_GoogleWebSearch extends TestCaseBase {
	
	@Test(groups = {"ChromeWin32"})
	public void testSearchWeb() throws Exception {
		String searchItem = TestData.get("input.search");
		
		GoogleHomePage googleHomePage = OpenHomePage.goToGoogleHome();
		Assert.assertTrue(googleHomePage.titleIs(GoogleHomePage.TITLE));

		GoogleWebSearchResultPage googleWebSearchResultPage = googleHomePage.search(searchItem);
		Assert.assertTrue(googleWebSearchResultPage.titleIs(searchItem));

		PerficientHomePage perficientHomePage = googleWebSearchResultPage.clickFirstLink();
		FunctionUtil.switchToNewWindow();//switch to new window
		Assert.assertTrue(perficientHomePage.titleIs(PerficientHomePage.TITLE));
	}
}
