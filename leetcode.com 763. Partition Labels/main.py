from typing import List

class Solution:
    def partitionLabels(self, s: str) -> List[int]:
        set_part = set()
        partition_length = 0
        answer = []

        for i in range(len(s)):
            c = s[i]

            if len(set_part) > 0:
                set_right = set(s[i:])
                intersection = set_part.intersection(set_right)

                if len(intersection) == 0:
                    set_part.clear()
                    answer.append(partition_length)
                    partition_length = 0

            if not c in set_part:
                set_part.add(c)
                partition_length += 1
            else:
                partition_length += 1

        if partition_length != 0:
            answer.append(partition_length)

        return answer