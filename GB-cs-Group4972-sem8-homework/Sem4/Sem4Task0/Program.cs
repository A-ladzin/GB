using System.Numerics;

var start = DateTime.Now;
var end = DateTime.Now;

BigInteger factN(BigInteger n){
    if (n < 2){
        return 1; 
    }
    else {
        return n*factN(n-1);
    }
}

Console.WriteLine("Enter a number");
BigInteger n = BigInteger.Parse(Console.ReadLine()??"0");






start = DateTime.Now;
Console.WriteLine(factN(n));
end = DateTime.Now;
Console.WriteLine($"Recursion time: {end-start}");

BigInteger Fact(BigInteger n){
    BigInteger fact = 1;
    while (n>1){
        fact*=n--;
    }
    return fact;
}
start = DateTime.Now;
Console.WriteLine(Fact(n));
end = DateTime.Now;
Console.WriteLine($"While-loop time: {end-start}");



