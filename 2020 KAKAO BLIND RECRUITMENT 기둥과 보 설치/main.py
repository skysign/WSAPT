from typing import List


def solution(n: int, build_frame: List[List[int]]):
    maps = [[[False, False] for _ in range(n + 2)] for _ in range(n + 2)]

    for line in build_frame:
        col, row, gb, build = line
        row += 1
        col += 1

        if build:  # 설치
            if gb:  # 보 1
                can_build = can_build_bo(maps, row, col)
                if not can_build:
                    continue
                maps[row][col][gb] = True
            else:  # 기둥 0
                can_build = can_build_gidoong(maps, row, col)
                if not can_build:
                    continue
                maps[row][col][gb] = True
        else:  # 삭제
            if gb:  # 보 1
                if not maps[row][col][gb]:
                    continue

                maps[row][col][gb] = False  # 임시로 보를 삭제함

                can_delete = True
                if maps[row][col - 1][gb]:  # 왼쪽에 보가 있는 경우
                    can_delete = can_delete and can_build_bo(maps, row, col - 1)  # 왼쪽 보가 설치 가능한지 확인
                if maps[row][col + 1][gb]:  # 오른쪽에 보가 있는 경우
                    can_delete = can_delete and can_build_bo(maps, row, col + 1)  # 오른쪽 보가 설치 가능한지 확인
                if maps[row][col][0]:  # 보 위에 기둥이 있는 경우
                    can_delete = can_delete and can_build_gidoong(maps, row, col)
                if maps[row][col + 1][0]:  # 보 위에 기둥이 있는 경우
                    can_delete = can_delete and can_build_gidoong(maps, row, col + 1)

                if can_delete:  # row, col에 위치한 보가 없어져도, 왼쪽/오른쪽에 있는 보를 설치하는대 문제가 없음
                    continue
                else:
                    maps[row][col][gb] = True

            else:  # 기둥 0
                if not maps[row][col][gb]:
                    continue

                maps[row][col][gb] = False  # 임시로 기둥을 삭제함
                can_delete = True

                if maps[row - 1][col][gb]:
                    can_delete = can_delete and can_build_gidoong(maps, row - 1, col)
                if maps[row + 1][col][gb]:
                    can_delete = can_delete and can_build_gidoong(maps, row + 1, col)
                if maps[row + 1][col][1]:
                    can_delete = can_delete and can_build_bo(maps, row + 1, col)
                if maps[row + 1][col - 1][1]:
                    can_delete = can_delete and can_build_bo(maps, row + 1, col - 1)

                if can_delete:
                    continue
                else:
                    maps[row][col][gb] = True

    answer = []

    for row in range(1, n + 2):
        for col in range(1, n + 2):
            if maps[row][col][0]:
                answer.append([col - 1, row - 1, 0])
            if maps[row][col][1]:
                answer.append([col - 1, row - 1, 1])

    answer.sort(key=lambda item: [item[0], item[1], item[2]])
    return answer


def can_build_bo(maps, row, col):
    # 보는 한쪽 끝 부분이 기둥 위에 있거나,
    b1 = maps[row - 1][col][0] or maps[row - 1][col + 1][0]
    # 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
    b2 = maps[row][col - 1][1] and maps[row][col + 1][1]
    return b1 or b2


def can_build_gidoong(maps, row, col):
    return row == 1 or maps[row - 1][col][0] == True or maps[row][col][1] == True or maps[row][col - 1][1] == True
