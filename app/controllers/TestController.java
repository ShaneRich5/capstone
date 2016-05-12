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
import java.io.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
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
	final String instanceofClass = requestData.get("class");
	String currentline;
	String line = "";
	String grade = "";
	int count = 0;
	
	
	try{
		FileWriter fWriter = new FileWriter(new File(".").getAbsolutePath() + "//test//junittest//JunitTest.java",false);
		FileWriter fw = new FileWriter(new File(".").getAbsolutePath() + "//test//junittest//text.txt", false);

		fWriter.write("package junittest; " + "\n" + "import junittest.lecturer.*;"+ 
					 "\n" + "import junittest.student.*;" + 
					 "\n" + "import static org.junit.Assert.*;" + 
					 "\n" + "import org.junit.Test;" +
					 "\n" + "import java.util.*;" + "\n");

		fWriter.write("\n" + "public class JunitTest {" + "\n"+ 
					  "public static int grade = 0;" + "\n" +
					  "public double mark = "+ overallMark + ";"+"\n" +
					  "public String name = " + '"'+ assignmentName + '"'+ ";"+ "\n" +
					   instanceofClass + "\n");
		
		fw.write(testcase  + "\n");
		fw.close();
		
	
		BufferedReader br = new BufferedReader(new FileReader(new File(".").getAbsolutePath() + "//test//junittest//text.txt"));
		while((currentline = br.readLine()) != null){
			
			currentline = currentline.trim();

			if (currentline.equals("test")){
				line  = line  + "\n"+" @Test" + "\n" + 
						"public void test" + Integer.toString(count) + "(){" + "\n"+ br.readLine() +";" + "\n"+ "grade = grade + " + br.readLine()+";"+"\n" + "}" + "\n" + "\n";
						fWriter.write(line+ "\n");
						count = count + 1;
						line = "";
                 }

             }
		
		br.close();

		
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