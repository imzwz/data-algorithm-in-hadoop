class TreeNode:
    def __init__(self, x):
        self.val = x
        self.lchild = None
        self.rchild = None

def treeToList(p, prev, head):
    if p is None:
        return
    treeToList(p.lchild, prev, head)
    p.lchild = prev
    if prev is None:
        head = p
    else:
        prev.rchild = p
    pright = p.rchild
    head.lchild = p
    p.rchild = head
    prev = p
    treeToList(pright, prev, head)

if __name__ == "__main__":
    root = TreeNode(5)
    root.lchild = TreeNode(3)
    root.rchild = TreeNode(7)
    prev = None
    head = None
    treeToList(root, prev, head)
    print head.val

