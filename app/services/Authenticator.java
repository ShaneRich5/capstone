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
        credentials .put("password", password);
        User returnUser = User.find.where().allEq(credentials).findUnique();
        if(returnUser == null || returnUser.role.equals(Role.find.where().eq("name","Student"))) {
            returnUser = new OurvleConnector().authenticate(idNum, password);
            if(returnUser != null) {
                returnUser.role = Role.find.where().eq("name","Student").findUnique();
                if(User.find.where().eq("idNum",returnUser.getIdNum()).findUnique() != null)
                    returnUser.update();
                else
                    returnUser.save();
            }
        }
        return returnUser;
    }
}
