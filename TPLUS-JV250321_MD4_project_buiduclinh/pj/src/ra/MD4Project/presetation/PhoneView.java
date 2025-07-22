package ra.MD4Project.presetation;

import ra.MD4Project.Model.Phone;
import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.business.IPhoneService;
import ra.MD4Project.business.impl.PhoneServiceImpl;

import java.util.List;
import java.util.Scanner;

public class PhoneView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IPhoneService phoneService = new PhoneServiceImpl();

    public static void PhoneViewPresentation() {
        boolean exit = false;
        do {
            System.out.println("============== QUẢN LÝ SẢN PHẨM ==============");
            System.out.println("1. Hiển thị danh sách sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xóa sản phẩm theo ID");
            System.out.println("5. Tìm kiếm theo Brand");
            System.out.println("6. Tìm kiếm theo khoảng giá");
            System.out.println("7. Tìm kiếm theo tồn kho");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng (1-8): ");

            String choice = scanner.nextLine();

            if (ValidateInput.isInt(choice)) {
                int option = Integer.parseInt(choice);

                switch (option) {
                    case 1:
                        displayProducts();
                        break;
                    case 2:
                        addProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        findProductByBrand();
                        break;
                    case 6:
                        findProductByPrice();
                        break;
                    case 7:
                        findProductByStock();
                        break;
                    case 8:
                        System.out.println("Thoát chương trình. Trở lại Menu Chính!");
                        exit = true;
                        break;
                    default:
                        System.err.println("Vui lòng chọn số từ 1 đến 8!");
                }
            } else {
                System.err.println("Lựa chọn không hợp lệ. Nhập số từ 1 đến 8!");
            }

        } while (!exit);
    }

    private static void displayProducts() {
        List<Phone> products = phoneService.displayProduct();
        if (products.isEmpty()) {
            System.out.println("📭 Không có sản phẩm nào trong danh sách.");
        } else {
            products.forEach(System.out::println);
        }
    }

    private static void addProduct() {
        Phone phone = new Phone();
        phone.inputData(scanner);

        boolean result = phoneService.addProduct(phone);
        if (result) {
            System.out.println("Thêm sản phẩm thành công!");
        } else {
            System.err.println("Thêm sản phẩm thất bại!");
        }
    }

    private static void updateProduct() {
        boolean backToPhoneServiceMenu = false;
        Phone product = new Phone();
        try {
            int inputId = product.inputId(scanner);
            Phone productIsExist = phoneService.findProductById(inputId);
            if (productIsExist == null) {
                System.err.println("Id không tồn tại!");
            } else {
                System.out.println(productIsExist);
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
                        if (ValidateInput.isInt(choice)) {
                            choiceValue = Integer.parseInt(choice);
                            switch (choiceValue) {
                                case 1:
                                    product.inputName(scanner);
                                    break;
                                case 2:
                                    product.inputBrand(scanner);
                                    break;
                                case 3:
                                    product.inputPrice(scanner);
                                    break;
                                case 4:
                                    product.inputStock(scanner);
                                    break;
                                case 5:
                                    backToPhoneServiceMenu = true;
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

    private static void deleteProduct() {
        System.out.print("Nhập ID sản phẩm cần xóa: ");
        String inputId = scanner.nextLine();

        if (ValidateInput.isInt(inputId)) {
            int id = Integer.parseInt(inputId);
            Phone existingProduct = phoneService.findProductById(id);

            if (existingProduct == null) {
                System.err.println("Sản phẩm với ID " + id + " không tồn tại.");
                return;
            }

            System.out.print("Bạn có chắc chắn muốn xóa sản phẩm này? (y/n): ");
            String confirm = scanner.nextLine();
            if (confirm.equalsIgnoreCase("y")) {
                boolean result = phoneService.deleteProduct(id);
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

    private static void findProductByBrand() {
        System.out.print("Nhập brand cần tìm kiếm: ");
        String brand = scanner.nextLine();

        if (ValidateInput.isEmpty(brand)) {
            System.err.println("Brand không được để trống.");
        } else {
            List<Phone> products = phoneService.displayProductByBrand(brand);
            if (products.isEmpty()) {
                System.out.println("Không tìm thấy sản phẩm nào với brand này.");
            } else {
                products.forEach(System.out::println);
            }
        }
    }

    private static void findProductByPrice() {
        System.out.print("Nhập khoảng giá (giá thấp nhất): ");
        String priceMinStr = scanner.nextLine();
        System.out.print("Nhập khoảng giá (giá cao nhất): ");
        String priceMaxStr = scanner.nextLine();

        if (ValidateInput.isFloat(priceMinStr) && ValidateInput.isFloat(priceMaxStr)) {
            float priceMin = Float.parseFloat(priceMinStr);
            float priceMax = Float.parseFloat(priceMaxStr);

            List<Phone> products = phoneService.displayProductByPrice(priceMin, priceMax);
            if (products.isEmpty()) {
                System.out.println("Không tìm thấy sản phẩm nào trong khoảng giá này.");
            } else {
                products.forEach(System.out::println);
            }
        } else {
            System.err.println("Giá nhập vào không hợp lệ.");
        }
    }

    private static void findProductByStock() {
        System.out.print("Nhập tồn kho tối thiểu: ");
        String stockMinStr = scanner.nextLine();
        System.out.print("Nhập tồn kho tối đa: ");
        String stockMaxStr = scanner.nextLine();

        if (ValidateInput.isInt(stockMinStr) && ValidateInput.isInt(stockMaxStr)) {
            int stockMin = Integer.parseInt(stockMinStr);
            int stockMax = Integer.parseInt(stockMaxStr);

            List<Phone> products = phoneService.displayProductByStock(stockMin, stockMax);
            if (products.isEmpty()) {
                System.out.println("Không tìm thấy sản phẩm nào trong khoảng tồn kho này.");
            } else {
                products.forEach(System.out::println);
            }
        } else {
            System.err.println("Tồn kho nhập vào không hợp lệ.");
        }
    }
}