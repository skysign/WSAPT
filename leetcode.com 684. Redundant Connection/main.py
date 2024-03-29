from typing import List
from collections import defaultdict

class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        uf = [0 for _ in range(len(edges) +1)]
        my_edges = defaultdict(list)
        [xto, xfr] = edges.pop(0)
        uf[xto] = xto
        uf[xfr] = xto
        my_edges[xto].append(xfr)
        my_edges[xfr].append(xto)

        while len(edges) > 0:
            [xto, xfr] = edges.pop(0)
            my_edges[xto].append(xfr)
            my_edges[xfr].append(xto)

            if uf[xto] != 0 and uf[xfr] != 0:
                if uf[xto] == uf[xfr]:
                    return [xto, xfr]
                else:
                    v = uf[xto]
                    queue: List = [xfr]
                    queue += my_edges[xfr]

                    while queue:
                        idx = queue.pop(0)
                        if uf[idx] == v:
                            continue

                        uf[idx] = v
                        queue += my_edges[idx]
            elif uf[xto] == 0 and uf[xfr] != 0:
                uf[xto] = uf[xfr]
            elif uf[xto] != 0 and uf[xfr] == 0:
                uf[xfr] = uf[xto]
            else:   # uf[xto] == 0 and uf[xfr] == 0:
                uf[xto] = xto
                uf[xfr] = xto

        return []