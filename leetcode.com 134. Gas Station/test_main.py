from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_can_complete_circuit(self):
        sol = Solution()
        self.assertEqual(3, sol.canCompleteCircuit([1,2,3,4,5], [3,4,5,1,2]))

    def test2_can_complete_circuit(self):
        sol = Solution()
        self.assertEqual(-1, sol.canCompleteCircuit([2,3,4], [3,4,3]))
