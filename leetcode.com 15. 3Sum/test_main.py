from unittest import TestCase
from main import Solution

# leetcode.com 15. 3Sum
#
# 유튜브 문제 풀이: https://youtu.be/RCa2xJFDUIg?si=BR9FHxPXvIM30adZ
#
# 파이썬 소스: https://bit.ly/3LRMvQR
#
# 문제 링크: https://leetcode.com/problems/3sum/

class TestSolution(TestCase):
    def test1_three_sum(self):
        sol = Solution()

        r1 = set()
        answers = [[-1, -1, 2], [-1, 0, 1]]
        for ans in answers:
            ans.sort()
            ans2 = tuple(ans)
            r1.add(ans2)

        r2 = set()
        rlts = sol.threeSum([-1, 0, 1, 2, -1, -4])
        for ans in rlts:
            ans.sort()
            ans2 = tuple(ans)
            r2.add(ans2)

        self.assertEqual(r1, r2)

    def test2_three_sum(self):
        sol = Solution()

        r1 = set()
        answers = []
        for ans in answers:
            ans.sort()
            ans2 = tuple(ans)
            r1.add(ans2)

        r2 = set()
        rlts = sol.threeSum([0,1,1])
        for ans in rlts:
            ans.sort()
            ans2 = tuple(ans)
            r2.add(ans2)

        self.assertEqual(r1, r2)

    def test3_three_sum(self):
        sol = Solution()

        r1 = set()
        answers = [[0,0,0]]
        for ans in answers:
            ans.sort()
            ans2 = tuple(ans)
            r1.add(ans2)

        r2 = set()
        rlts = sol.threeSum([0,0,0])
        for ans in rlts:
            ans.sort()
            ans2 = tuple(ans)
            r2.add(ans2)

        self.assertEqual(r1, r2)
