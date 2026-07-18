public class CartItem {

    private Product product;
    private int quantity;

    // Default Constructor
    public CartItem() {
    }

    // Parameterized Constructor
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculate subtotal
    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    // Display cart item
    public void displayCartItem() {
        System.out.printf("%-5d %-20s %-10.2f %-10d %-10.2f%n",
                product.getProductId(),
                product.getProductName(),
                product.getPrice(),
                quantity,
                getSubtotal());
    }
}