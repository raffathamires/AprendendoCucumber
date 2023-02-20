package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features = "src/test/java/features/inserir_conta.feature",
        features = "src/test/java/features/", //rodar toda suite de testes
        glue = "steps",
        tags = "not @ignore",
        plugin = {"pretty", "html: target/report-html"},
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false
)
public class Runner {

    @BeforeClass
    public static void reset(){
        WebDriver driver;

        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me");
        driver.findElement(By.id("email")).sendKeys("raffaela.monteiro@gmail.com");
        driver.findElement(By.id("senha")).sendKeys("doddy");
        driver.findElement(By.tagName("button")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.quit();
    }
}
