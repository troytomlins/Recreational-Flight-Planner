package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteModelTest {

    private Route testRoute;
    private RouteModel model;
    private ArrayList<Route> compare;

    @BeforeEach
    public void init() {
        model = new RouteModel();
        compare = new ArrayList<>();
        ArrayList<String> equipment = new ArrayList<>();
        testRoute = new Route("test", "code1", "code2", 5);
    }

    @Test
    public void addRouteTest() {
        ArrayList<Route> compare = new ArrayList<>();
        compare.add(testRoute);
        model.addRoute(testRoute);
        assertEquals(compare, model.getRoutes());
    }

    @Test
    public void addDuplicateRouteTest() {
        compare.add(testRoute);
        model.addRoute(testRoute);
        model.addRoute(testRoute);
        assertEquals(compare, model.getRoutes());
    }

    @Test
    public void deleteRouteTest() {
        model.addRoute(testRoute);
        model.deleteRoute(testRoute);
        assertEquals(compare, model.getRoutes());
    }

    /**
     * Tests both input types of Route.
     */
    @Test
    public void bothBothInputTypeTest() {
        int count = 0;
        ArrayList<String> equipment = new ArrayList<>();
        Route testRoute2 = new Route("code1", "Airport1", "code2", 2);
        model.addRoute(testRoute);
        model.addRoute(testRoute2);
        for (Route route : model.getRoutes()) {
            count += 1;
        }
        assertEquals(2, count);

    }

}
