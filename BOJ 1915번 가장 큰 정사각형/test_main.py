import os
from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_main(self):
        self.assertEqual(solution(filename='t1.txt'), '4')
