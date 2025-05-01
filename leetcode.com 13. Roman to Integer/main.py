class Solution:
    def romanToInt(self, s: str) -> int:
        ns = {}
        ns['Z'] = 0
        ns['I'] = 1
        ns['V'] = 5
        ns['X'] = 10
        ns['L'] = 50
        ns['C'] = 100
        ns['D'] = 500
        ns['M'] = 1000

        dt = list(s)
        dt.reverse()
        v = 0

        while dt:
            d1 = dt.pop(0)
            v += ns[d1]
            minus = 0

            while dt and ns[dt[0]] < ns[d1]:
                minus -= ns[dt[0]]
                dt.pop(0)

            v += minus

        return v
