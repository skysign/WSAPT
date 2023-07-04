# BOJ 2234번 성곽
#
# 유튜브 문제 풀이: https://youtu.be/MffbKhMOLX8
#
# 파이썬 소스: https://bit.ly/3JLE9sN
#
# 문제 링크: https://www.acmicpc.net/problem/2234

import sys

N = 0
M = 0
board = None

room = None
visited = None
adj_rooms = {}


def solve():
    global N, M, board, visited, room, adj_rooms

    visited = [[False for _ in range(N)] for _ in range(M)]
    room = [[-1 for _ in range(N)] for _ in range(M)]
    room_count = 0

    for j in range(M):
        for i in range(N):
            queue = [[i, j]]
            if bfs_find_room(queue, room_count):
                room_count += 1

    room_size = [0 for _ in range(room_count)]

    for j in range(M):
        for i in range(N):
             room_size[room[j][i]] += 1

    max_room_size = max(room_size)

    max_merged_two_room_size = 0

    for min_room_number in adj_rooms.keys():
        max_room_numbers = adj_rooms.get(min_room_number)
        for max_room_number in max_room_numbers:
            max_merged_two_room_size = max(max_merged_two_room_size, room_size[min_room_number] + room_size[max_room_number])

    print(room_count)
    print(max_room_size)
    print(max_merged_two_room_size)


def bfs_find_room(queue, room_number):
    global N, M, board, visited, room, adj_rooms

    new_room_is_found = False
    dijs = [[-1, 0], [0, -1], [1, 0], [0, 1]]
    # west 1
    # north 2
    # east 4
    # south 8

    while len(queue) > 0:
        si, sj = queue.pop(0)

        if visited[sj][si]:
            continue

        visited[sj][si] = True
        room[sj][si] = room_number
        new_room_is_found = True

        for idx in range(4):
            di, dj = dijs[idx]

            if (board[sj][si] & (2 ** idx)) > 0:
                adji = si + di
                adjj = sj + dj

                if 0 <= adji < N and 0 <= adjj < M and room[adjj][adji] != -1 and room[sj][si] != room[adjj][adji]:
                    min_room_number = min(room[sj][si], room[adjj][adji])
                    max_room_number = max(room[sj][si], room[adjj][adji])
                    if min_room_number in adj_rooms:
                        if False == (max_room_number in adj_rooms[min_room_number]):
                            adj_rooms[min_room_number] = [max_room_number] + adj_rooms[min_room_number]
                    else:
                        adj_rooms[min_room_number] = [max_room_number]
            else:
                dsti = si + di
                dstj = sj + dj

                if 0 <= dsti < N and 0 <= dstj < M and visited[dstj][dsti] == False:
                    queue.append([dsti, dstj])

    return new_room_is_found

def input():
    N, M = sys.stdin.readline().split()
    N = int(N)
    M = int(M)

    board = []

    for _ in range(M):
        board.append(sys.stdin.readline().split())

    for j in range(len(board)):
        for i in range(len(board[j])):
            board[j][i] = int(board[j][i])

    return N, M, board

if __name__ == '__main__':
    N, M, board = input()
    solve()
