import java.util.InputMismatchException;

public class test {
    public static void main (String[] args) {
        int a = 1;
        int b = 0;

        try {
            System.out.print(a/b);
            System.out.print("A");
        }
        catch (ArithmeticException e){
            System.out.print("B");
        }
        catch(InputMismatchException e) {
            System.out.print("C");
        }
        finally {
            System.out.print("D");
        }

        System.out.print("E");
    }
    
}
