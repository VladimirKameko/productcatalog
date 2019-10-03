package by.pvt;

import by.pvt.service.ProductCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ProductCatalogApplication {
        @Autowired
        ProductCatalogService productCatalogService;
    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogApplication.class,args);
    }
}
