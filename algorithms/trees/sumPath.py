class TreeNode:
    def __init__(self, x):
        self.val = x
        self.left = None
        self.right = None

def hasPath(root, sum):
    if root is None:
        return False
    if root.left is None and root.right is None and root.val == sum:
        return True
    else:
        return hasPath(root.left, sum - root.val) or hasPath(root.right, sum - root.val)

    

