package ra.MD4Project.presetation;


import ra.MD4Project.Validate.ValidateInput;
import ra.MD4Project.business.IInvoiceDetailsService;
import ra.MD4Project.business.IInvoiceService;
import ra.MD4Project.business.impl.InvoiceDetailsServiceImpl;
import ra.MD4Project.business.impl.InvoiceServiceImpl;
import ra.MD4Project.model.Invoice;
import ra.MD4Project.model.InvoiceDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class InvoiceView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final IInvoiceService invoiceService = new InvoiceServiceImpl();
    private static final IInvoiceDetailsService invoiceDetailsService = new InvoiceDetailsServiceImpl();

    public static void InvoiceViewPresentation() {
        boolean next = false;
        do {
            try {
                System.out.println("========== QUẢN LÝ HÓA ĐƠN ==========");
                System.out.println("1. Hiển thị danh sách hóa đơn");
                System.out.println("2. Thêm mới hóa đơn");
                System.out.println("3. Tìm kiếm hóa đơn");
                System.out.println("4. Quay lại menu chính");
                System.out.println("========================================");
                String choice = scanner.nextLine();
                if (ValidateInput.isInt(choice)) {
                    int option = Integer.parseInt(choice);
                    switch (option) {
                        case 1:
                            displayInvoice();
                            break;
                        case 2:
                            addInvoice();
                            break;
                        case 3:
                            findInvoice();
                            break;
                        case 4:
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

    public static void displayInvoice() {
        List<Invoice> invoiceList = invoiceService.getInvoices();
        if (invoiceList.isEmpty()) {
            System.out.println("Không có hóa đơn nào!");
        } else {
            invoiceList.forEach(System.out::println);
        }
    }

    public static void addInvoice() {
        InvoiceDetails invoiceDetails = new InvoiceDetails();
        Invoice invoice = new Invoice();
        invoice.inputCustomerId(scanner);
        int result = invoiceService.addInvoice(invoice);
        if (result != -1) {
            System.out.println("Thêm mới hóa đơn thành công");
            System.out.println("Thêm chi tiết hóa đơn");
            invoiceDetails.setInvoiceId(result);
            invoiceDetails.inputProductId(scanner);
            invoiceDetails.inputQuantity(scanner);
            boolean resultDetails = invoiceDetailsService.addInvoiceDetails(invoiceDetails);
            if (resultDetails) {
                System.out.println("Thêm mới chi tiết hóa đơn thành công!");
            } else {
                System.out.println("Không thể thêm mới chi tiết hóa đơn!");
            }
        } else {
            System.out.println("Thêm mới hóa đơn không thành công!");
        }
    }

    public static void findInvoice() {
        boolean next = false;
        do {
            try {
                System.out.println("========== TÌM KIẾM HÓA ĐƠN ==========");
                System.out.println("1. Tìm theo tên khách hàng");
                System.out.println("2. Tìm theo ngày/tháng/năm");
                System.out.println("3. Quay lại menu hóa đơn");
                System.out.println("========================================");
                String choice = scanner.nextLine();
                if (ValidateInput.isInt(choice)) {
                    int option = Integer.parseInt(choice);
                    switch (option) {
                        case 1:
                            System.out.print("Nhập Tên khách hàng cần tìm kiếm: ");
                            String customerName = scanner.nextLine();

                            if (ValidateInput.isEmpty(customerName)) {
                                System.out.println("Tên khách hàng không được để trống.");
                            } else {
                                List<Invoice> invoiceList = invoiceService.getInvoicesByCustomer(customerName);
                                System.out.println(invoiceList);
                                if (invoiceList != null && invoiceList.isEmpty()) {
                                    System.out.println("Không tìm thấy sản phẩm nào với tên khách hàng này.");
                                } else {
                                    invoiceList.forEach(System.out::println);
                                }
                            }
                            break;
                        case 2:
                            System.out.println("Nhập vào ngày tháng cần tìm kiếm");
                            Invoice invoice = new Invoice();
                            LocalDate localDate = invoice.inputCreatedAt(scanner);

                            List<Invoice> invoiceList = invoiceService.getInvoicesByCreatedDate(localDate);
                            if (invoiceList != null && invoiceList.isEmpty()) {
                                System.out.println("Không tìm thấy hóa đơn nào với ngày tháng năm đã nhập!");
                            } else {
                                System.out.println("Hóa đơn với " + localDate);
                                invoiceList.forEach(System.out::println);
                            }
                            break;
                        case 3:
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
}
