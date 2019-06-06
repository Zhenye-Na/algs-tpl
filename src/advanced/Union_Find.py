"""Union Find."""


class UnionFind(object):
    u"""
    Union Find.

    并查集
    时间复杂度 O(log^* n) 约等于 O(1)

    并查集可以做的事情:
    1. 合并两个集合
    2. 查询某个元素所在集合
    3. 判断两个元素是否在同一个集合
    4. 获得某个集合的元素个数
    5. 统计当前集合个数(联通块的个数)
    """

    def __init__(self, n):
        """Initialize Union Find."""
        super(UnionFind, self).__init__()
        self.father = {}
        for i in range(1, n + 1):
            self.father[i] = i

    def findIterative(self, node):
        u"""
        路径压缩 + 查找.

        什么是路径压缩? 在找到根节点 father 之后, 还需要把经过的所有节点的根节点改成 father
        """
        path = []
        while node != self.father[node]:
            path.append(node)
            node = self.father[node]

        for n in path:
            self.father[n] = node

        return node

    def findRecursive(self, node):
        u"""递归实现 路径压缩."""
        if node == self.father[node]:
            return node

        self.father[node] = self.findRecursive(self.father[node])
        return self.father[node]

    def union(self, a, b):
        u"""合并: 将两个节点的根节点合并即可."""
        self.father[self.findIterative(a)] = self.findIterative(b)
