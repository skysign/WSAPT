# 2022 카카오 신입 공채 1차 온라인 코딩테스트 신고 결과 받기 Python
#
# 유튜브 문제 풀이: https://youtu.be/09zaL3ViTWI
#
# 파이썬소스: http://bit.ly/3GbA1jc
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/92334

def solution(id_list, report, k):
    # key: 신고한 user
    # value: 신고 받은 users []
    user2reported = {}
    # key: 신고 받은 user
    # value: 신고한 users []
    reported2user = {}

    for i in range(len(report)):
        user, reported = report[i].split()

        if not user2reported.__contains__(user):
            user2reported[user] = []

        if not user2reported[user].__contains__(reported):
            user2reported[user].append(reported)

        if not reported2user.__contains__(reported):
            reported2user[reported] = []

        if not reported2user[reported].__contains__(user):
            reported2user[reported].append(user)

    answer = [0 for _ in range(len(id_list))]

    for i in range(len(id_list)):
        user = id_list[i]

        if user2reported.__contains__(user):
            for reported in user2reported[user]:
                if reported2user.__contains__(reported):
                    if len(reported2user[reported]) >= k:
                        answer[i] += 1

    return answer