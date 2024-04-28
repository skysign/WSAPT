from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution("...!@BaT#*..y.abcdefghijklm"), "bat.y.abcdefghi")

    def test2_solution(self):
        self.assertEqual(solution("z-+.^."), "z--")

    def test3_solution(self):
        self.assertEqual(solution("=.="), "aaa")

    def test4_solution(self):
        self.assertEqual(solution("123_.def"), "123_.def")

    def test5_solution(self):
        self.assertEqual(solution("abcdefghijklmn.p"), "abcdefghijklmn")
