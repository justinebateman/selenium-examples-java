package common;

import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

// A class to hold common re-usable functions
public class Common
{
    public static void checkURL(WebDriver pdriver, String pExpectedURL) throws Exception
    {
        // check URL
        Thread.sleep(1000);
        String url = pdriver.getCurrentUrl();
        assertThat(url, containsString(pExpectedURL));
    }
}
