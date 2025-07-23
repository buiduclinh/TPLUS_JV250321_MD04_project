package ra.MD4Project.business;

import ra.MD4Project.model.Customer;

import java.util.List;

public interface ICustomerService {
    List<Customer> displayAllCustomers();
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    Customer findCustomerById(int id);
    Customer findCustomerByEmail(String email);
    Customer findCustomerByName(String name);
}
