def multiply(num1, num2):
    len1 = len(num1)
    len2 = len(num2)
    sums = [0]*(len1 + len2)
    for i in range(len1)[::-1]:
        for j in range(len2)[::-1]:
            sums[i+j+1] += (int(num1[i])) * (int(num2[j]))
    carry = 0
    for i in range(len(sums))[::-1]:
        sums[i] += carry
        carry = sums[i] / 10
        sums[i] = sums[i] % 10
    firstNonzero = False
    result = []
    for i in range(len(sums)):
        if firstNonzero is False and sums[i] == 0:
            continue
        else:
            result.append(str(sums[i]))
            firstNonzero = True
    if len(result) == 0:
        return "0"
    return result
if __name__=="__main__":
    num1 = "123"
    num2 = "456"
    print ''.join(multiply(num1, num2))
