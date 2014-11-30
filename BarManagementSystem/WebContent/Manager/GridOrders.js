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
    }, 'userCreated', 'orderBarman', 'bmOrderStatus', 'bmItems', 'ordDateCreated', 'ordDateUpdated'],
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
                width:861,
                height:480,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {
                        border: false,
                        id: 'orders-table',
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
                            }
                        }),
                        columns: [{
                            text: '\u0423\u043D\u0438\u043A\u0430\u043B\u0435\u043D \u043D\u043E\u043C\u0435\u0440',
                            width: 90,
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
                            width: 60,
                            sortable: true,
                            dataIndex: 'tableId',
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: '\u0421\u0435\u0440\u0432\u0438\u0442\u044C\u043E\u0440',
//                            flex: 1,
                            width: 100,
                            sortable: true,
                            dataIndex: 'userCreated',
                        	renderer: function( v ){
                        		if ( v ){
                        			return v.usrUsername;
                        		}
                        		return '';
                        	},
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: '\u0411\u0430\u0440\u043C\u0430\u043D',
                            width: 100,
//                            flex: 1,
                            sortable: true,
                            dataIndex: 'orderBarman',
                        	renderer: function( v ){
                        		if ( v ){
                        			return v.usrUsername;
                        		}
                        		return '';
                        	},
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: '\u0421\u0442\u0430\u0442\u0443\u0441',
                            width: 70,
                            sortable: true,
                            dataIndex: 'bmOrderStatus',
                        	renderer: function( v ){
                        		if ( v ){
                        			return v.ordrstValue;
                        		}
                        		return '';
                        	},
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: '\u041F\u0440\u043E\u0434\u0443\u043A\u0442\u0438',
                            width: 120,
                            sortable: true,
                            dataIndex: 'bmItems',
                        	renderer: function( v ){
                        		if ( v ){
                        			return v.length;
                        		}
                        		return '';
                        	},
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: '\u0426\u0435\u043D\u0430',
                            width: 50,
                            sortable: true,
                            dataIndex: 'bmItems',
                        	renderer: function( v ){
                        		if ( v ){
                        			var price = 0;
                        			for ( var i = 0; i < v.length; i++ )
                    				{
                        				price += v[ i ].itmPrice;
                    				}
                        			return Math.round( price * 10 ) / 10;
                        		}
                        		return '';
                        	},
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: '\u0421\u044A\u0437\u0434\u0430\u0434\u0435\u043D\u0430',
                            width: 150,
                            sortable: true,
                            dataIndex: 'ordDateCreated',
                            field: {
                            	xtype: 'datefield'
                            }
                        }, {
                            header: '\u041E\u0431\u043D\u043E\u0432\u0435\u043D\u0430',
                            width: 150,
                            sortable: true,
                            dataIndex: 'ordDateUpdated',
                            field: {
                            	xtype: 'datefield'
                            }
                        }],
                        tbar:[{
                            text: '\u041E\u0431\u043D\u043E\u0432\u0438',
                            iconCls: 'icon-refresh',
                            handler: function(){
                            	Ext.getCmp( 'orders-table' ).getStore().reload();
                            }
                        }]
                    }
                ]
            });
        }
        return win;
    }
});

