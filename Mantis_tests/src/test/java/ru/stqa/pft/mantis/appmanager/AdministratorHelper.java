package ru.stqa.pft.mantis.appmanager;

/**
 * Created by Даниил on 13.06.2017.
 */


import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.User;

public class AdministratorHelper extends HelperBase {


  public AdministratorHelper(ApplicationManager app) {
    super(app);
  }

  public void administratorLogin(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void changeUserPass(User user) {
    wd.get(app.getProperty("web.baseUrl") + "manage_user_page.php");
    type(By.id("username"), user.getUsername());
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']",user.getId())));
    click(By.id("manage-user-reset-form"));

  }
}