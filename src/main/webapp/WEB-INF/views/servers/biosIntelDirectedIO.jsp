<%-- biosIntleDirectedIO.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left:0px;">VT for Directed IO:</label>
				</div>
			</td>
			<td>
				<select name="vtForDirectedIo" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
				<div class="labelspace">
					<label style="float: left;">Interrupt Remap:</label>
				</div>
			</td>
			<td>
				<select name="interruptRemap" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
				<div class="labelspace">
					<label style="float: left;">Coherency Support:</label>
				</div>
			</td>
			<td>
				<select name="coherencySupport" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;padding-left: 0px;">ATS Support:</label>
				</div>
			</td>
			<td>
				<select name="atsSupport" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td height="30">
				<div class="labelspace">
					<label style="float: left;">Pass Through DMA Support:</label>
				</div>
			</td>
			<td>
				<select name="passThroughDmaSupport" data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
</table>
</div>