from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_decode_string(self):
        sol = Solution()
        self.assertEqual("aaabcbc", sol.decodeString("3[a]2[bc]"))

    def test2_decode_string(self):
        sol = Solution()
        self.assertEqual("accaccacc", sol.decodeString("3[a2[c]]"))

    def test3_decode_string(self):
        sol = Solution()
        self.assertEqual("accaccacc", sol.decodeString("3[a2[c]]"))

    def test24_decode_string(self):
        sol = Solution()
        self.assertEqual("leetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcode",
                         sol.decodeString("100[leetcode]"))
