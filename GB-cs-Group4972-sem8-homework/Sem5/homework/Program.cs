



HomeTask t;

t = new Task34Starred();
t.Run();
t = new Task36Starred();
t.Run();
t = new Task38Starred();
t.Run();

abstract class HomeTask
{
    protected int[] GenArray(int size, int low = -2147483648, int high = 2147483647)
{
    int[] array = new int[size];
    for(int i = 0 ; i < array.Length; i++){
        array[i] = new Random().Next(low,high);
    }
    return array;
}
    protected void PrintArray<T>(T[] array)
{
    Console.Write("[");
    for(int i = 0; i < array.Length-1; i++)
    {
        Console.Write($"{array[i]}, ");
    }
    Console.Write($"{array[array.Length-1]}]");
    Console.WriteLine();
}

    protected int ReadData(){
        return int.Parse(Console.ReadLine()??"0");
    }

abstract public void Run();


}




class Task34Starred: HomeTask{

public Task34Starred() {
    Console.WriteLine("*Task34*");
}

private void BubbleSort(int[]array)
{

    
    for(int i = 0; i < array.Length; i++)
        for(int j = i+1; j< array.Length; j++)
            {
                if (array[j] < array[i]){
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }

}

private int countEven(int[]array)
{
    int count = 0;
    foreach(int elem in array)
    {
        count -= elem%2-1;
    }
    return count;
}

public override void Run(){
    Console.WriteLine("Enter a size of the array");
    int[] array = GenArray(ReadData(),100,1000);
    PrintArray<int>(array);
    BubbleSort(array);
    PrintArray<int>(array);
    Console.WriteLine(countEven(array));
}



}

class Task36Starred:HomeTask{


    public Task36Starred() {
    Console.WriteLine("*Task36*");
}

    private (int,int)[] quickSort((int,int)[] array, int leftIndex, int rightIndex)
    {
    int i = leftIndex, j = rightIndex, pivot = array[leftIndex].Item1;
    while (i <= j) 
    {
        while (array[i].Item1 < pivot) 
        {
            i++; 
        }
        while (array[j].Item1 > pivot) 
        {
            j--; 
        }
        if (i <= j) 
        {
            (int,int) temp = array[i]; 
            array[i] = array[j];
            array[j] = temp;
            i++; 
            j--; 
        }
    }
    if (leftIndex < j)
        quickSort(array, leftIndex, j);
    
    if (i < rightIndex) 
        quickSort(array, i, rightIndex); 
    
    return array;
}


    
    private (int,int)[] findPairs((int,int)[]array){
        int i = 1;
        int j = 0;
        //reserving capacity
        int size = array.Length/2;
        (int,int)[] temp = new (int,int)[size];
            while(i < array.Length)
            {
                if(array[i].Item1==array[i-1].Item1){
                    temp[j++] = (array[i].Item2, array[i-1].Item2);
                    i+=2;
                }
                
                else i++;
                
            }
            (int,int)[] result = new (int,int)[j];
            for(int k = 0; k < result.Length; k++)
            {
                result[k] = temp[k];
            }
            return result;
        }

    (int,int)[] enumerateArray(int[] array){
        //remembering indexes
        (int,int)[] enumerated = new (int,int)[array.Length];
        for (int i = 0; i < array.Length; i++)
        {
            enumerated[i] = (array[i],i);
        }
        return enumerated;
    }
    override public void Run(){
        Console.WriteLine("Enter a size of the array");
        int[] array = GenArray(ReadData(),1,5);
        
        Console.WriteLine($"Initial array: ");
        PrintArray<int>(array);

        //n*logn
        (int,int)[] enumerated = enumerateArray(array);  
        quickSort(enumerated,0,array.Length-1);

        var result = findPairs(enumerated);
        if (result.Length > 0){
         Console.WriteLine($"Pairs: ");
        PrintArray<(int,int)>(result);
        }
        
        
        else Console.WriteLine("There is no pairs");



    }
        
    }



class Task38Starred:HomeTask
{

public Task38Starred() {
    Console.WriteLine("*Task38*");
}



void InsertionSort(int[] array)
{
    for(int i = 1; i < array.Length; i++)
    {
        for( int j = i; 0 < j; j--)
        {
            if (array[j] < array[j-1]){
                int temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
            }

        }
    }

}
void CountingSort(int[] array)
{
    int min = 0;
    int max = 0;
    foreach (int i in array){
        if(i < min) min = i;
        else if(i > max) max = i;
    }

    int[] Nmap = new int[-min+1];
    int[] posMap = new int[max+1];

    foreach(int i in array)
    {
        if(i < 0 ){
            Nmap[-i]++;
        }
        else{
            posMap[i]++;
        }
        
    }
    int nextIndex = 0;
    for(int i = Nmap.Length-1; 0<=i; i--){
        for ( int j = Nmap[i]; j > 0; j--)
        {
            array[nextIndex] = -i;
            nextIndex++;
        }
    }
    for(int i = 0; i < posMap.Length; i++){
        for ( int j = posMap[i]; j > 0; j--)
        {
            array[nextIndex] = i;
            nextIndex++;
        }
    }

}

public override void Run(){
    Console.Write("Enter a size of arrays: ");
    int size = ReadData();
    int[] array = GenArray(size,-100,100);
    Console.Write($"Initial array: ");
    PrintArray<int>(array);
    Console.Write("\n\n COUNTING SORT... : ");
    CountingSort(array);
    PrintArray<int>(array);
    Console.WriteLine($"Difference: {array[array.Length-1]-array[0]}\n\n");
    array = GenArray(size,-100,100);
    Console.Write($"Initial array: ");
    PrintArray<int>(array);
    Console.Write("\n\n INSERTION SORT... : ");
    InsertionSort(array);
    PrintArray<int>(array);
    Console.WriteLine($"Difference: {array[array.Length-1]-array[0]}\n\n");

}

}