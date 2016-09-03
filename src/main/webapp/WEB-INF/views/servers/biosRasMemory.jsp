<%-- biosRasMemory.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">Memory RAS Config:</label>
				</div>
			</td>
			<td>
				<select name="memoryRasConfig" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-id="serversBiosMemoryRasConfig">
					<option value="platform-default">Platform Default</option>
					<option value="sparing">Sparing</option>
					<option value="lockstep">Lockstep</option>
					<option value="mirroring">Mirroring</option>
					<option value="maximum-performance">Maximum Performance</option>
				</select>
			</td>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;">Sparing Mode:</label>
				</div>
			</td>
			<td>
				<select name="sparingMode" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-id="serversBiosSparingMode"
					data-dojo-props='readOnly : "true" '>
					<option value="platform-default">Platform Default</option>
					<option value="rank-sparing">Rank Sparing</option>
					<option value="dimm-sparing">DIMM Sparing</option>
				</select>
				
			</td>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;">Mirroring Mode:</label>
				</div>
			</td>
			<td>
				<select name="mirroringMode" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-id="serversBiosMirroringMode"
					data-dojo-props='readOnly : "true" '>
					<option value="platform-default">Platform Default</option>
					<option value="intra-socket">Intra Socket</option>
					<option value="inter-socket">Inter Socket</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<div class="labelspace">
					<label style="float: left;">NUMA:</label>
				</div>
			</td>
			<td>
				<select name="numa" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
				<div class="labelspace">
					<label style="float: left;">LV DDR Mode:</label>
				</div>
			</td>
			<td>
				<select name="lvDdrMode" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="auto">Auto</option>
					<option value="performance-mode">Performance Mode</option>
					<option value="power-saving-mode">Power Saving Mode</option>
				</select>
			</td>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;">DRAM Refresh Rate:</label>
				</div>
			</td>
			<td>
				<select name="dramRefreshRate" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="auto">Auto</option>
					<option value="4x">4x</option>
					<option value="3x">3x</option>
					<option value="2x">2x</option>
					<option value="1x">1x</option>
				</select>
			</td>
			
		</tr>
</table>
</div>