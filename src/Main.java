import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Product List
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(1, "Laptop", 55000, 10));
        products.add(new Product(2, "Mouse", 700, 50));
        products.add(new Product(3, "Keyboard", 1500, 30));
        products.add(new Product(4, "Monitor", 12000, 15));
        products.add(new Product(5, "Headphones", 2500, 20));

        Cart cart = new Cart();

        int choice;

        do {

            System.out.println("\n=========================================");
            System.out.println("      CART MANAGEMENT SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. Update Product Quantity");
            System.out.println("5. View Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.println("\n========== AVAILABLE PRODUCTS ==========");

                    for (Product product : products) {
                        product.displayProduct();
                    }

                    break;

                case 2:

                    System.out.print("\nEnter Product ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    Product selectedProduct = null;

                    for (Product product : products) {

                        if (product.getProductId() == id) {

                            selectedProduct = product;
                            break;
                        }
                    }

                    if (selectedProduct != null) {

                        if (qty <= 0) {

                            System.out.println("Quantity must be greater than zero.");
                        }

                        else if (qty <= selectedProduct.getStock()) {

                            cart.addProduct(selectedProduct, qty);

                            selectedProduct.setStock(
                                    selectedProduct.getStock() - qty);

                        }

                        else {

                            System.out.println("Insufficient Stock.");
                        }

                    }

                    else {

                        System.out.println("Product Not Found.");
                    }

                    break;

                case 3:

                    System.out.print("\nEnter Product ID to Remove: ");

                    int removeId = sc.nextInt();

                    cart.removeProduct(removeId);

                    break;

                case 4:

                    System.out.print("\nEnter Product ID: ");

                    int updateId = sc.nextInt();

                    System.out.print("Enter New Quantity: ");

                    int newQty = sc.nextInt();

                    cart.updateQuantity(updateId, newQty);

                    break;

                case 5:

                    cart.displayCart();

                    break;

                case 6:

                    if (cart.isEmpty()) {

                        System.out.println("\nCart is Empty.");

                    } else {

                        System.out.print("\nDo you have a coupon? (yes/no): ");
                        String answer = sc.next();

                        if (answer.equalsIgnoreCase("yes")) {

                            System.out.print("Enter Coupon Code: ");
                            String coupon = sc.next();

                            boolean applied = cart.applyCoupon(coupon);

                            if (applied) {
                                System.out.println("Coupon Applied Successfully!");
                            } else {
                                System.out.println("Coupon could not be applied.");
                            }
                        }

                        System.out.println("\n==============================================");
                        System.out.println("                FINAL BILL");
                        System.out.println("==============================================");

                        cart.displayCart();

                        System.out.println("----------------------------------------------");
                        System.out.printf("Subtotal        : ₹%.2f%n",
                                cart.getTotalAmount());

                        if (!cart.getAppliedCoupon().isEmpty()) {

                            System.out.println("Coupon Applied  : "
                                    + cart.getAppliedCoupon());

                            System.out.printf("Discount        : -₹%.2f%n",
                                    cart.getDiscount());

                        } else {

                            System.out.println("Coupon Applied  : None");
                            System.out.printf("Discount        : ₹%.2f%n", 0.0);
                        }

                        System.out.println("----------------------------------------------");

                        System.out.printf("Amount Payable  : ₹%.2f%n",
                                cart.getFinalAmount());

                        System.out.println("==============================================");

                        System.out.println("\nPayment Successful!");
                        System.out.println("Thank You For Shopping.");

                        cart.clearCart();
                    }

                    break;

                case 7:

                    System.out.println("\nThank You for using Cart Management System.");
                    break;

                default:

                    System.out.println("\nInvalid Choice. Please Try Again.");
            }

        } while (choice != 7);

        sc.close();
    }
}