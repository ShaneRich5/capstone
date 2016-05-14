import com.avaje.ebean.Model;
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
        if(Course.find.findRowCount() == 0)
        {
            new Course("Systems Programming","","COMP2130");
            new Course("Information Security & Assurance","","INFO3155");
            new Course("Internship in Computing","","COMP3911");
            new Course("Group Project","","COMP3901");
            new Course("Database Management Systems","","COMP3161");
            new Course("Theory of Computation","","COMP3702");
            new Course("Modern Communication Systems","","ELET2480");
        }
//        if(User.find.findRowCount() > 0) {
//            List<User> users = User.find.where().eq("role",Role.find.where().eq("id",3)).findList();
//            users.forEach(Model::delete);
//        }
    }
}