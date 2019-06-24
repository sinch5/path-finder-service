package com.view.points.mostviews.experemental;

import com.view.points.mostviews.model.Location;
import com.view.points.mostviews.model.Way;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class AllRoutesGenerator {

    private Queue<Location> queue = new LinkedList<>();
    Location location = new Location(1,"1Home", "");
    Location location2 = new Location(2,"2Luvr", LocalTime.of(8,0), LocalTime.of(20,0),"");
    Location location3 = new Location(3,"3Burj Khalifa",LocalTime.of(8,0), LocalTime.of(12,0),"");
    Location location4 = new Location(4,"4Burj Alarab", LocalTime.of(10,0), LocalTime.of(13,0),"");
    Location location5 = new Location(5,"5Lamer", LocalTime.of(8,0), LocalTime.of(12,0),"");

    LocalTime departureTime = LocalTime.of(7, 30);


    final int NMAX = 11;

    //final int NMAX = 5;
    int solutions = 0;
    final int MAX_CANDIDATES = 12;

    Location a[]= new Location[NMAX];
    boolean finished = false;

    public static void main(String[] arr) {
        new AllRoutesGenerator().runMeth();
    }

    private  void runMeth() {

       /* location.addWay(new Way(location2, new PeriodOfTime(0,5)));
        location.addWay(new Way(location3, new PeriodOfTime(3,0)));
        location.addWay(new Way(location4, new PeriodOfTime(0,5)));

        location2.addWay(new Way(location, new PeriodOfTime(0,5)));
        location2.addWay(new Way(location3, new PeriodOfTime(1,3)));
        location2.addWay(new Way(location4, new PeriodOfTime(0,3)));
        location2.addWay(new Way(location5, new PeriodOfTime(1,3)));

        location3.addWay(new Way(location, new PeriodOfTime(2,0)));
        location3.addWay(new Way(location2, new PeriodOfTime(1,3)));
        location3.addWay(new Way(location5, new PeriodOfTime(0,3)));


        location4.addWay(new Way(location, new PeriodOfTime(0,5)));
        location4.addWay(new Way(location2, new PeriodOfTime(0,3)));
        location5.addWay(new Way(location3, new PeriodOfTime(0,3)));

        location5.addWay(new Way(location2, new PeriodOfTime(1,3)));*/

        a[0] = location;
        backtrack(a, 0, location5);

    }

    private void backtrack(Location[] a, int k, Location input) {
        Location c[]= new Location[MAX_CANDIDATES];
        Integer ncandidates=0;
        int i;

        if(isSolution(a, k, input)) {
            processSolution(a, k, input);
        } else {
            ncandidates = constructCandidates(a, k+1 , input, c);
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

    private Integer constructCandidates(Location[] a, int k, Location input, Location[] c) {
        int i;
        int ncandidates = 0;
        Location last;
        Set<Location> inSol = new HashSet<>();
        inSol.clear();
        for (i=0; i<k; i++) {
            if (a[i]!=null)
                inSol.add(a[i]);
        }

        if (k==1) {
            c[0] = a[0];
            return 1;
        }

        else {
            ncandidates = 0;
            last = a[k-2];
            for (Way way:last.getWays()) {
                if (!inSol.contains(way.getDestination())) {
                    c[ncandidates]=way.getDestination();
                    ncandidates++;
                }
            }
        }

        return ncandidates;
    }


    private void processSolution(Location[] a, int k, Location input) {
        for (Location location:a) {
            if (location!=null) {
                System.out.print(location + "; ");
            }
        }
        System.out.println("");
    }
    private boolean isSolution(Location[] a, int k, Location n) {
        if (k-1 != -1)
        return (n.equals(a[k-1]));
        return false;
    }


}
