package com.mti.veloid;

import java.text.FieldPosition;

/**
 * Created by TheTrope on 22/05/2017.
 */


/**
 * Define a VelibStation
 * Fields are encapsulated on the field attribute
 */

public class VelibStation {
    private String datasetid;
    private String recordid;
    private Fields fields;

    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    public Fields getFields() {
        return fields;
    }
    public String toString(){
        return fields.toString();
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public class Fields {
        private String status;
        private String contract_name;
        private String name;
        private Boolean bonus;
        private int bike_stands;
        private int number;
        private String last_update;
        private int available_bike_stands;
        private Boolean banking;
        private int available_bikes;
        private String address;
        private Float[] position;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContract_name() {
            return contract_name;
        }

        public void setContract_name(String contract_name) {
            this.contract_name = contract_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Boolean getBonus() {
            return bonus;
        }

        public void setBonus(Boolean bonus) {
            this.bonus = bonus;
        }

        public int getBike_stands() {
            return bike_stands;
        }

        public void setBike_stands(int bike_stands) {
            this.bike_stands = bike_stands;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public int getAvailable_bike_stands() {
            return available_bike_stands;
        }

        public void setAvailable_bike_stands(int available_bike_stands) {
            this.available_bike_stands = available_bike_stands;
        }

        public Boolean getBanking() {
            return banking;
        }

        public void setBanking(Boolean banking) {
            this.banking = banking;
        }

        public int getAvailable_bikes() {
            return available_bikes;
        }

        public void setAvailable_bikes(int available_bikes) {
            this.available_bikes = available_bikes;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Float[] getPosition() {
            return position;
        }

        public void setPosition(Float[] position) {
            this.position = position;
        }

        public String toString(){
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(" - ");
            sb.append(status);
            sb.append(" : ");
            sb.append(address);
            sb.append(" | ");
            sb.append(available_bikes);
            sb.append("available bikes and ");
            sb.append(available_bike_stands);
            sb.append("available stands");
            return sb.toString();

        }
    }
}