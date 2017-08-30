class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def partitionList(head, x):
    dummy = ListNode(0)
    pivot = ListNode(x)
    first = dummy
    second = pivot
    cur = head
    while cur != None:
        curnext = cur.next
        if cur.val < x:
            first.next = cur
            first = cur
        else:
            second.next = cur
            second = cur
            second.next = None
    cur = curnext
    first.next = pivot.next
    return dummy.next

if __name__=="__main__":
    print partitionList(head, 2).val
