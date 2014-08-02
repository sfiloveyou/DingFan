// create the data store
var ds = new Ext.data.Store({
    proxy: new Ext.data.HttpProxy({url:'dingfanAction.do?method=queryAll'}),  
    reader: new Ext.data.JsonReader({  
        root: 'root' 
    }, [  
        {name: 'id'},
        {name: 'personName', type: 'string'},
        {name: 'amount', type: 'int'},
        {name: 'paid', type: 'int'},
        {name: 'entertime', type: 'string', dateFormat: 'yyyy-mm-dd'},
        {name: 'lastIp', type: 'string'},
        {name: 'comments', type: 'string'}
    ])  
});
var sm = new Ext.grid.CheckboxSelectionModel();

function add(){
	
    // create the window on the first click and reuse on subsequent clicks
    if(!win){
    	var personStore = new Ext.data.Store({  
    	    proxy: new Ext.data.HttpProxy({  
    	        url: 'dingfanAction.do?method=queryPerson'
    	    }),  
            reader: new Ext.data.JsonReader({
                root: 'root'
            }, [
                {name: 'personName', mapping: 'personName'}
            ])
    	});
        win = new Ext.Window({
            applyTo     : 'mainWindow',
            layout      : 'fit',
            width       : 350,
            height      : 250,
            closeAction :'hide',
            plain       : true,
            items       : new Ext.FormPanel({
                frame:true,
                bodyStyle:'padding:5px 5px 0',
                items: [new Ext.form.ComboBox({
                    	id:'cb_personName',
                        fieldLabel: '订饭人',
                        queryParam:'personName',
                        valueField: 'personName',
                        displayField:'personName',
                        mode: 'remote',
                        minChars: 2,
                        triggerAction: 'all',
                        loadingText: 'loading...',
                        store:personStore,
                        allowBlank:false
                    }),new Ext.form.Field({
                    	id:'f_amount',
                        fieldLabel: '订饭数量',
                        value :1,
                        allowBlank:false
                    }),new Ext.form.TextArea({
                    	id:'f_comments',
                        fieldLabel: '备注',
                        width : 200,
                        maxLength : 50,
                        height : 100,
                        allowBlank:true
                    })
                ],

                buttons: [{
                    text: 'Save',
                    handler : function(){
                		save(win);
                	}	
                	},{
                    text: 'Cancel',
                    handler : function(){
                		win.findById('cb_personName').clearValue();
                		win.findById('f_amount').setValue(1);
                		win.hide();
            		}
                }]
            })
        });
    }
    win.show();
}
Date.prototype.format = function(format){
    /*
     * eg:format="yyyy-MM-dd hh:mm:ss";
     */
    if(!format){
        format = "yyyy-MM-dd hh:mm:ss";
    }
 
    var o = {
            "M+": this.getMonth() + 1, // month
            "d+": this.getDate(), // day
            "h+": this.getHours(), // hour
            "m+": this.getMinutes(), // minute
            "s+": this.getSeconds(), // second
            "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
            "S": this.getMilliseconds()
            // millisecond
    };
 
    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
 
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" +o[k]).length));
        }
    }
    return format;
}

function save(obj){

	var comments = obj.findById('f_comments').getValue();
	if(comments.length >50){
		Ext.Msg.alert('提示', '备注字太多');
		return;
	}
	document.getElementById("personNameForm").value=obj.findById('cb_personName').getRawValue();
	document.getElementById("amountForm").value=obj.findById('f_amount').getRawValue();
	document.getElementById("commentsForm").value=comments;	
	form.action='dingfanAction.do?method=save';
	form.submit();
}

function start(){
	
	form.action='dingfanAction.do?method=startDingFan';
	form.submit();
}

function stop(){
	
	form.action='dingfanAction.do?method=stopDingFan';
	form.submit();
}
function pay(grid){
	var selectRows = grid.getSelectionModel().getSelections();
	if(selectRows.length > 0){
		var ids = '';
		for(var i=0;i<selectRows.length;i++){
			var id = selectRows[i].get('id');
			ids +=','+id;
		}
		document.getElementById('selectedIdForm').value=ids.substring(1);
		form.action='dingfanAction.do?method=pay';
		form.submit();
	}
}
function refresh(){
	
	form.action=document.getElementById("pageForm").value+'.jsp';
	form.submit();
}
function login(){
	//form.action='login.jsp';
	//form.submit();
}
function logout(){
	form.action='index.jsp';
	form.submit();
}
function cal(grid){
	var allRows=grid.getStore().getRange(0,grid.getStore().getCount());
	var total_amount =0;
	var paid_amount=0;
	for(var i=0;i<allRows.length;i++){
		var amount = allRows[i].get('amount');
		total_amount +=amount;
		if(allRows[i].get('paid')>0){
			paid_amount += amount;
		}
	}
	Ext.Msg.alert('提示','共计'+total_amount+'份，应收'+ total_amount * 9 + '元，实收'+paid_amount * 9+'元');
}


function paid(val){
    if(val > 0){
        return '<span style="color:green;">是</span>';
    }else {
        return '<span style="color:red;">否</span>';
    }
    return val;
}

function formatTime(val){
    
	return '<span style="color:green;">'+new Date(parseInt(val)).format()+'</span>';
}