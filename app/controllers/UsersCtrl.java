package controllers;

import com.google.inject.Inject;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by shane on 3/15/16.
 */
public class UsersCtrl extends Controller {

    @Inject
    FormFactory formFactory;

    public Result all() {
        return ok("all");
    }

    public Result show(long id) {
        return ok("id is " + id);
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
