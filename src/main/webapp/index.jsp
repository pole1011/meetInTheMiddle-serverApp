<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    // retrieve our passed CollapseKey and Message parameters, if they exist.
    String collapseKey = "GCM_Message";
    String message  = "Generic Broadcast Message";
 
    Object collapseKeyObj =  request.getAttribute("CollapseKey");
     
    if (collapseKeyObj != null) {
        collapseKey = collapseKeyObj.toString();
    }
     
    Object msgObj =  request.getAttribute("Message");
     
    if (msgObj != null) {
        message = msgObj.toString();
    }
     
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Android GCM Broadcast</title>
 
</head>
<body>
 
    <h2>GCM Broadcast Demo</h2>
     
    <form action="GCMBroadcast" method="post">
        <label>Broadcast Message </label>
        <br /><input type="text" name="CollapseKey" value="<%=collapseKey %>" />
        <br/><textarea name="Message" rows="3" cols="60" ><%=message %> </textarea>
        <br/><input type="submit" name="submit" value="Submit" />
        </form>  
    </body>
</html>