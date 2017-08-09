package fr.tnducrocq.ufc.data.entity.fight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by tony on 26/07/2017.
 */

public class Strikes {

    @SerializedName("Knock Down")
    @Expose
    private Action knockDown;

    @SerializedName("Significant Strikes")
    @Expose
    private Action significantStrikes;

    @SerializedName("Total Strikes")
    @Expose
    private Action totalStrikes;

    @SerializedName("Punches")
    @Expose
    private Action punches;

    @SerializedName("Kicks")
    @Expose
    private Action kicks;

    @SerializedName("Distance Strikes")
    @Expose
    private Action distanceStrikes;

    @SerializedName("Clinch Significant Strikes")
    @Expose
    private Action clinchSignificantStrikes;

    @SerializedName("Ground Significant Strikes")
    @Expose
    private Action groundSignificantStrikes;

    @SerializedName("Clinch Total Strikes")
    @Expose
    private Action clinchTotalStrikes;

    @SerializedName("Ground Total Strikes")
    @Expose
    private Action groundTotalStrikes;

    @SerializedName("Head Significant Strikes")
    @Expose
    private Action headSignificantStrikes;

    @SerializedName("Body Significant Strikes")
    @Expose
    private Action bodySignificantStrikes;

    @SerializedName("Legs Significant Strikes")
    @Expose
    private Action legsSignificantStrikes;

    @SerializedName("Head Total Strikes")
    @Expose
    private Action headTotalStrikes;

    @SerializedName("Body Total Strikes")
    @Expose
    private Action bodyTotalStrikes;

    @SerializedName("Legs Total Strikes")
    @Expose
    private Action legsTotalStrikes;

    @SerializedName("Distance Head Strikes")
    @Expose
    private Action distanceHeadStrikes;

    @SerializedName("Distance Body Strikes")
    @Expose
    private Action distanceBodyStrikes;

    @SerializedName("Distance Leg Strikes")
    @Expose
    private Action distanceLegStrikes;

    @SerializedName("Clinch Head Strikes")
    @Expose
    private Action clinchHeadStrikes;

    @SerializedName("Clinch Body Strikes")
    @Expose
    private Action clinchBodyStrikes;

    @SerializedName("Clinch Leg Strikes")
    @Expose
    private Action clinchLegStrikes;

    @SerializedName("Ground Head Strikes")
    @Expose
    private Action groundHeadStrikes;

    @SerializedName("Ground Body Strikes")
    @Expose
    private Action groundBodyStrikes;

    @SerializedName("Ground Leg Strikes")
    @Expose
    private Action groundLegStrikes;

    @SerializedName("Distance Head Kicks")
    @Expose
    private Action distanceHeadKicks;

    @SerializedName("Distance Body Kicks")
    @Expose
    private Action distanceBodyKicks;

    @SerializedName("Distance Leg Kicks")
    @Expose
    private Action distanceLegKicks;

    @SerializedName("Distance Head Punches")
    @Expose
    private Action distanceHeadPunches;

    @SerializedName("Distance Body Punches")
    @Expose
    private Action distanceBodyPunches;

    @SerializedName("Clinch Significant Kicks")
    @Expose
    private Action clinchSignificantKicks;

    @SerializedName("Clinch Significant Punches")
    @Expose
    private Action clinchSignificantPunches;

    @SerializedName("Ground Significant Kicks")
    @Expose
    private Action groundSignificantKicks;

    @SerializedName("Ground Significant Punches")
    @Expose
    private Action groundSignificantPunches;

    public Action getKnockDown() {
        return knockDown;
    }

    public void setKnockDown(Action knockDown) {
        this.knockDown = knockDown;
    }

    public Action getSignificantStrikes() {
        return significantStrikes;
    }

    public void setSignificantStrikes(Action significantStrikes) {
        this.significantStrikes = significantStrikes;
    }

    public Action getTotalStrikes() {
        return totalStrikes;
    }

    public void setTotalStrikes(Action totalStrikes) {
        this.totalStrikes = totalStrikes;
    }

    public Action getPunches() {
        return punches;
    }

    public void setPunches(Action punches) {
        this.punches = punches;
    }

    public Action getKicks() {
        return kicks;
    }

    public void setKicks(Action kicks) {
        this.kicks = kicks;
    }

    public Action getDistanceStrikes() {
        return distanceStrikes;
    }

    public void setDistanceStrikes(Action distanceStrikes) {
        this.distanceStrikes = distanceStrikes;
    }

    public Action getClinchSignificantStrikes() {
        return clinchSignificantStrikes;
    }

    public void setClinchSignificantStrikes(Action clinchSignificantStrikes) {
        this.clinchSignificantStrikes = clinchSignificantStrikes;
    }

    public Action getGroundSignificantStrikes() {
        return groundSignificantStrikes;
    }

    public void setGroundSignificantStrikes(Action groundSignificantStrikes) {
        this.groundSignificantStrikes = groundSignificantStrikes;
    }

    public Action getClinchTotalStrikes() {
        return clinchTotalStrikes;
    }

    public void setClinchTotalStrikes(Action clinchTotalStrikes) {
        this.clinchTotalStrikes = clinchTotalStrikes;
    }

    public Action getGroundTotalStrikes() {
        return groundTotalStrikes;
    }

    public void setGroundTotalStrikes(Action groundTotalStrikes) {
        this.groundTotalStrikes = groundTotalStrikes;
    }

    public Action getHeadSignificantStrikes() {
        return headSignificantStrikes;
    }

    public void setHeadSignificantStrikes(Action headSignificantStrikes) {
        this.headSignificantStrikes = headSignificantStrikes;
    }

    public Action getBodySignificantStrikes() {
        return bodySignificantStrikes;
    }

    public void setBodySignificantStrikes(Action bodySignificantStrikes) {
        this.bodySignificantStrikes = bodySignificantStrikes;
    }

    public Action getLegsSignificantStrikes() {
        return legsSignificantStrikes;
    }

    public void setLegsSignificantStrikes(Action legsSignificantStrikes) {
        this.legsSignificantStrikes = legsSignificantStrikes;
    }

    public Action getHeadTotalStrikes() {
        return headTotalStrikes;
    }

    public void setHeadTotalStrikes(Action headTotalStrikes) {
        this.headTotalStrikes = headTotalStrikes;
    }

    public Action getBodyTotalStrikes() {
        return bodyTotalStrikes;
    }

    public void setBodyTotalStrikes(Action bodyTotalStrikes) {
        this.bodyTotalStrikes = bodyTotalStrikes;
    }

    public Action getLegsTotalStrikes() {
        return legsTotalStrikes;
    }

    public void setLegsTotalStrikes(Action legsTotalStrikes) {
        this.legsTotalStrikes = legsTotalStrikes;
    }

    public Action getDistanceHeadStrikes() {
        return distanceHeadStrikes;
    }

    public void setDistanceHeadStrikes(Action distanceHeadStrikes) {
        this.distanceHeadStrikes = distanceHeadStrikes;
    }

    public Action getDistanceBodyStrikes() {
        return distanceBodyStrikes;
    }

    public void setDistanceBodyStrikes(Action distanceBodyStrikes) {
        this.distanceBodyStrikes = distanceBodyStrikes;
    }

    public Action getDistanceLegStrikes() {
        return distanceLegStrikes;
    }

    public void setDistanceLegStrikes(Action distanceLegStrikes) {
        this.distanceLegStrikes = distanceLegStrikes;
    }

    public Action getClinchHeadStrikes() {
        return clinchHeadStrikes;
    }

    public void setClinchHeadStrikes(Action clinchHeadStrikes) {
        this.clinchHeadStrikes = clinchHeadStrikes;
    }

    public Action getClinchBodyStrikes() {
        return clinchBodyStrikes;
    }

    public void setClinchBodyStrikes(Action clinchBodyStrikes) {
        this.clinchBodyStrikes = clinchBodyStrikes;
    }

    public Action getClinchLegStrikes() {
        return clinchLegStrikes;
    }

    public void setClinchLegStrikes(Action clinchLegStrikes) {
        this.clinchLegStrikes = clinchLegStrikes;
    }

    public Action getGroundHeadStrikes() {
        return groundHeadStrikes;
    }

    public void setGroundHeadStrikes(Action groundHeadStrikes) {
        this.groundHeadStrikes = groundHeadStrikes;
    }

    public Action getGroundBodyStrikes() {
        return groundBodyStrikes;
    }

    public void setGroundBodyStrikes(Action groundBodyStrikes) {
        this.groundBodyStrikes = groundBodyStrikes;
    }

    public Action getGroundLegStrikes() {
        return groundLegStrikes;
    }

    public void setGroundLegStrikes(Action groundLegStrikes) {
        this.groundLegStrikes = groundLegStrikes;
    }

    public Action getDistanceHeadKicks() {
        return distanceHeadKicks;
    }

    public void setDistanceHeadKicks(Action distanceHeadKicks) {
        this.distanceHeadKicks = distanceHeadKicks;
    }

    public Action getDistanceBodyKicks() {
        return distanceBodyKicks;
    }

    public void setDistanceBodyKicks(Action distanceBodyKicks) {
        this.distanceBodyKicks = distanceBodyKicks;
    }

    public Action getDistanceLegKicks() {
        return distanceLegKicks;
    }

    public void setDistanceLegKicks(Action distanceLegKicks) {
        this.distanceLegKicks = distanceLegKicks;
    }

    public Action getDistanceHeadPunches() {
        return distanceHeadPunches;
    }

    public void setDistanceHeadPunches(Action distanceHeadPunches) {
        this.distanceHeadPunches = distanceHeadPunches;
    }

    public Action getDistanceBodyPunches() {
        return distanceBodyPunches;
    }

    public void setDistanceBodyPunches(Action distanceBodyPunches) {
        this.distanceBodyPunches = distanceBodyPunches;
    }

    public Action getClinchSignificantKicks() {
        return clinchSignificantKicks;
    }

    public void setClinchSignificantKicks(Action clinchSignificantKicks) {
        this.clinchSignificantKicks = clinchSignificantKicks;
    }

    public Action getClinchSignificantPunches() {
        return clinchSignificantPunches;
    }

    public void setClinchSignificantPunches(Action clinchSignificantPunches) {
        this.clinchSignificantPunches = clinchSignificantPunches;
    }

    public Action getGroundSignificantKicks() {
        return groundSignificantKicks;
    }

    public void setGroundSignificantKicks(Action groundSignificantKicks) {
        this.groundSignificantKicks = groundSignificantKicks;
    }

    public Action getGroundSignificantPunches() {
        return groundSignificantPunches;
    }

    public void setGroundSignificantPunches(Action groundSignificantPunches) {
        this.groundSignificantPunches = groundSignificantPunches;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Strikes{");
        sb.append("knockDown=").append(knockDown);
        sb.append(", significantStrikes=").append(significantStrikes);
        sb.append(", totalStrikes=").append(totalStrikes);
        sb.append(", punches=").append(punches);
        sb.append(", kicks=").append(kicks);
        sb.append(", distanceStrikes=").append(distanceStrikes);
        sb.append(", clinchSignificantStrikes=").append(clinchSignificantStrikes);
        sb.append(", groundSignificantStrikes=").append(groundSignificantStrikes);
        sb.append(", clinchTotalStrikes=").append(clinchTotalStrikes);
        sb.append(", groundTotalStrikes=").append(groundTotalStrikes);
        sb.append(", headSignificantStrikes=").append(headSignificantStrikes);
        sb.append(", bodySignificantStrikes=").append(bodySignificantStrikes);
        sb.append(", legsSignificantStrikes=").append(legsSignificantStrikes);
        sb.append(", headTotalStrikes=").append(headTotalStrikes);
        sb.append(", bodyTotalStrikes=").append(bodyTotalStrikes);
        sb.append(", legsTotalStrikes=").append(legsTotalStrikes);
        sb.append(", distanceHeadStrikes=").append(distanceHeadStrikes);
        sb.append(", distanceBodyStrikes=").append(distanceBodyStrikes);
        sb.append(", distanceLegStrikes=").append(distanceLegStrikes);
        sb.append(", clinchHeadStrikes=").append(clinchHeadStrikes);
        sb.append(", clinchBodyStrikes=").append(clinchBodyStrikes);
        sb.append(", clinchLegStrikes=").append(clinchLegStrikes);
        sb.append(", groundHeadStrikes=").append(groundHeadStrikes);
        sb.append(", groundBodyStrikes=").append(groundBodyStrikes);
        sb.append(", groundLegStrikes=").append(groundLegStrikes);
        sb.append(", distanceHeadKicks=").append(distanceHeadKicks);
        sb.append(", distanceBodyKicks=").append(distanceBodyKicks);
        sb.append(", distanceLegKicks=").append(distanceLegKicks);
        sb.append(", distanceHeadPunches=").append(distanceHeadPunches);
        sb.append(", distanceBodyPunches=").append(distanceBodyPunches);
        sb.append(", clinchSignificantKicks=").append(clinchSignificantKicks);
        sb.append(", clinchSignificantPunches=").append(clinchSignificantPunches);
        sb.append(", groundSignificantKicks=").append(groundSignificantKicks);
        sb.append(", groundSignificantPunches=").append(groundSignificantPunches);
        sb.append('}');
        return sb.toString();
    }
}
