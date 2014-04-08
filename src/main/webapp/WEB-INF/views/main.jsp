<%@page contentType="text/html" import="java.util.Date"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
  <meta charset="utf-8">
  <title>Web App Ranking</title>
  <link rel="stylesheet" href="<c:url value="/resources/css/ui-lightness/jquery-ui-1.10.4.custom.css" />" type="text/css">
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.js" /> "></script>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.10.4.custom.js" /> "></script>
  <script type="text/javascript">
  $(function() {
    $( "#datepicker" ).datepicker({
      dateFormat: 'yy-mm-dd',
      onSelect : function(){
        $('#dynamictable').html('');
        $('#dynamictable').append('<table></table>'); 
        var table = $('#dynamictable').children();
        
        $.getJSON("webapp", { datepicker: $('#datepicker').val() }, function(webapp){

          if(webapp.length == 0){
              $('#dynamictable').html('SORRY NO SEARCH RESULT!');
          } else {
              table.append("<thead><tr><th width='20%' align='left'>Date</th><th width='40%' align='left'>Website</th><th width='40%' align='left'>Visit</th></tr></thead>");
              $.each(webapp, function(i, item){
                table.append("<tr><td>" + item.rankdate + "</td><td>" + item.website + "</td><td>" + item.visit + "</td></tr>");          
              });
          }
        });
      }});
  });
  </script>
</head>
<body>
    <form 1action="webapp" name="webappranking" method="POST">
        <p>Date: <input type="text" id="datepicker"></p>
        <p><div id="dynamictable" /></p>
    </form>
</body>
</html>