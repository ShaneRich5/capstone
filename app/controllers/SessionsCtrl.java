package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import models.*;

public class SessionsCtrl extends Controller {

	public Result login() {
		// TODO
		return ok(login.render());
	}

	public Result register() {
		// TODO
		return ok("Register");
	} 
}