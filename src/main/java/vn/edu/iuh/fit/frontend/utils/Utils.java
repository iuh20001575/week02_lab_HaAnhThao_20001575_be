package vn.edu.iuh.fit.frontend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
    private static final Logger logger = LoggerFactory.getLogger("Utils");

    public static int convertToPage(String number) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return 1;
    }
}
