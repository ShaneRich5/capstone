package controllers;

import models.Role;
import play.mvc.*;

import views.html.pages.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class PagesCtrl extends Controller {

	public Result home() {

		// load initial data
		if (Role.find.findRowCount() == 0) {

			String[] names = new String[]{"student", "lecturer"};
			String[] description = new String[]{
					"Submits assigments", "Creates assignments"};

			for (int i = 0; i < 2; i++) {
				Role role = new Role();
				role.name = names[i];
				role.description = description[i];
				role.save();
			}
		}


		return ok(home.render());
	}

	public Result about() {
		return ok(about.render());
	}

	public Result contact() {
		return ok(contact.render());
	}
}