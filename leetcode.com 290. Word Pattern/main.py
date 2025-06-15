class Solution:
    def wordPattern(self, pattern: str, s: str) -> bool:
        ss = s.split()

        if len(pattern) != len(ss):
            return False

        c2word = {}
        word2c = {}

        for idx in range(len(pattern)):
            c = pattern[idx]
            word = ss[idx]

            if c not in c2word.keys():
                c2word[c] = word
            elif c2word[c] != word:
                return False

            if word not in word2c.keys():
                word2c[word] = c
            elif word2c[word] != c:
                return False

        return True
