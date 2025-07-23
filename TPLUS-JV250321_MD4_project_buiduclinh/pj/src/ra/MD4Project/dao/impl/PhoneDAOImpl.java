package ra.MD4Project.dao.impl;

import ra.MD4Project.model.Phone;
import ra.MD4Project.dao.IPhoneDAO;
import ra.MD4Project.utils.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class PhoneDAOImpl implements IPhoneDAO {

    @Override
    public List<Phone> getAllProduct() {
        String sql = "{Call get_all_product()}";
        return DBHelper.executeQuery(sql, rs -> {
            List<Phone> phones = new ArrayList<>();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setBrand(rs.getString("brand"));
                phone.setPrice(rs.getFloat("price"));
                phone.setStock(rs.getInt("stock"));
                phones.add(phone);
            }
            return phones;
        });
    }


    @Override
    public boolean addProduct(Phone product) {
        String sql = "{CALL add_product(?,?,?)}";
        return DBHelper.executeUpdate(sql,
                product.getName(),
                product.getBrand(),
                product.getPrice()
        );
    }

    @Override
    public boolean updateProduct(Phone product) {
        String sql = "{CALL update_product(?,?,?,?,?)}";
        return DBHelper.executeUpdate(sql,
                product.getId(),
                product.getName(),
                product.getBrand(),
                product.getPrice(),
                product.getStock()
        );
    }

    @Override
    public boolean deleteProduct(int id) {
        String sql = "{CALL delete_product(?)}";
        return DBHelper.executeUpdate(sql, id
        );
    }

    @Override
    public List<Phone> findAllProductByBrand(String brand) {
        String sql = "{CALL find_product_by_brand(?)}";
        return DBHelper.executeQuery(sql, rs -> {
            List<Phone> phones = new ArrayList<>();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setBrand(rs.getString("brand"));
                phone.setPrice(rs.getFloat("price"));
                phone.setStock(rs.getInt("stock"));
                phones.add(phone);
            }
            return phones;
        }, brand);
    }

    @Override
    public List<Phone> findAllProductByPrice(float price1, float price2) {
        String sql = "{CALL find_product_by_price(?,?)}";
        return DBHelper.executeQuery(sql, rs -> {
            List<Phone> phones = new ArrayList<>();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setBrand(rs.getString("brand"));
                phone.setPrice(rs.getFloat("price"));
                phone.setStock(rs.getInt("stock"));
                phones.add(phone);
            }
            return phones;
        }, price1, price2);
    }

    @Override
    public List<Phone> findAllProductByStock(int stock1, int stock2) {
        String sql = "{CALL find_product_by_stock(?,?)}";
        return DBHelper.executeQuery(sql, rs -> {
            List<Phone> phones = new ArrayList<>();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setBrand(rs.getString("brand"));
                phone.setPrice(rs.getFloat("price"));
                phone.setStock(rs.getInt("stock"));
                phones.add(phone);
            }
            return phones;
        }, stock1, stock2);
    }

    @Override
    public Phone findProductById(int id) {
        String sql = "{CALL find_product_by_id(?)}";
        return DBHelper.executeQuery(sql,rs -> {
            if(rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setBrand(rs.getString("brand"));
                phone.setPrice(rs.getFloat("price"));
                phone.setStock(rs.getInt("stock"));
                return phone;
            }
            return null;
        },id);
    }

    @Override
    public Phone findProductByName(String name) {
        String sql = "{CALL find_product_by_name(?)}";
        return DBHelper.executeQuery(sql,rs -> {
            if(rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id"));
                phone.setName(rs.getString("name"));
                phone.setBrand(rs.getString("brand"));
                phone.setPrice(rs.getFloat("price"));
                phone.setStock(rs.getInt("stock"));
                return phone;
            }
            return null;
        },name);
    }
}
