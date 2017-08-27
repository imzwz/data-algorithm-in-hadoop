class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
    def append(self, y):
        node = y
	if self is None:
            self = node
        else:  
            current = self  
            while current.next is not None:  
                current = current.next  
            current.next = node
        

def getCircleLength(head):
    slow = head
    if slow is None or slow.next is None:
        return 0
    fast = slow.next.next
    while fast != None and fast.next != None:
        if slow == fast:
            return getlength(slow)
        slow = slow.next
        fast = fast.next.next
    return 0

def getlength(head):
    length = 1
    cur = head
    while cur.next != head:
        length += 1
        cur = cur.next
    return length

seq = [3, 8, 7, 1, 2, 3, 4, 5, 1, 2]
head = ListNode(3)
head.append(ListNode(8))
head.append(ListNode(7))
y = ListNode(1)
head.append(y)
head.append(ListNode(2))
head.append(ListNode(3))
head.append(ListNode(4))
head.append(ListNode(5))
head.append(y)
print getCircleLength(head)
#head.next.next = ListNode(7)
#head.next.next.next = ListNode(1)
