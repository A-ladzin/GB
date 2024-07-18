HomeTask t;
t = new Task54();
t.run();
t = new Task56();
t.run();
t = new Task58();
t.run();
t = new Task60();
t.run();
t = new Task62();
t.run();




public static class MatrixExtensions
{
    public static T[] GetRow<T>(this T[,] matrix, int row)
    {
        var rowLength = matrix.GetLength(1);
        var rowVector = new T[rowLength];

        for (var i = 0; i < rowLength; i++)
            rowVector[i] = matrix[row, i];

        return rowVector;
    }

    public static T[] GetCol<T>(this T[,] matrix, int col)
    {
        var colLength = matrix.GetLength(0);
        var colVector = new T[colLength];

        for (var i = 0; i < colLength; i++)
            colVector[i] = matrix[i, col];

        return colVector;
    }
}


abstract class HomeTask
{


    protected static int[,,] Gen3DArray(int x, int y, int z, int low = -2147483648, int high = 2147483647, bool unique = false )
    {
        if (unique && x*y*z > high - low){
            Console.WriteLine("Empty spaces");
            return new int[0,0,0];
        }
        int[,,]array = new int[x,y,z];
        int[] memo = new int[x*y*z];
        int next_index = 0;
        Random rand = new Random();
        for(int i = 0; i < x; i ++)
        {
            for (int j = 0; j < y; j++)
            {
                for(int k = 0; k < z ; k++)
                {
                    array[i,j,k] = rand.Next(low,high);
                    if(unique)
                    {
                        bool check = true;
                        while(check)
                        {
                            bool found = false;
                            foreach(int e in memo)
                            {
                                if (e == array[i,j,k]){
                                    found = true;
                                    break;
                                } 
                            }
                            if(found){
                                array[i,j,k] =rand.Next(low,high);
                            }
                            else{
                                memo[next_index] = array[i,j,k];
                                next_index++;
                                check = false;
                            } 
                        }
                    }
                }
            }
        }
        return array;
    }
    protected static int[,] Gen2DArray(int axis0, int axis1, int low = -2147483648, int high = 2147483647)
    {
        int[,] array = new int[axis0, axis1];
        for (int i = 0; i < axis0; i++)
        {
            for (int j = 0; j < axis1; j++)
            {
                array[i, j] = new Random().Next(low, high);
            }
        }
        return array;
    }

    protected static int ReadData()
    {
        return int.Parse(Console.ReadLine() ?? "0");
    }

    public static void Print2DArray<T>(T[,] array)
    {
        Console.ForegroundColor = ConsoleColor.DarkGreen;
        Console.BackgroundColor = ConsoleColor.Black;
        Console.Write("[");
        for (int i = 0; i < array.GetLength(0) - 1; i++)
        {
            Console.Write("[");
            for (int j = 0; j < array.GetLength(1) - 1; j++)
            {
                {
                    Console.Write($"{array[i, j]}, ");
                }
            }
            Console.Write($"{array[i, array.GetLength(1) - 1]}],");
            Console.WriteLine();
        }
        Console.Write("[");
        for (int j = 0; j < array.GetLength(1) - 1; j++)

        {
            {
                Console.Write($"{array[array.GetLength(0) - 1, j]}, ");

            }
        }
        Console.Write($"{array[array.GetLength(0) - 1, array.GetLength(1) - 1]}]");
        Console.WriteLine("]");
    }


    abstract public void run();
}
class Task62: HomeTask
{
    public Task62(){
        Console.WriteLine("\tTask62\n");
    }
    private int[,] SpiralBuild(int n = 4, int m = 4)
    {
        int top = 1;
        int bot = n-1;
        int count = 1;
        int right = m-1;
        int left = 0;
        (int,int) curr = (0,0);
        int[,] array = new int[n,m];

        while(count <= m*n)
        {
            while(curr.Item2 < right)
            {
                array[curr.Item1,curr.Item2] = count;
                curr.Item2++;
                count++;
                if(count > m*n) return array;
            }
            array[curr.Item1,curr.Item2] = count;
            curr.Item1++;
            count++;
            if(count > m*n) return array;
            right--;
            while(curr.Item1<bot)
            {
                array[curr.Item1,curr.Item2] = count;
                curr.Item1++;
                count++;
                if(count > m*n) return array;
            }
            array[curr.Item1,curr.Item2] = count;
            curr.Item2--;
            count++;
            if(count > m*n) return array;
            bot--;
            while(curr.Item2 > left)
            {
                array[curr.Item1,curr.Item2] = count;
                curr.Item2--;
                count++;
                if(count > m*n) return array;
            }
            array[curr.Item1,curr.Item2] = count;
            count++;
            if(count > m*n) return array;
            left++;
            curr.Item1--;
            while(curr.Item1 > top)
            {
                array[curr.Item1,curr.Item2] = count;
                curr.Item1--;
                count++;
                if(count > m*n) return array;
            }
            array[curr.Item1,curr.Item2] = count;
            count++;
            if(count > m*n) return array;
            top++;
            curr.Item2++;
        }
        return array;
    }
    public override void run()
    {
        Print2DArray<int>(SpiralBuild());
    }
}
class Task60: HomeTask
{
    public Task60(){
        Console.WriteLine("\tTask60\n");
    }

    public override void run()
    {
        Console.Write("Enter x-dim size: ");
        int x = ReadData();
        Console.WriteLine();
                Console.Write("Enter y-dim size: ");
        int y = ReadData();
        Console.WriteLine();
                Console.Write("Enter z-dim size: ");
        int z = ReadData();
        Console.WriteLine();
        int [,,] array = Gen3DArray(x,y,z, 10,100, unique : true);

        for(int i = 0; i < array.GetLength(0); i ++)
        {
            for (int j = 0; j < array.GetLength(1); j++)
            {
                for(int k = 0; k < array.GetLength(2) ; k++)
                {
                    Console.WriteLine($"{array[i,j,k]}({i},{j},{k})");
                }
            }
        }
    }
}
class Task58 : HomeTask
{

    public Task58(){
        Console.WriteLine("\tTask58\n");
    }
    public override void run()
    {

        Console.WriteLine("\t**1st Matrix**");
        Console.Write("Enter n rows: ");
        int n = ReadData();
        Console.Write("Enter n columns: ");
        int m = ReadData();
        int[,] mat1 = Gen2DArray(n, m, -10, 10);
        Console.WriteLine("\t**2nd Matrix**");
        Console.Write("Enter n rows: ");
        n = ReadData();
        if (n != m)
        {
            Console.WriteLine("matsntmatch");
            return;
        }
        Console.Write("Enter n columns: ");
        m = ReadData();
        int[,] mat2 = Gen2DArray(n, m, -10, 10);
        int[,] rmat = new int[mat1.GetLength(0),m];
        Console.WriteLine("Matrix 1:");
        Print2DArray<int>(mat1);
        Console.WriteLine("\n");
        Console.WriteLine("Matrix 2:");
        Print2DArray<int>(mat2);
        Console.WriteLine("\n");
        for (int i = 0; i < rmat.GetLength(0); i++)
        {
            for (int j = 0; j < rmat.GetLength(1); j++)
                rmat[i, j] = mat1.GetRow(i).Zip(mat2.GetCol(j), (a, b) => a * b).Aggregate(0, (a, b) => a + b);
        }
        Console.WriteLine("Result Matrix:");
        Print2DArray<int>(rmat);

    }
}
class Task56 : HomeTask
{
        public Task56(){
        Console.WriteLine("\tTask56\n");
    }

    public override void run()
    {
        Console.Write("Enter size: ");
        int size = ReadData();
        int[,] array = Gen2DArray(size, size, 0, 2147483647 / (size * size));
        int sum = -1;
        int temp_sum = array[0, 0];
        int result = 0;
        Print2DArray<int>(array);
        Console.WriteLine("\n");
        for (int i = 1; i < array.Length; i++)
        {
            if (i % size == 0)
            {
                if (sum > temp_sum || sum == -1)
                {

                    sum = temp_sum;
                    result = i / size;
                }
                temp_sum = array[i / size, i % size];
                continue;
            }
            temp_sum += array[i / size, i % size];
        }
        if (sum > temp_sum || sum == -1)
        {

            sum = temp_sum;
            result = size;
        }
        Console.WriteLine($"Sum: {sum}\tRestul: {result}");
    }
}

class Task54 : HomeTask
{

    public Task54(){
        Console.WriteLine("\tTask54\n");
    }


    public override void run()
    {
        Console.Write("Enter n rows: ");
        int n = ReadData();
        Console.Write("Enter n columns: ");
        int m = ReadData();
        int[,] array = Gen2DArray(n, m);
        Print2DArray<int>(array);
        for (int row = 0; row < array.GetLength(0); row++)
        {


            for (int i = 0; i < array.GetLength(1); i++)
            {

                for (int j = i + 1; j < array.GetLength(1); j++)
                {
                    if (array[row, j] > array[row, i])
                    {
                        int temp = array[row, i];
                        array[row, i] = array[row, j];
                        array[row, j] = temp;
                    }
                }

            }
        }
        Console.WriteLine();
        Print2DArray<int>(array);


    }
}