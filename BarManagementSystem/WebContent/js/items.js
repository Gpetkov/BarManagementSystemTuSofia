Ext.require(['Ext.data.*', 'Ext.grid.*']);

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

Ext.onReady(function(){

    var store = Ext.create('Ext.data.Store', {
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
        title: 'Items',
        store: store,
        iconCls: 'icon-user',
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
        dockedItems: [{
            xtype: 'toolbar',
            items: [{
                text: 'Add',
                iconCls: 'icon-add',
                handler: function(){
                    // empty record
                    store.insert(0, new Item());
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