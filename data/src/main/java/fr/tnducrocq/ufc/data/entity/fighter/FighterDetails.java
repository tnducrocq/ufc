package fr.tnducrocq.ufc.data.entity.fighter;

import android.os.Parcel;
import android.os.Parcelable;

import com.fcannizzaro.jsoup.annotations.JsoupProcessor;
import com.fcannizzaro.jsoup.annotations.interfaces.Html;
import com.fcannizzaro.jsoup.annotations.interfaces.Text;

import org.apache.commons.lang3.math.NumberUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.tnducrocq.ufc.data.entity.HasId;

/**
 * Created by tony on 02/10/2017.
 */

public class FighterDetails implements HasId, Parcelable {

    private String id;

    @Text(value = "#fighter-details h1", optional = false)
    private String name;

    @Text(value = "td#fighter-nickname", optional = false)
    private String nickname;

    @Text(value = "td#fighter-from", optional = false)
    private String hometown;

    @Text(value = "td#fighter-lives-in", optional = false)
    private String location;

    @Text(value = "td#fighter-age", optional = false)
    private String age;

    private String height;
    private Integer height_cm;

    @Text(value = "td#fighter-height", optional = true)
    private void height(String text) {
        if (text != null) {
            //5' 9" ( 175 cm )
            Pattern p = Pattern.compile("([0-9]*' [0-9]*\") \\( ([0-9]*) cm \\)");
            Matcher m = p.matcher(text);
            if (m.find()) {
                MatchResult mr = m.toMatchResult();
                height = mr.group(1);//2.8
                height_cm = Integer.parseInt(mr.group(2));
            }
        }
    }

    private Integer weight;
    private Integer weight_kg;

    @Text(value = "td#fighter-weight", optional = true)
    private void weight(String text) {
        if (text != null) {
            //155 livres ( 70 kg )
            Pattern p = Pattern.compile("([0-9]*) livres \\( ([0-9]*) kg \\)");
            Matcher m = p.matcher(text);
            if (m.find()) {
                MatchResult mr = m.toMatchResult();
                weight = Integer.parseInt(mr.group(1));//2.8
                weight_kg = Integer.parseInt(mr.group(2));
            }
        }
    }

    private Integer reach;
    private Integer reach_cm;

    @Text(value = "td#fighter-reach", optional = true)
    private void reach(String text) {
        int value = NumberUtils.toInt(text.trim().replace("\"", ""), -1);
        if (value != -1) {
            reach = value;
            reach_cm = (int) (2.54 * value);
        }
    }

    private Integer legReach;
    private Integer legReach_cm;

    @Text(value = "td#fighter-leg-reach", optional = true)
    private void legReach(String text) {
        int value = NumberUtils.toInt(text.trim().replace("\"", ""), -1);
        if (value != -1) {
            legReach = value;
            legReach_cm = (int) (2.54 * value);
        }
    }

    @Text(value = "td#fighter-skill-record", optional = false)
    private String record;

    @Text(value = "td#fighter-college", optional = true)
    private String college;

    @Text(value = "td#fighter-degree", optional = true)
    private String degree;


    private Integer strikes_attempted;

    @Text(value = "#fight-history > .overall-stats > .graph > .max-number", optional = true)
    private void strikes_attempted(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_attempted = value;
        }
    }

    private Integer strikes_successful;

    @Text(value = "#types-of-successful-strikes-graph-maximum", optional = true)
    private void strikes_successful(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_successful = value;
        }
    }

    private Integer strikes_standing;

    @Text(value = "#types-of-successful-strikes-graph > .red-text-bar > .bar-text", optional = true)
    private void strikes_standing(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_standing = value;
        }
    }

    private Integer strikes_clinch;

    @Text(value = "#types-of-successful-strikes-graph > .dark-red-text-bar > .bar-text", optional = true)
    private void strikes_clinch(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_clinch = value;
        }
    }

    private Integer strikes_ground;

    @Text(value = "#types-of-successful-strikes-graph > .grey-text-bar > .bar-text", optional = true)
    private void strikes_ground(String text) {
        int value = NumberUtils.toInt(text, -1);
        if (value != -1) {
            strikes_ground = value;
        }
    }

    private Integer striking_defense_pecentage;

    @Text(value = "#striking-defense-pecentage", optional = true)
    private void striking_defense_pecentage(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            striking_defense_pecentage = value;
        }
    }

    private Integer takedowns_attempted;
    private Integer takedowns_successful;

    @Html(value = "#fight-history > .overall-stats", index = 1, optional = true)
    private void takedowns(Element elt) {

        Pattern p = Pattern.compile("([0-9]*).*");
        Matcher m = p.matcher(elt.select(".graph > .max-number").text());
        if (m.find()) {
            MatchResult mr = m.toMatchResult();
            this.takedowns_attempted = Integer.parseInt(mr.group(1));//2.8
        }

        String tmp = elt.select("#total-takedowns-number").text().trim();
        int value = NumberUtils.toInt(tmp.trim(), -1);
        if (value != -1) {
            takedowns_successful = value;
        }
    }

    private Integer takedowns_submissions;

    @Text(value = "#successful-submissions", optional = true)
    private void takedowns_submissions(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedowns_submissions = value;
        }
    }

    private Integer takedowns_passes;

    @Text(value = "#successful-passes", optional = true)
    private void takedowns_passes(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedowns_passes = value;
        }
    }

    private Integer takedowns_sweeps;

    @Text(value = "#successful-sweeps", optional = true)
    private void takedowns_sweeps(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedowns_sweeps = value;
        }
    }

    private Integer takedown_defense_pecentage;

    @Text(value = "#takedown-defense-percentage", optional = true)
    private void takedown_defense_pecentage(String text) {
        int value = NumberUtils.toInt(text.replace("%", "").trim(), -1);
        if (value != -1) {
            takedown_defense_pecentage = value;
        }
    }


    public static FighterDetails parse(String html) {
        Element document = Jsoup.parseBodyFragment(html);
        return JsoupProcessor.from(document, FighterDetails.class);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FighterDetails{\n");
        sb.append("name='").append(name).append('\'');
        sb.append(",\n\t nickname='").append(nickname).append('\'');
        sb.append(",\n\t hometown='").append(hometown).append('\'');
        sb.append(",\n\t location='").append(location).append('\'');
        sb.append(",\n\t age='").append(age).append('\'');
        sb.append(",\n\t height='").append(height).append('\'');
        sb.append(",\n\t height_cm=").append(height_cm);
        sb.append(",\n\t weight=").append(weight);
        sb.append(",\n\t weight_kg=").append(weight_kg);
        sb.append(",\n\t reach=").append(reach);
        sb.append(",\n\t reach_cm=").append(reach_cm);
        sb.append(",\n\t legReach=").append(legReach);
        sb.append(",\n\t legReach_cm=").append(legReach_cm);
        sb.append(",\n\t record='").append(record).append('\'');
        sb.append(",\n\t college='").append(college).append('\'');
        sb.append(",\n\t degree='").append(degree).append('\'');
        sb.append(",\n\t strikes_attempted=").append(strikes_attempted);
        sb.append(",\n\t strikes_successful=").append(strikes_successful);
        sb.append(",\n\t strikes_standing=").append(strikes_standing);
        sb.append(",\n\t strikes_clinch=").append(strikes_clinch);
        sb.append(",\n\t strikes_ground=").append(strikes_ground);
        sb.append(",\n\t striking_defense_pecentage=").append(striking_defense_pecentage);
        sb.append(",\n\t takedowns_attempted=").append(takedowns_attempted);
        sb.append(",\n\t takedowns_successful=").append(takedowns_successful);
        sb.append(",\n\t takedowns_submissions=").append(takedowns_submissions);
        sb.append(",\n\t takedowns_passes=").append(takedowns_passes);
        sb.append(",\n\t takedowns_sweeps=").append(takedowns_sweeps);
        sb.append(",\n\t takedown_defense_pecentage=").append(takedown_defense_pecentage);
        sb.append("\n}");
        return sb.toString();
    }

    @Override
    public String id() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getHometown() {
        return hometown;
    }

    public String getLocation() {
        return location;
    }

    public String getAge() {
        return age;
    }

    public String getHeight() {
        return height;
    }

    public Integer getHeight_cm() {
        return height_cm;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getWeight_kg() {
        return weight_kg;
    }

    public Integer getReach() {
        return reach;
    }

    public Integer getReach_cm() {
        return reach_cm;
    }

    public Integer getLegReach() {
        return legReach;
    }

    public Integer getLegReach_cm() {
        return legReach_cm;
    }

    public String getRecord() {
        return record;
    }

    public String getCollege() {
        return college;
    }

    public String getDegree() {
        return degree;
    }

    public Integer getStrikes_attempted() {
        return strikes_attempted;
    }

    public Integer getStrikes_successful() {
        return strikes_successful;
    }

    public Integer getStrikes_standing() {
        return strikes_standing;
    }

    public Integer getStrikes_clinch() {
        return strikes_clinch;
    }

    public Integer getStrikes_ground() {
        return strikes_ground;
    }

    public Integer getStriking_defense_pecentage() {
        return striking_defense_pecentage;
    }

    public Integer getTakedowns_attempted() {
        return takedowns_attempted;
    }

    public Integer getTakedowns_successful() {
        return takedowns_successful;
    }

    public Integer getTakedowns_submissions() {
        return takedowns_submissions;
    }

    public Integer getTakedowns_passes() {
        return takedowns_passes;
    }

    public Integer getTakedowns_sweeps() {
        return takedowns_sweeps;
    }

    public Integer getTakedown_defense_pecentage() {
        return takedown_defense_pecentage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nickname);
        dest.writeString(this.hometown);
        dest.writeString(this.location);
        dest.writeString(this.age);
        dest.writeString(this.height);
        dest.writeValue(this.height_cm);
        dest.writeValue(this.weight);
        dest.writeValue(this.weight_kg);
        dest.writeValue(this.reach);
        dest.writeValue(this.reach_cm);
        dest.writeValue(this.legReach);
        dest.writeValue(this.legReach_cm);
        dest.writeString(this.record);
        dest.writeString(this.college);
        dest.writeString(this.degree);
        dest.writeValue(this.strikes_attempted);
        dest.writeValue(this.strikes_successful);
        dest.writeValue(this.strikes_standing);
        dest.writeValue(this.strikes_clinch);
        dest.writeValue(this.strikes_ground);
        dest.writeValue(this.striking_defense_pecentage);
        dest.writeValue(this.takedowns_attempted);
        dest.writeValue(this.takedowns_successful);
        dest.writeValue(this.takedowns_submissions);
        dest.writeValue(this.takedowns_passes);
        dest.writeValue(this.takedowns_sweeps);
        dest.writeValue(this.takedown_defense_pecentage);
    }

    public FighterDetails() {
    }

    protected FighterDetails(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.nickname = in.readString();
        this.hometown = in.readString();
        this.location = in.readString();
        this.age = in.readString();
        this.height = in.readString();
        this.height_cm = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weight_kg = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.reach_cm = (Integer) in.readValue(Integer.class.getClassLoader());
        this.legReach = (Integer) in.readValue(Integer.class.getClassLoader());
        this.legReach_cm = (Integer) in.readValue(Integer.class.getClassLoader());
        this.record = in.readString();
        this.college = in.readString();
        this.degree = in.readString();
        this.strikes_attempted = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_successful = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_standing = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_clinch = (Integer) in.readValue(Integer.class.getClassLoader());
        this.strikes_ground = (Integer) in.readValue(Integer.class.getClassLoader());
        this.striking_defense_pecentage = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_attempted = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_successful = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_submissions = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_passes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedowns_sweeps = (Integer) in.readValue(Integer.class.getClassLoader());
        this.takedown_defense_pecentage = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<FighterDetails> CREATOR = new Parcelable.Creator<FighterDetails>() {
        @Override
        public FighterDetails createFromParcel(Parcel source) {
            return new FighterDetails(source);
        }

        @Override
        public FighterDetails[] newArray(int size) {
            return new FighterDetails[size];
        }
    };
}
