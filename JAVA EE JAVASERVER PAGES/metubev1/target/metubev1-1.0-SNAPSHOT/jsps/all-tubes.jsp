<%@ page import="metube.domain.models.view.AllTubeViewModel" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="templates/head.jsp"/>
</head>
<body>
<% List<AllTubeViewModel> tubes = (List<AllTubeViewModel>) request.getAttribute("allTubes");%>
     <div class="container">
         <main>
             <div class="row">
                 <div class="col col-md-12 d-flex justify-content-center">
                     <h1>
                         All Tubes
                     </h1>
                 </div>
             </div>
             <hr/>
             <div class="row">
                 <div class="col col-md-12 d-flex justify-content-center">
                     <h3>
                         Check our tubes below
                     </h3>
                 </div>
             </div>
             <hr/>
             <div class="row">
                 <div class="col col-md-12 d-flex justify-content-center">
                     <% if(tubes.size() == 0) {%>
                        <p>No tubes - <a href="/tubes/create">Create some</a></p> 
                     <%} else { %>
                     <ul>
                         <% for (AllTubeViewModel tube : tubes) { %>
                         <li><a href="/tubes/details?name=<%= tube.getName()%>"><%= tube.getName()%></a></li>
                         <% } %>
                     </ul>
                <%}%>
                 </div>
             </div>
             <hr/>
             <div class="row">
                 <div class="col col-md-12 d-flex justify-content-center">
                     <a href="/">Back to home</a>
                 </div>
             </div>
         </main>
         <footer>
             <c:import url="templates/footer.jsp"/>
         </footer>
     </div>
</body>
</html>
