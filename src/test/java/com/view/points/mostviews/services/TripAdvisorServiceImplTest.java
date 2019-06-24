package com.view.points.mostviews.services;


import com.view.points.mostviews.TestConfig;
import com.view.points.mostviews.entities.PlaceResponse;
import com.view.points.mostviews.googlemapsentities.*;
import com.view.points.mostviews.model.Way;
import com.view.points.mostviews.utils.JsonHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TripAdvisorServiceImpl.class)
@ContextConfiguration(classes= TestConfig.class)

public class TripAdvisorServiceImplTest {

    @Autowired
    private TripAdvisorService tripAdvisorService;

    @MockBean
    private DistanceMatrixService distanceMatrixService;

    @MockBean
    private PlaceDetailsService placeDetailsService;

    @MockBean
    private PlacesService placesService;

    @Test
    public void findBestRouteTrip() {

        DirectMatrixResponse directMatrixResponse = JsonHelper.readObject("json/distanceMatrixResponse.json", DirectMatrixResponse.class);
        Mono<DirectMatrixResponse> directMatrixResponseMono = Mono.fromSupplier(() -> directMatrixResponse);

        String query = "Boston,MA|Charlestown,MA|Lexington,MA|Concord,CA";
        var locations = query.split("\\|");

        var placeResponses = Arrays.stream(locations).collect(Collectors.toMap(s->s, v ->JsonHelper.readObject("json/places" + v +"Response.json", PlaceResponse.class)));
        var placeDetailsResponses = Arrays.stream(locations).collect(Collectors.toMap(s->s, v ->JsonHelper.readObject("json/placeDetails" + v +"Response.json", PlaceDetails.class)));

        for (String param:locations) {
            var placesMono = Mono.fromSupplier(() -> placeResponses.get(param));
            Mockito.when(placesService.findByName(param)).thenReturn(placesMono);
            var placeDetailsMono = Mono.fromSupplier(() -> placeDetailsResponses.get(param));
            Mockito.when(placeDetailsService.findById(placesMono.block().getCandidates().get(0).getPlace_id())).thenReturn(placeDetailsMono);
        }

        String queryParams = placeDetailsResponses.entrySet().
                stream().
                map(Map.Entry::getValue).
                map(PlaceDetails::getResult).
                map(Result::getGeometry).
                map(Geometry::getLocation).map(Location::toString).reduce((s, s2) -> s+"|"+s2).
                orElseThrow();

        Mockito.when(distanceMatrixService.buildMatrix(Mockito.anyString())).thenReturn(directMatrixResponseMono);

        List<Way> list = tripAdvisorService.getBestRouteTrip(query);
        assert(list.size() == 3);
        assert(list.get(0).getSource().getNumber().equals(0));

        List<Integer> locationList = list.stream().map(way -> way.getDestination().getNumber()).collect(Collectors.toList());
        assert(locationList.equals(List.of(2,1, 3)));
    }

}
