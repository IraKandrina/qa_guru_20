package hw10.model;

import java.util.List;

public class EllipseModel {
    private String objectType;
    private String id;
    private List<Coords> coords;
    private Tags tags;

    public String getObjectType() {
        return objectType;
    }

    public String getId() {
        return id;
    }

    public List<Coords> getCoords() {
        return coords;
    }

    public Tags getTags() {
        return tags;
    }

    public static class Coords {
        private double latitude;
        private double longitude;


        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    public static class Tags {
        private String creator;
        private boolean draggable;

        public String getCreator() {
            return creator;
        }

        public boolean isDraggable() {
            return draggable;
        }
    }


}
