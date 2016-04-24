/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junittest.student;
import java.util.ArrayList;

public class Student {
   
   public Student(){
   } 
   
   public int getGradeAvg(int a, int b){
        int x = (a + b)/2;
        return x;
   
   }
   
   public int getAddition(int a ,int b){
       int x = a - b;
       return x;
   }
       
   public ArrayList getList(){
       int x = 0;
       ArrayList<Integer> list = new ArrayList<>();
       while( x < 5){
           list.add(x);
           x++;
        }
       return list;
    }
   
 }
