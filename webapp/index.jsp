<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<tags:importJsLibs ext="true" />
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
<script type="text/javascript" src="js/dingfan.js"></script>
<script type="text/javascript">
var form;
var win;
Ext.onReady(function(){
	form = document.getElementById("dingfan");
	Ext.QuickTips.init();

    var cm = new Ext.grid.ColumnModel([  
                                       sm,  
                                       {id:'id',header: "id", width: 30, sortable: true, dataIndex: 'id',hidden :true},
                                       {header: "订饭人", width: 105, sortable: true,  dataIndex: 'personName'},
                                       {header: "订饭数量", width: 60, sortable: true, dataIndex: 'amount'},
                                       {header: "已经付账", width: 60, sortable: true,  renderer: paid,dataIndex: 'paid'},
                                       {header: "填写时间", width: 120, sortable: true, renderer: formatTime,dataIndex: 'entertime'},
                                       {header: "最后IP", width: 150, sortable: true,dataIndex: 'lastIp',hidden :true},
                                       {header: "备注", width: 250, sortable: true,dataIndex: 'comments'}
                                   ]);
    // create the Grid
    var grid = new Ext.grid.GridPanel({
    	cm:cm,
    	sm:sm,
    	ds: ds,
    	layout :'fit',
        stripeRows: true,
        height:600,
        width:950,
        title:'订饭',
        tbar: [
        {
            text: '新增',
            handler : function(){
        		add();
            }
        },{
            text: '删除',
            handler : function(){
        		del(grid);
            }
        },{
            text: '刷新',
            handler : function(){
        		refresh();
            }
        },{
            text: '菜单',
            handler : function(){
        		menu(grid);
            }
        }]
    });
    grid.render('mainGrid');
    ds.load();
    if('<%= request.getSession().getServletContext().getAttribute("startDingFan")%>' == 'false'){
        grid.disable();
        Ext.Msg.alert('提示', '订饭已结束');
    }
})

function menu(grid){
	window.open('images/DSC_0003.jpg')
}
function del(grid){
	var selectRows = grid.getSelectionModel().getSelections();
	if(selectRows.length > 0 ){
		var ids = '';
		var ip = document.getElementById('ip').value.trim();
		for(var i=0;i<selectRows.length;i++){
			if((selectRows[i].get('lastIp').trim() != ip) || selectRows[i].get('paid')>0 ){
				Ext.Msg.alert('提示', '你不能删除这条记录');
				return;
			}
			
			var id = selectRows[i].get('id');
			ids +=','+id;
		}
		document.getElementById('selectedIdForm').value=ids.substring(1);
		form.action='dingfanAction.do?method=del';
		form.submit();
	}

}

</script>
<style type="text/css">
</style>
</head>

<body>
<%
String ip = request.getHeader("x-forwarded-for"); 
if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
    ip = request.getHeader("Proxy-Client-IP"); 
} 
if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
    ip = request.getHeader("WL-Proxy-Client-IP"); 
} 
if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
    ip = request.getRemoteAddr(); 
}
request.setAttribute("ip",ip);

%>

<form id="dingfan" action="" method="post">
	<input id="personNameForm" name="personName" type="hidden" />
	<input id="amountForm" name="amount" type="hidden" />
	<input id="commentsForm" name="comments" type="hidden" />
	<input id="selectedIdForm" name="selectedId" type="hidden" />
	<input id="pageForm" name="page" value="index" type="hidden" />
	<input id="ip" name="ip" value="<%= ip%>" type="hidden" />
</form>

<table align="center">
  <tr>
    <td>
		<div id="mainGrid"></div>
		<div id="mainWindow" ></div>
    </td>
  </tr>
</table>

</body>
</html>