package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public class DHTData2SQL extends Data2SQL
{
  private static final String topic = "raw/dht";
  private static final String table = "weatherdata";
  
  public DHTData2SQL()
  {
    super("raw/dht");
  }
  



  public void connectionLost(Throwable arg0) {}
  



  public void deliveryComplete(IMqttDeliveryToken arg0) {}
  


  protected String getSQL(String json)
  {
    Gson gson = new Gson();
    DHTData data = (DHTData)gson.fromJson(json, DHTData.class);
    String sql = "insert into weatherdata(Temp,Hum,Pressure,MicroTimestamp,SensorName) values(" + Double.toString(data.getTemperature()) + "," + Double.toString(data.getHumidity()) + "," + Double.toString(0) + "," + data.getMicroTimeStamp() + ",'train_test_1')";
    return sql;
  }
}