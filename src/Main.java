import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ФИО и дату рождения (день.месяц.год): ");
        String surname = scanner.next();
        String name = scanner.next();
        String pName = scanner.next();
        String date = scanner.next();
        String end = "лет";
        char sex = 'W';

        ArrayList<Integer> threeZero = new ArrayList<>(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
        int day = Integer.parseInt(date.substring(0, 2));
        int month = Integer.parseInt(date.substring(3, 5));
        int year = Integer.parseInt(date.substring(6, 10));

        if (day > 31) {
            System.out.println("В месяце не больше 31 дня");
            return;
        } else if (day > 30 && threeZero.contains(month)) {
            System.out.println("В данном месяце не больше 31 дня");
            return;
        } else if (day > 29 && month == 2 && Year.isLeap(year)) {
            System.out.println("Год не является високосным");
            return;
        } else if (day > 28 && month == 2) {
            System.out.println("В феврале не больше 28-29 дней");
            return;
        } else if (month > 12) {
            System.out.println("В году 12 месяцев");
            return;
        }

        if (pName.charAt(pName.length() - 1) == 'ч') {
            sex = 'M';
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date birthDate = formatter.parse(date);
            Date currentDate = Calendar.getInstance().getTime();
            long diffInMills = currentDate.getTime() - birthDate.getTime();
            long diffInYears = diffInMills / (24L * 3600 * 1000 * 365);
            if (diffInYears < 20) {
                ;
            } else if (diffInYears % 10 == 1) {
                end = "год";
            } else if (diffInYears % 10 == 2 | diffInYears % 10 == 3 | diffInYears % 10 == 4) {
                end = "года";
            }
            System.out.println(surname + " " + name.charAt(0) + "." + pName.charAt(0) + ". " + sex + " " + diffInYears + " " + end);
        } catch (ParseException ex) {
            System.out.println("Введите дату рождения по шаблону");
            throw ex;
        }
    }
}