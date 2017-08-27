class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def reverseBetween(head, m, n):
    dummy = ListNode(0)
    dummy.next = head
    pre = dummy
    cur = head
    for i in range(1, n+1):
        if i == m:
            mNode = cur
        if i < m:
            pre = pre.next
        nextNode = cur.next
        if i > m and i <= n:
            mNode.next = nextNode
            cur.next = pre.next
            pre.next = cur
        cur = nextNode
    return dummy.next

            
head = ListNode(3)
head.next = ListNode(5)
head.next.next = ListNode(7)
head.next.next.next = ListNode(9)
print reverseBetween(head, 1, 4).val

