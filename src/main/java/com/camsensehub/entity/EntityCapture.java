package com.camsensehub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;
import java.util.Date;


@Document(collection = "EntityCapture")
public class EntityCapture {
    private String camera;

    private boolean entityDetection;

    private Date stampDate;


    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }


    public boolean isEntityDetection() {
        return entityDetection;
    }

    public void setEntityDetection(boolean entityDetection) {
        this.entityDetection = entityDetection;
    }

    public Date getStampDate() {
        return stampDate;
    }

    public void setStampDate(Date stampDate) {
        this.stampDate = stampDate;
    }

    @Override
    public String toString() {
        return "EntityCapture{" +
                "entityDetection='" + entityDetection + '\'' +
                ", camera='" + camera + '\'' +
                ", stampDate=" + stampDate +
                '}';
    }
}
