package Lec03.Program01;

/**
 * program
 */
public class program {

    public static void main(String[] args) {


    Object a = 5;
    int b = (int)a;
    GetType(a);
    GetType(b);
    System.out.println(mul("abc", 3));

    Object[] arr = {1,2,3,4,5,6};
    
    String el = "Hello digits";
    Object[] new_arr = push_back(arr, (Object)el);

    for(Object i: new_arr)
    {
        System.out.print(i.toString() + ' ');
    }
        
    
    }

    static void GetType(Object obj)
    {
        System.out.println(obj.getClass().getName());
    }

    static Object mul(Object a, Object b)
    {
        if (a instanceof String && b instanceof Integer){
            return (Object)new String(new char[(Integer)b]).replace("\0", (String)a);
            
        }
        return 0;
    }

    static Object[] push_back(Object[] array, Object el)
    {
        Object[] result = new Object[array.length+1];
        System.arraycopy(array,0,result,0,array.length);
        result[array.length] = el;
        return result;
    }




}