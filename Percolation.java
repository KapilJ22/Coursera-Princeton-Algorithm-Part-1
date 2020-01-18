import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.QuickFindUF;

import java.util.Scanner;

public class Percolation {
    private int nSquare;
    private int n;
    private boolean[] openSites;
    private boolean[] fullSites;
    private QuickFindUF quickFindUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");
        this.n = n;
        nSquare = n * n;
        quickFindUF = new QuickFindUF(nSquare + 2);
        openSites = new boolean[nSquare + 2];
        fullSites = new boolean[nSquare + 2];
        for (int i = 0; i < nSquare + 2; i++) {
            openSites[i] = false;
        }
        for (int i = 0; i < nSquare + 2; i++) {
            fullSites[i] = false;
        }
    }

    private int oneDimensionSiteAddress(int row, int col) {

        return row * n + col;

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        openSites[oneDimensionSiteAddress(row, col)] = true;
        unionAdjacent(row*n+col);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return openSites[oneDimensionSiteAddress(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return fullSites[oneDimensionSiteAddress(row, col)];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count=0;
        for(int i=0;i<nSquare;i++){
            if(isOpen(i/n,i%n)) count++;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        if (quickFindUF.connected(nSquare, nSquare + 1))
            return true;
        else return false;
    }

    private void unionAdjacent(int x) {
        int y = nSquare;
        if (x >= 0 && x < n)
            quickFindUF.union(x, y);

        y = nSquare + 1;
        if (x >= nSquare - n && x <= nSquare)
            quickFindUF.union(x, y);

        y = x - 1;
        unionAdjacents(x, y);

        y = x + 1;
        unionAdjacents(x, y);

        y = x - n;
        unionAdjacents(x, y);

        y = x + n;
        unionAdjacents(x, y);
    }

    private void unionAdjacents(int x, int y) {
        if (y >= 0 && y <= nSquare - 1) {
            if (isOpen(y / n, y % n))
                quickFindUF.union(x, y);
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolate = new Percolation(5); // MyClass is tested
        // generate and print n numbers between lo and hi


        int x;
        // printf(String.valueOf(x));
      //  int count = 0;
        while (!percolate.percolates()) {

            x = StdRandom.uniform(0, 24);
            while (percolate.openSites[x]) {

                x = StdRandom.uniform(0, 24);
            }
            System.out.println("random number to open site: " + x);
            percolate.open(x / percolate.n, x % percolate.n);
            System.out.println("site at : " + x + " " + percolate.openSites[x]);
        }
        if (percolate.percolates()) System.out.println("System Percolates");
        else System.out.println("System does not Percolates");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            int x1 = scanner.nextInt();
            int x2 = scanner.nextInt();


            System.out.println(
                    x1 + "," + x2 + "connected? " + percolate.quickFindUF.connected(x1, x2));

        }
    }
}