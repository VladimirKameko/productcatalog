package by.pvt.repo;

import by.pvt.pojo.ProductItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductCatalogRepository {

    static List<ProductItem> catalog = new ArrayList<>();

    public List<ProductItem> find(int i) {
        return catalog.stream()
                .limit(i)
                .collect(Collectors.toList());

    }

    public void save(ProductItem item) {
        catalog.add(item);
    }

    public ProductItem load(long id) {
        return catalog.stream().
                filter(productItem -> productItem.getId() == id)
                .findFirst()
                .get();
    }

    public void update(ProductItem item) {
        catalog.removeIf(productItem -> productItem.getId().equals(item.getId()));
        catalog.add(item);
    }
}
