<%-- rolesAndLocales.jsp --%>
<div id="displayLdapRolesAndLocalesTables" style="padding-bottom:10px;margin: 5px;">
						<div class="floatleft">
							
							<div class="commonclassForFormFields widtInPercent" style="padding-bottom: 10px;">
								<form id="adminLdapRolesTableForm"
									data-dojo-id="adminLdapRolesTableForm"
									data-dojo-type="xwt.widget.notification.Form" name="tableForm">
									<table>
										<tr>
											<td>
												<div class="labelspace">
													<label style="float: left;padding-left: 0px;">Name:<em>*</em></label>
												</div>
											</td>
											<td>
												<div id="adminLdapRolesName"
													name="adminLdapRolesName" style="width: 90px;"
													data-dojo-id="adminLdapRolesName"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
											</td>
											<td>
												<div class="labelspace">
													<label style="float: left;">Privileges:</label>
												</div>
											</td>
											<td>
											<select id="adminPrivileges" data-dojo-id="adminPrivileges" name="privileges" data-dojo-type="dojox/form/CheckedMultiSelect"
												data-dojo-props="dropDown:true,multiple:true, required:false"></select>
											</td>
											<td style="padding-left: 10px;">
												<button data-dojo-type="dijit/form/Button"
													data-dojo-id="adminLdapRolesGenerateDataBtn"
													onClick="adminLdapRolesGenerateData();" type="button">Add</button>
											</td>
										</tr>
									</table>
								</form>
							</div>
							
							<div id="RolesTableStartDiv">
									<div dojotype="dijit.layout.ContentPane" region="left"
										style="width: 520px; overflow: hidden;" splitter="true">
										<span dojoType="dojo.data.ItemFileWriteStore"
											jsId="adminLdapRolesDataStoreTab"
											data="adminLdapRolesDataTable"></span>
										<div style="width: 520px !important;"
											id="adminLdapRolesTableTollBar"
											dojoType="xwt.widget.table.ContextualToolbar"
											tableId="adminLdapRolesTable" quickFilter="false">
											<div dojoType="xwt.widget.table.ContextualButtonGroup"
												showButtons="delete"></div>
												<label>LDAP Roles</label>
										</div>
										<div id="adminLdapRolesTable"
											data-dojo-id="adminLdapRolesTable"
											dojoType="xwt.widget.table.Table"
											store="adminLdapRolesDataStoreTab"
											structure="adminLdapRolesColumns"
											style="width: 520px; height: 220px;" selectMultiple="true"
											selectAllOption="true" showIndex="false" selectModel="input"
											filterOnServer=false></div>
									</div>
							</div>
						</div>
						
						<div class="floatleft" style="float: right;">
							<div class="commonclassForFormFields widtInPercent" style="padding-bottom: 10px;">
								<form id="adminLdapLocalesTableForm"
									data-dojo-id="adminLdapLocalesTableForm"
									data-dojo-type="xwt.widget.notification.Form" name="tableForm">
									<table>
											<tr>
												<td>
													<div class="labelspace">
														<label style="float: left;padding-left: 0px;">Name:<em>*</em></label>
													</div>
												</td>
												<td>
													<div id="adminLdapLocalesName"
														name="adminLdapLocalesName" style="width: 90px;"
														data-dojo-id="adminLdapLocalesName"
														data-dojo-type="xwt.widget.notification.ValidationTextBox"
														data-dojo-props='pattern:REG_EX_NAME, trim:"true", maxlength:"16", promptMessage:"", invalidMessage:MSG_NAME'></div>
												</td>
												<td>
													<div class="labelspace">
														<label style="float: left;">Organizations:</label>
													</div>
												</td>
												<td>
												<select id="adminLdapLocalesOrganization" data-dojo-id="adminLdapLocalesOrganization" name="organizations" data-dojo-type="dojox/form/CheckedMultiSelect"
													data-dojo-props="dropDown:true,multiple:true, required:false"></select>
												</td>
												<td style="padding-left: 10px;">
													<button data-dojo-type="dijit/form/Button"
														data-dojo-id="adminLdapLocalesGenerateDataBtn"
														onClick="adminLdapLocalesGenerateData();" type="button">Add</button>
												</td>
											</tr>
										</table>
									</form>
								</div>
							<div dojotype="dijit.layout.ContentPane" region="left"
								style="width: 553px; overflow: hidden;" splitter="true">
								<span dojoType="dojo.data.ItemFileWriteStore"
									jsId="adminLdapLocalesDataStoreTab"
									data="adminLdapLocalesDataTable"></span>
								<div style="width: 553px !important;"
									id="adminLdapLocalesTableTollBar"
									dojoType="xwt.widget.table.ContextualToolbar"
									tableId="adminLdapLocalesTable" quickFilter="false">
									<div dojoType="xwt.widget.table.ContextualButtonGroup"
										showButtons="delete"></div>
										<label>LDAP Locales</label>
								</div>
								<div id="adminLdapLocalesTable"
									data-dojo-id="adminLdapLocalesTable"
									dojoType="xwt.widget.table.Table"
									store="adminLdapLocalesDataStoreTab"
									structure="adminLdapLocalesColumns"
									style="width: 553px; height: 220px;" selectMultiple="true"
									selectAllOption="true" showIndex="false" selectModel="input"
									filterOnServer=false></div>
							</div>
							<div style="clear: both;"></div>
							<div style="float: right;padding-top: 5px;"><button dojoType="xwt.widget.form.TextButton" 
										id="adminLdapRolesLocalesSaveDataBtn" baseClass="defaultButton"
										onClick="adminLdapRolesLocalesSaveFunc();" title="Save LDAP Roles &amp; Locales" >Save &amp; Return</button>
								</div>
						</div>
					</div>