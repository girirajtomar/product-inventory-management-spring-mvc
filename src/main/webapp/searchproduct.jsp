<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<style>
table, th, td {
  border:1px solid black;
}
#logout{
	margin-left:1200px;
}
</style>
<body>
<form method="GET" action="logout" >
<input type="submit" id="logout" value="Logout" ></button>
</form>
<table style="width:100%">
  <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>COLOUR</th>
            <th>GENDER</th>
            <th>AVAILABILITY</th>
            <th>PRICE</th>
            <th>RATING</th>
            <th>SIZE</th>
  </tr>
  <c:forEach items="${tshirtArr}" var="i" >
	  <tr align="center">
	            <td>${i.id }</td>
	            <td>${i.name}</td>
	            <td>${i.colour}</td>
	            <td>${i.gender}</td>
	            <td>${i.availablity}</td>
	            <td>${i.price}</td>
	            <td>${i.rating}</td>
	            <td>${i.size}</td>
	  </tr>
  </c:forEach>
  <tr>
    
  </tr>
</table>

<br><br>
<form method="POST" id="detail-form" name="searchForm" action="search" accept-charset="UTF-8">
<table style="width:50%" align="center">
  <tr>
            <th>Colour</th>
            <th>Size</th>
            <th>Gender</th>
            <th>Sort By</th>
  </tr>

  <tr align="center">
            <td><input name="colour" size="20"/></td>
            <td><input name="size" size="20"/></td>
            <td><input name="gender" size="20"/></td>
            <td><input name="sortBy" size="20" value=""/></td>
  </tr>
</table>
<br>
<div id="buttons" align="center">
        <input type="submit" value="Search" ></button>
</div>
</form>



</body>
</html>

