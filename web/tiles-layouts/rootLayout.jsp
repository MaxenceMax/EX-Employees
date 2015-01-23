<%-- 
    Document   : rootLayouts
    Created on : 20 janv. 2015, 14:37:38
    Author     : maxence
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>


<html>
    <head>
        <title><tiles:getAsString name="titleString"/></title>
    </head>

    <body>
        <table width="500" border="0" cellspacing="0" cellpadding="0">
            <!-- ============================================================ -->
            <!-- Begin topBanner -->
            <tiles:insert attribute="topBanner"/>
            <!-- End topBanner -->
            <!-- ============================================================ -->
        </table>

        <html:errors />


        <!-- ============================================================ -->
        <!-- Begin panel1 -->
        <tiles:insert attribute="panel1"/>
        <!-- End panel1 -->
        <!-- ============================================================ -->

        <table width="500" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td>
                    <!-- ============================================================ -->
                    <!-- Begin footer -->
                    <tiles:insert attribute="footer"/>
                    <!-- End footer -->
                    <!-- ============================================================ -->
                </td>
            </tr>
        </table>
        <!-- ============================================================ -->
        <!-- Begin footerDebug -->
        <tiles:insert attribute="footerDebug"/>
        <!-- End footerDebug -->
        <!-- ============================================================ -->
    </body>
</html>
