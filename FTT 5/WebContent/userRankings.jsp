<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.HashSet" import = "Login.UserBean" import = "Login.UserDAO" import = "java.util.Arrays"
    import = "java.util.Comparator"
    import = "Login.UserRank"
    import = "java.util.LinkedList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Rankings</title>
</head>
<body>


<%


//HashSet<UserBean> allUsers = new HashSet<UserBean>();
String rank = request.getParameter("rank");
if (rank== null) rank = "avgspeed";
LinkedList<UserRank> rankList = UserDAO.fillAllUsers(rank);
//application.setAttribute("all users", allUsers);	
	
// UserBean[] beans = rankList.toArray(new UserBean[allUsers.size()]);
// Arrays.sort(beans, new Comparator<UserBean>() {

// 	@Override
// 	public int compare(UserBean arg0, UserBean arg1) {
// 		if (rank == null || rank.equals("avg"))
// 		return arg0.getAvgSpeed() < arg1.getAvgSpeed() ? 1 : -1; 
// 		if (rank.equals("best"))
// 			return arg0.getBestSpeed() < arg1.getBestSpeed() ? 1 : -1;
// 		if (rank.equals("won"))
// 			return arg0.getRacesWon() < arg1.getRacesWon() ? 1 : -1;
// 		if (rank.equals("words"))
// 			return arg0.getTotalWordsTyped() < arg1.getTotalWordsTyped() ? 1 : -1;
// 		return 0;
// 	}
	
	
// });



%>

<%
String rankBy = null;
if (rank == null || rank.equals("avgspeed")) {
	rankBy = "Avg WPM";} 
else if (rank.equals("bestspeed")) {
		rankBy = "Fastest Speed";}
else if (rank.equals("raceswon")) {
		rankBy = "Races Won";}
else if (rank.equals("totalwordstyped")) {
		rankBy = "Total Words Typed";}


%>

            <table class="table table-condensed">
              <thead>
                <tr>
                  <th> User ID </th>
                  <th> Score </th>
                </tr>
              </thead>
              <tbody>


<% 
for (UserRank userRank : rankList) {
// for (UserBean bean : beans) {
// 	int value = 0;
// 	if (rank == null || rank.equals("avg")) {
// 		value = bean.getAvgSpeed();}
// 	else if (rank.equals("best")) {
// 			value = bean.getBestSpeed();}
// 	else if (rank.equals("won")) {
// 			value = bean.getRacesWon();}
// 	else if (rank.equals("words")) {
// 			value = bean.getTotalWordsTyped();		
// 	}

%>
<%-- Phone Type : <%= userRank.userid %>  <%= rankBy %>: <%= userRank.score %> --%>      
                <tr>
                  <td> <%= userRank.userid %> </td>
                  <td> <%= userRank.score %> </td>
                </tr>

<%} %>
              </tbody>
            </table>
 

</body>
</html>