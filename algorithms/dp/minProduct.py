def minProduct(seq1, seq2):
    m = len(seq1)
    n = len(seq2)
    dp = [ [0 for i in range(n)] for j in range(m)]
    for i in range(m):
        for j in range(i, i+n-m+1):
            if j > i:
                if i > 0:
                    dp[i][j] = min(seq1[i] * seq2[j]+ dp[i-1][j-1], dp[i][j-1])
                else:
                    dp[i][j] = min(seq1[i]*seq2[j], dp[i][j-1])
            else:
                if i == 0:
                    dp[i][j] = seq1[i] * seq2[j]
                else:
                    dp[i][j] = seq1[i] * seq2[j] + dp[i-1][j-1]
    return dp[m-1][n-1]

if __name__=="__main__":
    seq1 = [1,-1]
    seq2 = [1,2,3,4]
    print(minProduct(seq1, seq2))
