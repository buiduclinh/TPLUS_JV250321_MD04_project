package ra.MD4Project.business.impl;

import ra.MD4Project.business.IInvoiceDetailsService;
import ra.MD4Project.dao.IInvoiceDetailsDAO;
import ra.MD4Project.dao.impl.InvoiceDetailsDAOImpl;
import ra.MD4Project.model.InvoiceDetails;

public class InvoiceDetailsServiceImpl implements IInvoiceDetailsService {
    private IInvoiceDetailsDAO invoiceDetailsDAO;

    public InvoiceDetailsServiceImpl() {
        invoiceDetailsDAO = new InvoiceDetailsDAOImpl();
    }

    @Override
    public boolean addInvoiceDetails(InvoiceDetails invoiceDetails) {
        return invoiceDetailsDAO.addInvoiceDetails(invoiceDetails);
    }
}
