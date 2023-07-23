class Vertex:
    def __init__(self, n, trap):
        self.n = n
        self.edges = []
        self.trap = trap
        self.path_cost = int(2.1 * 10 ** 8)

    def trigger_trap(self):
        if self.trap == True:
            for edge in self.edges:
                edge.trigger_trap()

    def restore_trap(self):
        if self.trap == True:
            for edge in self.edges:
                edge.restore_dir()

class Edge:
    def __init__(self, fr_vertex, to_vertex, cost):
        self.fr_vertex = fr_vertex
        self.to_vertex = to_vertex
        self.cost = cost
        self.saved_frto = []
        fr_vertex.edges.append(self)
        to_vertex.edges.append(self)


    def trigger_trap(self):
        self.saved_frto.append([self.fr_vertex, self.to_vertex])
        tmp = self.fr_vertex
        self.fr_vertex = self.to_vertex
        self.to_vertex = tmp


    def restore_dir(self):
        if len(self.saved_frto) > 0:
            fr_vertex, to_vertex = self.saved_frto.pop()
            self.fr_vertex = fr_vertex
            self.to_vertex = to_vertex


BEGIN = 0
END = 0
vertexes = []
visited_count = []
min_path_cost = int(2.1 * 10 ** 8)


def dfs(vertex_n, total_cost):
    global BEGIN, END, vertexes, visited_count, min_path_cost

    if vertex_n == END:
        min_path_cost = min(min_path_cost, total_cost)
        return

    fr_vertex = vertexes[vertex_n]
    fr_vertex.trigger_trap()

    for edge in fr_vertex.edges:
        fr_vertex_n, to_vertex, to_vertex_n, edge_cost = edge.fr_vertex.n, edge.to_vertex, edge.to_vertex.n, edge.cost

        if fr_vertex.n == fr_vertex_n:
            go_to_vertex = False

            if to_vertex.trap:
                if visited_count[to_vertex_n] < 2:
                    go_to_vertex = True
                elif ((total_cost + edge_cost) <= to_vertex.path_cost):
                    go_to_vertex = True
            elif (total_cost + edge_cost) <= to_vertex.path_cost:
                go_to_vertex = True

            if go_to_vertex:
                total_cost += edge_cost
                to_vertex.path_cost = total_cost

                if (to_vertex.trap):
                    visited_count[to_vertex_n] += 1

                dfs(to_vertex_n, total_cost)

                if (to_vertex.trap):
                    visited_count[to_vertex_n] -= 1

                total_cost -= edge_cost

    fr_vertex.restore_trap()

def solution(n, begin, end, roads, traps):
    global BEGIN, END, vertexes, visited_count, min_path_cost
    BEGIN = begin
    END = end
    vertexes = [Vertex(nn, False) for nn in range(n + 1)]
    vertexes[0] = None
    visited_count = [0 for nn in range(n + 1)]

    for nn in traps:
        vertexes[nn].trap = True

    for road in roads:
        edge_fr_n, edge_to_n, edge_cost = road
        Edge(vertexes[edge_fr_n], vertexes[edge_to_n], edge_cost)

    vertexes[BEGIN].path_cost = 0
    dfs(BEGIN, 0)

    return min_path_cost