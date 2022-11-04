package Java;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {




    public static void main(String[] args) {
 
        Database db = new Database();
        db.connect();

        System.out.println("Vadeer vaader VAADER");

        ResultSet rs1 = db.getEspID();
        ResultSet rs2 = db.getSensorID();
   
        try{

            while(rs2.next()){
                String a = rs2.getString(1);
                System.out.println("Sensor_ID = " + a);
            }

            while(rs1.next()){
                String a = rs1.getString(1);
                System.out.println("ESP_ID = " + a);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}