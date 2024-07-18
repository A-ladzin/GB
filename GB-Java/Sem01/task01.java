package Sem01;


public class task01 {
    public static void main(String[] args) {
        
        
        int[] arr = {1,0,1,1,1};
        int max_count = 0;
        int count = 0;
        for (int i : arr)
        {
            if(i == 1) count++;
            else 
            {max_count = Math.max(count,max_count);
                count =0;
            }

        }
        max_count = Math.max(count,max_count);
        System.out.println(max_count);

    }
    
}
