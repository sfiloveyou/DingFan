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
                                       {id:'id',header: "id", width: 30, sortable: true, dataIndex: 'id'},
                                       {header: "订饭人", width: 105, sortable: true,  dataIndex: 'personName'},
                                       {header: "订饭数量", width: 60, sortable: true, dataIndex: 'amount'},
                                       {header: "已经付账", width: 60, sortable: true,  renderer: paid,dataIndex: 'paid'},
                                       {header: "填写时间", width: 120, sortable: true, renderer: formatTime,dataIndex: 'entertime'},
                                       {header: "备注", width: 250, sortable: true,dataIndex: 'comments'},
                                       {header: "最后IP", width: 130, sortable: true,dataIndex: 'lastIp'}
                                   ]);
    // create the Grid
    var grid = new Ext.grid.GridPanel({
    	cm:cm,
    	sm:sm,
    	ds: ds,
    	//layout :'fit',
        stripeRows: true,
        height:600,
        autoWidth:true,
        title:'订饭',
        tbar: [
        {
            text: '登陆',
            handler : function(){
        		login(grid);
            }
        },{
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
            text: '统计',
            handler : function(){
        		cal(grid);
            }
        },{
            text: '已付帐',
            handler : function(){
        		pay(grid);
            }
        },{
            text: '退出',
            handler : function(){
        		logout(grid);
            }
        },{
            text: '开始订饭',
            handler : function(){
        		start();
            }
        },{
            text: '结束订饭',
            handler : function(){
        		stop();
            }
        }]
    });
    grid.render('mainGrid');
    ds.load();
});

function del(grid){
	var selectRows = grid.getSelectionModel().getSelections();
	if(selectRows.length > 0){
		var ids = '';
		for(var i=0;i<selectRows.length;i++){
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
<form id="dingfan" action="" method="post">
	<input id="personNameForm" name="personName" type="hidden" />
	<input id="amountForm" name="amount" type="hidden" />
	<input id="commentsForm" name="comments" type="hidden" />
	<input id="selectedIdForm" name="selectedId" type="hidden" />
	<input id="pageForm" name="page" value="admin" type="hidden" />
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