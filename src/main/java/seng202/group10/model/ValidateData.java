package seng202.group10.model;

import java.util.ArrayList;

public class ValidateData {

    public static boolean Airline(ArrayList data) {
        int targetSize = 8;
        return (data.size() == targetSize);
    }

    public static boolean Airport(ArrayList data) {
        int targetSize = 12;
        return (data.size() == targetSize);
    }

    public static boolean Aircraft(ArrayList data) {
        return true;
    }

    public static boolean Route(ArrayList data) {
        int targetSize = 9;
        return (data.size() == targetSize);
    }

}
