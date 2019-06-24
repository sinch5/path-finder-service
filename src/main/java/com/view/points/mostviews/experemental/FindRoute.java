package com.view.points.mostviews.experemental;

import com.view.points.mostviews.model.Location;
import com.view.points.mostviews.model.Way;

import java.time.LocalTime;
import java.util.*;

public class FindRoute {

    private Queue<Location> queue = new LinkedList<>();
    Location location = new Location(1,"1Home",  "");
    Location location2 = new Location(2,"2Luvr",LocalTime.of(8,0), LocalTime.of(20,0), "");
    Location location3 = new Location(3,"3Burj Khalifa",LocalTime.of(8,0), LocalTime.of(12,0),"");
    Location location4 = new Location(4,"4Burj Alarab", LocalTime.of(10,0), LocalTime.of(13,0),"");
    Location location5 = new Location(5,"5Lamer", LocalTime.of(8,0), LocalTime.of(12,0),"s");

    LocalTime departureTime = LocalTime.of(7, 30);
        // Location location5 = new Location(LocalTime.of(9,0), LocalTime.of(12,0));

    public static void main(String args[]) {
        FindRoute findRoute = new FindRoute();
        findRoute.init();
    }

    public  void init() {

      /*  location.addWay(new Way(location2, new PeriodOfTime(0,5)));
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

        location5.addWay(new Way(location2, new PeriodOfTime(1,3)));
*/

        run(location5);

    }


    public  void run(Location destination) {
       Queue<Location> path = new LinkedList<>();

       queue.add(location);
       path.add(location);

       location.setProcessed(1);
       while(!queue.isEmpty()) {

           Location location = queue.poll();
           location.setProcessed(1);
           //  System.out.println(location);
           path.add(location);
           for (Way way: location.getWays()) {
              // if (way.getDestination().getProcessed() == 0) {
                    //way.getPathLengthInTime()<
                    queue.add(way.getDestination());

                    if (location.equals(destination) ) {
                        System.out.println(path);
                    }
              // }

           }
       }
       for (Way way: location.getWays()) {
          /* var arrivalTime = departureTime.plusHours(way.getPathLengthInTime().minutes).plusMinutes((way.getPathLengthInTime().hours));
           if (way.getDestination().getClosingTime().compareTo(arrivalTime) == 1 && way.getDestination().getOpeningTime().compareTo(arrivalTime) <=0) {

           }*/

       }


    }
}
