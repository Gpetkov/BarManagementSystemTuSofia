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
    }],
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

Ext.define("OrderItem", {
    extend: 'Ext.data.Model',
    idProperty: 'orderToItemId',
    fields: [{
        name: 'orderToItemId',
        type: 'int',
        useNull: true
    }, 'itmId', 'ordrId'],
    validations: [{
        type: 'length',
        field: 'ordrId',
        min: 1
    },{
        type: 'length',
        field: 'itmId',
        min: 1
    }]
});

Ext.define('Item', {
    extend: 'Ext.data.Model',
    idProperty: 'itmId',
    fields: [{
        name: 'itmId',
        type: 'int',
        useNull: true
    }, 'itmName', 'itmPrice']
});

Ext.define('Table', {
    extend: 'Ext.data.Model',
    idProperty: 'tblId',
    fields: [{
        name: 'tblId',
        type: 'int',
        useNull: true
    }, 'tblNumber']
});

var rowEditing;
var rowEditing2;

var storeItems;

Ext.onReady(function(){
	storeItems = Ext.create('Ext.data.Store', {
		    autoLoad: true,
		    model: 'Item',
		    proxy: {
		        type: 'rest',
		        url: 'http://localhost:8080/BarManagementSystem/jaxrs/item',
		        reader: {
		            type: 'json',
		            root: 'data'
		        },
		        writer: {
		            type: 'json'
		        }
		    }
		});
} );

Ext.define('MyDesktop.GridOrders', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.JsonStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.RowNumberer'
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
                        width: 220,
                        xtype: 'grid',
                        store: new Ext.data.JsonStore({
                        	autoLoad: true,
                            autoSync: true,
                            model: 'Order',
                            proxy: {
                                type: 'rest',
                                url: 'http://localhost:8080/BarManagementSystem/jaxrs/order',
                                reader: {
                                    type: 'json',
                                    root: 'data'
                                },
                                writer: {
                                    type: 'json'
                                }
                            },
                            listeners: {
                                write: function(store, operation){
                                    var record = operation.records[0],
                                        name = Ext.String.capitalize(operation.action),
                                        verb;
                                    
                                    if (name == 'Destroy') {
                                        verb = 'Destroyed';
                                    } else {
                                        verb = name + 'd';
                                    }
                                }
                            }
                        }),
                        plugins: [rowEditing = Ext.create('Ext.grid.plugin.RowEditing')],
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
                            	xtype: 'combobox',
                            	store: new Ext.data.JsonStore({
                            		autoLoad: true,
                                    model: 'Table',
                                    proxy: {
                                        type: 'rest',
                                        url: 'http://localhost:8080/BarManagementSystem/jaxrs/table',
                                        reader: {
                                            type: 'json',
                                            root: 'data'
                                        },
                                        writer: {
                                            type: 'json'
                                        }
                                    }
                                }),
                            	displayField: 'tblNumber',
                                valueField: 'tblId',
                            }
                        }],
                        tbar:[{
                            text: 'Add',
                            iconCls: 'icon-add',
                            handler: function(){
                            	Ext.getCmp( 'orders-table' ).getStore().insert(0, new Order({'orderStatusId':150}));
                                rowEditing.startEdit(0, 0);
                            }
                        }, '-', {
                            text: 'Delete',
                            iconCls: 'icon-delete',
                            handler: function(){
                                var selection = Ext.getCmp( 'orders-table' ).getView().getSelectionModel().getSelection()[0];
                                if (selection) {
                                	Ext.getCmp( 'orders-table' ).getStore().remove(selection);
                                }
                            }
                        }, '-', {
                            text: 'Refresh',
                            iconCls: 'icon-refresh',
                            handler: function(){
                            	Ext.getCmp( 'orders-table' ).getStore().reload();
                            }
                        }],
                        listeners:{
                        	selectionchange: function(sm, selectedRecord) {
                                if (selectedRecord.length) {
                                	var st = Ext.getCmp( 'items-table' ).getStore();
                                	st.clearFilter();
                                    st.filter( 'ordrId', sm.selected.items[0].data.ordId );
                                }
                            }
                        }
                    },{
                        id: 'items-table',
                        //width: 150,
                        region: 'center',
                        xtype: 'grid',
                        store: new Ext.data.JsonStore({
                        	autoLoad: true,
                            autoSync: true,
                            model: 'OrderItem',
                            proxy: {
                                type: 'rest',
                                url: 'http://localhost:8080/BarManagementSystem/jaxrs/ordertoitem',
                                reader: {
                                    type: 'json',
                                    root: 'data'
                                },
                                writer: {
                                    type: 'json'
                                }
                            },
                            listeners: {
                                write: function(store, operation){
                                    var record = operation.records[0],
                                        name = Ext.String.capitalize(operation.action),
                                        verb;
                                    
                                    if (name == 'Destroy') {
                                        verb = 'Destroyed';
                                    } else {
                                        verb = name + 'd';
                                    }
                                },
                                load: function(store){
                                	store.filter('ordId',-1);
                                }
                            }
                        }),
                        plugins: [rowEditing2 = Ext.create('Ext.grid.plugin.RowEditing')],
                        columns: [{
                            text: 'ID',
                            width: 40,
                            hidden: true,
                            dataIndex: 'orderToItemId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            text: 'Order ID',
                            width: 50,
                            dataIndex: 'ordrId',
                            hidden: true,
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: 'Item',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'itmId',
                            renderer: function(value){
                            	var rec = storeItems.findRecord( 'itmId', value );
                            	if ( rec )
                            	{
                            		return rec.data.itmName;
                            	}
                            	return value;
                            },
                            field: {
                            	xtype: 'combobox',
                            	store: new Ext.data.JsonStore({
                            		autoLoad: true,
                                    model: 'Item',
                                    proxy: {
                                        type: 'rest',
                                        url: 'http://localhost:8080/BarManagementSystem/jaxrs/item',
                                        reader: {
                                            type: 'json',
                                            root: 'data'
                                        },
                                        writer: {
                                            type: 'json'
                                        }
                                    }
                                }),
                            	displayField: 'itmName',
                                valueField: 'itmId',
                            }
                        }],
                        tbar:[{
                            text: 'Add',
                            iconCls: 'icon-add',
                            handler: function(){
                                var selection = Ext.getCmp( 'orders-table' ).getView().getSelectionModel().getSelection()[0];
                            	if ( selection )
                            	{
                            		Ext.getCmp( 'items-table' ).getStore().insert(0, new OrderItem({ordrId:selection.data.ordId}));
            	                    rowEditing2.startEdit(0, 0);
                            	}
                            }
                        }, '-', {
                            text: 'Delete',
                            iconCls: 'icon-delete',
                            handler: function(){
                                var selection = Ext.getCmp( 'items-table' ).getView().getSelectionModel().getSelection()[0];
                                if (selection) {
                                	Ext.getCmp( 'items-table' ).getStore().remove(selection);
                                }
                            }
                        }]
                    }
                ]
            });
        }
        return win;
    }
});

