import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> cartItems;

    // Constructor
    public Cart() {
        cartItems = new ArrayList<>();
    }

    // Add product to cart
    public void addProduct(Product product, int quantity) {

        // Check if product already exists in cart
        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // Add new product
        cartItems.add(new CartItem(product, quantity));
    }

    // Remove product from cart
    public void removeProduct(int productId) {

        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getProduct().getProductId() == productId) {
                cartItems.remove(i);
                System.out.println("Product removed successfully.");
                return;
            }
        }

        System.out.println("Product not found in cart.");
    }

    // Update product quantity
    public void updateQuantity(int productId, int newQuantity) {

        for (CartItem item : cartItems) {
            if (item.getProduct().getProductId() == productId) {
                item.setQuantity(newQuantity);
                System.out.println("Quantity updated successfully.");
                return;
            }
        }

        System.out.println("Product not found in cart.");
    }

    // Calculate total amount
    public double getTotalAmount() {

        double total = 0;

        for (CartItem item : cartItems) {
            total += item.getSubtotal();
        }

        return total;
    }

    // Display cart
    public void displayCart() {

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-10s %-10s%n",
                "ID", "Product", "Price", "Qty", "Subtotal");
        System.out.println("---------------------------------------------------------------");

        for (CartItem item : cartItems) {
            item.displayCartItem();
        }

        System.out.println("---------------------------------------------------------------");
        System.out.printf("Total Amount: ₹%.2f%n", getTotalAmount());
    }

    // Clear cart
    public void clearCart() {
        cartItems.clear();
    }

    // Check if cart is empty
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    // Getter
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }
}