package com.view.points.mostviews.experemental;

public class AllSetsGenerator {
    final int size = 9;

    int a[]= new int[size];
    boolean finished = false;

    public static void main(String[] arr) {
        new AllSetsGenerator().runMeth();
    }

    private  void runMeth() {
        backtrack(a, 0, size);

    }

    private void backtrack(int[] a, int k, int input) {
        int c[]= new int[2];
        Integer ncandidates=0;
        int i;

        if(isSolution(a, k, input)) {
            processSolution(a, k, input);
        } else {
            k =  k + 1;
            ncandidates = constructCandidates(a, k , input, c,  ncandidates);
            for (i =0; i <ncandidates;i++) {
                a[k-1] = c[i];
                //make_move(a,k,input);
                backtrack(a,k,input);
                //unmakeMove(a, k , input);
                if(finished) return;
            }
        }

    }

    private void processSolution(int[] a, int k, int input) {
        int i;
        System.out.print("{");
        for (i=0;i<k;i++) {
            if (a[i]==1) {
                System.out.print(", " +  (i+1));
            }
        }
        System.out.print("}, ");
    }

    private Integer constructCandidates(int[] a, int k, int input,int[] c, Integer ncandidates) {
        c[0]=1;
        c[1]=0;

        return 2;
    }

    private boolean isSolution(int[] a, int k, int n) {
        return (k==n);
    }


}
