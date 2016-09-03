<%-- biosProcessor.jsp --%>
<div class="accordionFieldsBorder">
<table>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left:0px;">Turbo Boost:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosTurboBoost" name="turboBoost" data-dojo-id="serversBiosTurboBoost"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Enhanced Intel Speedstep:</label>
			</div>
			</td>
			<td>
			<select id="serversBiosEnhancedIntelSpeedstep" name="enhancedIntelSpeedstep" data-dojo-id="serversBiosEnhancedIntelSpeedstep"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Hyper Threading:</label>
			</div>
			</td>
			<td>
			<select name="hyperThreading" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Core Multi Processing:</label>
			</div>
			</td>
			<td>
			<select name="coreMultiProcessing" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="all">All</option>
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
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Execute Disabled Bit:</label>
			</div>
			</td>
			<td>
			<select name="executeDisabledBit" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Virtualization Technology:</label>
			</div>
			</td>
			<td>
			<select name="virtualizationTechnology" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Hardware Prefetcher:</label>
			</div>
			</td>
			<td>
			<select name="hardwarePrefetcher" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Adjacent Cache Line Prefetcher:</label>
			</div>
			</td>
			<td>
			<select name="adjacentCacheLinePrefetcher"
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">DCU Streamer Prefetch</label>
			</div>
			</td>
			<td>
			<select name="dcuStreamerPrefetch"
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
				<label style="float: left;padding-left: 0px;">DCU IP Prefetcher:</label>
			</div>
			</td>
			<td>
			<select name="dcuIpPrefetch" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Direct Cache Access:</label>
			</div>
			</td>
			<td>
			<select name="directCacheAccess" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Processor C State:</label>
			</div>
			</td>
			<td>
			<select name="processorCState" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Processor C1E:</label>
			</div>
			</td>
			<td>
			<select name="processorC1e" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Processor C3 Report:</label>
			</div>
			</td>
			<td>
			<select name="processorC3Report" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="acpi-c3">ACPI-C3</option>
					<option value="acpi-c2">ACPI-C2</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Processor C6 Report:</label>
			</div>
			</td>
			<td>
			<select name="processorC6Report" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Processor C7 Report:</label>
			</div>
			</td>
			<td>
			<select name="processorC7Report" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">CPU Performance:</label>
			</div>
			</td>
			<td>
			<select name="cpuPerformance" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="hpc">Hpc</option>
					<option value="high-throughput">High Throughput</option>
					<option value="enterprise">Enterprise</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Max Variable MTRR Settings:</label>
			</div>
			</td>
			<td>
			<select name="maxVariableMtrrSetting" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="8">8</option>
					<option value="auto-max">Auto Max</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Local X2 APIC:</label>
			</div>
			</td>
			<td>
			<select name="localX2Apic" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px">
					<option value="platform-default">Platform Default</option>
					<option value="auto">Auto</option>
					<option value="x2apic">X2APIC</option>
					<option value="xapic">XAPIC</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Power Technology:</label>
			</div>
			</td>
			<td>
			<select name="powerTechnology" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="custom">Custom</option>
					<option value="performance">Performance</option>
					<option value="energy-efficient">Energy Efficient</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Energy Performance:</label>
			</div>
			</td>
			<td>
			<select name="energyPerformance" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="energy-efficient">Energy Efficient</option>
					<option value="balanced-energy">Balanced Energy</option>
					<option value="balanced-performance">Balanced Performance</option>
					<option value="performance">Performance</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Frequency Floor Override:</label>
			</div>
			</td>
			<td>
			<select name="frequencyFloorOverride" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">P-STATE Coordination:</label>
			</div>
			</td>
			<td>
			<select name="pstateCoordination" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="sw-any">SW Any</option>
					<option value="sw-all">SW All</option>
					<option value="hw-all">HW All</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">DRAM Clock Throttling:</label>
			</div>
			</td>
			<td>
			<select name="dramClockThrottling" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="energy-efficient">Energy Efficient</option>
					<option value="performance">Performance</option>
					<option value="balanced">Balanced</option>
					<option value="auto">Auto</option>
				</select>
			</td>
		</tr>
		<tr>
			<td height="30">
			<div class="labelspace">
				<label style="float: left;padding-left: 0px;">Channel Interleaving:</label>
			</div>
			</td>
			<td>
			<select name="channelInterleaving" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="4-way">4 way</option>
					<option value="3-way">3 way</option>
					<option value="2-way">2 way</option>
					<option value="1-way">1 way</option>
					<option value="auto">Auto</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Rank Interleaving:</label>
			</div>
			</td>
			<td>
			<select name="rankInterleaving" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="8-way">8 way</option>
					<option value="4-way">4 way</option>
					<option value="2-way">2 way</option>
					<option value="1-way">1 way</option>
					<option value="auto">Auto</option>
				</select>
			</td>
			<td>
			<div class="labelspace">
				<label style="float: left;">Demand Scrub:</label>
			</div>
			</td>
			<td>
			<select name="demandScrub" 
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
				<label style="float: left;padding-left: 0px;">Patrol Scrub:</label>
			</div>
			</td>
			<td>
			<select name="patrolScrub" 
				data-dojo-type="xwt.widget.form.DropDown" maxHeight="160" style="width: 140px" data-dojo-props=' readOnly:"true" '>
					<option value="platform-default">Platform Default</option>
					<option value="enabled">Enabled</option>
					<option value="disabled">Disabled</option>
				</select>
			</td>
		</tr>
</table>
</div>