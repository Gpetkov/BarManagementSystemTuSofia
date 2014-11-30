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
                            flex: 1,
                            sortable: true,
                            dataIndex: 'tblNumber',
                            field: {
                            	xtype: 'combobox',
                            	typeAhead : true,
								allowBlank : false,
								blankText: '\u041F\u043E\u043B\u0435\u0442\u043E \"\u041C\u0430\u0441\u0430\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.',
								triggerAction : 'all',
								selectOnTab : true,
								forceSelection: true,
                            	store: new Ext.data.JsonStore({
                            		autoLoad: true,
                                    model: 'Table',
                                	allowBlank : false,
									blankText: '\u041F\u043E\u043B\u0435\u0442\u043E \"\u041C\u0430\u0441\u0430\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.',
									triggerAction : 'all',
									selectOnTab : true,
									forceSelection: true,
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
                                valueField: 'tblNumber',
                            }
                        }],
                        tbar:[{
                            text: '\u0414\u043E\u0431\u0430\u0432\u0438',
                            iconCls: 'icon-add',
                            handler: function(){
                            	Ext.getCmp( 'orders-table' ).getStore().insert(0, new Order({'orderStatusId':2}));
                                rowEditing.startEdit(0, 0);
                            }
                        }, '-', {
                            text: '\u0418\u0437\u0442\u0440\u0438\u0439',
                            iconCls: 'icon-delete',
                            handler: function(){
                                var selection = Ext.getCmp( 'orders-table' ).getView().getSelectionModel().getSelection()[0];
                                if (selection) {
                                	Ext.getCmp( 'orders-table' ).getStore().remove(selection);
                                }
                            }
                        }, '-', {
                            text: '\u041E\u0431\u043D\u043E\u0432\u0438',
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
                            text: '\u0423\u043D\u0438\u043A\u0430\u043B\u0435\u043D \u043D\u043E\u043C\u0435\u0440',
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
                            text: '\u041D\u043E\u043C\u0435\u0440 \u043D\u0430 \u043F\u043E\u0440\u044A\u0447\u043A\u0430',
                            width: 50,
                            dataIndex: 'ordrId',
                            hidden: true,
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: '\u041F\u0440\u043E\u0434\u0443\u043A\u0442',
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
                            	typeAhead : true,
								allowBlank : false,
								blankText: '\u041F\u043E\u043B\u0435\u0442\u043E \"\u041F\u0440\u043E\u0434\u0443\u043A\u0442\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.',
								triggerAction : 'all',
								selectOnTab : true,
								forceSelection: true,
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
                            text: '\u0414\u043E\u0431\u0430\u0432\u0438',
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
                            text: '\u0418\u0437\u0442\u0440\u0438\u0439',
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

