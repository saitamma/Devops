<%-- biosBootOptionsStage.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">Boot Option Retry:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosBootOptionRetry" name="bootOptionRetry"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Intel Entry SAS RAID:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosIntelEntrySasRaid" name="intelEntrySasRaid" data-dojo-id="serversBiosIntelEntrySasRaid"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Intel Entry SAS RAID Module:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosIntelEntrySasRaidModule" name="intelEntrySasRaidModule" data-dojo-id="serversBiosIntelEntrySasRaidModule"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="it-ir-raid">IT IR Raid</option>
					<option value="intel-esrtil">Intel Esrtil</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">Onboard SCU Storage Support:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosOnboardScuStorageSupport" name="onboardScuStorageSupport"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
	</table>
</div>