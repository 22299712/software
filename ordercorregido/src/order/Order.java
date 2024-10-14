package order;

import java.util.List;
import java.util.ArrayList;

/**
 * Enum para representar los tipos de clientes.
 */
enum CustomerType {
    REGULAR, VIP
}

/**
 * Clase para calcular impuestos.
 */
class TaxCalculator {

    /**
     * Calcula el impuesto basado en la cantidad total.
     * 
     * @param totalAmount La cantidad total sobre la que se calcularán los impuestos.
     * @return El valor de los impuestos.
     */
    public double calculateTax(double totalAmount) {
        return totalAmount * 0.15;
    }
}

/**
 * Clase para calcular descuentos.
 */
class DiscountCalculator {

    /**
     * Aplica un descuento basado en el tipo de cliente y la cantidad total.
     * 
     * @param customerType El tipo de cliente (REGULAR o VIP).
     * @param totalAmount La cantidad total para aplicar el descuento.
     * @return El valor del descuento aplicado.
     */
    public double applyDiscount(CustomerType customerType, double totalAmount) {
        double discount = 0;
        if (customerType == CustomerType.REGULAR) {
            if (totalAmount > 100) {
                discount = totalAmount * 0.05;
            }
            if (totalAmount > 500) {
                discount = totalAmount * 0.1;
            }
        } else if (customerType == CustomerType.VIP) {
            if (totalAmount > 100) {
                discount = totalAmount * 0.1;
            }
            if (totalAmount > 500) {
                discount = totalAmount * 0.15;
            }
        }
        if (totalAmount > 1000) {
            discount = totalAmount * 0.2;
        }
        return discount;
    }
}

/**
 * Clase que representa un pedido.
 */
public class Order {
    public String customerName;
    public CustomerType customerType;
    public List<String> items;
    public double totalAmount;
    public double discount;
    public double tax;

    private DiscountCalculator discountCalculator;
    private TaxCalculator taxCalculator;

    /**
     * Constructor de la clase Order.
     * 
     * @param customerName Nombre del cliente.
     * @param customerType Tipo de cliente.
     * @param items Lista de artículos.
     * @param totalAmount Cantidad total del pedido.
     */
    public Order(String customerName, CustomerType customerType, List<String> items, double totalAmount) {
        this.customerName = customerName;
        this.customerType = customerType;
        this.items = items;
        this.totalAmount = totalAmount;
        this.discountCalculator = new DiscountCalculator();
        this.taxCalculator = new TaxCalculator();
        this.discount = 0;
        this.tax = 0;
    }

    /**
     * Aplica el descuento basado en el tipo de cliente y la cantidad total.
     */
    public void applyDiscount() {
        this.discount = discountCalculator.applyDiscount(this.customerType, this.totalAmount);
    }

    /**
     * Calcula los impuestos sobre la cantidad total.
     */
    public void calculateTax() {
        this.tax = taxCalculator.calculateTax(this.totalAmount);
    }

    /**
     * Imprime los detalles del pedido.
     */
    public void printOrderDetails() {
        double finalAmount = this.totalAmount - this.discount + this.tax;
        System.out.println("Customer: " + this.customerName);
        System.out.println("Customer Type: " + this.customerType);
        System.out.println("Items: " + String.join(", ", this.items));
        System.out.println("Total Amount: " + this.totalAmount);
        System.out.println("Discount: " + this.discount);
        System.out.println("Tax: " + this.tax);
        System.out.println("Final Amount: " + finalAmount);
    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>();
        items.add("Item1");
        items.add("Item2");

        Order order = new Order("Alice", CustomerType.VIP, items, 1200);
        order.applyDiscount();
        order.calculateTax();
        order.printOrderDetails();
    }
}
