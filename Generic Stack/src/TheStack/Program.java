package TheStack;

public class Program<T> extends Stack<T>{
    public static <StackType> void main(String[] args)  {

        Object StackType = selectStackType();
        Stack<StackType> DeStack= new Stack<>();
        for(int i = 0; i <= DeStack.getBrutoSize(); ++i){
            DeStack.Push(DeStack.getUserChoose());
        }
        DeStack.PrintStack();
    }
}

