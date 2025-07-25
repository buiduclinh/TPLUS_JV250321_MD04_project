package ra.MD4Project.dao.impl;

import ra.MD4Project.dao.IInvoiceDetailsDAO;
import ra.MD4Project.model.InvoiceDetails;
import ra.MD4Project.utils.JDBCUtil;

import java.sql.*;
import java.util.List;

public class InvoiceDetailsDAOImpl implements IInvoiceDetailsDAO {
    @Override
    public boolean addInvoiceDetails(List<InvoiceDetails> invoiceDetails) {
        String sql = "{CALL add_invoice_details(?,?,?)}";
        try (Connection connection = JDBCUtil.getInstance().getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            connection.setAutoCommit(false);
            for (InvoiceDetails invoiceDetail : invoiceDetails) {
                callableStatement.setInt(1, invoiceDetail.getInvoiceId());
                callableStatement.setInt(2, invoiceDetail.getProductId());
                callableStatement.setInt(3, invoiceDetail.getQuantity());
                callableStatement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                JDBCUtil.getInstance().getConnection().rollback();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        return false;
    }
}
