def solve():
    Q = int(input())
    houses = [0]  # 여왕 개미의 집을 x=0 위치에 건설합니다.

    for _ in range(Q):
        cmds = list(map(int, input().split()))

        if cmds[0] == 100:
            for house in cmds[2:]:
                houses.append(house)
        elif cmds[0] == 200:
            houses.append(cmds[1])
        elif cmds[0] == 300:
            q = cmds[1]
            houses[q] = -1
        elif cmds[0] == 400:
            r = cmds[1]
            mn_time = 0
            # mx_time = houses[-1]
            mx_time = 1000000000
            min_time = 0

            while mn_time <= mx_time:
                mid_time = (mn_time + mx_time) // 2

                last_house = -1
                interval = 1

                for house in houses[1:]:
                    if house < 0:
                        continue

                    if last_house < 0:
                        last_house = house
                    elif house - last_house > mid_time:
                        interval += 1
                        last_house = house

                if interval <= r:
                    min_time = mid_time
                    mx_time = mid_time - 1
                else:
                    mn_time = mid_time + 1

            print(min_time)


if __name__ == '__main__':
    solve()
