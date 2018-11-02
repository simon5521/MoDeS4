package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public class RailData2SQL extends Data2SQL
{
  private static final String topic = "sensor/rail";
  private static final String table = "raildata";
  
  public RailData2SQL()
  {
    super("sensor/rail");
  }
  



  public void connectionLost(Throwable arg0) {}
  



  public void deliveryComplete(IMqttDeliveryToken arg0) {}
  


  protected String getSQL(String json)
  {
    Gson gson = new Gson();
    RailData data = (RailData)gson.fromJson(json, RailData.class);
    String sql = "insert into raildata(Infra1,Infra2,AccX,AccY,AccZ,MicroTimestamp,SensorName) values(" + Integer.toString(data.getInfra1()) + "," + Integer.toString(data.getInfra2()) + "," + Double.toString(data.getAccX()) + "," + Double.toString(data.getAccY()) + "," + Double.toString(data.getAccZ()) + "," + data.getMicroTimeStamp() + ",'railtest')";
    return sql;
  }
}