package junittest; 
import junittest.lecturer.*;
import junittest.student.*;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class JunitTest {
public static int grade = 0;
public double mark = 4;
public String name = "Assingment 1";
Student student = new Student(); Lecturer lec = new Lecturer(); mathematics math = new mathematics();

 @Test
public void test0(){
compareInt(lec.getGradeAvg(10,10),student.getGradeAvg(10,10));
grade = grade + 1;
}



 @Test
public void test1(){
compareInt(lec.getAddition(10,10), student.getAddition(10,10));
grade = grade + 1;
}



 @Test
public void test2(){
compareInt(lec.Multiply(3,4),math.Multiply(3,4));
grade = grade + 1;
}



 @Test
public void test3(){
compareInt(lec.divide(12,4), math.divide(12,4));
grade = grade + 1;
}



//Method to compare integers
public void compareInt(int a, int b){ assertEquals(a,b); }

//Method to compare strings
public void compareStr(String a, String b){ assertEquals(a,b); }

//Method used get student grades
public double grade(){ return grade; }

//Method used get student grades
public String percentage(){
double p = ((double)(grade/mark) * 100);
String str = String.format("%2.01f",(double)p) ;
return str + "%";}

//Method used to get filename
public String getName(){ return name; }

}