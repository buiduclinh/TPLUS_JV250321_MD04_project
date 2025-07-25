package ra.MD4Project.business;

import ra.MD4Project.model.InvoiceDetails;

import java.util.List;

public interface IInvoiceDetailsService {
    boolean addInvoiceDetails(List<InvoiceDetails> invoiceDetails);
}
