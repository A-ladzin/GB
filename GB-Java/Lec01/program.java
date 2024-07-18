

public class program{
    public static void main(String[] args)
    {
        System.out.println("Hello");
        float a = 123.4f;
        double b = 123.4D;
        
        System.out.println(getType(a+b));
        String c = "qwerty";
        boolean d = c.length() <= 5 && c.charAt(1) == 'q';
        
        System.out.println(d);
        System.out.println(Character.isDigit('1'));
        int i = 123;
        System.out.println(i-- - --i);

        int[] arr = new int[10];
        arr[3] = 13;
        System.out.println((arr[3]));

        int [] arr2 = {1,2,3,4,5};
        System.out.println(arr2[2]);

        int[] arr2d[] = new int[3][5];

        for(int[] line : arr2d)
        {
            for (int j : line) System.out.print(j);
        } 
    }
    static String getType(Object o){
        return o.getClass().getSimpleName();
    }

}