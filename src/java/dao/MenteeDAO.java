/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.Feedback;
import entity.Mentee;

/**
 *
 * @author longc
 */
public class MenteeDAO extends DBContext {

    public Mentee getMenteebyAccID(int accid) {
        query = "SELECT * FROM Mentee WHERE accountid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String menteename = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                java.sql.Date birthday = rs.getDate("birthday");
                String avatar = rs.getString("avatar");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                return new Mentee(id, accountid, menteename, address, phone, birthday, sex, introduce, avatar);
            }
        } catch (Exception e) {
        }
        return null;
    }
    public Feedback getfeedback(int mentorid,int requestid){
        query = "SELECT f.id,f.menteeid,f.star,f.comment from feedback f, feedbackanswer fa,answer a, mentorcoderequest mcq \n"
                + "where f.id=fa.feedbackid  and fa.answerid=a.id and a.mentorcoderequestid=mcq.id\n"
                + "AND mcq.coderequestid=? AND mcq.mentorid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,requestid);
            ps.setInt(2,mentorid);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int menteeid=rs.getInt("menteeid");
                int star=rs.getInt("star");
                String comment=rs.getString("comment");
                return new Feedback(id, menteeid, star, comment);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Feedback getfeedbackbyid(int fid){
        query = "SELECT f.id,f.menteeid,f.star,f.comment from feedback f WHERE f.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,fid);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int menteeid=rs.getInt("menteeid");
                int star=rs.getInt("star");
                String comment=rs.getString("comment");
                return new Feedback(id, menteeid, star, comment);
            }
        } catch (Exception e) {
        }
        return null;
    } 
}
