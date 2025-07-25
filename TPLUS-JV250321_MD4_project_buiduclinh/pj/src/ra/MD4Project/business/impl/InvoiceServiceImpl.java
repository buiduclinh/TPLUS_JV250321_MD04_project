package ra.MD4Project.business.impl;

import ra.MD4Project.business.IInvoiceService;
import ra.MD4Project.dao.IInvoiceDAO;
import ra.MD4Project.dao.impl.InvoiceDAOImpl;
import ra.MD4Project.model.Invoice;
import ra.MD4Project.model.StatiticsInovice;
import ra.MD4Project.model.TempInvoiceDetail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class InvoiceServiceImpl implements IInvoiceService {
    private IInvoiceDAO invoiceDAO;


    @Override
    public Invoice findInvoiceId(int id) {
        return invoiceDAO.findInvoiceId(id);
    }

    @Override
    public List<Invoice> getInvoicesByCreatedDate(LocalDate date) {
        return invoiceDAO.getInvoiceByDate(date);
    }

    @Override
    public StatiticsInovice getTotalAmountByDay(int day, int month, int year) {
        return invoiceDAO.getTotalAmountByDay(day, month, year);
    }

    @Override
    public StatiticsInovice getTotalAmountByMonth(int month, int year) {
        return invoiceDAO.getTotalAmountByMonth(month, year);
    }

    @Override
    public StatiticsInovice getTotalAmountByYear(int year) {
        return invoiceDAO.getTotalAmountByYear(year);
    }

    public InvoiceServiceImpl() {
        invoiceDAO = new InvoiceDAOImpl();
    }

    @Override
    public List<Invoice> getInvoices() {
        return invoiceDAO.getInvoices();
    }

    @Override
    public int addInvoice(Invoice invoice) {
        return invoiceDAO.addInvoice(invoice);
    }

    @Override
    public List<Invoice> getInvoicesByCustomer(String customerName) {
        return invoiceDAO.getInvoiceByCustomerName(customerName);
    }

}
