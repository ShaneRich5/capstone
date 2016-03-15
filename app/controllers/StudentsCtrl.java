package controllers;

import models.User;
import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;

public class StudentsCtrl extends Controller {

	public Result show(long id) {
		return ok(Long.toString(id));
	}

	public Result all() {
		List<User> users = User.find.all();

		return ok("All Students");
	}

	public Result store() {
		return ok("Created new student");
	}
}