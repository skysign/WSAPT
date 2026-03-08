from typing import List
from collections import deque

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        class Vertex:
            def __init__(self, v):
                self.v = v
                self.incoming = []
                self.outgoing = []

        vertexes = {}

        for fr, to in prerequisites:
            if fr not in vertexes.keys():
                vertexes[fr] = Vertex(fr)

            if to not in vertexes.keys():
                vertexes[to] = Vertex(to)

            vertexes[fr].outgoing.append(to)
            vertexes[to].incoming.append(fr)

        queue = deque()

        for vertex in list(vertexes.values()):
            if len(vertex.incoming) == 0:
                queue.append(vertex)

        while queue:
            vertex = queue.popleft()
            fr = vertex.v

            for to in vertex.outgoing:
                vertexes[to].incoming.remove(fr)

                if len(vertexes[to].incoming) == 0:
                    queue.append(vertexes[to])

            del vertexes[fr]

        return len(vertexes.values()) == 0