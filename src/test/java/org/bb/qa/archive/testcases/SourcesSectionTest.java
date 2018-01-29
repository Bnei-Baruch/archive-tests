package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.pages.SourcesPage;
import org.bb.qa.common.TestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SourcesSectionTest extends TestTemplate {

    @Test
    public void InitialRender() {
        SourcesPage page = new SourcesPage().open();

        // section header
        assertThat("header.displayed", page.sectionHeader.isHeaderDisplayed());
        assertThat("header.text", page.sectionHeader.getHeaderText(), is(equalTo("Kabbalah Sources")));
        assertThat("subHeader.displayed", page.sectionHeader.isSubHeaderDisplayed());
        assertThat("subHeader.text", page.sectionHeader.getSubHeaderText(), not(isEmptyOrNullString()));
    }



//        @Test
    public void sourcesBaalHaSulamBrokenLinks () {

        SourcesPage page  = new SourcesPage ();

        boolean flag = page.baalHaSulamSources ();
        Assert.assertTrue (flag);
    }

//        @Test
    public void sourcesRabashBrokenLinks () {

        SourcesPage page  = new SourcesPage ();

        boolean flag = page.rabashSources ();
        Assert.assertTrue (flag);
    }

//        @Test
    public void sourcesMichaelLaitmanBrokenLinks () {

        SourcesPage page  = new SourcesPage ();

        boolean flag = page.michaelLaitmanSources ();
        Assert.assertTrue (flag);
    }

//        @Test
    public void mosesSourcesBrokenLinks () {

        SourcesPage page  = new SourcesPage ();

        boolean flag = page.sourcesMoses ();
        Assert.assertTrue (flag);
    }

//    @Test
    public void rashbiSourcesBrokenLinks () {

        SourcesPage page  = new SourcesPage ();

        boolean flag1 = page.sourcesRashbiPartOne ();
        Assert.assertTrue (flag1);
        boolean flag = page.sourcesRashbi ();
        Assert.assertTrue (flag);
    }

    @Test
    public void backTotheTopOfThePage () throws IOException, InterruptedException {

        SourcesPage page  = new SourcesPage ();

        boolean flag = page.PageStartFromTop ();
        Assert.assertTrue (flag);
    }


}
