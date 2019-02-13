package by.parakhonka.reverse.entity;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {
    public History() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String json;
    private String xml;
    private Long date;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String pName) {
        name = pName;
    }

    public int getId() {
        return id;
    }

    public void setId(int pId) {
        id = pId;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String pJson) {
        json = pJson;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String pXml) {
        xml = pXml;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long pDate) {
        date = pDate;
    }
}
