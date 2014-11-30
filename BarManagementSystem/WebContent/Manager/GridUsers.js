/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('User', {
	extend : 'Ext.data.Model',
	idProperty : 'usrId',
	fields : [ {
		name : 'usrId',
		type : 'int',
		useNull : true
	}, 'usrFirstname', 'usrLastname', 'usrPassword', 'usrRole', {
		name : 'usrStatus',
		convert : function(v) {
			return !!v;
		},
		serialize : function(v) {
			if (v) {
				return 1
			} else {
				return 0
			}
		}
	}, 'usrUsername' ],
	validations : [ {
		type : 'length',
		field : 'usrFirstname',
		min : 1
	}, {
		type : 'length',
		field : 'usrLastname',
		min : 1
	}, {
		type : 'length',
		field : 'usrPassword',
		min : 1
	}, {
		type : 'length',
		field : 'usrRole',
		min : 1
	}, {
		type : 'length',
		field : 'usrUsername',
		min : 1
	} ]
});

var rowEditing;
/*Ext.define('Override.form.field.Checkbox', {
    override : 'Ext.form.field.Checkbox',

    getValue: function () {
        return this.checked ? "Yes" : 0;
    }
});*/

Ext
		.define(
				'MyDesktop.GridUsers',
				{
					extend : 'Ext.ux.desktop.Module',

					requires : [ 'Ext.data.JsonStore', 'Ext.util.Format',
							'Ext.grid.Panel', 'Ext.grid.plugin.RowEditing' ],

					id : 'grid-users',

					init : function() {
						this.launcher = {
							text : '\u0423\u043F\u0440\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043D\u0430 \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0438',
							iconCls : 'icon-grid'
						};
					},

					// rowEditing: Ext.create('Ext.grid.plugin.RowEditing'),

					createWindow : function() {
						var desktop = this.app.getDesktop();
						var win = desktop.getWindow('grid-users');
						if (!win) {
							win = desktop
									.createWindow({
										id : 'grid-users',
										title : '\u0423\u043F\u0440\u0430\u0432\u043B\u0435\u043D\u0438\u0435 \u043D\u0430 \u043F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0438',
										width : 740,
										height : 516,
										iconCls : 'icon-grid',
										animCollapse : false,
										constrainHeader : true,
										layout : 'fit',
										items : [ {
											border : false,
											id : 'users-table',
											xtype : 'grid',
											store : new Ext.data.JsonStore(
													{
														autoLoad : true,
														autoSync : true,
														model : 'User',
														proxy : {
															type : 'rest',
															url : 'http://localhost:8080/BarManagementSystem/jaxrs/user',
															reader : {
																type : 'json',
																root : 'data'
															},
															writer : {
																type : 'json'
															}
														},
														listeners : {
															write : function(
																	store,
																	operation) {
																var record = operation.records[0], name = Ext.String
																		.capitalize(operation.action), verb;

																if (name == 'Destroy') {
																	verb = 'Destroyed';
																} else {
																	verb = name
																			+ 'd';
																}
															}
														}
													}),
											plugins : [ rowEditing = Ext
													.create('Ext.grid.plugin.RowEditing') ],
											columns : [
													{
														text : '\u0423\u043D\u0438\u043A\u0430\u043B\u0435\u043D \u043D\u043E\u043C\u0435\u0440',
														width : 109,
														sortable : true,
														dataIndex : 'usrId',
														renderer : function(v) {
															if (Ext.isEmpty(v)) {
																v = '&#160;';
															}
															return v;
														}
													},
													{
														text : '\u041F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0441\u043A\u043E \u0438\u043C\u0435',
														flex : 1,
														sortable : true,
														dataIndex : 'usrUsername',
														field : {
															xtype : 'textfield',
															allowBlank : false,
															blankText:'\u041F\u043E\u043B\u0435\u0442\u043E \"\u041F\u043E\u0442\u0440\u0435\u0431\u0438\u0442\u0435\u043B\u0441\u043A\u043E \u0438\u043C\u0435\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.'
														}
													},
													{
														text : '\u041F\u0430\u0440\u043E\u043B\u0430',
														flex : 1,
														sortable : true,
														dataIndex : 'usrPassword',
														field : {
															xtype : 'textfield',
															inputType : 'password',
															allowBlank : false,
															blankText: '\u041F\u043E\u043B\u0435\u0442\u043E \"\u041F\u0430\u0440\u043E\u043B\u0430\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.'
														}
													},
													{
														header : '\u0418\u043C\u0435',
														flex : 1,
														sortable : true,
														dataIndex : 'usrFirstname',
														field : {
															xtype : 'textfield',
															allowBlank : false,
															blankText:'\u041F\u043E\u043B\u0435\u0442\u043E \"\u0418\u043C\u0435\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.'
														}
													},
													{
														header : '\u0424\u0430\u043C\u0438\u043B\u0438\u044F',
														flex : 1,
														sortable : true,
														dataIndex : 'usrLastname',
														field : {
															xtype : 'textfield',
															allowBlank : false,
															blankText:'\u041F\u043E\u043B\u0435\u0442\u043E \"\u0424\u0430\u043C\u0438\u043B\u0438\u044F\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.'
														}
													},
													{
														text : '\u0420\u043E\u043B\u044F',
														width : 80,
														sortable : true,
														dataIndex : 'usrRole',
														field : {
															xtype : 'combobox',
															typeAhead : true,
															allowBlank : false,
															blankText: '\u041F\u043E\u043B\u0435\u0442\u043E \"\u0420\u043E\u043B\u044F\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.',
															triggerAction : 'all',
															selectOnTab : true,
															forceSelection: true,
															store : [
																	[
																			'Мениджър',
																			'Мениджър' ],
																	[ 'Барман',
																			'Барман' ],
																	[ 'Сервитьор',
																			'Сервитьор' ] ],
															lazyRender : true,
															listClass : 'x-combo-list-small'
														}
													},
													{
														header : '\u0410\u043A\u0442\u0438\u0432\u0435\u043D',
														width : 77,
														dataIndex : 'usrStatus',
														field : {
															xtype : 'checkbox'
														}
													} ],
											tbar : [
													{
														text : '\u0414\u043E\u0431\u0430\u0432\u0438',
														iconCls : 'icon-add',
														handler : function() {
															Ext
																	.getCmp(
																			'users-table')
																	.getStore()
																	.insert(
																			0,
																			new User());
															rowEditing
																	.startEdit(
																			0,
																			0);
														}
													},
													'-',
													{
														text : '\u0418\u0437\u0442\u0440\u0438\u0439',
														iconCls : 'icon-delete',
														handler : function() {
															var selection = Ext
																	.getCmp(
																			'users-table')
																	.getView()
																	.getSelectionModel()
																	.getSelection()[0];
															if (selection) {
																Ext
																		.getCmp(
																				'users-table')
																		.getStore()
																		.remove(
																				selection);
															}
														}
													},
													'-',
													{
														text : '\u041E\u0431\u043D\u043E\u0432\u0438',
														iconCls : 'icon-refresh',
														handler : function() {
															Ext
																	.getCmp(
																			'users-table')
																	.getStore()
																	.reload();
														}
													} ]
										} ]
									});
						}
						return win;
					}
				});
