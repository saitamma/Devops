<%-- biosPciStage.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">Max Memory Below 4G:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosMaxMemoryBelow4g" name="maxMemoryBelow4g" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Memory Mapped IO Above 4Gb Config:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosMemoryMappedIoAbove4gbConfig" name="memoryMappedIoAbove4gbConfig" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">VGA Priority:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosVgaPriority" name="vgaPriority"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="onboard">On Board</option>
					<option value="offboard">Off Board</option>
					<option value="onboard-vga-disabled">On Board VGA Disabled</option>
				</select>
			</td>
		</tr>
	</table>
</div>	
