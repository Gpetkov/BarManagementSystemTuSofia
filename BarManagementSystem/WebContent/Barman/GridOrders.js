/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Order', {
    extend: 'Ext.data.Model',
    idProperty: 'ordId',
    fields: [{
        name: 'ordId',
        type: 'int',
        useNull: true
    }, {
    	name: 'tableId',
    	mapping: 'bmTable.tblNumber'
    }, {
    	name: 'orderStatusId',
    	mapping: 'bmOrderStatus.ordstId'
    }, 'ordDateUpdated', 'bmItems'],
    validations: [{
        type: 'length',
        field: 'tableId',
        min: 1
    },{
        type: 'length',
        field: 'orderStatusId',
        min: 1
    }]
});

var bookTpl;

Ext.onReady(function(){
	function checkOrderStatuses()
	{
		if ( Ext.getCmp( 'items-table' ) && Ext.getCmp( 'items-table' ).items.length )
		{
			var st = Ext.getCmp( 'items-table' ).getStore();
			st.each( function( rec ){
				var recTime = Ext.util.Format.date( rec.data.ordDateUpdated, 'U' ),
					curTime = Ext.util.Format.date( new Date(), 'U' );
				if ( 3 == rec.data.bmOrderStatus.ordstId && curTime - recTime > 5 * 60 )
				{
					Ext.create('widget.uxNotification', {
						position: 'br',
						useXAxis: false,
						cls: 'ux-notification-light',
						iconCls: 'ux-notification-icon-information',
						closable: false,
						title: '\u041F\u043E\u0431\u044A\u0440\u0437\u0430\u0439!',
						spacing: 20,
						html: '\u041F\u043E\u0440\u0447\u044A\u0447\u043A\u0430 \u043D\u043E\u043C\u0435\u0440' + rec.data.ordId + ' \u0435 \u0437\u0430\u043A\u044A\u0441\u043D\u044F\u043B\u0430!',
						slideInDuration: 800,
						slideBackDuration: 1500,
						autoCloseDelay: 4000,
						paddingY: 30,
						slideInAnimation: 'elasticIn',
						slideBackAnimation: 'elasticIn'
					}).show();
				}
			} );
		}
		setTimeout( checkOrderStatuses, 1000 );
	}
	setTimeout( checkOrderStatuses, 1000 );
	var bookTplMarkup = [
	                     'ordId: <a href="{DetailPageURL}" target="_blank">{ordId}</a><br/>',
	                     'tableId: {tableId}<br/>',
	                     'bmItems: {bmItems}<br/>'
	                 ];
	bookTpl = Ext.create('Ext.Template', bookTplMarkup);
});

Ext.define('MyDesktop.GridOrders', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.JsonStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.ux.window.Notification'
    ],

    id:'grid-orders',

    init : function(){
        this.launcher = {
            text: '\u041F\u0440\u0435\u0433\u043B\u0435\u0434 \u043D\u0430 \u043F\u043E\u0440\u044A\u0447\u043A\u0438\u0442\u0435',
            iconCls:'icon-grid'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-orders');
        if(!win){
            win = desktop.createWindow({
                id: 'grid-orders',
                title:'\u041F\u0440\u0435\u0433\u043B\u0435\u0434 \u043D\u0430 \u043F\u043E\u0440\u044A\u0447\u043A\u0438\u0442\u0435',
                width:817,
                height:502,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'border',
                items: [
                    {
                        id: 'orders-table',
                        region: 'west',
                        xtype: 'grid',
                        store: new Ext.data.JsonStore({
                        	autoLoad: true,
                            autoSync: true,
                            model: 'Order',
                            proxy: {
                                type: 'rest',
                                url: 'http://localhost:8080/BarManagementSystem/jaxrs/order/orderwithoutbarman',
                                reader: {
                                    type: 'json',
                                    root: 'data'
                                },
                                writer: {
                                    type: 'json'
                                }
                            }
                        }),
                        columns: [{
                            text: '\u0423\u043D\u0438\u043A\u0430\u043B\u0435\u043D \u043D\u043E\u043C\u0435\u0440',
                            width: 116,
                            sortable: true,
                            dataIndex: 'ordId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            header: '\u041C\u0430\u0441\u0430',
                            width: 76,
                            sortable: true,
                            dataIndex: 'tableId',
                            field: {
                            	xtype: 'textfield'
                            }
                        }],
                        tbar:[{
                            text: '\u041F\u0440\u0438\u0435\u043C\u0438',
                            iconCls: 'icon-add',
                            handler: function(){
                            	var selection = Ext.getCmp( 'orders-table' ).getView().getSelectionModel().getSelection()[0];
                                if (selection) {
                                	Ext.Ajax.request({
                                	    url: 'http://localhost:8080/BarManagementSystem/jaxrs/order/' + selection.data.ordId + '/addbarman',
                                	    method: 'PUT',
                                	    success: function(response){
                                	        var text = response.responseText;
                                	        Ext.getCmp( 'orders-table' ).getStore().reload();
                                	        Ext.getCmp( 'items-table' ).getStore().reload();
                                	    }
                                	});
                                }
                            }
                        }]
                    },{
                    	xtype: 'panel',
                        region: 'center',
                        id: 'detailPanel',
                        bodyPadding: 7,
                        bodyStyle: "background: #ffffff;",
                        html: '\u041C\u043E\u043B\u044F \u0438\u0437\u0431\u0435\u0440\u0435\u0442\u0435 \u043F\u043E\u0440\u044A\u0447\u043A\u0430, \u0437\u0430 \u0434\u0430 \u0432\u0438\u0434\u0438\u0442\u0435 \u043F\u044A\u043B\u043D\u0430\u0442\u0430 \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F.'
                    },{
                        id: 'items-table',
                        width: 191,
                        region: 'east',
                        xtype: 'grid',
                        store: new Ext.data.JsonStore({
                        	autoLoad: true,
                            autoSync: true,
                            model: 'Order',
                            proxy: {
                                type: 'rest',
                                url: 'http://localhost:8080/BarManagementSystem/jaxrs/order/barmanorders',
                                reader: {
                                    type: 'json',
                                    root: 'data'
                                },
                                writer: {
                                    type: 'json'
                                }
                            },
                            listeners: {
                            	load: function( store ){
                            		//store.filter( 'orderStatusId', 150 );
                            	}
                            }
                        }),
                        columns: [{
                            text: '\u0423\u043D\u0438\u043A\u0430\u043B\u0435\u043D \u043D\u043E\u043C\u0435\u0440',
                            width: 116,
                            sortable: true,
                            dataIndex: 'ordId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            header: '\u041C\u0430\u0441\u0430',
                            width: 76,
                            sortable: true,
                            dataIndex: 'tableId',
                            field: {
                            	xtype: 'textfield'
                            }
                        }],
                        tbar:[{
                            text: '\u0413\u043E\u0442\u043E\u0432\u0430',
                            iconCls: 'icon-done',
                            handler: function(){
                            	var selection = Ext.getCmp( 'items-table' ).getView().getSelectionModel().getSelection()[0];
                                if (selection) {
                                	Ext.Ajax.request({
                                	    url: 'http://localhost:8080/BarManagementSystem/jaxrs/order/' + selection.data.ordId + '/makedone',
                                	    method: 'PUT',
                                	    success: function(response){
                                	        var text = response.responseText;
                                	        Ext.getCmp( 'orders-table' ).getStore().reload();
                                	        Ext.getCmp( 'items-table' ).getStore().reload();
                                	        var detailPanel = Ext.getCmp('detailPanel'),
                                        	data = selectedRecord[0].data,
                                        	bmItems = data.bmItems,
                                        	html = '<h1>\u041F\u0440\u043E\u0434\u0443\u043A\u0442\u0438:</h1><ol>';
                                        for ( var i = 0; i < bmItems.length; i++ )
                                    	{
                                    		html += '<li>' + bmItems[ i ].itmName + '</li>';
                                    	}
                                	    }
                                	});
                                }
                                Ext.getCmp('detailPanel').update('\u041C\u043E\u043B\u044F \u0438\u0437\u0431\u0435\u0440\u0435\u0442\u0435 \u043F\u043E\u0440\u044A\u0447\u043A\u0430, \u0437\u0430 \u0434\u0430 \u0432\u0438\u0434\u0438\u0442\u0435 \u043F\u044A\u043B\u043D\u0430\u0442\u0430 \u0438\u043D\u0444\u043E\u0440\u043C\u0430\u0446\u0438\u044F.');
                            }
                        }],
                        listeners:{
                        	selectionchange: function(sm, selectedRecord) {
                        		if (selectedRecord.length) {
                                    var detailPanel = Ext.getCmp('detailPanel'),
                                    	data = selectedRecord[0].data,
                                    	bmItems = data.bmItems,
                                    	html = '<h1>\u041F\u0440\u043E\u0434\u0443\u043A\u0442\u0438:</h1><ol>';
                                    for ( var i = 0; i < bmItems.length; i++ )
                                	{
                                		html += '<li>' + bmItems[ i ].itmName + '</li>';
                                	}
                                    html += '</ol>';
                                    detailPanel.body.setHTML( html );
                                }
                            }
                        }
                    }
                ]
            });
        }
        return win;
    }
});

