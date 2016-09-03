<%-- biosUsbStage.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">Make Device Non Bootable:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosMakeDeviceNonBootable" name="makeDeviceNonBootable" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Legacy USB Support:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosLegacyUsbSupport" name="legacyUsbSupport" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
					<option value="auto">Auto</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">USB System Idle Power Optimizing Setting:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbSystemIdlePowerOptimizingSetting" name="usbSystemIdlePowerOptimizingSetting"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="high-performance">High Performance</option>
					<option value="lower-idle-power">Lower Idle Power</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">USB Front Panel Access Lock:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbFrontPanelAccessLock" name="usbFrontPanelAccessLock"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Port 60/64 Emulation:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosPort6064Emulation" name="port6064Emulation" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">USB Port Front:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbPortFront" name="usbPortFront" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">USB Port Internal:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbPortInternal" name="usbPortInternal"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">USB Port KVM:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbPortKvm" name="usbPortKvm" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">USB Port Rear:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbPortRear" name="usbPortRear" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">USB Port SD Card:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbPortSdCard" name="usbPortSdCard"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">USB Port vMedia:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosUsbPortVMedia" name="usbPortVMedia" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">All USB Devices:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosAllUsbDevices" name="allUsbDevices" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
	</table>
</div>