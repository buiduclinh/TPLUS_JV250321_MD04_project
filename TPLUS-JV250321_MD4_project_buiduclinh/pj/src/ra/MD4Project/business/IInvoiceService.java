package ra.MD4Project.business;

import ra.MD4Project.model.Invoice;

import java.time.LocalDate;

import java.util.List;

public interface IInvoiceService {
    List<Invoice> getInvoices();
    int addInvoice(Invoice invoice);
    Invoice findInvoiceId(int id);
    List<Invoice> getInvoicesByCustomer(String customerName);
    List<Invoice> getInvoicesByCreatedDate(LocalDate date);
    List<Invoice> getTotalAmountByDay(LocalDate date,double amount);
    List<Invoice> getTotalAmountByMonth(LocalDate date,double amount);
    List<Invoice> getTotalAmountByYear(LocalDate date, double amount);
}
