from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(solution(["ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"], [2, 3, 4]), ["AC", "ACDE", "BCFG", "CDE"])

    def test2_solution(self):
        self.assertEqual(solution(["ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"], [2, 3, 5]), ["ACD", "AD", "ADE", "CD", "XYZ"])

    def test3_solution(self):
        self.assertEqual(solution(["XYZ", "XWY", "WXA"], [2, 3, 4]), ["WX", "XY"])
