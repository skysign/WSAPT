import sys
from pathlib import Path
from unittest import TestCase
from main import solve


class Test(TestCase):
    def test1_solve(self):
        sys.stdin = open('test1.txt', 'r')
        stdout = sys.stdout
        sys.stdout = open('stdout.txt', 'w')
        solve()
        sys.stdout.close()
        sys.stdout = stdout
        self.assertEqual('<', Path('stdout.txt').read_text().strip())
