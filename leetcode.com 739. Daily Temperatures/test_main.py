from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_daily_temperatures(self):
        sol = Solution()
        self.assertEqual([1,1,4,2,1,1,0,0], sol.dailyTemperatures([73,74,75,71,69,72,76,73]))
