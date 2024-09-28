package TheStack;

import java.util.ArrayList;

public interface I_stack<T> {

    public void Pop();
    public T Top();
    public void Push(String userChoose);
    public void PrintStack();
    public boolean isEmpty();
    public boolean isFull();
}
