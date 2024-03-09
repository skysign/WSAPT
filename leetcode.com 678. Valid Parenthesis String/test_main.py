from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1_check_valid_string(self):
        sol = Solution()
        self.assertEqual(True, sol.checkValidString("()"))

    def test2_check_valid_string(self):
        sol = Solution()
        self.assertEqual(True, sol.checkValidString("(*)"))

    def test3_check_valid_string(self):
        sol = Solution()
        self.assertEqual(True, sol.checkValidString("(*))"))

    def test65_check_valid_string(self):
        sol = Solution()
        self.assertEqual(False, sol.checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"))

    def test77_check_valid_string(self):
        sol = Solution()
        self.assertEqual(False, sol.checkValidString("(((()))())))*))())()(**(((())(()(*()((((())))*())(())*(*(()(*)))()*())**((()(()))())(*(*))*))())"))


