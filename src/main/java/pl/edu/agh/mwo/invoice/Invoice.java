package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<>();

    Invoice(){}

    Invoice(final Collection<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        if (product != null){
            products.add(product);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void addProduct(Product product, Integer quantity) {
        if (quantity <= 0){
            throw new IllegalArgumentException();
        }else {
            for (int i = 0; i < quantity; i++){
                products.add(product);
            }
        }
    }

    public BigDecimal getSubtotal() {
        if (products == null){
            return BigDecimal.ZERO;
        }else{
            BigDecimal sum = new BigDecimal("0");
            for (Product p : products){
                sum = sum.add(p.getPrice());
            }
            return sum;
        }
    }

    public BigDecimal getTax() {
        BigDecimal sumTax = new BigDecimal("0");
        if(products == null){
            return BigDecimal.ZERO;
        }else {
            for (Product p: products){
                sumTax = sumTax.add(p.getPriceWithTax().subtract(p.getPrice()));
            }
        }
        return sumTax;
    }

    public BigDecimal getTotal() {
        if (products == null){
            return BigDecimal.ZERO;
        }else {
            BigDecimal total = new BigDecimal("0");
            for (Product p : products) {
                total = total.add(p.getPriceWithTax());
            }
            return total;
        }
    }
}
