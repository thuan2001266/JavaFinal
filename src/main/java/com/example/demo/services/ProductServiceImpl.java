package com.example.demo.services;

import com.example.demo.controllers.ProductController;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addProduct(Product product) {
        List<Product> products = repository.findByName(product.getName());
        if (products.size()>0) {
            return false;
        }
        LocalDate tempdate = LocalDate.now();
        product.setDate(tempdate.getYear()*10000+tempdate.getMonthValue()*100+tempdate.getDayOfMonth());
        repository.save(product);
        return true;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }

    @Override
    public boolean updateProduct(Map<String, String> body) {
        List<String> priceList = Arrays.asList(body.get("price").trim().split("\\s*,\\s*"));
        List<String> imageList = Arrays.asList(body.get("image").trim().split("\\s*,\\s*"));
        List<String> colorList = Arrays.asList(body.get("color").trim().split("\\s*,\\s*"));
        List<String> optionList = Arrays.asList(body.get("option").trim().split("\\s*,\\s*"));

        Optional<Product> product = repository.findById(Long.parseLong(body.get("id")));
//        logger.info("check  :  " +priceList+ " " +imageList+ " " + colorList);
        if (product.isPresent()) {
            Product e = product.get();
            e.setName(body.get("name"));
            e.setDiscount("");
            e.setImg(imageList);
            e.setModel(body.get("model"));
            e.setOptionToBuy(optionList);
            e.setColor(colorList);
            e.setPrice(priceList);
            e.setType(body.get("type"));
            logger.info(e.toString());
            repository.save(e);
            return true;
        }
        return false;
    }
}
