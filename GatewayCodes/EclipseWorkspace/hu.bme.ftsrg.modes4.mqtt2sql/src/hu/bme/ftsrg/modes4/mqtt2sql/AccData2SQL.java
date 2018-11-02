package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public class AccData2SQL extends Data2SQL
{
  private static final String topic = "sensor/acc";
  private static final String table = "trainsensordata";
  
  public AccData2SQL()
  {
    super("sensor/acc");
  }
  



  public void connectionLost(Throwable arg0) {}
  



  public void deliveryComplete(IMqttDeliveryToken arg0) {}
  


  protected String getSQL(String json)
  {
    Gson gson = new Gson();
    AccData data = (AccData)gson.fromJson(json, AccData.class);
    String sql = "insert into trainsensordata(AccX,AccY,AccZ,MicroTimestamp,SensorName) values(" + Double.toString(data.getAccX()) + "," + Double.toString(data.getAccY()) + "," + Double.toString(data.getAccZ()) + "," + data.getMicroTimeStamp() + ",'train_test_1')";
    return sql;
  }
}