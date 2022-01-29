package com.model;

public class BuyerRequest {
    private String colour = "";
    private String size = "";
    private String gender = "";
    private String sortBy = "";

    public BuyerRequest() {
    }

    public BuyerRequest(String colour, String size, String gender, String sortBy) {
        this.colour = colour;
        this.size = size;
        this.gender = gender;
        this.sortBy = sortBy;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public String toString() {
        return "BuyerRequest{" +
                "colour='" + colour + '\'' +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                ", sortBy='" + sortBy + '\'' +
                '}';
    }
}
