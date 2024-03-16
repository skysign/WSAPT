from typing import List

OUTGOING = 0
INCOMING = 1

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        vertexes = {}

        for [fr, to] in prerequisites:
            if fr not in vertexes.keys():
                vertexes[fr] = [[], []]

            if to not in vertexes.keys():
                vertexes[to] = [[], []]

            vertexes[fr][OUTGOING].append(to)
            vertexes[to][INCOMING].append(fr)

        queue = []

        for key in vertexes.keys():
            if len(vertexes[key][INCOMING]) == 0:
                queue.append([key, vertexes[key]])

        while len(queue) > 0:
            fr, vertex = queue.pop(0)
            del vertexes[fr]

            for to in vertex[OUTGOING]:
                vertexes[to][INCOMING].remove(fr)

                if len(vertexes[to][INCOMING]) == 0:
                    queue.append([to, vertexes[to]])

        return len(vertexes.keys()) == 0
