/*
 * CSD201 - Group Exercise
 * Fall 2023 - FPT University
 * Group 4 - SE17D08
 * 
 * Shopping Cart
 * Implement a shopping cart for an e-commerce website using a doubly linked list.
 * Allow users to add and remove items and adjust quantities.
 * 
 * This is just a simple program to play with the doubly linked list data structure
 * and the underlying algorithm to manipulate the data structure.
 * This program does not have any data validation check.
 * 
 */

public class ShoppingCart {
    // Store the size of the list, instead of calculating the size every time
    int size = 0;
    Item head, tail;

    // Constructors
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
        size = 0;
        head = null;
        tail = null;
    }

    // Return the length of the linked list
    public int size() {
        return size;
    }

    // Add an item to the end of the linked list
    public void add(String name, int quantity) {
        if (isEmpty()) {
            // The added item is the first item
            head = new Item(name, quantity, null, null);
            tail = head;
        } else {
            // The added item is not the first item
            tail.setNext(new Item(name, quantity, tail, null));
            tail = tail.getNext();
        }

        size++;
    }

    // Return an item with a given zero-indexed position in the linked list
    public Item get(int position) {
        Item current = head;

        for (int i = 0; i < position && current != null; i++) {
            current = current.getNext();
        }

        return current;
    }

    // Remove an item with a given zero-indexed position in the linked list
    public Item remove(int position) {
        Item current = head;
        if (position < 0 || position >= size) {
            return null;
        }

        for (int i = 0; i < position && current != null; i++) {
            current = current.getNext();
        }

        if (current == head) {
            // The case of only one element
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.getNext();
                head.setPrevious(null);
            }
        } else if (current == tail) {
            tail = tail.getPrevious();
            tail.setNext(null);
        } else {
            current.getPrevious().setNext(current.getNext());
            current.getNext().setPrevious(current.getPrevious());
        }

        size--;
        return current;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        Item current = head;
        int position = 0;
        while (current != null) {
            sb.append("position=" + position + ", " + current + ".\n");
            position++;
            current = current.getNext();
        }

        if (position == 0) {
            sb.append("No item.");
        }

        return sb.toString();
    }

    public class Item {
        // Properties of an item
        private String name;
        private int quantity;

        // Pointers to previous and next nodes
        private Item previous;
        private Item next;

        // Constructor
        public Item(String name, int quantity, Item previous, Item next) {
            this.setName(name);
            this.setQuantity(quantity);
            this.setPrevious(previous);
            this.setNext(next);
        }

        // Getters and setters
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

        private void setPrevious(Item previous) {
            this.previous = previous;
        }

        public Item getNext() {
            return next;
        }

        private void setNext(Item next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "name='" + getName() + "', quantity=" + getQuantity();
        }
    }
}
