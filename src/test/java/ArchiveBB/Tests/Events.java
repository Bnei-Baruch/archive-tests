package ArchiveBB.Tests;

import PageObjects.EventsMain;
import com.automation.remarks.video.annotations.Video;
import helper.Class.InitClass;
import helper.Class.VideoPlayer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static PageObjects.EventsMain.*;
import static PageObjects.ProgramsGenre.VERTICAL_HAMBURGER_MENU;
import static helper.Class.VideoPlayer.MEDIA_PLAYER_CONTROLS;
import static helper.Class.VideoPlayer.MEDIA_PLAYER_FORWARD;


public class Events extends InitClass {

    private EventsMain eventsMain;
    private VideoPlayer videoPlayer;

    @BeforeMethod
    public void beforeMethod() {
        eventsMain = new EventsMain(driver);
        videoPlayer = new VideoPlayer(driver);
    }

    @Test()
    @Video()
    @Parameters({"link"})
    public void eventsMainAllItemsMultiLang(String link) {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        // ENG
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        int items_count = eventsMain.checkAllEventsItems();
        // RUS
        eventsMain.click(driver.findElement(By.cssSelector(RU_FLAG)));
        Assert.assertEquals(items_count,eventsMain.checkAllEventsItems(),
                "Items counter doesn't equal");
        // HEB
        eventsMain.click(driver.findElement(By.cssSelector(IL_FLAG)));
        Assert.assertEquals(items_count,eventsMain.checkAllEventsItems(),
                "Items counter doesn't equal");
    }


    @Test()
    @Video()
    @Parameters({"link"})
    public void eventsTestPlayerVideoSrcAndForwardBtn(String link) {
        driver.get(link);
        eventsMain.navigateToPanelAndSection(VERTICAL_HAMBURGER_MENU, EVENTS);
        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
        // click on EVENTS_Unity_Test
        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);
        // get list of all elements from vertical menu
        List<String> webPlayerItemsStr = videoPlayer.getWebElemListReturnListVideoSrc(EVENTS_VERTICAL_MENU);
        int i = 0;
        for(WebElement item : eventsMain.getWebElemListReturnWebElementList(EVENTS_VERTICAL_MENU)){
            eventsMain.click(item);
            Assert.assertTrue(eventsMain.isAttributeActive(item),"WebElement doesn't active");
            Assert.assertTrue(webPlayerItemsStr.get(i).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            // Click on Forward Button
            videoPlayer.action(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
            i++;
        }
    }


    @Test()
    public void playControl(){

    }

    @Test() // Seems like already covered in the test case eventsTestPlayerVideoSrcAndForwardBtn
    public void forwardControl(){

    }

    @Test()
    public void backwardControl(){

    }

    @Test()
    public void timeCode(){

    }

    @Test()
    public void timeCodeUpdateByPlay(){

    }

    @Test()
    public void timeCodeUpdateByScroll(){

    }

    @Test()
    public void timeCodeUpdateByLink(){

    }

    @Test()
    public void speedSelector(){

    }

    @Test()
    public void volumeBar(){

    }

    @Test()
    public void audioVideoToggle(){

    }

    @Test()
    public void languageSelector(){

    }

    @Test()
    public void fullScreenToggle(){

    }

    @Test()
    public void sharingModeOn(){

    }

    @Test()
    public void sharingModeOff(){

    }

    @Test()
    public void sharingModeActions(){

    }

}
