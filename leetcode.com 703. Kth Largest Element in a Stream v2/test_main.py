from unittest import TestCase
from main import KthLargest


class TestKthLargest(TestCase):
    def test1(self):
        obj = KthLargest(3, [4, 5, 8, 2])
        self.assertEqual(4, obj.add(3))
        self.assertEqual(5, obj.add(5))
        self.assertEqual(5, obj.add(10))
        self.assertEqual(8, obj.add(9))
        self.assertEqual(8, obj.add(4))

    def test8(self):
        obj = KthLargest(3,[1,1])
        self.assertEqual(1, obj.add(1))
        self.assertEqual(1, obj.add(1))
        self.assertEqual(1, obj.add(3))
        self.assertEqual(1, obj.add(3))
        self.assertEqual(3, obj.add(3))
        self.assertEqual(3, obj.add(4))
        self.assertEqual(3, obj.add(4))
        self.assertEqual(4, obj.add(4))
