package setup;

import common.Common;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by justine.bateman on 25/05/2017.
 */
public class SetUp
{

    public static String NODE_URL = "http://localhost:4444/wd/hub";
    public static String BASE_URL = "http://the-internet.herokuapp.com";

    // selenium driver
    public static WebDriver driver;
    public String browserName;

    @Parameters({"browserName", "platform"})
    @BeforeMethod
    public void setUp(@Optional("firefox") String pBrowserName, @Optional("WINDOWS") String pPlatform) throws Exception
    {
        browserName = pBrowserName;
        DesiredCapabilities capability = null;

        // set browser
        if (pBrowserName.equals("firefox"))
            capability = DesiredCapabilities.firefox();
        else if (pBrowserName.equals("chrome"))
            capability = DesiredCapabilities.chrome();
        else if (pBrowserName.equals("iexplore"))
            capability = DesiredCapabilities.internetExplorer();
        else if (pBrowserName.equals("opera"))
            capability = DesiredCapabilities.operaBlink();

        // set platform
        if (pPlatform.equals("XP"))
            capability.setPlatform(Platform.XP);
        else if (pPlatform.equals("WINDOWS"))
            capability.setPlatform(Platform.WINDOWS);

        // start driver
        driver = new RemoteWebDriver(new URL(NODE_URL), capability);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // open URL
        driver.get(BASE_URL);

        // check URL
        Common.checkURL(driver, BASE_URL);
    }

    @AfterMethod(alwaysRun = true)
    public void teardown()
    {
        // close browser
        driver.quit();
    }
}
