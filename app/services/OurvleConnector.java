package services;

import models.Course;
import models.User;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import play.mvc.*;
import play.libs.ws.*;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class OurvleConnector {

    @Inject
    static WSClient ws;


    private static String parseCookies(List<WSCookie> cookies)
    {
        String retString = "";
        for(WSCookie cookie: cookies)
        {
            retString += ";"+cookie.getName()+"="+cookie.getValue();
        }
        if(retString.length()> 0)
        return retString.substring(1,retString.length());
        else return null;

    }


    public static User authenticate(String id, String password)
    {
        final User returnUser = new User(id,"","",password);
//
//
//        WSClient wsClient;
//        final CompletionStage<WSResponse> response =
//            ws.url("http://ourvle.mona.uwi.edu/login/index.php").setContentType("application/x-www-form-urlencoded").post("username="+id+"&pasword="+password);
//
//
//
//

        final CompletionStage<List<WSCookie>> response =
                WS.url("http://ourvle.mona.uwi.edu/login/index.php").setContentType("application/x-www-form-urlencoded").post("username="+id+"&password="+password)
                        .thenApply(res ->
                                {
                                    if (res.getStatus() == 200) {
                                        System.out.println(res.getBody());
                                        return res.getCookies();
                                    }
                                    else
                                        return null;
                                });


        try
        {

            if(response.toCompletableFuture().get().size() < 1)
                return null;
        final CompletionStage<String> response2 =  WS.url("http://ourvle.mona.uwi.edu").setContentType("application/x-www-form-urlencoded")
                .setHeader("Cookie",parseCookies(response.toCompletableFuture().get())).get()
                .thenApply(res2 ->
                {
                    if(res2.getStatus() == 200)
                    {
                        return res2.getBody();
                    }
                    else
                        return null;

                });
            if(response2.toCompletableFuture().get() == null)
                return null;

            Document document = Jsoup.parse(response2.toCompletableFuture().get());
            List<Course> courses = new ArrayList<Course>();
            Elements el1 = document.getElementsByClass("coursename");
            for(Element element : el1) {
                Element link = element.getElementsByTag("a").first();
                String code = Course.codeFromName(link.text());
                Course c = Course.findByCode(code);
                if (c != null)
                    courses.add(c);
            }
            String name = document.getElementsByClass("profilepic").attr("title");
            returnUser.setName(name);
            returnUser.setCourses(courses);

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return returnUser;

       /* final CompletionStage<Result> response =
                ws.url("http://ourvle.mona.uwi.edu/login/index.php").setContentType("application/x-www-form-urlencoded").post("username="+id+"&pasword="+password)
                        .thenApply(res ->
                        {
                            if (res.getStatus() == 200)
                            {

                                final CompletionStage<Result> response2 =
                                        ws.url("http://ourvle.mona.uwi.edu").setContentType("application/x-www-form-urlencoded")
                                                .setHeader("Cookie",parseCookies(res.getCookies())).post("username="+id+"&pasword="+password)
                                                .thenApply(res2 ->
                                                {
                                                    Document document = Jsoup.parse(res2.getBody());
                                                    List<Course> courses = new ArrayList<Course>();
                                                    Elements el1 = document.getElementsByClass("coursename");
                                                    for(Element element : el1)
                                                    {
                                                        Element link = element.getElementsByTag("a").first();
                                                        String code = Course.codeFromName(link.text());
                                                        Course c = Course.findByCode(code);
                                                        if(c != null)
                                                            courses.add(c);
                                                    }
                                                    String name = document.getElementsByClass("profilepic").attr("title");
                                                    returnUser.setName(name);
                                                    returnUser.setCourses(courses);

                                                    return ;
                                                });
                                return response2;
                            }else  return null;
                        });
        response.toCompletableFuture().get();*/







        /*
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CookieStore cookieStore = new BasicCookieStore();
            HttpContext httpContext = new BasicHttpContext();
            HttpPost httpPost = new HttpPost("http://ourvle.mona.uwi.edu/login/index.php");
            HttpGet httpGet = new HttpGet("http://ourvle.mona.uwi.edu");
            httpPost.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
            httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(new BasicNameValuePair("username", id));
            urlParameters.add(new BasicNameValuePair("password", password));


            HttpEntity postParams = new UrlEncodedFormEntity(urlParameters);
            httpPost.setEntity(postParams);

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost, httpContext);


            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            if(httpResponse.getStatusLine().getStatusCode() == 200) {
                CloseableHttpResponse httpResponse2 = httpClient.execute(httpGet, httpContext);
                reader = new BufferedReader(new InputStreamReader(httpResponse2.getEntity().getContent()));
                response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                Document document = Jsoup.parse(response.toString());
                List<Course> courses = new ArrayList<Course>();
                Elements el1 = document.getElementsByClass("coursename");
                for(Element element : el1)
                {
                    Element link = element.getElementsByTag("a").first();
                    String code = Course.codeFromName(link.text());
                    Course c = Course.findByCode(code);
                    if(c != null)
                        courses.add(c);
                }
                String name = document.getElementsByClass("profilepic").attr("title");
                returnUser = new User(name,"",password);
                returnUser.courses = courses;
            }
            httpClient.close();
        }catch (Exception e)
        {

        }
        return returnUser;*/
    }
}
