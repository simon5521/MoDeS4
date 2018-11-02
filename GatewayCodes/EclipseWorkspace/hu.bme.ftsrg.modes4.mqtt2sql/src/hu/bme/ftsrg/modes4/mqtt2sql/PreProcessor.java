package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import org.apache.log4j.Logger;







public class PreProcessor
{
  static final Logger logger = Logger.getLogger(PreProcessor.class);
  



  public PreProcessor() {}
  


  public int StringToInt(int min, int max, String input, JsonObject json, ArrayList<String> errorList)
    throws Exception
  {
    int in = -1;
    
    try
    {
      in = Integer.valueOf(input.substring(1, input.length() - 1)).intValue();
    }
    catch (Exception e)
    {
      logger.warn("Sensor Error, parse failed " + e.getMessage() + " On sensor " + json.get("SensorID"));
      
      errorList.add("error," + json.get("SensorID").toString().substring(1, json.get("SensorID").toString().length() - 1) + "," + input);
      
      logger.info(errorList.get(0));
      
      return -1;
    }
    



    try
    {
      if (in < min)
      {
        logger.warn("DataClasses.PreProcessor warning input too low: " + in + " min is: " + min);
        
        return -1;

      }
      


    }
    catch (Exception localException1)
    {


      try
      {

        if (in > max)
        {
          logger.warn("DataClasses.PreProcessor warning input too high: " + in + " max is: " + max);
          
          return -1;
        }
      }
      catch (Exception localException2) {}
    }
    










    return in;
  }
}