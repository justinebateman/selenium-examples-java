package classes;

import common.Common;
import objrepo.LoginObjects;
import org.openqa.selenium.WebElement;
import setup.SetUp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

//import setup.SetUp;

public class Login
{
    static SetUp setup = new SetUp();
    public static String url = setup.BASE_URL + "/login";
    public String username = "";
    public String password = "";

    /**
     * testScenario = 1 = valid login
     * testScenario = 2 = invalid login, username incorrect
     * testScenario = 3 = invalid login, password incorrect
     */
    public int testScenario = 1;


    public void checkValidLogin() throws Exception
    {
        // check URL and Page Title
        checkSecurepage();

        // check info message
        String validLogin = "You logged into a secure area!";
        checkMessageText(validLogin);
    }

    public void checkInvalidLogin(String pExpectedError) throws Exception
    {
        // check URL and page title
        checkLoginPage();

        // check info message text matches expected
        checkMessageText(pExpectedError);
    }

    public void checkLoginPage() throws Exception
    {
        // check URL
        String expectedURL = url;
        Common.checkURL(setup.driver, expectedURL);

        // check page title
        String expectedTitle = "Login Page";
        checkPageTitle(expectedTitle);
    }

    public void checkSecurepage() throws Exception
    {
        // check URL
        String expectedURL = setup.BASE_URL + "/secure";
        Common.checkURL(setup.driver, expectedURL);

        // check page title
        String expectedTitle = "Secure Area";
        checkPageTitle(expectedTitle);
    }

    public void checkPageTitle(String pExpectedTitle)
    {
        // check page title matches expected
        String pageTitle = getPageTitle();
        assertThat(pageTitle, containsString(pExpectedTitle));
    }

    public String getPageTitle()
    {
        // get page title and return
        String pageTitle = setup.driver.findElement(LoginObjects.lblPageTitle).getText();
        return pageTitle;
    }

    public String getMessageText()
    {
        // get message text and return
        WebElement msg = setup.driver.findElement(LoginObjects.lblNotification);
        String messageText = msg.getText();
        return messageText;
    }

    public void checkMessageText(String pExpectedMessage)
    {
        // check text matches expected
        String messageText = getMessageText();
        assertThat(messageText, containsString(pExpectedMessage));
    }
}
