import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    private static void adjustQuantity(ShoppingCart cart) {
        System.out.println(cart);

        int position = -1;
        while (position < 0 || position >= cart.size()) {
            position = getNaturalInt("Input the position of the item to update quantity: ");
        }

        int newQuantity = getNaturalInt("Input new quantity: ");

        if (newQuantity == 0) {
            cart.remove(position);
        } else {
            cart.get(position).setQuantity(newQuantity);
        }
    }

    private static void removeItem(ShoppingCart cart) {
        System.out.println(cart);

        ShoppingCart.Item removedItem = cart.remove(getNaturalInt("Input position of the item to remove: "));
        if (removedItem == null) {
            System.out.println("Cannot remove the item with given index: item not found.");
            return;
        }

        System.out.println("Successfully removed item: " + removedItem + ".");
    }

    private static void addItem(ShoppingCart cart) {
        String name = input("Input name of item: ");
        int quantity = getNaturalInt("Input number of " + name + ": ");

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
}
