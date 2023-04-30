from unittest import TestCase
from main import solution

# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 표 병합 Python
#
# 유튜브 문제 풀이: https://youtu.be/4HHe_aMacKw
#
# 파이썬 소스: https://bit.ly/3LgKrAJ
#
# 문제 링크: https://bit.ly/3Nr1SRM

class Test(TestCase):
    def test1_solution(self):
        self.assertEqual(
            ["EMPTY", "group"],
            solution(
                ["UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"]
            )
        )

    def test2_solution(self):
        self.assertEqual(
            ["d", "EMPTY"],
            solution(
                ["UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"]
            )
        )

    # 테스트 케이스 출처: https://school.programmers.co.kr/questions/45437
    def test3_solution(self):
        self.assertEqual(
            ["hansik", "hansik", "hansik", "hansik"],
            solution(
                [ "UPDATE 1 1 menu", "MERGE 1 1 1 2", "MERGE 1 1 1 3", "MERGE 1 1 1 4", "MERGE 1 2 1 3", "UPDATE 1 1 hansik", "PRINT 1 1", "PRINT 1 2", "PRINT 1 3", "PRINT 1 4"]
            )
        )