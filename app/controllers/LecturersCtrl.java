package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class LecturersCtrl extends Controller {

	public Result show(Long id) {
		return ok(Long.toString(id));
	}

	public Result all() {
		// TODO
		return ok("All Lecturers");
	}
}