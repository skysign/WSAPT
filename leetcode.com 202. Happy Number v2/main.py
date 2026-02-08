class Solution:
    def isHappy(self, n: int) -> bool:
        visited = set()
        v = n

        while not visited.__contains__(v):
            visited.add(v)
            m = v
            vv = 0
            while m:
                m, r = divmod(m, 10)
                vv += (r * r)

            if vv is 1:
                return True

            v = vv

        return False