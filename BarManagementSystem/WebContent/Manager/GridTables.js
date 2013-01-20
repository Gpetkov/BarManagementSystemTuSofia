/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('Table', {
    extend: 'Ext.data.Model',
    idProperty: 'tblId',
    fields: [{
        name: 'tblId',
        type: 'int',
        useNull: true
    }, 'tblNumber'],
    validations: [{
    	type: 'length',
        field: 'tblNumber',
        min: 1
    }]
});

var rowEditing;

Ext.define('MyDesktop.GridTables', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.JsonStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.plugin.RowEditing'
    ],

    id:'grid-tables',

    init : function(){
        this.launcher = {
            text: 'Manage Tables',
            iconCls:'icon-grid'
        };
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-tables');
        if(!win){
            win = desktop.createWindow({
                id: 'grid-tables',
                title:'Manage Tables',
                width:740,
                height:480,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {
                        border: false,
                        id: 'tables-table',
                        xtype: 'grid',
                        store: new Ext.data.JsonStore({
                        	autoLoad: true,
                            autoSync: true,
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
                            dataIndex: 'tblId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            text: 'Number',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'tblNumber',
                            field: {
                                xtype: 'numberfield'
                            }
                        }],
                        tbar:[{
                            text: 'Add',
                            iconCls: 'icon-add',
                            handler: function(){
                            	Ext.getCmp( 'tables-table' ).getStore().insert(0, new Table());
                                rowEditing.startEdit(0, 0);
                            }
                        }, '-', {
                            text: 'Delete',
                            iconCls: 'icon-delete',
                            handler: function(){
                                var selection = Ext.getCmp( 'tables-table' ).getView().getSelectionModel().getSelection()[0];
                                if (selection) {
                                	Ext.getCmp( 'tables-table' ).getStore().remove(selection);
                                }
                            }
                        }, '-', {
                            text: 'Refresh',
                            iconCls: 'icon-refresh',
                            handler: function(){
                            	Ext.getCmp( 'tables-table' ).getStore().reload();
                            }
                        }]
                    }
                ]
            });
        }
        return win;
    }
});

