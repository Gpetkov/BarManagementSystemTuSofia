/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('ItemType', {
	extend : 'Ext.data.Model',
	idProperty : 'id',
	fields : [ {
		name : 'id',
		type : 'int',
		useNull : true
	}, 'itmType' ],
	validations : [ {
		type : 'length',
		field : 'itmType',
		min : 1
	} ]
});

var rowEditing;

Ext
		.define(
				'MyDesktop.GridTypes',
				{
					extend : 'Ext.ux.desktop.Module',

					requires : [ 'Ext.data.JsonStore', 'Ext.util.Format',
							'Ext.grid.Panel', 'Ext.grid.plugin.RowEditing' ],

					id : 'grid-types',

					init : function() {
						this.launcher = {
							text : '\u0423\u043F\u0440\u0430\u043B\u0435\u043D\u0438\u0435 \u0442\u0438\u043F \u043D\u0430 \u043F\u0440\u043E\u0434\u0443\u043A\u0442\u0438\u0442\u0435',
							iconCls : 'icon-grid'
						};
					},

					createWindow : function() {
						var desktop = this.app.getDesktop();
						var win = desktop.getWindow('grid-types');
						if (!win) {
							win = desktop
									.createWindow({
										id : 'grid-types',
										title : '\u0423\u043F\u0440\u0430\u043B\u0435\u043D\u0438\u0435 \u0442\u0438\u043F \u043D\u0430 \u043F\u0440\u043E\u0434\u0443\u043A\u0442\u0438\u0442\u0435',
										width : 740,
										height : 480,
										iconCls : 'icon-grid',
										animCollapse : false,
										constrainHeader : true,
										layout : 'fit',
										items : [ {
											border : false,
											id : 'types-table',
											xtype : 'grid',

											store : new Ext.data.JsonStore(
													{
														autoLoad : true,
														autoSync : true,
														model : 'ItemType',
														proxy : {
															type : 'rest',
															url : 'http://localhost:8080/BarManagementSystem/jaxrs/itemtype',
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
														width : 101,
														sortable : true,
														dataIndex : 'id',
														renderer : function(v) {
															if (Ext.isEmpty(v)) {
																v = '&#160;';
															}
															return v;
														}
													},
													{
														text : '\u0418\u043C\u0435',
														flex : 1,
														sortable : true,
														dataIndex : 'itmType',
														field : {
															xtype : 'textfield',
															allowBlank : false,
															blankText : '\u041F\u043E\u043B\u0435\u0442\u043E \"\u0418\u043C\u0435\" \u0435 \u0437\u0430\u0434\u044A\u043B\u0436\u0438\u0442\u0435\u043B\u043D\u043E.'
														}
													} ],
											tbar : [
													{
														text : '\u0414\u043E\u0431\u0430\u0432\u0438',
														iconCls : 'icon-add',
														handler : function() {
															Ext
																	.getCmp(
																			'types-table')
																	.getStore()
																	.insert(
																			0,
																			new ItemType());
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
																			'types-table')
																	.getView()
																	.getSelectionModel()
																	.getSelection()[0];
															if (selection) {
																Ext
																		.getCmp(
																				'types-table')
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
																			'types-table')
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
