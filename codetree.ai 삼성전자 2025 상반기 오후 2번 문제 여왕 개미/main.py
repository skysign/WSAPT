def solve():
    houses_pos = [0]  # 여왕 개미의 집
    houses_deleted = [False]  # 여왕 개미의 집

    Q = int(input())

    for _ in range(Q):
        cmds = list(map(int, input().split()))
        cmd = cmds[0]

        if cmd == 100:
            for pos in cmds[2:]:
                houses_pos.append(pos)
                houses_deleted.append(False)
        elif cmd == 200:
            pos_new = cmds[1]
            houses_pos.append(pos_new)
            houses_deleted.append(False)
        elif cmd == 300:
            pos_deleted = cmds[1]
            houses_deleted[pos_deleted] = True
        elif cmd == 400:
            cnt_ant = cmds[1]

            mn_time, mx_time = 0, 1000000000
            min_time = 0

            while mn_time <= mx_time:
                mid_time = (mn_time + mx_time) // 2

                intervals = 0
                last_pos = -1000000000

                for idx in range(1, len(houses_pos)):
                    if houses_deleted[idx]: # 삭제된 집인 경우 skip
                        continue

                    crnt_pos = houses_pos[idx]

                    if crnt_pos - last_pos > mid_time:
                        last_pos = crnt_pos
                        intervals += 1

                if intervals <= cnt_ant:
                    min_time = mid_time
                    mx_time = mid_time - 1
                else:
                    mn_time = mid_time + 1

            print(min_time)

if __name__ == '__main__':
    solve()
