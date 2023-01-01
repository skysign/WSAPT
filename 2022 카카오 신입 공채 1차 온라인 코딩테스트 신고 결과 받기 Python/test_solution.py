from unittest import TestCase
from solution import solution


class Test(TestCase):
    def test_solution(self):
        self.assertEqual([2,1,1,0],
                         solution(["muzi", "frodo", "apeach", "neo"],
                                  ["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"],
                                  2))
    def test_solution2(self):
        self.assertEqual([0,0],
                         solution(["con", "ryan"],
                                  ["ryan con", "ryan con", "ryan con", "ryan con"],
                                  3))
