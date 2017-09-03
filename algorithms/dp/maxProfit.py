def maxProfit(seq):
    n = len(seq)
    if n == 0:
        return 0
    currProfit = [ 0 for i in range(n)]
    futureProfit = [0 for i in range(n)]
    low = seq[0]
    maxProfits = 0
    for i in range(1, n):
        low = min(low, seq[i])
        currProfit[i] = max(currProfit[i-1], seq[i] - low)
    high = seq[-1]
    for j in range(n-1)[::-1]:
        high = max(high, seq[j])
        if j < n-1:
            futureProfit[j] = max(futureProfit[j+1], high-seq[j])
        maxProfits = max(maxProfits, currProfit[j] + futureProfit[j])    
    return maxProfits
if __name__=="__main__":
    seq = [1,3,2,1,3,5,7]
    print(maxProfit(seq))

        

