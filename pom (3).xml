package utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    // Returns date after adding days to today
    public static String getFutureDate(int daysToAdd) {
        return LocalDate.now().plusDays(daysToAdd)
                .format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
}