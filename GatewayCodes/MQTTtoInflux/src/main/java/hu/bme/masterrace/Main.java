package hu.bme.masterrace;

import hu.bme.masterrace.Data.CreateObjectFromData;
import hu.bme.masterrace.InfluxConnector.AccDataInfluxConnector;

public class Main {

    public static AccDataInfluxConnector accDataInfluxConnector = null;
    public static CreateObjectFromData createObjectFromData = null;


    public static void main(String[] args) {
        accDataInfluxConnector = new AccDataInfluxConnector();
        String serverURL = "tcp://127.0.0.1:1883";
        new MqttSubscriber(serverURL, "raw/acc", "AccData");
        new MqttSubscriber(serverURL, "raw/dht", "DHTData");
        new MqttSubscriber(serverURL, "raw/cameradata", "CameraData");
        new MqttSubscriber(serverURL, "raw/rail", "RailData");
        new MqttSubscriber(serverURL, "raw/light", "LightData");

    }
}
