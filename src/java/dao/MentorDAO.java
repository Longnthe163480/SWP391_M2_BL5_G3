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
 * @author legen
 */
public class MentorDAO extends DBContext {

    public List<Mentor> pagingMentor(int index) {
        List<Mentor> list = new ArrayList<>();
        query = "SELECT * FROM Mentor\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * 3);
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
                list.add(new Mentor(id, accountid, mentorname, address, phone, birthday, sex, introduce, achievement, avatar, costHire));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public int getTotalMentor() {
        query = "SELECT COUNT(*) count FROM Mentor";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int x = rs.getInt("count");
                return x;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public List<Mentor> searchMentor(String name, int index) {
        List<Mentor> list = new ArrayList<>();
        query = "SELECT * FROM Mentor m WHERE m.name LIKE ?\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, "%" + name + "%");
            ps.setInt(2, (index - 1) * 3);
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
                list.add(new Mentor(id, accountid, mentorname, address, phone, birthday, sex, introduce, achievement, avatar, costHire));
            }
        } catch (Exception e) {
        }
        return list;
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
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                Date birthday = rs.getDate("birthday");
                String sex = rs.getString("sex");
                String introduce = rs.getString("introduce");
                String achievement = rs.getString("achievement");
                String avatar = rs.getString("avatar");
                float costHire = rs.getFloat("costHire");
                return new Mentor(id, accountid, name, address, phone, birthday, sex, introduce, achievement, avatar, costHire);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Mentor> getTop3Mentor() {
        List<Mentor> list = new ArrayList<>();
        query = "WITH t AS(SELECT mc.mentorid id,AVG(CAST (f.star AS FLOAT(2))) averageStar FROM Feedback f, feedbackanswer fa, answer a, mentorcoderequest mc\n"
                + "WHERE f.id=fa.feedbackid and fa.answerid=a.id and a.mentorcoderequestid=mc.id\n"
                + "GROUP BY mc.mentorid)\n"
                + "SELECT TOP (2) m.id,m.accountid,m.name,m.address,m.phone,m.birthday,m.sex,m.introduce,m.achievement,m.avatar,m.costHire,t.averageStar\n"
                + "FROM mentor m,t WHERE m.id=t.id\n"
                + "ORDER BY t.averageStar";
        try {
            ps = connection.prepareStatement(query);
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
                list.add(new Mentor(id, accountid, name, address, phone, birthday, sex, introduce, achievement, avatar, costHire));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Skill> getallskill() {
        List<Skill> list = new ArrayList<>();
        query = "SELECT * FROM skill";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Skill(id, name));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}
