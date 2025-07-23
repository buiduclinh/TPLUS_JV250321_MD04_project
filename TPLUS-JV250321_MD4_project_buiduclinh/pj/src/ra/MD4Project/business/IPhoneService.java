package ra.MD4Project.business;


import ra.MD4Project.model.Phone;

import java.util.List;

public interface IPhoneService {
    List<Phone> displayProduct();
    boolean addProduct(Phone product);
    boolean updateProduct(Phone product);
    boolean deleteProduct(int id);
    List<Phone> displayProductByBrand(String brand);
    List<Phone> displayProductByPrice(float price1, float price2);
    List<Phone> displayProductByStock(int stock1, int stock2);
    Phone findProductById(int id);
    Phone findProductByName(String name);
}
