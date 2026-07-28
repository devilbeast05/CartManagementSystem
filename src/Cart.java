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

                System.out.println("Quantity Updated Successfully.");

                return;
            }
        }

        cartItems.add(new CartItem(product, quantity));

        System.out.println("Product Added Successfully.");
    }

    // Remove Product
    public void removeProduct(int productId) {

        for (int i = 0; i < cartItems.size(); i++) {

            if (cartItems.get(i).getProduct().getProductId() == productId) {

                cartItems.remove(i);

                System.out.println("Product Removed Successfully.");

                return;
            }
        }

        System.out.println("Product Not Found In Cart.");
    }

    // Update Quantity
    public void updateQuantity(int productId, int newQuantity) {

        if (newQuantity <= 0) {
            System.out.println("Quantity must be greater than zero.");
            return;
        }

        for (CartItem item : cartItems) {

            if (item.getProduct().getProductId() == productId) {

                item.setQuantity(newQuantity);

                System.out.println("Quantity Updated Successfully.");

                return;
            }
        }

        System.out.println("Product Not Found In Cart.");
    }

    // Calculate Total Amount
    public double getTotalAmount() {

        double total = 0;

        for (CartItem item : cartItems) {
            total += item.getSubtotal();
        }

        return total;
    }

    // ==========================
    // APPLY COUPON
    // ==========================
    public boolean applyCoupon(String couponCode) {

        couponCode = couponCode.toUpperCase();

        // Reset previous coupon
        discount = 0;
        appliedCoupon = "";

        double total = getTotalAmount();

        switch (couponCode) {

            case "SAVE10":

                if (total < 1000) {
                    System.out.println("\nCoupon SAVE10 requires a minimum purchase of ₹1000.");
                    return false;
                }

                discount = total * 0.10;
                appliedCoupon = couponCode;

                return true;

            case "SAVE20":

                if (total < 5000) {
                    System.out.println("\nCoupon SAVE20 requires a minimum purchase of ₹5000.");
                    return false;
                }

                discount = total * 0.20;
                appliedCoupon = couponCode;

                return true;

            case "FLAT500":

                if (total < 3000) {
                    System.out.println("\nCoupon FLAT500 requires a minimum purchase of ₹3000.");
                    return false;
                }

                discount = 500;

                if (discount > total) {
                    discount = total;
                }

                appliedCoupon = couponCode;

                return true;

            default:

                System.out.println("\nInvalid Coupon Code.");

                return false;
        }
    }

    // Discount
    public double getDiscount() {
        return discount;
    }

    // Final Amount
    public double getFinalAmount() {
        return getTotalAmount() - discount;
    }

    // Applied Coupon
    public String getAppliedCoupon() {
        return appliedCoupon;
    }

    // Display Cart
    public void displayCart() {

        if (cartItems.isEmpty()) {

            System.out.println("\nCart is Empty.");

            return;
        }

        System.out.println("\n==============================================================");
        System.out.printf("%-5s %-20s %-10s %-8s %-12s%n",
                "ID", "Product", "Price", "Qty", "Subtotal");
        System.out.println("==============================================================");

        for (CartItem item : cartItems) {
            item.displayCartItem();
        }

        System.out.println("==============================================================");

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