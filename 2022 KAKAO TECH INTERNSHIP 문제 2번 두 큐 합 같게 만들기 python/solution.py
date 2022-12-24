from collections import deque

# 2022 KAKAO TECH INTERNSHIP 두 큐 합 같게 만들기 python
#
# 유튜브 문제 풀이: https://youtu.be/rntT16XgqRc
#
# 파이썬소스: http://bit.ly/3WHqBmr

def solution(queue1, queue2):
    answer = -1
    max_moving = len(queue1) + len(queue2)
    q1 = deque(queue1)
    q2 = deque(queue2)

    sum1 = sum(q1)
    sum2 = sum(q2)

    for cnt in range(0, max_moving * 2):
        if sum1 == sum2:
            answer = cnt
            break
        elif sum1 > sum2:
            item = q1.popleft()
            sum1 -= item
            q2.append(item)
            sum2 += item
        else:
            item = q2.popleft()
            sum2 -= item
            q1.append(item)
            sum1 += item

    return answer
