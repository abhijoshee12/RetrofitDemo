package com.example.myapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class MyItems{

    @SerializedName("itemData")
    List<ItemData> itemData;

    public List<ItemData> getItemData() {
        return itemData;
    }
}
class ItemData{
    @SerializedName("id")

    int id;
    @SerializedName("title")

    String title;
    @SerializedName("image")

    String image;
    @SerializedName("description")

    String description;


    public ItemData(int id, String title, String image, String description) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public ItemData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MyItems{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
