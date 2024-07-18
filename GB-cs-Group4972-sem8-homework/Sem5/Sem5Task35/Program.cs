using System.Collections.Generic;


int[] GenArray(int seed)
{
    int[] array = new int[123];

    for(int i = 0; i< 123; i++)
    {
        array[i] = new Random(seed).Next(99,1000);
    }
    return array;
}


int Count(int[] arr){
    int count = 0;
    foreach(int i in arr){
        if(i > 9 && i < 100) count++;
    }
    return count;
}






int seed = 0;
int a = Count(GenArray(seed));
int count_iterations = 0;
while(a == 0){
    count_iterations++;
    a = Count(GenArray((++seed*seed)));
    if (count_iterations%100 == 0) Console.WriteLine(count_iterations);
}
Console.WriteLine($"result: {a}");
Console.WriteLine(count_iterations);
