package ra.MD4Project.dao.impl;

import ra.MD4Project.dao.ICustomerDAO;
import ra.MD4Project.model.Customer;
import ra.MD4Project.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {

    @Override
    public List<Customer> getAllCustomersInformation() {
        String sql = "{CALL get_all_customer_information()}";
        return DBHelper.executeQuery(sql, rs -> {
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                customers.add(customer);
            }
            return customers;
        });
    }

    @Override
    public boolean addCustomer(Customer customer) {
        String sql = "{CALL add_customer(?,?,?,?)}";
        return DBHelper.executeUpdate(sql,
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        String sql = "{CALL update_customer(?,?,?,?,?)}";
        return DBHelper.executeUpdate(sql,
                customer.getId(),
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress()
        );
    }

    @Override
    public boolean deleteCustomer(int id) {
        String sql = "{CALL delete_customer(?)}";
        return DBHelper.executeUpdate(sql, id);
    }

    @Override
    public Customer getCustomerById(int id) {
        String sql = "{CALL find_customer_id(?)}";
        return DBHelper.executeQuery(sql, rs -> {
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
            return null;
        }, id);
    }

    @Override
    public Customer getCustomerByName(String name) {
        String sql = "{CALL find_customer_name(?)}";
        return DBHelper.executeQuery(sql, rs -> {
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
            return null;
        }, name);
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        String sql = "{CALL find_customer_email(?)}";
        return DBHelper.executeQuery(sql, rs -> {
            if (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setAddress(rs.getString("address"));
                return customer;
            }
            return null;
        }, email);
    }
}
