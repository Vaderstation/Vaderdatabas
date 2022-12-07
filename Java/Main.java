package Java;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
 
        Database db = new Database();
        db.connect();

        System.out.println("Vadeer vaader VAADER");

        

        Map<String, Integer> dataSet = db.resultSetToMap(db.getMeasureValue(1, 1));

        Interface interFace = new Interface(dataSet);
    }
}