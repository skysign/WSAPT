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

            if r >= len(houses) - 1:
                print(0)
                continue


            mn_time = 0
            min_time = 0

            # 문제 푸는 방법은 2가지가 있는대요,
            # #1 철거되지 않은 모든 개미집을 모아서
            # 가장 오른쪽에 있는 개미집의 위치를 mx_time으로 사용하는 방법
            # 최대값인 1000000000 보다 작은 값을 사용하게 되어서 좀더 빠른 속도를 기대할 수 있지만,
            # 철거되지 않은 개미집을 모으는대 시간과 메모리가 필요함
            good_houses = [house for house in houses if house >= 0]
            mx_time = good_houses[-1]
            # mx_time = 1000000000
            # #2 mx_time을 최대로 설정하는 방법


            while mn_time <= mx_time:
                mid_time = (mn_time + mx_time) // 2

                last_house = -1
                interval = 1

                for house in good_houses[1:]:
                    # #2 방법으로 풀면, 철거된 집을 skip 하는 아래 코드가 필요합니다.
                    # if house < 0:
                    #     continue

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
