import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationProj2 {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver"  ,  "C:\\BrowserDrivers\\chromedriver.exe " );
        WebDriver driver = new ChromeDriver();

//        1. Navigate to http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php
        driver.get("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");

//        3. Verify the title is "Welcome to Duotify!"
        String actualTitle = driver.getTitle();
        if(actualTitle.equals("Welcome to Duotify!")) {
            System.out.println("The actual title matches the expected title:  \" Welcome to Duotify! \" ");
        }else {
            System.out.println("Title is different than expected.  Actual title is: " + actualTitle);
        }
        Thread.sleep(1000);
//        4. Click on Signup here
        WebElement signupButton = driver.findElement(By.id("hideLogin"));
        Thread.sleep(1000);
        signupButton.click();
        Thread.sleep(1000);

//        5. Fill out the form with the required info
        WebElement username = driver.findElement(By.id("username"));
        String userName = "MiyaG";
        username.sendKeys(userName);
        Thread.sleep(1000);

        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("Miyassar");
        Thread.sleep(1000);

        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Gylyj");
        Thread.sleep(1000);

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("miya@gmail.com");
        Thread.sleep(1000);

        WebElement email2 = driver.findElement(By.id("email2"));
        email2.sendKeys("miya@gmail.com");
        Thread.sleep(1000);

        WebElement password = driver.findElement(By.id("password"));
        String passwordMiy = "Password123";
        password.sendKeys(passwordMiy);
        Thread.sleep(1000);

        WebElement confirmPassword = driver.findElement(By.id("password2"));
        confirmPassword.sendKeys(passwordMiy);
        Thread.sleep(1000);

//        6. Click on Sign up
        WebElement submitButton = driver.findElement(By.name("registerButton"));
        submitButton.click();

//        7. Once logged in to the application, verify that the URL is:
//        http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?
        String expectedUrl = "http://qa-duotify.us-east-2.elasticbeanstalk.com/browse.php?";
        String currentUrl = driver.getCurrentUrl();
        if(expectedUrl.equals(currentUrl)) {
            System.out.println("the URL is correct.  The current URL is: " + currentUrl);
        }else{
            System.out.println("The URL is incorrect. The current URL is: " + currentUrl);
        }
        Thread.sleep(1000);

//        8. Click on the username on the left navigation bar and then click logout.
        WebElement un = driver.findElement(By.id("nameFirstAndLast"));
        un.click();
        Thread.sleep(1000);
        WebElement logoutButtn = driver.findElement(By.id("rafael"));
        logoutButtn.click();
        Thread.sleep(1000);


//        9. Verify that you are logged out by verifying the URL is:
//        http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php
        String expectedUrl2 = "http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php";
        String currentUrl2 = driver.getCurrentUrl();
        if(currentUrl2.equals(expectedUrl2)) {
            System.out.println("System successfully logged out");
        }else {
            System.out.println("System logout unsuccessful");
        }
        Thread.sleep(1000);

//        10. Login using the same username and password when you signed up.
        driver.navigate().to("http://qa-duotify.us-east-2.elasticbeanstalk.com/register.php");
        WebElement un2 = driver.findElement(By.name("loginUsername"));
        un2.sendKeys(userName);
        Thread.sleep(1000);

        WebElement loginPassword = driver.findElement(By.id("loginPassword"));
        loginPassword.sendKeys(passwordMiy);
        Thread.sleep(1000);

//        11. Verify successful login by verifying that the home page contains the text "You Might Also Like".
        if(driver.getPageSource().contains("You Might Also Like")) {
            System.out.println("Log in successful");
        }else {
            System.out.println("Log in unsuccessful");
        }

        driver.close();

    }
}
