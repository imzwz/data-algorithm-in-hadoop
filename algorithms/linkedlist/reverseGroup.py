class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def reverseGroup(head, k):
    if head is None or k <= 1:
        return head
    dummy = ListNode(0)
    dummy.next = head
    pre = dummy
    cross = head
    count = 0
    while cross != None:
        count += 1
        if count % k == 0:
            pre = reverseList(pre, cross.next)
            cross = pre.next
        else:
            cross = cross.next
    return dummy.next

def reverseList(pre, pnext):
    last = pre.next
    cur = last.next
    while cur != pnext:
        last.next = cur.next
        cur.next = pre.next
        pre.next = cur
        cur = last.next
    return last

if __name__ == "__main__":
    head = ListNode(9)
    head.next = ListNode(7)
    head.next.next = ListNode(8)
    head.next.next.next = ListNode(6)
    print(reverseGroup(head, 2).val)

