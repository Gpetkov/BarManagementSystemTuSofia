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
                            width: 40,
                            sortable: true,
                            dataIndex: 'tableId',
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: 'Waiter',
                            flex: 1,
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
                            header: 'Barman',
                            flex: 1,
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
                            header: 'Status',
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
                            header: 'Items',
                            width: 40,
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
                            header: 'Price',
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
                            header: 'Created',
                            width: 150,
                            sortable: true,
                            dataIndex: 'ordDateCreated',
                            field: {
                            	xtype: 'datefield'
                            }
                        }, {
                            header: 'Updated',
                            width: 150,
                            sortable: true,
                            dataIndex: 'ordDateUpdated',
                            field: {
                            	xtype: 'datefield'
                            }
                        }],
                        tbar:[{
                            text: 'Refresh',
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

