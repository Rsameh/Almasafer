package almosaferWeb;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class myTestCases {
	SoftAssert softassert = new SoftAssert();
	WebDriver driver = new ChromeDriver();
	String MyWebsite = "https://www.almosafer.com/en";
	Random rand = new Random();
	String[] ArabicCities = {"دبي","جدة"};
	String [] EnglishCities = {"Dubai","Jeddah","Riyadh"}; 
	int randomArabic = rand.nextInt(ArabicCities.length);
	int randomEnglish = rand.nextInt(EnglishCities.length);

	@BeforeTest
	public void SetUp() {
		driver.manage().window().maximize();
		driver.get(MyWebsite);
		WebElement WelcomeScreen = driver.findElement(By.cssSelector("button[class='sc-jTzLTM hQpNle cta__button cta__saudi btn btn-primary']"));
		WelcomeScreen.click();

	}

	@Test(enabled=false)
	public void CheckTheLanguage() {
		String ActualLanguage = driver.findElement(By.tagName("html")).getAttribute("lang");
		String ExpectedLanguage = "en";
		softassert.assertEquals(ActualLanguage,ExpectedLanguage);
		
	}
	
	@Test(enabled=false)
	public void CheckTheCuurrency() {
		WebElement CurrenyElement = driver.findElement(By.cssSelector(".sc-dRFtgE.fPnvOO"));
		String ActualCurrency = CurrenyElement.getText();
		String ExpectedCurrency="SAR";
		softassert.assertEquals(ActualCurrency, ExpectedCurrency);
	
	}
	
	@Test(enabled=false)
	public void CheckContactNumber() {
		String ExpectedNumber="+966554400000";
		WebElement 	ContactNumberElement = driver.findElement(By.cssSelector("a[class='sc-hUfwpO bWcsTG'] strong"));
		String ActualNumber = ContactNumberElement.getText();
		softassert.assertEquals(ActualNumber, ExpectedNumber);
	}
	
	@Test(enabled=false)
	public void CheckQitafLogo() {
		WebElement 	QitafLogo = driver.findElement(By.xpath("//div[@class='sc-dznXNo iZejAw']//*[name()='svg']"));
		
		boolean IsQitafLogoDesplayed = QitafLogo.isDisplayed();
		
		boolean ExpectedQitafLogo =true;
		softassert.assertEquals(IsQitafLogoDesplayed, ExpectedQitafLogo);
	}
	

	@Test(enabled=false)
	public void HotelTabNotSelected() {
		WebElement 	HotelTab = driver.findElement(By.cssSelector("#uncontrolled-tab-example-tab-hotels"));
		String ActualSelectArea = HotelTab.getAttribute("aria-selected");
		String ExpectedSelectArea = "false";
		softassert.assertEquals(ActualSelectArea, ExpectedSelectArea);
		
	}
	
	@Test(enabled=false)
	public void CheckTheDepartureAndArrivalDate() {
		WebElement 	DepartueDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-fvLVrH hNjEjT']"));
		WebElement 	ReturnDate = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-bYnzgO bojUIa'] span[class='sc-fvLVrH hNjEjT']"));
		
		int ActualDepartureDateValue = Integer.parseInt(DepartueDate.getText());
		int ActualReturnDateValue =Integer.parseInt( ReturnDate.getText());
		
		LocalDate today = LocalDate.now();
		today.plusDays(1);
		today.plusDays(2);
		
		int ExpectedDepartureDateValue = today.plusDays(1).getDayOfMonth();
		int ExpectedReturnDateValue = today.plusDays(2).getDayOfMonth();
		
		Assert.assertEquals(ActualReturnDateValue, ExpectedReturnDateValue);
		Assert.assertEquals(ActualDepartureDateValue, ExpectedDepartureDateValue);
		
		
		WebElement DayEelement = driver.findElement(By.cssSelector("div[class='sc-OxbzP sc-lnrBVv gKbptE'] span[class='sc-eSePXt ljMnJa']"));
		String ActualDay = DayEelement.getText().toUpperCase();
		String ExpectedDay = today.plusDays(1).getDayOfWeek().toString();
		
		Assert.assertEquals(ActualDay ,ExpectedDay);
			
	}
	
	@Test
	public void ChangeTheLnguage() throws InterruptedException {
		String [] MyUrls = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
		int randomindex = rand.nextInt(MyUrls.length);
		driver.get(MyUrls[randomindex]);
		String Actuallang = driver.findElement(By.tagName("html")).getAttribute("lang");
		
	
	WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	HotelTab.click();
	
	WebElement SearchInput = driver.findElement(By.className("phbroq-2"));
	
	if (driver.getCurrentUrl().contains("ar")) {
		Assert.assertEquals(Actuallang, "ar");
		SearchInput.sendKeys(ArabicCities[randomArabic]);
	} else {
		Assert.assertEquals(Actuallang, "en");
		SearchInput.sendKeys(EnglishCities[randomEnglish]);
		
	}
	Thread.sleep(2000);

	WebElement CityList = driver.findElement(By.className("phbroq-4"));
	List <WebElement> MyItems = CityList.findElements(By.tagName("li"));
	MyItems.get(1).click();
	
    
	WebElement VisitorInput = driver.findElement(By.className("tln3e3-1"));
	 Select myselect = new Select(VisitorInput);
	 myselect.selectByValue("A");
	
	
	
	}

	
	


	@AfterTest
	public void aftertest() {
		softassert.assertAll();

	}

}

