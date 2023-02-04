package com.example.demo.services;


import com.example.demo.models.OrderInfo;
import com.example.demo.models.Product;
import com.example.demo.models.Receipt;


import java.util.List;
import java.util.Map;

public interface ReceiptService {
    List<Receipt> getAllReceipt(String name);

    Receipt addReceipt(OrderInfo orderInfo);
}
