package SmartbuyTestingProject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases {

	WebDriver driver = new ChromeDriver();
	String SmartbuyURL = "https://smartbuy-me.com/";

	Random rand = new Random();

	String password = "Password@123";
	String emailAddress = " ";

	@BeforeTest
	public void mySetup() {

		driver.manage().window().maximize();
		driver.get(SmartbuyURL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 1)
	public void SignUp() {

		WebElement MyAccountButton = driver.findElement(By.xpath("//a[normalize-space()='My account']"));
		MyAccountButton.click();
		WebElement CreateAccount = driver.findElement(By.cssSelector("a[class='link link--accented']"));
		CreateAccount.click();

		String[] FirstNames = { "Alice", "Bob", "Charlie", "Diana", "Ethan" };
		String[] LastNames = { "Smith", "Johnson", "Williams", "Brown", "Jones" };

		int randomIndexForTheFirstName = rand.nextInt(FirstNames.length);
		int randomIndexForTheLastName = rand.nextInt(LastNames.length);

		WebElement firstNameInput = driver.findElement(By.id("customer[first_name]"));
		WebElement lastNameInput = driver.findElement(By.id("customer[last_name]"));
		WebElement emailAddressInput = driver.findElement(By.id("customer[email]"));
		WebElement passwordInput = driver.findElement(By.id("customer[password]"));

		String firstName = FirstNames[randomIndexForTheFirstName];
		String lastName = LastNames[randomIndexForTheLastName];

		firstNameInput.sendKeys(firstName);
		lastNameInput.sendKeys(lastName);
		int randomnumber = rand.nextInt(999);
		emailAddressInput.sendKeys(firstName + lastName + randomnumber + "@gmail.com");
		passwordInput.sendKeys(password);
		emailAddress = firstName + lastName + randomnumber + "@gmail.com";

		WebElement CreatMyAccountButton = driver
				.findElement(By.cssSelector(".form__submit.button.button--primary.button--full"));
		CreatMyAccountButton.click();
		System.out.println(emailAddress);

		String WelcomeMessage = driver
				.findElement(By.cssSelector(".header__action-item-title.hidden-pocket.hidden-lap")).getText();

		boolean ActualValue = WelcomeMessage.contains("Hello");
		boolean ExpectedValue = true;
		Assert.assertEquals(ActualValue, ExpectedValue);

		System.out.println(WelcomeMessage);

	}

	@Test(priority = 2)
	public void Logout() throws InterruptedException {

		WebElement MyAccount = driver.findElement(By.cssSelector(".header__action-item-link.hidden-pocket.hidden-lap"));
		MyAccount.click();
		WebElement LogoutButton = driver
				.findElement(By.cssSelector("div[class='card card--sticky hidden-pocket'] a:nth-child(3)"));
		LogoutButton.click();
		String headerMessage = driver.findElement(By.cssSelector(".header__action-item-title.hidden-pocket.hidden-lap"))
				.getText();

		boolean ActualValue = headerMessage.contains("Login");
		boolean ExpectedValue = true;

		assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 3)
	public void SignIn() {

		WebElement loginPage = driver.findElement(By.xpath("//a[normalize-space()='My account']"));
		loginPage.click();

		WebElement emaillogin = driver.findElement(By.id("customer[email]"));
		WebElement passwordlogin = driver.findElement(By.id("customer[password]"));

		emaillogin.sendKeys(emailAddress);
		passwordlogin.sendKeys(password);
		WebElement loginButton = driver.findElement(By.cssSelector("form[id='customer_login'] button[type='submit']"));
		loginButton.click();

		String WelcomeMessage = driver
				.findElement(By.cssSelector(".header__action-item-title.hidden-pocket.hidden-lap")).getText();

		boolean ActualValue = WelcomeMessage.contains("Hello");
		boolean ExpectedValue = true;
		Assert.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 4)
	public void CheckSearchBar() {

		WebElement SearchBar = driver.findElement(By.xpath("//input[@placeholder='Search...']"));

		String[] smartphoneBrands = { "iPhone", "Samsung", "Huawei" };
		int randomIndex = rand.nextInt(smartphoneBrands.length);
		String randomBrand = smartphoneBrands[randomIndex];
		SearchBar.sendKeys(randomBrand);

		WebElement SearchButton = driver.findElement(By.cssSelector("button[aria-label='Search']"));
		SearchButton.click();

		String resultsPageMsg = driver.findElement(By.cssSelector(".collection__title.heading.h1")).getText();

		boolean ActualMessage = resultsPageMsg.contains("Products");
		boolean ExpectedValue = true;
		assertEquals(ActualMessage, ExpectedValue);

	}

	@Test(priority = 5)
	public void AddToCart() throws InterruptedException {

		// Add first item to the cart

		WebElement ItemsContainer = driver.findElement(By.cssSelector(".product-list.product-list--collection"));
		List<WebElement> ListOfItems = ItemsContainer.findElements(By.tagName("a"));

		WebElement firstItem = ListOfItems.get(0);
		firstItem.click();

		System.out.println(ListOfItems.size());

		WebElement AddToCartButton = driver.findElement(By.xpath("//button[normalize-space()='Add to cart']"));
		AddToCartButton.click();
		Thread.sleep(3000);
		String MessageAdded = driver.findElement(By.cssSelector(".alert.alert--success")).getText();

		System.out.println(MessageAdded);
		boolean ActualValue = MessageAdded.contains("added");
		boolean ExpectedValue = true;

		assertEquals(ActualValue, ExpectedValue);

	}

}
