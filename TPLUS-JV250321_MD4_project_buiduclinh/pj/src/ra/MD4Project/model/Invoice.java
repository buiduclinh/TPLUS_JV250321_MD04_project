package ra.MD4Project.model;

import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.dao.ICustomerDAO;
import ra.MD4Project.dao.impl.CustomerDAOImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Invoice {
    private ICustomerDAO customerDAO;

    private int id;
    private int customerId;
    private LocalDateTime createdAt;
    private Double totalAmount;
    private String customerName;

    public ICustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public void setCustomerDAO(ICustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Invoice(int id, int customerId, LocalDateTime createdAt, Double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
    }

    public Invoice() {
        customerDAO = new CustomerDAOImpl();
    }

    ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int inputCustomerId(Scanner scanner) {
        while (true) {
            System.out.print("Nhập ID khách hàng: ");
            String idInput = scanner.nextLine().trim();

            if (ValidateInput.isInt(idInput)) {
                int idValue = Integer.parseInt(idInput);
                if (idValue > 0) {
                    if (customerDAO.getCustomerById(idValue) != null) {
                        setCustomerId(idValue);
                        return idValue;
                    } else {
                        System.out.println("ID khách hàng không tồn tại!");
                    }
                } else {
                    System.err.println("ID phải lớn hơn 0. Vui lòng nhập lại!");
                }
            } else {
                System.err.println("ID không hợp lệ. Chỉ được nhập số nguyên. Vui lòng nhập lại!");
            }
        }
    }

    public LocalDate inputCreatedAt(Scanner scanner) {
        LocalDate date = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (date == null) {
            System.out.print("Nhập ngày tạo hóa đơn (định dạng yyyy-MM-dd): ");
            String input = scanner.nextLine();
            try {
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày không hợp lệ. Vui lòng nhập lại đúng định dạng (ví dụ: 2024-12-31).");
            }
        }
        return date;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return String.format("Mã hóa đơn: %d, Mã khách hàng: %s, Ngày in hóa đơn: %s, Tổng giá trị hóa đơn: %.2f",
                this.id, this.customerId, this.createdAt.format(formatter), this.totalAmount);
    }
}
