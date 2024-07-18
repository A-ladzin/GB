
int height = 0;
int pos = 8;
int posi = 8;
int lower = 0;


char[,] field = new char[20, 20];
for (int i = 0; i < field.GetLength(0); i++)
{
    for (int j = 0; j < field.GetLength(1); j++)
    {
        field[i, j] = ' ';
    }
}
for (int i = 0; i < field.GetLength(1); i++)
{
    field[0, i] = '-';
    field[field.GetLength(0) - 1, i] = '-';
}
for (int i = 0; i < field.GetLength(0); i++)
{
    field[i, 0] = '|';
    field[i, field.GetLength(1) - 1] = '|';
}


char[,] fig = new char[4, 4];
int n = 0;
int m = 0;
for (int i = 0; i < fig.GetLength(0); i++)
{
    for (int j = 0; j < fig.GetLength(1); j++)
    {
        fig[i, j] = ' ';
    }
}

for (int i = 0; i < 4; i++)
{
    fig[n, m] = '*';
    int next = new Random().Next(2);
    if (next == 1) n++;
    else m++;
}


void move()
{
    while (height < 16)
    {
        string s = Console.ReadLine();
        if (s == "a")
        {
            posi--;
        }
        else if (s == "d")
        {
            posi++;
        }
        else
        {
            Rotate();
        }
    }

    void Rotate()
    {

        for (int i = 0; i < 2; i++)
        {
            for (int j = i; j < 4 - i - 1; j++)
            {
                char temp = fig[i, j];
                fig[i, j] = fig[j, 3 - i];
                fig[j, 3 - i] = fig[3 - i, 3 - j];
                fig[3 - i, 3 - j] = fig[3 - j, i];
                fig[3 - j, i] = temp;
            }
        }
    }

}


while (true)
{
    pos = 8;
    posi = pos;
    height = 0;
    lower = 0;
    play();

    void play() 
    {
        pos = posi;
    Thread t = new Thread(move);
    t.Start();
        if (lower > 16) return;
        Console.WriteLine(lower);
        height++;
        int tempo = 0;
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if (fig[i, j] == '*') tempo = i + 1;
            }
        }

        lower = tempo + height;


        for (int i = 0; i < fig.GetLength(0); i++)
        {
            for (int j = 0; j < fig.GetLength(1); j++)
            {
                if ( fig[i, j] == '*') {

        
    
                if (field[height + i+1, pos + j] == 'F')
                {
                    for (int k = 0; k < 4; k++)
                    {
                        for (int l = 0; l < 4; l++)
                        {
                            field[height + k, pos + l] = fig[k, l] == '*'? 'F' : field[height + k, pos + l];
                        }
                    }
                    if (lower <= 4) {
                        Console.WriteLine("Lose");
                        Console.ReadLine();
                        return;
                    }
                    else return;
                }
                else {
                    field[height + i, pos + j] = field[height + i, pos + j] == 'F'?'F': fig[i, j];
                }
             }
                
            }
        }
        for (int i = 0; i < field.GetLength(0); i++)
        {
            for (int j = 0; j < field.GetLength(1); j++)
            {
                Console.Write(field[i, j]);
            }
            Console.WriteLine();
        }
        for (int i = 0; i < fig.GetLength(0); i++)
        {
            for (int j = 0; j < fig.GetLength(1); j++)
            {
                field[height + i, pos + j] = field[height + i, pos + j] == 'F' ? 'F' : ' ';
            }
        }
        Thread.Sleep(500);
        Console.Clear();
        play();
    }


    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            field[height + i, pos + j] = fig[i, j] == '*' ? 'F' : ' ';
        }
    }
    Console.Clear();
}

// Thread.Sleep(1000);
// Console.Clear();


