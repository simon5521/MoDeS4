package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;










public class AccData
  extends Data
{
  private double AccX;
  private double AccY;
  private double AccZ;
  
  public AccData(String sensorID, String type, String microTimeStamp, double accX, double accY, double accZ)
  {
    super(sensorID, type, microTimeStamp);
    
    AccX = accX;
    
    AccY = accY;
    
    AccZ = accZ;
  }
  

  public AccData(JsonObject json)
  {
    super(json);
    
    AccX = Double.valueOf(json.get("AccX").toString().substring(1, json.get("AccX").toString().length() - 1)).doubleValue();
    
    AccY = Double.valueOf(json.get("AccY").toString().substring(1, json.get("AccY").toString().length() - 1)).doubleValue();
    
    AccZ = Double.valueOf(json.get("AccZ").toString().substring(1, json.get("AccZ").toString().length() - 1)).doubleValue();
  }
  




  public AccData() {}
  



  public int getLight()
  {
    return -1;
  }
  





  public String toSparkformat()
  {
    return null;
  }
  



  public double getAccX()
  {
    return AccX;
  }
  



  public void setAccX(double accX)
  {
    AccX = accX;
  }
  



  public double getAccY()
  {
    return AccY;
  }
  



  public void setAccY(double accY)
  {
    AccY = accY;
  }
  



  public double getAccZ()
  {
    return AccZ;
  }
  



  public void setAccZ(double accZ)
  {
    AccZ = accZ;
  }
  





  public String toClassID()
  {
    return "acc";
  }
}