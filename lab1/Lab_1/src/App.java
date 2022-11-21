import java.util.Scanner;

public class App {
    public static void main(String args[]) {
      int sumOf; 
      Scanner sc = new Scanner(System.in);
      do {
        System.out.print("\033\143");
        System.out.print("Enter count of the first nums: ");
        sumOf = sc.nextInt();
      } while (sumOf <= 1);
      sc.close();
      Fibonacci.fib(sumOf);
    }
}

class Fibonacci{
    static void fib(int sumOf) 
    {
        int n0 = 1, n1 = 1;
        int sum = 0;

        for(int i = 1; i <= sumOf; i++){
            System.out.print(n0 + " ");
            int n2 = n0 + n1;
            sum += n0;
            n0 = n1;
            n1 = n2;
        }
        System.out.println();
        System.out.println("Sum of the first " + sumOf + " elems: " + sum);
    }
}
