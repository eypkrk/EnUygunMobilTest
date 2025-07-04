package enuygunpom.test;

import enuygunpom.base.BaseTest;
import enuygunpom.page.FlyPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlyPageTest extends BaseTest {
    FlyPage flyPage;

    @BeforeClass
    public void before(){
        flyPage = new FlyPage(getAppiumDriver());
    }
    @Test
    public void test()throws Exception{
        flyPage.mainPage()
                //.flyScreen()
                .hotelsTab();
        Thread.sleep(2000);
    }
    @AfterClass
    public void teardowns(){
        teardown();
    }
}
