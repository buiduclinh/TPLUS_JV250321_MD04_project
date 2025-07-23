package ra.MD4Project.model;

import ra.MD4Project.Validate.ValidateInput;

import java.util.Scanner;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Customer() {
    }

    ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void inputData(Scanner scanner) {
        System.out.println("Nhập thông tin khách hàng");
        inputCustomerName(scanner);
        inputCustomerEmail(scanner);
        inputCustomerPhone(scanner);
        inputCustomerAddress(scanner);
        System.out.println("Nhập thông tin khách hàng thành công");
    }

    public int inputCustomerId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID khách hàng: ");
            String idInput = scanner.nextLine().trim();

            if (ValidateInput.isInt(idInput)) {
                int idValue = Integer.parseInt(idInput);
                if (idValue > 0) {
                    return idValue;
                } else {
                    System.err.println("ID phải lớn hơn 0. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("ID không hợp lệ. Chỉ được nhập số nguyên. Vui lòng nhập lại!");
            }
        }
    }

    public void inputCustomerName(Scanner scanner) {
        do {
            System.out.println("Nhập tên khách hàng: ");
            try {
                String nameInput = scanner.nextLine();
                if (ValidateInput.isEmpty(nameInput)) {
                    System.out.println("Không được để trống ô này!");
                    continue;
                }
                if (nameInput.length() <= 100) {
                    setName(nameInput);
                    break;
                } else {
                    System.out.println("Số lượng ký tự không vượt quá 100 ký tự");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    public void inputCustomerEmail(Scanner scanner) {
        do {
            System.out.println("Nhập email khách hàng: ");
            try {
                String emailInput = scanner.nextLine();
                if(ValidateInput.isEmpty(emailInput)){
                    setEmail(emailInput);
                    break;
                }
                if (emailInput.length() <= 100 && ValidateInput.isEmail(emailInput)) {
                    setEmail(emailInput);
                    break;
                } else {
                    System.out.println("Email nhập vào chưa đúng định dạng!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    public void inputCustomerPhone(Scanner scanner) {
        do {
            System.out.println("Nhập số điện thoại khách hàng: ");
            try {
                String phoneInput = scanner.nextLine();
                if(ValidateInput.isEmpty(phoneInput)){
                    setPhone(phoneInput);
                    break;
                }
                if (phoneInput.length() <= 20 && ValidateInput.isPhone(phoneInput)) {
                    setPhone(phoneInput);
                    break;
                } else {
                    System.out.println("Định dạng số điện thoại nhập chưa đúng!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    public void inputCustomerAddress(Scanner scanner) {
        do {
            System.out.println("Nhập địa chỉ khách hàng: ");
            try {
                String addressInput = scanner.nextLine();
                if (addressInput.length() <= 255) {
                    setAddress(addressInput);
                    break;
                } else {
                    System.out.println("Số lượng ký tự không vượt quá 255 ký tự");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    @Override
    public String toString() {
        return String.format("Mã khách hàng: %d, Tên khách hàng: %s,Số điện thoại khách hàng: %s, Email khách hàng: %s, Địa chỉ khách hàng: %s",
                this.id, this.name, this.phone, this.email, this.address);
    }
}
