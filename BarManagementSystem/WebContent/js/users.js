Ext.require(['Ext.data.*', 'Ext.grid.*']);

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

Ext.onReady(function(){

    var store = Ext.create('Ext.data.Store', {
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
                Ext.example.msg(name, Ext.String.format("{0} user: {1}", verb, record.getId()));
                
            }
        }
    });
    
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing');
    
    var grid = Ext.create('Ext.grid.Panel', {
        renderTo: document.body,
        plugins: [rowEditing],
        width: 800,
        height: 600,
        frame: true,
        title: 'Users',
        store: store,
        iconCls: 'icon-user',
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
        dockedItems: [{
            xtype: 'toolbar',
            items: [{
                text: 'Add',
                iconCls: 'icon-add',
                handler: function(){
                    // empty record
                    store.insert(0, new User());
                    rowEditing.startEdit(0, 0);
                }
            }, '-', {
                text: 'Delete',
                iconCls: 'icon-delete',
                handler: function(){
                    var selection = grid.getView().getSelectionModel().getSelection()[0];
                    if (selection) {
                        store.remove(selection);
                    }
                }
            }]
        }]
    });
});