package TheStack;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Stack<T> extends StackPrinter<T> implements I_stack<T> {

    private ArrayList <T> _stack;
    private int NetoSize = 0;
    private int BrutoSize = 0;
    private T number;
    private StackPrinter stackPrinter;
    private static String selectedType;

    public String getUserChoose() {
        return userChoose;
    }

    private String userChoose;

    public Stack(){
        this.stackSizeInsertion();
        this._stack = new ArrayList<>(BrutoSize);
        this.userChoose = this.userChoose();
    }

    public int getBrutoSize(){
        return this.BrutoSize;
    }

    public static Object selectStackType()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Select your stack type (1 - 4):\n");
        sb.append("1) String\n");
        sb.append("2) Integer\n");
        sb.append("3) Double\n");
        sb.append("4) Float\n");
        System.out.println(sb);
        Scanner sc = new Scanner(System.in);
        selectedType = sc.nextLine();

        Object StackType = null;

        switch (selectedType.toLowerCase()) {
            case "1":
                StackType = String.class;
                break;
            case "2":
                StackType = Integer.class;
                break;
            case "3":
                StackType = Double.class;
                break;
            case "4":
                StackType = Float.class;
                break;
            default:
                System.out.println("Unknown type. Try again!");
                selectStackType();
        }
        return StackType;
    }

    private void stackSizeInsertion()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter size of stack > 0:");
        String size = sc.nextLine();
        boolean isValid = false;

        while(!isValid)
        {
            if (tryParseInt(size) == true)
                if(this.BrutoSize > 0)
                    break;
            System.out.println("invalid input! Try again");
            size = sc.nextLine();
        }
    }

    private boolean tryParseInt(String size) {
        try
        {
            this.BrutoSize = Integer.parseInt(size);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void Pop() {
        if (!isEmpty())
        {
            ArrayList<T> _newStack = new ArrayList<>(NetoSize - 1);
            for (int index = 0; index < NetoSize - 1; ++index)
            {
                _newStack.add(_stack.get(index));
            }
            this.NetoSize--;
            this._stack = _newStack;
            System.out.println("After Pop:");
            this.PrintStack();
        }
    }

    @Override
    public T Top() {
        if (isEmpty())
            return null;
        return this._stack.get(NetoSize - 1);
    }

    private String userChoose() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Do you want to push by random ? y/other");
        String userInput = sc.nextLine();
        return userInput;
    }

    private Object randomInsertion()
    {
        Random rand = new Random();
        Object random = null;

        switch(selectedType)
        {
            case "1":
                String str = "ABCDEFGHIJKLMNOPQRSTUVWXY-abcdefghijklmnopqrstuvwxyz-0123456789_!@#$%^&*()+";
                StringBuilder sb = new StringBuilder();
                int length = rand.nextInt(1,8);
                for (int i = 0; i < length; i++) {
                    int index = rand.nextInt(str.length());
                    char randomChar = str.charAt(index);
                    sb.append(randomChar);
                }
                random = sb;
                break;
            case "2":
                random = rand.nextInt(1,8);
                break;
            case "3":
                random = Math.round(rand.nextDouble(1,2) * 1_000_000) / 1_000_000.0;// Output: 12.123456
                break;
            case "4":
                random = rand.nextFloat(1,2);
                break;
        }
        return random;
    }

    @Override
    public void Push(String userChoose) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        if (!isFull())
        {
            this.NetoSize++;
            if(this.userChoose.equals("y")) {
                this.number = (T) this.randomInsertion();
            }
            else {
                System.out.println("Enter an element to insert trough stack: ");
                userInput = sc.nextLine();
                this.number = convertToGeneric(userInput);
            }
            this._stack.add(this.number);
            if(!this.userChoose.equals("y"))
                System.out.println("Push success...");
        }
        else {
            System.out.println("Do you want to Pop first element? y/other");
            userInput = sc.nextLine();
            if (Objects.equals(userInput, "y")){
                this.Pop();
                this.Push(this.userChoose);
            }
        }
    }

    @Override
    public void PrintStack() {
        if (!isEmpty())
            stackPrinter.printStack(this._stack);
    }

    @Override
    public boolean isEmpty() {
        if (NetoSize == 0 || BrutoSize == 0)
        {
            System.out.println("📦 Stack is empty.");
            return true;
        }
        return false;
    }

    @Override
    public boolean isFull() {
        if (NetoSize == BrutoSize)
        {
            System.out.println("Stack is full!");
            return true;
        }
        return false;
    }

    private T convertToGeneric(Object o)
    {
        boolean isValid = false;

        while(!isValid) {
            try {
                T rv = (T) o;
                return rv;
            } catch (ClassCastException e) {
                System.out.println("Conversion error:" + e.getMessage() + "\nTry again:");
                Scanner sc = new Scanner(System.in);
                String userInput = sc.nextLine();
            }
        }
        return null;
    }
}