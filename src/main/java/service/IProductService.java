package service;

import model.Product;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    List<Product> findAll();

    Product findById(int productId);

    List<Product> searchByKey(String key);
}
