package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String NUMBER_REGEX = "\\d+";
    public static final String LETTER_WITHOUT_NUMBER_REGEX = "^([A-Z]+[a-z]*[ ]?)+$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}$";
    public static final String DATE_REGEX = "^[0-9]{4}-([0-9]|0[0-9]|1[0-2])-([0-9]|[0-2][0-9]|3[0-1])$";
    public static final String AGE_REGEX = "^[1-9][0-9]{0,2}";
    public static final String PHONE_REGEX ="^[0][1-9][0-9]{8}$|^[+84][1-9][0-9]{10}$";
    public static final String QUANTITY_REGEX = "^[1-9][0-9]{0,2}";
    public static final String PRICE_REGEX = "^[1-9][0-9]{1,10}";


    public static boolean isPriceValid(String price) {
        return Pattern.compile(PRICE_REGEX).matcher(price).matches();
    }

    public static boolean isQuantityValid(String quantity) {
        return Pattern.compile(QUANTITY_REGEX).matcher(quantity).matches();
    }

    public static boolean isPhoneValid(String phone) {
       return Pattern.compile(PHONE_REGEX).matcher(phone).matches();
    }

    public static boolean isAgeValid(String age) {
        return Pattern.compile(AGE_REGEX).matcher(age).matches();
    }

    public static boolean isNumberValid(String number) {
        return Pattern.compile(NUMBER_REGEX).matcher(number).matches();
    }

    public static boolean isLetterWithoutNumberValid(String name) {
        return Pattern.compile(LETTER_WITHOUT_NUMBER_REGEX).matcher(name).matches();
    }

    public static boolean isEmailValid(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public static boolean isDateValid(String dateStr) {
        return Pattern.compile(DATE_REGEX).matcher(dateStr).matches();
    }

}
