from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_can_construct(self):
        sln = Solution()
        self.assertEqual(False, sln.canConstruct('a', 'b'))

    def test2_can_construct(self):
        sln = Solution()
        self.assertEqual(False, sln.canConstruct('aa', 'ab'))

    def test3_can_construct(self):
        sln = Solution()
        self.assertEqual(True, sln.canConstruct('aa', 'aab'))
