from typing import List

def solution(board: List[List], moves: List):
    cnt_col = len(board[0])
    pan = [[] for _ in range(cnt_col)]
    stk = []

    while len(board) > 0:
        row = board.pop()

        for idx in range(cnt_col):
            if row[idx] != 0:
                pan[idx].append(row[idx])

    answer = 0

    # 네오 1
    # 무지 2
    # 콘 3
    # 어피치 4
    # 프로도 5
    for idx in moves:
        idx -= 1

        if len(pan[idx]) > 0:
            item = pan[idx].pop()

            if stk and stk[-1] == item:
                stk.pop()
                answer += 2
            else:
                stk.append(item)

    return answer