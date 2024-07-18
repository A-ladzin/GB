import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class hw {

    private static File log;

    public static void main(String[] args) {
        int []a  = {6,3,21,6,4,1,7};
        sort(a);
    }

    public static void sort(int[] mas){
      try (FileWriter fileWriter = new FileWriter("log.txt",true)){
        
        for (int i = mas.length; i > 0; i--)
        {
            for (int j = 1; j < i; j++)
            {
                if (mas[j] < mas[j-1]){
                    int temp = mas[j];
                    mas[j] = mas[j-1];
                    mas[j-1] = temp;
                }
            }
            StringBuilder log = new StringBuilder();

            String now = new SimpleDateFormat("YYYY-MM-dd HH:mm ").format(Calendar.getInstance().getTime());
            log.append(now);
            log.append("[");
            for(int k = 1; k < mas.length-1; k++){
                log.append(mas[k] + ", ");
            }
            log.append(mas[mas.length-1]+"]");
             
            fileWriter.write(log.toString()+"\n");
        }
      }
      catch (IOException e){
        boolean buuged = true;
      }
        
  }

}
