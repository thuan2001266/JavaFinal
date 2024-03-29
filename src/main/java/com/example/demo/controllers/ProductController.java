package com.example.demo.controllers;

import com.example.demo.models.OrderInfo;
import com.example.demo.models.Product;
import com.example.demo.models.ReturnValue;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.ReceiptRepository;
import com.example.demo.services.ProductService;
import com.example.demo.services.ReceiptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = "http://botzone.vercel.app")
public class ProductController {

    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    ReturnValue getAllProducts() {
        return new ReturnValue(1, "success", repository.findAll());
    }

    @GetMapping("/receipt/{name}")
    ReturnValue getAllRecepit(@PathVariable String name) {
        return new ReturnValue(1, "success", receiptService.getAllReceipt(name));
    }

    @PostMapping(
            value = "/addReceipt", consumes = "application/json", produces = "application/json")
    ReturnValue addReceipt(@RequestBody OrderInfo orderInfo) {
        return new ReturnValue(1, "success", receiptService.addReceipt(orderInfo));
    }

    @GetMapping("/product/search/{text}")
    @CrossOrigin(origins = "http://localhost:3000")
    ReturnValue getProductByText(@PathVariable String text) {
        List<Product> findByText = repository.findByNameContains(text);
        if (findByText.size() > 0) {
            return new ReturnValue(1, "success", findByText);
        } else {
            return new ReturnValue(0, "fail to get product by key word "+text, "");
        }
    }

    @PostMapping("/product/cart")
    ReturnValue getCart(@RequestParam Map<String, String> body) {
        String[] pID = body.get("listCart").replace("[","").replace("]","").replace("\"","").split(",");
        List<Product> findById = new ArrayList<>();
        logger.info(body.get("listCart"));
        if (pID.length > 0) {
            for (String p : pID)
            {
                findById.add(repository.findById(Long.parseLong(p)).stream().toList().get(0));
            }
        }
        logger.info(findById.toString());
        if (findById.size() > 0) {
            return new ReturnValue(1, "success", findById);
        } else {
            return new ReturnValue(0, "fail to get products cart", "");
        }
    }

    @GetMapping("/product/{id}")
    ReturnValue getProductById(@PathVariable Long id) {
        Optional<Product> findById = repository.findById(id);
        if (findById.isPresent()) {
            return new ReturnValue(1, "success", findById.stream().toList());
        } else {
            return new ReturnValue(0, "fail to get product by id "+id, "");
        }
    }

    @GetMapping("/product/type/{type}")
    ReturnValue getProductByType(@PathVariable String type) {
        List<Product> findByType = repository.findByType(type);
        if (findByType.size()>0) {
            return new ReturnValue(1, "success", findByType);
        } else {
            return new ReturnValue(0, "fail to get product by type "+type, "");
        }
    }

    @PostMapping("/manage/createProduct")
//    @CrossOrigin(origins = "http://localhost:3000")
    ReturnValue addProduct(@RequestParam Map<String, String> body) { //@RequestBody Product product
//        if (body.get("name")== "" || body.get("price")=="" || body.get("image")==""|| body.get("model")==""|| body.get("color")==""|| body.get("type")=="" || body.get("option")=="") {
//            return new ReturnValue(0, "Vui lòng điền đầy đủ thông tin sản phẩm!", "");
//        }
        logger.info(body.toString());
        List<Product> products = repository.findByName(body.get("name"));
        if (products.size()>0) {
            return new ReturnValue(0, "Tên sản phẩm bị trùng!", "");
        }
        List<String> priceList = Arrays.asList(body.get("price").trim().split("\\s*,\\s*"));
        List<String> imageList = Arrays.asList(body.get("image").trim().split("\\s*,\\s*"));
        List<String> colorList = Arrays.asList(body.get("color").trim().split("\\s*,\\s*"));
        List<String> optionList = Arrays.asList(body.get("option").trim().split("\\s*,\\s*"));
//        if (priceList.size()!=optionList.size()) {
//            return new ReturnValue(0, "Danh sách giá tiền và danh sách cấu hình không đều nhau!", "");
//        }
//        if (colorList.size() != imageList.size()) {
//            return new ReturnValue(0, "Danh sách màu sắc và danh sách hình ảnh không đều nhau!", "");
//        }
//        LocalDate tempdate = LocalDate.now();
        productService.addProduct(new Product(body.get("name"),priceList, colorList, imageList, optionList, "",Long.parseLong(body.get("date")), body.get("type"), body.get("model"))); //tempdate.getYear()*10000+tempdate.getMonthValue()*100+tempdate.getDayOfMonth()tempdate.getYear()*10000+tempdate.getMonthValue()*100+tempdate.getDayOfMonth()
//        return new ReturnValue(1, "Thêm sản phẩm thành công!", ""); //repository.save(product)
        return getAllProducts();
    }

    @PostMapping("/manage/updateProduct")
//    @CrossOrigin(origins = "http://localhost:3000")
    ReturnValue updateProduct(@RequestParam Map<String, String> body) {
        logger.info(body.toString());
        try {
            deleteProduct(body);
            addProduct(body);
//            return new ReturnValue(1, "Chỉnh sửa sản phẩm thành công", "");
            return getAllProducts();
        } catch (Exception e) {
            return new ReturnValue(0, "Có lổi xảy ra"+e.toString(), "");
        }
//        if (productService.updateProduct(body)) {
//            new ReturnValue(1, "Chỉnh sửa sản phẩm thành công", "");
//        }
//        return new ReturnValue(0, "Có lổi xảy ra", "");
    }

    @PostMapping("/manage/deleteProduct")
    ReturnValue deleteProduct(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        boolean exists = repository.existsById(id);
//        logger.info(String.valueOf(exists));
        if(exists) {
            repository.deleteById(id);
//            return new ReturnValue(1, "Đã xóa sản phẩm với id "+ id, "");
            return getAllProducts();
        }
        return new ReturnValue(0, "Không thể xóa sản phẩm với id "+ id, "");
    }
}