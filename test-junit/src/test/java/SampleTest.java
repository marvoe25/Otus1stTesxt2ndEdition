import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleTest {

    private Logger logger = LogManager.getLogger(SampleTest.class); //объявляем экземпляр логгера из Лог4дж
    protected static WebDriver driver;
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    String expectedText = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";

    @Test
    public void Log() {
        logger.info("This is info."); //настройки отображаемости выставляются в log4j2
        logger.fatal("This is fatal!"); // красное сообщение
        logger.debug("This is debug:)");// при ИНФО - не выведет, при ДЕБАГЕ - зеленый текст
    }

    @Before //перед каждым методом
    public void setUp() { //инициализация хромдрайвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят.");
    }

    @Test
    public void openPage() {
        driver.get(cfg.url());//("https://otus.ru/");
        logger.info("The page is opened:)");
        if (driver.getTitle().contains(expectedText)) {
            //Pass
            // System.out.println("Page title contains"+expectedText);
            logger.info("Page title contains: " + driver.getTitle());// expectedText);
        } else {
            //Fail
            //System.out.println("Page title doesn't contains " + expectedText);
            logger.error("Page title doesn't contains expected text.");
        }

    }

    @After
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }


    }
}
