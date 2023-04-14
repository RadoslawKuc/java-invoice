package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    int getNumber() {
        return products.size();
    }

    public String getStringFormatOfInvoice(){
        String result = "";
        Map<String, Integer> resultMap = new HashMap<>();
        int totalQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()){
            if (resultMap.containsKey(entry.getKey().getName() + ", " + entry.getKey().getPrice())){
                resultMap.put(entry.getKey().getName() + ", " + entry.getKey().getPrice(), resultMap.get(entry.getKey().getName() + ", " + entry.getKey().getPrice()) + entry.getValue());
            }else{
                resultMap.put(entry.getKey().getName() + ", " + entry.getKey().getPrice(), entry.getValue());
            }
            totalQuantity += entry.getValue();
        }

        for(Map.Entry<String, Integer> entry : resultMap.entrySet()){
            String temp = entry.getKey();
            String quantityProduct = String.valueOf(entry.getValue());
            int index = temp.indexOf(",");
            result += temp.substring(0, index+1) + " " + quantityProduct + ", " + temp.substring(index + quantityProduct.length() + 1) + "%n";

        }

        result += "Liczba pozycji: " + totalQuantity;
        return result;
    }


}
