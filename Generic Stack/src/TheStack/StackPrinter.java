package TheStack;
import java.util.ArrayList;
import java.util.List;

public class StackPrinter<T> {
    public static <T> void printStack(ArrayList<T> stack) {

        System.out.println("╔═══════════════╗");
        System.out.println("║   Stack (Top) ║");
        System.out.println("╠═══════════════╣");
        for (int i = stack.size() - 1; i >= 0; i--) {
            System.out.print("║");
            System.out.printf("    %s      ", stack.get(i));
            System.out.println();
        }
        System.out.println("╚═══════════════╝");
        int maxLength = 0;
        for (T item : stack) {
            maxLength = Math.max(maxLength, item.toString().length());
        }
    }
}
