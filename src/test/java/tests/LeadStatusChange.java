package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Krzysiek on 2016-06-08.
 */
public class LeadStatusChange{

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void openBrowser() {

        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void TestCase()
    {
        driver.navigate().to("https://futuresimple.com");
        driver.findElement(By.linkText("Log in")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_email")));
        driver.findElement(By.id("user_email")).sendKeys("krzysiekwadrzyk@gmail.com");
        driver.findElement(By.id("user_password")).sendKeys("krzysiek1990");
        driver.findElement(By.cssSelector("button.btn.btn-large.btn-primary")).click();
        Assert.assertEquals(driver.findElement(By.className("user-avatar")).getText(),"KW");

        driver.findElement(By.id("nav-leads")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("leads-new")));
        driver.findElement(By.id("leads-new")).click();
        driver.findElement(By.id("lead-first-name")).sendKeys("Krzysztof");
        driver.findElement(By.id("lead-last-name")).sendKeys("Wadrzyk");
        driver.findElement(By.cssSelector("button.save.btn.btn-large.btn-primary")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.className("lead-status")));

        Assert.assertEquals(driver.findElement(By.className("lead-status")).getText(),"New");

        driver.findElement(By.className("base-icon-arrow-down")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Settings")));
        driver.findElement(By.linkText("Settings")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Leads")));
        driver.findElement(By.linkText("Leads")).click();
        driver.findElement(By.linkText("Lead Statuses")).click();
        driver.findElement(By.cssSelector("button.btn.btn-mini.edit")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Leads")));

        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div[4]/div[1]/span[1]/div/div/form/fieldset/div[2]/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div[4]/div[1]/span[1]/div/div/form/fieldset/div[2]/div/input")).sendKeys("New_v2");
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div[4]/div[1]/span[1]/div/div/form/fieldset/div[3]/div/button")).click();

        driver.findElement(By.id("nav-leads")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Krzysztof Wadrzyk")));
        driver.findElement(By.linkText("Krzysztof Wadrzyk")).click();

        Assert.assertEquals(driver.findElement(By.className("lead-status")).getText(),"New_v2");

    }

    @After
    public void closeBrowser(){

        driver.quit();

    }

}
