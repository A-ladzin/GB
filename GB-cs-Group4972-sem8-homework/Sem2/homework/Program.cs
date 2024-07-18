using System.Linq.Expressions;
using System.Net.Http;
using System.Text;
using System.Runtime.CompilerServices;
using System.Security.Cryptography.X509Certificates;
using System.Text.Unicode;


HomeWork a = new HomeWork();
a.Task10();
a.Task13starred();
Console.WriteLine(a.Task15starred());
class HomeWork
{
    
    private long parseLongFromUser(int d) //Takes an arbitrary number of digits as an argument, returns the first occurrence of the desired number of digits from console as a single integer.
    {
        unsafe
        {

            Console.Write("Enter a");
            Console.WriteLine(" number.");
            String data = Console.ReadLine()??"0";
            fixed (char* ptr = data)
            {
                int index = 0;
                long num = 0;
                bool sign = false;
                bool count = false;
                while (*(ptr + index) != 0)
                {
                    if(!count && *(ptr + index) == 48) {
                        index++;
                        continue;
                    }
                    if (*(ptr + index) > 47 && *(ptr + index) < 58)
                    {
                        if (count)
                        {
                            num*=10;
                            count = true;
                        }
                        num += *(ptr + index) - 48;
                        d--;
                        if (d == 0) return sign?-num:num;
                        count = true;

                    }
                    else
                    {
                        if (!count){
                        if (*(ptr + index) == 45)
                        {
                            sign = true;
                        }
                        }
                    }
                    index++;
                }
                if(d<0) {return sign?-num:num;}
                throw new ArgumentException("Not Enough Digits.");
                
            }
        }
    }

    public void Task10(){
        Console.WriteLine("Task 10");
        Console.WriteLine(parseLongFromUser(3)/10%10);

    }

    public void Task13starred(){
        Console.WriteLine("Task13");
        long number = parseLongFromUser(-1);
        long count = 0;
        long temp = number;
        //Считает количество разрядов
        while(temp!=0){
            temp/=10;
            count++;
        }
        if (count < 0){
            Console.WriteLine("There is no third digit");
            return;
        }
        //Округляет число до трех высших разрядов
        for(int i = 0; i < count-3; i++){
            number /= 10;
        }

        //Выводит последнюю цифру
        Console.WriteLine(number%10);
        

    }


    private static async Task<string> CallUrl(string fullUrl)
{
	HttpClient client = new HttpClient();
	var response = await client.GetStringAsync(fullUrl);
	return response;
}

    public bool Task15starred(){ // Проверяет является ли определенный день текущей недели выходным днем по производственному календарю, в рамках одного года.
        Console.WriteLine("Task15");
        short n = (short)parseLongFromUser(1);
        Console.OutputEncoding = Encoding.UTF8;

        if (n < 1 || n > 7) {
            Console.WriteLine("Не неделя");
            return true;
        }

  

        DateTime now = DateTime.Now;
        // now= new DateTime(yyyy,mm,dd);


        //Секция со звездочкой
        Dictionary<int,String> weekdays= new Dictionary<int,String>{
                                                                        {1, "Понедельник" }, 
                                                                        {2, "Вторник"}, 
                                                                        {3, "Среда"}, 
                                                                        {4, "Четверг"}, {
                                                                            5, "Пятница"}, 
                                                                            {6, "Суббота"}
                                                                            ,{7, "Воскресеньк"}
                                                                    };

        Console.WriteLine("\n*"+weekdays[n]+"*\n");
        //
     
        int month = now.Month;
        int dow = (int)now.DayOfWeek;
        if(dow == 0) dow = 7;
        int day = now.DayOfYear;
        int last_mon = day-dow+1;
        var url = "https://www.consultant.ru/law/ref/calendar/proizvodstvennye/2023/";

        String response = CallUrl(url).Result;

        //scraping
        int m = response.IndexOf("month");
        response = response.Substring(m+1,response.Length-m-1);

        //Jump to the previous month;
        for(int i = 1; i < month-1; i++){
            m = response.IndexOf("month");
            day_counter += DateTime.DaysInMonth(now.Year,i);
            response = response.Substring(m+1,response.Length-m-1);
        }

        //Skip every empty cell
        removeEmpties(response);

        //Find monday of the current week
        response = nextSlice(response,last_mon);

        
        day_counter = 1;
        //Start from monday
        response = nextSlice(response,n);

        //Check if holiday
        if (response[0] == 'h' || response[0] == 'w') return true;

        return false;

    }

    private int day_counter = 0;

    private String removeEmpties(String response)
    {
        //scraping
    while(response[response.IndexOf("td class")+10] == 'i'){
            int next_cell = response.IndexOf("td class")+10;
            response = response.Substring(next_cell, response.Length-next_cell);
        }
        return response;
    }

    private String nextSlice(String response, int day_of_the_year)
    {


        while(day_counter < day_of_the_year){
        //in case of the end of the month
        response = removeEmpties(response);
        //scraping
        int next_cell = response.IndexOf("td class")+10;
        response = response.Substring(next_cell, response.Length-next_cell);
            day_counter++;   
        }
        return response;

    }
    


}
