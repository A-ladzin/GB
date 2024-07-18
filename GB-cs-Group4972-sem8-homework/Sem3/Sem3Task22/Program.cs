Console.WriteLine("Enter a number");
Task22 task = new Task22(int.Parse(Console.ReadLine()??"0"));
task.printSolution();

class Task22
{
    private int n;
    public Task22(int n)
    {
        this.n = n; 
    }


    public void printSolution()
    {
    int[] array = new int[n];
    array = Solution(this.n);
        for(int i = 0; i < n; i++){
            Console.Write(array[i]+", ");
        }
    }
    private int[] Solution(int n)
    {
            int[] array = new int[n];
            for(int i = 1;i <= n; i++)
            {
                array[i-1] = i*i;
            }

            return array;
    }


}


