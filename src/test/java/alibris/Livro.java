package alibris;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Livro {
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","drivers/chrome/88/chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void consultaLivro() {
        driver.get("https://www.alibris.com/");
        driver.findElement(By.id("searchBox")).click();
        driver.findElement(By.id("searchBox")).sendKeys("the art of software testing");
        driver.findElement(By.id("searchBox")).sendKeys(Keys.ENTER);
        driver.findElement(By.linkText("The Art of Software Testing")).click();
        assertThat(driver.findElement(By.cssSelector("h1")).getText(), is("The Art of Software Testing"));
        assertThat(driver.findElement(By.cssSelector(".product-container:nth-child(1) .price > p")).getText(), is("$130.95 $167.00"));
    }
}

