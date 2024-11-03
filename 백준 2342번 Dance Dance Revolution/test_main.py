import sys
from pathlib import Path
from unittest import TestCase
from main import solve


class Test(TestCase):
    def my_solve(self, testcase_input):
        sys.stdin = open(testcase_input, 'r')
        stdout = sys.stdout
        sys.stdout = open('stdout.txt', 'w')
        solve()
        sys.stdout.close()
        sys.stdout = stdout

    def test_solve(self, testcase_number: str):
        self.my_solve('test' + testcase_number + '.txt')
        self.assertEqual(
            Path('test' + testcase_number + '_answer.txt').read_text().strip(),
            Path('stdout.txt').read_text().strip())

    def test1_solve(self):
        self.test_solve('1')

    # 추가 테케 필요하시면, 아래 링크 참고하세요
    # https://www.acmicpc.net/board/view/59839