int q = int.Parse(Console.ReadLine()??"0");



void ShowInterval(int q){
    String x;
    String y;
    if (q < 1|| q> 4){ Console.WriteLine("Something went wrong");
    return;}

    if (q == 1|| q == 4){
        x = "[0:inf]";
    }
    else
        x = "[-inf:0]";

    if (q ==1||q ==2){
        y = "[0:inf]";
    }
    else y = "[-inf:0];";

    Console.WriteLine("x: "+ x+ " y: " +y);
}

ShowInterval(q);