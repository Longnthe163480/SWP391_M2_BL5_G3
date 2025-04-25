/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author legen
 */
public class HireRequestlist {
    private int id;
    private String mentorname;
    private String title;
    private String content;
    private float costhire;
    private String status;

    public HireRequestlist() {
    }

    public HireRequestlist(int id, String mentorname, String title, String content, float costhire, String status) {
        this.id = id;
        this.mentorname = mentorname;
        this.title = title;
        this.content = content;
        this.costhire = costhire;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMentorname() {
        return mentorname;
    }

    public void setMentorname(String mentorname) {
        this.mentorname = mentorname;
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

    public float getCosthire() {
        return costhire;
    }

    public void setCosthire(float costhire) {
        this.costhire = costhire;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
