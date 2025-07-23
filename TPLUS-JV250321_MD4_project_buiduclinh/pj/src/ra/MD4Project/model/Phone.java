package ra.MD4Project.model;

import ra.MD4Project.Validate.ValidateInput;

import java.util.Scanner;

public class Phone {
    private int id;
    private String name;
    private String brand;
    private float price;
    private int stock;

    public Phone() {
    }

    ;

    public Phone(int id, String name, String brand, float price, int stock) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void inputData(Scanner scanner) {
        System.out.println("===== NHẬP THÔNG TIN SẢN PHẨM =====");
        inputName(scanner);
        inputBrand(scanner);
        inputPrice(scanner);
        System.out.println("Nhập thông tin sản phẩm thành công!");
    }

    public void inputName(Scanner scanner) {
        do {
            System.out.println("Nhập tên sản phẩm: ");
            try {
                String nameInput = scanner.nextLine();
                if(ValidateInput.isEmpty(nameInput)){
                    System.out.println("Không được để trống trường này!");
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

    public void inputBrand(Scanner scanner) {
        do {
            System.out.println("Nhập nhãn sản phẩm");
            try {
                String brand = scanner.nextLine();
                if(ValidateInput.isEmpty(brand)){
                    System.out.println("Không được để trống trường này!");
                    continue;
                }
                if (brand.length() <= 100) {
                    setBrand(brand);
                    break;
                } else {
                    System.out.println("Số lượng ký tự không vượt quá 100 ký tự");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    public void inputPrice(Scanner scanner) {
        String priceInput;
        float priceValue;
        do {
            System.out.print("Nhập giá sản phẩm: ");
            try {
                priceInput = scanner.nextLine();
                if (ValidateInput.isFloat(priceInput)) {
                    priceValue = Float.parseFloat(priceInput);
                    if (priceValue > 0) {
                        setPrice(priceValue);
                        break;
                    } else {
                        System.out.println("Giá nhập vào phải lớn hơn 0");
                    }
                } else {
                    System.out.println("Giá sản phẩm không hợp lệ. Vui lòng nhập lại!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    public void inputStock(Scanner scanner) {
        String stockInput;
        int stockValue;
        do {
            System.out.println("Nhập số lượng sản phẩm");
            try {
                stockInput = scanner.nextLine();
                if (ValidateInput.isInt(stockInput)) {
                    stockValue = Integer.parseInt(stockInput);
                    if (stockValue > 0) {
                        setStock(stockValue);
                        break;
                    } else {
                        System.out.println("Số lượng tồn nhập vào phải lớn hơn 0");
                    }
                } else {
                    System.out.println("Số lượng tồn kho sản phẩm không hợp lệ. Vui lòng nhập lại!");
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        } while (true);
    }

    public int inputId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID sản phẩm: ");
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

    @Override
    public String toString() {
        return String.format("Mã sản phẩm: %d, Tên sản phẩm: %s, Tên nhãn sản phẩm: %s, Giá sản phẩm: %f, Số lượng tồn kho: %d",
                this.id, this.name, this.brand, this.price, this.stock);
    }


}
