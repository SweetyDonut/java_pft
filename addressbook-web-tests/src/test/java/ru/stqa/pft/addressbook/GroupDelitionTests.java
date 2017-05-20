package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupDelitionTests extends TestBase {


    @Test
    public void testGroupDelition() {
        goToGroupPage();
        selectGroup();
        deleteSelectedGroup();
        goToGroupPage();
    }

}
