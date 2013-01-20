/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('User', {
    extend: 'Ext.data.Model',
    idProperty: 'usrId',
    fields: [{
        name: 'usrId',
        type: 'int',
        useNull: true
    }, 'usrFirstname', 'usrLastname', 'usrPassword', 'usrRole', 
    {
    	name: 'usrStatus',
    	convert: function( v ){
    		return !!v;
    	},
    	serialize: function( v ){
    		if ( v ) { return 1 }
    		else { return 0 }
    	}
    }, 'usrUsername'],
    validations: [{
        type: 'length',
        field: 'usrFirstname',
        min: 1
    }, {
        type: 'length',
        field: 'usrLastname',
        min: 1
    }, {
        type: 'length',
        field: 'usrPassword',
        min: 1
    }, {
        type: 'length',
        field: 'usrRole',
        min: 1
    }, {
        type: 'length',
        field: 'usrUsername',
        min: 1
    }]
});

var rowEditing;

Ext.define('MyDesktop.GridUsers', {
    extend: 'Ext.ux.desktop.Module',

    requires: [
        'Ext.data.JsonStore',
        'Ext.util.Format',
        'Ext.grid.Panel',
        'Ext.grid.plugin.RowEditing'
    ],

    id:'grid-users',

    init : function(){
        this.launcher = {
            text: 'Manage Users',
            iconCls:'icon-grid'
        };
    },
    
    //rowEditing: Ext.create('Ext.grid.plugin.RowEditing'),

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-users');
        if(!win){
            win = desktop.createWindow({
                id: 'grid-users',
                title:'Manage Users',
                width:740,
                height:480,
                iconCls: 'icon-grid',
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: [
                    {
                        border: false,
                        id: 'users-table',
                        xtype: 'grid',
                        store: new Ext.data.JsonStore({
                        	autoLoad: true,
                            autoSync: true,
                            model: 'User',
                            proxy: {
                                type: 'rest',
                                url: 'http://localhost:8080/BarManagementSystem/jaxrs/user',
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
                            dataIndex: 'usrId',
                            renderer: function(v){
                                if (Ext.isEmpty(v)) {
                                    v = '&#160;';
                                }
                                return v;
                            }
                        }, {
                            text: 'Username',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'usrUsername',
                            field: {
                                xtype: 'textfield'
                            }
                        }, {
                            text: 'Password',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'usrPassword',
                            field: {
                                xtype: 'textfield',
                                inputType:'password'
                            }
                        }, {
                            header: 'First name',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'usrFirstname',
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            header: 'Last name',
                            flex: 1,
                            sortable: true,
                            dataIndex: 'usrLastname',
                            field: {
                            	xtype: 'textfield'
                            }
                        }, {
                            text: 'Role',
                            width: 80,
                            sortable: true,
                            dataIndex: 'usrRole',
                            field: {
                            	xtype: 'combobox',
                                typeAhead: true,
                                triggerAction: 'all',
                                selectOnTab: true,
                                store: [
                                    ['manager','manager'],
                                    ['barman','barman'],
                                    ['waiter','waiter']
                                ],
                                lazyRender: true,
                                listClass: 'x-combo-list-small'
                            }
                        }, {
                        	header: 'Active?',
                            width: 55,
                            dataIndex: 'usrStatus',
                            field: {
                            	xtype: 'checkboxfield'
                            }
                        }],
                        tbar:[{
                            text: 'Add',
                            iconCls: 'icon-add',
                            handler: function(){
                            	Ext.getCmp( 'users-table' ).getStore().insert(0, new User());
                                rowEditing.startEdit(0, 0);
                            }
                        }, '-', {
                            text: 'Delete',
                            iconCls: 'icon-delete',
                            handler: function(){
                                var selection = Ext.getCmp( 'users-table' ).getView().getSelectionModel().getSelection()[0];
                                if (selection) {
                                	Ext.getCmp( 'users-table' ).getStore().remove(selection);
                                }
                            }
                        }, '-', {
                            text: 'Refresh',
                            iconCls: 'icon-refresh',
                            handler: function(){
                            	Ext.getCmp( 'users-table' ).getStore().reload();
                            }
                        }]
                    }
                ]
            });
        }
        return win;
    }
});

