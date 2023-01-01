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