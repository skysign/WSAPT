from typing import Dict

class Node(Dict):
    def __init__(self):
        self.end: bool = False

class WordDictionary:
    def __init__(self):
        self.root: Node = Node()

    def addWord(self, word: str) -> None:
        crnt = self.root

        for c in word:
            if not c in crnt.keys():
                crnt[c] = Node()

            crnt = crnt[c]

        crnt.end = True

    def mysearch(self, word: str, crnt: Node) -> bool:
        for idx in range(len(word)):
            c = word[idx]

            if c == '.':
                rtn: bool = False

                for key in crnt.keys():
                    rtn |= self.mysearch(word[(idx+1):], crnt[key])

                return rtn
            else:
                if c in crnt.keys():
                    crnt = crnt[c]
                else:
                    return False

        return True & crnt.end

    def search(self, word: str) -> bool:
        return self.mysearch(word, self.root)

# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)