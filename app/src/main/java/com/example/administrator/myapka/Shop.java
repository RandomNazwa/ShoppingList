package com.example.administrator.myapka;

public class Shop {

        public String id;
        public String name;
        public String description;
        public String radius;
        public String latitude;
        public String longitude;

    public Shop(String id, String name, String description, String radius, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.radius = radius;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getRadius() {
            return radius;
        }

        public void setRadius(String radius) {
            this.radius = radius;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }
    }