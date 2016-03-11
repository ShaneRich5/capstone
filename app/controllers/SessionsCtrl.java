package controllers;

import play.*;
import play.mvc.*;

import views.html.*;
import models.*;

public class SessionsCtrl extends Controller {

	Form<User> userForm = Form.form(User.class);

	public Result login() {
		// TODO
		return ok("Login");
	}

	public Result register() {
		// TODO
		return ok("Register");
	} 
}