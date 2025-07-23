package ra.MD4Project.business.impl;

import ra.MD4Project.business.ICustomerService;
import ra.MD4Project.dao.ICustomerDAO;
import ra.MD4Project.dao.impl.CustomerDAOImpl;
import ra.MD4Project.model.Customer;

import java.util.List;

public class CustomerServiceImpl implements ICustomerService {

    private ICustomerDAO customerDAO;
    public CustomerServiceImpl() {
        customerDAO = new CustomerDAOImpl();
    }

    @Override
    public List<Customer> displayAllCustomers() {
        return customerDAO.getAllCustomersInformation();
    }

    @Override
    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return customerDAO.updateCustomer(customer);
    }

    @Override
    public boolean deleteCustomer(int id) {
        return customerDAO.deleteCustomer(id);
    }

    @Override
    public Customer findCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerDAO.getCustomerByEmail(email);
    }

    @Override
    public Customer findCustomerByName(String name) {
        return customerDAO.getCustomerByName(name);
    }
}
