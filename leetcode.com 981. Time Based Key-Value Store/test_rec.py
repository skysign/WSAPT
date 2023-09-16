from unittest import TestCase
from main import TimeMap
from main import rec

class Test(TestCase):
    def test_rec_none(self):
        self.assertEqual(None, rec([10, 20], 5, 0, 1))

    def test_rec_0(self):
        self.assertEqual(0, rec([5, 10, 20], 5, 0, 2))

    def test_rec_1(self):
        self.assertEqual(1, rec([1, 5, 20], 5, 0, 2))

    def test_rec_1(self):
        self.assertEqual(1, rec([1, 4, 20], 5, 0, 2))

    def test_rec_1(self):
        self.assertEqual(0, rec([3], 4, 0, 0))

    def test_rec_1(self):
        self.assertEqual(1, rec([3, 4], 4, 0, 1))
