package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation;

@Entity
public class User extends Model {

	public static Finder<Long, User> find = new Finder<Long, User>(User.class);

	@Id
	public Long id;

	@Constraints.Required
	public String username;
	public String password;

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
}