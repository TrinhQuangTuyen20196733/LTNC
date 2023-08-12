package Config;

import java.util.HashMap;
import java.util.Map;

public class TableMapper {
	public static Map<String, String> TableMap ;
       static {
    	   TableMap = new HashMap<>();
           TableMap.put("UserEntity", "user");
           TableMap.put("Airline", "airline");
           TableMap.put("Plane", "plane");
           TableMap.put("Seat", "seat");
           TableMap.put("Flight", "flight");
           TableMap.put("Service", "service");
}
       }