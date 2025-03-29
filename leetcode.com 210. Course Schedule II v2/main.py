from typing import List
from collections import deque

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        incoming_edge = [0 for _ in range(numCourses)]
        edge = [[] for _ in range(numCourses)]
        answer = []
        queue = deque()

        for to, fr in prerequisites:
            edge[fr].append(to)
            incoming_edge[to] += 1

        for i in range(numCourses):
            if incoming_edge[i] == 0:
                queue.append(i)

        while queue:
            fr = queue.popleft()
            answer.append(fr)

            for to in edge[fr]:
                incoming_edge[to] -= 1
                if incoming_edge[to] == 0:
                    queue.append(to)

        for i in range(numCourses):
            if incoming_edge[i] != 0:
                # cycle이 있어서, course를 수강하는게 불가능한 경우
                return []

        return answer

