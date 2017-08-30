class TreeNode:
    def __init__(self, x):
        self.val = x
        self.lchild = None
        self.rchild = None
    
def lca(root, p, q):
    if root is None or p is None or q is None:
        return None
    if root.val > p.val and root.val > q.val:
        return lca(p.lchild, p, q)
    elif root.val < p.val and root.val < p.val:
        return lca(p.rchild, p, q)
    else:
        return root

if __name__ == "__main__":
    root = TreeNode(4)
    rleft = TreeNode(2)
    rright = TreeNode(5)
    root.lchild = rleft
    root.rchild = rright
    print lca(root, rleft, rright).val
    
    
