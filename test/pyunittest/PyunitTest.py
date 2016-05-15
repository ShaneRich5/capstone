import unittest
import student.count

mark = 5
name = "Assignment 1"
grade = 0 
def makefoo():
    x = 0
    def foo():
        nonlocal x
        x = 1
        return x
    return foo

foo = makefoo()

class Tester(unittest.TestCase):

    def setUp(self):
        pass

    def test0(self):
       global grade
       self.assertEqual(2,student.count.multiply(1,2))
       grade = grade +(foo() *1)
       print(str(grade))       

    def test1(self):
       global grade
       self.assertEqual(2,student.count.addition(1,1))
       grade = grade +(foo() *1)
       print(str(grade))       


if __name__ == '__main__':
    unittest.main()