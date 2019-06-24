package com.view.points.mostviews.services;

import com.view.points.mostviews.model.Location;
import com.view.points.mostviews.model.Way;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TripAdvisorServiceImpl implements TripAdvisorService {

    private Long sum = Long.MAX_VALUE;

    private List<Way> minRoutes;

    final private PathFinderService pathFinderService;

    final private GraphBuilderService graphBuilderService;

    private Map<Location, List<Location>> matrix;

    private List<Integer> vertex = new ArrayList<>();
    private List<Integer> minLoc;

    public TripAdvisorServiceImpl(PathFinderService pathFinderService, GraphBuilderService graphBuilderService) {
        this.pathFinderService = pathFinderService;
        this.graphBuilderService = graphBuilderService;
    }

    @Override
    public List<Way> getBestRouteTrip(String places) {
        matrix = graphBuilderService.buildGraph(places);
        vertex = IntStream.generate(() -> -1).limit(matrix.size() + 1).boxed().collect(Collectors.toList());
        List<Way> ways =backtrack(vertex, 0, matrix.size());
        pathFinderService.calculateRoute(ways.get(0).getSource(), minLoc);
        return ways;
    }

    private List<Way> backtrack(List<Integer> a, int k, int input) {

        List<Integer> c = IntStream.generate(() -> -1).limit(matrix.size() + 1).boxed().collect(Collectors.toList());

        if(isSolution(k, input)) {
            return processSolution(a);
        } else {
            int ncandidates = constructCandidates(a, k+1 , input, c);
            for (int i =0; i <ncandidates; i++) {
                a.set(k, c.get(i));
                if (a.get(0) == 0) {
                    backtrack(a, k + 1, input);
                }
            }
            a.set(k, -1);
        }
        return minRoutes;
    }

    private Integer constructCandidates(List<Integer> a, int k, int n, List<Integer> c) {
        Integer ncandidates = 0;
        int i;
        Set<Integer> inPerm = new HashSet<>();
        for (i=0; i<k; i++) {
            if (!a.isEmpty() && a.get(i)!=-1 )
                inPerm.add(a.get(i));
        }
        for (i=0; i <n; i++) {
            if (!inPerm.contains(i)) {
                c.set(ncandidates, i);
                ncandidates++;
            }
        }

        return ncandidates;
    }

    private List<Way> processSolution(List<Integer> a) {
        List<Integer> routes = a.subList(1, a.size()-1);
        Map.Entry<Location, List<Location>> entry = matrix.entrySet().iterator().next();

        //List<Integer> listWithBack  = new ArrayList(routes);
        //listWithBack.add(a.get(0));
        List<Way>  rt = pathFinderService.calculateRoute(entry.getKey(), routes);
        Long sum = 0L;
        for (Way way: rt) {
            sum = sum + way.getPathLengthInTime();
        }
        if (sum.compareTo(this.sum)==-1 && rt.size() == routes.size()) {
            this.sum = sum;
            this.minRoutes = rt;
            this.minLoc= new ArrayList<>(routes);

        }
        return minRoutes;
    }

    private boolean isSolution(int k, int n) {
        return (k == n);
    }

}
