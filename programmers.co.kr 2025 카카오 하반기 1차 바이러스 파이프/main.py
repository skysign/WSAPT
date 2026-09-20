import copy
from collections import deque

answer = 0


def solution(n, infection, edges, k):
    global answer

    nodes = [[[], [], [], []] for _ in range(n + 1)]
    infected = set([infection])

    for node1, node2, type in edges:
        nodes[node1][type].append(node2)
        nodes[node2][type].append(node1)

    rec(infected, nodes, k, 0)

    return answer


def bfs(infected_node_number, nodes, type):
    queue = deque([infected_node_number])
    visited = set()
    visited.add(infected_node_number)

    while queue:
        node_number = queue.popleft()
        for node in nodes[node_number][type]:
            if node not in visited:
                visited.add(node)
                queue.append(node)

    return visited


def rec(infected, nodes, k, kk):
    global answer

    if k == kk:
        answer = max(answer, len(infected))
        return

    infected_all = [[copy.deepcopy(infected), 1], [copy.deepcopy(infected), 2], [copy.deepcopy(infected), 3]]

    for infected_nodes, type in infected_all:
        new_infected_nodes = set()
        for infected_node_number in infected_nodes:
            new_infected_nodes = new_infected_nodes.union(bfs(infected_node_number, nodes, type))

        infected_all[type - 1][0] = new_infected_nodes.union(infected_nodes)

    rec(infected_all[0][0], nodes, k, kk + 1)
    rec(infected_all[1][0], nodes, k, kk + 1)
    rec(infected_all[2][0], nodes, k, kk + 1)
