package Week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    final private int nSquare;
    final private int n;
    private boolean[] openSites;
    private boolean[] fullSites;
    private WeightedQuickUnionUF quickFindUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");
        this.n = n;
        nSquare = n * n;
        quickFindUF = new WeightedQuickUnionUF(nSquare + 2);
        openSites = new boolean[nSquare + 2];
        fullSites = new boolean[nSquare + 2];
        for (int i = 0; i < nSquare; i++) {
            openSites[i] = false;
        }
        openSites[nSquare] = true;
        openSites[nSquare + 1] = true;

        for (int i = 0; i < nSquare; i++) {
            fullSites[i] = false;
        }
        fullSites[nSquare] = true;
        fullSites[nSquare + 1] = true;

    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolate = new Percolation(10); // MyClass is tested
        // generate and print n numbers between lo and hi


        int x;
        // printf(String.valueOf(x));
        //  int count = 0;
        while (!percolate.percolates()) {

            x = StdRandom.uniform(0, percolate.nSquare - 1);
            //  x = StdRandom.uniform(0, 10);
            while (percolate.openSites[x]) {

                x = StdRandom.uniform(0, percolate.nSquare - 1);
                //    x = StdRandom.uniform(0, 10);
            }
            System.out.println("random number to open site: " + x);
            percolate.open((x / percolate.n) + 1, (x % percolate.n) + 1);
            System.out.println("site at : " + x + " " + percolate.openSites[x]);
        }
        if (percolate.percolates()) System.out.println("System Percolates");
        else System.out.println("System does not Percolates");

        double fraction = (double) percolate.numberOfOpenSites() / percolate.nSquare;
        System.out.println("System percolates at: " + fraction);
        // while (true) {
        //     Scanner scanner = new Scanner(System.in);
        //     int x1 = scanner.nextInt();
        //     int x2 = scanner.nextInt();
        //
        //
        //     System.out.println(
        //             x1 + "," + x2 + "connected? " + percolate.quickFindUF.connected(x1, x2));
        //
        // }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        openSites[oneDimensionSiteAddress(row - 1, col - 1)] = true;
        unionAdjacent((row - 1) * n + (col - 1));

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return openSites[oneDimensionSiteAddress(row - 1, col - 1)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return quickFindUF.connected(
                oneDimensionSiteAddress(row - 1, col - 1),
                nSquare
        );
    }

    private int oneDimensionSiteAddress(int row, int col) {

        return row * n + col;

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < nSquare; i++) {
            if (isOpen((i / n) + 1, (i % n) + 1)) count++;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickFindUF.connected(nSquare, nSquare + 1);

    }

    private void unionAdjacent(int x) {
        int y = nSquare;
        if (x >= 0 && x < n) {
            quickFindUF.union(x, y);
            //    setSitesFull(x, y);
        }
        y = nSquare + 1;
        if (x >= nSquare - n && x < nSquare) {
            quickFindUF.union(x, y);
            //   setSitesFull(x, y);
        }

        if (!(x % n == 0)) {
            y = x - 1;
            unionAdjacents(x, y);
        }

        if (!((x + 1) % n == 0)) {
            y = x + 1;
            unionAdjacents(x, y);
        }
        y = x - n;
        unionAdjacents(x, y);

        y = x + n;
        unionAdjacents(x, y);
    }

    private void setSitesFull(int x, int y) {
        // if (quickFindUF.connected(x, nSquare)) {
        //     this.fullSites[x] = true;
        //     this.fullSites[y] = true;
        // }
    }

    private void unionAdjacents(int x, int y) {
        if (y >= 0 && y <= nSquare - 1) {
            if (isOpen((y / n) + 1, (y % n) + 1)) {
                quickFindUF.union(x, y);
                //  setSitesFull(x, y);
            }
        }
    }
}