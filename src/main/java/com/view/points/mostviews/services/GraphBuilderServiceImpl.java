package com.view.points.mostviews.services;

import com.view.points.mostviews.googlemapsentities.*;
import com.view.points.mostviews.model.Location;
import com.view.points.mostviews.model.Way;
import reactor.core.publisher.Mono;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.view.points.mostviews.utils.JsonHelper;

/**
 * This is class responsible for building graph of routes regarding passed locations in parameter
 */
public class GraphBuilderServiceImpl implements GraphBuilderService {

    final private DistanceMatrixService distanceMatrixService;
    final private PlaceDetailsService placeDetailsService;
    final private PlacesService placesService;

    public GraphBuilderServiceImpl(DistanceMatrixService distanceMatrixService, PlaceDetailsService placeDetailsService, PlacesService placesService) {
        this.distanceMatrixService = distanceMatrixService;
        this.placeDetailsService = placeDetailsService;
        this.placesService = placesService;
    }

    /**
     *
     * @param places  which are wanted to be visited. first one of them is source location
     * @return map of ways
     */
    public Map<Location, List<Location>> buildGraph(String places) {

        final LocalDateTime now = LocalDateTime.now();

        var locations = places.split("\\|");
        var allPlaces = Arrays.stream(locations).map(s ->  placesService.findByName(s)).collect(Collectors.toList());
/*
        for (int i=0; i< allPlaces.size(); i++) {
            JsonHelper.writeObject("places" + locations[i] +"Response.json", allPlaces.get(i).block());
        }*/
        var candidates = allPlaces.stream().
                map(placeResponseMono -> placeResponseMono.block()).
                map(placeResponse -> placeResponse.getCandidates().get(0)).
                collect(Collectors.toList());

        final List<PlaceDetails> details = candidates.stream().map(candidate -> placeDetailsService.findById(candidate.getPlace_id())).map(Mono::block).collect(Collectors.toList());

//        for (int i=0; i< allPlaces.size(); i++) {
//            JsonHelper.writeObject("placeDetails" + locations[i] +"Response.json", details.get(i));
//
//        }

        String query = details.
                stream().
                map(PlaceDetails::getResult).
                map(Result::getGeometry).
                map(Geometry::toString).
                reduce((s1,s2)->s1+"|"+s2).get();

        return  distanceMatrixService.buildMatrix(query).map(directMatrixResponse -> {

/*
            JsonHelper.writeObject("distanceMatrixResponce.json", directMatrixResponse);
*/

            List<Location> locationList =
                    IntStream.range(0, directMatrixResponse.getOrigin_addresses().size()).
                    mapToObj(i -> createLocation(directMatrixResponse, details, i, now)).collect(Collectors.toList());
                    //locationList.add(locationList.get(0));
                    return locationList.stream().
                    collect(
                        Collectors.toMap(locationFrom -> locationFrom,
                            locationFrom -> locationList.stream().
                                filter(locationTo -> areLocationsNotEqual(locationTo, locationFrom)).
                                map(locationTo -> addWay(locationFrom, locationTo, directMatrixResponse)).
                                collect(Collectors.toList()),
                                GraphBuilderServiceImpl::duplicateKeysProcess,
                            LinkedHashMap::new)
                    );
            }).block();
    }

    private Location addWay(Location from, Location to, DirectMatrixResponse directMatrixResponse) {
        TextValue time = directMatrixResponse.getRows().get(from.getNumber()).getElements().get(to.getNumber()).getDuration();
        TextValue trafficTime = directMatrixResponse.getRows().get(from.getNumber()).getElements().get(to.getNumber()).getDuration_in_traffic().orElse(time);

        Way way = new Way(from, to,trafficTime!=null?trafficTime.getValue():time.getValue()/*element.getDuration_in_traffic().getValue()*/);// TODO add real period
        from.addWay(way);
        return from;
    }

    private Location createLocation(DirectMatrixResponse directMatrixResponse, List<PlaceDetails> details, int i, LocalDateTime now) {
        PlaceDetails placeDetails = details.get(i);
        Period period = details.get(i).getResult().getOpening_hours().
                map(OpeningHours::getPeriods).
                filter(periods -> periods.size() > 1).
                map(periods -> periods.get(now.getDayOfWeek().getValue())).
                orElse(new Period());
        LocalTime closeTime= period.getClose().map(TimePoint::getTime).map(GraphBuilderServiceImpl::parseTime).orElse(LocalTime.of(0,0));
        LocalTime openTime= period.getOpen().map(TimePoint::getTime).map(GraphBuilderServiceImpl::parseTime).orElse(LocalTime.of(0,0));

        return new Location(i, placeDetails.getResult().getName(), openTime, closeTime, details.get(i).getResult().getFormatted_address());
    }

    private boolean areLocationsNotEqual(Location location1, Location location2) {
        return !location1.equals(location2);
    }

    private static <U> U duplicateKeysProcess(U u, U u1) {
       // throw new RuntimeException("duplicated params");
        return  u;
    }

    private static LocalTime parseTime(String str) {
        String strTime = MessageFormat.format("{0}:{1}",str.substring(0,2), str.substring(2,4));
        return LocalTime.parse(strTime);
    }
}
