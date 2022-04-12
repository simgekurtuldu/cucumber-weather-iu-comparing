package StepDefination;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BackendScripts;
import java.util.List;

public class GET_Request {
    static WebDriver driver;
    static BackendScripts backendScripts = new BackendScripts();
    static String hava_durumu_bilgisi;
    static String api_hava_durumu;
    @When("{string} {int} bilgilerine göre request atildiginda")
    public void bilgilerine_göre_request_atildiginda(String string, Integer int1) {
        System.out.println("*********************");
        System.out.println(backendScripts.getWeatherDetails(string,int1));
        api_hava_durumu = backendScripts.getApiWeatherDetails(backendScripts.getWeatherDetails(string,int1));
        System.out.println("-----------------------");
        System.out.println(api_hava_durumu);
    }
    @Then("Dort gunluk hava durumu tahmini gelmis olmalidir")
    public void dort_gunluk_hava_durumu_tahmini_gelmis_olmalidir() {

    }

    @Given("google.com sitesine gidilmis olmalidir")
    public void google_com_sitesine_gidilmis_olmalidir() {
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
    }
    @When("Arama kismina {string} hava durumu yazildigi zaman")
    public void arama_kismina_hava_durumu_yazildigi_zaman(String string) {
        WebElement arama = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        arama.sendKeys(string+" hava durumu");
        arama.sendKeys(Keys.ENTER);
    }
    @When("{int}. gunun hava durumuna bakildiginda")
    public void gunun_hava_durumuna_bakildiginda(Integer int1) {
        List<WebElement> lists = driver.findElements(By.className("wob_df"));
        lists.get(int1).click();
    }
    @Then("Uygun hava durumu gelmis olmalidir")
    public void uygun_hava_durumu_gelmis_olmalidir() {
        hava_durumu_bilgisi = driver.findElement(By.id("wob_dc")).getText();
        System.out.println("-----------------------");
        System.out.println(hava_durumu_bilgisi);
        Assert.assertEquals(api_hava_durumu,hava_durumu_bilgisi);
    }



}

