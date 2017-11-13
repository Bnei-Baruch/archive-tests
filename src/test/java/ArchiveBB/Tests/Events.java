package ArchiveBB.Tests;

import PageObjects.EventsList;
import helper.Class.InitClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import videoHelper.VideoRecorder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Events extends InitClass{

    private VideoRecorder videoRecord;
    private EventsList eventsList;

    @BeforeMethod
    public void beforeMethod() {
        videoRecord = new VideoRecorder(driver);
        eventsList = new EventsList(driver);
    }

    @Test()
    @Parameters({"link", "videoPath"})
    public void testEventsListEng(String link, String videoPath) throws Exception {
        // start video recording
        videoRecord.startRecording(videoPath);

        // do test
        try {
            driver.get(link);

            setLang("en");
            List<WebElement> events = driver.findElements(By.cssSelector(".top.aligned"));
            Assert.assertTrue(events.size() > 0, "Events list contains zero items!");
            Reporter.log(String.format("Found %d events on page %s", events.size(), driver.getCurrentUrl()));
            int eng_events_len = events.size();

            setLang("ru");
            events = driver.findElements(By.cssSelector(".top.aligned"));
            Assert.assertTrue(events.size() > 0, "Events list contains zero items!");
            Reporter.log(String.format("Found %d events on page %s", events.size(), driver.getCurrentUrl()));
            int rus_events_len = events.size();

            setLang("he");
            events = driver.findElements(By.cssSelector(".top.aligned"));
            Assert.assertTrue(events.size() > 0, "Events list contains zero items!");
            Reporter.log(String.format("Found %d events on page %s", events.size(), driver.getCurrentUrl()));
            int heb_events_len = events.size();

            Assert.assertTrue((eng_events_len == rus_events_len) && (eng_events_len == heb_events_len),
                    String.format("Events list mismatch en: %d ru: %d he: %d", eng_events_len, rus_events_len, heb_events_len));

        }
        // stop video recording
        finally { videoRecord.stopRecording(videoPath); }

        // if we're here it means the test passed. remove video files
        deleteVideoLog(videoPath);
    }

    private void deleteVideoLog(String videoPath) throws IOException {
        if (!videoPath.equals("")) {
            List<File> toDelete = videoRecord.getCreatedMovieFiles();
            Files.deleteIfExists(Paths.get(String.valueOf(toDelete.get(0))));
        }
    }

    void setLang(String lang){
        switch (lang) {
            case "en":
                eventsList.clickElement(".us.flag");
                break;
            case "ru":
                eventsList.clickElement(".ru.flag");
                break;
            case "he":
                eventsList.clickElement(".il.flag");
                break;
        }
    }
}
