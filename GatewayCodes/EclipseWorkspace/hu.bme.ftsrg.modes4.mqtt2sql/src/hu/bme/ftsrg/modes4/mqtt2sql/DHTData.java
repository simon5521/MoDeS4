

package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;







public class DHTData
  extends Data
{
  private double Temperature;
  private double Humidity;
  
  public DHTData(String sensorID, String type, String microTimeStamp, double temperature, double humidity)
  {
    super(sensorID, type, microTimeStamp);
    
    Temperature = temperature;
    
    Humidity = humidity;
  }
  

  public DHTData(JsonObject json)
  {
    super(json);
    


    Temperature = Double.valueOf(json.get("Temperature").toString()).doubleValue();
    
    Humidity = Double.valueOf(json.get("Humidity").toString()).doubleValue();
  }
  



  public double getTemperature()
  {
    return Temperature;
  }
  



  public void setTemperature(double temperature)
  {
    Temperature = temperature;
  }
  



  public double getHumidity()
  {
    return Humidity;
  }
  



  public void setHumidity(double humidity)
  {
    Humidity = humidity;
  }
  





  public String toClassID()
  {
    return "dht";
  }
  





  public int getLight()
  {
    return -1;
  }
  





  public String toSparkformat()
  {
    return null;
  }
}



