package ra.MD4Project.presetation;

import ra.MD4Project.Model.Phone;
import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.business.IPhoneService;
import ra.MD4Project.business.impl.PhoneServiceImpl;
import ra.MD4Project.dao.impl.PhoneDAOImpl;

import java.util.List;
import java.util.Scanner;


public class PhoneView {
    private IPhoneService phoneService;
    private Scanner scanner;

    public PhoneView() {
        phoneService = new PhoneServiceImpl();
        scanner = new Scanner(System.in);
    }

    public void PhoneViewPresetiton() {
        boolean backToMainMenu = false;
        do {
            System.out.println("============== Quản lý sản phẩm ==============");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Quay lại menu chính");
            int choiceValue;
            String choice;
            try {
                choice = scanner.nextLine();
                if (ValidateInput.validateIsInt(choice)) {
                    choiceValue = Integer.parseInt(choice);
                    switch (choiceValue) {
                        case 1:
                            displayProducts();
                            break;
                        case 2:
                            addProduct(scanner);
                            break;
                        case 3:
                            updateProduct(scanner);
                            break;
                        case 4:
                            deleteProduct(scanner);
                            break;
                        case 5:
                            findProductByBrand(scanner);
                            break;
                        case 6:
                            findProductByPrice(scanner);
                            break;
                        case 7:
                            findProductByStock(scanner);
                            break;
                        case 8:
                            backToMainMenu = true;
                        default:
                            System.err.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                    }
                } else {
                    System.err.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (!backToMainMenu);


    }

    public void displayProducts() {
        List<Phone> products = phoneService.displayProduct();
        if (products.isEmpty()) {
            System.out.println("Không có sản phầm nào!");
        } else {
            products.forEach(System.out::println);
        }
    }

    public void addProduct(Scanner scanner) {
        Phone product = new Phone();
        try {
            product.inputData(scanner);
            boolean result = phoneService.addProduct(product);
            if (result) {
                System.out.println("Thêm sản phẩm thành công!");
            } else {
                System.err.println("Thêm sản phẩm không thành công!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Scanner scanner) {
        Phone product = new Phone();
        try {
            int inputId = product.inputId(scanner);
            Phone productIsExist = phoneService.findProductById(inputId);
            if (productIsExist == null) {
                System.err.println("Id không tồn tại!");
            } else {
                System.out.println(product);
                boolean backToPhoneServiceMenu = false;
                do {
                    System.out.println("************** Cập nhật sản phẩm **************");
                    System.out.println("1. Cập nhật tên sản phẩm");
                    System.out.println("2. Cập nhật nhãn sản phẩm");
                    System.out.println("3. Cập nhật giá sản phẩm");
                    System.out.println("4. Cập nhật số lượng tồn kho");
                    System.out.println("5. Trở lại danh mục dịch vụ điện thoại.");
                    int choiceValue;
                    String choice;
                    try {
                        choice = scanner.nextLine();
                        if (ValidateInput.validateIsInt(choice)) {
                            choiceValue = Integer.parseInt(choice);
                            switch (choiceValue) {
                                case 1:
                                    product.inputName(scanner);
                                    break;
                                case 2:
                                    product.inputBrand(scanner);
                                case 3:
                                    product.inputPrice(scanner);
                                case 4:
                                    product.inputStock(scanner);
                                    break;
                                case 5:
                                    backToPhoneServiceMenu = true;
                                default:
                                    System.err.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                            }
                        } else {
                            System.err.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                        }
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e);
                    }
                } while (!backToPhoneServiceMenu);
            }
            boolean result = phoneService.updateProduct(product);
            if (result) {
                System.out.println("Cập nhật thành công sản phẩm!");
            } else {
                System.out.println("Cập nhật không thành công!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteProduct(Scanner scanner) {
        Phone product = new Phone();
        try {
            int inputId = product.inputId(scanner);
            Phone productIsExist = phoneService.findProductById(inputId);
            if (productIsExist == null) {
                System.err.println("Id không tồn tại!");
            } else {
                System.out.println(product);
                System.out.println("Bạn có chắc chắn muốn xóa không? y/n ");
                String y = scanner.nextLine();
                if (y.equalsIgnoreCase("y")) {
                    boolean result = phoneService.deleteProduct(inputId);
                    if (result) {
                        System.out.println("Xóa sản phẩm thành công!");
                    } else {
                        System.out.println("Xóa không thành công!");
                    }
                } else {
                    System.out.println("Sản phầm chưa vẫn tồn tại!");
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void findProductByBrand(Scanner scanner) {
        boolean backToPhoneServiceMenu = false;
        do {
            System.out.println("Nhập nhãn sản phẩm cần tìm kiếm: ");
            try {
                String findBrand = scanner.nextLine();
                if (ValidateInput.ValidateIsEmpty(findBrand)) {
                    System.out.println("Không được để trống!");
                } else {
                    List<Phone> products = phoneService.displayProductByBrand(findBrand);
                    if (products.isEmpty()) {
                        System.out.println("Không có sản phẩm nào!");
                        backToPhoneServiceMenu = true;
                    } else {
                        products.forEach(System.out::println);
                        backToPhoneServiceMenu = true;
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (!backToPhoneServiceMenu);
    }

    public void findProductByPrice(Scanner scanner) {
        boolean backToPhoneServiceMenu = false;
        do {
            System.out.println("Nhập nhãn sản phẩm cần tìm kiếm: ");
            float price1;
            float price2;
            try {
                String findPrice1 = scanner.nextLine();
                String findPrice2 = scanner.nextLine();
                if (ValidateInput.ValidateIsFloat(findPrice1) && ValidateInput.ValidateIsFloat(findPrice2)) {
                    System.out.println("Không được để trống!");
                } else {
                    price1 = Float.parseFloat(findPrice1);
                    price2 = Float.parseFloat(findPrice2);
                    List<Phone> products = phoneService.displayProductByPrice(price1, price2);
                    if (products.isEmpty()) {
                        System.out.println("Không có sản phẩm nào!");
                        backToPhoneServiceMenu = true;
                    } else {
                        products.forEach(System.out::println);
                        backToPhoneServiceMenu = true;
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (!backToPhoneServiceMenu);
    }

    public void findProductByStock(Scanner scanner) {
        boolean backToPhoneServiceMenu = false;
        do {
            System.out.println("Nhập nhãn sản phẩm cần tìm kiếm: ");
            int stock1;
            int stock2;
            try {
                String findStock1 = scanner.nextLine();
                String findStock2 = scanner.nextLine();
                if (ValidateInput.ValidateIsFloat(findStock1) && ValidateInput.ValidateIsFloat(findStock2)) {
                    System.out.println("Không được để trống!");
                } else {
                    stock1 = Integer.parseInt(findStock1);
                    stock2 = Integer.parseInt(findStock2);
                    List<Phone> products = phoneService.displayProductByStock(stock1, stock2);
                    if (products.isEmpty()) {
                        System.out.println("Không có sản phẩm nào!");
                        backToPhoneServiceMenu = true;
                    } else {
                        products.forEach(System.out::println);
                        backToPhoneServiceMenu = true;
                    }
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (!backToPhoneServiceMenu);
    }
}