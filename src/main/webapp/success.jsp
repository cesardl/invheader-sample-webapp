<%-- 
    Document   : success
    Created on : 10/05/2013, 02:05:49 PM
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
        <link rel="stylesheet" type="text/css" media="screen" href="css/themes/cupertino/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" media="screen" href="css/ui.jqgrid.css" />
        <script src="js/jquery-1.9.0.min.js" type="text/javascript"></script>
        <script src="js/i18n/grid.locale-en.js" type="text/javascript"></script>
        <script src="js/jquery.jqGrid.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(function(){
                loadGrid();
                
                $("#a1").click(function(){
                    var id = jQuery("#list").jqGrid('getGridParam','selrow'); 
                    if (id) { 
                        var ret = jQuery("#list").jqGrid('getRowData',id); 
                        alert("id="+ret.invid+" invdate="+ret.invdate+" note="+ret.note); 
                    } else { 
                        alert("Please select row");
                    }
                });
                
                $('#a2').click(function() {
                    var id = jQuery("#list").jqGrid('getGridParam','selrow'); 
                    if (id) { 
                        var ret = jQuery("#list").jqGrid('getRowData',id); 
                        window.open('inv.do?do=loadInv&invid='+ret.invid,'nuevo',
                        'directories=no,location=no,menubar=no, scrollbars=yes, statusbar=no, titlebar=no, resizable=yes, width=300,height=300');
                    } else { 
                        alert("Please select row");
                    }
                });
            });
            
            function loadGrid() {
                $("#list").jqGrid({
                    caption: 'My first grid',
                    url: 'inv.do?do=listarInvheader',
                    datatype: 'json',
                    width: '100%',
                    height: 230,
                    hidegrid: false,
                    hoverrows: false,
                    pager: '#pager',
                    pgbuttons: false,
                    pginput: false,
                    rowNum:9999,
                    recordtext: "Total {2}",
                    viewrecords: true,
                    multiselect: true,
                    colNames:['Inv No','Date', 'Client Detail','Amount','Tax','Total','Notes'],
                    colModel :[ 
                        {name:'invid', index:'invid', width:100, sortable:false, resizable: false}, 
                        {name:'invdate', index:'invdate', width:90, sortable:false, resizable: false, date:true}, 
                        {name:'clientDetail', index:'clientDetail', width:150, sortable:false, resizable: false, formatter: myformatter}, 
                        {name:'amount', index:'amount', width:80, sortable:false, resizable: false, align:'right'}, 
                        {name:'tax', index:'tax', width:80, sortable:false, resizable: false, align:'right'}, 
                        {name:'total', index:'total', width:80, sortable:false, resizable: false, align:'right'}, 
                        {name:'note', index:'note', width:150, sortable:false, resizable: false} 
                    ]
                });
            }
            
            function myformatter ( cellvalue, options, rowObject ) {
                // format the cellvalue to new format
                return 'Client '+rowObject.clientId;
            }
            function updateGrid(data) {
                var su=jQuery("#list").jqGrid('setRowData',data.invid,data);
                if(su) alert("Succes. Write custom code to update row in server"); else alert("Can not update");
            }
        </script>
    </head>
    <body>
        <h1>Congratulations!</h1>

        <h2><bean:message key="welcome.heading"/></h2>

        <p><bean:message key="welcome.message"/></p>

        <p>You have successfully logged in.</p>

        <p>Your name is: <bean:write name="LoginForm" property="name" />.</p>

        <p>Your email address is: <bean:write name="LoginForm" property="email" />.</p>
        <table id="list"><tr><td/></tr></table> 
        <div id="pager"></div>
        <br /> <a href="#" id="a1">Get data from selected row</a>
        <br /> <a href="#" id="a2">Edit row</a>
    </body>
</html:html>
