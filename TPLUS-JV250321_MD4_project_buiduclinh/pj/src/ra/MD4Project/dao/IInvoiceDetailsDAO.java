package ra.MD4Project.dao;

import ra.MD4Project.model.InvoiceDetails;

import java.util.List;

public interface IInvoiceDetailsDAO {
    boolean addInvoiceDetails(List<InvoiceDetails> invoiceDetails);
}
