def printBrace(l, r, strv, count):
    global strs, counts
    strs = strv
    counts = count
    if l < 0 or r < 1:
        return
    if l == 0 and r == 0:
        print strs
    else:
        if l > 0:
            strs[count] = '('
            printBrace(l - 1, r, strs, count + 1)
        if r > 1:
            strs[count] = ')'
            printBrace(l, r - 1, strs, count + 1)

if __name__=="__main__":
    n = 3
    strs = ['0']*(6)
    printBrace(3, 3, strs, 0) 
