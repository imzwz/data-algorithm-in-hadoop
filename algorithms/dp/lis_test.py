def lis(seq):
    n = len(seq)
    pre = [i for i in range(n)]
    dp = [1 for i in range(n)]
    maxCount = 0
    end = 0
    result = []
    for i in range(n):
        for j in range(i):
            if seq[i] >= seq[j] and dp[i] < dp[j] + 1:
                pre[i] = j
                dp[i] = dp[j] + 1
                if maxCount < dp[i]:
                    maxCount = dp[i]
                    end = i
    i = maxCount - 1
    while pre[end] != end:
        result.append(seq[end])
        end = pre[end]
    result.append(seq[end])
    return result

seq = [1,3,9,7,9,4,2,6,3,2,8]
print(lis(seq))


