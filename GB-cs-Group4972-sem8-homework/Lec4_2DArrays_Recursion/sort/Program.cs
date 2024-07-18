using System.Numerics;


int[] sortVibor(int[] array)
{
    for (int i = 0; i < array.Length; i++) // Счетчики цикла лучше всего обзывать i, j, k, m, n
    {
        for (int j = i; j < array.Length; j++)
        {
            if (array[j] < array[i]){
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        }
    }
    return array;
}


int[] SortSelection(int[] collection)
  {
    int size = collection.Length;
    for (int i = 0; i < size - 1; i++)
    {
      int pos = i;
      for (int j = i + 1; j < size; j++)
      {
        if (collection[j] < collection[pos]) pos = j;
      }
      int temp = collection[i];
      collection[i] = collection[pos];
      collection[pos] = temp;
    }
    return collection;
  }


var start = DateTime.Now;
var end = DateTime.Now;
int[] a = {5,4,2,8,5,3,-3,-7,3634,6,33,6};
start = DateTime.Now;
int[] b = sortVibor(a);
end = DateTime.Now;
Console.WriteLine(end-start);
for(int i = 0; i < 8; i++)
{
    Console.WriteLine(b[i]);
}
start = DateTime.Now;
int[] c = SortSelection(a);
 end = DateTime.Now;
Console.WriteLine(end-start);
for(int i = 0; i < 8; i++)
{
    Console.WriteLine(c[i]);
}





