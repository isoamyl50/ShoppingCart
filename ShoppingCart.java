/*
 * CSD201 - Group Exercise
 * Fall 2023 - FPT University
 * Group 4 - SE17D08
 * 
 * Shopping Cart
 * Implement a shopping cart for an e-comerce website using a doubly linked list.
 * Allow users to add and remove items and adjust quantities.
 * 
 * This is just a simple program to play with the doubly linked list data structure
 * and the underlying algorithm to manipulate the data structure.
 * This program does not have any data validation check.
 * 
 */

public class ShoppingCart {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.add(1, "Apple iPhone 14", 1);
        shoppingCart.add(2, "Apple AirTags", 4);
        shoppingCart.add(3, "Apple MacBook Air", 2);
        shoppingCart.add(4, "Apple Pro Display XDR", 1);
        shoppingCart.add(5, "Apple Pro Stand", 1);

        shoppingCart.remove(3);
        System.out.println(shoppingCart);
    }

    Item head, tail;

    public ShoppingCart() {
        clear();
    }

    public ShoppingCart(Item head, Item tail) {
        this.head = head;
        this.tail = tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void add(int id, String name, int quantity) {
        if (isEmpty()) {
            head = new Item(id, name, quantity, null, null);
            tail = head;
            return;
        }

        Item newNode = new Item(id, name, quantity, tail, null);
        tail.setNext(newNode);
        tail = newNode;
    }

    public boolean remove(int id) {
        Item current = head;

        while (current != null) {

            if (current.getId() == id) {
                if (current == head) {
                    head = head.getNext();
                    head.setPrevious(null);
                } else if (current == tail) {
                    tail = tail.getPrevious();
                    tail.setNext(null);
                } else {
                    current.getPrevious().setNext(current.getNext());
                    current.getNext().setPrevious(current.getPrevious());
                }
                return true;
            }

            current = current.getNext();
        }

        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        Item current = head;
        while (current != null) {
            sb.append("id=" + current.getId() + ", name=\'" + current.getName() + "\', quantity=" + current.getQuantity() + ".\n");
            current = current.getNext();
        }

        return sb.toString();
    }

    private class Item {
        // Properties of an item
        private int id;
        private String name;
        private int quantity;

        // Pointers to previous and next nodes
        private Item previous;
        private Item next;

        // Constructor
        public Item(int id, String name, int quantity, Item previous, Item next) {
            this.setId(id);
            this.setName(name);
            this.setQuantity(quantity);
            this.setPrevious(previous);
            this.setNext(next);
        }

        // Getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Item getPrevious() {
            return previous;
        }

        public void setPrevious(Item previous) {
            this.previous = previous;
        }

        public Item getNext() {
            return next;
        }

        public void setNext(Item next) {
            this.next = next;
        }
    }
}
