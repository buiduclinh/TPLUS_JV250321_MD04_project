package ra.MD4Project.business.impl;

import ra.MD4Project.business.IInvoiceService;
import ra.MD4Project.dao.IInvoiceDAO;
import ra.MD4Project.dao.impl.InvoiceDAOImpl;
import ra.MD4Project.model.Invoice;
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
    public List<Invoice> getTotalAmountByDay(LocalDate date, double amount) {
        return invoiceDAO.getTotalAmountByDay(date, amount);
    }

    @Override
    public List<Invoice> getTotalAmountByMonth(LocalDate date, double amount) {
        return invoiceDAO.getTotalAmountByMonth(date, amount);
    }

    @Override
    public List<Invoice> getTotalAmountByYear(LocalDate date, double amount) {
        return invoiceDAO.getTotalAmountByYear(date, amount);
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
