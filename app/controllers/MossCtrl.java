package controllers;

import org.apache.commons.io.FileUtils;
import play.libs.ws.WSClient;
import play.mvc.*;

import javax.inject.Inject;
import java.io.File;
import java.util.Collection;

/**
 * Created by Shane on 5/16/2016.
 */
public class MossCtrl extends Controller {

    @Inject
    WSClient ws;

    public Result testMoss() {

        Collection<File> files = FileUtils.listFiles(new File(
                new File(".").getAbsolutePath() + "//public//courses//comp3911//test-1//"), new String[] { "java" }, true);

//        SocketClient socket = new SocketClient("123456");

        return ok("folder created");
    }
}
