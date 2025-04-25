/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Administrator
 */
public class HireRequest {
    private int id;
    private int menteeid;
    private int mentorid;
    private String title;
    private String content;
    private int statusid;

    public HireRequest() {
    }

    public HireRequest(int id, int menteeid, int mentorid, String title, String content, int statusid) {
        this.id = id;
        this.menteeid = menteeid;
        this.mentorid = mentorid;
        this.title = title;
        this.content = content;
        this.statusid = statusid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenteeid() {
        return menteeid;
    }

    public void setMenteeid(int menteeid) {
        this.menteeid = menteeid;
    }

    public int getMentorid() {
        return mentorid;
    }

    public void setMentorid(int mentorid) {
        this.mentorid = mentorid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }
}
