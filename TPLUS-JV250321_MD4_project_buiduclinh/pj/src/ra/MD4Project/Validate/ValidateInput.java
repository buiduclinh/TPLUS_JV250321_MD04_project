package ra.MD4Project.Validate;

import java.util.Scanner;

public class ValidateInput {

    public static boolean ValidateIsEmpty(String inputData) {

        if (inputData == null || inputData.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean validateIsInt(String inputData) {
        if (inputData == null || inputData.trim().isEmpty()) {
            return false; // Rỗng => Không hợp lệ
        }

        try {
            Integer.parseInt(inputData); // Thử parse thành số
            return true; // Parse được => hợp lệ
        } catch (NumberFormatException e) {
            return false; // Không phải số => không hợp lệ
        }
    }
    public static boolean ValidateIsFloat(String inputData) {
        if (inputData == null || inputData.trim().isEmpty()) {
            return false; // Rỗng => Không hợp lệ
        }

        try {
            Float.parseFloat(inputData); // Thử parse thành số
            return true; // Parse được => hợp lệ
        } catch (NumberFormatException e) {
            return false; // Không phải số => không hợp lệ
        }
    }

}
