package fr.unice.polytech.directace.acessor;
import com.oreilly.servlet.MultipartResponse;
import com.oreilly.servlet.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by clement0210 on 15-02-06.
 */
public class PushServlet extends HttpServlet {

    //static final String LAUNCH = "/images/launch.gif";
    private MatchLog currentScoreLog;
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ServletOutputStream out = res.getOutputStream();  // some binary output
        // Prepare a multipart response
        MultipartResponse multi = new MultipartResponse(res);


       while (true) {

            try {
                InputDataAccess inputDataAccess=new InputDataAccess();
                MatchLog scoreLog=inputDataAccess.getLastMatchLog("score");
                if(!scoreLog.equals(currentScoreLog)){
                    multi.startResponse("text/plain");
                    out.println(scoreLog.toString());
                    multi.endResponse();
                    currentScoreLog=scoreLog;
                }

            } catch (Exception e) {
                e.printStackTrace();
                break;
            }


            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); break;}
        }



        // Don't forget to end the multipart response
        multi.finish();
    }
}