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
  public  void adminLogin(String username, String password) throws IOException {
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "login.php");
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));
    CloseableHttpResponse response = httpClient.execute(post);
    String body = getTextFrom(response);
  }

  public boolean login(String username, String password) throws IOException {
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "login.php");
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    post.setEntity(new UrlEncodedFormEntity(params));
    CloseableHttpResponse response = httpClient.execute(post);
    String body = getTextFrom(response);
    return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
  }

  private String getTextFrom(CloseableHttpResponse response) throws IOException {
    try { //закрытие
      return EntityUtils.toString(response.getEntity());
    } finally {
      {
        response.close();
      }
    }
  }
  public boolean isLoggedInAs(String username) throws IOException {
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl")+ "index.php");
    CloseableHttpResponse response =  httpClient.execute(get);
    String body = getTextFrom(response); //получение текста
    return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
  }


}
