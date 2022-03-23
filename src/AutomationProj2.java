import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class AutomationProj2 {

    public static void main(String[] args) throws InterruptedException {
//        1. Launch Chrome browser.
        System.setProperty("webdriver.chrome.driver", "C:\\BrowserDrivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
//        2. Navigate to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
//        3. Login using username Tester and password test
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test" + Keys.ENTER);
//        4. Click on Order link
        driver.findElement(By.linkText("Order")).click();
//        5. Enter a random product quantity between 1 and 100
//        /html/body/form/table/tbody/tr/td[2]/div[2]/table/tbody/tr/td/ol[1]/li[2]/input
        driver.findElement(By.xpath("//ol//input")).click();
        driver.findElement(By.xpath("//ol//input")).sendKeys("", Keys.BACK_SPACE);

        int qty = (int)(Math.random() * 101);

        driver.findElement(By.xpath("//ol//input")).sendKeys(String.valueOf(qty));
//        6. Click on Calculate and verify that the Total value is correct.
//              Price per unit is 100.  The discount of 8 % is applied to quantities of 10+.
//
        driver.findElement(By.xpath("//ol//input[@type='submit']")).click();

        String amount = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value");
        double actualAmnt = Double.parseDouble(amount);
//        System.out.println("actual " + actualAmnt);

        double expectedAmnt = validateQuantity(qty);
//        System.out.println("expected " + expectedAmnt);
        Assert.assertEquals(actualAmnt, expectedAmnt);

//        7. Generate and enter random first name and last name.
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(firstAndLast() + Keys.ENTER );
//        8. Generate and Enter random street address
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(street() + Keys.ENTER);
//        9. Generate and Enter random city
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(generateCityName() + Keys.ENTER);
//        10. Generate and Enter random state
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(generateState() + Keys.ENTER);
//        11. Generate and Enter a random 5 digit zip code
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(generateZip() + Keys.ENTER);
//        12. Select the card type randomly. On each run your script should select a random type.
        String randomCard = selectRadioButton();
        driver.findElement(By.id(randomCard)).click();

//        13. Generate and enter the random card number:
//        If Visa is selected, the card number should start with 4.
//        If MasterCard is selected, card number should start with 5.
//        If American Express is selected, card number should start with 3.
//        Card numbers should be 16 digits for Visa and MasterCard, 15 for American Express.
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(generateCardNo(randomCard) + Keys.ENTER);

//        14. Enter a valid expiration date (newer than the current date)
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(generateExp() + Keys.ENTER);

        Thread.sleep(3000);

//        15. Click on Process
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
//        16. Verify that “New order has been successfully added” message appeared on the page.
        String actualMsg = driver.findElement(By.xpath("//div//strong")).getText();
        Assert.assertEquals(actualMsg, "New order has been successfully added.");

//        17. Log out of the application.
        driver.findElement(By.id("ctl00_logout")).click();

        Thread.sleep(2000);

        driver.close();


    }






    //        Price per unit is 100.  The discount of 8 % is applied to quantities of 10+.
//        So for example, if the quantity is 8, the Total should be 800.
//        If the quantity is 20, the Total should be 1840.
//        If the quantity is 77, the Total should be 7084. And so on.
    public static double validateQuantity(int qty) {
        double returnQty = 0;
        if(qty >=10) {
            returnQty = qty *(100.00 * 0.92);
        }
        else if (qty <10){
            returnQty = qty * 100.00;
        }
        return returnQty;
    }


    public static String firstAndLast(){
        String pool = "abcdefghijklmnopqrstuvwxyz";

        String accmltr = "";
        for (int i = 0; i < 12; i++) {
            accmltr += pool.charAt((int)(Math.random() * pool.length()));
        }

        StringBuilder sb = new StringBuilder(accmltr);
        sb.setCharAt(0,  Character.toUpperCase(sb.charAt(0)));
        sb.setCharAt(6, ' ');
        sb.setCharAt(7,  Character.toUpperCase(sb.charAt(7)));

        return sb.toString();
    }



    public static String street(){
        String pool = "1234567890";
        String accmltrNum = "";
        for (int i = 0; i < 4; i++) {
            accmltrNum += pool.charAt((int)(Math.random() * pool.length()));
        }
        String pool2 = "abcdefghijklmnopqrstuvwxyz ";
        String accmltrStreet = "";
        for (int i = 0; i < 8; i++) {
            accmltrStreet += pool2.charAt((int)(Math.random() * pool2.length()));
        }
        StringBuilder sb = new StringBuilder(accmltrStreet);
        sb.setCharAt(0,  Character.toUpperCase(sb.charAt(0)));

        return accmltrNum + " " + sb.toString();
    }

    public static String generateCityName(){
        String pool = "abcdefghijklmnopqrstuvwxyz";

        String accmltr = "";
        for (int i = 0; i < 7; i++) {
            accmltr += pool.charAt((int)(Math.random() * pool.length()));
        }

        StringBuilder sb = new StringBuilder(accmltr);
        sb.setCharAt(0,  Character.toUpperCase(sb.charAt(0)));

        return "San" + " " + sb.toString();

    }

    public static String generateState(){
        String[] arr = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL", "GA", "HI", "ID", "IL", "IN", "IA",
                "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY",
                "NC", "ND", "OH", "OK", "OR", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "WA", "WV", "WY"
        };
        return arr[ (int)(Math.random() * arr.length) ] ;


    }
    public static String generateZip(){
        String pool = "1234567890";
        String accmltrNum = "";
        for (int i = 0; i < 5; i++) {
            accmltrNum += pool.charAt((int)(Math.random() * pool.length()));
        }
        return accmltrNum;
    }

    public static String selectRadioButton(){
        String[] arr = {"ctl00_MainContent_fmwOrder_cardList_0", "ctl00_MainContent_fmwOrder_cardList_1", "ctl00_MainContent_fmwOrder_cardList_2" };
        return arr[   0+ (int)(Math.random() * arr.length)   ];
    }



    public static String generateCardNo(String randomCard){
        String accmltrNum = "";

//  If Visa is selected, the card number should start with 4:
        if(randomCard.contentEquals("ctl00_MainContent_fmwOrder_cardList_0")) {
            for (int i = 0; i < 15; i++) {
                accmltrNum += "1234567890".charAt((int)(Math.random() * "1234567890".length()));
            }
            return "4"+accmltrNum;
        }

//  If MasterCard is selected, card number should start with 5:
        else if(randomCard.contentEquals("ctl00_MainContent_fmwOrder_cardList_1")) {
            for (int i = 0; i < 15; i++) {
                accmltrNum += "1234567890".charAt((int)(Math.random() * "1234567890".length()));
            }
            return "5"+accmltrNum;
        }
//  If American Express is selected, card number should start with 3. (Card numbers should be 15 digits for AmEx):
        else if(randomCard.contentEquals("ctl00_MainContent_fmwOrder_cardList_2")) {
            for (int i = 0; i < 14; i++) {
                accmltrNum += "1234567890".charAt((int)(Math.random() * "1234567890".length()));
            }
            return "3"+accmltrNum;
        }
        return accmltrNum;
    }

    //    Enter a valid expiration date (newer than the current date)
    public static String generateExp(){
        String[] years = {"22", "23", "24", "25", "26", "27", "28", "29", "30"};
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        String year = years[   ( (int) (Math.random() *years.length  )  )   ];
        String month = months[   ( (int) (Math.random() * months.length  )  )   ];;

        if(year.contentEquals("22")){
            String[] months2 = {"04", "05", "06", "07", "08", "09", "10", "11", "12"};
            String month2 = months2[   ( (int) (Math.random() * months.length  )  )   ];;

            return month2 + "/" + year;
        }
        return  month + "/" + year;

    }
}
