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
	@Constraints.Required
	@Column(unique = true)
    public String idNum;

	public String name;

	public String password;

	public String email;

	public boolean rememberMe;

	@OneToMany(cascade = CascadeType.ALL)
	public List<Token> tokens;

	@ManyToOne(cascade = CascadeType.ALL)
	public Role role;

	@OneToMany(cascade = CascadeType.ALL)
	public List<Submission> submissions;

	@OneToMany(cascade = CascadeType.ALL)
	public List<Assignment> assignments;

	@ManyToMany
	public List<Course> courses;

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Submission> getSubmissions() {
		return submissions;
	}

	public void setSubmissions(List<Submission> submissions) {
		this.submissions = submissions;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public User() {
		courses = new ArrayList<>();
		submissions = new ArrayList<>();
		assignments = new ArrayList<>();
	}

	public User(String idNum,String name, String email) {
		this.idNum = idNum;
		this.name = name;
		this.email = email;
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


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public void addCourses(List<Course> courses) {courses.forEach(course -> this.courses.add(course));}
    public void addCourse(Course c) {courses.add(c);}


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