package controllers;

import models.Course;
import models.Submission;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.submissions.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by shane on 3/15/16.
 */
public class SubmissionsCtrl extends Controller {
    @Inject
    DynamicForm dynamicForm;

    public Result index() {
        List<Submission> submissions = Submission.find.all();
        return ok(index.render(submissions));
    }

    public Result create() {
        return ok(create.render());
    }

    public Result store() {
        dynamicForm.bindFromRequest();
        return redirect("/");
    }

    public Result show() {

        return ok("Show");
    }

    public Result edit() {

        return ok("Edit form");
    }

    public Result update() {

        return ok("Respond to put/patch");
    }

    public Result destroy() {

        return ok("Delete assignment");
    }
}
