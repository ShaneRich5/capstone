package controllers;

import models.Role;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.*;
import views.html.pages.home;
import views.html.sessions.*;

import javax.inject.Inject;

public class SessionsCtrl extends Controller {

	@Inject
	FormFactory formFactory;

	public Result login() {
		String email = session("email");

		if (null != email)
			ok ("Already logged in");

		return ok(login.render());
	}

	public Result register() {
		return ok(register.render());
	}

	public Result authenticate() {
		DynamicForm requestData = formFactory.form().bindFromRequest();

		if (requestData.data().size() == 0) return badRequest("Expecting data");

		final String email = requestData.get("email");
		final String password = requestData.get("password");
		final String rememberMe = requestData.get("remember");

		User user = User.authenticate(email, password);

		if (null == user)
			return ok("Not found");

		session("email", email);

		return ok(home.render());
	}

	public Result store() {

		DynamicForm requestData = formFactory.form().bindFromRequest();

		if (requestData.data().size() == 0) return badRequest("Expecting data");

		final String name = requestData.data().get("name");
		final String email = requestData.data().get("email");
		final String password = requestData.data().get("password");
		final String confirmation = requestData.data().get("confirm_password");
		final String student = requestData.data().get("student");
		final String lecturer = requestData.data().get("lecturer");

		if (!password.equals(confirmation))
			return ok("Password missmatch");

		Role role = Role.find
				.where()
				.eq("name", (student == null) ? "lecturer" : "student")
				.findUnique();

		User newUser = new User(name, email, password);

		newUser.role = role;
		newUser.save();

		return ok("Saved new user");
	}
}