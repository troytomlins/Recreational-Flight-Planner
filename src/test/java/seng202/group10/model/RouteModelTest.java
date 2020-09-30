package seng202.group10.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteModelTest {

    private Route testRoute;
    private RouteModel model;
    private ArrayList<Route> compare;

    @BeforeEach
    public void init() {
        DatabaseConnection.getInstance().disconnect();
        File file = new File("database.db");
        file.delete();
        model = new RouteModel();
        compare = new ArrayList<>();
        ArrayList<String> equipment = new ArrayList<>();
        testRoute = new Route("test", "code1", "code2", 5);
    }

    @AfterAll
    public static void tearDown() {
        DatabaseConnection.getInstance().disconnect();
    }

    @Test
    public void addRouteTest() {
        ArrayList<Route> compare = new ArrayList<>();
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
        ArrayList<String> equipment = new ArrayList<>();
        Route testRoute2 = new Route("code1", "Airport1", "code2", 2);
        model.addRoute(testRoute);
        model.addRoute(testRoute2);
        model.save();
        assertEquals(initLen + 2, model.getRoutes().size());

    }

}
