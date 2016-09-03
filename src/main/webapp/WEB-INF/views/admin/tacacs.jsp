<%-- tacacs.jsp --%>
<div id="displayTacacsProvidersAndGroupsTables" style="padding-top:10px;margin: 5px;">
						<div class="commonclassForFormFields" style="padding: 0 0 10px 0;">
							<form id="adminTacacsGeneralSettingTableForm"
									data-dojo-id="adminTacacsGeneralSettingTableForm"
									data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<input id="adminTacacsGeneralSettingId" name="id" data-dojo-id="adminTacacsGeneralSettingId" type="hidden" value="0" dojoType="dijit.form.TextBox"></input>
								<table>
									<tr>
										<td>
											<div class="labelspace">
												<label style="float: left;padding-left: 15px;">Timeout:<em>*</em></label>
											</div>
										</td>
										<td>
											<div id="adminTacacsGeneralSettingTimeout"
												name="timeout" style="width: 135px;" value="30"
												data-dojo-id="adminTacacsGeneralSettingTimeout"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='required:"true", pattern: REG_EX_NUMBER_0_TO_60, trim:"true", maxlength:"2", promptMessage:"", invalidMessage: MSG_BET_0_TO_60'></div>
										</td>
									</tr>									
								</table>
							</form>
							</div>
						<div class="floatleft">
							<div id="tacacsProviderTableRowAddEdit" class="classForEditRowFormFields" style="display: none;width: 670px;">
								<div class="pageHeader"><h2>Add/Edit TACACS Provider: <span id="nameOfLanVnicRow"></span></h2></div>
									<form id="adminAuthTacacsProviderAddEditTableRowForm"
										data-dojo-id="adminAuthTacacsProviderAddEditTableRowForm"
										data-dojo-type="xwt.widget.notification.Form" name="tableForm">
										<table style="width: 100%;">
										<tr>
										<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;" title="Hostname/FQDN (or IP Address)">Hostname:<em>*</em></label>
												</div>
											</td>
											<td height="40">
												<div id="tacacsProviderHostnameEditTableRowName"
													data-dojo-id="tacacsProviderHostnameEditTableRowName"
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
												<select id="tacacsProviderEditTableRowOrder"
													data-dojo-id="tacacsProviderEditTableRowOrder"
													name="providerOrder"
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
													<label style="float: left;">Port:<em>*</em></label>
												</div>
											</td>
											
											<td>
												<div id="tacacsProviderEditTableRowPort"
													data-dojo-id="tacacsProviderEditTableRowPort"
													name="port" value="49" style="width: 80px;"
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
												<div id="tacacsProviderEditTableRowTimeout"
													data-dojo-id="tacacsProviderEditTableRowTimeout"
													name="timeout" value="5" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern: REG_EX_NUMBER_0_TO_60, trim:"true", maxlength:"2", required:"true", invalidMessage: MSG_BET_0_TO_60 '></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Key:</label>
												</div>
											</td>
											<td>
													<div id="tacacsProviderEditTableRowKey"
													data-dojo-id="tacacsProviderEditTableRowKey"
													name="providerKey" style="width: 80px;" type="password"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"63" '></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Confirm Key:</label>
												</div>
											</td>
											<td>
												<div id="tacacsProviderEditTableRowConfirmKey"
													data-dojo-id="tacacsProviderEditTableRowConfirmKey"
													name="confirmKey" style="width: 80px;" type="password"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"63" '></div>
											</td>
											</tr>
											<tr></tr>
											<tr>
												<td height="40" style="padding-left: 5px; text-align: right;" colspan="6" >
												<button dojoType="xwt.widget.form.TextButton" 
													id="tacacsProviderEditTableRowSaveDataBtn" baseClass="defaultButton"
													onClick="tacacsProviderAddEditTableRowSaveData();">Save</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button dojoType="xwt.widget.form.TextButton" 
													id="tacacsProviderEditTableRowCalcelBtn"
													onClick="tacacsProviderEditTableRowCancelBtnFun();">Cancel</button>
												</td>
											</tr>
											
										</table>
									</form>
							</div>	
							
							<div id="tacacsProviderTableStartDiv">
									<div dojotype="dijit.layout.ContentPane" region="left"
										style="width: 670px; overflow: hidden;" splitter="true">
										<span dojoType="dojo.data.ItemFileWriteStore"
											jsId="adminTacacsProviderDataStoreTab"
											data="adminAuthenticationTacacsProviderDataTable"></span>
										<div style="width: 670px !important;"
											id="adminTacacsProviderTableTollBar"
											dojoType="xwt.widget.table.ContextualToolbar"
											tableId="adminTacacsProviderTable" quickFilter="false">
											<div dojoType="xwt.widget.table.ContextualButtonGroup"
												showButtons="delete"></div>
											<div dojoType="dijit.form.Button" iconClass="fi-plus"
												title="Create Provider" showLabel="false"
												onclick="return createProvider();"></div>
												<label>TACACS Providers</label>
										</div>
										<div id="adminTacacsProviderTable"
											data-dojo-id="adminTacacsProviderTable"
											dojoType="xwt.widget.table.Table"
											store="adminTacacsProviderDataStoreTab"
											structure="adminTacacsProviderColumns"
											style="width: 670px; height: 230px;" selectMultiple="true"
											selectAllOption="true" showIndex="false" selectModel="input"
											filterOnServer=false></div>
									</div>
							</div>
						</div>
						<div class="floatleft" style="width:50px;margin: 120px 12px 12px 12px;">
							<div dojoType="dijit.form.Button" iconClass="fi-arrow" title="Create new Provider Group. The order of the Providers will be the same as the insertion order." showLabel="false" onclick="return copyTacacsProviderToGroupTable();"></div>
						</div>
						<div class="floatleft">
							<div dojotype="dijit.layout.ContentPane" region="left"
								style="width: 345px; overflow: hidden;" splitter="true">
								<span dojoType="dojo.data.ItemFileWriteStore"
									jsId="adminTacacsProviderGroupDataStoreTab"
									data="adminAuthenticationTacacsGroupDataTable"></span>
								<div style="width: 345px !important;"
									id="adminTacacsProviderGroupTableTollBar"
									dojoType="xwt.widget.table.ContextualToolbar"
									tableId="adminTacacsProviderGroupTable" quickFilter="false">
									<div dojoType="xwt.widget.table.ContextualButtonGroup"
										showButtons="delete"></div>
										<label>TACACS Provider Groups</label>
								</div>
								<div id="adminTacacsProviderGroupTable"
									data-dojo-id="adminTacacsProviderGroupTable"
									dojoType="xwt.widget.table.Table"
									store="adminTacacsProviderGroupDataStoreTab"
									structure="adminTacacsProviderGroupColumns"
									style="width: 345px; height: 230px;" selectMultiple="true"
									selectAllOption="true" showIndex="false" selectModel="input"
									filterOnServer=false></div>
							</div>
							<div style="clear: both;"></div>
							<div style="float: right;padding-top: 5px;"><button dojoType="xwt.widget.form.TextButton" 
										id="adminAuthTacacsProviderAndGroupSaveDataBtn" baseClass="defaultButton"
										onClick="adminAuthTacacsProviderAndGroupSaveFunc();" title="Save TACACS Providers &amp; Groups">Save  &amp; Return</button>
								</div>
						</div>
					</div>