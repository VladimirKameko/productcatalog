package by.pvt.service;

import by.pvt.Controller.CreateProductCmd;
import by.pvt.Controller.UpdateProductCmd;
import by.pvt.dto.ProductDto;
import by.pvt.pojo.ProductItem;
import by.pvt.repo.ProductCatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProductCatalogService {

    private static Logger log = Logger.getLogger("b.p.s.ProductCatalogService");

    @Autowired
    ProductCatalogRepository productCatalogRepository;

    public List<ProductDto> getProduct(int i) {
        List<ProductItem> productItems = productCatalogRepository.find(i);
        return productItems.stream()
                .map(productItem -> {
                    ProductDto dto = new ProductDto();
                    dto.id = productItem.getId();
                    dto.name = productItem.getName();
                    dto.price = productItem.getPrice();
                    dto.serialNumber = productItem.getSerialNumber();
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void create(CreateProductCmd cmd) {
        ProductItem item = new ProductItem();
        item.setId((long) (Math.random() * 100));
        item.setCreateDate(new Date());
        item.setName(cmd.getName());
        item.setPrice(cmd.getPrice());
        item.setSerialNumber("N/A");
        productCatalogRepository.save(item);
    }

    public void update(long id, UpdateProductCmd updateProductCmd) {
        ProductItem item = productCatalogRepository.load(id);
        if (item == null) {
            log.warning("No such item in product catalog ID=" + id);
            return;
        }
        ;
        item.setSerialNumber(updateProductCmd.getSerialNumber());
        productCatalogRepository.update(item);
    }

    public ProductDto get(long id) {
        ProductItem item=productCatalogRepository.load(id);
        ProductDto dto = new ProductDto();
        dto.id=item.getId();
        dto.serialNumber=item.getSerialNumber();
        dto.price=item.getPrice();
        dto.name= item.getName();
        return dto;
    }
}
