from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_min_distance(self):
        sln = Solution()
        self.assertEqual(3, sln.minDistance('horse', 'ros'))

    def test2_min_distance(self):
        sln = Solution()
        self.assertEqual(5, sln.minDistance('intention', 'execution'))

    def test1088_min_distance(self):
        sln = Solution()
        self.assertEqual(1, sln.minDistance('', 'a'))
