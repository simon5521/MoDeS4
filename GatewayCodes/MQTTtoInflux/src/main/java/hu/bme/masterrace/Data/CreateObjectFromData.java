package hu.bme.masterrace.Data;

import hu.bme.masterrace.InfluxConnector.CameraDataInfluxConnector;
import hu.bme.masterrace.InfluxConnector.DHTDataInfluxConnector;
import hu.bme.masterrace.InfluxConnector.LightDataInfluxConnector;
import hu.bme.masterrace.InfluxConnector.RailDataInfluxConnector;
import hu.bme.masterrace.Main;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.effect.Light;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import static hu.bme.masterrace.Main.accDataInfluxConnector;

public class CreateObjectFromData {

    public CreateObjectFromData(String myClass, MqttMessage message) {

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(message.toString());

        switch (myClass) {
            case "AccData":
                AccData accData = new AccData(jsonObject);
                accDataInfluxConnector.addData(accData);
                break;
            case "CameraData":
                CameraData cameraData = new CameraData(jsonObject);
                CameraDataInfluxConnector.addData(cameraData);
                break;
            case "DHTData":
                DHTData dhtData = new DHTData(jsonObject);
                DHTDataInfluxConnector.addData(dhtData);
                break;
            case "LightData":
                LightData lightData = new LightData(jsonObject);
                LightDataInfluxConnector.addData(lightData);
                break;
            case "RailData":
                RailData railData = new RailData(jsonObject);
                RailDataInfluxConnector.addData(railData);
                break;
        }

    }

}
