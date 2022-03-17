package google.basepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Page;
import util.TestCaseBase;
import util.Waiting;

public class GoogleWebSearchResultPage extends Page {

	@FindBy(xpath = "//*[@id=\"rso\"]/div[1]/div/div/div/div/div/div/div[1]/a")
	public WebElement firstLink;
	
	public boolean titleIs(String title) {
		title += " - Google Search";
		
		log.info("expected title="+title);
		log.info("actual title="+getTitle());
		
		return (getTitle().equals(title));
	}

	public PerficientHomePage clickFirstLink() throws Exception {
		Waiting.until(firstLink);		
		
		if (TestCaseBase.browserFlag.equals("ie")) {
			firstLink.sendKeys("\n");
			//firstLink.click();
		} else {
			firstLink.click();
		}
		
		return new PerficientHomePage();
	}

	public GoogleWebSearchResultPage waitPageLoad() throws InterruptedException {
		//Sleep one second to avoid stale reference exception that occurs when getting references to dynamic elements while they update
		Thread.sleep(1000);
		Waiting.until(firstLink);
		
		return this;
	}

}
