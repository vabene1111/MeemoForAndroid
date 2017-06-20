package de.droidenschmiede.meemoforandroid.objects;

/**
 * Created by vabene1111 on 20.06.2017.
 * Represents Meeomo Attachment Object
 */

public class Attachment {
    private String identifier;
    private String fileName;
    private String type;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
