from typing import Dict

class Node(Dict):
    def __init__(self):
        self.end:bool = False

class Trie:
    def __init__(self):
        self.root: Node = {}

    def insert(self, word: str) -> None:
        crnt = self.root

        for c in word:
            if not c in crnt.keys():
                crnt[c] = {}

            crnt = crnt[c]

        crnt.end = True


    def search(self, word: str) -> bool:
        crnt = self.root

        for c in str:
            if c in crnt.keys():
                crnt = crnt[c]
            else:
                return False

        if crnt.end:
            return True

        return False

    def startsWith(self, prefix: str) -> bool:
        crnt = self.root

        for c in str:
            if c in crnt.keys():
                crnt = crnt[c]
            else:
                return False

        return True

# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)