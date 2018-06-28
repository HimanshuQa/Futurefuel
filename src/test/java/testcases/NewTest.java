package testcases;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;

public class NewTest {

	WebDriver driver;
	JavascriptExecutor js;

	@Test
	public void f() throws InterruptedException {
		js = (JavascriptExecutor) driver;

		RemoteWebElement video = (RemoteWebElement) js
				.executeScript("return document.querySelector('button[data-target=\"#videoModal\"]')");
		js.executeScript("return document.querySelector('button[data-target=\"#videoModal\"]').click()");
		
		Actions action = new Actions(driver);
		
		Thread.sleep(10000); // let the video play
		action.moveToElement(video).build().perform();

		driver.switchTo().frame("iframeVideo");

		RemoteWebElement like_button = (RemoteWebElement) js
				.executeScript("return document.querySelector('button.like-button.rounded-box')");
		RemoteWebElement watch_later_button = (RemoteWebElement) js
				.executeScript("return document.querySelector('button.watch-later-button.rounded-box')");
		RemoteWebElement share_button = (RemoteWebElement) js
				.executeScript("return document.querySelector('button.share-button.rounded-box')");

		
		if (js.executeScript("return document.querySelector('div.vp-sidedock').hidden").equals(false)) {
			
			System.out.println("Side Dock found");

			action.moveToElement(like_button).build().perform();
			
			//assert if the label is visible while moving mouse on like button
			Assert.assertFalse(
					(Boolean) js.executeScript("return document.querySelector('label.rounded-box.like-label').hidden"));
			
			action.moveToElement(watch_later_button).build().perform();
			
			//assert if the label is visible while moving mouse on watch button		
			Assert.assertFalse(
					(Boolean) js.executeScript("return document.querySelector('button.watch-later-button.rounded-box').hidden"));

			action.moveToElement(share_button).build().perform();
			
			//assert if the label is visible while moving mouse on share button
			Assert.assertFalse(
					(Boolean) js.executeScript("return document.querySelector('button.share-button.rounded-box').hidden"));

			
		} else {
			System.out.println("Side dock not found");

		}

	}

	@BeforeClass
	public void afterClass() {
		System.setProperty("webdriver.chrome.driver", "/home/himanshuchaudhary/Downloads/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://futurefuel.io/");
	}

	@AfterClass
	public void afterTest() throws InterruptedException {
		Thread.sleep(5000);
		driver.close();
	}
}
