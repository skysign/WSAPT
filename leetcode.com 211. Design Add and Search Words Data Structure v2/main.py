from typing import Dict


class Node(Dict):
    def __init__(self):
        self.ends = False


class WordDictionary:
    def __init__(self):
        self.node = Node()

    def addWord(self, word: str) -> None:
        node = self.node

        for c in word:
            if c not in node.keys():
                node[c] = Node()

            node = node[c]

        node.ends = True

    def search(self, word: str) -> bool:
        return self.mysearch(word, self.node)

    def mysearch(self, word: str, node) -> bool:
        for idx in range(len(word)):
            c = word[idx]

            if c == '.':
                rtn = False
                for my_c in node.keys():
                    rtn |= self.mysearch(word[idx + 1:], node[my_c])

                return rtn
            else:
                if c in node.keys():
                    node = node[c]
                else:
                    return False

        return node.ends

# Your WordDictionary object will be instantiated and called as such:
# obj = WordDictionary()
# obj.addWord(word)
# param_2 = obj.search(word)
