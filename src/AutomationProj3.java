import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutomationProj3 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();


        driver.get("https://www.carfax.com/");
//        Click on Find a Used Car
        driver.findElement(By.xpath("//a[.='Find a Used Car']")).click();
//        Verify the page title contains the word “Used Cars”
        Assert.assertTrue(driver.getTitle().contains("Used Cars"));
//        4.	Choose “Tesla” for  Make.
        Select select = new Select(driver.findElement(By.name("make")));
        select.selectByValue("Tesla");

//        5.	Verify that the Select Model dropdown box contains 4 current Tesla models - (Model 3, Model S, Model X, Model Y).
        List<String> expected = Arrays.asList(" Model 3 ", " Model S ", " Model X ", " Model Y ");
//        driver.findElement(By.name("model")).click();
        List<WebElement> list = driver.findElements(By.xpath("//select[@name='model']/optgroup[@class='current-models']//option"));
        Thread.sleep(1500);
        List<String> actual = new ArrayList<>();
        Thread.sleep(1500);
        for (WebElement model : list) {
            actual.add(model.getText());
        }
        Thread.sleep(4000);
        Assert.assertEquals(actual, expected);

//        6.	Choose “Model S” for Model.
        driver.findElement(By.xpath("//select[@name='model']/optgroup[@class='current-models']//option[@id='model_Model-S']")).click();
//        7.	Enter the zip code 22182 and click Next
        driver.findElement(By.xpath("//input[@name='zip']")).sendKeys("22182");
        driver.findElement(By.xpath("//button[@id='make-model-form-submit-button']")).click();
//        8.	Verify that the page contains the text “Step 2 – Show me cars with”
        Assert.assertTrue(driver.getPageSource().contains("Show me cars with"));
        Thread.sleep(3000);
//        9.	Check all 4 checkboxes.
        List<WebElement> chkbxes = driver.findElements(By.xpath("//span[@role='checkbox']"));
        for (WebElement chkbx : chkbxes) {
            chkbx.click();
        }
        Thread.sleep(3000);

//        10.	Save the count of results from “Show me X Results” button. In this case it is 10.
        String resultsActualQty = driver.findElement(By.xpath("//button[@class='button large primary-green show-me-search-submit']//span")).getText();


//        11.	Click on “Show me x Results” button.
        driver.findElement(By.xpath("//span[@class='totalRecordsText']")).click();
        Thread.sleep(3000);
//        12.	Verify the results count by getting the actual number of results displayed in the page by getting the count of WebElements that represent each result
        List<WebElement> resultsList = driver.findElements(By.xpath("//div[@class='srp-list-container srp-list-container--srp']//article//h4"));
        System.out.println(resultsList.size());
//        13.	Verify that each result header contains “Tesla Model S”.
        Thread.sleep(3000);
        for (WebElement webElement : resultsList) {
            Assert.assertTrue(webElement.getText().contains("Tesla Model S"));
        }
        Thread.sleep(1500);

//        14.	Get the price of each result and save them into a List in the order of their appearance. (You can exclude “Call for price” options)
        List<WebElement> prices = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
        List<String> prices2 = new ArrayList<>();
        for (WebElement price : prices) {
            String webelmTxt = price.getText().substring(8);
            prices2.add(webelmTxt);
        }
//        15.	Choose “Price - High to Low” option from the Sort By menu
//        16.	Verify that the results are displayed from high to low price.
        Select select2 = new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']")));
        select2.selectByVisibleText("Price - High to Low ");
        Thread.sleep(1000);

        List<WebElement> sortedActual = driver.findElements(By.xpath("//span[@class='srp-list-item-price']"));
        Thread.sleep(1000);
        List<Double> sortedActual2 = new ArrayList<>();
        for (WebElement webElement : sortedActual) {
            Double v = Double.parseDouble(webElement.getText().replace(",", "").substring(8));
            sortedActual2.add(v);
        }
        for (int i=1; i<sortedActual2.size()-1; i++) {
            Assert.assertTrue(sortedActual2.get(i) <= sortedActual2.get(i-1));
        }
//        17.	Choose “Mileage - Low to High” option from Sort By menu
        Select select3 = new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']")));
        select3.selectByVisibleText("Mileage - Low to High ");
        Thread.sleep(1000);

//        18.	Verify that the results are displayed from low to high mileage.
        List<WebElement> sortedByMlg1 = driver.findElements(By.xpath("//div[@class='srp-list-item-basic-info srp-list-item-special-features']//span[contains(text(), 'miles')]"));
        List<Integer> sortedByMlg2 = new ArrayList<>();

        for (WebElement webElement : sortedByMlg1) {
            Integer i=Integer.parseInt(webElement.getText().replace("Mileage: ", "").replace(" miles", "").replace(",", ""));
            sortedByMlg2.add(i);
        }
        for (int i=1; i<sortedByMlg2.size()-1; i++) {
            Assert.assertTrue(sortedByMlg2.get(i) >= sortedByMlg2.get(i - 1));
        }

//        19.	Choose “Year - New to Old” option from Sort By menu
        Select select4 = new Select(driver.findElement(By.xpath("//select[@aria-label='SelectSort']")));
        select4.selectByVisibleText("Year - New to Old ");
        Thread.sleep(1000);

//        20.	Verify that the results are displayed from new to old year.
        List<WebElement> sortedByYr1 = driver.findElements(By.xpath("//span[contains(text(), 'Description')]//following-sibling::span"));
        List<Integer> sortedByYr2 = new ArrayList<>();

        for (WebElement webElement : sortedByYr1) {
            Integer year = Integer.parseInt(webElement.getText().substring(5, 9));
            sortedByYr2.add(year);
        }

        for (int i=1; i<sortedByYr2.size()-1; i++) {
            Assert.assertTrue(sortedByYr2.get(i) <= sortedByYr2.get(i - 1));
        }


    }
}
