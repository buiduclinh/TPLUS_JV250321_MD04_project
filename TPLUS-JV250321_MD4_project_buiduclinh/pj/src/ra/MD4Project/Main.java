package ra.MD4Project;

import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.presetation.PhoneView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("============ HỆ THỐNG QUẢN LÝ CỬA HÀNG ============");
            System.out.println("1. Đăng nhập ADMIN");
            System.out.println("2. Thoát");
            try {
                String choice = scanner.nextLine();
                if (ValidateInput.isInt(choice)) {
                    int option = Integer.parseInt(choice);
                    switch (option) {
                        case 1:
                            PhoneView.PhoneViewPresentation();
                            break;
                        case 2:
                            System.exit(0);
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                    }
                } else {
                    System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }
}