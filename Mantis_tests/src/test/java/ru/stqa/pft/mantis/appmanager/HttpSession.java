package ru.stqa.pft.mantis.appmanager;

import com.sun.scenario.effect.impl.state.LinearConvolveRenderState;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.net.www.http.HttpClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Даниил on 12.06.2017.
 */
public class HttpSession {
  private final ApplicationManager app;
  private final CloseableHttpClient httpClient;

  public HttpSession(ApplicationManager app){
    this.app=app;
    httpClient= HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();

  }

  public boolean login(String username, String password) throws IOException { // выполнение логина
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "login.php"); //отправка запроса по адресу. созданеи запроса.
    List<NameValuePair> params = new ArrayList<>(); // формируется набор параметров
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    post.setEntity(new UrlEncodedFormEntity(params)); // упаковка параметров
    CloseableHttpResponse response = httpClient.execute(post); // отправка запроса - результат ответ
    String body = getTextFrom(response);
    return body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>", username));// проверка вошел ли пользователь в систему
  }

  private String getTextFrom(CloseableHttpResponse response) throws IOException { // получение текста ответа
    try { //закрытие
      return EntityUtils.toString(response.getEntity());
    } finally {
      {
        response.close();
      }
    }
  }
  public boolean isLoggedInAs(String username) throws IOException { //какой пользователь сейчас залогинен
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl")+ "index.php"); // обращение к странице
    CloseableHttpResponse response =  httpClient.execute(get); //выполнение запроса гет
    String body = getTextFrom(response); //получение текста
    return body.contains(String.format("<span class=\"label hidden-xs label-default arrowed\">%s</span>", username)); // проверка текста страницы на содержание нужной инфы
  }


}
