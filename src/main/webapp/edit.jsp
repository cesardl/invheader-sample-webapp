<%-- 
    Document   : success
    Created on : 10/05/2013, 02:05:49 PM
    Author     : pdiaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<!DOCTYPE html>
<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><bean:message key="welcome.title"/></title>
        <link rel="stylesheet" type="text/css" media="screen" href="css/themes/cupertino/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
        <script src="js/jquery-1.9.0.min.js" type="text/javascript"></script>
        <script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
        <script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function(){
                var theForm = $('form');
                theForm.submit(function() { 
                    $.ajax({
                        type: "POST",
                        url: "inv.do?do=edit",
                        data: theForm.serialize(),
                        success: recuperarMensaje
                    }); 
                    return false; 
                });
                
                function recuperarMensaje(data) {
                    var message = data.fail_message;
                    if(message) {
                        alert(message);
                    } else {
                        alert('Actualizacion exitosa');
                        window.opener.updateGrid(data);
                        window.close();
                    }
                }
            });
        </script>
    </head>
    <body>
        <html:form action="inv.do?do=edit">
            <table>
                <tr>
                    <td>Amount:</td>
                    <td><html:text property="amount" value="${invheader.amount}" /></td>
                </tr>
                <tr>
                    <td>Task:</td>
                    <td><html:text property="tax" value="${invheader.tax}" /></td>
                </tr>
                <tr>
                    <td>Notes:</td>
                    <td><html:text property="note" value="${invheader.note}" /></td>
                </tr>
                <tr>
                    <td></td>
                    <td><html:submit value="Guardar" /></td>
                </tr>
            </table>
        </html:form>
    </body>
</html:html>
