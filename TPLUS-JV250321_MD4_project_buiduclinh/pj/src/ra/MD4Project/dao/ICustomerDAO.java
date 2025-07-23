package ra.MD4Project.dao;

import ra.MD4Project.model.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomersInformation();
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    Customer getCustomerById(int id);
    Customer getCustomerByName(String name);
    Customer getCustomerByEmail(String email);
}
