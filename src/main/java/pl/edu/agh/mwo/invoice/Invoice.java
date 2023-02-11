package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    //private Collection<Product> products = new ArrayList<>();
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        // TODO: implement
        this.products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        // TODO: implement
        for (int i=0; i<quantity; i++){
            this.products.add(product);
        }
    }

    public BigDecimal getSubtotal() {

        if (products == null)
            return BigDecimal.ZERO;
        return this.products.stream()
                .map(product -> product.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getTax() {

        return BigDecimal.ZERO;
    }

    public BigDecimal getTotal() {

        return BigDecimal.ZERO;
    }
}
