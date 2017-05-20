package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDelitionTests extends TestBase {


    @Test
    public void testGroupDelition() {
        app.goToGroupPage();
        app.selectGroup();
        app.deleteSelectedGroup();
        app.goToGroupPage();
    }

}
