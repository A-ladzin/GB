package Sem01;

public class task02 {



    public static void main(String[] args) {
        int[]arr = {3,2,5,3};
        int[] sorted = sort(arr,3);
        for(int i : sorted)
        {
            System.out.print(i);
            System.out.print(' ');
        }

        }
    
    public static int[] sort(int[] arr, int n)
    {
        int[] sorted = new int[arr.length];
        int i = 0;
        for(int elem : arr){
            if(elem != n){
                sorted[i] = elem;
                i++;
            }
        }
        while(i < arr.length)
        {
            sorted[i] = n;
            i++;
        }
        return sorted;

    }
        


    }

