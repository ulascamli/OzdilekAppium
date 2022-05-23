import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


import static org.openqa.selenium.By.*;

public class BaseStep extends BaseTest {

    final static Logger logger = Logger.getLogger(BaseStep.class.getName());

    @Step("<second> kadar bekle")
    public void waitForsecond(int second) throws InterruptedException {
        Thread.sleep(1000 * second);

    }

    @Step("<Key> İd'li elemente tıkla")
    public void clickElementByid(String Key) {
        appiumDriver.findElement(id(Key)).click();
        logger.info(Key + "Elenitine tıklandı");

    }

    @Step("<Key> İd'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByid(String Key, String keywordc) {
        appiumDriver.findElement(id(Key)).sendKeys(keywordc);
        logger.info(Key + "Elenitine tıklandı");


    }

    @Step("<Key> xpath'li elemente tıkla")
    public void clickElementByxpath(String Key) {
        appiumDriver.findElement(xpath(Key)).click();
        logger.info(Key + "Elenitine tıklandı");


    }

    @Step("<Key> xpath'li elemente <keywordc> değerini yaz")
    public void SendkeyElementByxpath(String Key, String keywordc) {
        appiumDriver.findElement(xpath(Key)).sendKeys(keywordc);
        logger.info(Key + "Elenitine tıklandı");


    }

    @Step("Sayfayı yukarı kaydır")
    public void swipeUpI() {
        Dimension dims = appiumDriver.manage().window().getSize();
        System.out.println("Telefon Boyutu " + dims);
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder = 5;
        final int PRESS_TIME = 200; // ms
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        System.out.println("pointOptionStart " + pointOptionStart);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        System.out.println("pointOptionEnd " + pointOptionEnd);
        new TouchAction(appiumDriver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }

    @Step("Elementi <xpath> bul ve <keyword> değerini kontrol et")
    public void xpathTextControl(String xpath, String keyword) {
        logger.info("Text değeri " + appiumDriver.findElement(xpath(xpath)).getText());
        Assert.assertTrue("Text değeri bulunmamadı ", appiumDriver.findElement(xpath(xpath)).getText().equals(keyword));

    }

    @Step("<Key> İd'li elemente <keyword> değerini kontrol et")
    public void idTextControl(String Key, String keyword) {
        appiumDriver.findElement(id(Key)).getText();
        logger.info("rasgele secildi");
        Assert.assertTrue("Text değeri bulunmamadı ", appiumDriver.findElement(id(Key)).getText().equals(keyword));


    }
    public List<MobileElement> listElements() {
        return appiumDriver.findElements(xpath("//*[@class='androidx.cardview.widget.CardView']"));
    }

    @Step("<saniye> sonra ürün rasgele seçilecektir.")
    public void randomClick(int saniye) throws InterruptedException {

        Random random = new Random();
        listElements().get(random.nextInt(listElements().size())).click();

        Thread.sleep(1000 * saniye);
        logger.info("rasgele secildi");


    }
}

