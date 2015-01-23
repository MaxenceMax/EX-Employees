<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<hr>
<p><b><u>Debug Information</u></b></p>
<%
java.util.Enumeration e = null;
%>
<p>
<b>System Properties:</b>
<ul>
<li>Date = <%= new java.util.Date(System.currentTimeMillis()) %></li>
</ul>
</p>
<p>
<b>Request properties:</b>
<ul>
<li>request.getAuthType() = <%= request.getAuthType() %></li>
<li>request.getContextPath() = <%= request.getContextPath() %></li>
<li>request.getMethod() = <%= request.getMethod() %></li>
<li>request.getRemoteHost() = <%= request.getRemoteHost() %></li>
</ul>
<b>Request attributes:</b>
<ul>
<%
e = request.getAttributeNames();
while(e.hasMoreElements()) {
  String attName = (String)e.nextElement();
  Object attValue = request.getAttribute(attName);
  out.println("<li>" + attName + " = " + attValue.toString() + "</li>");
}
%>
</ul>
<b>Request parameters:</b>
<ul>
<%
e = request.getParameterNames();
while(e.hasMoreElements()) {
  String paramName = (String)e.nextElement();
  String paramValue = request.getParameter(paramName);
  out.println("<li>" + paramName + " = " + paramValue + "</li>");
}
%>
</ul>
<b>Request headers:</b>
<ul>
<%
e = request.getHeaderNames();
while(e.hasMoreElements()) {
  String headerName = (String)e.nextElement();
  String headerValue = request.getHeader(headerName);
  out.println("<li>" + headerName + " = " + headerValue + "</li>");
}
%>
</ul>
</p>
<p>
<b>Session properties:</b>
<ul>
<li>session.getCreationTime() = <%= (new java.util.Date(session.getCreationTime())).toString() %></li>
<li>session.getId() = <%= session.getId() %></li>
<li>session.getLastAccessedTime() = <%= (new java.util.Date(session.getLastAccessedTime())).toString() %></li>
<li>session.getMaxInactiveInterval() = <%= session.getMaxInactiveInterval() %></li>
</ul>
<b>Session attributes:</b>
<ul>
<%
e = session.getAttributeNames();
while(e.hasMoreElements()) {
  String attName = (String)e.nextElement();
  Object attValue = session.getAttribute(attName);
  out.println("<li>" + attName + " = " + attValue.toString() + "</li>");
}
%>
</ul>
</p>
<p>
<b>Security attributes of HttpServletRequest:</b>
<ul>
<li>request.getRemoteUser() = <%= request.getRemoteUser() %></li>
<li>request.isUserInRole("role1") = <%= request.isUserInRole("role1") %></li>
<li>request.isUserInRole("role2") = <%= request.isUserInRole("role2") %></li>
<li>request.isUserInRole("role3") = <%= request.isUserInRole("role3") %></li>
<li>request.isUserInRole("role4") = <%= request.isUserInRole("role4") %></li>
<li>request.getUserPrincipal() = <%= request.getUserPrincipal() %></li>
</ul>
</p>
<p>
<b>ServletContext (Application) attributes:</b>
<ul>
<%
e = application.getAttributeNames();
while(e.hasMoreElements()) {
  String attName = (String)e.nextElement();
  Object attValue = application.getAttribute(attName);
  out.println("<li>" + attName + " = " + attValue.toString() + "</li>");
}
%>
</ul>
<b>ServletContext (Application) init parameters:</b>
<ul>
<%
e = application.getInitParameterNames();
while(e.hasMoreElements()) {
  String paramName = (String)e.nextElement();
  String paramValue = application.getInitParameter(paramName);
  out.println("<li>" + paramName + " = " + paramValue + "</li>");
}
%>
</ul>
</p>
<p>
<b>PageContext attributes:</b>
<ul>
<%
e = pageContext.getAttributeNamesInScope(javax.servlet.jsp.PageContext.PAGE_SCOPE);
while(e.hasMoreElements()) {
  String attName = (String)e.nextElement();
  Object attValue = pageContext.getAttribute(attName);
  out.println("<li>" + attName + " = " + attValue.toString() + "</li>");
}
%>
</ul>
</p>
