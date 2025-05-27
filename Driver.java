import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println("expecting 0: " + p.evaluate(3));
        
        double [] c1 = {6,2,2,5};
        int [] e1 = {0, 1, 2, 3};
        Polynomial p1 = new Polynomial(c1, e1);

        double [] c2 = {1,-2,1,1,-9};
        int [] e2 = {0, 1, 2, 3, 4};
        Polynomial p2 = new Polynomial(c2, e2);

        p1.print();
        p2.print();

        Polynomial s = p1.add(p2);
        s.print();
        System.out.println("expecting 7.0351 " + s.evaluate(0.1));
        
        if(s.hasRoot(-0.87505)) {
            System.out.println("-0.87505 is a root of s");
        } else {
            System.out.println("-0.87505 is not a root of s");
        }

        Polynomial q = p1.multiply(p2);
        q.print();
        System.out.println(q.evaluate(6) == p1.evaluate(6)*p2.evaluate(6));

        try {
            File file = new File("text.txt");
            Polynomial f = new Polynomial(file);
            f.print();
        } catch (IOException e) {
            System.out.println("error");
        }

        try {
            p1.saveToFile("p1out");
        } catch (IOException e) {
            System.out.println("error");
        }
        
    }
}