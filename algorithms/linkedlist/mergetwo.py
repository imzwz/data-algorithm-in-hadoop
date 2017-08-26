class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None
        
def mergetwo(list1, list2):
    dummy = ListNode(0)
    cur = dummy
    while list1 != None and list2 != None:
        if list1.val < list2.val:
            cur.next = list1
            list1 = list1.next
        else:
            cur.next = list2
            list2 = list2.next
        cur = cur.next
    if list1 != None:
        cur.next = list1
    else:
        cur.next = list2
    return dummy.next

list1 = ListNode(1)
list1.next = ListNode(4)
list2 = ListNode(3)
list2.next = ListNode(5)
list3 = mergetwo(list1, list2)
while list3 != None:
    print list3.val
    list3 = list3.next
