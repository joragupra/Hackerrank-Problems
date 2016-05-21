import math

def combinations(n, k):
    return math.factorial(n) * 1.0 / (math.factorial(k) * math.factorial(n - k))

def binomial(x, n, p):
    return combinations(n, x) * math.pow(p, x) * math.pow((1 - p), (n - x))

P = 4.0/5.0
N = 4

print('%.3f'%(binomial(3, N, P) + binomial(4, N, P)))
print('%.3f'%(binomial(0, N, P) + binomial(1, N, P)))
