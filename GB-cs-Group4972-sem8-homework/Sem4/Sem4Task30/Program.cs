
int ReadData()
{
    Console.WriteLine("Enter a size of the array");
    return int.Parse(Console.ReadLine() ?? "0");
}






Task30 task = new Task30(ReadData());
task.printArray();







class Task30
{
    private int[] array;
    private int size;
    public Task30(int len)
    {

        size = len;
        array = new int[len];
        Random n = new Random(0);
        for (int i = 0; i < len; i++)
        {

            array[i] = n.Next() % 2;
        }
    }

    public void printArray()
    {
        Console.Write("[");
        for (int i = 0; i < size - 1; i++)
        {
            Console.Write(array[i] + ", ");
        }
        Console.Write(array[size - 1] + "]");
    }


}





