package controller;

import dao.MenteeDAO;
import entity.HireRequestlist;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewMentorHireRequest extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // Check if user is logged in and is a mentor
            if (request.getSession().getAttribute("account") == null || 
                request.getSession().getAttribute("getmentor") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String index = request.getParameter("index");
            String mentorid = request.getParameter("mentorid");
            
            if (mentorid == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            
            int id = Integer.parseInt(mentorid);
            if (index == null) {
                index = "1";
            }
            
            int indexp;
            try {
                indexp = Integer.parseInt(index);
                if (indexp < 1) indexp = 1;
            } catch (NumberFormatException e) {
                indexp = 1;
            }
            
            MenteeDAO dao = new MenteeDAO();
            List<HireRequestlist> list = dao.pagingMentorHireRequest(id, indexp);

            int total = dao.getTotalMentorHireRequest(id);
            int end = total / 4;
            if (total % 4 != 0) {
                end++;
            }

            request.setAttribute("endpage", end);
            request.setAttribute("tag", indexp);
            request.setAttribute("hirerequests", list);
            request.getRequestDispatcher("MentorHireRequest.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid mentor ID format");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
