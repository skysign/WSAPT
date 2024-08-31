from unittest import TestCase
from main import solution


class Test(TestCase):
    def test1_solution(self):
        self.assertEquals(12, solution([1, 3, 2],
                                       ["diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"]))

    def test2_solution(self):
        self.assertEquals(50, solution([0, 1, 1],
                                       ["diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron",
                                        "iron", "iron", "diamond"]))