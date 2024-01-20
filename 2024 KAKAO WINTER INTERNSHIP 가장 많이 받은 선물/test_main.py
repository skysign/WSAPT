from unittest import TestCase
from main import solution

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(2,
                         solution(["muzi", "ryan", "frodo", "neo"],
                                  ["muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi", "frodo ryan", "neo muzi"]))

    def test2_solution(self):
        self.assertEqual(4,
                         solution(["joy", "brad", "alessandro", "conan", "david"],
                                  ["alessandro brad", "alessandro joy", "alessandro conan", "david alessandro", "alessandro david"]))
