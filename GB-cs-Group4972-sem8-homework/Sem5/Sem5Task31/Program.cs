
int[] GenArray(int num, int lowBorder,int highBorder)
{
    int[] array = new int[num];

    for(int i = 0; i< num; i++)
    {
        array[i] = new Random().Next(lowBorder,highBorder+1);
    }
    return array;
}

void PrintArray(int[] arr)
{
    foreach(int i in arr){
        Console.Write($"{i}, ");
    }
    
}


(int, int) a = (int.Parse(Console.ReadLine()??"0"), int.Parse(Console.ReadLine()??"0"));

PrintArray(GenArray(10,a.Item1,a.Item2));


