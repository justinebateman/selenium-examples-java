import classes.Checkbox;
import objrepo.CheckboxObjects;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

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

    @Test
    public void checkDefault()
    {
        // expected results - first checkbox should not be ticked on page load, second checkbox should be ticked
        boolean expectedIsCheckbox1Ticked = false;
        boolean expectedIsCheckbox2Ticked = true;
        
        // get the actual results
        boolean actualIsCheckbox1Ticked = driver.findElement(CheckboxObjects.chkCheckbox1).isSelected();
        boolean actualIsCheckbox2Ticked = driver.findElement(CheckboxObjects.chkCheckbox2).isSelected();

        // compare expected and actual
        assertThat(expectedIsCheckbox1Ticked, equalTo(actualIsCheckbox1Ticked));
        assertThat(expectedIsCheckbox2Ticked, equalTo(actualIsCheckbox2Ticked));
    }

}
