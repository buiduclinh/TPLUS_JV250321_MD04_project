package ra.MD4Project.dao;

import ra.MD4Project.model.Invoice;
import ra.MD4Project.model.StatiticsInovice;

import java.time.LocalDate;

import java.util.List;

public interface IInvoiceDAO {
    List<Invoice> getInvoices();

    int addInvoice(Invoice invoice);

    Invoice findInvoiceId(int id);

    List<Invoice> getInvoiceByCustomerName(String name);

    List<Invoice> getInvoiceByDate(LocalDate date);

    StatiticsInovice getTotalAmountByDay(int day, int month, int year);

    StatiticsInovice getTotalAmountByMonth(int month, int year);

    StatiticsInovice getTotalAmountByYear(int year);
}
