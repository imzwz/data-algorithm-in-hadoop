class TreeNode:
    def __init__(self, x):
        self.val = x
        self.parent = None
        self.lchild = None
        self.rchild = None

def findpq(root, p, q, pq):
    if root is None:
        return
    if root == p:
        pq.lchild = root
    if root == q:
        pq.rchild = root
    if pq.lchild is None or pq.rchild is None:
        findpq(root.lchild, p, q, pq)
        findpq(root.rchild, p, q, pq)

def lca_bt(root, p, q):
    findpq(root, p, q, pq)
    pp = pq.lchild
    qq = pq.rchild
    if pp is None or qq is None:
        return None
    lenp = 0
    lenq = 0
    up1 = pp
    up2 = qq
    while up1 != root:
        up1 = up1.parent
        lenp += 1
    while up2 != root:
        up2 = up2.parent
        lenq += 1

    while lenp > lenq:
        up1 = up1.parent
        lenp -= 1

    while lenp < lenq:
        up2 = up2.parent
        lenq -= 1

    while up1 != up2:
        up1 = up1.parent
        up2 = up2.parent
    return up1

pq = TreeNode(0)

if __name__=="__main__":
    root = TreeNode(5)
    lchild = TreeNode(3)
    rchild = TreeNode(9)
    root.lchild = lchild
    root.rchild = rchild
    lchild.parent = root
    rchild.parent = root
    #findpq(root, lchild, rchild, pq)
    print lca_bt(root, lchild, rchild).val



    

        

