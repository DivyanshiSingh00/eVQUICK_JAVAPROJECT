package com.shivangi.eVQUICK.models;

public class Slots {

        private int id;
        private static String totalSlots;
        private static String availableSlots;


        public static final String TABLE_NAME = "slots";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TOTAL_SLOTS = "totalSlots";
        public static final String COLUMN_AVAILABLE_SLOTS = "availableSlots";

        // SQL Query for table creation
        public static final String sqlQuery = "CREATE TABLE " + TABLE_NAME + "(\n" +
                "\t`id`\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`totalSlots`\tTEXT,\n" +
                "\t`availableSlots`\tTEXT,\n" +
                "\t`timestamp`\tDATETIME DEFAULT CURRENT_TIMESTAMP\n" +
                ");";

        public Slots(int id, String totalSlots, String availableSlots) {
            this.id = id;
            this.totalSlots = totalSlots;
            this.availableSlots = availableSlots;
        }

        public Slots(String totalSlots, String availableSlots) {
            this.totalSlots = totalSlots;
            this.availableSlots = availableSlots;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public static String getTotalSlots() {
            return totalSlots;
        }

        public void setTotalSlots(String totalSlots) {
            this.totalSlots = totalSlots;
        }

        public static String getAvailableSlots() {
            return availableSlots;
        }

        public void setAvailableSlots(String availableSlots) {
            this.availableSlots = availableSlots;
        }


}

