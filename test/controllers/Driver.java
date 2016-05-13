/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.Test;
import junittest.JunitTest;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Tevin
 */
public class Driver {

    @Test
    public void main() throws InstantiationException, IllegalAccessException{
         
        Result result = JUnitCore.runClasses(JunitTest.class);
        Object obj = JunitTest.class.newInstance();
        String failed = "";
         try{
          for(Failure failure : result.getFailures()){
            failed = failed + "Test Failed: " + failure.toString() + "\n";
            }
          System.out.println(result.wasSuccessful());
          Method method = JunitTest.class.getMethod("grade");
          Method method2 = JunitTest.class.getMethod("percentage");
          Method method3 = JunitTest.class.getMethod("getName");
          System.out.println("Final grade: " + method.invoke(obj));
          System.out.println("Percentage: " + method2.invoke(obj));
          System.out.println(failed);
          
            try{
               File file = new File(method3.invoke(obj) + ".txt");
               if(!file.exists()){
                 file.createNewFile();
               }
              FileWriter fw = new FileWriter(file, true);
              BufferedWriter bw = new BufferedWriter(fw);
              PrintWriter pw = new PrintWriter(bw);
              pw.println("");
              pw.println("Student: " + method.invoke(obj));
              pw.close();
         
               }
            catch(IOException io){
                    io.getMessage();
                 }
          }
         catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
             System.out.println(ex.getMessage());
           }
         
         
    }
}
