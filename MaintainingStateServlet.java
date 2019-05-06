/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maintaining.state;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author air
 */
@WebServlet(name = "MaintainingStateServlet", urlPatterns = {"/MaintainingStateServlet"})
public class MaintainingStateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            out.println("<title>Servlet MaintainingStateServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MaintainingStateServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

         request.setAttribute("requestState", "bob");
         request.getSession().setAttribute("sessionState", "bob");
         this.getServletContext().setAttribute("applicationState", "bob");
         Cookie c = new Cookie("cookiePermanentState", "bob");
         c.setMaxAge(3600);
         response.addCookie(c);

         c = new Cookie("cookieTemporaryState", "bob");
         response.addCookie(c);
         PrintWriter out = response.getWriter();
         out.print("<html><head><title>Test</title></head><body>");
         out.print("<form method='post'>");
         out.print("<p>Please click the button</p>");
         out.print("<input type='hidden' name='hiddenState' value='bob'>");
         out.print("<input type='submit' value='Click me'/>");
         out.print("</form>");
         out.print("</body></html>"); 

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
//        processRequest(request, response);
        PrintWriter out = response.getWriter();
        out.print("<html><head><title>Test</title></head><body><p>See the Commandwindow</p></body></html>");

         System.out.format("requestState: %s\r\n",
        request.getAttribute("requestState"));
         System.out.format("sessionState: %s\r\n",
        request.getSession().getAttribute("sessionState"));
         System.out.format("applicationState: %s\r\n",
        this.getServletContext().getAttribute("applicationState"));

         Cookie[] cookies = request.getCookies();
         int cnt = 0;
         if (cookies != null)
         {
         for (Cookie cookie : cookies)
         {
         if (cookie.getName().equals("cookiePermanentState"))
         System.out.format("cookiePermanentState: %s\r\n",
        cookie.getValue());
         else if (cookie.getName().equals("cookieTemporaryState"))
         System.out.format("cookieTemporaryState: %s\r\n",
        cookie.getValue());
         }
         }
         System.out.format("hiddenState: %s\r\n",
        request.getParameter("hiddenState"));
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
