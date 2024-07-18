using System.Numerics;

BigInteger[] fibN(int n)
{
    BigInteger[n] fib;

        for (int i = 0; i < 2 && i < n; i++){
            fib[i] = 1;
        }
    for (int i = 2; i < fib; i++)
    {
        fib[i] = fib[i-1] + fib[i-2];
    }
}


int n = 5;
int[] f = fibN(n);
for (int i = 0; i < n; i++)
{
    Console.Write($"{f[i]} ");
}