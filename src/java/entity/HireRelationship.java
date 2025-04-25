/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Administrator
 */
public class HireRelationship {
    private int id;
    private int menteeid;
    private int mentorid;

    public HireRelationship() {
    }

    public HireRelationship(int id, int menteeid, int mentorid) {
        this.id = id;
        this.menteeid = menteeid;
        this.mentorid = mentorid;
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

}
