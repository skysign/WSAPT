from typing import Dict


class Node(Dict):
    def __init__(self):
        self.end: bool = False


class Trie:
    def __init__(self):
        self.root = Node()

    def insert(self, word: str) -> None:
        node = self.root

        for c in word:
            if c not in node.keys():
                node[c] = Node()

            node = node[c]

        node.end = True

    def search(self, word: str) -> bool:
        node = self.root

        for c in word:
            if c in node.keys():
                node = node[c]
            else:
                return False

        return node.end

    def startsWith(self, prefix: str) -> bool:
        node = self.root

        for c in prefix:
            if c in node.keys():
                node = node[c]
            else:
                return False

        return True

# Your Trie object will be instantiated and called as such:
# obj = Trie()
# obj.insert(word)
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)
