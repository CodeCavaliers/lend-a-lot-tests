package com.codecavaliers.lendALot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest {

	static WebDriver driver;

	@BeforeClass
	public static void before() {
		// set web driver
		System.setProperty("webdriver.chrome.driver", "C:/Users/gheorghe/projects/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();

		// Launch the first screen of the app
		driver.get("http://localhost:3449/index.html");

		// Print a Log In message to the screen
		System.out.println("Successfully opened the website http://localhost:3449/index.html");

	}

	@Test
	// testing the name of the App
	public void testNameOfApp() {

		before();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[1]/h1")));
		WebElement findElement = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/h1"));

		assertEquals("LendALot", findElement.getText());
		assertNotEquals("LendALott", findElement.getText());
		after();
	}

	@Test
	// testing if add button redirects to the second screen
	public void testAddButton() {
		before();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[2]")));
		WebElement click = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/button"));
		assertEquals(true, click.isEnabled());
		click.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[1]")));
		WebElement clickResult = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]"));
		assertEquals(true, clickResult.isDisplayed());
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[1]")));

		WebElement clickRestult2 = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[1]"));
		assertEquals(true, clickRestult2.isDisplayed());
		after();
	}

	@BeforeClass
	public static void beforeP2() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/gheorghe/projects/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();

		// Launch the second screen of the app
		driver.get("http://localhost:3449/index.html#/new");

		// Print a Log In message to the screen
		System.out.println("Successfully opened the website http://localhost:3449/index.html");

	}

	@Test
	// test if addName field is found and can be clicked
	public void testAddName() {
		beforeP2();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[1]")));
		WebElement firstName = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[1]"));
		assertEquals("First Name", firstName.getText());

		after();
	}

	@Test
	// test if the text of the field addName is saved in the field after
	// introducing name
	public void testIntroduceName() {
		beforeP2();
		WebDriverWait wait = new WebDriverWait(driver, 10);

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[1]")));
		WebElement firstName = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/div[1]/div/div[1]"));
		firstName.click();

		// add text to the field
		Actions actions = new Actions(driver);
		actions.moveToElement(firstName);
		actions.click();
		actions.sendKeys("Some Name");
		actions.build().perform();

		WebElement firstName1 = driver.findElement(By.xpath("(//*[@id=\"app\"]/div/div[2]/div[1]/div/div[1])[2]"));
		assertEquals("Some Name", firstName1.getText());

		after();

	}

	@AfterClass
	public static void after() {
		// Close the driver
		driver.quit();

	}

}
