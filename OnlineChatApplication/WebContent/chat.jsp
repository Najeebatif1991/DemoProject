<%@ page language="java" import="java.util.*,java.text.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
if(session.getAttribute("login")==null){
	session.setAttribute("login", "no");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LetusChat.com</title>
<style>
div.container {
    width: 100%;
    border: 1px solid gray;
}

header, footer {
    padding: 1em;
    color: white;
    background-color: black;
    clear: left;
    text-align: center;
}

nav {
    float: left;
    max-width: 160px;
    margin: 0;
    padding: 1em;
}

nav ul {
    list-style-type: none;
    padding: 0;
}
   
nav ul a {
    text-decoration: none;
}

article {
    margin-left: 170px;
    border-left: 1px solid gray;
    padding: 1em;
    overflow: hidden;
}
h2{
	text-align: center;
}
</style>
</head>
<body>
<div class="container">

<header>
   <h1>LetUsChat</h1>
</header>
  
<nav>
  <ul>
    <li><a href="#">Home</a></li>
    <li><a href="#">Administrator</a></li>
    <li><a href="#">Join a Chat Room</a></li>
  </ul>
</nav>

<h2>Welcome!!</h2>
<%
DateFormat df = new SimpleDateFormat("EEEE,dd MMMM, yyyy");
String date = df.format(new Date());
out.println(date+"<br>");
String login = (String)session.getAttribute("login");
if("no".equals(login)){
	out.println("Click for <a href='/src/login.jsp?type=User'><font color=yellow>Login</a></font>");
}
else {
	out.println("Hello <font color=yellow>"+session.getAttribute("user")+"<br><a href=logout.jsp>Logout</a></font>");

if("Admin".equals(session.getAttribute("type"))){
	out.println("<br><br><a href='adduser.jsp'> Click here for admin Console");
}
else{
	out.println("<br><br><a href='RoomListServlet'> Click for User Console");
}
}
%>
<footer>Copyright &copy; LetUsChat.com</footer>

</div>

</body>
</html>