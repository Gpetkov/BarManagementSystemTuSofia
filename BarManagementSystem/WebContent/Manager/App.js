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
        'MyDesktop.GridUsers',
        'MyDesktop.GridItems',
        'MyDesktop.GridTables',

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
            new MyDesktop.GridOrders(),
            new MyDesktop.GridUsers(),
            new MyDesktop.GridItems(),
            new MyDesktop.GridTables()
        ];
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            //cls: 'ux-desktop-black',

            contextMenuItems: [
                { text: 'Change Settings', handler: me.onSettings, scope: me }
            ],

            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: [
                    { name: 'View Orders', iconCls: 'grid-shortcut', module: 'grid-orders' },
                    { name: 'Manage Users', iconCls: 'accordion-shortcut', module: 'grid-users' },
                    { name: 'Manage Items', iconCls: 'notepad-shortcut', module: 'grid-items' },
                    { name: 'Manage Tables', iconCls: 'table-shortcut', module: 'grid-tables' }
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
            title: 'Don Griffin',
            iconCls: 'user',
            height: 300,
            toolConfig: {
                width: 100,
                items: [
                    {
                        text:'Settings',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    '-',
                    {
                        text:'Logout',
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
                { name: 'View Orders', iconCls: 'icon-grid', module: 'grid-orders' },
                { name: 'Manager Users', iconCls: 'icon-grid', module: 'grid-users' },
                { name: 'Manager Items', iconCls: 'icon-grid', module: 'grid-items' },
                { name: 'Manager Tables', iconCls: 'icon-grid', module: 'grid-tables' }
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('Logout', 'Are you sure you want to logout?',function( answer ){
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
