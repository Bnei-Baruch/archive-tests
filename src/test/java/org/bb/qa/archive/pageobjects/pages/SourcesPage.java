package org.bb.qa.archive.pageobjects.pages;

import org.bb.qa.archive.pageobjects.PageObject;
import org.bb.qa.archive.pageobjects.widgets.SectionHeader;
import org.bb.qa.archive.pageobjects.widgets.filters.FilterPanel;
import org.bb.qa.archive.pageobjects.widgets.pagination.Pagination;
import org.bb.qa.archive.pageobjects.widgets.pagination.ResultsPageHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SourcesPage extends PageObject {

    public SectionHeader sectionHeader = new SectionHeader ();
    public ResultsPageHeader paginationHeader = new ResultsPageHeader ();
    public Pagination pagination = new Pagination ();
    public FilterPanel filterPanel = new FilterPanel ();

    public SourcesPage open () {
        getUrl (urlBuilder.getUrlForPath ("sources"));
        this.waitForPresent ();
        return this;
    }

        @Override
    public void waitForPresent () {
        // Wait for root app element (react has rendered)
        this.wait.forElementPresent (By.id ("app"));

        // Wait for root section-header element (data fetching is done)
        this.wait.forElementPresent (By.className ("section-header"));
    }


    public boolean verifyLinkActive (String linkUrl) {

        boolean flag = false;
        try {
            URL url = new URL (linkUrl);

            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection ();

            httpURLConnect.setConnectTimeout (3000);

            httpURLConnect.connect ();

            if (httpURLConnect.getResponseCode () == 200) {
                System.out.println (linkUrl + " - " + httpURLConnect.getResponseMessage ());
                flag = true;
            }
            if (httpURLConnect.getResponseCode () == HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println (linkUrl + " - " + httpURLConnect.getResponseMessage () + " - " + HttpURLConnection.HTTP_NOT_FOUND);
            }
        } catch (Exception e) {

        }
        return flag;
    }

    public boolean baalHaSulamSources () {
        boolean flag = false;

//   open Kabbalah sources page
        driver.get ("https://archive.kbb1.com/sources");

        driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
        List<WebElement> sourcesList = driver.findElements (By.cssSelector (" table > tbody > tr:nth-child(2) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
        for (int i = 0; i < sourcesList.size (); i++) {
            List<WebElement> listSources = driver.findElements (By.cssSelector (" table > tbody > tr:nth-child(2) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
            highlightElement (listSources.get (i));
            listSources.get (i).click ();
            List<WebElement> sourcesLinks = driver.findElements (By.cssSelector (" div:nth-child(2) > div > div.title"));
            for (WebElement link : sourcesLinks) {
                flag = false;
                highlightElement (link);
                link.click ();
                driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
                String slink = driver.getCurrentUrl ();
                System.out.println (slink);
                verifyLinkActive (slink);
                flag = true;
            }
            driver.navigate ().back ();
            driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
        }
        return flag;
    }

    public boolean rabashSources () {
        boolean flag = false;

//   open Kabbalah sources page
        driver.get ("https://archive.kbb1.com/sources");

        driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
        List<WebElement> sourcesList = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(2) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
        for (int i = 0; i < sourcesList.size (); i++) {
            List<WebElement> listSources = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(2) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
            highlightElement (listSources.get (i));
            listSources.get (i).click ();
            List<WebElement> sourcesLinks = driver.findElements (By.cssSelector (" div:nth-child(2) > div > div.title"));
            for (WebElement link : sourcesLinks) {
                flag = false;
                highlightElement (link);
                link.click ();
                driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
                String slink = driver.getCurrentUrl ();
                System.out.println (slink);
                verifyLinkActive (slink);
                flag = true;
            }
            driver.navigate ().back ();
            driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
        }
        return flag;
    }

    public boolean michaelLaitmanSources () {
        boolean flag = false;

//   open Kabbalah sources page
//        driver.get ("https://archive.kbb1.com/sources");
        driver.get ("https://archive.kbb1.com/he/sources");

        driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
        List<WebElement> sourcesList = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(3) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
        for (int i = 0; i < sourcesList.size (); i++) {
            List<WebElement> listSources = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(3) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
            highlightElement (listSources.get (i));
            listSources.get (i).click ();
            List<WebElement> sourcesLinks = driver.findElements (By.cssSelector (" div:nth-child(2) > div > div.title"));
            for (WebElement link : sourcesLinks) {
                flag = false;
                highlightElement (link);
                link.click ();
                driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
                String slink = driver.getCurrentUrl ();
                System.out.println (slink);
                verifyLinkActive (slink);
                flag = true;
            }
            driver.navigate ().back ();
            driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
        }
        return flag;
    }

    public boolean sourcesMoses () {
        boolean flag = false;

//   open Kabbalah sources page
        driver.get ("https://archive.kbb1.com/sources");
//        driver.get ("https://archive.kbb1.com/he/sources");

        navigate ("table > tbody > tr:nth-child(5) > td:nth-child(2)");
        driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
        List<WebElement> sourcesList = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(5) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
        for (int i = 0; i < sourcesList.size (); i++) {
            List<WebElement> listSources = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(5) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
            highlightElement (listSources.get (i));
            listSources.get (i).click ();
            navigate (".source__toc>div>p");
            List<WebElement> sourcesLinks = driver.findElements (By.cssSelector ("  div.ui.left.rail > div > div:nth-child(2) > div > div.title"));
            for (int j = 0; j < sourcesLinks.size (); j++) {
                List<WebElement> inLinksSources = driver.findElements (By.cssSelector ("  div.ui.left.rail > div > div:nth-child(2) > div > div.title"));
                highlightElement (inLinksSources.get (j));
                inLinksSources.get (j).click ();
                List<WebElement> links = driver.findElements (By.cssSelector (".content.active>div>div"));
                for (WebElement link : links) {
                    flag = false;
                    highlightElement (link);
                    link.click ();
                    driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
                    String slink = driver.getCurrentUrl ();
                    System.out.println (slink);
                    verifyLinkActive (slink);
                    flag = true;
                }
            }
            driver.navigate ().back ();
            driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
        }
        return flag;
    }

    public boolean sourcesRashbi () {
        boolean flag = false;

//   open Kabbalah sources page
//        driver.get ("https://archive.kbb1.com/sources");
//        driver.get ("https://archive.kbb1.com/he/sources");

        navigate ("table > tbody > tr:nth-child(6) > td:nth-child(2)");
        driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
        List<WebElement> sourcesList = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(6) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
        for (int i = 0; i < sourcesList.size (); i++) {
            List<WebElement> listSources = driver.findElements (By.cssSelector ("table > tbody > tr:nth-child(6) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
            highlightElement (listSources.get (i));
            listSources.get (i).click ();
            navigate (".source__toc>div>p");
            List<WebElement> sourcesLinks = driver.findElements (By.cssSelector ("  div.ui.left.rail > div > div:nth-child(2) > div > div.title"));
            for (int j = 1; j < sourcesLinks.size (); j++) {
                List<WebElement> inLinksSources = driver.findElements (By.cssSelector ("  div.ui.left.rail > div > div:nth-child(2) > div > div.title"));
                highlightElement (inLinksSources.get (j));
                inLinksSources.get (j).click ();
                List<WebElement> links = driver.findElements (By.cssSelector (".content.active>div>div"));
                for (WebElement link : links) {
                    flag = false;
                    highlightElement (link);
                    link.click ();
                    driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
                    String slink = driver.getCurrentUrl ();
                    System.out.println (slink);
                    verifyLinkActive (slink);
                    flag = true;
                }
            }
            driver.navigate ().back ();
            driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
        }
        return flag;
    }

    public boolean sourcesRashbiPartOne () {
        boolean flag = false;

//   open Kabbalah sources page


        driver.get ("https://archive.kbb1.com/sources");
//        driver.get ("https://archive.kbb1.com/he/sources");

        navigate ("table > tbody > tr:nth-child(6) > td:nth-child(2)");
        driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
        WebElement sourcesList = driver.findElement (By.cssSelector ("table > tbody > tr:nth-child(6) > td:nth-child(2) > div.ui.bulleted.horizontal.list > div > a"));
        sourcesList.click ();
        navigate (".source__toc>div>p");
        WebElement sourcesLinks = driver.findElement (By.cssSelector ("  div.ui.left.rail > div > div:nth-child(2) > div > div:nth-child(1)"));
        sourcesLinks.click ();
        WebElement inLinksSources = driver.findElement (By.cssSelector (".content.active>div>div"));
        inLinksSources.click ();
        List<WebElement> Slinks = driver.findElements (By.cssSelector (".content.active>div>div >div>div"));
        for (WebElement link : Slinks) {
            flag = false;
            highlightElement (link);
            link.click ();
            driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
            String slink = driver.getCurrentUrl ();
            System.out.println (slink);
            verifyLinkActive (slink);
            flag = true;
        }
        driver.navigate ().back ();
        driver.manage ().timeouts ().pageLoadTimeout (20, TimeUnit.SECONDS);
        return flag;
    }


    public boolean PageStartFromTop () throws IOException, InterruptedException {
        boolean flag = false;
        boolean top;
        String pageUrl;
        driver.get ("https://archive.kbb1.com/en/sources");

        List<WebElement> sourcesList = driver.findElements (By.cssSelector (".ui.bulleted.horizontal.list "));
        for (WebElement Source : sourcesList) {
            List<WebElement> sourcesList0 = driver.findElements (By.cssSelector (".ui.bulleted.horizontal.list a"));
            for (int i = 0; i < sourcesList0.size (); i++) {
                List<WebElement> listSources = driver.findElements (By.cssSelector (".ui.bulleted.horizontal.list a"));
                highlightElement (listSources.get (i));
            listSources.get (i).click ();
                Thread.sleep (700);
                driver.manage ().timeouts ().pageLoadTimeout (60, TimeUnit.SECONDS);
                pageUrl = driver.getCurrentUrl ();
                top = pageStartFromeTheTop ();
                if (top) {
                    driver.navigate ().back ();
                } else {
                    System.out.println ("Page not shown from top -" + pageUrl);
                    Assert.assertTrue (top);
                }
            }
        }
        return flag;
    }
}

