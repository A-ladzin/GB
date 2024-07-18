

Dot d = new Dot(-2,-1);
Console.WriteLine(d.GetQuarter());
Dot d2 = new Dot();
Console.WriteLine(d2.GetQuarter());

class Dot
{

    public Dot(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    WH

    public Dot(){
        Console.Write("Enter x-coordinate: ");
        this.x = int.Parse(Console.ReadLine()??"0");
        Console.WriteLine();
        Console.Write("Enter y-coordinate: ");
        this.y = int.Parse(Console.ReadLine()??"0");
    }

    public void SetX(int x){
        this.x = x;
    }

    public void SetY(int y)
    {
        this.y=y;
    }



    private int x;
    private int y;



    public int GetQuarter(){
        if (x*y==0){
            return 0;
        }
        if (x>0){
            return x*y>0?1:6;
        }
        return x*y<0?2:3;
    

    }


}


