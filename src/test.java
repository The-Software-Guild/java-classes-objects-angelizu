import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

public class test {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //System.out.println("Enter a date ");
        //String date1 = scanner.nextLine();
        //validateDate();
        String date = validateDate();
        System.out.println(date);

        //validateDate();

        //runapp();

    }

    private static String validateDate() {
        boolean validDate = true;
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd/MM/yyyy")
                .toFormatter(Locale.ENGLISH);
        Scanner scanner = new Scanner(System.in);
        LocalDate curdate = LocalDate.now();
        int curyear = curdate.getYear();
        String dateStr = null;
        while (validDate) {
            try {
                dateStr = scanner.nextLine();
                LocalDate ld = LocalDate.parse(dateStr, df);
                int year = ld.getYear();
                if (year < 1000 || year > curyear) {
                    System.out.println("Out of range try again");
                } else {
                    //System.out.println(dateStr + " " + ld);
                    validDate = false;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect date format " + e);
                System.out.println("Please try again");
                validDate = true;
            }
        }
        return dateStr;


    }

    public static String validateDate(String date) {
        boolean valid = true;
        DateTimeFormatter df = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd/MM/yyyy")
                .toFormatter(Locale.ENGLISH);
        Scanner scanner1 = new Scanner(System.in);
        LocalDate curdate = LocalDate.now();
        int curyear = curdate.getYear();
        while (valid) {
            try {
                String date1 = scanner1.nextLine();
                LocalDate ld = LocalDate.parse(date1, df);
                int year = ld.getYear();
                if (year < 1000 || year > curyear) {
                    System.out.println("Out of range try again");
                } else {
                    date = date1;
                    System.out.println(date + " " + ld);
                    valid = false;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect date format " + e);
                System.out.println("Please try again");
            }
        }
        return date;
    }

}
