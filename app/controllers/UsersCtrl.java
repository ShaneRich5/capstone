package controllers;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import com.google.inject.Inject;
import models.User;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.users.*;

import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
public class UsersCtrl extends Controller {

    @Inject
    FormFactory formFactory;

    public Result all(String role) {
        Model.Finder<Long, User> finder = User.find;
;
        List<User> users;

        if (null != role)
            users = finder.select("*")
                .fetch("roles")
                .where().eq("roles.name", role).findList();
        else
            users = finder.all();

        return ok(index.render(users, role));
    }

    public Result show(long id) {
        User user = User.find.byId(id);

        return ok(show.render(user));
    }

    public Result create() {
        return ok("create user form");
    }

    public Result store() {
        return ok("save new user");
    }

    public Result update() {
        return ok("update user");
    }

    public Result edit() {
        return ok("edit form");
    }

    public Result destroy() {
        return ok("destroyed");
    }
}
