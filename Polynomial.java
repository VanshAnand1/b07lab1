public class Polynomial {
    double coefficients[];

    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double Array[]) {
        this.coefficients = Array;
    }

    public Polynomial add(Polynomial p1) {
        for (int i = 0; i < this.coefficients.length; i++) {
            p1.coefficients[i] += this.coefficients[i];
        }
        return p1;
    }

    public double evaluate(double e) {
        double sum = 0;
        for (int i = 0; i < this.coefficients.length; i++) {
            sum = sum + this.coefficients[i] * Math.pow(e,i);
        }
        return sum;
    }

    public boolean hasRoot(double x) {
        return this.evaluate(x) == 0;
    }
}