package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

class MyLinkedList<T> implements Iterable<T>{
    Node<T> head,tail;
    int length;

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    static class Node<T> {
        T val;
        Node<T> next;

        Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }

    }

    public MyLinkedList() {

    }

    public T get(int index) {
        if (index < 0 || index >= length)
            return null;
        Node<T> p = head;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.val;
    }


    public void addAtHead(T val) {
        if (length == 0) {
            head = new Node<T>(val, null);
            tail = head;
        } else {
            head = new Node<T>(val, head);
        }
        length++;
    }

    public void addAtTail(T val) {
        if (length == 0) {
            head = new Node<T>(val, null);
            tail = head;
        } else {
            tail.next = new Node<T>(val, null);
            tail = tail.next;
        }
        length++;
    }


    public void addAtIndex(int index, T val) {
        if (index <= 0) {
            addAtHead(val);
        } else if (index == length) {
            addAtTail(val);
        } else if (index < length) {
            Node<T> p = head;
            for (int i = 0; i < index - 1; i++) {
                p = p.next;
            }
            p.next = new Node<T>(val, p.next);
            length++;
        }
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= length)
            return;
        if (index == 0) {
            head = head.next;
            if (length == 1)
                tail = head;
            length--;
            return;
        }
        Node<T> p = head;
        for (int i = 0; i < index - 1; i++) {
            p = p.next;
        }
        p.next = p.next.next;
        if (p.next == null)
            tail = p;
        length--;
    }

    private class MyLinkedListIterator implements Iterator<T> {
        private Node<T> cur = head;

        public boolean hasNext() {
            return cur != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> oldCur = cur;
            cur = cur.next;
            return oldCur.val;
        }
    }

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.addAtHead(5);
        list.addAtHead(3);
        for(Integer value: list) {
            System.out.println(value);
        }
    }
}
