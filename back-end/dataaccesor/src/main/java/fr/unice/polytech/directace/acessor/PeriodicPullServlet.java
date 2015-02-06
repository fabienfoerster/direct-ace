package fr.unice.polytech.directace.acessor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by clement0210 on 15-02-06.
 */
public class PeriodicPullServlet extends HttpServlet{
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/plain");
        PrintWriter out = res.getWriter();
        res.setHeader("Refresh", "10");
        try {
            InputDataAccess inputDataAccess=new InputDataAccess();
            out.print(inputDataAccess.getLastMatchLog("score"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
