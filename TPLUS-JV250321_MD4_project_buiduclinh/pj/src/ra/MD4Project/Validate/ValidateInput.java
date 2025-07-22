package ra.MD4Project.Validate;

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
}