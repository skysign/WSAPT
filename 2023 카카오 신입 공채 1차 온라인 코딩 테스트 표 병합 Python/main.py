# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 표 병합 Python
#
# 유튜브 문제 풀이: https://youtu.be/4HHe_aMacKw
#
# 파이썬 소스: https://bit.ly/3LgKrAJ
#
# 문제 링크: https://bit.ly/3Nr1SRM

def solution(commands):
    answers = []

    board = [["EMPTY" for c in range(51)] for r in range(51)]
    merged = [[(r, c) for c in range(51)] for r in range(51)]

    for command in commands:
        data = str(command).split(' ')
        cmd = data[0]

        if cmd == 'UPDATE':
            if len(data) == 4:
                r, c, value = int(data[1]), int(data[2]), data[3]
                rt, ct = merged[r][c]
                board[rt][ct] = value
            else:
                value1, value2 = data[1], data[2]
                for r in range(51):
                    for c in range(51):
                        if board[r][c] == value1:
                            board[r][c] = value2
        elif cmd == 'MERGE':
            r1, c1, r2, c2 = int(data[1]), int(data[2]), int(data[3]), int(data[4])
            if r1 == r2 and c1 == c2:
                continue

            # r2,c2를 r1,c1에 병합합니다.
            rt, ct = merged[r1][c1]
            rt2, ct2 = merged[r2][c2]

            if board[rt][ct] == "EMPTY":
                board[rt][ct] = board[rt2][ct2]

            for r in range(51):
                for c in range(51):
                    if merged[r][c] == (rt2, ct2):
                        merged[r][c] = (rt, ct)

        elif cmd == 'UNMERGE':
            sr, sc = int(data[1]), int(data[2])
            rt, ct = merged[sr][sc]
            v = board[rt][ct]

            for r in range(51):
                for c in range(51):
                    if merged[r][c] == (rt, ct):
                        merged[r][c] = (r, c)
                        board[r][c] = "EMPTY"

            board[sr][sc] = v

        elif cmd == 'PRINT':
            r, c = int(data[1]), int(data[2])
            answers.append(board[rt][ct])
            rt, ct = merged[r][c]

    return answers
