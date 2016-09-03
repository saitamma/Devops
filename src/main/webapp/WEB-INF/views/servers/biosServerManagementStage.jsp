<%-- biosServerManagementStage.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">Assert Nmi On Serr:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosAssertNmiOnSerr" name="assertNmiOnSerr"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Assert Nmi On Perr:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosAssertNmiOnPerr" name="assertNmiOnPerr"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">FRB-2 Timer:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosFrb2Timer" name="frb2Timer"
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
				<label style="float: left;padding-left: 0px;">OS Boot Watchdog Timer:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosOsBootWatchdogTimer" name="osBootWatchdogTimer" data-dojo-id="serversBiosOsBootWatchdogTimer"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Watchdog Timer Timeout Policy:</label>
			</div>
			</td>
			<td>
			<select name="osBootWatchdogTimerTimeoutPolicy" data-dojo-props='readOnly :"true" ' data-dojo-id="serversBiosOsBootWatchdogTimerTimeoutPolicy"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="power-off">Power Off</option>
					<option value="reset">Reset</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Watchdog Timer Timeout:</label>
			</div>
			</td>
			<td>
			<select name="osBootWatchdogTimerTimeout" data-dojo-props=' readOnly :"true" ' data-dojo-id="serversBiosOsBootWatchdogTimerTimeout"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="5-minutes">5 Minutes</option>
					<option value="10-minutes">10 Minutes</option>
					<option value="15-minutes">15 Minutes</option>
					<option value="20-minutes">20 Minutes</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Console Redirection:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosConsoleRedirection" name="consoleRedirection" data-dojo-id="serversBiosConsoleRedirection"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="serial-port-a">Serial Port A</option>
					<option value="serial-port-b">Serial Port B</option>
					<option value="enabled">Enabled</option>
					<option value="com-0">Com 0</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">BAUD Rate:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosBaudRate" name="baudRate" data-dojo-id="serversBiosBaudRate"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="110" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="9600">9600</option>
					<option value="19200">19200</option>
					<option value="38400">38400</option>
					<option value="57600">57600</option>
					<option value="115200">115200</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Flow Control:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosFlowControl" name="flowControl"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="110" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="none">None</option>
					<option value="rts-cts">Rts Cts</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Terminal Type:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosTerminalType" name="terminalType"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="110" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="pc-ansi">Pc Ansi</option>
					<option value="vt100">VT 100</option>
					<option value="vt100-plus">VT 100 Plus</option>
					<option value="vt-utf8">VT UTF8</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Legacy OS Redirect:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosLegacyOsRedirect" name="legacyOsRedirect"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="110" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Putty Keypad:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosPuttyKeypad" name="puttyKeypad"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="110" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="vt100">VT 100</option>
					<option value="linux">Linux</option>
					<option value="xtermr6">Xtermr 6</option>
					<option value="sco">Sco</option>
					<option value="escn">Escn</option>
					<option value="vt400">VT 400</option>
				</select>
			</td>
		</tr>
	</table>
</div>