Ext.define('KitchenSink.view.examples.Example', {
    extend: 'Ext.Container',
    requires: [
        'Ext.layout.container.VBox'
    ],
    baseCls: 'center-bitch',
    
    layout: {
        type: 'absolute',
        region: 'center',
        /*align: 'center',
        pack: 'center'*/
    },
    
    defaults: {
        width: 400,
        height: 300
    }
});

function onSubmit(){
	document.getElementById( 'username-ugly' ).value = document.getElementById( 'username-fancy-inputEl' ).value;
	document.getElementById( 'password-ugly' ).value = document.getElementById( 'password-fancy-inputEl' ).value;
	document.forms[ 'login-form' ].submit();
}

Ext.define('KitchenSink.view.examples.forms.Login', {
    extend: 'KitchenSink.view.examples.Example',
    requires: [
        'Ext.form.Panel',
        'Ext.form.field.Checkbox',
        'Ext.form.field.Text'
    ],

    items: [
        {
            xtype: 'form',
            id: 'login-panel',
            title: '\u0412\u0445\u043E\u0434',
            frame:true,
            bodyPadding: 13,
            height: null,
            
            defaultType: 'textfield',
            defaults: { anchor: '100%' },
            
            items: [
                { id: 'username-fancy', allowBlank:false, fieldLabel: '\u041F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0441\u043A\u043E \u0438\u043C\u0435', name: 'user', emptyText: '\u041F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0441\u043A\u043E \u0438\u043C\u0435' },
                { id: 'password-fancy', allowBlank:false, fieldLabel: '\u041F\u0430\u0440\u043E\u043B\u0430', name: 'pass', emptyText: '\u041F\u0430\u0440\u043E\u043B\u0430', inputType: 'password' }
            ],
            listeners: {
                afterRender: function(thisForm, options){
                    this.keyNav = Ext.create('Ext.util.KeyNav', this.el, {
                        enter: onSubmit,
                        scope: this
                    });
                }
            },
            buttons: [
                {
                	text:'\u0412\u043B\u0435\u0437',
                	listeners: {
                		click: onSubmit
                	}
                }
            ]
        }
    ]
});

Ext.onReady(function(){
	var loginForm = Ext.create('KitchenSink.view.examples.forms.Login', {
		renderTo: document.body
	} );
});