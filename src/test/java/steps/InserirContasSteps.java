package steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class InserirContasSteps {

    private WebDriver driver;

    @Dado("que estou acessando a aplicação")
    public void que_estou_acessando_a_aplicação() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\raffa\\Documents\\Cursos\\AprendendoCucumber\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me");
    }
    @Quando("^informo o usuário (.*)$")
    public void informo_o_usuário(String arg1) throws Throwable{
        driver.findElement(By.id("email")).sendKeys(arg1);
    }
    @Quando("a senha {string}")
    public void a_senha(String arg1) {
        driver.findElement(By.id("senha")).sendKeys(arg1);
    }
    @Quando("seleciono entrar")
    public void seleciono_entrar() {
        driver.findElement(By.tagName("button")).click();
    }
    @Então("visualizo a página inicial")
    public void visualizo_a_página_inicial() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Bem vindo, Raffaela Monteiro!", texto);
    }
    @Quando("seleciono Contas")
    public void seleciono_contas() {
        driver.findElement(By.linkText("Contas")).click();
    }
    @Quando("seleciono Adicionar")
    public void seleciono_adicionar() {
        driver.findElement(By.linkText("Adicionar")).click();
    }
    @Quando("^informo a conta (.*)$")
    public void informo_a_conta(String arg1) {
        driver.findElement(By.id("nome")).sendKeys(arg1);
    }
    @Quando("seleciono Salvar")
    public void seleciono_salvar() {
        driver.findElement(By.tagName("button")).click();
    }

    @Entao("recebo a mensagem {string}")
    public void receboAMensagem(String arg1){
        String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
        Assert.assertEquals(arg1, texto);
    }

    @After(order = 1, value = "not @unitarios")
    public void capturaDeTela(Scenario cenario){
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, new File("target/capturasDeTela/"+cenario.getId()+".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @After(order = 0, value = "not @unitarios")
    public void fecharNavegador() {
        driver.quit();
    }


}
