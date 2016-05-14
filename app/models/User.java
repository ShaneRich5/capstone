package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;
import services.OurvleConnector;

@Entity
@Table(name = "users")
public class User extends Model {

	public static Finder<Long, User> find = new Finder<Long, User>(User.class);

	@Id
	@Constraints.Required
	@Column(unique = true)
	public long id;

	public String name;

	public String email;

	public String idNum;

	@Constraints.Required
	public String password;

	public boolean rememberMe;

	/*
		Relationships
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	public Role role;

	@OneToMany(cascade = CascadeType.ALL)
	public List<Submission> submissions;

	@OneToMany(cascade = CascadeType.ALL)
	public List<Assignment> assignments;

	@ManyToMany
	public List<Course> courses;

	public User() {}

	public User(String idNum,String name, String email, String password) {
		this.idNum = idNum;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
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

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}


//	public static User authenticate(String idNum, String password) {
//		Map<String , Object> credentials = new HashMap<String , Object>();
//
//		credentials .put("idNum", idNum);
//		credentials .put("password", password);
//		User returnUser = User.find.where().allEq(credentials).findUnique();
//		if(returnUser == null || returnUser.role.equals(Role.find.where().eq("name","Student"))) {
//			returnUser = OurvleConnector.authenticate(idNum, password);
//			if(returnUser != null) {
//				returnUser.role = Role.find.where().eq("name","Student").findUnique();
//				if(User.find.where().eq("idNum",returnUser.getIdNum()).findUnique() != null)
//					returnUser.update();
//				else
//					returnUser.save();
//			}
//		}
//		return returnUser;
//	}

	public static User findByEmail(String email) {
		return User.find.where()
				.eq("email", email)
				.findUnique();
	}

	@Override
	public void save() {
		// hash password
		super.save();
	}
}