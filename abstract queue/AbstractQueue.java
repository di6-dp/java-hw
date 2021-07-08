public abstract class AbstractQueue implements Queue{
    protected int capacity;

    protected AbstractQueue() {
        this.capacity = 8;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    protected abstract void increaseCapacity();
}
