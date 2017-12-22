package ArchiveBB.Tests;

import PageObjects.EventsMain;
import helper.Class.InitClass;
import helper.Class.VideoPlayerClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static PageObjects.EventsMain.*;
import static helper.Class.VideoPlayerClass.*;

public class Player extends InitClass{

    private EventsMain eventsMain;
    private VideoPlayerClass videoPlayer;

    @BeforeMethod
    public void beforeMethod() {
        eventsMain = new EventsMain(driver);
        videoPlayer = new VideoPlayerClass(driver);
    }

    @Test()
//    @Video()
    @Parameters({"link"})
    public void playerVideoSrcAndForwardBtn(String link) {
        driver.get(link);

//        eventsMain.navigateToPanelAndSection(EVENTS);
//        // click on EVENTS_Unity_Test
//        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);

//        // Navigate to Events section and click on first link in list
        eventsMain.chooseSectionAndOpenItemByText(EVENTS, EVENTS_MAIN_TABLE_LINKS, EVENTS_Unity_Test);

        // get list of all elements from vertical menu
        List<String> webPlayerItemsStr = videoPlayer.getWebElemListReturnListVideoSrc(EVENTS_VERTICAL_MENU);
        int i = 0;
        for(WebElement item : eventsMain.getCssPathReturnWebElementList(EVENTS_VERTICAL_MENU)){
            eventsMain.click(item);
            Assert.assertTrue(eventsMain.isWebElemAttributeActiveItem(item),"WebElement doesn't active");
            Assert.assertTrue(webPlayerItemsStr.get(i).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            // Click on Forward Button
            videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
            i++;
        }
    }

    @Test()
//    @Video()
    @Parameters({"link"})
    public void playerPlayBtnAndPauseBtn(String link){
        driver.get(link);

//        eventsMain.navigateToPanelAndSection(EVENTS);
//        // click on EVENTS_Unity_Test
//        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);

        // Navigate to Events section and click on first link in list
        eventsMain.chooseSectionAndOpenItemByText(EVENTS, EVENTS_MAIN_TABLE_LINKS, EVENTS_Unity_Test);

        // get list of web elements from vertical menu
        for(WebElement item : eventsMain.getCssPathReturnWebElementList(EVENTS_VERTICAL_MENU)){
            eventsMain.click(item);
            Assert.assertTrue(eventsMain.isWebElemAttributeActiveItem(item),"WebElement doesn't active");
            // Click on Play Button
            videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PLAY);
            Assert.assertTrue(videoPlayer.HTMLMediaElement_IF_Paused(),"Video doesn't started");
            // Check if displayed Pause btn instead of Play btn
            Assert.assertTrue(videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PAUSE),
                    "Play button doesn't replaced by Pause button");
            // Click on Forward Button
            videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
        }
    }

    @Test()
//    @Video()
    @Parameters({"link"})
    public void playerForwardBtnAndBackwardBtn(String link) {
        driver.get(link);

//        eventsMain.navigateToPanelAndSection(EVENTS);
//        // click on EVENTS_Unity_Test
//        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);

        eventsMain.chooseSectionAndOpenItemByText(EVENTS, EVENTS_MAIN_TABLE_LINKS, EVENTS_Unity_Test);

        // get list of all elements from vertical menu
        List<String> webPlayerItemsStr = videoPlayer.getWebElemListReturnListVideoSrc(EVENTS_VERTICAL_MENU);
        int i = 0;
        for(WebElement item : eventsMain.getCssPathReturnWebElementList(EVENTS_VERTICAL_MENU)){
            videoPlayer.click(item);
            Assert.assertTrue(videoPlayer.isWebElemAttributeActiveItem(item),"WebElement doesn't active");
            System.out.println("Current URL: " + webPlayerItemsStr.get(i));
            Assert.assertTrue(webPlayerItemsStr.get(i).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            // Click on Forward Button
            videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD);
            i++;
            // Check the last element - Should be disabled
            if (i == webPlayerItemsStr.size())
                // Verify if Forward button is disabled
                Assert.assertTrue(videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_FORWARD_DISABLED),
                        "The MEDIA_PLAYER_FORWARD_DISABLED item doesn't disabled");
        }
        // This block testing Backward button and in the end of iteration verify disabled Backward button
        for (int j = webPlayerItemsStr.size()-1; j >= 0; j--) {
            System.out.println("Current URL: " + webPlayerItemsStr.get(j));
            Assert.assertTrue(webPlayerItemsStr.get(j).equals(videoPlayer.HTMLMediaElement_GetVideoSource()),
                    "Video sources doesn't equals");
            // click backward btn
            videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_BACKWARD);
            if (j==0)
                Assert.assertTrue(videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_BACKWARD_DISABLED),
                    "The MEDIA_PLAYER_BACKWARD_DISABLED item doesn't disabled");
        }
    }

    // TODO: This one should be moved to PlaylistTests
    @Test()
    @Parameters({"link"})
    public void playerTimeCodeToPlaylistCompare(String link){
        driver.get(link);

//        eventsMain.navigateToPanelAndSection(EVENTS);
//        eventsMain.click(driver.findElement(By.cssSelector(US_FLAG)));
//        // click on EVENTS_Unity_Test
//        eventsMain.clickListAndTarget(EVENTS_MAIN_TABLE + " a", EVENTS_Unity_Test);

        eventsMain.chooseSectionAndOpenItemByText(EVENTS, EVENTS_MAIN_TABLE_LINKS, EVENTS_Unity_Test);

        // navigate to list items and get all lessons
        for(WebElement item : eventsMain.getCssPathReturnWebElementList(EVENTS_VERTICAL_MENU)) {
            videoPlayer.click(item);
            System.out.println(item.getText());
            videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PLAY);
            String[] timeCode = videoPlayer.getTimeCode();
            Assert.assertTrue(!timeCode[1].equals("00:00"),"End time doesn't equal 00:00");
            // get timeCode from player side list
            String[] timeFromPlayList = item.getText().trim().split("-");
            // compare file duration
            System.out.println("Displayed time in playlist: " + timeFromPlayList[timeFromPlayList.length - 1].trim());
            System.out.println("Displayed time in player: " + timeCode[1].trim());
            //Assert.assertFalse(!timeFromPlayList[timeFromPlayList.length - 1].trim().equals(timeCode[1].trim()),
            //        "Duration doesn't equals");
        }
    }


    @Test()
    @Parameters({"link"})
    public void playerTimeCode(String link){
        driver.get(link);
        videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PLAY);
        String[] timeCode = videoPlayer.getTimeCode();
        Assert.assertTrue(timeCode[0].equals("00:00"),"Start time doesn't equal 00:00");
        Assert.assertTrue(!timeCode[1].equals("00:00"),"End time equal 00:00");
        // compare file duration
        System.out.println("Displayed start time in player: " + timeCode[0].trim());
        System.out.println("Displayed start time in player: " + timeCode[1].trim());
    }

    @Test()
    @Parameters({"link"})
    public void timeCodeUpdateByPlay(String link){
        // click on play button
        // verify that player timecode is increased
        driver.get(link);
        String[] timeCode = videoPlayer.getTimeCode();
        Assert.assertTrue(timeCode[0].equals("00:00"),"Start time doesn't equal 00:00");
        System.out.println("Displayed time in player: " + timeCode[0].trim());
        videoPlayer.actionAndReturnState(MEDIA_PLAYER_CONTROLS, MEDIA_PLAYER_PLAY);
        eventsMain.wait(10000);
        timeCode = videoPlayer.getTimeCode();
        Assert.assertTrue(!timeCode[0].equals("00:00"),"Start time equal 00:00");
        System.out.println("Displayed time in player: " + timeCode[0].trim());
    }


    @Test()
    @Parameters({"link"})
    public void timeCodeUpdateByScroll(String link){
        // click on play
        // scroll to the right
        // VERIFY START TIME UPDATED
        driver.get(link);
        String[] timeCode = videoPlayer.getTimeCode();
        Assert.assertTrue(timeCode[0].equals("00:00"),"Start time doesn't equal 00:00");
        // Drag and Drop SeekBar
        videoPlayer.getCoordinatesOfElement(MEDIA_PLAYER_SEEKBAR);
        timeCode = videoPlayer.getTimeCode();
        Assert.assertTrue(!timeCode[0].equals("00:00"),"Start time equal 00:00");
    }

    // todo - need to finish
    @Test()
    public void timeCodeUpdateByLink(){
        // open predefined link with start and end times
        // https://archive.kbb1.com/lessons/part/5E2Itk4w?language=en&sstart=150.000
        // get time and compare 2:30
    }

    @Test()
    public void speedSelector(){
        // star play with regular speed during 5 sec and store the run time
        // change to 1.5 speed and run 5 sec and store the run time and compare
        // change to 2 speed and compare with previous rum time
    }

    @Test()
    public void volumeBar(){
        // mute mode
        // 1 line volume
        // 3 line volume maximum
    }

    @Test()
    public void audioVideoToggle(){
        // verify src type (audio / video)
        // start video / audio
        // switch to audio check player in audio mode and verify
        // switch to video mode and verify
    }

    @Test()
    public void languageSelector(){
        // open language selector
        // assert if opened
        // assert language list present
        // switch language and assert URL video source
    }

    @Test()
    public void fullScreenToggle(){
        // investigate size of player
    }

    @Test()
    public void sharingModeOn(){
        // click on share
        // verify that displayed (share option, link option, back to play, right and left share controls)
    }

    @Test()
    public void sharingModeOff(){
        // click back to play
        // verify that doesn't dispayed ...
    }

    @Test()
    public void sharingModeActions(){
        // enter sharing mode
        // move start time control
        // assert time updated
        // move end time control
        // assert time updated
        // copy link and open in new tap and paste copied link
        // compare copied link with source link
        //
    }

}
