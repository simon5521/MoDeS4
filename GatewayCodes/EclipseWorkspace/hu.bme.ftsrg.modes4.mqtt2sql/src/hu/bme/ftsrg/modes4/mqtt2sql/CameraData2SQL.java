package hu.bme.ftsrg.modes4.mqtt2sql;

import com.google.gson.Gson;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;

public class CameraData2SQL extends Data2SQL
{
  private static final String topic = "raw/cameradata";
  private static final String table = "cameradata";
  
  public CameraData2SQL()
  {
    super("raw/cameradata");
  }
  



  public void connectionLost(Throwable arg0) {}
  



  public void deliveryComplete(IMqttDeliveryToken arg0) {}
  


  protected String getSQL(String json)
  {
    Gson gson = new Gson();
    CameraData data = (CameraData)gson.fromJson(json, CameraData.class);
    String sql = "insert into cameradata(textlabel,boxtop,boxbottom,boxleft,boxright,upperbound,lowerbound,MicroTimestamp,SensorName) values(" + data.getLabelText() + "," + Double.toString(data.getBoxTop()) + "," + Double.toString(data.getBoxBottom()) + "," + Double.toString(data.getBoxLeft()) + "," + Double.toString(data.getBoxRight()) + "," + data.getMicroTimeStamp() + ",'train_test_1')";
    return sql;
  }
}