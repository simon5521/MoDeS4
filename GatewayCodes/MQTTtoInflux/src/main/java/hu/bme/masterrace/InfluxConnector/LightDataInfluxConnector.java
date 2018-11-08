package hu.bme.masterrace.InfluxConnector;

import hu.bme.masterrace.Data.LightData;
import org.influxdb.dto.Point;

import java.util.concurrent.TimeUnit;

import static java.lang.System.currentTimeMillis;

public class LightDataInfluxConnector extends InfluxConnector {

    public static void addData(LightData lightData) {

        databaseConnection.write(
                Point.measurement("LightSensorRaw")
                        .time(currentTimeMillis(), TimeUnit.MILLISECONDS)
                        .tag("sensorName", lightData.getSensorID())
                        .addField("light", lightData.getLight())
                        .addField("microTimeStamp", lightData.getMicroTimeStamp())
                        .build());

        System.out.println("sent data on " + currentTimeMillis() + lightData.toString());
    }
}
