/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.*;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Administrator
 */
public class MentorDAO extends DBContext {
    public Mentor getMentorDetail(int mentorid) {
        Mentor mentor = new Mentor();
        query = "SELECT * FROM Mentor WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String achievement = rs.getString("achievement");
                String avatar = rs.getString("avatar");
                float costHire = rs.getFloat("costHire");
                mentor = new Mentor(id, accountid, name, address, phone, birthday, sex, introduce, achievement, avatar, costHire);
            }
        } catch (Exception e) {
        }
        return mentor;
    }
    public Mentor getMentorbyAccID(int accid) {
        query = "SELECT * FROM Mentor WHERE accountid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int accountid = rs.getInt("accountid");
                String mentorname = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String achievement = rs.getString("achievement");
                String avatar = rs.getString("avatar");
                float costHire = rs.getFloat("costHire");
                return new Mentor(id, accountid, mentorname, address, phone, birthday, sex, introduce, achievement, avatar, costHire);
            }
        } catch (Exception e) {
        }
        return null;
    }
    public List<Skill> getSkillAMentor(int mentorid) {
        List<Skill> skill = new ArrayList<>();
        query = "SELECT s.id,s.name FROM mentor m, mentorskill ms, skill s\n"
                + "WHERE m.id=ms.mentorid AND s.id=ms.skillid AND m.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                skill.add(new Skill(id, name));
            }
        } catch (Exception e) {
        }
        return skill;
    }

    public List<Job> getJobAMentor(int mentorid) {
        List<Job> job = new ArrayList<>();
        query = "SELECT j.id,j.jobname FROM mentor m, mentorjob mj,job j\n"
                + "WHERE m.id=mj.mentorid AND j.id=mj.jobid AND m.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String jobname = rs.getString("jobname");
                job.add(new Job(id, jobname));
            }
        } catch (Exception e) {
        }
        return job;
    }
    public float getRateAMentor(int mentorid) {
        query = "WITH t AS(SELECT mc.mentorid id,AVG(CAST (f.star AS FLOAT(2))) averageStar FROM Feedback f, feedbackanswer fa, answer a, mentorcoderequest mc\n"
                + "WHERE f.id=fa.feedbackid and fa.answerid=a.id and a.mentorcoderequestid=mc.id\n"
                + "GROUP BY mc.mentorid)\n"
                + "SELECT t.averageStar\n"
                + "FROM mentor m,t WHERE m.id=t.id AND m.id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getFloat("averageStar");
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<MenteeFeedback> getFeedbackAMentor(int mentorid) {
        List<MenteeFeedback> list = new ArrayList<>();
        query = "SELECT TOP 3 f.id,f.comment,me.avatar,me.name FROM Feedback f, feedbackanswer fa"
                + ", answer a, mentorcoderequest mc,mentee me\n"
                + "WHERE f.id=fa.feedbackid AND fa.answerid=a.id AND a.mentorcoderequestid=mc.id "
                + "AND f.menteeid=me.id AND mc.mentorid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mentorid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String comment = rs.getString("comment");
                String avatar = rs.getString("avatar");
                String name = rs.getString("name");
                list.add(new MenteeFeedback(id, comment, avatar, name));
            }
        } catch (Exception e) {
        }
        return list;
    }    
}
