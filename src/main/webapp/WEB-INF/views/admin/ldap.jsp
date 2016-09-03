<%-- ldap.jsp --%>
<div id="displayLdapProvidersAndGroupsTables" style="padding-top:10px;margin: 5px;">
						<div class="commonclassForFormFields" style="padding: 0 0 10px 0;">
							<form id="adminLdapGeneralSettingTableForm"
									data-dojo-id="adminLdapGeneralSettingTableForm"
									data-dojo-type="xwt.widget.notification.Form" name="tableForm">
							<input id="adminLdapGeneralSettingId" name="id" data-dojo-id="adminLdapGeneralSettingId" type="hidden" value="0" dojoType="dijit.form.TextBox"></input>
								<table>
									<tr>
										<td>
											<div class="labelspace">
												<label style="float: left;padding-left: 0px;">Timeout:<em>*</em></label>
											</div>
										</td>
										<td>
											<div id="adminLdapGeneralSettingTimeout"
												name="timeout" style="width: 135px;" value="30"
												data-dojo-id="adminLdapGeneralSettingTimeout"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='required:"true", pattern: REG_EX_NUMBER_0_TO_60, trim:"true", maxlength:"2", promptMessage:"", invalidMessage: MSG_BET_0_TO_60'></div>
										</td>
										<td>
											<div class="labelspace">
												<label style="float: left;">Attribute:</label>
											</div>
										</td>
										<td>
											<div id="adminLdapGeneralSettingAttribute"
												name="attribute" style="width: 135px;"
												data-dojo-id="adminLdapGeneralSettingAttribute"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='trim:"true", maxlength:"45"'></div>
										</td>
										<td>
											<div class="labelspace">
												<label style="float: left;">Base DN:</label>
											</div>
										</td>
										<td>
											<div id="adminLdapGeneralSettingBaseDn"
												name="baseDn" style="width: 135px;"
												data-dojo-id="adminLdapGeneralSettingBaseDn"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='trim:"true", maxlength:"128"'></div>
										</td>
										<td>
											<div class="labelspace">
												<label style="float: left;">Filter:</label>
											</div>
										</td>
										<td>
											<div id="adminLdapGeneralSettingFilter"
												name="filter" style="width: 135px;" value="cn=$userid"
												data-dojo-id="adminLdapGeneralSettingFilter"
												data-dojo-type="xwt.widget.notification.ValidationTextBox"
												data-dojo-props='trim:"true", maxlength:"128", readOnly:"true" '></div>
										</td>
									</tr>
									
								</table>
							</form>
							</div>
						<div class="floatleft">
							<div id="providerTableRowAddEdit" class="classForEditRowFormFields" style="display: none;width: 670px;">
								<div class="pageHeader"><h2>Add/Edit LDAP Provider: <span id="nameOfLanVnicRow"></span></h2></div>
									<form id="adminAuthProviderAddEditTableRowForm"
										data-dojo-id="adminAuthProviderAddEditTableRowForm"
										data-dojo-type="xwt.widget.notification.Form" name="tableForm">
										<table style="width: 100%;">
										<tr>
										<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;" title="Hostname/FQDN (or IP Address)">Hostname:<em>*</em></label>
												</div>
											</td>
											<td height="40">
												<div id="providerHostnameEditTableRowName"
													data-dojo-id="providerHostnameEditTableRowName"
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
												<select id="providerEditTableRowOrder"
													data-dojo-id="providerEditTableRowOrder"
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
													<label style="float: left;">Bind DN:</label>
												</div>
											</td>
											<td height="40">
											<div id="providerEditTableRowBindDn"
													data-dojo-id="providerEditTableRowBindDn"
													name="bindDn" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"127" '></div></td>
											</tr>
											
										<tr>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;">Base DN:</label>
												</div>
											</td>
											<td>
											<div id="providerEditTableRowBaseDn"
													data-dojo-id="providerEditTableRowBaseDn"
													name="baseDn" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"127" '></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Port:<em>*</em></label>
												</div>
											</td>
											<td>
												<div id="providerEditTableRowPort"
													data-dojo-id="providerEditTableRowPort"
													name="port" value="389" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern: REG_EX_NUMBER_1_TO_65535, trim:"true", maxlength:"5", required:"true", invalidMessage: MSG_BET_1_TO_65535'></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Enable SSL:</label>
												</div>
											</td>
											<td>
												<select id="providerEditTableRowEnableSsl"
													data-dojo-id="providerEditTableRowEnableSsl"
													name="enableSsl"
													data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
													style="width: 98px; border: 1px solid #b4b4b4;">
														<option value="no">No</option>
														<option value="yes">Yes</option>
													</select>
											</td>
											</tr>
											
											<tr>
												<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;">Filter:</label>
												</div>
												</td>
												<td>
													<div id="providerEditTableRowFilter"
													data-dojo-id="providerEditTableRowFilter"
													name="filter" style="width: 80px;" value="cn=$userid"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"45", readOnly: "true" '></div>
												</td>
												<td height="40">
												<div class="labelspace">
													<label style="float: left;">Attribute:</label>
												</div>
											</td>
											<td>
													<div id="providerEditTableRowAttribute"
													data-dojo-id="providerEditTableRowAttribute"
													name="attribute" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"45" '></div>
											</td>
											<td>
												<div class="labelspace">
													<label style="float: left;">Timeout:<em>*</em></label>
												</div>
											</td>
											<td>
												<div id="providerEditTableRowTimeout"
													data-dojo-id="providerEditTableRowTimeout"
													name="timeout" value="30" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='pattern: REG_EX_NUMBER_0_TO_60, trim:"true", maxlength:"2", required:"true", invalidMessage: MSG_BET_0_TO_60 '></div>
											</td>
											</tr>
											<tr>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;">Password:</label>
												</div>
											</td>
											<td>
													<div id="providerEditTableRowPassword"
													data-dojo-id="providerEditTableRowPassword"
													name="providerPassword" style="width: 80px;" type="password"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"45" '></div>
											</td>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;">Confirm Pwd:</label>
												</div>
											</td>
											<td>
												<div id="providerEditTableRowCPassword"
													data-dojo-id="providerEditTableRowCPassword"
													name="cPassword" style="width: 80px;" type="password"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"45" '></div>
											</td>
											
											<td>
												<div class="labelspace">
													<label style="float: left;">Vendor:</label>
												</div>
											</td>
											<td>
												<select id="providerEditTableRowVendor"
													data-dojo-id="providerEditTableRowVendor"
													name="vendor"
													data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
													style="width: 98px; border: 1px solid #b4b4b4;">
														<option value="OpenLdap">Open Ldap</option>
														<option value="MS-AD">MS AD</option>
													</select>
											</td>
											</tr>
											<tr>
											<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;">Authorization:</label>
												</div>
											</td>
											<td>
												<select id="providerEditTableRowGroupAauth"
													data-dojo-id="providerEditTableRowGroupAauth"
													name="groupAuthorization"
													data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
													style="width: 98px; border: 1px solid #b4b4b4;">
														<option value="disable">Disable</option>
														<option value="enable">Enable</option>
													</select>
											</td>
											<td>
												<div class="labelspace">
													<label style="float: left;">Recursion:</label>
												</div>
											</td>
											<td>
												<select id="providerEditTableRowGroupRecursion"
													data-dojo-id="providerEditTableRowGroupRecursion"
													name="groupRecursion"
													data-dojo-type="xwt.widget.form.DropDown" maxHeight="100"
													style="width: 98px; border: 1px solid #b4b4b4;">
														<option value="non-recursive">Non Recursive</option>
														<option value="recursive">Recursive</option>
													</select>
											</td>
											<td>
												<div class="labelspace">
													<label style="float: left;">Target Attribute:</label>
												</div>
											</td>
											<td>
												<div id="providerEditTableRowTargetAttribute"
													data-dojo-id="providerEditTableRowTargetAttribute"
													name="targetAttribute" value="memberOf" style="width: 80px;"
													data-dojo-type="xwt.widget.notification.ValidationTextBox"
													data-dojo-props='trim:"true", maxlength:"64"'></div>
											</td>
											</tr>
											<tr>
												<td height="40">
												<div class="labelspace">
													<label style="float: left;padding-left: 5px;">Primary Group:</label>
												</div>
												</td>
												<td>
													<select id="providerEditTableRowUsePrimaryGroup" data-dojo-id="providerEditTableRowUsePrimaryGroup"
														name="usePrimaryGroup" data-dojo-type="xwt.widget.form.DropDown" maxHeight="100" style="width: 98px; border: 1px solid #b4b4b4;">
														<option value="no">No</option>
														<option value="yes">Yes</option>
													</select>
												</td>
												
												<td height="40" style="padding-left: 5px; text-align: right;" colspan="4" >
												<button dojoType="xwt.widget.form.TextButton" 
													id="providerEditTableRowSaveDataBtn" baseClass="defaultButton"
													onClick="providerAddEditTableRowSaveData();">Save</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button dojoType="xwt.widget.form.TextButton" 
													id="providerEditTableRowCalcelBtn"
													onClick="providerEditTableRowCancelBtnFun();">Cancel</button>
												</td>
											</tr>
											
										</table>
									</form>
							</div>	
							
							<div id="providerTableStartDiv">
									<div dojotype="dijit.layout.ContentPane" region="left"
										style="width: 670px; overflow: hidden;" splitter="true">
										<span dojoType="dojo.data.ItemFileWriteStore"
											jsId="adminLdapProviderDataStoreTab"
											data="adminAuthenticationProviderDataTable"></span>
										<div style="width: 670px !important;"
											id="adminLdapProviderTableTollBar"
											dojoType="xwt.widget.table.ContextualToolbar"
											tableId="adminLdapProviderTable" quickFilter="false">
											<div dojoType="xwt.widget.table.ContextualButtonGroup"
												showButtons="delete"></div>
											<div dojoType="dijit.form.Button" iconClass="fi-plus"
												title="Create Provider" showLabel="false"
												onclick="return createLdapProvider();"></div>
												<label>LDAP Providers</label>
										</div>
										<div id="adminLdapProviderTable"
											data-dojo-id="adminLdapProviderTable"
											dojoType="xwt.widget.table.Table"
											store="adminLdapProviderDataStoreTab"
											structure="adminLapProviderColumns"
											style="width: 670px; height: 230px;" selectMultiple="true"
											selectAllOption="true" showIndex="false" selectModel="input"
											filterOnServer=false></div>
									</div>
							</div>
						</div>
						<div class="floatleft" style="width:50px;margin: 120px 12px 12px 12px;">
							<div dojoType="dijit.form.Button" iconClass="fi-arrow" title="Create new Provider Group. The order of the Providers will be the same as the insertion order." showLabel="false" onclick="return copyProviderToGroupTable();"></div>
						</div>
						<div class="floatleft" style="">
							<div dojotype="dijit.layout.ContentPane" region="left"
								style="width: 345px; overflow: hidden;" splitter="true">
								<span dojoType="dojo.data.ItemFileWriteStore"
									jsId="adminLdapProviderGroupDataStoreTab"
									data="adminAuthenticationGroupDataTable"></span>
								<div style="width: 345px !important;"
									id="adminLdapProviderGroupTableTollBar"
									dojoType="xwt.widget.table.ContextualToolbar"
									tableId="adminLdapProviderGroupTable" quickFilter="false">
									<div dojoType="xwt.widget.table.ContextualButtonGroup"
										showButtons="delete"></div>
										<label>LDAP Provider Groups</label>
								</div>
								<div id="adminLdapProviderGroupTable"
									data-dojo-id="adminLdapProviderGroupTable"
									dojoType="xwt.widget.table.Table"
									store="adminLdapProviderGroupDataStoreTab"
									structure="adminLdapProviderGroupColumns"
									style="width: 345px; height: 230px;" selectMultiple="true"
									selectAllOption="true" showIndex="false" selectModel="input"
									filterOnServer=false></div>
							</div>
							<div style="clear: both;"></div>
							<div style="float: right;padding-top: 5px;"><button dojoType="xwt.widget.form.TextButton" 
										id="adminAuthLdapProviderAndGroupSaveDataBtn" baseClass="defaultButton"
										onClick="adminAuthLdapProviderAndGroupSaveFunc();" title="Save LDAP Providers &amp; Groups">Save  &amp; Return</button>
								</div>
						</div>
					</div>