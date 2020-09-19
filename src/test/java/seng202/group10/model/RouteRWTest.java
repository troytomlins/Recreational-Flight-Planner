package seng202.group10.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RouteRWTest {
    public class AirlineRWTest {
        private final String goodFileString = "src/test/resources/seng202.group10/model/routesGood.dat";
        private final String badFileString = "src/test/resources/seng202.group10/model/routesBad.dat";
        private final String corruptFileString = "src/test/resources/seng202.group10/model/routesCorrupt.dat";

        @Test
        public void readFileReturnsCorrectArrayGoodFile() throws FileFormatException, IncompatibleFileException {
            ArrayList<Route> correctArray = new ArrayList<>();
            correctArray.add(new Route("2B", "AER", "KZN", 0));
            correctArray.add(new Route("2B", "ASF", "KZN", 0));
            correctArray.add(new Route("2B", "ASF", "MRV", 0));

            RouteRW stream = new RouteRW(goodFileString);
            assertEquals(stream.readRoutes().size(), correctArray.size());
        }

        @Test
        public void readFileThrowsErrorBadFile() {
            RouteRW stream = new RouteRW(badFileString);
            assertThrows(FileFormatException.class, stream::readRoutes);
        }

        @Test
        public void readFileIgnoreLinesBadFile() throws FileFormatException, IncompatibleFileException {
            RouteRW stream = new RouteRW(badFileString);
            ArrayList<Integer> ignoreLines = new ArrayList<>();
            ignoreLines.add(2);
            ignoreLines.add(3);
            assertEquals(stream.readRoutes(ignoreLines).size(), 1);
        }

        @Test
        public void readFileThrowsErrorCorruptFile() {
            RouteRW stream = new RouteRW(corruptFileString);
            System.out.println();
            assertThrows(IncompatibleFileException.class, stream::readRoutes);
        }
    }
}