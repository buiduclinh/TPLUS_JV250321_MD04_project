package ra.MD4Project.dao.impl;

import ra.MD4Project.dao.IInvoiceDAO;
import ra.MD4Project.model.Invoice;
import ra.MD4Project.utils.DBHelper;
import ra.MD4Project.utils.JDBCUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements IInvoiceDAO {
    @Override
    public List<Invoice> getInvoices() {
        String sql = "{CALL get_all_invoice_information()}";
        return DBHelper.executeQuery(sql, rs -> {
            List<Invoice> invoiceList = new ArrayList<>();
            while (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                invoice.setCustomerId(rs.getInt("customer_id"));
                invoice.setCreatedAt(LocalDateTime.parse(rs.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                invoice.setTotalAmount(rs.getDouble("total_amount"));
                invoiceList.add(invoice);
            }
            return invoiceList;
        });
    }

    @Override
    public Invoice findInvoiceId(int id) {
        String sql = "{CALL find_invoice_id(?)}";
        return DBHelper.executeQuery(sql, rs -> {
            if (rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(id);
                return invoice;
            }
            return null;
        }, id);
    }

    @Override
    public int addInvoice(Invoice invoice) {
        String sql = "{CALL add_invoice(?,?)}";
        try (Connection connection = JDBCUtil.getInstance().getConnection();
             CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, invoice.getCustomerId());
            stmt.registerOutParameter(2, Types.INTEGER);
            stmt.executeUpdate();
            return stmt.getInt(2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Invoice> getInvoiceByCustomerName(String name) {
        String sql = "{CALL find_invoice_by_customer_name(?)}";
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection connection = JDBCUtil.getInstance().getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, name);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("invoice_id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                invoice.setCustomerName(resultSet.getString("customer_name"));
                invoice.setCreatedAt(LocalDateTime.parse(resultSet.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                invoice.setTotalAmount(resultSet.getDouble("total_amount"));
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    @Override
    public List<Invoice> getInvoiceByDate(LocalDate date) {
        String sql = "{CALL find_invoice_by_created_at(?)}";
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection connection = JDBCUtil.getInstance().getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("invoice_id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                invoice.setCustomerName(resultSet.getString("customer_name"));
                invoice.setCreatedAt(LocalDateTime.parse(resultSet.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                invoice.setTotalAmount(resultSet.getDouble("total_amount"));
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    @Override
    public List<Invoice> getTotalAmountByDay(LocalDate date, double amount) {
        String sql = "{CALL total_amount_date_by_day(?)}";
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection connection = JDBCUtil.getInstance().getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setDate(1, Date.valueOf(date));
            callableStatement.setDouble(2, amount);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("invoice_id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                invoice.setCustomerName(resultSet.getString("customer_name"));
                invoice.setCreatedAt(LocalDateTime.parse(resultSet.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                invoice.setTotalAmount(resultSet.getDouble("total_amount"));
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    @Override
    public List<Invoice> getTotalAmountByMonth(LocalDate date, double amount) {
        String sql = "{CALL total_amount_date_by_month(?)}";
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection connection = JDBCUtil.getInstance().getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setDate(1, Date.valueOf(date));
            callableStatement.setDouble(2, amount);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("invoice_id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                invoice.setCustomerName(resultSet.getString("customer_name"));
                invoice.setCreatedAt(LocalDateTime.parse(resultSet.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                invoice.setTotalAmount(resultSet.getDouble("total_amount"));
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    @Override
    public List<Invoice> getTotalAmountByYear(LocalDate date, double amount) {
        String sql = "{CALL total_amount_date_by_year(?)}";
        List<Invoice> invoiceList = new ArrayList<>();
        try (Connection connection = JDBCUtil.getInstance().getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setDate(1, Date.valueOf(date));
            callableStatement.setDouble(2, amount);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(resultSet.getInt("invoice_id"));
                invoice.setCustomerId(resultSet.getInt("customer_id"));
                invoice.setCustomerName(resultSet.getString("customer_name"));
                invoice.setCreatedAt(LocalDateTime.parse(resultSet.getString("created_at"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                invoice.setTotalAmount(resultSet.getDouble("total_amount"));
                invoiceList.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }
}
