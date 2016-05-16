package services;

import models.Course;
import models.Role;
import models.User;

import java.util.HashMap;
import java.util.Map;

public class Authenticator {
    public User authenticate(String idNum, String password) {
        Map<String , Object> credentials = new HashMap<String , Object>();

        User OurvleUser = new OurvleConnector().authenticate(idNum, password);

        if(OurvleUser == null)
            return null;
        credentials .put("idNum", idNum);
        User returnUser = User.find.where().allEq(credentials).findUnique();
        if(returnUser == null)
        {
            OurvleUser.setRole(Role.find.where().eq("name","Student").findUnique());
            OurvleUser.save();
            return OurvleUser;
        }else
        {
            OurvleUser.setRole(returnUser.getRole());
            OurvleUser.setAssignments(returnUser.getAssignments());
            OurvleUser.setSubmissions(returnUser.getSubmissions());
            returnUser.getCourses().stream().filter(c -> !OurvleUser.getCourses().contains(c)).forEach(OurvleUser::addCourse);
            OurvleUser.update();
            OurvleUser.getCourses().stream().filter(c -> !c.participants.contains(OurvleUser)).forEach(c -> {
                c.addParticipant(OurvleUser);
                c.update();
            });
            return OurvleUser;
        }

    }
}
