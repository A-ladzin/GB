import java.util.Map;
import java.util.HashMap;

public class UserData {
    import java.util.HashMap;
    import java.util.Map;
    
    public class UserData {
        Map<String, String> userData = new HashMap<>();
    
        void addData(String numPass, String name){
            userData.put(numPass, name);
        }
        String findUserByName(String userName){
            StringBuilder sb = new StringBuilder();
            for (String key: userData.keySet()) {
                if (userName.equals(userData.get(key))){
                    sb.append(key).append(": ").append(userData.get(key));
                    sb.append("\n");
                }
            }
            return sb.toString().trim();
        }
    }




}


// Создать структуру для хранения Номеров паспортов и Фамилий сотрудников организации.
// 123456 Иванов
// 321456 Васильев
// 234561 Петрова
// 234432 Иванов
// 654321 Петрова
// 345678 Иванов
// Вывести данные по сотрудникам с фамилией Иванов.