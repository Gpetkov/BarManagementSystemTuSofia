Ext.require(['Ext.data.*', 'Ext.grid.*']);

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

Ext.onReady(function(){

    var store = Ext.create('Ext.data.Store', {
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
                Ext.example.msg(name, Ext.String.format("{0} user: {1}", verb, record.getId()));
                
            }
        }
    });
    
    var store2 = Ext.create('Ext.data.Store', {
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
                Ext.example.msg(name, Ext.String.format("{0} user: {1}", verb, record.getId()));
                
            },
            load: function(store){
            	store.filter('ordId',-1);
            }
        }
    });
    
    var storeItems = Ext.create('Ext.data.Store', {
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
    
    var storeTables = Ext.create('Ext.data.Store', {
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
    });
    
    var rowEditing = Ext.create('Ext.grid.plugin.RowEditing');
    var rowEditing2 = Ext.create('Ext.grid.plugin.RowEditing');
    
    var grid = Ext.create('Ext.grid.Panel', {
        renderTo: document.body,
        plugins: [rowEditing],
        width: 800,
        height: 300,
        frame: true,
        title: 'Orders',
        store: store,
        iconCls: 'icon-user',
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
            width: 80,
            sortable: true,
            dataIndex: 'tableId',
            field: {
            	xtype: 'combobox',
            	store: storeTables,
            	displayField: 'tblNumber',
                valueField: 'tblId',
            }
        }],
        dockedItems: [{
            xtype: 'toolbar',
            items: [{
                text: 'Add',
                iconCls: 'icon-add',
                handler: function(){
                    // empty record
                    store.insert(0, new Order({'orderStatusId':150}));
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
    
    grid.getSelectionModel().on('selectionchange', function(sm, selectedRecord) {
        if (selectedRecord.length) {
        	var st = grid2.getStore();
        	st.clearFilter();
            st.filter( 'ordrId', sm.selected.items[0].data.ordId );
        }
    });
    
    var grid2 = Ext.create('Ext.grid.Panel', {
        renderTo: document.body,
        plugins: [rowEditing2],
        width: 800,
        height: 300,
        frame: true,
        title: 'Items',
        store: store2,
        iconCls: 'icon-user',
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
            fit: 1,
            sortable: true,
            dataIndex: 'itmId',
            renderer: function(value){
            	var rec = storeItems.findRecord( 'itmId', value );
            	if ( rec )
            	{
            		return storeItems.findRecord( 'itmId', value ).data.itmName;
            	}
            	return value;
            },
            field: {
            	xtype: 'combobox',
            	store: storeItems,
            	displayField: 'itmName',
                valueField: 'itmId',
            }
        }],
        dockedItems: [{
            xtype: 'toolbar',
            items: [{
                text: 'Add',
                iconCls: 'icon-add',
                handler: function(){
                	var selection = grid.getView().getSelectionModel().getSelection()[0];
                	if ( selection )
                	{
	                    grid2.getStore().insert(0, new OrderItem({ordrId:selection.data.ordId}));
	                    rowEditing2.startEdit(0, 0);
                	}
                }
            }, '-', {
                text: 'Delete',
                iconCls: 'icon-delete',
                handler: function(){
                    var selection = grid2.getView().getSelectionModel().getSelection()[0];
                    if (selection) {
                    	grid2.getStore().remove(selection);
                    }
                }
            }]
        }]
    });
});