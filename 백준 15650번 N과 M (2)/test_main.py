import sys
from pathlib import Path
from unittest import TestCase
from main import solve

def my_solve(testcase_input):
    sys.stdin = open(testcase_input, 'r')
    stdout = sys.stdout
    sys.stdout = open('stdout.txt', 'w')
    solve()
    sys.stdout.close()
    sys.stdout = stdout

class Test(TestCase):
    def test1_solve(self):
        my_solve('test1.txt')
        self.assertEqual(
            Path('test1_answer.txt').read_text().strip(),
            Path('stdout.txt').read_text().strip())

    def test2_solve(self):
        my_solve('test2.txt')
        self.assertEqual(
            Path('test2_answer.txt').read_text().strip(),
            Path('stdout.txt').read_text().strip())

    def test3_solve(self):
        my_solve('test3.txt')
        self.assertEqual(
            Path('test3_answer.txt').read_text().strip(),
            Path('stdout.txt').read_text().strip())