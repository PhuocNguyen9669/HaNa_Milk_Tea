package utils;


import java.text.DecimalFormat;

public class FormatVND {
    public static String doubleToVND(double value){
        String patternVND = ",###đ";
        DecimalFormat decimalFormat = new DecimalFormat(patternVND);
        return decimalFormat.format(value);
    }
}
