from typing import List

OUTGOING = 1
INCOMING = 2

class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        answers = [v for v in range(numCourses)]

        vertexes: dict = {}

        for to, fr in prerequisites:
            if fr not in vertexes.keys():
                vertexes[fr] = [fr, [], []]
            if to not in vertexes.keys():
                vertexes[to] = [to, [], []]

            vertexes[fr][OUTGOING].append(to)
            vertexes[to][INCOMING].append(fr)

        for v in vertexes.keys():
            answers.remove(v)

        queue = []

        for key in list(vertexes.keys()):
            if len(vertexes[key][INCOMING]) == 0:
                queue.append(vertexes[key])

        while queue:
            fr, outgoing, _ = queue.pop(0)
            del vertexes[fr]
            answers.append(fr)

            for to in outgoing:
                vertexes[to][INCOMING].remove(fr)

                if len(vertexes[to][INCOMING]) == 0:
                    queue.append(vertexes[to])

        if len(vertexes.keys()) == 0:
            return answers

        return []