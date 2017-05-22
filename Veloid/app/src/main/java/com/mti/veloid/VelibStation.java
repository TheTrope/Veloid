package com.mti.veloid;

/**
 * Created by TheTrope on 22/05/2017.
 */

public class VelibStation {
    private int id;
    private Boolean status;
    private String name;
    private int maxBikeStands;
    private int availableBikeStands;
    private String adress;
    private String lastUpdate;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxBikeStands() {
        return maxBikeStands;
    }

    public void setMaxBikeStands(int maxBikeStands) {
        this.maxBikeStands = maxBikeStands;
    }

    public int getAvailableBikeStands() {
        return availableBikeStands;
    }

    public void setAvailableBikeStands(int availableBikeStands) {
        this.availableBikeStands = availableBikeStands;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
