"""Trie."""


class TrieNode(object):
    """TrieNode."""

    def __init__(self):
        """Initialize TrieNode."""
        super(TrieNode, self).__init__()
        self.children = {}
        self.is_word = False


class Trie(object):
    """Trie."""

    def __init__(self):
        """Initialize Trie."""
        super(Trie, self).__init__()
        self.root = TrieNode()

    def insert(self, word):
        """Insert new word."""
        node = self.root
        for c in word:
            if c not in node.children:
                node.children[c] = TrieNode()
            node = node.children[c]

        node.is_word = True

    def find(self, word):
        """Find word in Trie."""
        node = self.root
        for c in word:
            node = node.children.get(c)
            if node is None:
                return None

        return node
