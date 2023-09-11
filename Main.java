import java.util.Scanner;

public class Main {
    /*
     * This class is is a simulation of a shopping cart, which is intended for purpose of 
     * providing a demonstration of the functions implemented in the ShoppingCart class,
     * with interactive menu and prompts.
     */ 

    private static final Scanner scanner = new Scanner(System.in);
    
    // A hard-coded string for menu
    private static final String ACTIONS = "Shopping Cart\n=============\n1. Print items\n2. Add item\n3. Remove item\n4. Adjust quantity\n5. Quit.\n";

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        boolean breakFlag = false;

        // Interactive menu
        while (!breakFlag) {
            System.out.println(ACTIONS);
            switch (getNaturalInt("Select an option: ")) {
                case 1 -> System.out.println(cart);
                case 2 -> addItem(cart);
                case 3 -> removeItem(cart);
                case 4 -> adjustQuantity(cart);
                case 5 -> breakFlag = true;
                // Case 50 FOR DEMO
                case 13 -> loadHardCodedSamples(cart);
                default -> System.out.println("Invalid option.");
            }

            input("Press enter to continue...");
        }
    }

    // Interactively adjust the quantity of an item in cart
    private static void adjustQuantity(ShoppingCart cart) {
        System.out.println(cart);
        if (cart.size() == 0) {
            return;
        }

        // Prompt the user to input item position and quantity
        int position = -1;
        while (position < 0 || position >= cart.size()) {
            position = getNaturalInt("Input the position of the item to update quantity (Max: " + (cart.size() - 1) + "): ");
        }

        int newQuantity = getNaturalInt("Input new quantity: ");

        // Remove the item if new quantity is zero, or else update it
        if (newQuantity == 0) {
            cart.remove(position);
        } else {
            cart.get(position).setQuantity(newQuantity);
        }
    }

    // Interactively remove an item from cart
    private static void removeItem(ShoppingCart cart) {
        System.out.println(cart);
        if (cart.size() == 0) {
            return;
        }

        ShoppingCart.Item removedItem = cart.remove(getNaturalInt("Input position of the item to remove (Max: " + (cart.size() - 1) + "): "));
        if (removedItem == null) {
            System.out.println("Cannot remove the item with given index: item not found.");
            return;
        }

        System.out.println("Successfully removed item: " + removedItem + ".");
    }

    // Interactively add an item to the cart
    private static void addItem(ShoppingCart cart) {
        String name = input("Input name of item: ");
        int quantity = getNaturalInt("Input number of '" + name + "'': ");

        cart.add(name, quantity);
    }

    // Get an integer from stdin
    private static int getNaturalInt(String prompt) {
        int tmp;
        while (true) {
            try {
                tmp = Integer.parseInt(input(prompt).strip());
                if (tmp < 0) {
                    System.out.println("Please input a non-negative integer.");
                    continue;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Please input a valid Integer.");
                continue;
            }
            break;
        }

        return tmp;
    }

    // Pythonic input function
    private static String input(String prompt) {
        System.out.print(prompt);

        if (!scanner.hasNextLine()) {
            System.out.println("Error: End of input reached.");
            return null;
        }
        return scanner.nextLine();
    }

    // FOR DEMO PURPOSE ONLY
    // Add some hard-coded items to the cart
    private static void loadHardCodedSamples(ShoppingCart cart) {
        System.out.println("Adding some hard-coded sample items to cart...");

        cart.add("Apple iPhone 14 Pro Max", 2);
        cart.add("Apple AirTags", 8);
        cart.add("Apple Pro Display XDR", 1);
        cart.add("Apple Pro Stand", 1);
        cart.add("Apple Cider Vinegar", 5);
        cart.add("Apple Juice", 3);

        System.out.println("Items added to cart: ");
        System.out.println(cart);
    }
}
