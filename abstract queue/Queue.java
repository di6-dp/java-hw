public interface Queue {
    void enqueue(final int element);
    int element();
    int dequeue();

    int size();
    boolean isEmpty();
    void clear();

    int[] toArray();
}
