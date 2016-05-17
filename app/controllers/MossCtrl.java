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
import java.net.UnknownHostException;
import java.util.Collection;

/**
 * Created by Shane on 5/16/2016.
 */
public class MossCtrl extends Controller {

    @Inject
    WSClient ws;

    public Result testMoss() {

        Collection<File> files = FileUtils.listFiles(new File(
                new File(".").getAbsolutePath() + "//public//uploads//courses//comp3801//"), new String[] { "java" }, true);

//        SocketClient socket = new SocketClient("123456");

        // a list of students' source code files located in the prepared
        // directory.
//        Collection<File> files = FileUtils.listFiles(new File(
//                "C:\\temp\\solution_directory"), new String[] { "java" }, true);

        // a list of base files that was given to the students for this
        // assignment.
        Collection<File> baseFiles = FileUtils.listFiles(new File(
                "C:\\temp\\base_directory"), new String[] { "java" }, true);

        //get a new socket client to communicate with the Moss server
        //and set its parameters.
        SocketClient socketClient = new SocketClient();

        //set your Moss user ID
        socketClient.setUserID("123456789");
        //socketClient.setOpt...

        try {
            //set the programming language of all student source codes
            socketClient.setLanguage("java");

            //initialize connection and send parameters
            socketClient.run();

            // upload all base files
            for (File f : baseFiles) {
                socketClient.uploadBaseFile(f);
            }

            //upload all source files of students
            for (File f : files) {
                socketClient.uploadFile(f);
            }

            //finished uploading, tell server to check files
            socketClient.sendQuery();

        } catch (MossException | IOException e) {
            e.printStackTrace();
            return ok("Failed");
        }

        //get URL with Moss results and do something with it
        URL results = socketClient.getResultURL();
        System.out.println("Results available at " + results.toString());

        return ok("folder created");
    }
}
