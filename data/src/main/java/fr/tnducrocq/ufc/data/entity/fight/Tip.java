package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class Tip {

    @SerializedName("Standing Time")
    @Expose
    private String standingTime;

    @SerializedName("Ground Time")
    @Expose
    private String groundTime;

    @SerializedName("Control Time")
    @Expose
    private String controlTime;

    @SerializedName("Neutral Time")
    @Expose
    private String neutralTime;

    @SerializedName("Ground Control Time")
    @Expose
    private String groundControlTime;

    @SerializedName("Distance Time")
    @Expose
    private String distanceTime;

    @SerializedName("Clinch Time")
    @Expose
    private String clinchTime;

    @SerializedName("Misc. Ground Control Time")
    @Expose
    private String miscGroundControlTime;

    @SerializedName("Guard Control Time")
    @Expose
    private String guardControlTime;

    @SerializedName("Half Guard Control Time")
    @Expose
    private String halfGuardControlTime;

    @SerializedName("Side Control Time")
    @Expose
    private String sideControlTime;

    @SerializedName("Mount Control Time")
    @Expose
    private String mountControlTime;

    @SerializedName("Back Control Time")
    @Expose
    private String backControlTime;

    public String getStandingTime() {
        return standingTime;
    }

    public void setStandingTime(String standingTime) {
        this.standingTime = standingTime;
    }

    public String getGroundTime() {
        return groundTime;
    }

    public void setGroundTime(String groundTime) {
        this.groundTime = groundTime;
    }

    public String getControlTime() {
        return controlTime;
    }

    public void setControlTime(String controlTime) {
        this.controlTime = controlTime;
    }

    public String getNeutralTime() {
        return neutralTime;
    }

    public void setNeutralTime(String neutralTime) {
        this.neutralTime = neutralTime;
    }

    public String getGroundControlTime() {
        return groundControlTime;
    }

    public void setGroundControlTime(String groundControlTime) {
        this.groundControlTime = groundControlTime;
    }

    public String getDistanceTime() {
        return distanceTime;
    }

    public void setDistanceTime(String distanceTime) {
        this.distanceTime = distanceTime;
    }

    public String getClinchTime() {
        return clinchTime;
    }

    public void setClinchTime(String clinchTime) {
        this.clinchTime = clinchTime;
    }

    public String getMiscGroundControlTime() {
        return miscGroundControlTime;
    }

    public void setMiscGroundControlTime(String miscGroundControlTime) {
        this.miscGroundControlTime = miscGroundControlTime;
    }

    public String getGuardControlTime() {
        return guardControlTime;
    }

    public void setGuardControlTime(String guardControlTime) {
        this.guardControlTime = guardControlTime;
    }

    public String getHalfGuardControlTime() {
        return halfGuardControlTime;
    }

    public void setHalfGuardControlTime(String halfGuardControlTime) {
        this.halfGuardControlTime = halfGuardControlTime;
    }

    public String getSideControlTime() {
        return sideControlTime;
    }

    public void setSideControlTime(String sideControlTime) {
        this.sideControlTime = sideControlTime;
    }

    public String getMountControlTime() {
        return mountControlTime;
    }

    public void setMountControlTime(String mountControlTime) {
        this.mountControlTime = mountControlTime;
    }

    public String getBackControlTime() {
        return backControlTime;
    }

    public void setBackControlTime(String backControlTime) {
        this.backControlTime = backControlTime;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tip{");
        sb.append("standingTime='").append(standingTime).append('\'');
        sb.append(", groundTime='").append(groundTime).append('\'');
        sb.append(", controlTime='").append(controlTime).append('\'');
        sb.append(", neutralTime='").append(neutralTime).append('\'');
        sb.append(", groundControlTime='").append(groundControlTime).append('\'');
        sb.append(", distanceTime='").append(distanceTime).append('\'');
        sb.append(", clinchTime='").append(clinchTime).append('\'');
        sb.append(", miscGroundControlTime='").append(miscGroundControlTime).append('\'');
        sb.append(", guardControlTime='").append(guardControlTime).append('\'');
        sb.append(", halfGuardControlTime='").append(halfGuardControlTime).append('\'');
        sb.append(", sideControlTime='").append(sideControlTime).append('\'');
        sb.append(", mountControlTime='").append(mountControlTime).append('\'');
        sb.append(", backControlTime='").append(backControlTime).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
