package com.iangregson.algorithms.datastructures.linkedlist;

@SuppressWarnings("unchecked")
public class DoublyLinkedList<T> implements Iterable<T> {

  private int size = 0;
  private Node head = null;
  private Node tail = null;

  public DoublyLinkedList() {}

  // Need a Node class to hold data and previous and next pointers
  private class Node<T> {
    T data;
    Node <T> prev, next;

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