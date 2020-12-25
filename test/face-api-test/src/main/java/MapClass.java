
public class MapClass {
    private String image;
    private String image_type="BASE64";
    private String face_type="LIVE";
    private String quality_control="LOW";
    private String liveness_control="HIGH";

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getFace_type() {
        return face_type;
    }

    public void setFace_type(String face_type) {
        this.face_type = face_type;
    }

    public String getQuality_control() {
        return quality_control;
    }

    public void setQuality_control(String quality_control) {
        this.quality_control = quality_control;
    }

    public String getLiveness_control() {
        return liveness_control;
    }

    public void setLiveness_control(String liveness_control) {
        this.liveness_control = liveness_control;
    }

    @Override
    public String toString() {
        return "{"+
                "\"image\":" + image + ',' +
                ", image_type='" + image_type + ',' +
                ", face_type='" + face_type + ',' +
                ", quality_control='" + quality_control + ',' +
                ", liveness_control='" + liveness_control + ',' +
                '}';
    }
}
