from typing import List


def solution(commands: List[str]):
    MX = 51
    map = [['EMPTY' for _ in range(MX)] for _ in range(MX)]
    merged = [[(r, c) for c in range(MX)] for r in range(MX)]
    answer = []

    for cmd in commands:
        cmd = cmd.split(' ')

        if cmd[0] == 'UPDATE' and len(cmd) == 4:
            r, c, v = int(cmd[1]), int(cmd[2]), cmd[3]
            if merged[r][c] == (r,c):
                map[r][c] = v
            else:
                mr, mc = merged[r][c]
                map[mr][mc] = v

        elif cmd[0] == 'UPDATE' and len(cmd) == 3:
            _, v1, v2 = cmd

            for r in range(1, MX):
                for c in range(1, MX):
                    if map[r][c] == v1:
                        map[r][c] = v2

        elif cmd[0] == 'MERGE':
            r1, c1, r2, c2 = int(cmd[1]), int(cmd[2]), int(cmd[3]), int(cmd[4])
            to = merged[r1][c1]
            fr = merged[r2][c2]
            if map[to[0]][to[1]] == 'EMPTY':
                map[to[0]][to[1]] = map[fr[0]][fr[1]]

            for r in range(1, MX):
                for c in range(1, MX):
                    if merged[r][c] == fr:
                        merged[r][c] = to

        elif cmd[0] == 'UNMERGE':
            sr, sc = int(cmd[1]), int(cmd[2])
            mr, mc = merged[sr][sc]
            v = map[mr][mc]

            for r in range(1, MX):
                for c in range(1, MX):
                    if merged[r][c] == (mr, mc):
                        merged[r][c] = (r, c)
                        map[r][c] = 'EMPTY'

            map[sr][sc] = v

        elif cmd[0] == 'PRINT':
            r, c = int(cmd[1]), int(cmd[2])
            if merged[r][c] != (r, c):
                mr, mc = merged[r][c]
                answer.append(map[mr][mc])
            else:
                answer.append(map[r][c])

    return answer
