package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class PagesCtrl extends Controller {

	public Result home() {
		return ok(home.render());
	}

	public Result about() {
		return ok(about.render());
	}

	public Result contact() {
		return ok(contact.render());
	}
}