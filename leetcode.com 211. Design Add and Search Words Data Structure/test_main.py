from unittest import TestCase
from main import WordDictionary

class TestWordDictionary(TestCase):
    def test_my1(self):
        w = WordDictionary()
        w.addWord('bad')
        w.addWord('dad')
        w.addWord('mad')
        w.addWord('pad')
        w.addWord('bad')
        b = w.search('.ad')
        print(b)

