package controllers;

import models.Assignment;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.testcase.*;

import javax.inject.Inject;
import java.io.File;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class TestController extends Controller {

	@Inject
	FormFactory formFactory;

public Result show(){
	return ok(create.render());

}

public Result addtest(){
	DynamicForm requestData = formFactory.form().bindFromRequest();
	final String testcase = requestData.get("testcase");
	final String assignmentName = requestData.get("Aname");
	final String overallMark = requestData.get("mark");
    
	
	try{
		File myFile = new File("Junittest.java");
		FileWriter fWriter = new FileWriter(myFile,false);

		fWriter.write("package junittest; " + "\n" + "import junittest.lecturer.*;"+ 
					 "\n" + "import junittest.student.*;" + 
					 "\n" + "import static org.junit.Assert.*;" + 
					 "\n" + "import org.junit.Test;" +
					 "\n" + "import java.util.*;" + "\n");

		fWriter.write("\n" + "public class JunitTest {" + "\n"+ 
					  "public static int grade = 0;" + "\n" +
					  "public double mark = "+ overallMark + ";"+"\n" +
					  "public String name = " + '"'+ assignmentName + '"'+ ";"+"\n");
		
		fWriter.write(testcase  + "\n");
		
		fWriter.write("\n" + "//Method to compare integers" + "\n" 
			          + "public void compareInt(int a, int b){ assertEquals(a,b); }" + "\n");

		fWriter.write("\n" + "//Method to compare strings" + "\n" 
			          + "public void compareStr(String a, String b){ assertEquals(a,b); }" + "\n");

		fWriter.write("\n" + "//Method used get student grades" + "\n" 
			          + "public double grade(){ return grade; }" + "\n");

		fWriter.write("\n"+"//Method used get student grades" + "\n" 
			          + "public String percentage(){" + "\n" + 
			             "double p = ((double)(grade/mark) * 100)" +";"+"\n" + 
			      	     "String str = String.format(\"%2.01f\",(double)p) " + ";" + "\n" +
			      	 	 "return str + \"%\"" + ";" + "}" + "\n");
		
		fWriter.write("\n" + "//Method used to get filename" + "\n" 
			          + "public String getName(){ return name; }" + "\n");


		fWriter.write("\n" + "}");
		fWriter.close();

	}
	catch(IOException io){
		io.getMessage();

	}
  return ok("Success");
}
}