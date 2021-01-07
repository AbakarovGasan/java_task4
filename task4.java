import java.util.*;

public class task4{
//1. Бесси работает над сочинением для своего класса писателей. Поскольку ее почерк
//довольно плох, она решает напечатать эссе с помощью текстового процессора.
//Эссе содержит N слов (1≤N≤100), разделенных пробелами. Каждое слово имеет
//длину от 1 до 15 символов включительно и состоит только из прописных или
//строчных букв. Согласно инструкции к заданию, эссе должно быть
//отформатировано очень специфическим образом: каждая строка должна содержать
//не более K (1≤K≤80) символов, не считая пробелов. К счастью, текстовый
//процессор Бесси может справиться с этим требованием, используя следующую
//стратегию:
//– Если Бесси набирает Слово, и это слово может поместиться в текущей строке, поместите
//его в эту строку. В противном случае поместите слово на следующую строку и
//продолжайте добавлять к этой строке. Конечно, последовательные слова в одной строке
//все равно должны быть разделены одним пробелом. В конце любой строки не должно
//быть места.
//– К сожалению, текстовый процессор Бесси только что сломался. Пожалуйста,
//помогите ей правильно оформить свое эссе!
    //функция принимает на вход два числа и строку,
    //на выходе возвращает отформатированный текст
    public static String text_format(int n, int k, String str){
        Iterator container = (Arrays.asList(str.trim().split("\\s+"))).iterator(); //итератор массива со словами
        StringBuilder out = new StringBuilder(""); // cтрока на выходе
        int symbols_left;               // 
        
        if (!container.hasNext()) return "";       //если на входе пустая строка, вернуть пустую строку 
        
        String temp = (String)container.next();         //следующее слова из итератора
        
        int temp_length = temp.length();  // размер слова
        
        while (true)
        {
            symbols_left = k;      // оставшиеся символы в строке
            do{
                out.append(temp);      // добавить слово в строку
                out.append(" ");       // добавить пробел
                
                symbols_left-=temp_length;  // 
                
                temp = (String)container.next();  //
                temp_length = temp.length();   //
            
            } while ((symbols_left >= temp_length)&(container.hasNext()));  // выполнить до тех пор,
            // пока есть слово в итераторе и слово может поместиться в текущей строке
            
            if (!container.hasNext()){ // если не осталось слова в итераторе
                if (symbols_left < temp_length){
                    out.append("\n");  // если слово не может поместиться в текущей строке, то создать новую строку
                }
                out.append(temp); // добавить слово
                break;
            }
            else{
                out.append("\n");
            }
        }
        return out.toString();
    }
    
//2. Напишите функцию, которая группирует строку в кластер скобок. Каждый кластер
//должен быть сбалансирован.     
    public static String [] split(String str){
        ArrayList<String> out = new ArrayList<String>(); // кластер скобок
        int left = 0;
        
        StringBuilder temp = new StringBuilder();
        boolean push = false;
        
        for (char i : str.toCharArray()){         // 
            
            if ((i!='(')&(i!=')')) continue;  // игнорировать все символы, кроме скобок 
            
            if (i=='('){
                if (!push) push = true;  
                left += 1; 
            }
            
            if (push){
                temp.append(i);        
            }
            
            if (i==')'){
                left -= 1;
                if (push & (left == 0)){
                    out.add(temp.toString());
                    temp=new StringBuilder();
                    push = false;
                }
                if (left < 0) left = 0;
            }
        }
        return out.toArray(new String[0]);
    }
    
//3. Создайте две функции toCamelCase () и toSnakeCase (), каждая из которых берет
//одну строку и преобразует ее либо в camelCase, либо в snake_case. 
    public static String toCamelCase(String str){
        StringBuilder out = new StringBuilder();
        boolean push = false;
        
        for (char i : str.toCharArray()){
            if (i=='_') push = true; // cледующая после "_" - заглавная буква
            else{
                if (push){  
                    i = Character.toUpperCase(i);
                    push = false;
                }
                out.append(i);
            }
        }
        return out.toString();
    }    
    
    public static String toSnakeCase(String str){
        StringBuilder out = new StringBuilder();
        for (char i : str.toCharArray()){
            
            if (Character.isUpperCase(i)){      //если идет заглавная буква, то
                i = Character.toLowerCase(i);   //добавить "_" и строчный тип буквы
                out.append('_');                  
            }
            out.append(i);
        }
        return out.toString();
    }
    
//4. Напишите функцию, которая вычисляет сверхурочную работу и оплату, связанную
//с сверхурочной работой.
//Работа с 9 до 5: обычные часы работы
//После 5 вечера это сверхурочная работа
//Ваша функция получает массив с 4 значениями:
//– Начало рабочего дня, в десятичном формате, (24-часовая дневная нотация)
//– Конец рабочего дня. (Тот же формат)
//– Почасовая ставка
//– Множитель сверхурочных работ
//Ваша функция должна возвращать:
//$ + заработанные в тот день (округлены до ближайшей сотой)
    public static String overTime(float [] plan){
        float r;
        if (plan[0]>17) r = (plan[1]-plan[0])*plan[3]; // после 5 только сверхурочная работа
        
        else if (plan[1]<17) r = (plan[1]-plan[0])*plan[2]; // до 5 - обычные часы работы
        
        else r = (17-plan[0])*plan[2]+(plan[1]-17)*plan[3]; // сверхурочные и обычные часы
        
        return "$"+String.format("%.2f", r); // округлить до сотой
    }
    
//5. Индекс массы тела (ИМТ) определяется путем измерения вашего веса в
//килограммах и деления на квадрат вашего роста в метрах. Категории ИМТ таковы:
//Недостаточный вес: <18,5
//Нормальный вес: 18.5-24.9
//Избыточный вес: 25 и более
//Создайте функцию, которая будет принимать вес и рост (в килограммах, фунтах,
//метрах или дюймах) и возвращать ИМТ и связанную с ним категорию. Округлите
//ИМТ до ближайшей десятой. 
    public static final Map<String, Float> map1 = new HashMap<>();
    public static final Map<String, Float> map2 = new HashMap<>();
    static{
        map1.put("pounds", (Float)0.453592f);  // соотношение килограмм/фунт
        map1.put("kilos", (Float)1f);         // килограмм/килограмм = 1
        map2.put("inches", (Float)0.0254f);  // соотношение метр/дюйм 
        map2.put("meters", (Float)1f);      // метр/метр = 1
    } 
    public static String BMI(String mass, String growth){
        float w;
        String [] a1 = mass.trim().split("\\s+"); 
        String [] a2 = growth.trim().split("\\s+"); 
        try{
            w = (Float.parseFloat(a1[0])*map1.get(a1[1]))/((float)Math.pow((double)Float.parseFloat(a2[0])*map2.get(a2[1]), 2)); 
            // вернуть килограмм / метр
        }
        catch(Exception e){
            return "unsupported format";
        }
        return String.format("%.1f %s", w, (w>=25.0f)?"Overweight":( (w<18.5f)?"Underweight":"Normal weight")); 
        // если вес <18,5, то вернуть <вес> + " Underweight"
        // если вес >=25, то вернуть <вес> + " Overweight"
        // иначе <вес> + " Normal weight"
    }
    
//6. Создайте функцию, которая принимает число и возвращает его мультипликативное
//постоянство, которое представляет собой количество раз, которое вы должны
//умножать цифры в num, пока не достигнете одной цифры. 
    public static int bugger(int a){
        int r = 0;
        int temp = 1;
        String h = String.valueOf(a);
        while (h.length()!=1){
            for ( char i: h.toCharArray()){
                i-=48;
                temp*=i;
            }
            h=String.valueOf(temp);
            temp=1;
            r+=1;
        }
        return r;
    }      
    
//7. Напишите функцию, которая преобразует строку в звездную стенографию. Если
//символ повторяется n раз, преобразуйте его в символ*n.
    public static String toStarShorthand(String str){
        char push = 0;
        int temp = 0;
        
        StringBuilder out = new StringBuilder();
        
        for (char i: str.toCharArray()){
            if (push==i){
                temp+=1;
            }
            else{
                out.append(push);
                push = i;
                if (temp>1){
                    out.append('*');
                    out.append(String.valueOf(temp));
                }
                temp = 1;
            }
        }
        out.append(push);
        if (temp>1){
            out.append('*');
            out.append(String.valueOf(temp));
        }
        return out.toString();
    }
    
//8. Создайте функцию, которая возвращает true, если две строки рифмуются, и false в
//противном случае. Для целей этого упражнения две строки рифмуются, если
//последнее слово из каждого предложения содержит одни и те же гласные. 
    public static boolean doesRhyme(String str1, String str2){
        char [] _str1 = str1.trim().toCharArray();
        char [] _str2 = str2.trim().toCharArray();
        
        StringBuilder vowel_list1 = new StringBuilder(); // список гласных первой строки
        StringBuilder vowel_list2 = new StringBuilder(); // список гласных второй строки
        
        char temp = 0;
        int i = _str1.length;
        while (i>0){ // цикл начинается с конца строки
            i-=1;
            temp=Character.toUpperCase(_str1[i]);
            if (!Character.isLetter(temp)) break; // 
            if (VOWELS.indexOf(temp) >= 0) vowel_list1.append(temp); // если символ - гласная буква, то добавить в список
        }
        i = _str2.length;
        while (i>0){ // цикл начинается с конца строки
            i-=1;
            temp=Character.toUpperCase(_str2[i]);
            if (!Character.isLetter(temp)) break;
            if (VOWELS.indexOf(temp) >= 0) vowel_list2.append(temp); // если символ - гласная буква, то добавить в список
        }
        return vowel_list1.toString().equals(vowel_list2.toString());// сравнить списки
    }
    
    private static final String VOWELS = "AEIOUYАЕЁИОУЫЭЮЯÀÁÂÃÄÅĀĂĄǺȀȂẠẢẤẦẨẪẬẮẰẲẴẶḀÆǼȄȆḔḖḘḚḜẸẺẼẾỀỂỄỆĒĔĖĘĚÈÉÊËȈȊḬḮỈỊĨĪĬĮİÌÍÎÏĲŒØǾȌȎṌṎṐṒỌỎỐỒỔỖỘỚỜỞỠỢŌÒÓŎŐÔÕÖŨŪŬŮŰŲÙÚÛÜȔȖṲṴṶṸṺỤỦỨỪỬỮỰẙỲỴỶỸŶŸÝ";
    
//9. Создайте функцию, которая принимает два целых числа и возвращает true, если
//число повторяется три раза подряд в любом месте в num1 и то же самое число
//повторяется два раза подряд в num2. 
    public static boolean trouble(int a, int b){
        String str1 = String.valueOf(a);
        String str2 = String.valueOf(b);
        char push = 0;
        int temp = 0;
        
        List num1_2 = new ArrayList<Integer> ();// числа, которые повторяются 2 раза в num1
        List num1_3 = new ArrayList<Integer> ();// числа, которые повторяются 3 раза в num1
        List num2_2 = new ArrayList<Integer> ();// числа, которые повторяются 2 раза в num2
        List num2_3 = new ArrayList<Integer> ();// числа, которые повторяются 3 раза в num1
        
        for (char i: str1.toCharArray()){
            if (push==i){
                temp+=1;
            }
            else{
                if (temp==2){
                    if (!num1_2.contains(push)) num1_2.add(push);
                }
                if (temp==3){
                    if (!num1_3.contains(push)) num1_3.add(push);
                }
                temp = 1;
                push = i;
            }
        }
        
        if (temp==2){
            if (!num1_2.contains(push)) num1_2.add(push);
        }
        if (temp==3){
            if (!num1_3.contains(push)) num1_3.add(push);
        }
        push = 0;
        temp = 0;
        for (char i: str2.toCharArray()){
            if (push==i){
                temp+=1;
            }
            else{
                if (temp==2){
                    if (!num2_2.contains(push)) num2_2.add(push);
                }
                if (temp==3){
                    if (!num2_3.contains(push)) num2_3.add(push);
                }
                temp = 1;
                push = i;
            }
        } 
        if (temp==2){
            if (!num2_2.contains(push)) num2_2.add(push);
        }
        if (temp==3){
            if (!num2_3.contains(push)) num2_3.add(push);
        }
        push = 0;
        temp = 0;       
        return !(Collections.disjoint(num1_2, num2_3)&Collections.disjoint(num2_2, num1_3));
    }
    
//10. Предположим, что пара одинаковых символов служит концами книги для всех
//символов между ними. Напишите функцию, которая возвращает общее количество
//уникальных символов (книг, так сказать) между всеми парами концов книги.
    public static int countUniqueBooks(String a, char b){
        ArrayList<Character> sequence = new ArrayList<Character> ();//все уникальные символы 
        boolean push = false;
        for (char i : a.toCharArray()){ // для каждого i в последовательности символов
            if (i == b) push = !push; 
            else if (push & (!sequence.contains(i))) sequence.add(i); // 
        }
        return sequence.size(); // размер списка уникальных символов
    }
    
    public static void main(String [] argv){
       
    }
}
