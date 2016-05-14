package services;

import models.Role;
import models.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by uhhh on 5/14/16.
 */
public class Authenticator {
    public User authenticate(String idNum, String password) {
        Map<String , Object> credentials = new HashMap<String , Object>();

        credentials .put("idNum", idNum);
        User returnUser = User.find.where().allEq(credentials).findUnique();
        User OurvleUser = new OurvleConnector().authenticate(idNum, password);
        if(OurvleUser == null)
            return null;
        if(returnUser == null)
        {
            OurvleUser.setRole(Role.find.where().eq("name","Student").findUnique());
            OurvleUser.save();
            return OurvleUser;
        }else
        {
            OurvleUser.setRole(returnUser.getRole());
            OurvleUser.update();
            return OurvleUser;
        }

    }
}
