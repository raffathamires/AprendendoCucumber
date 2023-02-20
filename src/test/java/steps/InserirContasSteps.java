package steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
    @Então("a conta é inserida com sucesso")
    public void a_conta_é_inserida_com_sucesso() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", texto);
    }

    @Então("sou notificado que o nome da conta é obrigatório")
    public void souNotificarQueONomeDaContaÉObrigatório() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Informe o nome da conta", texto);
    }

    @Então("sou notificado que já existe uma conta com esse nome")
    public void souNotificadoQueJáExisteUmaContaComEsseNome() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!", texto);
    }

    @After
    public void fecharNavegador() {
        driver.quit();
    }

}
