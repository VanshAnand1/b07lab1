import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Polynomial {
    double coefficients[];
    int exponents[];

    public Polynomial() {
        this.coefficients = new double[]{0};
        this.exponents = new int[]{0};
    }

    public Polynomial(double c[], int e[]) {
        this.coefficients = c;
        this.exponents = e;
    }

    public Polynomial(File f) throws FileNotFoundException {
        Map<Integer, Double> map = new TreeMap<>();
        Scanner s = new Scanner(f);
        String input = s.nextLine().replaceAll("-", "+-");
        s.close();
        String[] terms = input.split("\\+");
        for (int x = 0; x < terms.length; x++) {
            if (terms[x].isBlank()) {
                continue;
            } 
            if (terms[x].contains("x")) {
                String[] split = terms[x].split("x");
                double c;
                if (split[0].equals("") || split[0].equals("+")) {
                    c = 1;
                } else if (split[0].equals("-")) {
                    c = -1;
                } else {
                    c = Double.parseDouble(split[0]);
                }
                int e;
                if (split.length == 1 || split[1].isBlank()) {
                    e = 1;
                } else {
                    e = Integer.parseInt(split[1]);
                }
                map.put(e, c);
            } else {
                map.put(0, Double.parseDouble(terms[x]));
            }
        }
        double coeffs[] = new double[map.size()];
        int exps[] = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            exps[index] = entry.getKey();
            coeffs[index] = entry.getValue();
            index++;
        }

        this.coefficients = coeffs;
        this.exponents = exps;
    }

    public void saveToFile(String filename) throws IOException {
        FileWriter output = new FileWriter(filename, false);
        boolean notempty = false;
        for (int x = 0; x < this.coefficients.length; x++) {
            double c = this.coefficients[x];
            int e = this.exponents[x];
            if (c < 0) {
                output.append("-");
                c = -c;
            } else {
                if (notempty) {
                    output.append("+");
                    
                }
            }

            if (e == 0) {
                output.append(Double.toString(c));
            } else if (e == 1) {
                output.append(Double.toString(c) + "x");
            } else {
                output.append(Double.toString(c) + "x" + Integer.toString(e));
            }
            notempty = true;
        }
        // check if the line starts with + and remove it if it does
        output.close();
    }

    public void print() {
        System.out.println("Polynomial has the following values, c : e");
        for (int x = 0; x < this.coefficients.length; x++) {
            System.out.println(this.coefficients[x] + " : " + this.exponents[x]);
        }
    }

    public Polynomial add(Polynomial p1) {
        Map<Integer, Double> map = new TreeMap<>();
        for (int x = 0; x < this.exponents.length; x++) {
            if (map.get(this.exponents[x]) == null) {
                map.put(this.exponents[x], this.coefficients[x]);
            } else {
                map.put(this.exponents[x], map.get(this.exponents[x]) +  this.coefficients[x]);
            }
        } 
        for (int x = 0; x < p1.exponents.length; x++) {
            if (map.get(p1.exponents[x]) == null) {
                map.put(p1.exponents[x], p1.coefficients[x]);
            } else {
                map.put(p1.exponents[x], map.get(p1.exponents[x]) +  p1.coefficients[x]);
            }
        }
        
        Map<Integer, Double> newMap = new TreeMap<>();
        map.forEach((key, value) -> {
            if (value != 0.0) {
                newMap.put(key, value);
            }
        });

        double c[] = new double[newMap.size()];
        int e[] = new int[newMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : newMap.entrySet()) {
            e[index] = entry.getKey();
            c[index] = entry.getValue();
            index++;
        }

        return new Polynomial(c, e);
    }

    public double evaluate(double e) {
        double sum = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            sum = sum + this.coefficients[i] * Math.pow(e, this.exponents[i]);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial p1) {
        Map<Integer, Double> terms = new TreeMap<>();
        int tlen = this.coefficients.length;
        int plen = p1.coefficients.length;

        for (int x = 0; x < tlen; x++) {
            for (int y = 0; y < plen; y++) {
                int e = this.exponents[x] + p1.exponents[y];
                double c = this.coefficients[x] * p1.coefficients[y];
                if (terms.get(e) == null) {
                    terms.put(e, c);
                } else {
                    terms.put(e, terms.get(e) + c);
                }
            }
        }

        double coeffs[] = new double[terms.size()];
        int exps[] = new int[terms.size()];
        int index = 0;
        for (Map.Entry<Integer, Double> entry : terms.entrySet()) {
            exps[index] = entry.getKey();
            coeffs[index] = entry.getValue();
            index++;
        }

        return new Polynomial(coeffs, exps);
        
    }
}