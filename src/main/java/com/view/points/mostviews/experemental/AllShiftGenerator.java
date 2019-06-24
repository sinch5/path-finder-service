package com.view.points.mostviews.experemental;

public class AllShiftGenerator {
    final int NMAX = 5;

    int solutions = 0;
    final int MAX_CANDIDATES = 12;

    int a[]= new int[NMAX];

    public static void main(String[] arr) {
        new AllShiftGenerator().runMeth();
    }

    private  void runMeth() {
        for (int i = 0; i<NMAX; i++) {
            a[i]=-1;
        }
        backtrack(a, 0, NMAX);

    }

    private void backtrack(int[] a, int k, int input) {
        int c[]= new int[MAX_CANDIDATES];
        Integer ncandidates=0;
        int i;
        if(isSolution(a, k, input)) {
            processSolution(a, k, input);
        } else {
            ncandidates = constructCandidates(a, k+1 , input, c,  ncandidates);
            for (i =0; i <ncandidates; i++) {
                a[k] = c[i];
                backtrack(a,k+1, input);
            }
            a[k] = -1;
        }
    }

    private Integer constructCandidates(int[] a, int k, int n,int[] c, Integer ncandidates) {
        int i;
        boolean in_perm[] = new boolean[NMAX];
        for (i=0; i<NMAX; i++) {
            in_perm[i] = false;
        }
        for (i=0; i<k; i++) {
            if (a[i]!=-1)
                in_perm[a[i]] = true;
        }
        int gg=0;
        for (i=0; i <n; i++) {
            if (in_perm[i] == false) {
                c[ncandidates] = i;
                ncandidates++;
            }
        }

        return ncandidates;
    }


    private void processSolution(int[] a, int k, int input) {
        int i;
        for (i=0;i<k;i++) {
            System.out.print(", " +  (a[i] + 1));
        }
        System.out.println("");

    }
    private boolean isSolution(int[] a, int k, int n) {
        return (k==n);
    }


}
