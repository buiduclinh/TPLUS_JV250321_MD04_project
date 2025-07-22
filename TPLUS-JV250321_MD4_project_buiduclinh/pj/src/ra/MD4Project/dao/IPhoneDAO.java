package ra.MD4Project.dao;

import ra.MD4Project.Model.Phone;

import java.util.List;

public interface IPhoneDAO {
    List<Phone> getAllProduct();
    boolean addProduct(Phone product);
    boolean updateProduct(Phone product);
    boolean deleteProduct(int id);
    List<Phone> findAllProductByBrand(String brand);
    List<Phone> findAllProductByPrice(float price1, float price2);
    List<Phone> findAllProductByStock(int stock1, int stock2);
    Phone findProductById(int id);
    Phone findProductByName(String name);
}
