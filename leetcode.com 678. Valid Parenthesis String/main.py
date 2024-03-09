class Solution:
    def checkValidString(self, s: str) -> bool:
        left_min = 0    # left 괄호의 최소 수
        left_max = 0    # left 괄호의 최대 수

        for c in s:
            if c == '(':
                left_min += 1
                left_max += 1
            elif c == ')':
                left_min -= 1
                left_max -= 1
            else:
                left_min -= 1
                left_max += 1

            # *를 모두 left 괄호로 가정한 값이 left_max 인대,
            # 이 값이 0 보다 작다면, invalid 한 s 이다.
            if left_max < 0:
                return False

            # * 를 모두 left 괄호로 가정했기 때문에,
            # left로 가정했던 * 가 right 괄호였다면 발생하지 안는 경우
            # 따라서 0 보다 작게 되면, 0으로 변경해준다.
            if left_min < 0:
                left_min = 0

        return left_min == 0
