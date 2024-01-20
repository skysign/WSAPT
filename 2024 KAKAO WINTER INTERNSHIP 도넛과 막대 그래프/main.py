from typing import List, Dict

import sys
sys.setrecursionlimit(1000000 * 2)


class Vertex:
    def __init__(self, n):
        self.n: int = n
        self.visited: bool = False
        self.edge_incoming: List[Edge] = []
        self.edge_outgoing: List[Edge] = []

class Edge:
    def __init__(self, v_fr: Vertex, v_to: Vertex):
        self.v_fr = v_fr
        self.v_to = v_to
        v_fr.edge_outgoing.append(self)
        v_to.edge_incoming.append(self)

def build_vertexes(vertexes: Dict, edges: List[List[int]]):
    for n_fr, n_to in edges:
        if n_fr in vertexes.keys():
            pass
        else:
            vertexes[n_fr] = Vertex(n_fr)

        if n_to in vertexes.keys():
            pass
        else:
            vertexes[n_to] = Vertex(n_to)

        v_fr = vertexes[n_fr]
        v_to = vertexes[n_to]
        Edge(v_fr, v_to)

def find_jung_vertex(vertexes: Dict) -> Vertex:
    candi = []

    for vertex in vertexes.values():
        if len(vertex.edge_incoming) == 0 \
            and len(vertex.edge_outgoing) > 0:
            candi.append(vertex)

    candi.sort(key = lambda v: len(v.edge_outgoing), reverse=True)

    return candi[0]

graph_donut: int = 0
graph_macda: int = 0
graph_palja: int = 0

early_stop: bool = False

def travel(v: Vertex):
    global graph_donut, graph_macda, graph_palja
    global early_stop

    if early_stop:
        return

    if len(v.edge_outgoing) == 0:
        early_stop = True
        graph_macda += 1
        return

    if len(v.edge_incoming) > 1 and len(v.edge_outgoing) > 1:
        early_stop = True
        graph_palja += 1
        return

    if v.visited:
        early_stop = True
        graph_donut += 1
        return

    v.visited = True

    for edge in v.edge_outgoing:
        travel(edge.v_to)


def solution(edges: List[List[int]]):
    global early_stop
    vertexes: Dict = {}

    build_vertexes(vertexes, edges)
    v_jung = find_jung_vertex(vertexes)

    for edge in v_jung.edge_outgoing:
        edge.v_to.edge_incoming.remove(edge)
        travel(edge.v_to)
        early_stop = False

    return [v_jung.n, graph_donut, graph_macda, graph_palja]
