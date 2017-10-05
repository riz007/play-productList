package models;


import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private static List<Product> products;

    static {
        products = new ArrayList<Product>();

        products.add(new Product("5102017000001", "Lalin 100mg", "Lalin 100mg description 1"));
        products.add(new Product("5102017000002", "Lalin 300mg", "Lalin 300mg description 2"));
        products.add(new Product("5102017000003", "Laoor", "Laoor description 3"));
        products.add(new Product("5102017000004", "OWhite", "OWhite description 4"));
        products.add(new Product("5102017000005", "Soap", "Soap description 5"));
    }

    @Constraints.Required
    public String ean;
    @Constraints.Required
    public String name;
    @Constraints.Required
    public String description;

    public Product(){}

    public Product(String ean, String name, String description) {
        this.ean = ean;
        this.name = name;
        this.description = description;
    }

    public String toString() {
        return String.format("%s - %s", ean, name);
    }

    public static List<Product> findAll() {
        return new ArrayList<Product>(products);
    }

    public static Product findByEan(String ean) {
        for(Product candidate : products) {
            if(candidate.ean.equals(ean)) {
                return candidate;
            }
        }
        return null;
    }

    public static List<Product> findByName(String term) {
        final List<Product> results = new ArrayList<Product>();
        for(Product candidate : products) {
            if(candidate.name.toLowerCase().contains(term.toLowerCase())) {
                results.add(candidate);
            }
        }

        return results;
    }

    public static boolean remove(Product product) {
        return products.remove(product);
    }

    public void save() {
        products.remove(findByEan(this.ean));
        products.add(this);
    }
}
