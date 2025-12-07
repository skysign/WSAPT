class Solution:
    def convert(self, s: str, numRows: int) -> str:
        if numRows == 1:
            return s

        max_row = numRows
        max_col = int((len(s) / (numRows + (numRows - 2)))) * (numRows - 1)
        remains = len(s) % (numRows * 2 - 2)
        if 0 <= remains <= numRows:
            max_col += 1
        else:
            max_col += 1
            max_col += (remains - numRows)

        board = [[' '] * max_col for _ in range(max_row)]

        drc = [[1, 0], [-1, 1]]
        ddrc = drc.pop(0)
        drc.append(ddrc)

        idx = 0
        sr, sc = 0, 0
        board[sr][sc] = s[idx]

        while True:
            idx += 1
            if len(s) <= idx:
                break

            dr, dc = ddrc
            nr, nc = sr + dr, sc + dc

            board[nr][nc] = s[idx]
            sr, sc = nr, nc

            if nr == 0 or nr == max_row - 1:
                ddrc = drc.pop(0)
                drc.append(ddrc)

        rtn = ''

        for r in range(max_row):
            for c in range(max_col):
                if board[r][c] != ' ':
                    rtn = rtn + board[r][c]

        return rtn
