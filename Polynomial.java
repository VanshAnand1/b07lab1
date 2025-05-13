public class Polynomial {
    double coefficients[];

    public Polynomial() {
        this.coefficients = new double[]{0};
    }

    public Polynomial(double Array[]) {
        this.coefficients = Array;
    }

    public Polynomial add(Polynomial p1) {
        int maxlen = Math.max(p1.coefficients.length, this.coefficients.length);
        double[] c = new double[]{maxlen};

        int i = 0;
        while (i < Math.min(p1.coefficients.length, this.coefficients.length)) {
            c[i] = p1.coefficients[i] + this.coefficients[i];
            i++;
        }

        if (p1.coefficients.length < this.coefficients.length) {
            while (i < maxlen) {
                c[i] = this.coefficients.length;
                i++;
            }
        } else {
            while (i < maxlen) {
                c[i] = p1.coefficients.length;
                i++;
            }
        }
        
        return new Polynomial(c);
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