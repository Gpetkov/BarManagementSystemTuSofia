/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',

    requires: [
        'Ext.window.MessageBox',

        'Ext.ux.desktop.ShortcutModel',

        'MyDesktop.GridOrders',

//        'MyDesktop.Blockalanche',
        'MyDesktop.Settings'
    ],

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
        return [
            new MyDesktop.GridOrders()
        ];
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            //cls: 'ux-desktop-black',

            contextMenuItems: [
                { text: '\u041F\u0440\u043E\u043C\u0435\u043D\u0438 \u043D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438\u0442\u0435', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: [
                    { name: '\u041F\u0440\u0435\u0433\u043B\u0435\u0434 \u043D\u0430 \u043F\u043E\u0440\u044A\u0447\u043A\u0438\u0442\u0435', iconCls: 'grid-shortcut', module: 'grid-orders' },
                ]
            }),

            wallpaper: 'wallpapers/Blue-Sencha.jpg',
            wallpaperStretch: false
        });
    },

    // config for the start menu
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            title: '\u0421\u0442\u0430\u0440\u0442 \u041C\u0435\u043D\u044E',
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: [
                    {
                        text:'\u041D\u0430\u0441\u0442\u0440\u043E\u0439\u043A\u0438',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    '-',
                    {
                        text:'\u0418\u0437\u043B\u0435\u0437',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },

    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            quickStart: [
                { name: '\u041F\u0440\u0435\u0433\u043B\u0435\u0434 \u043D\u0430 \u043F\u043E\u0440\u044A\u0447\u043A\u0438\u0442\u0435', iconCls: 'icon-grid', module: 'grid-orders' },
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('\u0418\u0437\u043B\u0435\u0437', '\u0421\u0438\u0433\u0443\u0440\u043D\u0438 \u043B\u0438 \u0441\u0442\u0435, \u0447\u0435 \u0438\u0441\u043A\u0430\u0442\u0435 \u0434\u0430 \u0438\u0437\u043B\u0435\u0437\u0435\u0442\u0435?',function( answer ){
        	if ( 'yes' == answer )
    		{
        		var arr = location.href.split( '/' );
        		arr.pop();
        		arr.pop();
        		arr.push( 'logout.jsp' );
        		location.href = arr.join( '/' );
    		}
        });
    },

    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
