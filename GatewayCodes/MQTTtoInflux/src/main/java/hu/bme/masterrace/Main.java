package hu.bme.masterrace;

public class Main {

    public static void main(String[] args) {
        String serverURL = "tcp://127.0.0.1:1883";
        new MqttSubscriber(serverURL, "raw/acc", "AccData");
        new MqttSubscriber(serverURL, "raw/dht", "DHTData");
        new MqttSubscriber(serverURL, "raw/cameradata", "CameraData");
        new MqttSubscriber(serverURL, "raw/rail", "RailData");
        new MqttSubscriber(serverURL, "raw/light", "LightData");
    }
}
