package com.camsensehub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import java.util.Date;


@Document(collection = "WeatherDetection")
public class WeatherDetection {
    @Id
    private ObjectId camera;

    private boolean weatherDetection;

    private Date stampDate;


    public ObjectId getCamera() {
        return camera;
    }

    public void setCamera(ObjectId camera) {
        this.camera = camera;
    }


    public boolean isWeatherDetection() {
        return weatherDetection;
    }

    public void setWeatherDetection(boolean weatherDetection) {
        this.weatherDetection = weatherDetection;
    }

    public Date getStampDate() {
        return stampDate;
    }

    public void setStampDate(Date stampDate) {
        this.stampDate = stampDate;
    }

    @Override
    public String toString() {
        return "WeatherDetection{" +
                "camera='" + camera + '\'' +
                ", weatherDetection=" + weatherDetection +
                ", stampDate=" + stampDate +
                '}';
    }
}
