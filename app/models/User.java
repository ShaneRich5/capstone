package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

@Entity
@Table(name = "users")
public class User extends Model {

	public static Finder<Long, User> find = new Finder<Long, User>(User.class);

	@Id
	public long id;


	public String name;

	@Column(unique = true)
	@Constraints.Required
	public String email;

	@Constraints.Required
	public String password;

	public boolean rememberMe;

	/*
		Relationships
	 */
	@OneToOne
	public Role role;

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public static User authenticate(String email, String password) {

		Map<String , Object> map = new HashMap<>();
		map.put("email", email);
		map.put("password", password);

		return User.find.where().allEq(map).findUnique();
	}

	@Override
	public void save() {
		// hash password
		super.save();
	}
}