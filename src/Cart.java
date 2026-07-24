import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> cartItems;

    // Coupon Details
    private double discount;
    private String appliedCoupon;

    // Constructor
    public Cart() {
        cartItems = new ArrayList<>();
        discount = 0;
        appliedCoupon = "";
    }

    // Add Product
    public void addProduct(Product product, int quantity) {

        for (CartItem item : cartItems) {

            if (item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        cartItems.add(new CartItem(product, quantity));
    }

    // Remove Product
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

    // Update Quantity
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

    // Calculate Total
    public double getTotalAmount() {

        double total = 0;

        for (CartItem item : cartItems) {
            total += item.getSubtotal();
        }

        return total;
    }

    // Apply Coupon
    public boolean applyCoupon(String couponCode) {

        couponCode = couponCode.toUpperCase();

        // Reset previous coupon
        discount = 0;
        appliedCoupon = "";

        switch (couponCode) {

            case "SAVE10":
                discount = getTotalAmount() * 0.10;
                appliedCoupon = couponCode;
                return true;

            case "SAVE20":
                discount = getTotalAmount() * 0.20;
                appliedCoupon = couponCode;
                return true;

            case "FLAT500":
                discount = 500;

                // Prevent discount greater than total
                if (discount > getTotalAmount()) {
                    discount = getTotalAmount();
                }

                appliedCoupon = couponCode;
                return true;

            default:
                return false;
        }
    }

    // Discount Getter
    public double getDiscount() {
        return discount;
    }

    // Final Amount
    public double getFinalAmount() {
        return getTotalAmount() - discount;
    }

    // Applied Coupon Getter
    public String getAppliedCoupon() {
        return appliedCoupon;
    }

    // Display Cart
    public void displayCart() {

        if (cartItems.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-10s %-10s%n",
                "ID", "Product", "Price", "Qty", "Subtotal");
        System.out.println("------------------------------------------------------------------");

        for (CartItem item : cartItems) {
            item.displayCartItem();
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("Subtotal : ₹%.2f%n", getTotalAmount());

        if (!appliedCoupon.isEmpty()) {
            System.out.println("Coupon   : " + appliedCoupon);
            System.out.printf("Discount : -₹%.2f%n", discount);
            System.out.printf("Total    : ₹%.2f%n", getFinalAmount());
        }
    }

    // Clear Cart
    public void clearCart() {
        cartItems.clear();
        discount = 0;
        appliedCoupon = "";
    }

    // Check Empty
    public boolean isEmpty() {
        return cartItems.isEmpty();
    }

    // Getter
    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }
}