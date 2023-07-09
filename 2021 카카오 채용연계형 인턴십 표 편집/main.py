def solution(N, K, cmds):
    nodes = [Node(idx-1, idx, idx+1, 'O') for idx in range(N)]
    crnt = nodes[K]
    deleted_nodes = []

    for cmd in cmds:
        if cmd[0] == 'U':
            for _ in range(int(cmd.split(' ')[1])):
                crnt = nodes[crnt.prev]
        elif cmd[0] == 'D':
            for _ in range(int(cmd.split(' ')[1])):
                crnt = nodes[crnt.next]
        elif cmd[0] == 'C':
            crnt.ox = 'X'
            deleted_nodes.append(crnt)

            if crnt.prev >= 0:
                nodes[crnt.prev].next = crnt.next

            if crnt.next < N:
                nodes[crnt.next].prev = crnt.prev
                crnt = nodes[crnt.next]
            else:
                crnt = nodes[crnt.prev]

        elif cmd[0] == 'Z':
            restored_node = deleted_nodes.pop()
            restored_node.ox = 'O'

            if restored_node.prev >= 0:
                nodes[restored_node.prev].next = restored_node.idx
            if restored_node.next < N:
                nodes[restored_node.next].prev = restored_node.idx

    answer = ''
    for node in nodes:
        answer += node.ox

    return answer


class Node:
    def __init__(self, prev_idx, idx, next_idx, ox):
        self.ox = ox
        self.prev = prev_idx
        self.idx = idx
        self.next = next_idx
