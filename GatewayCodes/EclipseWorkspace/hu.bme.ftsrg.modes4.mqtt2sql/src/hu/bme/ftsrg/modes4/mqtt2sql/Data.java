package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.JsonObject;
import org.apache.log4j.Logger;














public abstract class Data
{
  static final Logger logger = Logger.getLogger(Data.class);
  

  private String SensorID;
  

  private String Type;
  

  private String MicroTimeStamp;
  

  public Data(String sensorID, String type, String microTimeStamp)
  {
    SensorID = sensorID;
    
    Type = type;
    
    MicroTimeStamp = microTimeStamp;
  }
  



  public Data(JsonObject json)
  {
    try
    {
      SensorID = json.get("SensorID").toString();
      
      Type = json.get("Type").toString();
      
      MicroTimeStamp = json.get("MicroTimeStamp").toString();
    }
    catch (Exception e)
    {
      logger.error("Parse failed" + json.get("SensorID").toString());
    }
  }
  




  public abstract String toClassID();
  




  public Data() {}
  




  public String getSensorID()
  {
    return SensorID;
  }
  



  public void setSensorID(String sensorID)
  {
    SensorID = sensorID;
  }
  



  public String getType()
  {
    return Type;
  }
  



  public void setType(String type)
  {
    Type = type;
  }
  



  public String getMicroTimeStamp()
  {
    return MicroTimeStamp;
  }
  
  public abstract int getLight();
  
  public abstract String toSparkformat();
}