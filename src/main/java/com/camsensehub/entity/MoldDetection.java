package com.camsensehub.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@Document(collection = "MoldDetection")
public class MoldDetection {
    private String camera;

    private boolean moldDetection;

    private Date stampDate;


    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }


    public boolean isMoldDetection() {
        return moldDetection;
    }

    public void setMoldDetection(boolean moldDetection) {
        this.moldDetection = moldDetection;
    }

    public Date getStampDate() {
        return stampDate;
    }

    public void setStampDate(Date stampDate) {
        this.stampDate = stampDate;
    }

    @Override
    public String toString() {
        return "MoldDetection{" +
                "camera='" + camera + '\'' +
                ", moldDetection=" + moldDetection +
                ", stampDate=" + stampDate +
                '}';
    }
}
