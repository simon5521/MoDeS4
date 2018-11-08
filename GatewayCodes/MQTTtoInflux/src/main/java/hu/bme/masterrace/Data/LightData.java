package hu.bme.masterrace.Data;

import com.google.gson.JsonObject;

public class LightData extends Data {

    private int light;

    public LightData(String sensorID, String type, String microTimeStamp, int light) {
        super(sensorID, type, microTimeStamp);
        this.light = light;
    }
    // {"SensorID":"mySensorLight", "Type":"Light", "MicroTimeStamp":"11111", "Light":"500"}

    LightData(JsonObject json) {
        super(json);
        light =Integer.parseInt(json.get("Light").toString());
    }

    public int getLight() {
        return light;
    }

    public String toString(){
        return ("LightData: " + "Light: " + getLight()
                +" SensorID: " + this.getSensorID() + " MicroTimeStamp" + this.getMicroTimeStamp());
    }

}