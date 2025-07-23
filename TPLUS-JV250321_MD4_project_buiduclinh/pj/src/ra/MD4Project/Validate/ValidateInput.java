package ra.MD4Project.Validate;

import java.util.regex.Pattern;

public class ValidateInput {


    public static boolean isEmpty(String inputData) {
        return inputData == null || inputData.trim().isEmpty();
    }


    public static boolean isInt(String inputData) {
        if (isEmpty(inputData)) return false;
        try {
            Integer.parseInt(inputData); // Thử parse
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static boolean isFloat(String inputData) {
        if (isEmpty(inputData)) return false;
        try {
            Float.parseFloat(inputData); // Thử parse
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isEmail(String inputData) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, inputData);
    }

    public static boolean isPhone(String inputData) {
        String phoneRegex = "^(0[1-9])+([0-9]{8,9})$"; // Số bắt đầu từ 0 + 9~10 số
        return Pattern.matches(phoneRegex, inputData);
    }
}