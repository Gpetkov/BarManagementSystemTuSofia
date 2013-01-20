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
            title: 'Login',
            frame:true,
            bodyPadding: 13,
            height: null,
            
            defaultType: 'textfield',
            defaults: { anchor: '100%' },
            
            items: [
                { id: 'username-fancy', allowBlank:false, fieldLabel: 'Username', name: 'user', emptyText: 'username' },
                { id: 'password-fancy', allowBlank:false, fieldLabel: 'Password', name: 'pass', emptyText: 'password', inputType: 'password' }
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
                	text:'Login',
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