package com.view.points.mostviews;

import com.view.points.mostviews.model.Way;
import com.view.points.mostviews.services.TripAdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartupApplicationListenerExample implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TripAdvisorService tripAdvisorService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
      /*  List<Way> route = tripAdvisorService.getBestRouteTrip(List.of("Boston,MA", "Charlestown,MA", "Lexington,MA", "Concord,MA"));
        route.stream().map(o->o.getDestination()).map(o->o.getNumber().toString()).reduce((o, o2) -> o+"->"+o2).ifPresent(System.out::println);
*/
    }
}
