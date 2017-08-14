import classes.Login;
import objrepo.LoginObjects;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends setup.SetUp
{
    static String TEST_URL = Login.url;
    String validUsername = "tomsmith";
    String validPassword = "SuperSecretPassword!";
    String incorrectUsername = "wrongusername";
    String incorrectPassword = "wrongpassword";

    Login login = new Login();

    @BeforeMethod
    public void openURL() throws Exception
    {
        driver.get(TEST_URL);
        login.checkLoginPage();
    }

    @Test
    // smoke test that the page can be accessed
    public void smokeTest() throws Exception
    {

    }

    @Test
    // valid login details
    public void validLogin() throws Exception
    {
        Login loginData = new Login();
        loginData.username = validUsername;
        loginData.password = validPassword;
        loginData.testScenario = 1;
        loginSteps(loginData);
    }

    @Test
    // enter wrong username
    public void invalidUsername() throws Exception
    {
        Login loginData = new Login();
        loginData.username = incorrectUsername;
        loginData.password = validPassword;
        loginData.testScenario = 2;
        loginSteps(loginData);
    }

    @Test
    // enter wrong password
    public void invalidPassword() throws Exception
    {
        Login loginData = new Login();
        loginData.username = validUsername;
        loginData.password = incorrectPassword;
        loginData.testScenario = 3;
        loginSteps(loginData);
    }

    @Test
    // enter valid password but no username
    public void blankUsername() throws Exception
    {
        Login loginData = new Login();
        loginData.username = "";
        loginData.password = validPassword;
        loginData.testScenario = 2;
        loginSteps(loginData);
    }

    @Test
    // enter valid username but no password
    public void blankPassword() throws Exception
    {
        Login loginData = new Login();
        loginData.username = validUsername;
        loginData.password = "";
        loginData.testScenario = 3;
        loginSteps(loginData);
    }

    @Test
    // enter no username or password
    public void blankUsernameAndPassword() throws Exception
    {
        Login loginData = new Login();
        loginData.username = "";
        loginData.password = "";
        loginData.testScenario = 2;
        loginSteps(loginData);
    }

    @Test
    // login and logout
    public void validlogout() throws Exception
    {
        Login loginData = new Login();
        loginData.username = validUsername;
        loginData.password = validPassword;
        loginData.testScenario = 1;
        loginSteps(loginData);
        logout();
    }


    @Test
    // navigate to the secure area without logging in first
    public void securePageURL() throws Exception
    {
        driver.get(BASE_URL + "/secure");

        // check URL and page title
        login.checkLoginPage();
    }


    public void loginSteps(Login pLogin) throws Exception
    {
        // p_valid = 1 = valid login
        // p_valid = 2 = invalid login, username incorrect
        // p_valid = 3 = invalid login, password incorrect

        // enter username
        driver.findElement(LoginObjects.txtUsername).sendKeys(pLogin.username);
        // enter password
        driver.findElement(LoginObjects.txtPassword).sendKeys(pLogin.password);
        // click login
        driver.findElement(LoginObjects.btnLogin).click();
        Thread.sleep(1000);

        switch (pLogin.testScenario)
        {
            case 1:
                // valid login
                pLogin.checkValidLogin();
                break;
            case 2:
                // invalid username
                String invalidUsername = "Your username is invalid!";
                pLogin.checkInvalidLogin(invalidUsername);
                break;
            case 3:
                // invalid password
                String invalidPassword = "Your password is invalid!";
                pLogin.checkInvalidLogin(invalidPassword);
                break;
            default:
                // invalid option selected, needs to be 1 2 or 3
                Assert.fail("Invalid option");
        }

    }

    public void logout() throws Exception
    {
        // click the logout button
        driver.findElement(LoginObjects.btnLogout).click();

        // check URL
        login.checkLoginPage();

        // check info message
        String validLogout = "You logged out of the secure area!";
        login.checkMessageText(validLogout);
    }


}
