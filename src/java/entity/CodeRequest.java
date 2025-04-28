/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author legen
 */
public class CodeRequest {
    private int id;
    private String title;
    private String content;
    private Date deadline;
    private int menteeid;
    private Mentee mentee;

    public CodeRequest() {
    }

    public CodeRequest(int id, String title, String content, Date deadline, int menteeid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.deadline = deadline;
        this.menteeid = menteeid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getMenteeid() {
        return menteeid;
    }

    public void setMenteeid(int menteeid) {
        this.menteeid = menteeid;
    }

    public Mentee getMentee() {
        return mentee;
    }

    public void setMentee(Mentee mentee) {
        this.mentee = mentee;
    }
    
}
