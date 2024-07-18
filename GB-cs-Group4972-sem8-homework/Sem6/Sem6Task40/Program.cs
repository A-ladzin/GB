double ReadData(){
    return double.Parse(Console.ReadLine()??"0");
}


bool isTrianglable(double a, double b, double c)
{
    return !(a+b <= c || b+c <= a || a+c <= b);
}


Console.WriteLine(isTrianglable(ReadData(),ReadData(),ReadData()));
