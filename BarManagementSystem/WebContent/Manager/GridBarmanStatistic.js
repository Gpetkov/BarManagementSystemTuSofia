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
	}]
});


var rowEditing;

Ext
		.define(
				'MyDesktop.GridBarmanStatistic',
				{
					extend : 'Ext.ux.desktop.Module',

					requires : [ 'Ext.data.JsonStore', 'Ext.util.Format',
							'Ext.grid.Panel', 'Ext.grid.plugin.RowEditing',
							'Ext.chart.*' ],

					id : 'grid-barmanStatistic',

					init : function() {
						this.launcher = {
							text : '\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430 \u0437\u0430 \u0431\u0430\u0440\u043C\u0430\u043D\u0438',
							iconCls : 'icon-mychart'
						};
					},

					createWindow : function() {
						var desktop = this.app.getDesktop();
						var win = desktop.getWindow('grid-waiterStatistic');
						if (!win) {
							win = desktop
									.createWindow({
										id : 'grid-barmanStatistic',
										title : '\u0421\u0442\u0430\u0442\u0438\u0441\u0442\u0438\u043A\u0430 \u0437\u0430 \u0431\u0430\u0440\u043C\u0430\u043D\u0438 \u043F\u043E \u0431\u0440\u043E\u0439 \u043F\u0440\u0438\u0435\u0442\u0438 \u043F\u043E\u0440\u044A\u0447\u043A\u0438',
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
																		'mybarmanchart')
																.getStore()
																.reload();
													}
												} ],
										items : [ {
											xtype : 'chart',
											shadow: true,
											animate : true,
											id: 'mybarmanchart',
											legend: {
										        position: 'right',
										        boxFill: '#000',
										        labelColor: '#fff'
										    },
											store : new Ext.data.JsonStore({
												autoLoad: true,
											    autoSync: true,
											    model: 'UserStatistic',
											    proxy: {
											        type: 'rest',
											        url: 'http://localhost:8080/BarManagementSystem/jaxrs/statistic/barman',
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
											insetPadding: 60,
								            theme: 'Base:gradients',
											series : [ {
												type : 'pie',
												angleField : 'data',
												showInLegend : true,
												tips: {
									                  trackMouse: true,
									                  width: 140,
									                  height: 28,
									                  renderer: function(storeItem, item) {
									                    // calculate percentage.
									                    var total = 0;
									                    Ext.getCmp( 'mybarmanchart' ).getStore().each(function(rec) {
									                        total += rec.get('data');
									                    });
									                    this.setTitle(storeItem.get('name') + ': ' + Math.round(storeItem.get('data') / total * 100) + '%');
									                  }
									                },
									                highlight: {
									                  segment: {
									                    margin: 20
									                  }
									                },
									                label: {
									                    field: 'name',
									                    display: 'rotate',
									                    contrast: true,
									                    font: '18px Arial'
									                }
											} ]
										
										} ]
									});
						}
						return win;
					}
				});
