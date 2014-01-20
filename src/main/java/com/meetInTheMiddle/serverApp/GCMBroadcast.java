package com.meetInTheMiddle.serverApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;
 
/**
 * Servlet implementation class GCMBroadcast
 */
@WebServlet("/GCMBroadcast")
public class GCMBroadcast extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    // The SENDER_ID here is the "Browser Key" that was generated when I
    // created the API keys for my Google APIs project.
    private static final String SENDER_ID = "AIzaSyB54ZWdcIx-Z3IpvSNhe3IJzTNVj5mXKdA";
     
    // This is a *cheat*  It is a hard-coded registration ID from an Android device
    // that registered itself with GCM using the same project id shown above.
    private static final String DROID_BIONIC = "APA91bHtZ0V7AB9wCOqjfSes6j1843pBM8CaNZPiDX8V8JSpDoJ5lvYPoVSoPPzrDMZn5WuAkT9QY_U0ezegeUQ6PnWFZTwbAAc2mxbM_rAWJ5rm5dkP_lQOIWYVkJAuCMLv7wAtphZ368_z5wu3ctqf4EyUcDZrGzijiVnJmHV9i2pT4kMKia4";
         
    // This array will hold all the registration ids used to broadcast a message.
    // for this demo, it will only have the DROID_BIONIC id that was captured
    // when we ran the Android client app through Eclipse.
    List<String> androidTargets = new ArrayList<String>();
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GCMBroadcast(String DROID_BIONIC) {
         
        super();
 
        // we'll only add the hard-coded *cheat* target device registration id
        // for this demo.
        androidTargets.add(DROID_BIONIC);
         
    }
     
    // This doPost() method is called from the form in our index.jsp file.
    // It will broadcast the passed "Message" value.
    public void doPost(String usermessage) throws ServletException, IOException {
//    		HttpServletRequest request, HttpServletResponse response
         
        // We'll collect the "CollapseKey" and "Message" values from our JSP page
        String collapseKey = "";
        String userMessage = usermessage;
         
        try {
            userMessage = usermessage;
//            		request.getParameter("Message");
            collapseKey = "trololol";
//            request.getParameter("CollapseKey");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
 
        // Instance of com.android.gcm.server.Sender, that does the
        // transmission of a Message to the Google Cloud Messaging service.
        Sender sender = new Sender(SENDER_ID);
         
        // This Message object will hold the data that is being transmitted
        // to the Android client devices.  For this demo, it is a simple text
        // string, but could certainly be a JSON object.
        Message message = new Message.Builder()
         
        // If multiple messages are sent using the same .collapseKey()
        // the android target device, if it was offline during earlier message
        // transmissions, will only receive the latest message for that key when
        // it goes back on-line.
        .collapseKey(collapseKey)
        .timeToLive(30)
        .delayWhileIdle(true)
        .addData("message", userMessage)
        .build();
         
        try {
            // use this for multicast messages.  The second parameter
            // of sender.send() will need to be an array of register ids.
            MulticastResult result = sender.send(message, androidTargets, 1);
             
            if (result.getResults() != null) {
                int canonicalRegId = result.getCanonicalIds();
                if (canonicalRegId != 0) {
                     
                }
            } else {
                int error = result.getFailure();
                System.out.println("Broadcast failure: " + error);
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        // We'll pass the CollapseKey and Message values back to index.jsp, only so
        // we can display it in our form again.
//        request.setAttribute("CollapseKey", collapseKey);
//        request.setAttribute("Message", userMessage);
         
//        request.getRequestDispatcher("index.jsp").forward(request, response);
                 
    }
 
}