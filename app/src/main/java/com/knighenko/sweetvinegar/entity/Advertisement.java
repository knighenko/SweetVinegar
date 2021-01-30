package com.knighenko.sweetvinegar.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class Advertisement implements Parcelable {

    private String id;
    private String title;
    private String url;
    private String imageSrc;
    private String description;

    public Advertisement() {

    }

    public Advertisement(String id,String title, String url, String imageSrc, String description) {
        this.id=id;
        this.title = title;
        this.url = url;
        this.imageSrc = imageSrc;
        this.description = description;
    }


    protected Advertisement(Parcel in) {
        id=in.readString();
        title = in.readString();
        url = in.readString();
        imageSrc = in.readString();
        description = in.readString();
    }

    public static final Creator<Advertisement> CREATOR = new Creator<Advertisement>() {
        @Override
        public Advertisement createFromParcel(Parcel in) {
            return new Advertisement(in);
        }

        @Override
        public Advertisement[] newArray(int size) {
            return new Advertisement[size];
        }
    };


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    /**
     * Method return string - description of advertisement in JSON
     */
    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder("{");
        stringBuffer.append("\"id\":\"").append(id).append('\"').append(", \"title\":\"").append(title).append('\"').append(", \"url\":\"").append(url).append('\"').
                append(", \"imageSrc\":\"").append(imageSrc).append('\"').append(", \"description\":\"").append(description).append('\"').append("}");

        return stringBuffer.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(url);
        parcel.writeString(imageSrc);
        parcel.writeString(description);
    }
}

