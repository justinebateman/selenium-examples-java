package classes;

import common.Common;
import objrepo.CheckboxObjects;
import setup.SetUp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class Checkbox
{
    static SetUp setup = new SetUp();
    public static String url = setup.BASE_URL + "/checkboxes";

    public void checkCheckboxesPage() throws Exception
    {
        // check URL
        String expectedURL = url;
        Common.checkURL(setup.driver, expectedURL);

        // check page title
        String expectedTitle = "Checkboxes";
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
        String pageTitle = setup.driver.findElement(CheckboxObjects.lblPageTitle).getText();
        return pageTitle;
    }

}
