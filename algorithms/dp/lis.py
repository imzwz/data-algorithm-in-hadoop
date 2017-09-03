def minDelete(seq):
    res = []
    bt = {}
    dp = [1 for x in range(len(seq))]
    maxCount = 0
    end = 0
    for i in range(len(seq)):
        for j in range(i):
            if seq[i] >= seq[j] and dp[j]+1 > dp[i]:
                dp[i] = dp[j] + 1
                bt[i] = j
                if maxCount < dp[i]:
                    maxCount = dp[i]
                    print bt
                    end = i
    k = len(seq) - 1
    while k >= 0:
        while k > end:
            res.append(seq[k])
            k -= 1
        k -= 1
        if bt.has_key(end):
            end = bt.get(end)
        else:
            end -= 1
    return res

seq = [1,3,9,7,9,4,2,6,3,2,8]
print minDelete(seq)


