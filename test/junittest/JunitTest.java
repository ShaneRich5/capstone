/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junittest;

import junittest.lecturer.Lecturer;
import junittest.student.Student;
import junittest.student.mathematics;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;


/**
 *
 * @author Tevin
 */

public class JunitTest {
        public static int grade = 0;  //Grade allocated to student
        public double mark = 5.0;     // Mark for overall assignment
        public String name = "Assignment 1 grade";
        
        
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3,4));
        Student student = new Student();
        Lecturer lec = new Lecturer();
        mathematics math = new mathematics();
        
        
    
    //Test that compares integers
    @Test
    public void test1() {
        int x = lec.getGradeAvg(10, 10);
        int y = student.getGradeAvg(10, 10);
        compareInt(x,y);
        grade = grade + 1;
        }
    
    @Test
    public void test2() {
        int x = lec.getAddition(10, 10);
        int y = student.getAddition(10, 10);
        compareInt(x,y);
        grade = grade + 1;
    }
    
    @Test
    public void test3(){
        int x = lec.Multiply(3, 4);
        int y = math.Multiply(3, 4);
        compareInt(x,y);
        grade = grade + 1;
    }
    
    @Test
    public void test4(){
        int x = lec.divide(12, 4);
        int y = math.divide(12,4);
        compareInt(x,y);
        grade = grade + 1;
    
    }
    @Test
    
    public void test5(){
        assertEquals(student.getList(),list);
        grade = grade + 1;
    
    }
    
    // Method to compare integers
    public void compareInt(int a, int b){
        assertEquals(a,b);
    }
    
    //Method to compare strings
    public void compareStr(String a, String b){
        assertEquals(a,b);
    }
    
    //Used to get students overall grade
    public double grade(){
        return grade;
    }
    
    //Method used to convert grade into percentage
    public String percentage(){
        double p = ((double)(grade/mark) *100);
        String str = String.format("%2.01f", (double)p);
        return str + "%";
    
    }
    
    //Method used to get the name of the file
    public String getName(){
        return name;
    }
    
   
}
