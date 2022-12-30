def solution(id_list, report, k):
    # key: 신고한 user
    # value : 신고 당한 user
    user_reported = {}

    # key: 신고 당한 user
    # value : 신고한 user
    reported_user = {}

    for i in range(len(report)):
        user, reported = report[i].split()

        if not reported_user.__contains__(reported):
            reported_user[reported] = []

        if not reported_user[reported].__contains__(user):
            reported_user[reported].append(user)

        if not user_reported.__contains__(user):
            user_reported[user] = []

        if not user_reported[user].__contains__(reported):
            user_reported[user].append(reported)


    answer = [0 for _ in range(len(id_list))]

    for i in range(len(id_list)):
        user = id_list[i]

        if user_reported.__contains__(user):
            for r in user_reported[user]:
                if reported_user.__contains__(r):
                    if len(reported_user[r]) >= k:
                        answer[i] += 1

    return answer
