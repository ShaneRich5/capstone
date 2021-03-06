package controllers;

import models.Role;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.ws.WSClient;
import play.mvc.*;
import services.auth.Authenticator;
import views.html.pages.home;
import views.html.sessions.*;
import views.html.sessions.login;

import javax.inject.Inject;

public class SessionsCtrl extends Controller {

	@Inject FormFactory formFactory;
	@Inject WSClient wsClient;

	public Result login() {
		String id = session("id");

		if (null != id)
			return ok ("Already logged in");

        return ok(login.render());
    }

	public Result register() {
		return ok(register.render());
	}

	public Result authenticate() {
		DynamicForm requestData = formFactory.form().bindFromRequest();

		if (requestData.data().size() == 0) return badRequest("Expecting data");

		final String id = requestData.get("id");
		final String password = requestData.get("password");
		final String rememberMe = requestData.get("remember");

		User user = new Authenticator().authenticate(id, password);

		if (null == user)
			return ok("Not found");

		session("id", user.getIdNum());
		session("name", user.getName());
		session("role", user.role.name);

		return ok(home.render());
	}

	public Result store() {

		if(null != session().get("email"))
			redirect("/");

		DynamicForm requestData = formFactory.form().bindFromRequest();

		if (requestData.data().size() == 0) return badRequest("Expecting data");

		final String idNum = requestData.data().get("id");
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

		User newUser = new User(idNum,name, email);

		newUser.role = role;
		newUser.save();

		session("email", email);
		session("id", newUser.getIdNum() + "");
		session("name", newUser.name);

		return redirect("/users");
	}

	public Result logout() {
		session().clear();
		return redirect("/login");
	}
}