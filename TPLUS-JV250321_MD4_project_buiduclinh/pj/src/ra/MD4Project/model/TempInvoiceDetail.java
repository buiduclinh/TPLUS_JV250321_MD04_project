package ra.MD4Project.model;

import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.dao.IPhoneDAO;
import ra.MD4Project.dao.impl.PhoneDAOImpl;

import java.math.BigDecimal;
import java.util.Scanner;

public class TempInvoiceDetail {
    private int productId;
    private int quantity;
    private Double unitPrice;

    private IPhoneDAO phoneDAO;

    public TempInvoiceDetail() {
        phoneDAO = new PhoneDAOImpl();
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int inputTempProductId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID sản phẩm: ");
            String idInput = scanner.nextLine().trim();

            if (ValidateInput.isInt(idInput)) {
                int idValue = Integer.parseInt(idInput);
                if (idValue > 0) {
                    if (phoneDAO.findProductById(idValue) != null) {
                        setProductId(idValue);
                        return idValue;
                    } else {
                        System.out.println("ID sản phẩm không tồn tại!");
                    }
                } else {
                    System.err.println("ID phải lớn hơn 0. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("ID không hợp lệ. Chỉ được nhập số nguyên. Vui lòng nhập lại!");
            }
        }
    }

    public int inputTempQuantity(Scanner scanner) {
        while (true) {
            System.out.print("Nhập số lượng sản phẩm: ");
            String quantityInput = scanner.nextLine().trim();

            if (ValidateInput.isInt(quantityInput)) {
                int quantityValue = Integer.parseInt(quantityInput);
                if (quantityValue > 0) {
                    setQuantity(quantityValue);
                    return quantityValue;
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
        return String.format("Mã sản phẩm: %d, Số lượng sản phẩm: %d, Giá sản phẩm: %.2f",
                this.productId, this.quantity, this.unitPrice);
    }
}
