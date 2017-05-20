package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelitionTests extends TestBase {


    @Test
    public void testGroupDelition() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroup();
        app.getNavigationHelper().goToGroupPage();
    }

}
