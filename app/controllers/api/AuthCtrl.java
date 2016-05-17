package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import models.User;
import play.mvc.*;

/**
 * Created by Shane on 5/17/2016.
 */
public class AuthCtrl extends Controller {

    public Result login() {
        JsonNode json = request().body().asJson();

        if (json == null) return badRequest("Expecting Json data");

        String username = json.findPath("username").textValue();
        String password = json.findPath("password").textValue();

        if (username == null || null == password) return badRequest("Invalid form submitted");

        return Results.TODO;
    }

    public Result register() {

        return Results.TODO;

    }
}
