import java.util.ArrayList;

class LinkedList<T> {
    private int numberOfNodes = 0; 
    private ListNode<T> front = null;
    
    // Returns true if the linked list has no nodes, or false otherwise.
    public boolean isEmpty() {
        return (front == null);
    }
    
    // Deletes all of the nodes in the linked list.
    // Note: ListNode objects will be automatically garbage collected by JVM.
    public void makeEmpty() {
        front = null;
        numberOfNodes = 0;
    }
    
    // Returns the number of nodes in the linked list
    public int size() {
        return numberOfNodes;
    }
    
    // Adds a node to the front of the linked list.
    public void addFront( T element ) {
        front = new ListNode<T>( element, front );
        numberOfNodes++;
    }
    
    // Returns a reference to the data in the first node, or null if the list is empty.
    public T peek() {
        if (isEmpty()) 
            return null;
        
        return front.getData();
    }
    
    // Removes a node from the front of the linked list (if there is one).
    // Returns a reference to the data in the first node, or null if the list is empty.
    @SuppressWarnings("unchecked")
    public T removeFront() {
        T tempData;
        
        if (isEmpty()) 
            return null;
        
        tempData = front.getData();
        front = front.getNext();
        numberOfNodes--;
        return tempData;
    }
    
    @SuppressWarnings("unchecked")
    public void removeEnd(T element) {
        ListNode<T> node=front;
        while(node.getNext() != null)
        {
            node = node.getNext();
        }
        node.setNext(new ListNode<T>((T)element, null));
    }
    
    // Return array filled with T objects
    @SuppressWarnings("unchecked")
    public ArrayList<T> getArray() {
        
        ArrayList<T> shapeArray=new ArrayList<T>();
        
        ListNode<T> node=front;
        while (node!=null)
        {
            shapeArray.add(node.getData());
            node = node.getNext();
        }
        
        return shapeArray;
    }
}