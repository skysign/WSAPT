from unittest import TestCase
from main import Solution


class TestSolution(TestCase):
    def test1_min_distance(self):
        sol = Solution()
        self.assertEqual(3, sol.minDistance(word1 = "horse", word2 = "ros"))

    def test2_min_distance(self):
        sol = Solution()
        self.assertEqual(5, sol.minDistance(word1 = "intention", word2 = "execution"))

    def test968_min_distance(self):
        sol = Solution()
        self.assertEqual(2, sol.minDistance(word1="ab", word2="bc"))
