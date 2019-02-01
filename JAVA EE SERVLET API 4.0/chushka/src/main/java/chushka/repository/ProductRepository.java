package chushka.repository;

import chushka.domain.entity.Product;

public interface ProductRepository extends GenericRepository<Product, String> {
     
    Product findByName(String name);
}
