package controllers;

import org.apache.commons.io.FileUtils;
import play.libs.ws.WSClient;
import play.mvc.*;
import services.moss.MossException;
import services.moss.SocketClient;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class MossCtrl extends Controller {

    @Inject
    WSClient wsClient;

    public Result triggerMoss() {

        Collection<File> files = FileUtils.listFiles(new File(
                new File(".").getAbsolutePath() + "\\public\\uploads\\courses\\comp3801\\"), new String[] { "java" }, true);

        SocketClient socketClient = new SocketClient();

        socketClient.setUserID("123456789");

        try {
            socketClient.setLanguage("java");
            socketClient.run();

            for (File f : files) {
                socketClient.uploadFile(f);
                System.out.println(f.getName());
            }

            socketClient.sendQuery();

        } catch (MossException | IOException e) {
//            e.printStackTrace();
            return ok(e.getMessage());
        }

        URL results = socketClient.getResultURL();
        System.out.println("Results available at " + results.toString());

        return ok("Results available at " + results.toString());
    }
}
