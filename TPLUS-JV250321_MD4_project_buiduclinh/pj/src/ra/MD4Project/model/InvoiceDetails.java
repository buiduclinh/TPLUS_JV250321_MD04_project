package ra.MD4Project.model;

import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.dao.IInvoiceDAO;
import ra.MD4Project.dao.IPhoneDAO;
import ra.MD4Project.dao.impl.InvoiceDAOImpl;
import ra.MD4Project.dao.impl.PhoneDAOImpl;

import java.util.Scanner;

public class InvoiceDetails {
    private int id;
    private int invoiceId;
    private int productId;
    private int quantity;
    private double unitPrice;

    private IPhoneDAO  phoneDAO;

    private IInvoiceDAO invoiceDAO;

    public InvoiceDetails(int id, int invoiceId, int productId, int quantity, double unitPrice) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public InvoiceDetails() {
        phoneDAO = new PhoneDAOImpl();
        invoiceDAO = new InvoiceDAOImpl();
    }

    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void inputDataInvoiceDetails(Scanner scanner) {
        System.out.println("Nhập dữ liệu:");
        inputInvoiceId(scanner);
        inputProductId(scanner);
        inputQuantity(scanner);
        System.out.println("Hoàn tất nhập dữ liệu!");
    };

    public int inputInvoiceId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID Hóa đơn: ");
            String idInput = scanner.nextLine().trim();

            if (ValidateInput.isInt(idInput)) {
                int idValue = Integer.parseInt(idInput);
                if (idValue > 0) {
                    if (invoiceDAO.findInvoiceId(idValue) != null) {
                        setProductId(idValue);
                        return idValue;
                    } else {
                        System.out.println("ID hóa đơn không tồn tại!");
                    }
                } else {
                    System.err.println("ID phải lớn hơn 0. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("ID không hợp lệ. Chỉ được nhập số nguyên. Vui lòng nhập lại!");
            }
        }
    }


    public int inputProductId(Scanner scanner) {
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

    public int inputQuantity(Scanner scanner) {
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
        return String.format("Mã chi tiết hóa đơn: %d, Mã hóa đơn: %d, Mã sản phẩm: %d, Số lượng sản phẩm: %d, Đơn giá tại thời điểm bán: %.2f",
                this.id, this.invoiceId, this.productId, this.quantity, this.unitPrice);
    }
}
