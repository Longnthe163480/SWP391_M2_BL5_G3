package controller;

import dao.AccountDAO;
import entity.Skill;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminSkillManager", urlPatterns = {"/AdminSkillManager"})
public class AdminSkillManager extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        String search = request.getParameter("search");
        int page = 1;
        int pageSize = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        List<Skill> skillList = dao.pagingSkill(search, page, pageSize);
        int totalSkill = dao.countSkill(search);
        int totalPage = (int) Math.ceil((double) totalSkill / pageSize);

        request.setAttribute("skillList", skillList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("search", search == null ? "" : search);
        request.getRequestDispatcher("AdminSkillManager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        AccountDAO dao = new AccountDAO();
        if ("add".equals(action)) {
            String skillName = request.getParameter("skillName");
            dao.addSkillAdmin(skillName);
        } else if ("update".equals(action)) {
            int skillId = Integer.parseInt(request.getParameter("skillId"));
            String skillName = request.getParameter("skillName");
            dao.updateSkillAdmin(skillId, skillName);
        } else if ("delete".equals(action)) {
            int skillId = Integer.parseInt(request.getParameter("skillId"));
            dao.deleteSkillAdmin(skillId);
        }
        response.sendRedirect("AdminSkillManager");
    }
} 