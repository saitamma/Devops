<%-- radius.jsp --%>
<div id="displayRadiusProvidersAndGroupsTables" style="padding-top:10px;margin: 5px;">
						<div class="commonclassForFormFields" style="padding: 0 0 10px 0;">
							<form id="adminRadiusGeneralSettingTableForm"
									data-dojo-id="adminRadiusGeneralSettingTableForm"
									data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<input id="adminRadiusGeneralSettingId" name="id" data-dojo-id="adminRadiusGeneralSettingId" type="hidden" value="0" dojoType="dijit.form.TextBox"></input>
								<table>
									<tr>
										<td>
											<div class="labelspace">
												<label style="float: left;padding-left: 15px;">Timeout:<em>*</em></label>
											</div>
										</td>
										<td>
											<div id="adminRadiusGeneralSettingTimeout"
												name="timeout" style="width: 135px;" value="30"
												data-dojo-id="adminRadiusGeneralSettingTimeout"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='required:"true", pattern: REG_EX_NUMBER_0_TO_60, trim:"true", maxlength:"16", promptMessage:"", invalidMessage: MSG_BET_0_TO_60'></div>
										</td>
										<td>
											<div class="labelspace">
												<label style="float: left;padding-left: 15px;">Retries:<em>*</em></label>
											</div>
										</td>
										<td>
											<div id="adminRadiusGeneralSettingRetries"
												name="retries" style="width: 135px;" value="1"
												data-dojo-id="adminRadiusGeneralSettingRetries"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='required:"true", pattern: REG_EX_NUMBER_0_TO_5, trim:"true", maxlength:"1", promptMessage:"", invalidMessage: MSG_BET_0_TO_5'></div>
										</td>
									</tr>									
								</table>
							</form>
							</div>
						<div class="floatleft">
							<div id="radiusProviderTableRowAddEdit" class="classForEditRowFormFields" style="display: none;width: 670px;">
								<div class="pageHeader"><h2>Add/Edit RADIUS Provider: <span id="nameOfLanVnicRow"></span></h2></div>
									<form id="adminAuthRadiusProviderAddEditTableRowForm"
										data-dojo-id="adminAuthRadiusProviderAddEditTableRowForm"
										data-dojo-type="xwt.widget.notification.Form" name="tableForm">
										<table style="width: 100%;">
										<tr>
										<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;" title="Hostname/FQDN (or IP Address)">Hostname:<em>*</em></label>
												</div>
											</td>
											<td height="40">
												<div id="radiusProviderHostnameEditTableRowName"
													data-dojo-id="radiusProviderHostnameEditTableRowName"
													name="hostname" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern: REG_EX_NTP, trim:"true", maxlength:"64", required:"true", invalidMessage: MSG_HOSTNAME_IP'></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Order:<em>*</em></label>
												</div>
											</td>
											<td height="40">
												<select id="radiusProviderEditTableRowOrder"
													data-dojo-id="radiusProviderEditTableRowOrder"
													name="radiusOrder"
													data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
													style="width: 98px; border: 1px solid #b4b4b4;">
														<option value="1">1</option>
														<option value="2">2</option>
														<option value="3">3</option>
														<option value="4">4</option>
														<option value="5">5</option>
														<option value="6">6</option>
														<option value="7">7</option>
														<option value="8">8</option>
														<option value="9">9</option>
														<option value="10">10</option>
														<option value="11">11</option>
														<option value="12">12</option>
														<option value="13">13</option>
														<option value="14">14</option>
														<option value="15">15</option>
														<option value="16">16</option>
													</select>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Authorization Port:<em>*</em></label>
												</div>
											</td>
											
											<td>
												<div id="radiusProviderEditTableRowPort"
													data-dojo-id="radiusProviderEditTableRowPort"
													name="authorizationPort" value="1812" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern: REG_EX_NUMBER_1_TO_65535, trim:"true", maxlength:"5", required:"true", invalidMessage: MSG_BET_1_TO_65535'></div>
											</td>
										</tr>
										<tr>
										
											<td>
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;">Timeout:<em>*</em></label>
												</div>
											</td>
											<td>
												<div id="radiusProviderEditTableRowTimeout"
													data-dojo-id="radiusProviderEditTableRowTimeout"
													name="timeout" value="5" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern: REG_EX_NUMBER_0_TO_60, trim:"true", maxlength:"2", required:"true", invalidMessage: MSG_BET_0_TO_60'></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Key:</label>
												</div>
											</td>
											<td>
													<div id="radiusProviderEditTableRowKey"
													data-dojo-id="radiusProviderEditTableRowKey"
													name="sslKey" style="width: 80px;" type="password"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"63" '></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Confirm Key:</label>
												</div>
											</td>
											<td>
												<div id="radiusProviderEditTableRowConfirmKey"
													data-dojo-id="radiusProviderEditTableRowConfirmKey"
													name="confirmKey" style="width: 80px;" type="password"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"63" '></div>
											</td>
											</tr>
											<tr>
												<td>
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;">Retries:<em>*</em></label>
												</div>
												<td>
												<div id="radiusProviderEditTableRowRetries"
													data-dojo-id="radiusProviderEditTableRowRetries"
													name="retries" value="1" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern: REG_EX_NUMBER_0_TO_5, trim:"true", maxlength:"1", required:"true", invalidMessage: MSG_BET_0_TO_5'></div>
											</td>
											
												</td>				
												<td height="40" style="padding-left: 5px; text-align: right;" colspan="4" >
												<button dojoType="xwt.widget.form.TextButton" 
													id="radiusProviderEditTableRowSaveDataBtn" baseClass="defaultButton"
													onClick="radiusProviderAddEditTableRowSaveData();">Save</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button dojoType="xwt.widget.form.TextButton" 
													id="radiusProviderEditTableRowCalcelBtn"
													onClick="radiusProviderEditTableRowCancelBtnFun();">Cancel</button>
												</td>
											</tr>
											
										</table>
									</form>
							</div>	
							
							<div id="radiusProviderTableStartDiv">
									<div dojotype="dijit.layout.ContentPane" region="left"
										style="width: 670px; overflow: hidden;" splitter="true">
										<span dojoType="dojo.data.ItemFileWriteStore"
											jsId="adminRadiusProviderDataStoreTab"
											data="adminAuthenticationRadiusProviderDataTable"></span>
										<div style="width: 670px !important;"
											id="adminRadiusProviderTableTollBar"
											dojoType="xwt.widget.table.ContextualToolbar"
											tableId="adminRadiusProviderTable" quickFilter="false">
											<div dojoType="xwt.widget.table.ContextualButtonGroup"
												showButtons="delete"></div>
											<div dojoType="dijit.form.Button" iconClass="fi-plus"
												title="Create Provider" showLabel="false"
												onclick="return createRadiusProvider();"></div>
												<label>RADIUS Providers</label>
										</div>
										<div id="adminRadiusProviderTable"
											data-dojo-id="adminRadiusProviderTable"
											dojoType="xwt.widget.table.Table"
											store="adminRadiusProviderDataStoreTab"
											structure="adminRadiusProviderColumns"
											style="width: 670px; height: 230px;" selectMultiple="true"
											selectAllOption="true" showIndex="false" selectModel="input"
											filterOnServer=false></div>
									</div>
							</div>
						</div>
						<div class="floatleft" style="width:50px;margin: 120px 12px 12px 12px;">
							<div dojoType="dijit.form.Button" iconClass="fi-arrow" title="Create new Provider Group. The order of the Providers will be the same as the insertion order." showLabel="false" onclick="return copyRadiusProviderToGroupTable();"></div>
						</div>
						<div class="floatleft" style="">
							<div dojotype="dijit.layout.ContentPane" region="left"
								style="width: 345px; overflow: hidden;" splitter="true">
								<span dojoType="dojo.data.ItemFileWriteStore"
									jsId="adminRadiusProviderGroupDataStoreTab"
									data="adminAuthenticationRadiusGroupDataTable"></span>
								<div style="width: 345px !important;"
									id="adminRadiusProviderGroupTableTollBar"
									dojoType="xwt.widget.table.ContextualToolbar"
									tableId="adminRadiusProviderGroupTable" quickFilter="false">
									<div dojoType="xwt.widget.table.ContextualButtonGroup"
										showButtons="delete"></div>
										<label>RADIUS Provider Groups</label>
								</div>
								<div id="adminRadiusProviderGroupTable"
									data-dojo-id="adminRadiusProviderGroupTable"
									dojoType="xwt.widget.table.Table"
									store="adminRadiusProviderGroupDataStoreTab"
									structure="adminRadiusProviderGroupColumns"
									style="width: 345px; height: 230px;" selectMultiple="true"
									selectAllOption="true" showIndex="false" selectModel="input"
									filterOnServer=false></div>
							</div>
							<div style="clear: both;"></div>
							<div style="float: right;padding-top: 5px;"><button dojoType="xwt.widget.form.TextButton" 
										id="adminAuthRadiusProviderAndGroupSaveDataBtn" baseClass="defaultButton"
										onClick="adminAuthRadiusProviderAndGroupSaveFunc();" title="Save RADIUS Providers &amp; Groups">Save  &amp; Return</button>
								</div>
						</div>
					</div>