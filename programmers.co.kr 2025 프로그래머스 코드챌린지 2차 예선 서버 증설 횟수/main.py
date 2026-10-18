def number_of_server(player, m):
    p = player - (m - 1)
    if p <= 0:
        return 0

    quotient, remainder = divmod(p, m)
    if remainder > 0:
        quotient += 1

    return quotient

def solution(players, m, k):
    answer = 0
    servers = []

    # 서버의 수
    for player in players:
        # 서버의 사용시간 1감소
        for i in range(len(servers)):
            servers[i] -= 1

        # 0인 서버는 다 사용한 서버
        while servers.__contains__(0):
            servers.remove(0)

        current_server = len(servers)
        new_server = number_of_server(player, m)

        if new_server > current_server:
            answer += (new_server - current_server)

            for _ in range(new_server - current_server):
                servers.append(k)

    return answer
