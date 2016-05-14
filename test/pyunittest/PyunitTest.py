import unittest
import pyunittest.student.foo

import pyunittest.student.poo

import pyunittest.student.coo

class Tester(unittest.TestCase):


    mark = 5
    name = "Assignment 1"
    def setUp(self):
        pass

   def test0(self):
       compareInt()
       grade(1)


   def test1(self):
       compareInt()
       grade(1)


     # Method to compare integers
   def compareInt(a, b):
         self.assertEquals(a,b)

     # Method to count grade
   def grade(x):
        grade.counter += x 


if __name__ == '__main__':
    unittest.main()