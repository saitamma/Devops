<%-- biosMain.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">Quiet Boot:</label>
				</div>
			</td>
			<td>
				<select name="quietBoot" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
				<div class="labelspace">
					<label style="float: left;">Post Error Pause:</label>
				</div>
			</td>
			<td>
				<select name="postErrorPause" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
				<div class="labelspace">
					<label style="float: left;">Resume AC on Power Loss:</label>
				</div>
			</td>
			<td>
				<select name="resumeAcOnPowerLoss" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="reset">Reset</option>
					<option value="last-state">Last State</option>
					<option value="stay-off">Stay Off</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left: 0px;">Front Panel Lockout:</label>
				</div>
			</td>
			<td>
				<select name="frontPanelLockout" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;">Reboot on BIOS Settings Change:</label>
				</div>
			</td>
			<td>
				<input name="rebootOnChange" dojoType="dijit.form.CheckBox" checked="false" value="yes" data-dojo-id='serversBiosRebootOnChange'/>
			</td>
		</tr>
</table>
</div>