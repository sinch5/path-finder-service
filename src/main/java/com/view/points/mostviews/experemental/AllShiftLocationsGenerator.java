package com.view.points.mostviews.experemental;

import com.view.points.mostviews.model.Location;

import java.util.HashSet;
import java.util.Set;

public class AllShiftLocationsGenerator {

    final int NMAX = 11;
    int solutions = 0;
    final int MAX_CANDIDATES = 12;

    Location a[]= new Location[NMAX];
    boolean finished = false;

    public static void main(String[] arr) {
        new AllShiftLocationsGenerator().runMeth();
    }

    private  void runMeth() {

        backtrack(a, 0, NMAX);

    }

    private void backtrack(Location[] a, int k, int input) {
        Location c[]= new Location[MAX_CANDIDATES];
        Integer ncandidates=0;
        int i;

        if(isSolution(a, k, input)) {
            processSolution(a, k, input);
        } else {

            ncandidates = constructCandidates(a, k+1 , input, c,  ncandidates);
            for (i =0; i <ncandidates; i++) {
                a[k] = c[i];
                //make_move(a,k,input);
                backtrack(a,k+1,input);
                //unmakeMove(a, k , input);
                if(finished) return;
            }
            a[k] = null;
        }

    }

    private Integer constructCandidates(Location[] a, int k, int n,Location[] c, Integer ncandidates) {
        int i;
        Set<Location> in_perm = new HashSet<Location>() ;

        for (i=0; i<k; i++) {
            if (a[i]!=null)
                in_perm.add(a[i]);
        }
        int gg=0;
        for (i=0; i <n; i++) {
            if (!in_perm.contains(i)) {
               // c[ncandidates] = i;
                ncandidates++;
            }
        }

        return ncandidates;
    }


    private void processSolution(Location[] a, int k, int input) {
        int i;
       // System.out.print("{");
        for (i=0;i<k;i++) {
            //System.out.print(", " +  (a[i] + 1));
        }
        //System.out.print("}, ");
        solutions++;
        System.out.println(solutions);



    }
    private boolean isSolution(Location[] a, int k, int n) {
        return (k==n);
    }


}
