public interface StackInterface<T> {
    public boolean isEmpty();
    public T pop() throws java.util.EmptyStackException;
    public void push(T n);
    public void clear();
}