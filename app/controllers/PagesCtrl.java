package controllers;

import models.Role;
import models.User;
import play.mvc.*;

import views.html.pages.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class PagesCtrl extends Controller {
		public Result home() {
		if (0 == User.find.findRowCount()) session().clear();

		return ok(home.render());
	}

	public Result about() {


		return ok(about.render());
	}

	public Result contact() {
		return ok(contact.render());
	}
}