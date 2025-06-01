from typing import List
from collections import deque


class Solution:
    def minMutation(self, startGene: str, endGene: str, bank: List[str]) -> int:
        queue = deque([[startGene, 0]])
        visited = set()
        bank = set(bank)

        while queue:
            gene, level = queue.popleft()
            if gene == endGene:
                return level

            for i in range(8):
                for g in 'ACGT':
                    new_gene = gene[:i] + g + gene[i + 1:]
                    if new_gene not in visited and new_gene in bank:
                        queue.append([new_gene, level + 1])
                        visited.add(new_gene)

        return -1
