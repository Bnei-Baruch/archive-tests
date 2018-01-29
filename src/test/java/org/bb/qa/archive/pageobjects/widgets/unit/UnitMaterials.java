package org.bb.qa.archive.pageobjects.widgets.unit;

import org.bb.qa.archive.pageobjects.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UnitMaterials extends PageObject {

    protected String Summary = "div.ui.blue.pointing.secondary.menu > a:nth-child(1)";
    protected String Transcription = "div.ui.blue.pointing.secondary.menu > a:nth-child(2)";
    protected String Sources = "div.ui.blue.pointing.secondary.menu > a:nth-child(3)";
    protected String Sketches = "div.ui.blue.pointing.secondary.menu > a:nth-child(4)";
    protected String sketchescuonter = ".image-gallery-index-current";



    // בודק ישור לימין בתמליל
    public boolean unitMaterialsLanguageAlignment () throws InterruptedException {
        boolean flag = false;
//        driver = new HtmlUnitDriver ();
        driver.get ("https://archive.kbb1.com/events/c/DIcWK1yE?language=en");
        driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
        // click on Transcription button
        highlightElement (driver.findElement (By.cssSelector (Transcription)));
        click (driver.findElement (By.cssSelector (Transcription)));
        // זיהוי אלמנט טקסט תמליל שיעור
        navigate (".doc2html");
        driver.manage ().timeouts ().implicitlyWait (20, TimeUnit.SECONDS);
        highlightElement (driver.findElement (By.cssSelector (".doc2html")));
        WebElement textBlock = driver.findElement (By.cssSelector (".doc2html"));
        String g = textBlock.getAttribute ("style");
        if (g.contains ("rtl")) {
            flag = true;
            System.out.println ("Text is aligned to the right ");
        } else {
            System.out.println ("Text is not aligned to the right ");
        }
        return flag;
    }

    // בודק ישור לימין בעברית או לשמאל בשפה אחרת
    public boolean unitMaterialsSource (String languagesSelect) {
        boolean flag = false;
// לינק לתוכנית עם מקורות בשפות שונות
        driver.get ("https://archive.kbb1.com/en/lessons/part/Ox7WW9cN?language=en");
        driver.manage ().timeouts ().pageLoadTimeout (40, TimeUnit.SECONDS);
// open sources
        click (driver.findElement (By.cssSelector (Sources)));
        WebElement element = driver.findElement (By.cssSelector (".right.aligned.four.wide.column"));
        ((JavascriptExecutor) driver).executeScript ("arguments[0].scrollIntoView(true);", element);
        List<WebElement> languagesSelectInSourc = driver.findElements (By.cssSelector (".ui.icon.button"));
        for (WebElement item : languagesSelectInSourc) {
            String itemText = item.getAttribute ("title");
            if (itemText.contentEquals (languagesSelect)) {
                item.click ();
//                String languagepik = item.getAttribute ("title");
                WebElement sourcText = driver.findElement (By.cssSelector (".doc2html"));
                if (sourcText.getAttribute ("style").contains ("rtl")) {
                    System.out.println (itemText + " " + sourcText.getAttribute ("style"));
                    flag = true;
                }
            } else if (!itemText.equals (languagesSelect)) {
                item.click ();
                WebElement sourcText = driver.findElement (By.cssSelector (".doc2html"));
                if (sourcText.getAttribute ("style").contains ("ltr")) {
                    System.out.println (itemText + " " + sourcText.getAttribute ("style"));
                    flag = true;
                }
            }

        }return flag;
    }

    // בודק כמות תומונות שווה למונה של התמונות
    public boolean unitMaterialsSketchesScrollCounter () {

        boolean flag = false;

        driver.get ("https://archive.kbb1.com/lessons/cu/YAmh6Oo7?language=en");
        driver.manage ().timeouts ().pageLoadTimeout (90, TimeUnit.SECONDS);

        click (driver.findElement (By.cssSelector (Sketches)));

        navigate (".image-gallery-thumbnails");
        driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);
        List<WebElement> imageGallery = driver.findElements (By.cssSelector (".image-gallery-thumbnail>div>img"));
        driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);
        for (int i = 1; i < imageGallery.size () + 1; i++) {
            flag = false;
            highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
            String dd = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
            int check = Integer.parseInt (dd);
            if (i == check & i < 9) {
                flag = true;
                waitForClickabilityOfElementLocated (imageGallery.get (i), 20);
//            Thread.sleep (100);
                imageGallery.get (i).click ();
            } else if (check == 9) {
                flag = true;
            } else {
            }
        }
        return flag;
    }

    // בודק בחירה בתמונות ספציפיות
    public boolean unitMaterialSwitchBetweenSpecificImages () {
        boolean flag = false;

//        driver.get ("https://archive.kbb1.com/lessons/cu/YAmh6Oo7?language=en");
        driver.get ("https://archive.kbb1.com/ru/lessons/cu/YAmh6Oo7?language=ru");
        driver.manage ().timeouts ().pageLoadTimeout (90, TimeUnit.SECONDS);
        // open Sketches
        highlightElement (driver.findElement (By.cssSelector (Sketches)));
        click (driver.findElement (By.cssSelector (Sketches)));
        driver.manage ().timeouts ().implicitlyWait (20, TimeUnit.SECONDS);

        navigate (sketchescuonter);

        highlightElement (driver.findElement (By.cssSelector ("div > div > a:nth-child(7) > div > img")));
        click (driver.findElement (By.cssSelector ("div > div > a:nth-child(7) > div > img")));
        driver.manage ().timeouts ().implicitlyWait (20, TimeUnit.SECONDS);
        highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
        String dd = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
        int check = Integer.parseInt (dd);

        if (check == 7) {
            highlightElement (driver.findElement (By.cssSelector ("div > div > a:nth-child(5) > div > img")));
            click (driver.findElement (By.cssSelector ("div > div > a:nth-child(5) > div > img")));
            driver.manage ().timeouts ().implicitlyWait (20, TimeUnit.SECONDS);
            highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
            String dd2 = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
            int check2 = Integer.parseInt (dd2);

            if (check2 == 5) {
                highlightElement (driver.findElement (By.cssSelector ("div > div > a:nth-child(9) > div > img")));
                click (driver.findElement (By.cssSelector ("div > div > a:nth-child(9) > div > img")));
                driver.manage ().timeouts ().implicitlyWait (20, TimeUnit.SECONDS);
                highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
                String dd3 = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
                int check3 = Integer.parseInt (dd3);

                if (check3 == 9) {
                    flag = true;
                }
            }
        } else {
        }

        return flag;
    }

    // בודק גלילה מעגלית
    public boolean unitMaterialEndlessScrolling () {
        boolean flag = false;

//        driver.get ("https://archive.kbb1.com/lessons/cu/YAmh6Oo7?language=en");
        driver.get ("https://archive.kbb1.com/he/lessons/cu/YAmh6Oo7?language=he");
        driver.manage ().timeouts ().pageLoadTimeout (90, TimeUnit.SECONDS);
        // open Sketches
        highlightElement (driver.findElement (By.cssSelector (Sketches)));
        click (driver.findElement (By.cssSelector (Sketches)));
        driver.manage ().timeouts ().implicitlyWait (20, TimeUnit.SECONDS);

        navigate (".image-gallery-left-nav");

        List<WebElement> imageGallery = driver.findElements (By.cssSelector (".image-gallery-thumbnail>div>img"));
        driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);
        for (int i = 1; i < imageGallery.size () + 1; i++) {
            flag = false;
            highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
            String cunterNum = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
            int check = Integer.parseInt (cunterNum);
            if (i == check & i < 9) {
                WebElement rightScrollArrow = driver.findElement (By.cssSelector (".image-gallery-right-nav"));
                highlightElement (rightScrollArrow);
                waitForClickabilityOfElementLocated (rightScrollArrow, 20);
                rightScrollArrow.click ();
//                Thread.sleep (200);
                flag = true;
            } else if (i == 9) {
                driver.findElement (By.cssSelector (".image-gallery-right-nav")).click ();
                List<WebElement> imageGallery2 = driver.findElements (By.cssSelector (".image-gallery-thumbnail>div>img"));
                driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);
                for (int f = 1; f < imageGallery2.size () + 1; f++) {
                    flag = false;
                    highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
                    String cunterNum2 = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
                    int check2 = Integer.parseInt (cunterNum2);
                    if (f == check2 & f < 9) {
                        flag = true;
                        WebElement rightScrollArrow = driver.findElement (By.cssSelector (".image-gallery-right-nav"));
                        highlightElement (rightScrollArrow);
                        waitForClickabilityOfElementLocated (rightScrollArrow, 20);
                        rightScrollArrow.click ();
//                        Thread.sleep (200);
                    } else if (f == 9) {
                        flag = true;
                    } else {
                    }

                }
            }
        }
        if (flag) {
            boolean flag2 = EndlessScrollingLeft ();
            return flag2;
        }else return flag;
    }
    public boolean EndlessScrollingLeft () {
        boolean flag = false;
        List<WebElement> imageGallery = driver.findElements (By.cssSelector (".image-gallery-thumbnail>div>img"));
        driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);
        for (int i = imageGallery.size (); i > 0; i--) {
            flag = false;
            highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
            String cunterNum = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
            int check = Integer.parseInt (cunterNum);
            if (i > 1 & i == check) {
                flag = true;
                WebElement leftScrollArrow = driver.findElement (By.cssSelector (".image-gallery-left-nav"));
                highlightElement (leftScrollArrow);
                waitForClickabilityOfElementLocated (leftScrollArrow, 20);
                leftScrollArrow.click ();
//                Thread.sleep (200);
            } else if (check == 1) {
                driver.findElement (By.cssSelector (".image-gallery-left-nav")).click ();
                for (int j = imageGallery.size (); j > 0; j--) {
                    flag = false;
                    highlightElement (driver.findElement (By.cssSelector (sketchescuonter)));
                    String cunterNum2 = driver.findElement (By.cssSelector (sketchescuonter)).getText ();
                    int check2 = Integer.parseInt (cunterNum2);
                    if (j > 1 & j == check2) {
                        flag = true;
                        WebElement leftScrollArrow = driver.findElement (By.cssSelector (".image-gallery-left-nav"));
                        highlightElement (leftScrollArrow);
                        waitForClickabilityOfElementLocated (leftScrollArrow, 20);
                        leftScrollArrow.click ();
//                        Thread.sleep (200);
                    } else if (check2 == 1) {
                        flag = true;
                    } else {
                    }
                }

            }
        }
        return flag;
    }

    public boolean brokenImegecheck (String imegeListpathToCss){
        boolean flag= false;

        driver.get ("https://archive.kbb1.com/lessons/cu/YAmh6Oo7?language=en");
        driver.manage ().timeouts ().pageLoadTimeout (90, TimeUnit.SECONDS);

        click (driver.findElement (By.cssSelector (Sketches)));

        navigate (".image-gallery-thumbnails");
        driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);
        List<WebElement> imageGallery = driver.findElements (By.cssSelector (".image-gallery-thumbnail>div>img"));
        driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);

        List<WebElement> list = driver.findElements(By.cssSelector(imegeListpathToCss));
        driver.manage ().timeouts ().implicitlyWait (60, TimeUnit.SECONDS);

        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        String script = "return arguments[0].complete" +
                "&& typeof arguments[0].naturalWidth != 'undefined'" +
                "&& arguments[0].naturalWidth > 0";

        for(WebElement element : list){
            try {
                if (jsExec.executeScript(script, element).equals(false))
                    System.out.println(String.format("Image [%s] is broken : ",element.getAttribute("src")));
                else
                    System.out.println(String.format("Image [%s] doesn't broken : ",element.getAttribute("src")));
                flag=true;
            }catch (Exception e){
                System.out.println(e);
            }
        }

        return flag;
    }

}
