/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Item', {
    extend: 'Ext.data.Model',
    idProperty: 'itmId',
    fields: [{
        name: 'itmId',
        type: 'int',
        useNull: true
    }, 'itmName', 'itmPrice', 'itmType'],
    validations: [{
        type: 'length',
        field: 'itmName',
        min: 1
    }, {
        type: 'length',
        field: 'itmPrice',
        min: 1
    }, {
        type: 'length',
        field: 'itmType',
        min: 1
    }]
});

var rowEditing;

Ext.define('MyDesktop.GridItems', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.JsonStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.plugin.RowEditing',
        'Ext.ux.window.Notification'
    ],

    id:'grid-items',

    init : function(){
        this.launcher = {
            text: 'Manage Items',
            iconCls:'icon-grid'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-items');
        if(!win){
            win = desktop.createWindow({
                id: 'grid-items',
                title:'Manage Items',
                width:740,
                height:480,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {
                        border: false,
                        id: 'items-table',
                        xtype: 'grid',
                        store: new Ext.data.JsonStore({
                        	autoLoad: true,
                            autoSync: true,
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
                            dataIndex: 'itmId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            text: 'Name',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'itmName',
                            field: {
                                xtype: 'textfield'
                            }
                        }, {
                            header: 'Price',
                            width: 80,
                            sortable: true,
                            dataIndex: 'itmPrice',
                            field: {
                            	xtype: 'numberfield',
                                allowBlank: false,
                                minValue: 0,
                                maxValue: 100000
                            }
                        }, {
                            text: 'Type',
                            width: 80,
                            sortable: true,
                            dataIndex: 'itmType',
                            field: {
                            	xtype: 'combobox',
                                typeAhead: true,
                                triggerAction: 'all',
                                selectOnTab: true,
                                store: [
                                    ['Drink','Drink'],
                                    ['Food','Food'],
                                    ['Survice','Survice']
                                ],
                                lazyRender: true,
                                listClass: 'x-combo-list-small'
                            }
                        }],
                        tbar:[{
                            text: 'Add',
                            iconCls: 'icon-add',
                            handler: function(){
                            	Ext.getCmp( 'items-table' ).getStore().insert(0, new Item());
                                rowEditing.startEdit(0, 0);
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
                        }, '-', {
                            text: 'Refresh',
                            iconCls: 'icon-refresh',
                            handler: function(){
                            	Ext.getCmp( 'items-table' ).getStore().reload();
                            }
                        }]
                    }
                ]
            });
        }
        return win;
    }
});

