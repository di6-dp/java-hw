import java.util.Arrays;

public class ArrayQueue extends AbstractQueue{
    private final int multiplier;
    private int[] elements;
    private int head;
    private int tail;

    public ArrayQueue() {
        super();
        this.multiplier = capacity;
        this.elements = new int[capacity];
        this.head = 0;
        this.tail = 0;
    }

    @Override
    protected void increaseCapacity() {
        capacity *= multiplier;
        elements = Arrays.copyOf(elements, capacity);
    }

    @Override
    public void enqueue(int element) {
        if (size() == capacity) {
            increaseCapacity();
        }
        elements[tail] = element;
        tail++;
    }

    @Override
    public int element() {
        return elements[head];
    }

    @Override
    public int dequeue() {
        assert !isEmpty();
        int res = element();
        head++;
        return res;
    }

    @Override
    public int size() {
        if (head > tail) {
            return capacity - head + tail;
        }
        return tail - head;
    }

    @Override
    public void clear() {
        head = tail = 0;
        this.capacity = multiplier;
        elements = new int[capacity];
    }

    @Override
    public int[] toArray() {
        int[] res = new int[tail - head];
        for (int i = head; i < tail; i++) {
            res[i - head] = elements[i];
        }  return res;
    }

}
