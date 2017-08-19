import classes.Checkbox;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckboxTests extends setup.SetUp
{
    static String TEST_URL = Checkbox.url;

    Checkbox checkbox = new Checkbox();

    @BeforeMethod
    public void openURL() throws Exception
    {
        driver.get(TEST_URL);
        checkbox.checkCheckboxesPage();
    }

    @Test(priority = -1)
    // smoke test that the page can be accessed
    public void smokeTest() throws Exception
    {

    }
    
}
