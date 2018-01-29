package org.bb.qa.archive.testcases;

import org.bb.qa.archive.pageobjects.widgets.unit.UnitMaterials;
import org.bb.qa.common.TestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UnitMaterialstest extends TestTemplate {



    @Test
    public void LanguageAlignment () throws InterruptedException {

        UnitMaterials page = new UnitMaterials ();

        boolean flag = page.unitMaterialsLanguageAlignment ();
        System.out.println ("the unitMaterialsLanguageAlignment test result is - " + flag);
        Assert.assertTrue (flag);
    }

    @Test
    public void SourceOne () {

        UnitMaterials page = new UnitMaterials ();

//        boolean flag = um.unitMaterialsSource ("Hebrew");
        boolean flag = page.unitMaterialsSource ("En");
        System.out.println ("the unitMaterialsSource test result is - " + flag);
        Assert.assertTrue (flag);

    }

    @Test
    public void SketchesScrollCheck () throws InterruptedException {

        UnitMaterials page = new UnitMaterials ();

        boolean flag = page.unitMaterialsSketchesScrollCounter ();
        System.out.println ("the unitMaterialsSketchesScrollCounter test result is - " + flag);
        Assert.assertTrue (flag);
    }

    @Test
    public void SwitchBetweenSpecificImages () {

        UnitMaterials page = new UnitMaterials ();

        boolean flag = page.unitMaterialSwitchBetweenSpecificImages ();
        Assert.assertTrue (flag);
    }

    @Test
    public void EndlessScrolling ()  {

        UnitMaterials page = new UnitMaterials ();

        boolean flag = page.unitMaterialEndlessScrolling ();
        Assert.assertTrue (flag);
    }

    @Test
    public void checkForBrokenImeges () {

        UnitMaterials page = new UnitMaterials ();

        boolean flag = page.brokenImegecheck (".image-gallery-thumbnail>div>img");
        Assert.assertTrue (flag);

    }
}
