using BenchmarkDotNet.Attributes;
using BenchmarkDotNet.Running;

ListOfSorting.arr = ListOfSorting.GenArray(ListOfSorting.ReadData(),ListOfSorting.ReadData(),ListOfSorting.ReadData());
BenchmarkRunner.Run<ListOfSorting>();


[MemoryDiagnoser]
[RankColumn]
public class ListOfSorting
{
    public static int[] arr;

    public static int [] GetArr(){
        int[] res = new int[arr.Length];
        Array.Copy(arr,res, arr.Length);
        return res;
    }
    
    public static int[] GenArray(int size, int low = -2147483648, int high = 2147483647)
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

    public static int ReadData(){
        return int.Parse(Console.ReadLine()??"0");
    }

[Benchmark]
public void BubbleSort()
{

    var array = GetArr();

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


[Benchmark]
public void quickSortTest()
{
    var array = GetArr();
    quickSort(array, 0 , array.Length-1);

}



int[] quickSort(int[] array, int leftIndex, int rightIndex)
{
    int i = leftIndex, j = rightIndex, pivot = array[leftIndex];
    while (i <= j) 
    {
        while (array[i] < pivot) 
        {
            i++; 
        }
        while (array[j] > pivot) 
        {
            j--; 
        }
        if (i <= j) 
        {
            int temp = array[i]; 
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






[Benchmark]
public void InsertionSort()
{
    var array = GetArr();
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

[Benchmark]
public void CountingSort()
{
    var array = GetArr();

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

}