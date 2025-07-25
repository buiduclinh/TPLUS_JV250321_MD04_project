package ra.MD4Project.business.impl;

import ra.MD4Project.model.Phone;
import ra.MD4Project.business.IPhoneService;
import ra.MD4Project.dao.IPhoneDAO;
import ra.MD4Project.dao.impl.PhoneDAOImpl;

import java.util.List;


public class PhoneServiceImpl implements IPhoneService {
    private IPhoneDAO phoneDAO;

    public PhoneServiceImpl() {
        phoneDAO = new PhoneDAOImpl();
    }

    @Override
    public int getStockProductById(int id) {
        return phoneDAO.getStockProductById(id);
    }

    @Override
    public List<Phone> displayProduct() {
        return phoneDAO.getAllProduct();
    }

    @Override
    public boolean addProduct(Phone product) {
        return phoneDAO.addProduct(product);
    }

    @Override
    public boolean updateProduct(Phone product) {
        return phoneDAO.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(int id) {
        return phoneDAO.deleteProduct(id);
    }

    @Override
    public List<Phone> displayProductByBrand(String brand) {
        return phoneDAO.findAllProductByBrand(brand);
    }

    @Override
    public List<Phone> displayProductByPrice(float price1, float price2) {
        return phoneDAO.findAllProductByPrice(price1, price2);
    }

    @Override
    public List<Phone> displayProductByStock(int stock1, int stock2) {
        return phoneDAO.findAllProductByStock(stock1, stock2);
    }

    @Override
    public Phone findProductByName(String name) {
        return phoneDAO.findProductByName(name);
    }

    @Override
    public Phone findProductById(int id) {
        return phoneDAO.findProductById(id);
    }
}
