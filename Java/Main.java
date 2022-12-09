package Java;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Main {
    public static Database db = new Database();
    public static void main(String[] args) {
 
        db.connect();

        System.out.println("Vader_StationV2");

        Map<String, Integer> dataSet = db.resultSetToMap(db.getMeasureValue(1, 1));

        //Interface interFace = new Interface(dataSet);

        View veiw = new View(dataSet);


    }
}