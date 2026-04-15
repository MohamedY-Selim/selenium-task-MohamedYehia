package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;

public abstract class BasePage<T extends BasePage<T>> {

    //Driver
    protected final WebDriver driver;

    //Constructor
    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    //Methods
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public T refresh() {
        driver.navigate().refresh();
        return (T) this;
    }
}