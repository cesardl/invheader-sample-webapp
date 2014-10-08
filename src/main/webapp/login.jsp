<%-- 
    Document   : login
    Created on : 10/05/2013, 02:03:47 PM
    Author     : pdiaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css">
    </head>
    <body>
        <h1>Login Form</h1>
        <html:form action="/login">
            <table border="0">
                <tr>
                    <td colspan="2">
                        <bean:write name="LoginForm" property="error" filter="false"/>
                        &nbsp;</td>
                </tr>
                <tr>
                    <td>Enter your name:</td>
                    <td><html:text property="name" /></td>
                </tr>
                <tr>
                    <td>Enter your email:</td>
                    <td><html:text property="email" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><html:submit value="Login" /></td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>
