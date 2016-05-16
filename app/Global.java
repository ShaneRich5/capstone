import com.avaje.ebean.Model;
import play.*;
import play.libs.*;
import com.avaje.ebean.Ebean;
import models.*;
import java.util.*;

public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        if(User.find.findRowCount() > 0)
        {
            List<User> users = User.find.findList();
            //users.stream().filter(u -> u.getIdNum() != null).forEach(Model::delete);
            System.out.println("Users in DB");
            for(User u: users)
                System.out.println(u.getIdNum()+" "+u.name);
        }

        if (Role.find.findRowCount() == 0) {
            new Role(1,"Administrator","").save();
            new Role(2,"Lecturer","").save();
            new Role(3,"Student","").save();
        }

        if(Course.find.findRowCount() == 0)
        {
            new Course("Systems Programming","","COMP2130").save();
            new Course("Information Security & Assurance","","INFO3155").save();
            new Course("Internship in Computing","","COMP3911").save();
            new Course("Group Project","","COMP3901").save();
            new Course("Database Management Systems","","COMP3161").save();
            new Course("Theory of Computation","","COMP3702").save();
            new Course("Modern Communication Systems","","ELET2480").save();
            String[] admins = {"620063216","620067948"};
            for(String admin: admins)
            {
                User u = new User(admin,null,null);
                User dbU = User.find.where().eq("idNum",admin).findUnique();
                Role adminRole = Role.find.where().eq("name","Administrator").findUnique();
                if(dbU != null)
                {
                    dbU.setRole(adminRole);
                    dbU.update();
                }else
                {
                    u.setRole(adminRole);
                    u.save();
                }
            }
        }

    }
}