class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

def reverseList(head):
    if head is None or head.next is None:
        return head
    dummy = ListNode(0)
    dummy.next = head
    cur = head.next
    while cur != None:
        temp = cur.next
        cur.next = dummy.next
        dummy.next = cur 
        cur = temp
    return dummy.next
        
head = ListNode(1)
head.next = ListNode(3)
head.next.next = ListNode(5)
print reverseList(head).val
