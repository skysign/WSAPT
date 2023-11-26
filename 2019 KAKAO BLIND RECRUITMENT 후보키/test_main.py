from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(2, solution([["100","ryan","music","2"],["200","apeach","math","2"],["300","tube","computer","3"],["400","con","computer","4"],["500","muzi","music","3"],["600","apeach","music","2"]]))

    def testx_solution(self):
        self.assertEqual(1, solution([['a', 'aa'], ['aa', 'a'], ['a', 'a']]))
