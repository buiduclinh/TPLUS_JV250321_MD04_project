package ra.MD4Project.dao.impl;

import ra.MD4Project.dao.IInvoiceDetailsDAO;
import ra.MD4Project.model.Invoice;
import ra.MD4Project.model.InvoiceDetails;
import ra.MD4Project.utils.DBHelper;

public class InvoiceDetailsDAOImpl implements IInvoiceDetailsDAO {
    @Override
    public boolean addInvoiceDetails(InvoiceDetails invoiceDetails) {
        String sql = "{CALL add_invoice_details(?,?,?)}";
        return DBHelper.executeUpdate(sql,
                invoiceDetails.getInvoiceId(),
                invoiceDetails.getProductId(),
                invoiceDetails.getQuantity()
        );
    }
}
