package com.shivangi.eVQUICK.models;

public class ChargingStation {

        private String name;
        private String location;
        private String latitude;
        private String longitude;
        private String totalSlots;
        private String availableSlots;


        public ChargingStation() {}

        public ChargingStation(String name, String location, String latitude, String longitude, String totalSlots, String availableSlots) {
            this.name = name;
            this.location = location;
            this.latitude = latitude;

            this.longitude = longitude;
            this.totalSlots = totalSlots;
            this.availableSlots = availableSlots;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
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

        public String getTotalSlots() {
            return totalSlots;
        }

        public void setTotalSlots(String totalSlots) {
            this.totalSlots = totalSlots;
        }

        public String getAvailableSlots() {
            return availableSlots;
        }

        public void setAvailableSlots(String availableSlots) {
            this.availableSlots = availableSlots;
        }
    }

