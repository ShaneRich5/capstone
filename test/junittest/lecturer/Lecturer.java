/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junittest.lecturer;

/**
 *
 * @author Tevin
 */
public class Lecturer {
    
    public Lecturer(){
    }
    
    public int getGradeAvg(int a, int b){
        int x = (a + b)/2;
        return x;
   
   }
   
   public int getAddition(int a ,int b){
       int x = a + b;
       return x;
   
   }
   
   public int Multiply(int a, int b){
        int x  = a * b;
        return x;
     }
    
    public int divide(int a, int b){
        int x = a/b;
        return x;
    
    }
}
