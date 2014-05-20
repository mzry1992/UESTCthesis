<%--
 Default decorator
--%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" ng-app="cdoj">
<head>
  <page:applyDecorator name="head" page="/WEB-INF/views/common/header.jsp"/>
  <decorator:head/>

  <title><decorator:title/> - UESTC Online Judge</title>
</head>

<body>
<page:applyDecorator name="body"
                     page="/WEB-INF/views/common/navbarTop.jsp"/>
<div id="cdoj-layout">
  <div id="cdoj-navbar">
    <page:applyDecorator name="body"
                         page="/WEB-INF/views/common/navbarList.jsp"/>
  </div>
  <div id="cdoj-container">
    <decorator:body/>
  </div>
</div>

<page:applyDecorator name="body"
                     page="/WEB-INF/views/common/modal.jsp"/>
<page:applyDecorator name="head"
                     page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>
