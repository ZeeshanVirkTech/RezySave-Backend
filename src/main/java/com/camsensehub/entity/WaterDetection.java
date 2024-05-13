package com.camsensehub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import java.util.Date;


@Document(collection = "WaterDetection")
public class WaterDetection {
    private ObjectId camera;

    private boolean waterDetection;

    private Date stampDate;


    public ObjectId getCamera() {
        return camera;
    }

    public void setCamera(ObjectId camera) {
        this.camera = camera;
    }


    public boolean isWaterDetection() {
        return waterDetection;
    }

    public void setWaterDetection(boolean waterDetection) {
        this.waterDetection = waterDetection;
    }

    public Date getStampDate() {
        return stampDate;
    }

    public void setStampDate(Date stampDate) {
        this.stampDate = stampDate;
    }

    @Override
    public String toString() {
        return "WaterDetection{" +
                "camera='" + camera + '\'' +
                ", waterDetection=" + waterDetection +
                ", stampDate=" + stampDate +
                '}';
    }
}
