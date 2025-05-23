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

    public List<Mentor> getTop3Mentor() {
        List<Mentor> list = new ArrayList<>();
        query = "WITH t AS(SELECT mc.mentorid id,AVG(CAST (f.star AS FLOAT(2))) averageStar FROM Feedback f, feedbackanswer fa, answer a, mentorcoderequest mc\n"
                + "WHERE f.id=fa.feedbackid and fa.answerid=a.id and a.mentorcoderequestid=mc.id\n"
                + "GROUP BY mc.mentorid)\n"
                + "SELECT TOP (3) m.id,m.accountid,m.name,m.address,m.phone,m.birthday,m.sex,m.introduce,m.achievement,m.avatar,m.costHire,\n"
                + "COALESCE(t.averageStar, 0) as averageStar\n"
                + "FROM mentor m\n"
                + "LEFT JOIN t ON m.id=t.id\n"
                + "ORDER BY COALESCE(t.averageStar, 0) DESC";
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
                float averageStar = rs.getFloat("averageStar");
                list.add(new Mentor(id, accountid, name, address, phone, birthday, sex, introduce, achievement, avatar, costHire, averageStar));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
    
    public List<Skill> getSkillMentor(int accountId) {
    List<Skill> skill = new ArrayList<>();
    query = "SELECT s.id, s.name " +
            "FROM mentor m " +
            "JOIN mentorskill ms ON m.id = ms.mentorid " +
            "JOIN skill s ON s.id = ms.skillid " +
            "WHERE m.accountid = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, accountId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            skill.add(new Skill(id, name));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return skill;
}

public List<Job> getJobMentor(int accountId) {
    List<Job> job = new ArrayList<>();
    query = "SELECT j.id, j.jobname " +
            "FROM mentor m " +
            "JOIN mentorjob mj ON m.id = mj.mentorid " +
            "JOIN job j ON j.id = mj.jobid " +
            "WHERE m.accountid = ?";
    try {
        ps = connection.prepareStatement(query);
        ps.setInt(1, accountId);
        rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String jobname = rs.getString("jobname");
            job.add(new Job(id, jobname));
        }
    } catch (Exception e) {
        e.printStackTrace();
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

    public List<Mentor> getAllMentor() {
        List<Mentor> list = new ArrayList<>();
        query = "SELECT * FROM Mentor";
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
    
    public MentorRequest getMentorcoderequest(int mid, int rid){
        query = "SELECT * FROM mentorcoderequest WHERE mentorid=? AND coderequestid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setInt(2, rid);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                int coderequestid=rs.getInt("coderequestid");
                int mentorid=rs.getInt("mentorid");
                return new MentorRequest(id, coderequestid, mentorid);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void CreateAnswer(int mrid, String content){
        query = "INSERT INTO answer VALUES (?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mrid);
            ps.setString(2, content);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

     public List<CodeRequest> pagingMentorRequest(int mid, int index) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT c.id,c.title,c.content,c.deadline,c.menteeid "
                 + "FROM coderequest c,mentorcoderequest mc WHERE c.id=mc.coderequestid AND mc.mentorid=?\n"
                 + "ORDER BY c.id\n"
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

    public void updateAnswer(int aid, String content){
        query = "UPDATE answer SET content=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, content);
            ps.setInt(2, aid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
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

        public List<CodeRequest> searchRequest(String name, int index, int mid) {
        List<CodeRequest> list = new ArrayList<>();
        query = "SELECT c.id,c.title,c.content,c.deadline,c.menteeid FROM coderequest c,mentorcoderequest mc "
                + "WHERE c.id=mc.coderequestid AND mc.mentorid=? AND (c.title LIKE ? OR c.content LIKE ?)\n"
                + "ORDER BY id\n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, mid);
            ps.setString(2, "%" + name + "%");
            ps.setString(3, "%" + name + "%");
            ps.setInt(4, (index - 1) * 4);
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

    public Account checkEmail(String email) {
        query = "SELECT * FROM Account WHERE email=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String accname = rs.getString("accountname");
                String pass = rs.getString("password");
                int roleid = rs.getInt("roleid");
                String emails = rs.getString("email");
                return new Account(id, accname, pass, roleid, emails);
            }
        } catch (Exception e) {
        }
        return null;
    }
    public void updateEmailMentorProfile(int accid, String email) {
        query = "UPDATE Account SET email=? WHERE id=?\n";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setInt(2, accid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    public void updateMorMentorProfile(int mentorid, String name, String sex, String address, String phone, java.sql.Date birth,String introduce,String achievement,float cost) {
        query = " UPDATE Mentor SET name=?, sex=?, address=?, phone=?, birthday=?,introduce =?,achievement =?,costHire =?\n"
                + "WHERE id=?;";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, sex);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setDate(5, birth);
            ps.setString(6, introduce);
            ps.setString(7, achievement);
            ps.setFloat(8, cost);
            ps.setInt(9, mentorid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public List<Skill> getAllSkills() {
        List<Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM Skill";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill s = new Skill();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Job> getAllJobs() {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT * FROM Job";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Job j = new Job();
                j.setId(rs.getInt("id"));
                j.setJobname(rs.getString("jobname"));
                list.add(j);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Skill> getAvailableSkills(int mentorid) {
        List<Skill> list = new ArrayList<>();
        String sql = "SELECT s.* FROM Skill s "
                + "WHERE s.id NOT IN ("
                + "    SELECT ms.skillid FROM MentorSkill ms "
                + "    JOIN Mentor m ON ms.mentorid = m.id "
                + "    WHERE m.accountid = ?"
                + ")";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill s = new Skill();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Job> getAvailableJobs(int mentorid) {
        List<Job> list = new ArrayList<>();
        String sql = "SELECT j.* FROM Job j "
                + "WHERE j.id NOT IN ("
                + "    SELECT mj.jobid FROM MentorJob mj "
                + "    JOIN Mentor m ON mj.mentorid = m.id "
                + "    WHERE m.accountid = ?"
                + ")";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Job j = new Job();
                j.setId(rs.getInt("id"));
                j.setJobname(rs.getString("jobname"));
                list.add(j);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void removeAllSkills(int mentorid) {
        String sql = "DELETE FROM MentorSkill WHERE mentorid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addSkill(int mentorid, int skillid) {
        String sql = "INSERT INTO MentorSkill (mentorid, skillid) VALUES (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            st.setInt(2, skillid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeAllJobs(int mentorid) {
        String sql = "DELETE FROM MentorJob WHERE mentorid = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addJob(int mentorid, int jobid) {
        String sql = "INSERT INTO MentorJob (mentorid, jobid) VALUES (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, mentorid);
            st.setInt(2, jobid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addSkillAdmin(String name) {
        String sql = "INSERT INTO Skill(name) VALUES(?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateSkillAdmin(int id, String name) {
        String sql = "UPDATE Skill SET name=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void deleteSkillAdmin(int id) {
        String sql = "DELETE FROM Skill WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateMentorAvatar(int mentorid, String avatar) {
        String sql = "UPDATE Mentor SET avatar=? WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, avatar);
            st.setInt(2, mentorid);
            st.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public void updateHireRequestStatus(int requestId, int statusId) {
        query = "UPDATE hirerequest SET statusid = ? WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, statusId);
            ps.setInt(2, requestId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

}
