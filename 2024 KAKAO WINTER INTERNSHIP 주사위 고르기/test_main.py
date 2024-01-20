from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1(self):
        self.assertEqual([1, 4], solution([[1, 2, 3, 4, 5, 6], [3, 3, 3, 3, 4, 4], [1, 3, 3, 4, 4, 4], [1, 1, 4, 4, 5, 5]]))

    def test2(self):
        self.assertEqual([2], solution([[1, 2, 3, 4, 5, 6], [2, 2, 4, 4, 6, 6]]))
