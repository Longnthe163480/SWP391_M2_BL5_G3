
package controller;
import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import util.EmailUtil;
import util.PasswordUtil;

public class ResetPassword extends HttpServlet {
   private final ExecutorService executorService = Executors.newFixedThreadPool(5);
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForgotPassController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgotPassController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.setAttribute("emailExisted", false);
        request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
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
        AccountDAO userDAO = new AccountDAO();
        EmailUtil emailUtil = new EmailUtil();
        PasswordUtil passUtil = new PasswordUtil();

        // Get session
        HttpSession session = request.getSession();

        // Get parameter
        String email = request.getParameter("email");

        // Check if email existed
        boolean emailExists = userDAO.checkExistedEmail(email);

        if (emailExists) {
            String emailType = "forgotpass";
            // Generate temporary password
            String generatedPass = passUtil.generatePassword();
            System.out.println("Mat khau tam thoi duoc khoi tao: "+ generatedPass);
            

            // Update password and forgoted in database
            boolean updated = userDAO.updateTempPassword(email, generatedPass);
            System.out.println("Cap nhat mat khau: "+ updated +", Mat khau: "+ generatedPass);

            if (updated) {
                // Store password and timestamp in session
                session.setAttribute("generatedPass", generatedPass); // Lưu plain text
                session.setAttribute("generatedPassTimestamp", System.currentTimeMillis());
                System.out.println("Mat khau trong session: " + generatedPass);

                // Send email asynchronously
                System.out.println("Mat khau duoc gui qua email: " + generatedPass);
                emailUtil.sendEmail(email, emailType, generatedPass);

                // Redirect to OTP verification
                request.setAttribute("emailExisted", true);
                request.getRequestDispatcher("ResetPasswordOTP.jsp").forward(request, response);
            } else {
                // Handle database error or forgoted not 0
                request.setAttribute("err", "Không thể đặt lại mật khẩu. Vui lòng thử lại.");
                request.setAttribute("emailExisted", email);
                request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
            }
        } else {
            // Send error message back to forgotPassword.jsp
            request.setAttribute("err", "Email không tồn tại. Vui lòng nhập lại.");
            request.setAttribute("emailExisted", email);
            request.getRequestDispatcher("ResetPassword.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Xử lý quên mật khẩu và đặt lại mật khẩu tạm thời";
    }

    @Override
    public void destroy() {
        super.destroy();
        // Shutdown the thread pool when the servlet is destroyed
        executorService.shutdown();
    }
}