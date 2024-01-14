from unittest import TestCase
from main import Solution

class TestSolution(TestCase):
    def test1(self):
        sol = Solution()
        self.assertEqual(["oath","eat"],
                         sol.findWords([["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], ["oath","pea","eat","rain"]))

    def test19(self):
        sol = Solution()
        self.assertEqual(["oath","eat","hklf","hf"],
        # self.assertEqual(["hklf", "hf"],
        # self.assertEqual(["hf"],
                         sol.findWords([["o","a","a","n"],
                                        ["e","t","a","e"],
                                        ["i","h","k","r"],
                                        ["i","f","l","v"]],
                                        ["oath","pea","eat","rain","hklf", "hf"]))
                                        # ["hklf", "hf"]))
                                       #  ["hf"]))

    def test37(self):
        sol = Solution()
        self.assertEqual([],
                         sol.findWords([["a"],["a"]], ["aaa"]))
