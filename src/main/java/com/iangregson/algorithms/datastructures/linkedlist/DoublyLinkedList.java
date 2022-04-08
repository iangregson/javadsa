package com.iangregson.algorithms.datastructures.linkedlist;

@SuppressWarnings("unchecked")
public class DoublyLinkedList<T> implements Iterable<T> {

  private int size = 0;
  private Node<T> head = null;
  private Node<T> tail = null;

  public DoublyLinkedList() {}

  // Need a Node class to hold data and previous and next pointers
  private static class Node<T> {
    private T data;
    private Node<T> prev, next;

    public Node(T data, Node<T> prev, Node<T> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    @Override
    public String toString() {
      return data.toString();
    }
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public T removeFirst() {
    if (isEmpty()) {
      throw new RuntimeException("Can't remove from an empty list.");
    }
    // Extract the data at head, then reset the pointers to drop head
    T data = head.data;
    head = head.next;
    --size;

    // cleanup
    if (isEmpty()) {
      tail = null;
    } else {
      head.prev = null;
    }

    return data;
  }
  
  public T removeLast() {
    if (isEmpty()) {
      throw new RuntimeException("Can't remove from an empty list.");
    }
    // Extract the data at tail, then reset the pointers to drop tail
    T data = tail.data;
    tail = tail.prev;
    --size;

    // cleanup
    if (isEmpty()) {
      head = null;
    } else {
      tail.next = null;
    }

    return data;
  }
  
  public T removeAt(int idx) {
    if (isEmpty()) {
      throw new RuntimeException("Can't remove from an empty list.");
    }

    // bounds check
    if (idx >= size || idx < 0) {
      return null;
    }

    // if idx is zero, just do removeFirst
    if (idx == 0) {
      return removeFirst();
    }
    
    // if idx is the last, just removeLast
    if (idx == size - 1) {
      return removeLast();
    }

    // if idx is somewhere in between, setup a pointer to head and walk
    // to the element before the position we want
    Node<T> temp = head;
    for (int i = 0; i < idx; i++) {
      temp = temp.next;
    }
    // now our pointer is right, extract the data, then swap the pointers
    T data = temp.data;
    temp.next.prev = temp.prev;
    temp.prev.next = temp.next;
    --size;

    return data;
  }

  public boolean remove(Object element) {
    int idx = indexOf(element);
    if (idx == -1) {
      return false;
    }
    removeAt(idx);
    return true;
  }

  public int indexOf(Object element) {
    int foundIdx = -1;
    int idx = 0;

    // null search
    if (element == null) {
      Node<T> node = head;
      while (node != null) {
        if (node.data == null) {
          foundIdx = idx;
          break;
        }
        node = node.next;
        idx++;
      }
    } else {
      Node<T> node = head;
      while (node != null) {
        if (element.equals(node.data)) {
          foundIdx = idx;
          break;
        }
        node = node.next;
        idx++;
      }
    }
    return foundIdx;
  }

  public T peekFirst() {
    if (isEmpty()) {
      throw new RuntimeException("Can't peek at an empty list.");
    }
    return head.data;
  }
  
  public T peekLast() {
    if (isEmpty()) {
      throw new RuntimeException("Can't peek at an empty list.");
    }
    return tail.data;
  }

  public void addFirst(T element) {
    if (isEmpty()) {
      // make a new node with no prev or last nodes
      Node<T> node = new Node<T>(element, null, null);
      // the head and tail of the list are now pointing to this node
      head = tail = node;
    } else {
      // make a new node with no prev node whose next node is the current head
      Node<T> node = new Node<T>(element, null, head);
      head.prev = node;
      // the head becomes the prev pointer on the current head
      head = head.prev;
    }
    // either way our size increases by one
    size++;
  }

  public void add(T element) {
    addLast(element);
  }

  public void addLast(T element) {
    if (isEmpty()) {
      // make a new node with no prev or last nodes
      Node<T> node = new Node<T>(element, null, null);
      // the head and tail of the list are now pointing to this node
      head = tail = node;
    } else {
      // make a new node with no next node whose prev node is the current tail
      Node<T> node = new Node<T>(element, tail, null);
      tail.next = node;
      // the head becomes the prev pointer on the current head
      tail = tail.next;
    }
    // either way our size increases by one
    size++;
  }

  public void addAt(int idx, T element) {
    // bounds check
    if (idx < 0 || idx > size) {
      throw new IllegalArgumentException("idx is out of bounds for the size of the list.");
    }

    // if idx is zero, just do addFirst
    if (idx == 0) {
      addFirst(element);
      return;
    }
    
    // if idx is the last, just addLast
    if (idx == size) {
      addLast(element);
      return;
    }

    // if idx is somewhere in between, setup a pointer to head and walk
    // to the element before the position we want
    Node<T> temp = head;
    for (int i = 0; i < idx - 1; i++) {
      temp = temp.next;
    }
    // now our pointer is right, swap in the new node
    Node<T> newNode = new Node<T>(element, temp, temp.next);
    temp.next.prev = newNode;
    temp.next = newNode;
    
    // either way our size increases by one
    size++;
  }

  public void clear() {
    Node<T> node = head;
    // walk the whole list and null all the things
    while (node != null) {
      Node<T> next = node.next;
      node.prev = node.next = null;
      node.data = null;
      node = next;
    }
    head = tail = node = null;
    size = 0;
  }

  @Override
  public java.util.Iterator<T> iterator() {
    return new java.util.Iterator<T>() {
      private Node<T> trav = head;

      @Override
      public boolean hasNext() {
        return trav != null;
      }

      @Override
      public T next() {
        T data = trav.data;
        trav = trav.next;
        return data;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    Node<T> trav = head;
    while (trav != null) {
      sb.append(trav.data);
      if (trav.next != null) {
        sb.append(", ");
      }
      trav = trav.next;
    }
    sb.append(" ]");
    return sb.toString();
  }
}