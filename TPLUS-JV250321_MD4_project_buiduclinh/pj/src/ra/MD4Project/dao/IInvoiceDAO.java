package ra.MD4Project.dao;

import ra.MD4Project.model.Invoice;

import java.time.LocalDate;

import java.util.List;

public interface IInvoiceDAO {
    List<Invoice> getInvoices();

    int addInvoice(Invoice invoice);

    Invoice findInvoiceId(int id);

    List<Invoice> getInvoiceByCustomerName(String name);

    List<Invoice> getInvoiceByDate(LocalDate date);

    List<Invoice> getTotalAmountByDay(LocalDate date, double amount);

    List<Invoice> getTotalAmountByMonth(LocalDate date, double amount);

    List<Invoice> getTotalAmountByYear(LocalDate date, double amount);
}
