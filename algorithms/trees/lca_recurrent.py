class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

def lca_recurrent(root, p, q):
    if root is None or p is None or q is None:
        return null
    left = lca_recurrent(root.left, p, q)
    right = lca_recurrent(root.right, p, q)
    if left != None and right != None:
        return root
    if left is None:
        return right
    else:
        return left

