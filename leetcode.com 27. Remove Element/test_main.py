from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_remove_element(self):
        sln = Solution()
        nums = [3, 2, 2, 3]
        val = 3
        self.assertEqual(2, sln.removeElement(nums, val))
        self.assertEqual([2,2], nums[:2])

    def test2_remove_element(self):
        sln = Solution()
        nums = [0,1,2,2,3,0,4,2]
        val = 2
        self.assertEqual(5, sln.removeElement(nums, val))
        self.assertEqual([0,1,4,0,3], nums[:5])
