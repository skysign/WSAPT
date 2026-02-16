class Solution:
    def isIsomorphic(self, s: str, t: str) -> bool:
        dict_s, dict_t = {}, {}

        for idx in range(len(s)):
            ss, tt = s[idx], t[idx]

            if dict_s.keys().__contains__(ss) and dict_t.keys().__contains__(tt):
                if dict_s[ss] == tt and dict_t[tt] == ss:
                    continue
                else:
                    return False

            if (not dict_s.keys().__contains__(ss)) and (not dict_t.keys().__contains__(tt)):
                dict_s[ss], dict_t[tt] = tt, ss
            else:
                return False

        return True