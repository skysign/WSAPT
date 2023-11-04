import collections


class Solution:
    def minWindow(self, s: str, t: str) -> str:
        answer = ''
        targets = collections.Counter(t)
        tracks = {}

        def found_substring():
            for target_key, target_value in targets.items():
                if target_value > tracks[target_key]:
                    return False

            return True

        # init tracks
        for key in targets.keys():
            tracks[key] = 0

        lt = 0
        # t에 포함문 캐릭터를 s에서 찾을 때, 가장 왼쪽에 있는 캐릭터를 찾기
        for idx in range(len(s)):
            if s[idx] in targets.keys():
                lt = idx
                break


        for rt in range(lt, len(s)):
            if s[rt] in targets.keys():
                tracks[s[rt]] += 1

                while found_substring():
                    substring = s[lt: rt+1]
                    if answer == '' or len(substring) < len(answer):
                        answer = substring

                    tracks[s[lt]] -= 1

                    for j in range(lt +1, rt +1):
                        if s[j] in targets.keys():
                            lt = j
                            break

        return answer
