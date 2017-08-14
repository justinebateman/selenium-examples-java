package objrepo;

import org.openqa.selenium.By;

public class LoginObjects
{
    final public static By lblPageTitle = By.xpath(".//*[@id='content']/div/h2");
    final public static By txtUsername = By.id("username");
    final public static By txtPassword = By.id("password");
    final public static By lblNotification = By.id("flash");
    final public static By btnLogin = By.className("radius");
    final public static By btnLogout = By.cssSelector("i.icon-2x.icon-signout");

}
