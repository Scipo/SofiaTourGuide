package DB;

public class PlacesDBSchema {
    public static final class PlaceTable{
        public  static final String NAME = "name";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String Code ="code";
            public static final String Name = "name";
            public static final String Description = "description";
            public static final String Address = "address";
            public static final String Schedule = "schedule";
            public static final String Price = "0.0";
            public static final Double Lat = 0.0;
            public static final Double Lon = 0.0;
            public static final int ImageResourceId = 0;
        }
    }
}
