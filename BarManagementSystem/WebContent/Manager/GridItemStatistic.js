Ext.define('UserStatistic', {
	extend : 'Ext.data.Model',
	idProperty : 'name',
	fields : [ {
		name : 'data',
		type : 'int',
		useNull : true
	}, {
		name : 'name',
		type : 'string',
		useNull : true
	} ],
	validations : [ {
		type : 'length',
		field : 'data',
		min : 1
	}, {
		type : 'length',
		field : 'name',
		min : 1
	} ]
});

Ext.define('Table', {
	extend : 'Ext.data.Model',
	idProperty : 'tblId',
	fields : [ {
		name : 'tblId',
		type : 'int',
		useNull : true
	}, 'tblNumber' ],
	validations : [ {
		type : 'length',
		field : 'tblNumber',
		min : 1
	} ]
});

var currentstore = Ext.create('Ext.data.JsonStore', {
	fields : [ 'name', 'data' ],
	data : [ {
		'name' : 'metric one',
		'data' : 10
	}, {
		'name' : 'metric two',
		'data' : 7
	}, {
		'name' : 'metric three',
		'data' : 5
	}, {
		'name' : 'metric four',
		'data' : 2
	}, {
		'name' : 'metric five',
		'data' : 27
	} ]
});

var rowEditing;

Ext
		.define(
				'MyDesktop.GridItemStatistic',
				{
					extend : 'Ext.ux.desktop.Module',

					requires : [ 'Ext.data.JsonStore', 'Ext.util.Format',
							'Ext.grid.Panel', 'Ext.grid.plugin.RowEditing',
							'Ext.chart.*' ],

					id : 'grid-itemStatistic',

					init : function() {
						this.launcher = {
							text : '\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430 \u0437\u0430 \u043F\u0440\u043E\u0434\u0443\u043A\u0442\u0438',
							iconCls : 'icon-mychart'
						};
					},

					createWindow : function() {
						var desktop = this.app.getDesktop();
						var win = desktop.getWindow('grid-itemStatistic');
						if (!win) {
							win = desktop
									.createWindow({
										id : 'grid-itemStatistic',
										title : '\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430 \u0437\u0430 \u043F\u0440\u043E\u0434\u0443\u043A\u0442\u0438 \u0441\u043F\u0440\u044F\u043C\u043E \u0431\u0440\u043E\u0439 \u043F\u0440\u043E\u0434\u0430\u0436\u0431\u0438',
										width : 840,
										height : 580,
										iconCls : 'icon-mychart',
										animCollapse : false,
										constrainHeader : true,
										layout : 'fit',
										tbar : [
												{
													text : '\u041E\u0431\u043D\u043E\u0432\u0438',
													iconCls : 'icon-refresh',
													handler : function() {
														Ext
																.getCmp(
																		'myitemchart')
																.getStore()
																.reload();
													}
												} ],
										items : [ {
											xtype : 'chart',
											width : '100%',
											id : 'myitemchart',
											height : 410,
											style : 'background: #fff',
											padding : '10 0 0 0',
											insetPadding : 40,
											animate : true,
											shadow : false,
											store : new Ext.data.JsonStore({
												autoLoad: true,
											    autoSync: true,
											    model: 'UserStatistic',
											    proxy: {
											        type: 'rest',
											        url: 'http://localhost:8080/BarManagementSystem/jaxrs/statistic/item',
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
											axes : [ {
												type : 'Numeric',
												position : 'left',
												fields : [ 'data' ],
												label : {
													renderer : function(v) {
														return v;
													}
												},
												grid : true,
												minimum : 0
											}, {
												type : 'Category',
												position : 'bottom',
												fields : [ 'name' ],
												grid : true,
												label : {
													rotate : {
														degrees : -45
													}
												}
											} ],
											series : [ {
												type : 'column',
												axis : 'left',
												xField : 'name',
												yField : 'data',
												style : {
													opacity : 0.80
												},
												highlight : {
													fill : '#000',
													'stroke-width' : 20,
													stroke : '#fff'
												},
												tips : {
													trackMouse : true,
													style : 'background: #FFF',
													height : 20,
													width:210,
													renderer : function(
															storeItem, item) {
														this
																.setTitle(storeItem
																		.get('name')
																		+ ': '
																		+ storeItem
																				.get('data'));
													}
												}
											} ]
										} ]
									});
						}
						return win;
					}
				});
