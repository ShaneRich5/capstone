package services;

import models.Course;
import models.User;
import org.apache.commons.lang3.concurrent.ConcurrentException;
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
import java.util.concurrent.ExecutionException;

public class OurvleConnector {

    @Inject WSClient ws;



    private String parseCookies(List<WSCookie> cookies)
    {
        String retString = "";
        String prevCookieName = "";
        int prevStart = -1;
        for(WSCookie cookie: cookies)
        {
            if(cookie.getName().equals(prevCookieName))
                retString = retString.substring(0,prevStart);
            //if(!cookie.getName().equals(prevCookieName) && !cookie.getName().equals("MOODLEID1_")) {
                prevStart = retString.length();
                retString += ";" + cookie.getName() + "=" + cookie.getValue();
                prevCookieName = cookie.getName(); //hotfix
            //}
        }
        if(retString.length()> 0)
        return retString.substring(1,retString.length());
        else return null;

    }


    public User authenticate(String id, String password)
    {
        User returnUser = new User(id,"","",password);

        final CompletionStage<List<WSCookie>> response =
                WS.url("http://ourvle.mona.uwi.edu/login/index.php?authldap_skipntlmsso=1").setFollowRedirects(true).setHeader("Upgrade-Insecure-Requests","1")
                        .setContentType("application/x-www-form-urlencoded").setHeader("Cookie","").post("username="+id+"&password="+password)
                        .thenApply(res ->
                                {
                                 System.out.println("\n");
                                    System.out.println("First Request:"+res.getAllHeaders());
                                    if (res.getStatus() == 200) {
                                        return res.getCookies();
                                    }
                                    else
                                        return null;
                                });


        try
        {

            if(response.toCompletableFuture().get() == null || response.toCompletableFuture().get().size() < 1)
            {
                System.out.println("Cookies blank");
                return null;
            }
        System.out.println("Cookie: "+parseCookies(response.toCompletableFuture().get()));
        final CompletionStage<String> response2 =  WS.url("http://ourvle.mona.uwi.edu").setContentType("application/x-www-form-urlencoded")
                .setHeader("Cookie",parseCookies(response.toCompletableFuture().get())).get()
                .thenApply(res2 ->
                {
                    System.out.println("2nd Request:"+res2.getAllHeaders());
                    if(res2.getStatus() == 200)
                    {
                        return res2.getBody();
                    }
                    else
                        return null;

                });
            if(response2.toCompletableFuture().get() == null) {

                System.out.println("Data not 200.");
                return null;
            }


            Document document = Jsoup.parse(response2.toCompletableFuture().get());
            System.out.println("\n\n\n\n"+document+"\n\n\n\n");
            List<Course> courses = new ArrayList<Course>();
            Elements el1 = document.getElementsByClass("coursename");
            for(Element element : el1) {
                Element link = element.getElementsByTag("a").first();
                String code = Course.codeFromName(link.text());
                Course c = Course.findByCode(code);
                if (c != null)
                    courses.add(c);
                System.out.println("Course found:"+code);
            }
            String name = document.getElementsByClass("profilepic").attr("title");
            System.out.println("Name found:"+name);
            returnUser.setName(name);
            returnUser.setCourses(courses);
            if(name.length() < 1)
                return null;

        }catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
            return null;
        }
        System.out.print(id);
        System.out.println("User:"+returnUser.getIdNum()+ " "+returnUser.getName());
        return returnUser;
    }
}
