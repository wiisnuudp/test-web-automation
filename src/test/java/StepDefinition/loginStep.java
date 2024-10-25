package StepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;

public class loginStep {

    WebDriver driver;
    @Before
    public void setup() {
        System.setProperty("Webdriver.gecko.driver",
                System.getProperty("user.dir")
                        + "\\src\\test\\java\\Drivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

//    @Step ("test case login positive")
    @Given("user is on login page")
    public void user_is_on_login_page() throws InterruptedException {
        driver.get("https://kasirdemo.vercel.app/login");
        Thread.sleep(1000);
    }

    @When("user enter valid username and password")
    public void user_enter_valid_username_and_password() {
        driver.findElement(By.id("email")).sendKeys("halodek@gmail.com");
        driver.findElement(By.id("password")).sendKeys("halodek123");
    }

    @And("user click login button")
    public void user_click_login_button() {
        driver.findElement(By.xpath("//button[text()='login']")).click();
    }

    @Then("user must redirect to dashboard page")
    public void user_must_redirect_to_dashboard_page() {
        // wait for element presented
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h3[text()='kasirAja']")));
        element.getText();

        String currentURL = driver.getCurrentUrl();


        // Assert the URL
        String expectedURL = "https://kasirdemo.vercel.app/dashboard";

        Assert.assertEquals(currentURL, expectedURL);
    }

    @Step("test case negative")

    @When("user enter valid test@email.com and asasas")
    public void user_enter_valid_test_email_com_and_asasas() {
        driver.findElement(By.id("email")).sendKeys("test@email.com");
        driver.findElement(By.id("password")).sendKeys("asasas");
    }

    @When("user enter valid testing@email.com and btktbbtk")
    public void user_enter_valid_testing_email_com_and_btktbbtk() {
        driver.findElement(By.id("email")).sendKeys("testing@email.com");
        driver.findElement(By.id("password")).sendKeys("btktbbtk");
    }

    @Then("system showing error message")
    public void system_showing_error_message() {

        // wait for element presented
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[text()='Kredensial yang Anda berikan salah']")));
        element.getText();
        Assert.assertEquals("Kredensial yang Anda berikan salah", "Kredensial yang Anda berikan salah");
    }


}
