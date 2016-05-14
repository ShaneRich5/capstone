import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        if (Role.find.findRowCount() == 0) {
            new Role(1,"Administrator","").save();
            new Role(2,"Lecturer","").save();
            new Role(3,"Student","").save();
        }
    }
}