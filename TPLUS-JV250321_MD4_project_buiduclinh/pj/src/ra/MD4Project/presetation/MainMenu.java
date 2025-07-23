package ra.MD4Project.presetation;

import ra.MD4Project.Validate.ValidateInput;

import java.util.Scanner;

public class MainMenu {
    public static void MainMenuPresentation() {
        boolean next = false;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("============= MENU CHÍNH =============");
            System.out.println("1. Quản lý điện thoại");
            System.out.println("2. Quản lý khách hàng");
            System.out.println("3. Quản lý hóa đơn");
            System.out.println("4. Thống kê doanh thu");
            System.out.println("5. Đăng xuất");
            System.out.println("======================================");
            System.out.println("Nhập lựa chọn:");
            try {
                String choice = scanner.nextLine();
                if (ValidateInput.isInt(choice)) {
                    int option = Integer.parseInt(choice);
                    switch (option) {
                        case 1:
                            PhoneView.PhoneViewPresentation();
                            break;
                        case 2:
                            CustomerView.CustomerViewPresentation();
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            next = true;
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ!");
                    }
                } else {
                    System.out.println("Lựa chọn không hợp lệ!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (!next);

    }
}
