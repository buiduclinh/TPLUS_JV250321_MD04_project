package ra.MD4Project.presetation.login.Impl;

import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.presetation.MainMenu;

import java.util.Scanner;

public class Login {
    private static ILogin loginDAO;

    public Login() {
        loginDAO = new LoginImpl();
    }

    public static void LoginPresentation() {
        Scanner scanner = new Scanner(System.in);
        boolean next = false;
        do {
            try {
                System.out.println("========== Đăng nhập quản trị ==========");
                System.out.println("Tài khoản: ");
                String username = scanner.nextLine();
                System.out.println("Mật khẩu: ");
                String password = scanner.nextLine();
                if (ValidateInput.isEmpty(username) || ValidateInput.isEmpty(password)) {
                    System.out.println("Không được để trống Tài khoản và Mật khẩu!");
                } else {
                    Admin admin = loginDAO.admin(username, password);
                    if (admin == null) {
                        System.out.println("Tài khoản mật khẩu sai hoặc không tồn tại!");
                    } else {
                        System.out.println("Đăng nhập thành công!");
                        next = true;
                        MainMenu.MainMenuPresentation();
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (!next);
    }
}

