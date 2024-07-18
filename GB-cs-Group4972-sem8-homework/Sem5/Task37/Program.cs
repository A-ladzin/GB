
int[] pairedMultiplication(int[] arr ){
int [] result = new int[arr.Length/2];
for(int i = 0 ; i < arr.Length/2; i++){
    result[i] = arr[i]*arr[arr.Length-1-i];
}
return result;
}



int[] arr = new int[7]{1,5,7,4,3,2,7};
int[] result = new int[arr.Length/2];
result = pairedMultiplication(arr);

foreach (int i in result)
{
    Console.Write($"{i} ");
}