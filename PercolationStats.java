import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] fraction;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");
        if (trials <= 0) throw new IllegalArgumentException("Trials must be > 0");
        this.trials = trials;
        int t = 0;

        fraction = new double[trials];
        while (t < trials) {
            fraction[t] = PercolationNumber(n);
            t++;
        }


    }

    private double PercolationNumber(int n) {


        Percolation percolate = new Percolation(n); // MyClass is tested
        // generate and print n numbers between lo and hi
        final int nSquare = n * n;

        int x;
        // printf(String.valueOf(x));
        //  int count = 0;
        while (!percolate.percolates()) {

            x = StdRandom.uniform(0, nSquare - 1);
            //  x = StdRandom.uniform(0, 10);
            while (percolate.isOpen((x / n) + 1, (x % n) + 1)) {

                x = StdRandom.uniform(0, nSquare - 1);
                //    x = StdRandom.uniform(0, 10);
            }
            // System.out.println("random number to open site: " + x);
            percolate.open((x / n) + 1, (x % n) + 1);
            // System.out.println("site at : " + x + " " + percolate.openSites[x]);
        }
        // if (percolate.percolates()) System.out.println("System Percolates");
        // else System.out.println("System does not Percolates");

        double fraction = (double) percolate.numberOfOpenSites() / nSquare;
        // System.out.println("System percolates at: " + fraction);
        return fraction;
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, T);
        System.out.println("mean: " + percolationStats.mean());
        System.out.println("standard dev: " + percolationStats.stddev());

        System.out.println("95% confidence interval: [" + percolationStats.confidenceLo() + "],["
                                   + +percolationStats.confidenceLo() + "]");

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return (mean() - 1.96 * stddev() / Math.sqrt(this.trials));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return (mean() + 1.96 * stddev() / Math.sqrt(this.trials));
    }

    // sample mean of percolation threshold
    public double mean() {
        double total = 0.0;
        for (double f : fraction) {
            total += f;
        }
        return total / this.trials;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        double mean = mean();
        double deviation = 0;
        for (double f : fraction) {
            deviation += (f - mean) * (f - mean);
        }

        double deviationSqr = deviation / trials;
        return Math.sqrt(deviationSqr);
    }

}