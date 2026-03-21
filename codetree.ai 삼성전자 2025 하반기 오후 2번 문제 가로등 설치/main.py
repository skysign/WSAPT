import math


def solve():
    cnt_cmds = int(input().strip())
    N, M, streetlights = 0, 0, []

    for _ in range(cnt_cmds):
        tmps = list(map(int, input().strip().split(' ')))
        cmd = tmps[0]

        if cmd == 100:
            N, M = tmps[1], tmps[2]
            mx_M = M

            for idx in range(len(tmps[3:])):
                streetlights.append([idx + 1, tmps[3:][idx]])
        elif cmd == 200:
            M += 1
            mx_M += 1
            new_streetlight = [0, 0, M]
            for idx in range(len(streetlights) - 1):
                if idx == 0:
                    left, right = 1, streetlights[idx + 1][1]
                elif 0 < idx < len(streetlights) - 1:
                    left, right = streetlights[idx - 1][1], streetlights[idx + 1][1]

                local_delta, local_Ln, local_idx = 0, 0, 0

                if streetlights[idx][1] - left >= right - streetlights[idx][1]:
                    local_delta = streetlights[idx][1] - left
                    local_Ln = math.ceil((left + streetlights[idx][1]) / 2)
                    local_idx = idx
                else:
                    local_delta = right - streetlights[idx][1]
                    local_Ln = math.ceil((streetlights[idx][1] + right) / 2)
                    local_idx = idx + 1

                if new_streetlight[0] < local_delta:
                    new_streetlight[0], new_streetlight[1], new_streetlight[2] = local_delta, local_Ln, local_idx
                elif new_streetlight[0] == local_delta:
                    if new_streetlight[1] > local_Ln:
                        new_streetlight[0], new_streetlight[1], new_streetlight[2] = local_delta, local_Ln, local_idx

            if new_streetlight[2] < len(streetlights):
                streetlights = streetlights[0:new_streetlight[2]] + [[mx_M, new_streetlight[1]]] + streetlights[new_streetlight[2]:]
            else:
                streetlights = streetlights + [[M, new_streetlight[1]]]

        elif cmd == 300:
            D = tmps[1]
            idx = -1
            for i in range(len(streetlights)):
                if streetlights[i][0] == D:
                    idx = i
                    break

            streetlights.pop(idx)
            M -= 1

        elif cmd == 400:
            mx_r = 0
            for idx in range(len(streetlights)):
                if idx == 0:
                    left, right = 1, streetlights[idx + 1][1]
                    r = max(streetlights[idx][1] - left, (right - streetlights[idx][1]) / 2)
                elif 0 < idx < len(streetlights) - 1:
                    left, right = streetlights[idx - 1][1], streetlights[idx + 1][1]
                    r = max((streetlights[idx][1] - left) / 2, (right - streetlights[idx][1]) / 2)
                else:  # if idx == len(streetlights)-1
                    left, right = streetlights[idx - 1][1], N
                    r = max((streetlights[idx][1] - left) / 2, right - streetlights[idx][1])

                mx_r = max(mx_r, r)

            print(round(mx_r * 2))


if __name__ == '__main__':
    solve()
