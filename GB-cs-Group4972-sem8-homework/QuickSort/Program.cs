void inputArray(int[] array)
{
    for (int i = 0; i < array.Length; i++)
        array[i] = new Random().Next(-20, 21); // [-20; 20]
}


int[] quickSort(int[] array, int leftIndex, int rightIndex)
{
    Console.WriteLine($"[{string.Join(", ", array)}] ({leftIndex}, {rightIndex})");
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


Console.Clear();
// Console.Write("Введите кол-во элементов массива: ");
// int n = int.Parse(Console.ReadLine()!);
// int[] array = new int[n];
int[] array = {-8, 19, 6, 12, 15};
// inputArray(array);
Console.WriteLine($"Начальный массив: [{string.Join(", ", array)}]");
Console.WriteLine($"Конечный массив: [{string.Join(", ", quickSort(array, 0, array.Length - 1))}]");