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
    private MatchLog currentScoreLog, currentAceLog, currentBackhandsLog, currentForehandsLog;
    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        ServletOutputStream out = res.getOutputStream();  // some binary output
        // Prepare a multipart response
        MultipartResponse multi = new MultipartResponse(res);
        try {
            InputDataAccess inputDataAccess=new InputDataAccess();
            while (true) {
                    System.out.println("Checking");
                    MatchLog scoreLog=inputDataAccess.getLastMatchLog("score");
                    if(!scoreLog.equals(currentScoreLog) && scoreLog.getId()!=null){
                        multi.startResponse("application/json");
                        out.println(scoreLog.toString());
                        multi.endResponse();
                        currentScoreLog=scoreLog;
                    }
                    MatchLog aceLog=inputDataAccess.getLastMatchLog("aces");
                    if(!aceLog.equals(currentAceLog) && aceLog.getId()!=null){
                        multi.startResponse("application/json");
                        out.println(aceLog.toString());
                        multi.endResponse();
                        currentAceLog=aceLog;
                    }
                    MatchLog backhandsLog=inputDataAccess.getLastMatchLog("backhands");
                    if(!backhandsLog.equals(currentBackhandsLog) && backhandsLog.getId()!=null){
                        multi.startResponse("application/json");
                        out.println(backhandsLog.toString());
                        multi.endResponse();
                        currentBackhandsLog=backhandsLog;
                    }
                    MatchLog forehandsLog=inputDataAccess.getLastMatchLog("forehands");
                    if (!forehandsLog.equals(currentForehandsLog) && forehandsLog.getId()!=null){
                        multi.startResponse("application/json");
                        out.println(forehandsLog.toString());
                        multi.endResponse();
                        currentForehandsLog=forehandsLog;
                    }

                try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); break;}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        // Don't forget to end the multipart response
        multi.finish();
    }
}