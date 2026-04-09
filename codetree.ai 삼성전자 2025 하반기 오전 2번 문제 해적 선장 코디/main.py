from collections import deque
import heapq

def solve():
    T = int(input())
    dt = dict()
    heap = []
    passed_time = 0

    for _ in range(T):
        cmds = list(map(int, input().split()))

        if cmds[0] == 100:
            queue = deque(cmds[2:])
            while queue:
                id = queue.popleft()
                p = queue.popleft()
                rmax = queue.popleft()
                dt[id] = [p, rmax]
                ship = [-p, id, 0]
                heapq.heappush(heap, ship)

            passed_time +=1

        elif cmds[0] == 200:
            id = cmds[1]
            p = cmds[2]
            rmax = cmds[3]
            dt[id] = [p, rmax]
            ship = [-p, id, 0]
            heapq.heappush(heap, ship)
            passed_time += 1

        elif cmds[0] == 300:
            id = cmds[1]
            p = cmds[2]
            dt[id][0] = p
            ship = [-p, id, 0]
            heapq.heappush(heap, ship)
            passed_time += 1

        elif cmds[0] == 400:
            count = 5
            attack_ships = []
            reloading_ships = []
            sum_power = 0

            while heap and count > 0:
                ship = heapq.heappop(heap)
                if -dt[ship[1]][0] != ship[0]:
                    continue

                # 마지막으로 발사했던 시간이 ship[2]
                if ship[2] == 0 or passed_time >= ship[2] + dt[ship[1]][1]:
                    attack_ships.append(ship)
                    sum_power += -ship[0]
                    ship[2] = passed_time
                    count -= 1
                else:
                    reloading_ships.append(ship)

            line = str(sum_power)
            line += ' ' + str(len(attack_ships))

            for ship in attack_ships:
                line += ' ' + str(ship[1])
                heapq.heappush(heap, ship)

            for ship in reloading_ships:
                heapq.heappush(heap, ship)

            print(line)

            passed_time += 1

if __name__ == '__main__':
    solve()