/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.*;
import entity.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

public class EditMentorProfile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("accmentorid");
        if (id == null) {
            response.sendRedirect("ViewTop3Mentor");
            return;
        }

        int mentorid = Integer.parseInt(id);
        MentorDAO dao = new MentorDAO();
        
        // Get data from database
        Mentor mentor = dao.getMentorbyAccID(mentorid);
        List<Skill> currentSkills = dao.getSkillMentor(mentorid);
        List<Job> currentJobs = dao.getJobMentor(mentorid);
        AccountDAO daoa = new AccountDAO();
        Account acc = daoa.getAccountByid(mentorid);

        // Get available skills and jobs
        List<Skill> availableSkills = dao.getAvailableSkills(mentorid);
        List<Job> availableJobs = dao.getAvailableJobs(mentorid);

        // Debug logging
        System.out.println("Mentor ID: " + mentorid);
        System.out.println("Current Skills count: " + (currentSkills != null ? currentSkills.size() : 0));
        System.out.println("Available Skills count: " + (availableSkills != null ? availableSkills.size() : 0));
        System.out.println("Current Jobs count: " + (currentJobs != null ? currentJobs.size() : 0));
        System.out.println("Available Jobs count: " + (availableJobs != null ? availableJobs.size() : 0));

        // Set data to request scope
        request.setAttribute("getmentor", mentor);
        request.setAttribute("account", acc);
        request.setAttribute("currentSkills", currentSkills);
        request.setAttribute("currentJobs", currentJobs);
        request.setAttribute("availableSkills", availableSkills);
        request.setAttribute("availableJobs", availableJobs);

        // Forward to JSP
        request.getRequestDispatcher("EditMentorProfile.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String accountid = request.getParameter("accountid");
        String mentorid = request.getParameter("mentorid");
        String achievment = request.getParameter("achievment");
        String introduce = request.getParameter("introduce");
        String cost = request.getParameter("cost");

        // Get skills and jobs from form
        String[] skills = request.getParameterValues("skills[]");
        String[] jobs = request.getParameterValues("jobs[]");

        // Convert data types
        int accid = Integer.parseInt(accountid);
        int mid = Integer.parseInt(mentorid);
        float costi = Float.parseFloat(cost);
        Date birth = Date.valueOf(birthday);

        // Update data
        MentorDAO dao = new MentorDAO();
        String mess = null;
        Account a = dao.checkEmail(email);

        if (a != null && a.getId() != accid) {
            mess = "Email is exist";
            response.sendRedirect("ViewMentorProfile?accmentorid=" + accid + "&mess=" + mess);
        } else {
            // Update mentor profile
            dao.updateEmailMentorProfile(accid, email);
            dao.updateMorMentorProfile(mid, name, sex, address, phone, birth, introduce, achievment, costi);

            // Update skills
            if (skills != null) {
                // First remove all existing skills
                dao.removeAllSkills(mid);
                // Then add new skills
                for (String skillId : skills) {
                    int skillIdInt = Integer.parseInt(skillId);
                    dao.addSkill(mid, skillIdInt);
                }
            }

            // Update jobs
            if (jobs != null) {
                // First remove all existing jobs
                dao.removeAllJobs(mid);
                // Then add new jobs
                for (String jobId : jobs) {
                    int jobIdInt = Integer.parseInt(jobId);
                    dao.addJob(mid, jobIdInt);
                }
            }

            mess = "Edit is successful";
            response.sendRedirect("ViewMentorProfile?accmentorid=" + accid + "&mess=" + mess);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
