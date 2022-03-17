package google.basepages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.FunctionUtil;
import util.Page;
import util.TestCaseBase;
import util.Waiting;

public class PerficientHomePage extends Page {
	public static String TITLE = "Perficient, Inc. | End-to-End Digital Solutions for Global Enterprises / Perficient, Inc.";
	
	//Perficient logo
	@FindBy(xpath = ".//*[@class='logo']/img")
	public WebElement log_Perficient;
	
	public boolean titleIs(String title) {
		log.info ("expected result="+title);
		log.info ("actual result="+TestCaseBase.threadDriver.get().getTitle());
		return (TestCaseBase.threadDriver.get().getTitle().equals(title));
	}
	
	public PerficientHomePage waitPageLoad() {
//		Waiting.until(anyDiv);
		Waiting.until(log_Perficient);
		return this;
	}
	public PerficientHomePage switchToNewWindow() {
		// Switch to new window opened
		FunctionUtil.switchToNewWindow();
		return new PerficientHomePage();
	}
}
