package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test Class for RouteModel.
 */
public class RouteModelTest {

    private Route testRoute;
    private RouteModel model;
    private ArrayList<Route> compare;

    @BeforeEach
    public void init() {
        File file = new File("database.db");
        file.delete();
        model = new RouteModel();
        compare = new ArrayList<>();
        testRoute = new Route("test", "code1", "code2", 5);
    }

    @Test
    public void addRouteTest() {
        int initLen = model.getRoutes().size();
        model.addRoute(testRoute);
        model.save();
        int postLen = model.getRoutes().size();
        assertEquals(initLen + 1, postLen);
    }

    @Test
    public void addDuplicateRouteTest() {
        int initLen = model.getRoutes().size();
        compare.add(testRoute);
        model.addRoute(testRoute);
        model.addRoute(testRoute);
        model.save();
        int postLen = model.getRoutes().size();
        assertEquals(initLen + 2, postLen);
    }

//    @Test
//    public void deleteRouteTest() {
//        int initLen = model.getRoutes().size();
//        model.addRoute(testRoute);
//        model.save();
//        model.deleteRoute(testRoute);
//        model.save();
//        int postLen = model.getRoutes().size();
//        assertEquals(initLen, postLen);
//    }

    /**
     * Tests both input types of Route.
     */
    @Test
    public void bothBothInputTypeTest() {
        int initLen = model.getRoutes().size();
        Route testRoute2 = new Route("code1", "Airport1", "code2", 2);
        model.addRoute(testRoute);
        model.addRoute(testRoute2);
        model.save();
        assertEquals(initLen + 2, model.getRoutes().size());

    }

}
