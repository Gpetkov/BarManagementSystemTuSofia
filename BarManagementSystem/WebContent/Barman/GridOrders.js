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
				if ( 150 == rec.data.orderStatusId && curTime - recTime > 5 * 60 )
				{
					Ext.create('widget.uxNotification', {
						position: 'br',
						useXAxis: false,
						cls: 'ux-notification-light',
						iconCls: 'ux-notification-icon-information',
						closable: false,
						title: 'Your time is up!',
						spacing: 20,
						html: rec.data.ordId + ' is late!',
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
            text: 'View Orders',
            iconCls:'icon-grid'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-orders');
        if(!win){
            win = desktop.createWindow({
                id: 'grid-orders',
                title:'View Orders',
                width:740,
                height:480,
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
                            text: 'ID',
                            width: 40,
                            sortable: true,
                            dataIndex: 'ordId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            header: 'Table',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'tableId',
                            field: {
                            	xtype: 'textfield'
                            }
                        }],
                        tbar:[{
                            text: 'Confirm',
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
                        html: 'Please select an order to see additional details.'
                    },{
                        id: 'items-table',
                        width: 150,
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
                            		store.filter( 'orderStatusId', 150 );
                            	}
                            }
                        }),
                        columns: [{
                            text: 'ID',
                            width: 40,
                            sortable: true,
                            dataIndex: 'ordId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            header: 'Table',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'tableId',
                            field: {
                            	xtype: 'textfield'
                            }
                        }],
                        tbar:[{
                            text: 'Done',
                            iconCls: 'icon-add',
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
                                	    }
                                	});
                                }
                            }
                        }],
                        listeners:{
                        	selectionchange: function(sm, selectedRecord) {
                        		if (selectedRecord.length) {
                                    var detailPanel = Ext.getCmp('detailPanel'),
                                    	data = selectedRecord[0].data,
                                    	bmItems = data.bmItems,
                                    	html = '<h1>Items:</h1><ol>';
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

