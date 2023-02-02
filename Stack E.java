public interface Stack<E> {

    public void add(E item);

    public E remove();

    public E peek();

    public boolean empty();
    
    public int size();
}