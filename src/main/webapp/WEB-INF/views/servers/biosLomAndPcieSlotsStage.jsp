<%-- biosLomAndPcieSlotsStage.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">All Onboard LOM Ports:</label>
				</div>
			</td>
			<td>
			<select id="serversBiosOnboardLomPorts" name="allOnboardLomPorts"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="disabled">Disabled</option>
					<option value="enabled">Enabled</option>
				</select>
				</td>
				<td>
				<div class="labelspace">
					<label style="float: left;">LOM Port 0 Option ROM:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosLomPort0OptionRom" name="lomPort0OptionRom"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="disabled">Disabled</option>
						<option value="enabled">Enabled</option>
						<option value="uefi-only">Uefi Only</option>
						<option value="legacy-only">Legacy Only</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td>
				<div class="labelspace">
					<label style="float: left;">LOM Port 1 Option ROM:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosLomPort1OptionRom" name="lomPort1OptionRom"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="disabled">Disabled</option>
						<option value="enabled">Enabled</option>
						<option value="uefi-only">Uefi Only</option>
						<option value="legacy-only">Legacy Only</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">LOM Port 2 Option ROM:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosLomPort2OptionRom" name="lomPort2OptionRom"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="disabled">Disabled</option>
						<option value="enabled">Enabled</option>
						<option value="uefi-only">Uefi Only</option>
						<option value="legacy-only">Legacy Only</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td>
				<div class="labelspace">
					<label style="float: left;">LOM Port 3 Option ROM:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosLomPort3OptionRom" name="lomPort3OptionRom"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="disabled">Disabled</option>
						<option value="enabled">Enabled</option>
						<option value="uefi-only">Uefi Only</option>
						<option value="legacy-only">Legacy Only</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td>
				<div class="labelspace">
					<label style="float: left;">PCIe Slot:SAS Option ROM:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlotSasOptionRom" name="pcieSlotSasOptionRom"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="disabled">Disabled</option>
						<option value="enabled">Enabled</option>
						<option value="uefi-only">Uefi Only</option>
						<option value="legacy-only">Legacy Only</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">PCIe Slot:1 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot1LinkSpeed" name="pcieSlot1LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;">PCIe Slot:2 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot2LinkSpeed" name="pcieSlot2LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;">PCIe Slot:3 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot3LinkSpeed" name="pcieSlot3LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">PCIe Slot:4 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot4LinkSpeed" name="pcieSlot4LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;">PCIe Slot:5 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot5LinkSpeed" name="pcieSlot5LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;">PCIe Slot:6 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot6LinkSpeed" name="pcieSlot6LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">PCIe Slot:7 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot7LinkSpeed" name="pcieSlot7LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;">PCIe Slot:8 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot8LinkSpeed" name="pcieSlot8LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
				<td height="30">
				<div class="labelspace">
					<label style="float: left;">PCIe Slot:9 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot9LinkSpeed" name="pcieSlot9LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
			</tr>
			<tr>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">PCIe Slot:10 Link Speed:</label>
				</div>
				</td>
				<td>
				<select id="serversBiosPcieSlot10LinkSpeed" name="pcieSlot10LinkSpeed"
					data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
						<option value="gen1">Gen1</option>
						<option value="gen2">Gen2</option>
						<option value="gen3">Gen3</option>
						<option value="auto">Auto</option>
						<option value="disabled">Disabled</option>
						<option value="platform-default">Platform Default</option>
					</select>
				</td>
			</tr>
	</table>
</div>