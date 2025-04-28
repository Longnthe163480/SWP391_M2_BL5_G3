package controller;

import dao.MenteeDAO;
import entity.Mentee;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MenteeDetailController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Get mentee ID from request parameter
            String id = request.getParameter("id");
            System.out.println("Mentee ID: " + id);
            
            // Get mentee details from DAO
            MenteeDAO menteeDAO = new MenteeDAO();
            Mentee mentee = menteeDAO.getMenteeById(Integer.parseInt(id));
            
            if (mentee != null) {
                System.out.println("Mentee found: " + mentee.getName());
                System.out.println("Avatar path: " + mentee.getAvatar());
            } else {
                System.out.println("Mentee not found");
            }
            
            // Set mentee object as request attribute
            request.setAttribute("mentee", mentee);
            
            // Forward to mentee detail page
            request.getRequestDispatcher("MenteeDetail.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("ViewAllMentee");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
} 