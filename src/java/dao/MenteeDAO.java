/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.Answer;
import entity.CodeRequest;
import entity.Feedback;
import entity.HireRelationship;
import entity.HireRequest;
import entity.HireRequestlist;
import entity.Mentee;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
    
    public void inserHireRequest(int menteeid, int mentorid, String title, String content) {
        query = "INSERT INTO hirerequest VALUES(?,?,?,?,3);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            ps.setInt(2, mentorid);
            ps.setString(3, title);
            ps.setString(4, content);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
     
    public List<HireRelationship> getHireRelationship(){
        List<HireRelationship> list=new ArrayList<>();
        query = "SELECT * FROM HireRelatitonship";
        try {
            ps = connection.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int menteeid=rs.getInt("menteeid");
                int mentorid=rs.getInt("mentorid");
                list.add(new HireRelationship(id, menteeid, mentorid));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void inserCodeRequest(int mid, String title, String content, java.sql.Date deadline) {
        query = "INSERT INTO coderequest VALUES(?,?,?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setDate(3, deadline);
            ps.setInt(4, mid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public CodeRequest getNewInsertReqeust() {
        query = "SELECT TOP 1 * FROM coderequest ORDER BY id DESC";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeid");
                return new CodeRequest(id, title, content, deadline, menteeid);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void inserMentorCodeRequest(int requestid, int mentorid) {
        query = "INSERT INTO mentorcoderequest VALUES(?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, mentorid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void inserCodeRequestSkill(int requestid, int skillid) {
        query = "INSERT INTO coderequestskill VALUES(?,?);";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            ps.setInt(2, skillid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<HireRequestlist> pagingMenteeHireRequest(int mid, int index) {
         List<HireRequestlist> list = new ArrayList<>();
         query = "SELECT h.id,m.[name],h.title,h.content,m.costHire,s.[Status] FROM hirerequest h, [status] s,mentor m \n"
                 + "WHERE h.mentorid=m.id AND h.statusid=s.id AND menteeid=?\n"
                 + "ORDER BY id\n"
                 + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
         try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setInt(2, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String mentorname = rs.getString("name");
                String title = rs.getString("title");
                String content = rs.getString("content");
                float cost=rs.getFloat("costhire");
                String status = rs.getString("status");
                list.add(new HireRequestlist(id, mentorname, title, content, cost, status));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalMenteeHireRequest(int menteeid) {
        query = "SELECT COUNT(*) count FROM hirerequest WHERE menteeid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<HireRequestlist> searchHireRequest(String name, int index, int mid) {
        List<HireRequestlist> list = new ArrayList<>();
        query = "SELECT h.id,m.[name],h.title,h.content,m.costhire,s.[Status] FROM hirerequest h, [status] s,mentor m \n"
                 + "WHERE h.mentorid=m.id AND h.statusid=s.id AND menteeid=?\n"
                + "AND (h.title LIKE ? OR h.content LIKE ? OR m.name LIKE ?)"
                 + "ORDER BY id\n"
                 + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setString(2, "%" + name + "%");
            ps.setString(3, "%" + name + "%");
            ps.setString(4, "%" + name + "%");
            ps.setInt(5, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String mentorname = rs.getString("name");
                String title = rs.getString("title");
                String content = rs.getString("content");
                float cost=rs.getFloat("costhire");
                String status = rs.getString("status");
                list.add(new HireRequestlist(id, mentorname, title, content, cost, status));
            }
        } catch (Exception e) {
        }
        return list;
    }

   public List<CodeRequest> pagingMenteeRequest(int mid, int index) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT * FROM coderequest WHERE menteeid=? \n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setInt(2, (index - 1) * 4);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeid");
                list.add(new CodeRequest(id, title, content, deadline, menteeid));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalMenteeRequest(int menteeid) {
        query = "SELECT COUNT(*) count FROM coderequest WHERE menteeid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public HireRequest getHireRequestbyid(int requestid){
        query = "SELECT * FROM hirerequest WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,requestid);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int idmentee=rs.getInt("menteeid");
                int mentorid=rs.getInt("mentorid");
                String title=rs.getString("title");
                String content=rs.getString("content");
                int statusid=rs.getInt("statusid");
                return new HireRequest(id, idmentee, mentorid, title, content, statusid);
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void updateHireRequest(int id, int menteeid, int mentorid,String title,String content) {
        query = "UPDATE hirerequest SET menteeid=?, mentorid=?, title=?,content=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, menteeid);
            ps.setInt(2, mentorid);
            ps.setString(3,title);
            ps.setString(4, content);
            ps.setInt(5, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void createFeedback(int id,int star,String comment){
        query = "INSERT INTO feedback VALUES(?,?,?) ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            ps.setInt(2,star);
            ps.setString(3,comment);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public Feedback getfeedbackadd(){
        query ="SELECT TOP 1 * FROM feedback ORDER BY id DESC";
        try {
            ps = connection.prepareStatement(query);
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
    
    public void createFeedbackAnswer(int fid,int aid){
        query = "INSERT INTO feedbackanswer VALUES(?,?) ";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,fid);
            ps.setInt(2,aid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
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
    
    public void updateFeedback(int id,int star,String comment){
        query = "UPDATE feedback SET star=?, comment=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,star);
            ps.setString(2,comment);
            ps.setInt(3,id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    } 

    public CodeRequest getAReqeustByID(int requestid) {
        query = "SELECT * FROM coderequest WHERE id=? \n";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, requestid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                java.sql.Date deadline = rs.getDate("deadline");
                int menteeid = rs.getInt("menteeid");
                return new CodeRequest(id, title, content, deadline, menteeid);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Answer getAnswer(int mentorid,int requestid){
        query = "SELECT a.id,a.mentorcoderequestid,a.content FROM answer a, mentorcoderequest mc \n"
                + "WHERE a.mentorcoderequestid=mc.id AND mc.mentorid=? AND mc.coderequestid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,mentorid);
            ps.setInt(2,requestid);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int mcrid=rs.getInt("mentorcoderequestid");
                String content=rs.getString("content");
                return new Answer(id, mcrid, content);
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
}
