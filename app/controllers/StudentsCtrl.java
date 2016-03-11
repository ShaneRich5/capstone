package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class StudentsCtrl extends Controller {

	public Result show(long id) {
		return ok(Long.toString(id));
	}

	public Result all() {
		return ok("All Students");
	}
}