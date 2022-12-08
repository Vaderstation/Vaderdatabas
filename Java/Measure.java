package Java;

public class Measure {

    public Measure(){
       
    }

    public double getMeanTemp(double max, double min){
        return meanValue(max, min);
    }

    public double getMeanHumidity(double max, double min){
        return meanValue(max, min);
    }

    public double getMeanGas(double max, double min){
        return meanValue(max, min);
    }

    private double meanValue(double max, double min){
        double mean = 0;

        mean = (max+min)/2;

        return mean; 
    } 

    public int checkTemp(double temp){
        if((temp>30 || temp<20) && temp != 0.0){
            return 1;
        }
        
        if(temp == 0.0){
            return 0;
        }
        else{
            return 0;
        }
    }

    public int checkHumidity(double Humidity){
        if(Humidity>55){
            return 1;
        }
        if(Humidity == 0.0){
            return 0;
        }
        else{
            return 0; 
        }
    }

    public int checkGas(double gas){
        if(gas>1000){
            return 1; 
        }
        if(gas == 0.0){
            return 0; 
        }
        else{
            return 0; 
        }
    }

}
