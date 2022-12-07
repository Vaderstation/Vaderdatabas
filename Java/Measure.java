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

}
