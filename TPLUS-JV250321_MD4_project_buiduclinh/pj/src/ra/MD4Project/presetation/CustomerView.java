package ra.MD4Project.presetation;

import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.business.ICustomerService;
import ra.MD4Project.business.impl.CustomerServiceImpl;
import ra.MD4Project.model.Customer;
import ra.MD4Project.model.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ICustomerService customerService = new CustomerServiceImpl();

    public static void CustomerViewPresentation() {
        boolean next = false;
        do {
            try {
                System.out.println("========== QUẢN LÝ KHÁCH HÀNG ==========");
                System.out.println("1. Hiển thị danh sách khách hàng");
                System.out.println("2. Thêm khách hàng mới");
                System.out.println("3. Cập nhật thông tin khách hàng");
                System.out.println("4. Xóa khách hàng theo ID");
                System.out.println("5. Thoát");
                System.out.println("========================================");
                String choice = scanner.nextLine();
                if (ValidateInput.isInt(choice)) {
                    int option = Integer.parseInt(choice);
                    switch (option) {
                        case 1:
                            displayCustomer();
                            break;
                        case 2:
                            addCustomer();
                            break;
                        case 3:
                            updateCustomer();
                            break;
                        case 4:
                            deleteCustomer();
                            break;
                        case 5:
                            next = true;
                            break;
                        default:
                            System.out.println("Dữ liệu nhập không hợp lệ!");
                    }
                } else {
                    System.out.println("Dữ liệu nhập không hợp lệ!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (!next);
    }

    public static void displayCustomer() {
        List<Customer> customers = customerService.displayAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("Không có khách hàng nào trong danh sách!");
        } else {
            customers.forEach(System.out::println);
        }
    }

    ;

    public static void addCustomer() {
        Customer customer = new Customer();
        customer.inputData(scanner);
        boolean result = customerService.addCustomer(customer);
        if (result) {
            System.out.println("Thêm mới khách hàng thành công!");
        } else {
            System.out.println("không thể thêm mới khách hàng!");
        }
    }

    ;

    public static void updateCustomer() {
        boolean backToCustomerServiceMenu = false;
        Customer customer = new Customer();
        try {
            int inputId = customer.inputCustomerId(scanner);
            Customer customerIsExist = customerService.findCustomerById(inputId);
            if (customerIsExist == null) {
                System.err.println("Id không tồn tại!");
            } else {
                System.out.println(customerIsExist);
                do {
                    System.out.println("************** Cập nhật thông tin khách hàng **************");
                    System.out.println("1. Cập nhật tên khách hàng");
                    System.out.println("2. Cập nhật số điện thoại khách hàng");
                    System.out.println("3. Cập nhật Email khách hàng");
                    System.out.println("4. Cập nhật địa chỉ khách hàng");
                    System.out.println("5. Trở lại danh mục dịch vụ khách hàng.");
                    int choiceValue;
                    String choice;
                    try {
                        choice = scanner.nextLine();
                        if (ValidateInput.isInt(choice)) {
                            choiceValue = Integer.parseInt(choice);
                            switch (choiceValue) {
                                case 1:
                                    customerIsExist.inputCustomerName(scanner);
                                    break;
                                case 2:
                                    customerIsExist.inputCustomerPhone(scanner);
                                    break;
                                case 3:
                                    customerIsExist.inputCustomerEmail(scanner);
                                    break;
                                case 4:
                                    customerIsExist.inputCustomerAddress(scanner);
                                    break;
                                case 5:
                                    backToCustomerServiceMenu = true;
                                    break;
                                default:
                                    System.err.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                            }
                        } else {
                            System.err.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                        }
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                } while (!backToCustomerServiceMenu);
            }
            boolean result = customerService.updateCustomer(customerIsExist);
            if (result) {
                System.out.println("Cập nhật thành công sản phẩm!");
            } else {
                System.out.println("Cập nhật không thành công!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    ;

    public static void deleteCustomer() {
        System.out.print("Nhập ID khách hàng cần xóa: ");
        String inputId = scanner.nextLine();

        if (ValidateInput.isInt(inputId)) {
            int id = Integer.parseInt(inputId);
            Customer existingCustomer = customerService.findCustomerById(id);

            if (existingCustomer == null) {
                System.err.println("Khách hàng với ID " + id + " không tồn tại.");
                return;
            }

            System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này? (y/n): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                boolean result = customerService.deleteCustomer(id);
                if (result) {
                    System.out.println("Xóa sản phẩm thành công!");
                } else {
                    System.err.println("Xóa sản phẩm thất bại!");
                }
            } else {
                System.out.println("Đã hủy xóa sản phẩm.");
            }

        } else {
            System.err.println("ID phải là số nguyên hợp lệ!");
        }
    }

    ;

}
