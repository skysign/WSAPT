from collections import defaultdict

class Solution:
    def characterReplacement(self, s: str, k: int) -> int:
        counts = defaultdict(int)
        mx = 0

        lt = 0
        for rt in range(len(s)):
            counts[s[rt]] += 1

            window_length = rt - lt + 1
            max_count = max(counts.values())

            if (window_length - max_count) > k:
                counts[s[lt]] -= 1
                lt += 1

            mx = max(mx, rt +1 -lt)

        return mx