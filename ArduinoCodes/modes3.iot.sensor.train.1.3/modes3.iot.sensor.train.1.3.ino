#include <Wire.h>
#include "FS.h"
#include <ESP8266WiFi.h>

#include <ArduinoJson.h>


#include "MPU9250.h"
#include <PubSubClient.h>

WiFiClient espClient;
PubSubClient client(espClient);
const char* TOPIC="raw/acc";
const char* MQTTID = "mqtt-test";
const char* MQTTPSWD = "mqtt-test";


// an MPU9250 object with the MPU-9250 sensor on I2C bus 0 with address 0x68
MPU9250 IMU(Wire,0x68);
int status;


void mqttconnect() {
  /* Loop until reconnected */
  while (!client.connected()) {
    Serial.print("MQTT connecting ...");
    /* client ID */
    String clientId = "TRAINNODE01";
    /* connect now */
    if (client.connect("train_sensor_2")) {
      Serial.println("connected");
    } else {
      Serial.print("failed, status code =");
      Serial.print(client.state());
      Serial.println("try again in 5 seconds");
      /* Wait 5 seconds before retrying */
      delay(5000);
    }
  }
}


#define MQTTCONN
#define LABORWIFI

#ifdef LABWIFI
char* ssid     = "Container";
char* password = "LaborImage";
char* host = "192.168.1.239";
#endif
#ifdef LABORWIFI
char* ssid     = "MoDeS3";
char* password = "LaborImage";
char* host = "192.168.1.230";
#endif
#ifdef OTTHONWIFI
char* ssid     = "VargaK";
char* password = "14Basipa";
const char* host = "192.168.1.239";
#endif
#ifdef UTIWIFI
char* ssid     = "HungarianWiFi";
char* password = "flower01";
char* host = "192.168.43.199";
#endif
#ifdef JENOWIFI
char* ssid     = "szepnapot";
char* password = "analizis";
char* host = "192.168.43.208";
#endif

const int serverPort = 5502;


/* Assign a unique ID to this sensor at the same time */

int period = 1000;


void setup(void)
{
  Serial.begin(115200);
  Serial.println("Accelerometer Test"); Serial.println("");

  // start communication with IMU 
  status = IMU.begin();
  if (status < 0) {
    Serial.println("IMU initialization unsuccessful");
    Serial.println("Check IMU wiring or try cycling power");
    Serial.print("Status: ");
    Serial.println(status);
    while(1) {}
  }
  
  Serial.println("initalising network communication");
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());


  client.setServer(host,1883);
  mqttconnect();



}

void loop(void)
{
  int t0 = micros();
  /* Get a new sensor event */
  // read the sensor
  IMU.readSensor();

  
  // display the data
  Serial.print(IMU.getAccelX_mss(),6);
  Serial.print("\t");
  Serial.print(IMU.getAccelY_mss(),6);
  Serial.print("\t");
  Serial.print(IMU.getAccelZ_mss(),6);
  Serial.print("\t");
  Serial.print(IMU.getGyroX_rads(),6);
  Serial.print("\t");
  Serial.print(IMU.getGyroY_rads(),6);
  Serial.print("\t");
  Serial.print(IMU.getGyroZ_rads(),6);
  Serial.print("\t");
  Serial.print(IMU.getMagX_uT(),6);
  Serial.print("\t");
  Serial.print(IMU.getMagY_uT(),6);
  Serial.print("\t");
  Serial.print(IMU.getMagZ_uT(),6);
  Serial.print("\t");

  Serial.println("put the data into JSON");

  // Allocate JsonBuffer
  // Use arduinojson.org/assistant to compute the capacity.

  StaticJsonBuffer<1024> jsonBuffer;

  // Create the root object
  JsonObject& root = jsonBuffer.createObject();

  root["SensorID"] = "009";

  root["AccX"] = String(IMU.getAccelX_mss());
  root["AccY"] = String(IMU.getAccelY_mss());
  root["AccZ"] = String(IMU.getAccelZ_mss());

  root["MicroTimeStamp"] = String(millis());

  root["Type"] = "acc";
  #ifndef MQTTCONN
  root.printTo(Serial);


  Serial.println("sending the data with TCP");


  Serial.println("connecting to server");
  WiFiClient client;

  if (!client.connect(host, serverPort)) {
    Serial.println("connection failed");
    return;
  }

  Serial.println("sending data");

  root.printTo(client);
#endif


  String message;
  root.printTo(message);
  char msg[1024];
  message.toCharArray(msg,1024);
  Serial.println(msg);
  client.publish(TOPIC, msg);
  Serial.println("massage has published");




  delay(1000);

}
