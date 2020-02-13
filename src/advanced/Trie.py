"""Trie."""


class TrieNode(object):
    u"""TrieNode."""

    def __init__(self):
        """Initialize TrieNode."""
        super(TrieNode, self).__init__()
        self.children = {}
        self.is_word = False
        self.words = []


class Trie(object):
    u"""Trie."""

    def __init__(self):
        u"""Initialize Trie."""
        super(Trie, self).__init__()
        self.root = TrieNode()

    def insert(self, word):
        u"""Insert new word."""
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]
            node.words.append(word)

        node.is_word = True

    def find(self, word):
        u"""Find word in Trie."""
        node = self.root
        for c in word:
            node = node.children.get(c)
            if node is None:
                return None

        return node

    def search_word(self, word):
        u"""Find exact word."""
        node = self.find(word)
        return node is not None and node.is_word

    def search_prefix(self, prefix):
        u"""Find word starting with given prefix."""
        node = self.find(prefix)
        # if node is not None and len(node.words) > 0:
        #     # there is at least one word starting with the given prefix
        #     return node.words
        return [] if node is None else node.words

# trie = Trie()
# words = ["cattle", "catatonic", "battle", "seattle"]
# for word in words:
#     trie.insert(word)
# result = trie.search_prefix("catt")
# print(result)
# result = trie.search_word("cattle")
# print(result)
