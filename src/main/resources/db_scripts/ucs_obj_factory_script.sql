DROP TABLE IF EXISTS `aaaauthrealm`;
CREATE TABLE `aaaauthrealm` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `defRolePolicy` varchar(255) DEFAULT NULL,
  `defLogin` varchar(255) DEFAULT NULL,
  `conLogin` varchar(255) DEFAULT NULL,
  `FK_topSystem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaadomain`;
CREATE TABLE `aaadomain` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `sessionTimeout` varchar(255) DEFAULT NULL,
  `refreshPeriod` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_aaaAuthRealm` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaadomainauth`;
CREATE TABLE `aaadomainauth` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `use2Factor` varchar(255) DEFAULT NULL,
  `providerGroup` varchar(255) DEFAULT NULL,
  `realm` varchar(255) DEFAULT NULL,
  `FK_aaaDomain` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfccdbworkqueueprofile`;
CREATE TABLE `adaptorfccdbworkqueueprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `ringSize` varchar(255) DEFAULT NULL,
  `count` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfcerrorrecoveryprofile`;
CREATE TABLE `adaptorfcerrorrecoveryprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `portDownTimeout` varchar(255) DEFAULT NULL,
  `portDownIoRetryCount` varchar(255) DEFAULT NULL,
  `linkDownTimeout` varchar(255) DEFAULT NULL,
  `fcpErrorRecovery` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfcinterruptprofile`;
CREATE TABLE `adaptorfcinterruptprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfcportflogiprofile`;
CREATE TABLE `adaptorfcportflogiprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfcportplogiprofile`;
CREATE TABLE `adaptorfcportplogiprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfcportprofile`;
CREATE TABLE `adaptorfcportprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `lunsPerTarget` varchar(255) DEFAULT NULL,
  `ioThrottleCount` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfcrecvqueueprofile`;
CREATE TABLE `adaptorfcrecvqueueprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `ringSize` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorfcworkqueueprofile`;
CREATE TABLE `adaptorfcworkqueueprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `ringSize` varchar(255) DEFAULT NULL,
  `FK_adaptorHostFcIfProfile` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorhostfcifprofile`;
CREATE TABLE `adaptorhostfcifprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `commdatetime`;
CREATE TABLE `commdatetime` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `timezone` varchar(255) DEFAULT NULL,
  `FK_commSvcEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `commdns`;
CREATE TABLE `commdns` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `FK_commSvcEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `commdnsprovider`;
CREATE TABLE `commdnsprovider` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_commDns` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `commntpprovider`;
CREATE TABLE `commntpprovider` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_commDateTime` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `commsvcep`;
CREATE TABLE `commsvcep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_topSystem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `computechassisqual`;
CREATE TABLE `computechassisqual` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `minId` varchar(255) DEFAULT NULL,
  `maxId` varchar(255) DEFAULT NULL,
  `FK_computeQual` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `computepool`;
CREATE TABLE `computepool` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `computepoolingpolicy`;
CREATE TABLE `computepoolingpolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `qualifier` varchar(255) DEFAULT NULL,
  `poolDn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `computepsupolicy`;
CREATE TABLE `computepsupolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `redundancy` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `computequal`;
CREATE TABLE `computequal` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `computeslotqual`;
CREATE TABLE `computeslotqual` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `minId` varchar(255) DEFAULT NULL,
  `maxId` varchar(255) DEFAULT NULL,
  `FK_computeChassisQual` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `dpsecmac`;
CREATE TABLE `dpsecmac` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `forge` varchar(255) DEFAULT NULL,
  `FK_nwctrlDefinition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `epqosdefinition`;
CREATE TABLE `epqosdefinition` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `epqosegress`;
CREATE TABLE `epqosegress` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rate` varchar(255) DEFAULT NULL,
  `prio` varchar(255) DEFAULT NULL,
  `hostControl` varchar(255) DEFAULT NULL,
  `burst` varchar(255) DEFAULT NULL,
  `FK_epqosDefinition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricbhvlan`;
CREATE TABLE `fabricbhvlan` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `sharing` varchar(255) DEFAULT NULL,
  `pubNwName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `FK_fabricFcEstcCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricdcesrv`;
CREATE TABLE `fabricdcesrv` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `FK_fabricEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricdceswsrv`;
CREATE TABLE `fabricdceswsrv` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `FK_fabricDceSrv` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricdceswsrvep`;
CREATE TABLE `fabricdceswsrvep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `usrLbl` varchar(255) DEFAULT NULL,
  `slotId` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `FK_fabricDceSwSrv` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricdceswsrvpc`;
CREATE TABLE `fabricdceswsrvpc` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `FK_fabricDceSwSrv` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricdceswsrvpcep`;
CREATE TABLE `fabricdceswsrvpcep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `usrLbl` varchar(255) DEFAULT NULL,
  `slotId` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `FK_fabricDceSwSrvPc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricep`;
CREATE TABLE `fabricep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `FK_topRoot` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricethestc`;
CREATE TABLE `fabricethestc` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `FK_fabricEthEstcCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricethestccloud`;
CREATE TABLE `fabricethestccloud` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `FK_fabricEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricethlan`;
CREATE TABLE `fabricethlan` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricethlanep`;
CREATE TABLE `fabricethlanep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `usrLbl` varchar(255) DEFAULT NULL,
  `slotId` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `FK_fabricEthLan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricethlanpc`;
CREATE TABLE `fabricethlanpc` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `operSpeed` varchar(255) DEFAULT NULL,
  `flowCtrlPolicy` varchar(255) DEFAULT NULL,
  `adminSpeed` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `FK_fabricEthLan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricethlanpcep`;
CREATE TABLE `fabricethlanpcep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `slotId` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `ethLinkProfileName` varchar(255) DEFAULT NULL,
  `FK_fabricEthLanPc` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricethlinkprofile`;
CREATE TABLE `fabricethlinkprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `udldLinkPolicyName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cdpLinkPolicyName` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricfcestc`;
CREATE TABLE `fabricfcestc` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `FK_fabricFcEstcCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricfcestccloud`;
CREATE TABLE `fabricfcestccloud` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `FK_fabricEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricfcsan`;
CREATE TABLE `fabricfcsan` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `uplinkTrunking` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `FK_fabricSanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricfcsanep`;
CREATE TABLE `fabricfcsanep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `usrLbl` varchar(255) DEFAULT NULL,
  `slotId` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `fillPattern` varchar(255) DEFAULT NULL,
  `FK_fabricFcSan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricfcvsanportep`;
CREATE TABLE `fabricfcvsanportep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `switchId` varchar(255) DEFAULT NULL,
  `slotId` varchar(255) DEFAULT NULL,
  `portId` varchar(255) DEFAULT NULL,
  `FK_fabricVsan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabriclancloud`;
CREATE TABLE `fabriclancloud` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `vlanCompression` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `macAging` varchar(255) DEFAULT NULL,
  `extvmmVMNetworkSets` varchar(255) DEFAULT NULL,
  `extvmmNetworkSets` varchar(255) DEFAULT NULL,
  `vnicProfileSet` varchar(255) DEFAULT NULL,
  `FK_fabricEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricsancloud`;
CREATE TABLE `fabricsancloud` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `FK_fabricEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricudldlinkpolicy`;
CREATE TABLE `fabricudldlinkpolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricvcon`;
CREATE TABLE `fabricvcon` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `transport` varchar(255) DEFAULT NULL,
  `share` varchar(255) DEFAULT NULL,
  `select` varchar(255) DEFAULT NULL,
  `placement` varchar(255) DEFAULT NULL,
  `instType` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `fabric` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricvlan`;
CREATE TABLE `fabricvlan` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `sharing` varchar(255) DEFAULT NULL,
  `pubNwName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mcastPolicyName` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `defaultNet` varchar(255) DEFAULT NULL,
  `compressionType` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  `FK_fabricEthEstcCloud` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fabricvsan`;
CREATE TABLE `fabricvsan` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `zoningState` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `fcoeVlan` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  `FK_fabricFcEstcCloud` varchar(255) DEFAULT NULL,
  `FK_fabricFcSan` varchar(255) DEFAULT NULL,
  `FK_fabricSanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fcpoolblock`;
CREATE TABLE `fcpoolblock` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `to` varchar(255) DEFAULT NULL,
  `from` varchar(255) DEFAULT NULL,
  `FK_fcpoolInitiators` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fcpoolinitiator`;
CREATE TABLE `fcpoolinitiator` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `FK_fcpoolInitiators` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `fcpoolinitiators`;
CREATE TABLE `fcpoolinitiators` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `maxPortsPerNode` varchar(255) DEFAULT NULL,
  `assignmentOrder` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `flowctrldefinition`;
CREATE TABLE `flowctrldefinition` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `flowctrlitem`;
CREATE TABLE `flowctrlitem` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `snd` varchar(255) DEFAULT NULL,
  `rcv` varchar(255) DEFAULT NULL,
  `prio` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_flowctrlDefinition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ippoolblock`;
CREATE TABLE `ippoolblock` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `to` varchar(255) DEFAULT NULL,
  `subnet` varchar(255) DEFAULT NULL,
  `primDns` varchar(255) DEFAULT NULL,
  `from` varchar(255) DEFAULT NULL,
  `defGw` varchar(255) DEFAULT NULL,
  `FK_ippoolPool` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `ippoolpool`;
CREATE TABLE `ippoolpool` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `supportsDHCP` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `isNetBIOSEnabled` varchar(255) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `extManaged` varchar(255) DEFAULT NULL,
  `assignmentOrder` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootbootsecurity`;
CREATE TABLE `lsbootbootsecurity` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `childAction` varchar(255) DEFAULT NULL,
  `secureBoot` varchar(255) DEFAULT NULL,
  `FK_lsbootPolicy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootlan`;
CREATE TABLE `lsbootlan` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `prot` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootPolicy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootlanimagepath`;
CREATE TABLE `lsbootlanimagepath` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `vnicName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `provSrvPolicyName` varchar(255) DEFAULT NULL,
  `imgSecPolicyName` varchar(255) DEFAULT NULL,
  `imgPolicyName` varchar(255) DEFAULT NULL,
  `iSCSIVnicName` varchar(255) DEFAULT NULL,
  `bootIpPolicyName` varchar(255) DEFAULT NULL,
  `FK_lsbootLan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootlocalhddimage`;
CREATE TABLE `lsbootlocalhddimage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootLocalStorage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootlocalstorage`;
CREATE TABLE `lsbootlocalstorage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `FK_lsbootStorage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootpolicy`;
CREATE TABLE `lsbootpolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `rebootOnUpdate` varchar(255) DEFAULT NULL,
  `purpose` varchar(255) DEFAULT NULL,
  `enforceVnicName` varchar(255) DEFAULT NULL,
  `bootMode` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootsan`;
CREATE TABLE `lsbootsan` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootPolicy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootsancatsanimage`;
CREATE TABLE `lsbootsancatsanimage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `vnicName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `FK_lsbootSan` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootsancatsanimagepath`;
CREATE TABLE `lsbootsancatsanimagepath` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `wwn` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `lun` varchar(255) DEFAULT NULL,
  `FK_lsbootSanCatSanImage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootstorage`;
CREATE TABLE `lsbootstorage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootPolicy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootusbexternalimage`;
CREATE TABLE `lsbootusbexternalimage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootLocalStorage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootusbflashstorageimage`;
CREATE TABLE `lsbootusbflashstorageimage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootLocalStorage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootusbinternalimage`;
CREATE TABLE `lsbootusbinternalimage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootLocalStorage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lspower`;
CREATE TABLE `lspower` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsrequirement`;
CREATE TABLE `lsrequirement` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `restrictMigration` varchar(255) DEFAULT NULL,
  `qualifier` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsserver`;
CREATE TABLE `lsserver` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `vconProfileName` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `usrLbl` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `statsPolicyName` varchar(255) DEFAULT NULL,
  `srcTemplName` varchar(255) DEFAULT NULL,
  `solPolicyName` varchar(255) DEFAULT NULL,
  `scrubPolicyName` varchar(255) DEFAULT NULL,
  `resolveRemote` varchar(255) DEFAULT NULL,
  `powerPolicyName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mgmtFwPolicyName` varchar(255) DEFAULT NULL,
  `mgmtAccessPolicyName` varchar(255) DEFAULT NULL,
  `maintPolicyName` varchar(255) DEFAULT NULL,
  `localDiskPolicyName` varchar(255) DEFAULT NULL,
  `identPoolName` varchar(255) DEFAULT NULL,
  `hostFwPolicyName` varchar(255) DEFAULT NULL,
  `extIPState` varchar(255) DEFAULT NULL,
  `extIPPoolName` varchar(255) DEFAULT NULL,
  `dynamicConPolicyName` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `bootPolicyName` varchar(255) DEFAULT NULL,
  `biosProfileName` varchar(255) DEFAULT NULL,
  `agentPolicyName` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsserverextension`;
CREATE TABLE `lsserverextension` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `guid` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsvconassign`;
CREATE TABLE `lsvconassign` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `vnicName` varchar(255) DEFAULT NULL,
  `transport` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `adminVcon` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsversionbeh`;
CREATE TABLE `lsversionbeh` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `vnicOrder` varchar(255) DEFAULT NULL,
  `vnicMap` varchar(255) DEFAULT NULL,
  `vconMap` varchar(255) DEFAULT NULL,
  `pciEnum` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `macpoolblock`;
CREATE TABLE `macpoolblock` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `to` varchar(255) DEFAULT NULL,
  `from` varchar(255) DEFAULT NULL,
  `FK_macpoolPool` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `macpoolpool`;
CREATE TABLE `macpoolpool` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `assignmentOrder` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `mgmtinbandprofile`;
CREATE TABLE `mgmtinbandprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `poolName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `defaultVlanName` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `nwctrldefinition`;
CREATE TABLE `nwctrldefinition` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `uplinkFailAction` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `macRegisterMode` varchar(255) DEFAULT NULL,
  `cdp` varchar(255) DEFAULT NULL,
  `FK_fabricEthEstcCloud` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `orgorg`;
CREATE TABLE `orgorg` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL,
  `FK_topRoot` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `qosclassdefinition`;
CREATE TABLE `qosclassdefinition` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `qosclassethbe`;
CREATE TABLE `qosclassethbe` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `multicastOptimize` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `mtu` varchar(255) DEFAULT NULL,
  `FK_qosclassDefinition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `qosclassethclassified`;
CREATE TABLE `qosclassethclassified` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `multicastOptimize` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `mtu` varchar(255) DEFAULT NULL,
  `drop` varchar(255) DEFAULT NULL,
  `cos` varchar(255) DEFAULT NULL,
  `adminState` varchar(255) DEFAULT NULL,
  `FK_qosclassDefinition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `qosclassfc`;
CREATE TABLE `qosclassfc` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `cos` varchar(255) DEFAULT NULL,
  `FK_qosclassDefinition` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `statsthresholdpolicy`;
CREATE TABLE `statsthresholdpolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_fabricDceSrv` varchar(255) DEFAULT NULL,
  `FK_fabricEthEstcCloud` varchar(255) DEFAULT NULL,
  `FK_fabricFcEstcCloud` varchar(255) DEFAULT NULL,
  `FK_fabricLanCloud` varchar(255) DEFAULT NULL,
  `FK_fabricSanCloud` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `storagelocaldiskconfigpolicy`;
CREATE TABLE `storagelocaldiskconfigpolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `protectConfig` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `flexFlashState` varchar(255) DEFAULT NULL,
  `flexFlashRAIDReportingState` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `toproot`;
CREATE TABLE `toproot` (
  `PrimaryKey` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `topsystem`;
CREATE TABLE `topsystem` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `FK_topRoot` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `uuidpoolblock`;
CREATE TABLE `uuidpoolblock` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `to` varchar(255) DEFAULT NULL,
  `from` varchar(255) DEFAULT NULL,
  `FK_uuidpoolPool` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `uuidpoolpool`;
CREATE TABLE `uuidpoolpool` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `prefix` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `assignmentOrder` varchar(255) DEFAULT NULL,
  `Text` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicconndef`;
CREATE TABLE `vnicconndef` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `lanConnPolicyName` varchar(255) DEFAULT NULL,
  `sanConnPolicyName` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicether`;
CREATE TABLE `vnicether` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `statsPolicyName` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `adminVcon` varchar(255) DEFAULT NULL,
  `switchId` varchar(255) DEFAULT NULL,
  `qosPolicyName` varchar(255) DEFAULT NULL,
  `pinToGroupName` varchar(255) DEFAULT NULL,
  `nwTemplName` varchar(255) DEFAULT NULL,
  `nwCtrlPolicyName` varchar(255) DEFAULT NULL,
  `mtu` varchar(255) DEFAULT NULL,
  `identPoolName` varchar(255) DEFAULT NULL,
  `adaptorProfileName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicetherif`;
CREATE TABLE `vnicetherif` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `defaultNet` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_vnicEther` varchar(255) DEFAULT NULL,
  `FK_vnicLanConnTempl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicfc`;
CREATE TABLE `vnicfc` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `switchId` varchar(255) DEFAULT NULL,
  `statsPolicyName` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `adminVcon` varchar(255) DEFAULT NULL,
  `qosPolicyName` varchar(255) DEFAULT NULL,
  `pinToGroupName` varchar(255) DEFAULT NULL,
  `persBind` varchar(255) DEFAULT NULL,
  `nwTemplName` varchar(255) DEFAULT NULL,
  `maxDataFieldSize` varchar(255) DEFAULT NULL,
  `identPoolName` varchar(255) DEFAULT NULL,
  `adaptorProfileName` varchar(255) DEFAULT NULL,
  `persBindClear` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL,
  `FK_vnicSanConnPolicy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicfcif`;
CREATE TABLE `vnicfcif` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rn` varchar(255) DEFAULT NULL,
  `FK_vnicFc` varchar(255) DEFAULT NULL,
  `FK_vnicSanConnTempl` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicfcnode`;
CREATE TABLE `vnicfcnode` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `identPoolName` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `FK_lsServer` varchar(255) DEFAULT NULL,
  `FK_vnicSanConnPolicy` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vniclanconntempl`;
CREATE TABLE `vniclanconntempl` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `statsPolicyName` varchar(255) DEFAULT NULL,
  `pinToGroupName` varchar(255) DEFAULT NULL,
  `templType` varchar(255) DEFAULT NULL,
  `switchId` varchar(255) DEFAULT NULL,
  `qosPolicyName` varchar(255) DEFAULT NULL,
  `nwCtrlPolicyName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mtu` varchar(255) DEFAULT NULL,
  `identPoolName` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicsanconnpolicy`;
CREATE TABLE `vnicsanconnpolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vnicsanconntempl`;
CREATE TABLE `vnicsanconntempl` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `switchId` varchar(255) DEFAULT NULL,
  `statsPolicyName` varchar(255) DEFAULT NULL,
  `pinToGroupName` varchar(255) DEFAULT NULL,
  `templType` varchar(255) DEFAULT NULL,
  `qosPolicyName` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `maxDataFieldSize` varchar(255) DEFAULT NULL,
  `identPoolName` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `lsbootdefaultlocalimage`;
CREATE TABLE `lsbootdefaultlocalimage` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_lsbootLocalStorage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaldapep`;
CREATE TABLE `aaaldapep` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `attribute` varchar(255) DEFAULT NULL,
  `basedn` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `filter` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `FK_topSystem` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaldapgrouprule`;
CREATE TABLE `aaaldapgrouprule` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `authorization` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `targetAttr` varchar(255) DEFAULT NULL,
  `traversal` varchar(255) DEFAULT NULL,
  `usePrimaryGroup` varchar(255) DEFAULT NULL,
  `FK_aaaLdapEp` varchar(255) DEFAULT NULL,
  `FK_aaaLdapProvider` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaldapprovider`;
CREATE TABLE `aaaldapprovider` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `attribute` varchar(255) DEFAULT NULL,
  `basedn` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `enableSSL` varchar(255) DEFAULT NULL,
  `encKey` varchar(255) DEFAULT NULL,
  `filter` varchar(255) DEFAULT NULL,
  `key` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `rootdn` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `vendor` varchar(255) DEFAULT NULL,
  `FK_aaaLdapEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaprovidergroup`;
CREATE TABLE `aaaprovidergroup` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_aaaLdapEp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaproviderref`;
CREATE TABLE `aaaproviderref` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order` varchar(255) DEFAULT NULL,
  `FK_aaaProviderGroup` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaUserEp`;
CREATE TABLE `aaaUserEp` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pwdStrengthCheck` varchar(255) DEFAULT NULL,
  `FK_topSystem` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaLocale`;
CREATE TABLE `aaaLocale` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_aaaUserEp` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaOrg`;
CREATE TABLE `aaaOrg` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `orgDn` varchar(255) DEFAULT NULL,
  `FK_aaaLocale` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaLdapGroup`;
CREATE TABLE `aaaLdapGroup` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_aaaldapep` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaUserLocale`;
CREATE TABLE `aaaUserLocale` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_aaaLdapGroup` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaRole`;
CREATE TABLE `aaaRole` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `priv` varchar(255) DEFAULT NULL,
  `FK_aaaUserEp` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaUserRole`;
CREATE TABLE `aaaUserRole` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_aaaLdapGroup` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaRadiusEp`;
CREATE TABLE `aaaRadiusEp` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `FK_topSystem` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaRadiusProvider`;
CREATE TABLE `aaaRadiusProvider` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `authPort` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `encKey` varchar(255) DEFAULT NULL,
  `key_key` varchar(255) DEFAULT NULL,
  `order_order` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `FK_aaaRadiusEp` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaTacacsPlusEp`;
CREATE TABLE `aaaTacacsPlusEp` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `FK_topSystem` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `aaaTacacsPlusProvider`;
CREATE TABLE `aaaTacacsPlusProvider` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `encKey` varchar(255) DEFAULT NULL,
  `key_key` varchar(255) DEFAULT NULL,
  `order_order` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `retries` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `FK_aaaTacacsPlusEp` int(11) NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `firmwareComputeHostPack`;
CREATE TABLE `firmwareComputeHostPack` (
  `PrimaryKey` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `bladeBundleVersion` varchar(255) DEFAULT NULL,
  `ignoreCompCheck` varchar(255) DEFAULT NULL,
  `firmware_mode` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `rackBundleVersion` varchar(255) DEFAULT NULL,
  `stageSize` varchar(255) DEFAULT NULL,
  `updateTrigger` varchar(255) DEFAULT NULL,
  `FK_orgOrg` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `biosVfACPI10Support`;
CREATE TABLE biosVfACPI10Support (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpACPI10Support varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfAllUSBDevices`;
CREATE TABLE biosVfAllUSBDevices (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpAllUSBDevices varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfAssertNMIOnPERR`;
CREATE TABLE biosVfAssertNMIOnPERR (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpAssertNMIOnPERR varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfAssertNMIOnSERR`;
CREATE TABLE biosVfAssertNMIOnSERR (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpAssertNMIOnSERR varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfBootOptionRetry`;
CREATE TABLE biosVfBootOptionRetry (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpBootOptionRetry varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfConsoleRedirection`;
CREATE TABLE biosVfConsoleRedirection (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpBaudRate varchar(255),
vpConsoleRedirection varchar(255),
vpFlowControl varchar(255),
vpLegacyOSRedirection varchar(255),
vpPuttyKeyPad varchar(255),
vpTerminalType varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfCoreMultiProcessing`;
CREATE TABLE biosVfCoreMultiProcessing (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpCoreMultiProcessing varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfCPUPerformance`;
CREATE TABLE biosVfCPUPerformance (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpCPUPerformance varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfDirectCacheAccess`;
CREATE TABLE biosVfDirectCacheAccess (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpDirectCacheAccess varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfDRAMClockThrottling`;
CREATE TABLE biosVfDRAMClockThrottling (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpDRAMClockThrottling varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfDramRefreshRate`;
CREATE TABLE biosVfDramRefreshRate (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpDramRefreshRate varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfEnhancedIntelSpeedStepTech`;
CREATE TABLE biosVfEnhancedIntelSpeedStepTech (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpEnhancedIntelSpeedStepTech varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfExecuteDisableBit`;
CREATE TABLE biosVfExecuteDisableBit (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpExecuteDisableBit varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfFRB2Timer`;
CREATE TABLE biosVfFRB2Timer (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpFRB2Timer varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfFrequencyFloorOverride`;
CREATE TABLE biosVfFrequencyFloorOverride (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpFrequencyFloorOverride varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfFrontPanelLockout`;
CREATE TABLE biosVfFrontPanelLockout (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpFrontPanelLockout varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfIntelEntrySASRAIDModule`;
CREATE TABLE biosVfIntelEntrySASRAIDModule (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpSASRAID varchar(255),
vpSASRAIDModule varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfIntelHyperThreadingTech`;
CREATE TABLE biosVfIntelHyperThreadingTech (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpIntelHyperThreadingTech varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfIntelTurboBoostTech`;
CREATE TABLE biosVfIntelTurboBoostTech (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpIntelTurboBoostTech varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfIntelVirtualizationTechnology`;
CREATE TABLE biosVfIntelVirtualizationTechnology (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpIntelVirtualizationTechnology varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfIntelVTForDirectedIO`;
CREATE TABLE biosVfIntelVTForDirectedIO (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpIntelVTDATSSupport varchar(255),
vpIntelVTDCoherencySupport varchar(255),
vpIntelVTDInterruptRemapping varchar(255),
vpIntelVTDPassThroughDMASupport varchar(255),
vpIntelVTForDirectedIO varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfInterleaveConfiguration`;
CREATE TABLE biosVfInterleaveConfiguration (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpChannelInterleaving varchar(255),
vpMemoryInterleaving varchar(255),
vpRankInterleaving varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfLocalX2Apic`;
CREATE TABLE biosVfLocalX2Apic (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpLocalX2Apic varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfLOMPortsConfiguration`;
CREATE TABLE biosVfLOMPortsConfiguration (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpAllOnboardLOMPorts varchar(255),
vpLOMPort0OptionROM varchar(255),
vpLOMPort1OptionROM varchar(255),
vpLOMPort2OptionROM varchar(255),
vpLOMPort3OptionROM varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfLvDIMMSupport`;
CREATE TABLE biosVfLvDIMMSupport (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpLvDDRMode varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfMaximumMemoryBelow4GB`;
CREATE TABLE biosVfMaximumMemoryBelow4GB (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpMaximumMemoryBelow4GB varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfMaxVariableMTRRSetting`;
CREATE TABLE biosVfMaxVariableMTRRSetting (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpProcessorMtrr varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfMemoryMappedIOAbove4GB`;
CREATE TABLE biosVfMemoryMappedIOAbove4GB (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpMemoryMappedIOAbove4GB varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfMirroringMode`;
CREATE TABLE biosVfMirroringMode (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpMirroringMode varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfNUMAOptimized`;
CREATE TABLE biosVfNUMAOptimized (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpNUMAOptimized varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfOnboardSATAController`;
CREATE TABLE biosVfOnboardSATAController (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpOnboardSATAController varchar(255),
vpSATAMode varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfOnboardStorage`;
CREATE TABLE biosVfOnboardStorage (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpOnboardSCUStorageSupport varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfOptionROMEnable`;
CREATE TABLE biosVfOptionROMEnable (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpState varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfOptionROMLoad`;
CREATE TABLE biosVfOptionROMLoad (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpLoad varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfOSBootWatchdogTimer`;
CREATE TABLE biosVfOSBootWatchdogTimer (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpOSBootWatchdogTimer varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfOSBootWatchdogTimerPolicy`;
CREATE TABLE biosVfOSBootWatchdogTimerPolicy (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpOSBootWatchdogTimerPolicy varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfOSBootWatchdogTimerTimeout`;
CREATE TABLE biosVfOSBootWatchdogTimerTimeout (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpOSBootWatchdogTimerTimeout varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfPackageCStateLimit`;
CREATE TABLE biosVfPackageCStateLimit (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpPackageCStateLimit varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfPCISlotLinkSpeed`;
CREATE TABLE biosVfPCISlotLinkSpeed (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpPCIeSlot10LinkSpeed varchar(255),
vpPCIeSlot1LinkSpeed varchar(255),
vpPCIeSlot2LinkSpeed varchar(255),
vpPCIeSlot3LinkSpeed varchar(255),
vpPCIeSlot4LinkSpeed varchar(255),
vpPCIeSlot5LinkSpeed varchar(255),
vpPCIeSlot6LinkSpeed varchar(255),
vpPCIeSlot7LinkSpeed varchar(255),
vpPCIeSlot8LinkSpeed varchar(255),
vpPCIeSlot9LinkSpeed varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfPCISlotOptionROMEnable`;
CREATE TABLE biosVfPCISlotOptionROMEnable (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpPCIeSlotSASOptionROM varchar(255),
vpSlot10State varchar(255),
vpSlot1State varchar(255),
vpSlot2State varchar(255),
vpSlot3State varchar(255),
vpSlot4State varchar(255),
vpSlot5State varchar(255),
vpSlot6State varchar(255),
vpSlot7State varchar(255),
vpSlot8State varchar(255),
vpSlot9State varchar(255),
vpSlotMezzState varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfPOSTErrorPause`;
CREATE TABLE biosVfPOSTErrorPause (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpPOSTErrorPause varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfProcessorC1E`;
CREATE TABLE biosVfProcessorC1E (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpProcessorC1E varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfProcessorC3Report`;
CREATE TABLE biosVfProcessorC3Report (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpProcessorC3Report varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfProcessorC6Report`;
CREATE TABLE biosVfProcessorC6Report (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpProcessorC6Report varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfProcessorC7Report`;
CREATE TABLE biosVfProcessorC7Report (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpProcessorC7Report varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfProcessorCState`;
CREATE TABLE biosVfProcessorCState (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpProcessorCState varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfProcessorEnergyConfiguration`;
CREATE TABLE biosVfProcessorEnergyConfiguration (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpEnergyPerformance varchar(255),
vpPowerTechnology varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfProcessorPrefetchConfig`;
CREATE TABLE biosVfProcessorPrefetchConfig (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpAdjacentCacheLinePrefetcher varchar(255),
vpDCUIPPrefetcher varchar(255),
vpDCUStreamerPrefetch varchar(255),
vpHardwarePrefetcher varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfPSTATECoordination`;
CREATE TABLE biosVfPSTATECoordination (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpPSTATECoordination varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfQPILinkFrequencySelect`;
CREATE TABLE biosVfQPILinkFrequencySelect (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpQPILinkFrequencySelect varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfQuietBoot`;
CREATE TABLE biosVfQuietBoot (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpQuietBoot varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfResumeOnACPowerLoss`;
CREATE TABLE biosVfResumeOnACPowerLoss (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpResumeOnACPowerLoss varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfScrubPolicies`;
CREATE TABLE biosVfScrubPolicies (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpDemandScrub varchar(255),
vpPatrolScrub varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfSelectMemoryRASConfiguration`;
CREATE TABLE biosVfSelectMemoryRASConfiguration (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpSelectMemoryRASConfiguration varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfSerialPortAEnable`;
CREATE TABLE biosVfSerialPortAEnable (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpSerialPortAEnable varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfSparingMode`;
CREATE TABLE biosVfSparingMode (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpSparingMode varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfSriovConfig`;
CREATE TABLE biosVfSriovConfig (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpSriov varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfUCSMBootModeControl`;
CREATE TABLE biosVfUCSMBootModeControl (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpUEFIBootMode varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfUCSMBootOrderRuleControl`;
CREATE TABLE biosVfUCSMBootOrderRuleControl (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpUCSMBootOrderRule varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfUEFIOSUseLegacyVideo`;
CREATE TABLE biosVfUEFIOSUseLegacyVideo (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpUEFIOSUseLegacyVideo varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfUSBBootConfig`;
CREATE TABLE biosVfUSBBootConfig (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpLegacyUSBSupport varchar(255),
vpMakeDeviceNonBootable varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfUSBFrontPanelAccessLock`;
CREATE TABLE biosVfUSBFrontPanelAccessLock (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpUSBFrontPanelLock varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfUSBPortConfiguration`;
CREATE TABLE biosVfUSBPortConfiguration (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpPort6064Emulation varchar(255),
vpUSBPortFront varchar(255),
vpUSBPortInternal varchar(255),
vpUSBPortKVM varchar(255),
vpUSBPortRear varchar(255),
vpUSBPortSDCard varchar(255),
vpUSBPortVMedia varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfUSBSystemIdlePowerOptimizingSetting`;
CREATE TABLE biosVfUSBSystemIdlePowerOptimizingSetting (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpUSBIdlePowerOptimizing varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVfVGAPriority`;
CREATE TABLE biosVfVGAPriority (
PrimaryKey varchar(255),
FK_biosVProfile varchar(255),
childAction varchar(255),
rn varchar(255),
vpVGAPriority varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `biosVProfile`;
CREATE TABLE biosVProfile (
PrimaryKey varchar(255),
childAction varchar(255),
descr varchar(255),
dn varchar(255),
intId varchar(255),
name varchar(255),
policyLevel varchar(255),
policyOwner varchar(255),
rebootOnUpdate varchar(255),
FK_orgOrg varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `lsmaintMaintPolicy`;
CREATE TABLE lsmaintMaintPolicy (
PrimaryKey varchar(255),
descr varchar(255),
name varchar(255),
policyOwner varchar(255),
schedName varchar(255),
uptimeDisr varchar(255),
FK_orgOrg varchar(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ;

DROP TABLE IF EXISTS `adaptorhostethifprofile`;
CREATE TABLE `adaptorhostethifprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `FK_orgOrg` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorextipv6rsshashprofile`;
CREATE TABLE `adaptorextipv6rsshashprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptoripv6rsshashprofile`;
CREATE TABLE `adaptoripv6rsshashprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptoripv4rsshashprofile`;
CREATE TABLE `adaptoripv4rsshashprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorethfailoverprofile`;
CREATE TABLE `adaptorethfailoverprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `timeout` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorethoffloadprofile`;
CREATE TABLE `adaptorethoffloadprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `largeReceive` varchar(255) DEFAULT NULL,
  `tcpRxChecksum` varchar(255) DEFAULT NULL,
  `tcpSegment` varchar(255) DEFAULT NULL,
  `tcpTxChecksum` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorethworkqueueprofile`;
CREATE TABLE `adaptorethworkqueueprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `count` varchar(255) DEFAULT NULL,
  `ringSize` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorethcompqueueprofile`;
CREATE TABLE `adaptorethcompqueueprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `count` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorethrecvqueueprofile`;
CREATE TABLE `adaptorethrecvqueueprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `count` varchar(255) DEFAULT NULL,
  `ringSize` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptoretharfsprofile`;
CREATE TABLE `adaptoretharfsprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `accelaratedRFS` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorethinterruptprofile`;
CREATE TABLE `adaptorethinterruptprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `coalescingTime` varchar(255) DEFAULT NULL,
  `coalescingType` varchar(255) DEFAULT NULL,
  `count` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `adaptorrssprofile`;
CREATE TABLE `adaptorrssprofile` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `receiveSideScaling` varchar(255) DEFAULT NULL,
  `FK_adaptorhostethifprofile` INT NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vniclanconnpolicy`;
CREATE TABLE `vniclanconnpolicy` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `FK_orgOrg` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhomeep`;
CREATE TABLE `callhomeep` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `adminState` varchar(255) DEFAULT NULL,
  `alertThrottlingAdminState` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `FK_topRoot` INT DEFAULT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhomepolicy`;
CREATE TABLE `callhomepolicy` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `adminState` varchar(255) DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `FK_callhomeep` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhomeperiodicsysteminventory`;
CREATE TABLE `callhomeperiodicsysteminventory` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `adminState` varchar(255) DEFAULT NULL,
  `intervalDays` varchar(255) DEFAULT NULL,
  `maximumRetryCount` varchar(255) DEFAULT NULL,
  `minimumSendNowIntervalSeconds` varchar(255) DEFAULT NULL,
  `pollIntervalSeconds` varchar(255) DEFAULT NULL,
  `retryDelayMinutes` varchar(255) DEFAULT NULL,
  `sendNow` varchar(255) DEFAULT NULL,
  `timeOfDayHour` varchar(255) DEFAULT NULL,
  `timeOfDayMinute` varchar(255) DEFAULT NULL,
  `FK_callhomeep` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhomeprofile`;
CREATE TABLE `callhomeprofile` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `alertGroups` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `format` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `maxSize` varchar(255) DEFAULT NULL,
  `FK_callhomeep` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhomedest`;
CREATE TABLE `callhomedest` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `FK_callhomeprofile` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhometestalert`;
CREATE TABLE `callhometestalert` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `group` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `messageSubtype` varchar(255) DEFAULT NULL,
  `messageType` varchar(255) DEFAULT NULL,
  `sendNow` varchar(255) DEFAULT NULL,
  `FK_callhomeep` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhomesource`;
CREATE TABLE `callhomesource` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `addr` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `contract` varchar(255) DEFAULT NULL,
  `customer` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `from` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `replyTo` varchar(255) DEFAULT NULL,
  `site` varchar(255) DEFAULT NULL,
  `urgency` varchar(255) DEFAULT NULL,
  `FK_callhomeep` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `callhomesmtp`;
CREATE TABLE `callhomesmtp` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `FK_callhomeep` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `mgmtbackuppolicy`;
CREATE TABLE `mgmtbackuppolicy` (
  `PrimaryKey` int(11) NOT NULL AUTO_INCREMENT,
  `adminState` varchar(255) DEFAULT NULL,
  `descr` varchar(255) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `policyOwner` varchar(255) DEFAULT NULL,
  `proto` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `remoteFile` varchar(255) DEFAULT NULL,
  `schedule` varchar(255) DEFAULT NULL,
  `user` varchar(255) DEFAULT NULL,
  `FK_orgOrg` INT NOT NULL,
  PRIMARY KEY (`PrimaryKey`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- UCS Mini Support for Scalability ports 4.0
DROP TABLE IF EXISTS `fabricsubgroup`;
CREATE TABLE `fabricsubgroup` (
  `PrimaryKey` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `slotId` varchar(255) DEFAULT NULL,
  `aggrPortId` varchar(255) DEFAULT NULL,
  `FK_fabricDceSwSrv` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `aaaauthrealm` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaaauthrealm` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT ;
ALTER TABLE `aaadomain` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaadomain` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `aaadomainauth` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaadomainauth` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfccdbworkqueueprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfccdbworkqueueprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfcerrorrecoveryprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfcerrorrecoveryprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfcinterruptprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfcinterruptprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfcportflogiprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfcportflogiprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfcportplogiprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfcportplogiprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfcportprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfcportprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfcrecvqueueprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfcrecvqueueprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorfcworkqueueprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorfcworkqueueprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorhostfcifprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorhostfcifprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `commdatetime` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `commdatetime` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `commdns` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `commdns` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `commdnsprovider` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `commdnsprovider` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `commntpprovider` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `commntpprovider` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `commsvcep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `commsvcep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `computechassisqual` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `computechassisqual` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `computepool` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `computepool` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `computepoolingpolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `computepoolingpolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `computepsupolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `computepsupolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `computequal` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `computequal` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `computeslotqual` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `computeslotqual` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `dpsecmac` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `dpsecmac` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `epqosdefinition` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `epqosdefinition` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `epqosegress` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `epqosegress` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricbhvlan` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricbhvlan` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricdcesrv` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricdcesrv` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricdceswsrv` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricdceswsrv` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricdceswsrvep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricdceswsrvep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricethestc` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricethestc` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricethestccloud` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricethestccloud` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricethlan` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricethlan` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricethlanep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricethlanep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricethlanpc` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricethlanpc` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricethlanpcep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricethlanpcep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricethlinkprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricethlinkprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricfcestc` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricfcestc` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricfcestccloud` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricfcestccloud` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricfcsan` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricfcsan` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricfcsanep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricfcsanep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricfcvsanportep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricfcvsanportep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabriclancloud` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabriclancloud` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricsancloud` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricsancloud` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricudldlinkpolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricudldlinkpolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricvcon` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricvcon` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricvlan` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricvlan` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricvsan` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricvsan` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fcpoolblock` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fcpoolblock` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fcpoolinitiator` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fcpoolinitiator` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fcpoolinitiators` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fcpoolinitiators` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `flowctrldefinition` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `flowctrldefinition` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `flowctrlitem` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `flowctrlitem` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `ippoolblock` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `ippoolblock` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `ippoolpool` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `ippoolpool` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootbootsecurity` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootbootsecurity` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootlan` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootlan` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootlanimagepath` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootlanimagepath` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootlocalhddimage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootlocalhddimage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootlocalstorage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootlocalstorage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootpolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootpolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootsan` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootsan` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootsancatsanimage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootsancatsanimage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootsancatsanimagepath` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootsancatsanimagepath` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootstorage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootstorage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootusbexternalimage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootusbexternalimage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootusbflashstorageimage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootusbflashstorageimage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootusbinternalimage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootusbinternalimage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lspower` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lspower` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsrequirement` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsrequirement` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsserver` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsserver` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsserverextension` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsserverextension` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsvconassign` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsvconassign` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsversionbeh` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsversionbeh` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `macpoolblock` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `macpoolblock` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `macpoolpool` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `macpoolpool` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `mgmtinbandprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `mgmtinbandprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `nwctrldefinition` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `nwctrldefinition` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `orgorg` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `orgorg` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `qosclassdefinition` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `qosclassdefinition` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `qosclassethbe` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `qosclassethbe` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `qosclassethclassified` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `qosclassethclassified` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `qosclassfc` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `qosclassfc` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `statsthresholdpolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `statsthresholdpolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `storagelocaldiskconfigpolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `storagelocaldiskconfigpolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `toproot` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `toproot` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `topsystem` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `topsystem` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `uuidpoolblock` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `uuidpoolblock` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `uuidpoolpool` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `uuidpoolpool` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicconndef` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicconndef` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicether` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicether` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicetherif` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicetherif` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicfc` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicfc` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicfcif` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicfcif` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicfcnode` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicfcnode` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vniclanconntempl` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vniclanconntempl` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicsanconnpolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicsanconnpolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vnicsanconntempl` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vnicsanconntempl` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `lsbootdefaultlocalimage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsbootdefaultlocalimage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `aaaldapep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaaldapep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT ;
ALTER TABLE `aaaldapgrouprule` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaaldapgrouprule` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT ;
ALTER TABLE `aaaldapprovider` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaaldapprovider` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT ;
ALTER TABLE `aaaprovidergroup` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaaprovidergroup` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT ;
ALTER TABLE `aaaproviderref` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `aaaproviderref` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT ;
-- UCS Mini Support for Scalability ports 4.0
ALTER TABLE `fabricsubgroup` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricsubgroup` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;


ALTER TABLE `aaaauthrealm` CHANGE COLUMN `FK_topSystem` `FK_topSystem` INT NOT NULL  ;
ALTER TABLE `aaaauthrealm` ADD CONSTRAINT `topsystem_fk_0` FOREIGN KEY(`FK_topSystem`) REFERENCES `topsystem` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaadomain` CHANGE COLUMN `FK_aaaAuthRealm` `FK_aaaAuthRealm` INT NOT NULL  ;
ALTER TABLE `aaadomain` ADD CONSTRAINT `aaaauthrealm_fk_1` FOREIGN KEY(`FK_aaaAuthRealm`) REFERENCES `aaaauthrealm` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaadomainauth` CHANGE COLUMN `FK_aaaDomain` `FK_aaaDomain` INT NOT NULL  ;
ALTER TABLE `aaadomainauth` ADD CONSTRAINT `aaadomain_fk_2` FOREIGN KEY(`FK_aaaDomain`) REFERENCES `aaadomain` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfccdbworkqueueprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfccdbworkqueueprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_3` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfcerrorrecoveryprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfcerrorrecoveryprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_4` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfcinterruptprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfcinterruptprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_5` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfcportflogiprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfcportflogiprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_6` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfcportplogiprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfcportplogiprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_7` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfcportprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfcportprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_8` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfcrecvqueueprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfcrecvqueueprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_9` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorfcworkqueueprofile` CHANGE COLUMN `FK_adaptorHostFcIfProfile` `FK_adaptorHostFcIfProfile` INT NOT NULL  ;
ALTER TABLE `adaptorfcworkqueueprofile` ADD CONSTRAINT `adaptorhostfcifprofile_fk_10` FOREIGN KEY(`FK_adaptorHostFcIfProfile`) REFERENCES `adaptorhostfcifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorhostfcifprofile` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `adaptorhostfcifprofile` ADD CONSTRAINT `orgorg_fk_11` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `commdatetime` CHANGE COLUMN `FK_commSvcEp` `FK_commSvcEp` INT NOT NULL  ;
ALTER TABLE `commdatetime` ADD CONSTRAINT `commsvcep_fk_12` FOREIGN KEY(`FK_commSvcEp`) REFERENCES `commsvcep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `commdns` CHANGE COLUMN `FK_commSvcEp` `FK_commSvcEp` INT NOT NULL  ;
ALTER TABLE `commdns` ADD CONSTRAINT `commsvcep_fk_13` FOREIGN KEY(`FK_commSvcEp`) REFERENCES `commsvcep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `commdnsprovider` CHANGE COLUMN `FK_commDns` `FK_commDns` INT NOT NULL  ;
ALTER TABLE `commdnsprovider` ADD CONSTRAINT `commdns_fk_14` FOREIGN KEY(`FK_commDns`) REFERENCES `commdns` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `commntpprovider` CHANGE COLUMN `FK_commDateTime` `FK_commDateTime` INT NOT NULL  ;
ALTER TABLE `commntpprovider` ADD CONSTRAINT `commdatetime_fk_15` FOREIGN KEY(`FK_commDateTime`) REFERENCES `commdatetime` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `commsvcep` CHANGE COLUMN `FK_topSystem` `FK_topSystem` INT NOT NULL  ;
ALTER TABLE `commsvcep` ADD CONSTRAINT `topsystem_fk_16` FOREIGN KEY(`FK_topSystem`) REFERENCES `topsystem` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `computechassisqual` CHANGE COLUMN `FK_computeQual` `FK_computeQual` INT NOT NULL  ;
ALTER TABLE `computechassisqual` ADD CONSTRAINT `computequal_fk_17` FOREIGN KEY(`FK_computeQual`) REFERENCES `computequal` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `computepool` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `computepool` ADD CONSTRAINT `orgorg_fk_18` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `computepoolingpolicy` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `computepoolingpolicy` ADD CONSTRAINT `orgorg_fk_19` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `computepsupolicy` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `computepsupolicy` ADD CONSTRAINT `orgorg_fk_20` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `computequal` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `computequal` ADD CONSTRAINT `orgorg_fk_21` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `computeslotqual` CHANGE COLUMN `FK_computeChassisQual` `FK_computeChassisQual` INT NOT NULL  ;
ALTER TABLE `computeslotqual` ADD CONSTRAINT `computechassisqual_fk_22` FOREIGN KEY(`FK_computeChassisQual`) REFERENCES `computechassisqual` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `dpsecmac` CHANGE COLUMN `FK_nwctrlDefinition` `FK_nwctrlDefinition` INT NOT NULL  ;
ALTER TABLE `dpsecmac` ADD CONSTRAINT `nwctrldefinition_fk_23` FOREIGN KEY(`FK_nwctrlDefinition`) REFERENCES `nwctrldefinition` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `epqosdefinition` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `epqosdefinition` ADD CONSTRAINT `orgorg_fk_24` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `epqosegress` CHANGE COLUMN `FK_epqosDefinition` `FK_epqosDefinition` INT NOT NULL  ;
ALTER TABLE `epqosegress` ADD CONSTRAINT `epqosdefinition_fk_25` FOREIGN KEY(`FK_epqosDefinition`) REFERENCES `epqosdefinition` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricbhvlan` CHANGE COLUMN `FK_fabricFcEstcCloud` `FK_fabricFcEstcCloud` INT NOT NULL  ;
ALTER TABLE `fabricbhvlan` ADD CONSTRAINT `fabricfcestccloud_fk_26` FOREIGN KEY(`FK_fabricFcEstcCloud`) REFERENCES `fabricfcestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricdcesrv` CHANGE COLUMN `FK_fabricEp` `FK_fabricEp` INT NOT NULL  ;
ALTER TABLE `fabricdcesrv` ADD CONSTRAINT `fabricep_fk_27` FOREIGN KEY(`FK_fabricEp`) REFERENCES `fabricep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricdceswsrv` CHANGE COLUMN `FK_fabricDceSrv` `FK_fabricDceSrv` INT NOT NULL  ;
ALTER TABLE `fabricdceswsrv` ADD CONSTRAINT `fabricdcesrv_fk_28` FOREIGN KEY(`FK_fabricDceSrv`) REFERENCES `fabricdcesrv` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricdceswsrvep` CHANGE COLUMN `FK_fabricDceSwSrv` `FK_fabricDceSwSrv` INT NOT NULL  ;
ALTER TABLE `fabricdceswsrvep` ADD CONSTRAINT `fabricdceswsrv_fk_29` FOREIGN KEY(`FK_fabricDceSwSrv`) REFERENCES `fabricdceswsrv` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricep` CHANGE COLUMN `FK_topRoot` `FK_topRoot` INT NOT NULL  ;
ALTER TABLE `fabricep` ADD CONSTRAINT `toproot_fk_30` FOREIGN KEY(`FK_topRoot`) REFERENCES `toproot` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricethestc` CHANGE COLUMN `FK_fabricEthEstcCloud` `FK_fabricEthEstcCloud` INT NOT NULL  ;
ALTER TABLE `fabricethestc` ADD CONSTRAINT `fabricethestccloud_fk_31` FOREIGN KEY(`FK_fabricEthEstcCloud`) REFERENCES `fabricethestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricethestccloud` CHANGE COLUMN `FK_fabricEp` `FK_fabricEp` INT NOT NULL  ;
ALTER TABLE `fabricethestccloud` ADD CONSTRAINT `fabricep_fk_32` FOREIGN KEY(`FK_fabricEp`) REFERENCES `fabricep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricethlan` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT NOT NULL  ;
ALTER TABLE `fabricethlan` ADD CONSTRAINT `fabriclancloud_fk_33` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricethlanep` CHANGE COLUMN `FK_fabricEthLan` `FK_fabricEthLan` INT NOT NULL  ;
ALTER TABLE `fabricethlanep` ADD CONSTRAINT `fabricethlan_fk_34` FOREIGN KEY(`FK_fabricEthLan`) REFERENCES `fabricethlan` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricethlanpc` CHANGE COLUMN `FK_fabricEthLan` `FK_fabricEthLan` INT NOT NULL  ;
ALTER TABLE `fabricethlanpc` ADD CONSTRAINT `fabricethlan_fk_35` FOREIGN KEY(`FK_fabricEthLan`) REFERENCES `fabricethlan` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricethlanpcep` CHANGE COLUMN `FK_fabricEthLanPc` `FK_fabricEthLanPc` INT NOT NULL  ;
ALTER TABLE `fabricethlanpcep` ADD CONSTRAINT `fabricethlanpc_fk_36` FOREIGN KEY(`FK_fabricEthLanPc`) REFERENCES `fabricethlanpc` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricethlinkprofile` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT NOT NULL  ;
ALTER TABLE `fabricethlinkprofile` ADD CONSTRAINT `fabriclancloud_fk_37` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricfcestc` CHANGE COLUMN `FK_fabricFcEstcCloud` `FK_fabricFcEstcCloud` INT NOT NULL  ;
ALTER TABLE `fabricfcestc` ADD CONSTRAINT `fabricfcestccloud_fk_38` FOREIGN KEY(`FK_fabricFcEstcCloud`) REFERENCES `fabricfcestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricfcestccloud` CHANGE COLUMN `FK_fabricEp` `FK_fabricEp` INT NOT NULL  ;
ALTER TABLE `fabricfcestccloud` ADD CONSTRAINT `fabricep_fk_39` FOREIGN KEY(`FK_fabricEp`) REFERENCES `fabricep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricfcsan` CHANGE COLUMN `FK_fabricSanCloud` `FK_fabricSanCloud` INT NOT NULL  ;
ALTER TABLE `fabricfcsan` ADD CONSTRAINT `fabricsancloud_fk_40` FOREIGN KEY(`FK_fabricSanCloud`) REFERENCES `fabricsancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricfcsanep` CHANGE COLUMN `FK_fabricFcSan` `FK_fabricFcSan` INT NOT NULL  ;
ALTER TABLE `fabricfcsanep` ADD CONSTRAINT `fabricfcsan_fk_41` FOREIGN KEY(`FK_fabricFcSan`) REFERENCES `fabricfcsan` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricfcvsanportep` CHANGE COLUMN `FK_fabricVsan` `FK_fabricVsan` INT NOT NULL  ;
ALTER TABLE `fabricfcvsanportep` ADD CONSTRAINT `fabricvsan_fk_42` FOREIGN KEY(`FK_fabricVsan`) REFERENCES `fabricvsan` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabriclancloud` CHANGE COLUMN `FK_fabricEp` `FK_fabricEp` INT NOT NULL  ;
ALTER TABLE `fabriclancloud` ADD CONSTRAINT `fabricep_fk_43` FOREIGN KEY(`FK_fabricEp`) REFERENCES `fabricep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricsancloud` CHANGE COLUMN `FK_fabricEp` `FK_fabricEp` INT NOT NULL  ;
ALTER TABLE `fabricsancloud` ADD CONSTRAINT `fabricep_fk_44` FOREIGN KEY(`FK_fabricEp`) REFERENCES `fabricep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricudldlinkpolicy` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT NOT NULL  ;
ALTER TABLE `fabricudldlinkpolicy` ADD CONSTRAINT `fabriclancloud_fk_45` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricvcon` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT NOT NULL  ;
ALTER TABLE `fabricvcon` ADD CONSTRAINT `lsserver_fk_46` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricvlan` CHANGE COLUMN `FK_fabricEthEstcCloud` `FK_fabricEthEstcCloud` INT DEFAULT NULL  ;
ALTER TABLE `fabricvlan` ADD CONSTRAINT `fabricethestccloud_fk_47` FOREIGN KEY(`FK_fabricEthEstcCloud`) REFERENCES `fabricethestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricvlan` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT DEFAULT NULL  ;
ALTER TABLE `fabricvlan` ADD CONSTRAINT `fabriclancloud_fk_48` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricvsan` CHANGE COLUMN `FK_fabricFcEstcCloud` `FK_fabricFcEstcCloud` INT DEFAULT NULL  ;
ALTER TABLE `fabricvsan` ADD CONSTRAINT `fabricfcestccloud_fk_49` FOREIGN KEY(`FK_fabricFcEstcCloud`) REFERENCES `fabricfcestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricvsan` CHANGE COLUMN `FK_fabricFcSan` `FK_fabricFcSan` INT DEFAULT NULL  ;
ALTER TABLE `fabricvsan` ADD CONSTRAINT `fabricfcsan_fk_50` FOREIGN KEY(`FK_fabricFcSan`) REFERENCES `fabricfcsan` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricvsan` CHANGE COLUMN `FK_fabricSanCloud` `FK_fabricSanCloud` INT DEFAULT NULL  ;
ALTER TABLE `fabricvsan` ADD CONSTRAINT `fabricsancloud_fk_51` FOREIGN KEY(`FK_fabricSanCloud`) REFERENCES `fabricsancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fcpoolblock` CHANGE COLUMN `FK_fcpoolInitiators` `FK_fcpoolInitiators` INT NOT NULL  ;
ALTER TABLE `fcpoolblock` ADD CONSTRAINT `fcpoolinitiators_fk_52` FOREIGN KEY(`FK_fcpoolInitiators`) REFERENCES `fcpoolinitiators` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fcpoolinitiator` CHANGE COLUMN `FK_fcpoolInitiators` `FK_fcpoolInitiators` INT NOT NULL  ;
ALTER TABLE `fcpoolinitiator` ADD CONSTRAINT `fcpoolinitiators_fk_53` FOREIGN KEY(`FK_fcpoolInitiators`) REFERENCES `fcpoolinitiators` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fcpoolinitiators` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `fcpoolinitiators` ADD CONSTRAINT `orgorg_fk_54` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `flowctrldefinition` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT NOT NULL  ;
ALTER TABLE `flowctrldefinition` ADD CONSTRAINT `fabriclancloud_fk_55` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `flowctrlitem` CHANGE COLUMN `FK_flowctrlDefinition` `FK_flowctrlDefinition` INT NOT NULL  ;
ALTER TABLE `flowctrlitem` ADD CONSTRAINT `flowctrldefinition_fk_56` FOREIGN KEY(`FK_flowctrlDefinition`) REFERENCES `flowctrldefinition` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `ippoolblock` CHANGE COLUMN `FK_ippoolPool` `FK_ippoolPool` INT NOT NULL  ;
ALTER TABLE `ippoolblock` ADD CONSTRAINT `ippoolpool_fk_57` FOREIGN KEY(`FK_ippoolPool`) REFERENCES `ippoolpool` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `ippoolpool` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `ippoolpool` ADD CONSTRAINT `orgorg_fk_58` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootbootsecurity` CHANGE COLUMN `FK_lsbootPolicy` `FK_lsbootPolicy` INT NOT NULL  ;
ALTER TABLE `lsbootbootsecurity` ADD CONSTRAINT `lsbootpolicy_fk_59` FOREIGN KEY(`FK_lsbootPolicy`) REFERENCES `lsbootpolicy` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootlan` CHANGE COLUMN `FK_lsbootPolicy` `FK_lsbootPolicy` INT NOT NULL  ;
ALTER TABLE `lsbootlan` ADD CONSTRAINT `lsbootpolicy_fk_60` FOREIGN KEY(`FK_lsbootPolicy`) REFERENCES `lsbootpolicy` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootlanimagepath` CHANGE COLUMN `FK_lsbootLan` `FK_lsbootLan` INT NOT NULL  ;
ALTER TABLE `lsbootlanimagepath` ADD CONSTRAINT `lsbootlan_fk_61` FOREIGN KEY(`FK_lsbootLan`) REFERENCES `lsbootlan` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootlocalhddimage` CHANGE COLUMN `FK_lsbootLocalStorage` `FK_lsbootLocalStorage` INT NOT NULL  ;
ALTER TABLE `lsbootlocalhddimage` ADD CONSTRAINT `lsbootlocalstorage_fk_62` FOREIGN KEY(`FK_lsbootLocalStorage`) REFERENCES `lsbootlocalstorage` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootlocalstorage` CHANGE COLUMN `FK_lsbootStorage` `FK_lsbootStorage` INT NOT NULL  ;
ALTER TABLE `lsbootlocalstorage` ADD CONSTRAINT `lsbootstorage_fk_63` FOREIGN KEY(`FK_lsbootStorage`) REFERENCES `lsbootstorage` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootpolicy` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `lsbootpolicy` ADD CONSTRAINT `orgorg_fk_64` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootsan` CHANGE COLUMN `FK_lsbootPolicy` `FK_lsbootPolicy` INT NOT NULL  ;
ALTER TABLE `lsbootsan` ADD CONSTRAINT `lsbootpolicy_fk_65` FOREIGN KEY(`FK_lsbootPolicy`) REFERENCES `lsbootpolicy` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootsancatsanimage` CHANGE COLUMN `FK_lsbootSan` `FK_lsbootSan` INT NOT NULL  ;
ALTER TABLE `lsbootsancatsanimage` ADD CONSTRAINT `lsbootsan_fk_66` FOREIGN KEY(`FK_lsbootSan`) REFERENCES `lsbootsan` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootsancatsanimagepath` CHANGE COLUMN `FK_lsbootSanCatSanImage` `FK_lsbootSanCatSanImage` INT NOT NULL  ;
ALTER TABLE `lsbootsancatsanimagepath` ADD CONSTRAINT `lsbootsancatsanimage_fk_67` FOREIGN KEY(`FK_lsbootSanCatSanImage`) REFERENCES `lsbootsancatsanimage` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootstorage` CHANGE COLUMN `FK_lsbootPolicy` `FK_lsbootPolicy` INT NOT NULL  ;
ALTER TABLE `lsbootstorage` ADD CONSTRAINT `lsbootpolicy_fk_68` FOREIGN KEY(`FK_lsbootPolicy`) REFERENCES `lsbootpolicy` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootusbexternalimage` CHANGE COLUMN `FK_lsbootLocalStorage` `FK_lsbootLocalStorage` INT NOT NULL  ;
ALTER TABLE `lsbootusbexternalimage` ADD CONSTRAINT `lsbootlocalstorage_fk_69` FOREIGN KEY(`FK_lsbootLocalStorage`) REFERENCES `lsbootlocalstorage` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootusbflashstorageimage` CHANGE COLUMN `FK_lsbootLocalStorage` `FK_lsbootLocalStorage` INT NOT NULL  ;
ALTER TABLE `lsbootusbflashstorageimage` ADD CONSTRAINT `lsbootlocalstorage_fk_70` FOREIGN KEY(`FK_lsbootLocalStorage`) REFERENCES `lsbootlocalstorage` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootusbinternalimage` CHANGE COLUMN `FK_lsbootLocalStorage` `FK_lsbootLocalStorage` INT NOT NULL  ;
ALTER TABLE `lsbootusbinternalimage` ADD CONSTRAINT `lsbootlocalstorage_fk_71` FOREIGN KEY(`FK_lsbootLocalStorage`) REFERENCES `lsbootlocalstorage` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lspower` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT NOT NULL  ;
ALTER TABLE `lspower` ADD CONSTRAINT `lsserver_fk_72` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsrequirement` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT NOT NULL  ;
ALTER TABLE `lsrequirement` ADD CONSTRAINT `lsserver_fk_73` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsserver` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `lsserver` ADD CONSTRAINT `orgorg_fk_74` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsserverextension` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT NOT NULL  ;
ALTER TABLE `lsserverextension` ADD CONSTRAINT `lsserver_fk_75` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsvconassign` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT NOT NULL  ;
ALTER TABLE `lsvconassign` ADD CONSTRAINT `lsserver_fk_76` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsversionbeh` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT NOT NULL  ;
ALTER TABLE `lsversionbeh` ADD CONSTRAINT `lsserver_fk_77` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `macpoolblock` CHANGE COLUMN `FK_macpoolPool` `FK_macpoolPool` INT NOT NULL  ;
ALTER TABLE `macpoolblock` ADD CONSTRAINT `macpoolpool_fk_78` FOREIGN KEY(`FK_macpoolPool`) REFERENCES `macpoolpool` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `macpoolpool` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `macpoolpool` ADD CONSTRAINT `orgorg_fk_79` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `mgmtinbandprofile` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT NOT NULL  ;
ALTER TABLE `mgmtinbandprofile` ADD CONSTRAINT `fabriclancloud_fk_80` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `nwctrldefinition` CHANGE COLUMN `FK_fabricEthEstcCloud` `FK_fabricEthEstcCloud` INT DEFAULT NULL  ;
ALTER TABLE `nwctrldefinition` ADD CONSTRAINT `fabricethestccloud_fk_81` FOREIGN KEY(`FK_fabricEthEstcCloud`) REFERENCES `fabricethestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `nwctrldefinition` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT DEFAULT NULL  ;
ALTER TABLE `nwctrldefinition` ADD CONSTRAINT `orgorg_fk_82` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `orgorg` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT DEFAULT NULL  ;
ALTER TABLE `orgorg` ADD CONSTRAINT `orgorg_fk_83` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `orgorg` CHANGE COLUMN `FK_topRoot` `FK_topRoot` INT DEFAULT NULL  ;
ALTER TABLE `orgorg` ADD CONSTRAINT `toproot_fk_84` FOREIGN KEY(`FK_topRoot`) REFERENCES `toproot` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `qosclassdefinition` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT NOT NULL  ;
ALTER TABLE `qosclassdefinition` ADD CONSTRAINT `fabriclancloud_fk_85` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `qosclassethbe` CHANGE COLUMN `FK_qosclassDefinition` `FK_qosclassDefinition` INT NOT NULL  ;
ALTER TABLE `qosclassethbe` ADD CONSTRAINT `qosclassdefinition_fk_86` FOREIGN KEY(`FK_qosclassDefinition`) REFERENCES `qosclassdefinition` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `qosclassethclassified` CHANGE COLUMN `FK_qosclassDefinition` `FK_qosclassDefinition` INT NOT NULL  ;
ALTER TABLE `qosclassethclassified` ADD CONSTRAINT `qosclassdefinition_fk_87` FOREIGN KEY(`FK_qosclassDefinition`) REFERENCES `qosclassdefinition` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `qosclassfc` CHANGE COLUMN `FK_qosclassDefinition` `FK_qosclassDefinition` INT NOT NULL  ;
ALTER TABLE `qosclassfc` ADD CONSTRAINT `qosclassdefinition_fk_88` FOREIGN KEY(`FK_qosclassDefinition`) REFERENCES `qosclassdefinition` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `statsthresholdpolicy` CHANGE COLUMN `FK_fabricDceSrv` `FK_fabricDceSrv` INT DEFAULT NULL  ;
ALTER TABLE `statsthresholdpolicy` ADD CONSTRAINT `fabricdcesrv_fk_89` FOREIGN KEY(`FK_fabricDceSrv`) REFERENCES `fabricdcesrv` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `statsthresholdpolicy` CHANGE COLUMN `FK_fabricEthEstcCloud` `FK_fabricEthEstcCloud` INT DEFAULT NULL  ;
ALTER TABLE `statsthresholdpolicy` ADD CONSTRAINT `fabricethestccloud_fk_90` FOREIGN KEY(`FK_fabricEthEstcCloud`) REFERENCES `fabricethestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `statsthresholdpolicy` CHANGE COLUMN `FK_fabricFcEstcCloud` `FK_fabricFcEstcCloud` INT DEFAULT NULL  ;
ALTER TABLE `statsthresholdpolicy` ADD CONSTRAINT `fabricfcestccloud_fk_91` FOREIGN KEY(`FK_fabricFcEstcCloud`) REFERENCES `fabricfcestccloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `statsthresholdpolicy` CHANGE COLUMN `FK_fabricLanCloud` `FK_fabricLanCloud` INT DEFAULT NULL  ;
ALTER TABLE `statsthresholdpolicy` ADD CONSTRAINT `fabriclancloud_fk_92` FOREIGN KEY(`FK_fabricLanCloud`) REFERENCES `fabriclancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `statsthresholdpolicy` CHANGE COLUMN `FK_fabricSanCloud` `FK_fabricSanCloud` INT DEFAULT NULL  ;
ALTER TABLE `statsthresholdpolicy` ADD CONSTRAINT `fabricsancloud_fk_93` FOREIGN KEY(`FK_fabricSanCloud`) REFERENCES `fabricsancloud` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `storagelocaldiskconfigpolicy` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `storagelocaldiskconfigpolicy` ADD CONSTRAINT `orgorg_fk_94` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `topsystem` CHANGE COLUMN `FK_topRoot` `FK_topRoot` INT NOT NULL  ;
ALTER TABLE `topsystem` ADD CONSTRAINT `toproot_fk_95` FOREIGN KEY(`FK_topRoot`) REFERENCES `toproot` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `uuidpoolblock` CHANGE COLUMN `FK_uuidpoolPool` `FK_uuidpoolPool` INT NOT NULL  ;
ALTER TABLE `uuidpoolblock` ADD CONSTRAINT `uuidpoolpool_fk_96` FOREIGN KEY(`FK_uuidpoolPool`) REFERENCES `uuidpoolpool` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `uuidpoolpool` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `uuidpoolpool` ADD CONSTRAINT `orgorg_fk_97` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicconndef` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT NOT NULL  ;
ALTER TABLE `vnicconndef` ADD CONSTRAINT `lsserver_fk_98` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicether` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT DEFAULT NULL  ;   -- Can be null because same vnicether may exit under lanConnectivity Policy: vnicLanConnPolicy
ALTER TABLE `vnicether` ADD CONSTRAINT `lsserver_fk_99` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicetherif` CHANGE COLUMN `FK_vnicEther` `FK_vnicEther` INT DEFAULT NULL  ;
ALTER TABLE `vnicetherif` ADD CONSTRAINT `vnicether_fk_100` FOREIGN KEY(`FK_vnicEther`) REFERENCES `vnicether` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicetherif` CHANGE COLUMN `FK_vnicLanConnTempl` `FK_vnicLanConnTempl` INT DEFAULT NULL  ;
ALTER TABLE `vnicetherif` ADD CONSTRAINT `vniclanconntempl_fk_101` FOREIGN KEY(`FK_vnicLanConnTempl`) REFERENCES `vniclanconntempl` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicfc` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT DEFAULT NULL  ;
ALTER TABLE `vnicfc` ADD CONSTRAINT `lsserver_fk_102` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicfc` CHANGE COLUMN `FK_vnicSanConnPolicy` `FK_vnicSanConnPolicy` INT DEFAULT NULL  ;
ALTER TABLE `vnicfc` ADD CONSTRAINT `vnicsanconnpolicy_fk_103` FOREIGN KEY(`FK_vnicSanConnPolicy`) REFERENCES `vnicsanconnpolicy` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicfcif` CHANGE COLUMN `FK_vnicFc` `FK_vnicFc` INT DEFAULT NULL  ;
ALTER TABLE `vnicfcif` ADD CONSTRAINT `vnicfc_fk_104` FOREIGN KEY(`FK_vnicFc`) REFERENCES `vnicfc` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicfcif` CHANGE COLUMN `FK_vnicSanConnTempl` `FK_vnicSanConnTempl` INT DEFAULT NULL ;
ALTER TABLE `vnicfcif` ADD CONSTRAINT `vnicsanconntempl_fk_105` FOREIGN KEY(`FK_vnicSanConnTempl`) REFERENCES `vnicsanconntempl` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicfcnode` CHANGE COLUMN `FK_lsServer` `FK_lsServer` INT DEFAULT NULL  ;
ALTER TABLE `vnicfcnode` ADD CONSTRAINT `lsserver_fk_106` FOREIGN KEY(`FK_lsServer`) REFERENCES `lsserver` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicfcnode` CHANGE COLUMN `FK_vnicSanConnPolicy` `FK_vnicSanConnPolicy` INT DEFAULT NULL  ;
ALTER TABLE `vnicfcnode` ADD CONSTRAINT `vnicsanconnpolicy_fk_107` FOREIGN KEY(`FK_vnicSanConnPolicy`) REFERENCES `vnicsanconnpolicy` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vniclanconntempl` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `vniclanconntempl` ADD CONSTRAINT `orgorg_fk_108` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicsanconnpolicy` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `vnicsanconnpolicy` ADD CONSTRAINT `orgorg_fk_109` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicsanconntempl` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `vnicsanconntempl` ADD CONSTRAINT `orgorg_fk_110` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsbootdefaultlocalimage` CHANGE COLUMN `FK_lsbootLocalStorage` `FK_lsbootLocalStorage` INT NOT NULL  ;
ALTER TABLE `lsbootdefaultlocalimage` ADD CONSTRAINT `lsbootlocalstorage_fk_111` FOREIGN KEY(`FK_lsbootLocalStorage`) REFERENCES `lsbootlocalstorage` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaldapep` CHANGE COLUMN `FK_topSystem` `FK_topSystem` INT NOT NULL  ;
ALTER TABLE `aaaldapep` ADD CONSTRAINT `topsystem_fk_112` FOREIGN KEY(`FK_topSystem`) REFERENCES `topsystem` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaldapgrouprule` CHANGE COLUMN `FK_aaaLdapEp` `FK_aaaLdapEp` INT DEFAULT NULL  ;
ALTER TABLE `aaaldapgrouprule` ADD CONSTRAINT `aaaldapep_fk_113` FOREIGN KEY(`FK_aaaLdapEp`) REFERENCES `aaaldapep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaldapgrouprule` CHANGE COLUMN `FK_aaaLdapProvider` `FK_aaaLdapProvider` INT DEFAULT NULL  ;
ALTER TABLE `aaaldapgrouprule` ADD CONSTRAINT `aaaldapprovider_fk_114` FOREIGN KEY(`FK_aaaLdapProvider`) REFERENCES `aaaldapprovider` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaldapprovider` CHANGE COLUMN `FK_aaaLdapEp` `FK_aaaLdapEp` INT NOT NULL  ;
ALTER TABLE `aaaldapprovider` ADD CONSTRAINT `aaaldapep_fk_115` FOREIGN KEY(`FK_aaaLdapEp`) REFERENCES `aaaldapep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaprovidergroup` CHANGE COLUMN `FK_aaaLdapEp` `FK_aaaLdapEp` INT NOT NULL  ;
ALTER TABLE `aaaprovidergroup` ADD CONSTRAINT `aaaldapep_fk_116` FOREIGN KEY(`FK_aaaLdapEp`) REFERENCES `aaaldapep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaproviderref` CHANGE COLUMN `FK_aaaProviderGroup` `FK_aaaProviderGroup` INT NOT NULL  ;
ALTER TABLE `aaaproviderref` ADD CONSTRAINT `aaaprovidergroup_fk_117` FOREIGN KEY(`FK_aaaProviderGroup`) REFERENCES `aaaprovidergroup` (`PrimaryKey`) ON DELETE CASCADE;

ALTER TABLE `aaaprovidergroup` CHANGE COLUMN `FK_aaaLdapEp` `FK_aaaLdapEp` INT DEFAULT NULL ;
ALTER TABLE `aaaprovidergroup` ADD COLUMN `FK_aaaRadiusEp` INT DEFAULT NULL;
ALTER TABLE `aaaprovidergroup` ADD COLUMN `FK_aaaTacacsPlusEp` INT DEFAULT NULL;
ALTER TABLE `aaaUserEp` ADD CONSTRAINT `topsystem_fk_118` FOREIGN KEY(`FK_topSystem`) REFERENCES `topsystem` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaLocale` ADD CONSTRAINT `aaaUserEp_fk_119` FOREIGN KEY (`FK_aaaUserEp`) REFERENCES `aaaUserEp` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaOrg` ADD CONSTRAINT `aaaLocale_fk_120` FOREIGN KEY (`FK_aaaLocale`) REFERENCES `aaaLocale` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaLdapGroup` ADD CONSTRAINT `aaaldapep_fk_121` FOREIGN KEY (`FK_aaaldapep`) REFERENCES `aaaldapep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaUserLocale` ADD CONSTRAINT `aaaLdapGroup_fk_122` FOREIGN KEY (`FK_aaaLdapGroup`) REFERENCES `aaaLdapGroup` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaRole` ADD CONSTRAINT `aaaUserEp_fk_123` FOREIGN KEY (`FK_aaaUserEp`) REFERENCES `aaaUserEp` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaUserRole` ADD CONSTRAINT `aaaLdapGroup_fk_124` FOREIGN KEY (`FK_aaaLdapGroup`) REFERENCES `aaaLdapGroup` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaRadiusEp` ADD CONSTRAINT `topsystem_fk_125` FOREIGN KEY (`FK_topSystem`) REFERENCES `topsystem` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaRadiusProvider` ADD CONSTRAINT `aaaRadiusEp_fk_126` FOREIGN KEY (`FK_aaaRadiusEp`) REFERENCES `aaaRadiusEp` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaTacacsPlusEp` ADD CONSTRAINT `topsystem_fk_127` FOREIGN KEY (`FK_topSystem`) REFERENCES `topsystem` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaTacacsPlusProvider` ADD CONSTRAINT `aaaTacacsPlusEp_fk_128` FOREIGN KEY (`FK_aaaTacacsPlusEp`) REFERENCES `aaaTacacsPlusEp` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaprovidergroup` ADD CONSTRAINT `aaaRadiusEp_fk_129` FOREIGN KEY(`FK_aaaRadiusEp`) REFERENCES `aaaRadiusEp` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `aaaprovidergroup` ADD CONSTRAINT `aaaTacacsPlusEp_fk_130` FOREIGN KEY(`FK_aaaTacacsPlusEp`) REFERENCES `aaaTacacsPlusEp` (`PrimaryKey`) ON DELETE CASCADE;

ALTER TABLE `qosclassethclassified` CHANGE COLUMN `drop` `drop_drop` varchar(255) DEFAULT NULL  ;
ALTER TABLE `fcpoolblock` CHANGE COLUMN `to` `to_to` varchar(255) DEFAULT NULL  ;
ALTER TABLE `fcpoolblock` CHANGE COLUMN `from` `from_from` varchar(255) DEFAULT NULL  ;
ALTER TABLE `ippoolblock` CHANGE COLUMN `to` `to_to` varchar(255) DEFAULT NULL  ;
ALTER TABLE `ippoolblock` CHANGE COLUMN `from` `from_from` varchar(255) DEFAULT NULL  ;
ALTER TABLE `macpoolblock` CHANGE COLUMN `to` `to_to` varchar(255) DEFAULT NULL  ;
ALTER TABLE `macpoolblock` CHANGE COLUMN `from` `from_from` varchar(255) DEFAULT NULL  ;
ALTER TABLE `uuidpoolblock` CHANGE COLUMN `to` `to_to` varchar(255) DEFAULT NULL  ;
ALTER TABLE `uuidpoolblock` CHANGE COLUMN `from` `from_from` varchar(255) DEFAULT NULL  ;
ALTER TABLE `fabricvcon` CHANGE COLUMN `select` `select_select` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsvconassign` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootlan` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootlocalhddimage` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootsan` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootstorage` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootusbexternalimage` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootusbflashstorageimage` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootusbinternalimage` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `vnicether` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `vnicfc` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootlanimagepath` CHANGE COLUMN `type` `type_type` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootsancatsanimage` CHANGE COLUMN `type` `type_type` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootsancatsanimagepath` CHANGE COLUMN `type` `type_type` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsserver` CHANGE COLUMN `type` `type_type` varchar(255) DEFAULT NULL  ;
ALTER TABLE `adaptorfccdbworkqueueprofile` CHANGE COLUMN `count` `count_count` varchar(255) DEFAULT NULL  ;
ALTER TABLE `adaptorfcinterruptprofile` CHANGE COLUMN `mode` `mode_mode` varchar(255) DEFAULT NULL  ;
ALTER TABLE `fabriclancloud` CHANGE COLUMN `mode` `mode_mode` varchar(255) DEFAULT NULL  ;
ALTER TABLE `fabricsancloud` CHANGE COLUMN `mode` `mode_mode` varchar(255) DEFAULT NULL  ;
ALTER TABLE `fabricudldlinkpolicy` CHANGE COLUMN `mode` `mode_mode` varchar(255) DEFAULT NULL  ;
ALTER TABLE `storagelocaldiskconfigpolicy` CHANGE COLUMN `mode` `mode_mode` varchar(255) DEFAULT NULL  ;
ALTER TABLE `commdns` CHANGE COLUMN `domain` `domain_domain` varchar(255) DEFAULT NULL  ;
ALTER TABLE `fabricvcon` CHANGE COLUMN `share` `share_share` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lspower` CHANGE COLUMN `state` `state_state` varchar(255) DEFAULT NULL  ;
ALTER TABLE `qosclassethclassified` CHANGE COLUMN `cos` `cos_cos` varchar(255) DEFAULT NULL  ;
ALTER TABLE `qosclassfc` CHANGE COLUMN `cos` `cos_cos` varchar(255) DEFAULT NULL  ;
ALTER TABLE `uuidpoolpool` CHANGE COLUMN `prefix` `prefix_prefix` varchar(255) DEFAULT NULL  ;
ALTER TABLE `lsbootdefaultlocalimage` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `aaaldapgrouprule` CHANGE COLUMN `authorization` `authorization_authorization` varchar(255) DEFAULT NULL  ;
ALTER TABLE `aaaldapprovider` CHANGE COLUMN `key` `key_key` varchar(255) DEFAULT NULL  ;
ALTER TABLE `aaaldapprovider` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;
ALTER TABLE `aaaproviderref` CHANGE COLUMN `order` `order_order` varchar(255) DEFAULT NULL  ;

ALTER TABLE `firmwareComputeHostPack` ADD CONSTRAINT `orgorg_fk_131` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;

-- For BIOS Policy --
ALTER TABLE `biosVfACPI10Support` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfACPI10Support` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfAllUSBDevices` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfAllUSBDevices` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfAssertNMIOnPERR` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfAssertNMIOnPERR` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfAssertNMIOnSERR` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfAssertNMIOnSERR` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfBootOptionRetry` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfBootOptionRetry` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfConsoleRedirection` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfConsoleRedirection` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfCoreMultiProcessing` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfCoreMultiProcessing` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfCPUPerformance` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfCPUPerformance` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfDirectCacheAccess` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfDirectCacheAccess` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfDRAMClockThrottling` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfDRAMClockThrottling` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfDramRefreshRate` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfDramRefreshRate` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfEnhancedIntelSpeedStepTech` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfEnhancedIntelSpeedStepTech` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfExecuteDisableBit` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfExecuteDisableBit` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfFRB2Timer` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfFRB2Timer` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfFrequencyFloorOverride` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfFrequencyFloorOverride` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfFrontPanelLockout` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfFrontPanelLockout` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfIntelEntrySASRAIDModule` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfIntelEntrySASRAIDModule` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfIntelHyperThreadingTech` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfIntelHyperThreadingTech` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfIntelTurboBoostTech` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfIntelTurboBoostTech` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfIntelVirtualizationTechnology` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfIntelVirtualizationTechnology` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfIntelVTForDirectedIO` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfIntelVTForDirectedIO` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfInterleaveConfiguration` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfInterleaveConfiguration` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfLocalX2Apic` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfLocalX2Apic` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfLOMPortsConfiguration` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfLOMPortsConfiguration` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfLvDIMMSupport` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfLvDIMMSupport` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfMaximumMemoryBelow4GB` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfMaximumMemoryBelow4GB` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfMaxVariableMTRRSetting` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfMaxVariableMTRRSetting` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfMemoryMappedIOAbove4GB` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfMemoryMappedIOAbove4GB` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfMirroringMode` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfMirroringMode` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfNUMAOptimized` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfNUMAOptimized` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfOnboardSATAController` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfOnboardSATAController` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfOnboardStorage` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfOnboardStorage` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfOptionROMEnable` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfOptionROMEnable` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfOptionROMLoad` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfOptionROMLoad` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfOSBootWatchdogTimer` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfOSBootWatchdogTimer` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfOSBootWatchdogTimerPolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfOSBootWatchdogTimerPolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfOSBootWatchdogTimerTimeout` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfOSBootWatchdogTimerTimeout` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfPackageCStateLimit` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfPackageCStateLimit` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfPCISlotLinkSpeed` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfPCISlotLinkSpeed` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfPCISlotOptionROMEnable` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfPCISlotOptionROMEnable` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfPOSTErrorPause` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfPOSTErrorPause` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfProcessorC1E` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfProcessorC1E` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfProcessorC3Report` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfProcessorC3Report` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfProcessorC6Report` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfProcessorC6Report` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfProcessorC7Report` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfProcessorC7Report` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfProcessorCState` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfProcessorCState` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfProcessorEnergyConfiguration` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfProcessorEnergyConfiguration` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfProcessorPrefetchConfig` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfProcessorPrefetchConfig` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfPSTATECoordination` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfPSTATECoordination` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfQPILinkFrequencySelect` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfQPILinkFrequencySelect` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfQuietBoot` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfQuietBoot` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfResumeOnACPowerLoss` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfResumeOnACPowerLoss` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfScrubPolicies` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfScrubPolicies` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfSelectMemoryRASConfiguration` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfSelectMemoryRASConfiguration` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfSerialPortAEnable` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfSerialPortAEnable` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfSparingMode` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfSparingMode` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfSriovConfig` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfSriovConfig` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfUCSMBootModeControl` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfUCSMBootModeControl` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfUCSMBootOrderRuleControl` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfUCSMBootOrderRuleControl` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfUEFIOSUseLegacyVideo` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfUEFIOSUseLegacyVideo` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfUSBBootConfig` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfUSBBootConfig` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfUSBFrontPanelAccessLock` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfUSBFrontPanelAccessLock` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfUSBPortConfiguration` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfUSBPortConfiguration` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfUSBSystemIdlePowerOptimizingSetting` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfUSBSystemIdlePowerOptimizingSetting` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVfVGAPriority` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVfVGAPriority` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `biosVProfile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `biosVProfile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `lsmaintMaintPolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `lsmaintMaintPolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT;
ALTER TABLE `fabricdceswsrvpc` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricdceswsrvpc` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `fabricdceswsrvpcep` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `fabricdceswsrvpcep` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorhostethifprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorhostethifprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorextipv6rsshashprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorextipv6rsshashprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptoripv6rsshashprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptoripv6rsshashprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptoripv4rsshashprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptoripv4rsshashprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorethfailoverprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorethfailoverprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorethoffloadprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorethoffloadprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorethworkqueueprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorethworkqueueprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorethcompqueueprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorethcompqueueprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorethrecvqueueprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorethrecvqueueprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptoretharfsprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptoretharfsprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorethinterruptprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorethinterruptprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `adaptorrssprofile` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `adaptorrssprofile` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;


ALTER TABLE `biosVfACPI10Support` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfACPI10Support` ADD CONSTRAINT `biosvprofile_fk_132` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfAllUSBDevices` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfAllUSBDevices` ADD CONSTRAINT `biosvprofile_fk_133` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfAssertNMIOnPERR` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfAssertNMIOnPERR` ADD CONSTRAINT `biosvprofile_fk_134` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfAssertNMIOnSERR` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfAssertNMIOnSERR` ADD CONSTRAINT `biosvprofile_fk_135` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfBootOptionRetry` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfBootOptionRetry` ADD CONSTRAINT `biosvprofile_fk_136` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfConsoleRedirection` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfConsoleRedirection` ADD CONSTRAINT `biosvprofile_fk_137` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfCoreMultiProcessing` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfCoreMultiProcessing` ADD CONSTRAINT `biosvprofile_fk_138` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfCPUPerformance` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfCPUPerformance` ADD CONSTRAINT `biosvprofile_fk_139` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfDirectCacheAccess` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfDirectCacheAccess` ADD CONSTRAINT `biosvprofile_fk_140` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfDRAMClockThrottling` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfDRAMClockThrottling` ADD CONSTRAINT `biosvprofile_fk_141` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfDramRefreshRate` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfDramRefreshRate` ADD CONSTRAINT `biosvprofile_fk_142` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfEnhancedIntelSpeedStepTech` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfEnhancedIntelSpeedStepTech` ADD CONSTRAINT `biosvprofile_fk_143` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfExecuteDisableBit` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfExecuteDisableBit` ADD CONSTRAINT `biosvprofile_fk_144` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfFRB2Timer` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfFRB2Timer` ADD CONSTRAINT `biosvprofile_fk_145` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfFrequencyFloorOverride` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfFrequencyFloorOverride` ADD CONSTRAINT `biosvprofile_fk_146` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfFrontPanelLockout` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfFrontPanelLockout` ADD CONSTRAINT `biosvprofile_fk_147` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfIntelEntrySASRAIDModule` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfIntelEntrySASRAIDModule` ADD CONSTRAINT `biosvprofile_fk_148` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfIntelHyperThreadingTech` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfIntelHyperThreadingTech` ADD CONSTRAINT `biosvprofile_fk_149` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfIntelTurboBoostTech` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfIntelTurboBoostTech` ADD CONSTRAINT `biosvprofile_fk_150` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfIntelVirtualizationTechnology` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfIntelVirtualizationTechnology` ADD CONSTRAINT `biosvprofile_fk_151` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfIntelVTForDirectedIO` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfIntelVTForDirectedIO` ADD CONSTRAINT `biosvprofile_fk_152` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfInterleaveConfiguration` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfInterleaveConfiguration` ADD CONSTRAINT `biosvprofile_fk_153` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfLocalX2Apic` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfLocalX2Apic` ADD CONSTRAINT `biosvprofile_fk_154` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfLOMPortsConfiguration` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfLOMPortsConfiguration` ADD CONSTRAINT `biosvprofile_fk_155` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfLvDIMMSupport` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfLvDIMMSupport` ADD CONSTRAINT `biosvprofile_fk_156` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfMaximumMemoryBelow4GB` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfMaximumMemoryBelow4GB` ADD CONSTRAINT `biosvprofile_fk_157` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfMaxVariableMTRRSetting` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfMaxVariableMTRRSetting` ADD CONSTRAINT `biosvprofile_fk_158` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfMemoryMappedIOAbove4GB` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfMemoryMappedIOAbove4GB` ADD CONSTRAINT `biosvprofile_fk_159` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfMirroringMode` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfMirroringMode` ADD CONSTRAINT `biosvprofile_fk_160` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfNUMAOptimized` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfNUMAOptimized` ADD CONSTRAINT `biosvprofile_fk_161` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfOnboardSATAController` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfOnboardSATAController` ADD CONSTRAINT `biosvprofile_fk_162` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfOnboardStorage` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfOnboardStorage` ADD CONSTRAINT `biosvprofile_fk_163` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfOptionROMEnable` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfOptionROMEnable` ADD CONSTRAINT `biosvprofile_fk_164` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfOptionROMLoad` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfOptionROMLoad` ADD CONSTRAINT `biosvprofile_fk_165` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfOSBootWatchdogTimer` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfOSBootWatchdogTimer` ADD CONSTRAINT `biosvprofile_fk_166` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfOSBootWatchdogTimerPolicy` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfOSBootWatchdogTimerPolicy` ADD CONSTRAINT `biosvprofile_fk_167` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfOSBootWatchdogTimerTimeout` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfOSBootWatchdogTimerTimeout` ADD CONSTRAINT `biosvprofile_fk_168` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfPackageCStateLimit` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfPackageCStateLimit` ADD CONSTRAINT `biosvprofile_fk_169` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfPCISlotLinkSpeed` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfPCISlotLinkSpeed` ADD CONSTRAINT `biosvprofile_fk_170` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfPCISlotOptionROMEnable` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfPCISlotOptionROMEnable` ADD CONSTRAINT `biosvprofile_fk_171` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfPOSTErrorPause` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfPOSTErrorPause` ADD CONSTRAINT `biosvprofile_fk_172` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfProcessorC1E` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfProcessorC1E` ADD CONSTRAINT `biosvprofile_fk_173` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfProcessorC3Report` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfProcessorC3Report` ADD CONSTRAINT `biosvprofile_fk_174` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfProcessorC6Report` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfProcessorC6Report` ADD CONSTRAINT `biosvprofile_fk_175` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfProcessorC7Report` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfProcessorC7Report` ADD CONSTRAINT `biosvprofile_fk_176` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfProcessorCState` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfProcessorCState` ADD CONSTRAINT `biosvprofile_fk_177` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfProcessorEnergyConfiguration` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfProcessorEnergyConfiguration` ADD CONSTRAINT `biosvprofile_fk_178` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfProcessorPrefetchConfig` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfProcessorPrefetchConfig` ADD CONSTRAINT `biosvprofile_fk_179` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfPSTATECoordination` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfPSTATECoordination` ADD CONSTRAINT `biosvprofile_fk_180` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfQPILinkFrequencySelect` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfQPILinkFrequencySelect` ADD CONSTRAINT `biosvprofile_fk_181` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfQuietBoot` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfQuietBoot` ADD CONSTRAINT `biosvprofile_fk_182` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfResumeOnACPowerLoss` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfResumeOnACPowerLoss` ADD CONSTRAINT `biosvprofile_fk_183` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfScrubPolicies` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfScrubPolicies` ADD CONSTRAINT `biosvprofile_fk_184` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfSelectMemoryRASConfiguration` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfSelectMemoryRASConfiguration` ADD CONSTRAINT `biosvprofile_fk_185` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfSerialPortAEnable` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfSerialPortAEnable` ADD CONSTRAINT `biosvprofile_fk_186` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfSparingMode` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfSparingMode` ADD CONSTRAINT `biosvprofile_fk_187` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfSriovConfig` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfSriovConfig` ADD CONSTRAINT `biosvprofile_fk_188` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfUCSMBootModeControl` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfUCSMBootModeControl` ADD CONSTRAINT `biosvprofile_fk_189` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfUCSMBootOrderRuleControl` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfUCSMBootOrderRuleControl` ADD CONSTRAINT `biosvprofile_fk_190` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfUEFIOSUseLegacyVideo` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfUEFIOSUseLegacyVideo` ADD CONSTRAINT `biosvprofile_fk_191` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfUSBBootConfig` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfUSBBootConfig` ADD CONSTRAINT `biosvprofile_fk_192` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfUSBFrontPanelAccessLock` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfUSBFrontPanelAccessLock` ADD CONSTRAINT `biosvprofile_fk_193` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfUSBPortConfiguration` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfUSBPortConfiguration` ADD CONSTRAINT `biosvprofile_fk_194` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfUSBSystemIdlePowerOptimizingSetting` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfUSBSystemIdlePowerOptimizingSetting` ADD CONSTRAINT `biosvprofile_fk_195` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVfVGAPriority` CHANGE COLUMN FK_biosVProfile FK_biosVProfile INT NOT NULL;
ALTER TABLE `biosVfVGAPriority` ADD CONSTRAINT `biosvprofile_fk_196` FOREIGN KEY(FK_biosVProfile) REFERENCES `biosVProfile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `biosVProfile` CHANGE COLUMN FK_orgOrg FK_orgOrg INT NOT NULL;
ALTER TABLE `biosVProfile` ADD CONSTRAINT `biosvprofile_fk_197` FOREIGN KEY(FK_orgOrg) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `lsmaintMaintPolicy` CHANGE COLUMN FK_orgOrg FK_orgOrg INT NOT NULL;
ALTER TABLE `lsmaintMaintPolicy` ADD CONSTRAINT `lsmaintmaintpolicy_fk_198` FOREIGN KEY(FK_orgOrg) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;

ALTER TABLE `fabricdceswsrvpc` CHANGE COLUMN `FK_fabricDceSwSrv` `FK_fabricDceSwSrv` INT NOT NULL  ;
ALTER TABLE `fabricdceswsrvpc` ADD CONSTRAINT `fabricdceswsrv_fk_199` FOREIGN KEY(`FK_fabricDceSwSrv`) REFERENCES `fabricdceswsrv` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricdceswsrvpcep` CHANGE COLUMN `FK_fabricDceSwSrvPc` `FK_fabricDceSwSrvPc` INT NOT NULL  ;
ALTER TABLE `fabricdceswsrvpcep` ADD CONSTRAINT `fabricdceswsrvpc_fk_200` FOREIGN KEY(`FK_fabricDceSwSrvPc`) REFERENCES `fabricdceswsrvpc` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorhostethifprofile` ADD CONSTRAINT `orgorg_fk_201` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorextipv6rsshashprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_202` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptoripv6rsshashprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_203` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptoripv4rsshashprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_204` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorethfailoverprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_205` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorethoffloadprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_206` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorethworkqueueprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_207` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorethcompqueueprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_208` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorethrecvqueueprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_209` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptoretharfsprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_210` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorethinterruptprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_211` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `adaptorrssprofile` ADD CONSTRAINT `adaptorhostethifprofile_fk_212` FOREIGN KEY(`FK_adaptorhostethifprofile`) REFERENCES `adaptorhostethifprofile` (`PrimaryKey`) ON DELETE CASCADE;

-- Added for LanConnectivity Policy: 3.0 Sprint-2
ALTER TABLE `vniclanconnpolicy` ADD PRIMARY KEY(`PrimaryKey`);
ALTER TABLE `vniclanconnpolicy` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO_INCREMENT  ;
ALTER TABLE `vniclanconnpolicy` CHANGE COLUMN `FK_orgOrg` `FK_orgOrg` INT NOT NULL  ;
ALTER TABLE `vniclanconnpolicy` ADD CONSTRAINT `orgorg_fk_213` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `vnicether` ADD COLUMN `FK_vniclanconnpolicy` INT DEFAULT NULL;
ALTER TABLE `vnicether` ADD CONSTRAINT `vniclanconnpolicy_fk_214` FOREIGN KEY(`FK_vniclanconnpolicy`) REFERENCES `vniclanconnpolicy` (`PrimaryKey`) ON DELETE CASCADE;

-- Added for CallHome
ALTER TABLE `callhomeep` ADD CONSTRAINT `toproot_fk_215` FOREIGN KEY(`FK_topRoot`) REFERENCES `toproot` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhomepolicy` ADD CONSTRAINT `callhomeep_fk_216` FOREIGN KEY(`FK_callhomeep`) REFERENCES `callhomeep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhomeperiodicsysteminventory` ADD CONSTRAINT `callhomeep_fk_217` FOREIGN KEY(`FK_callhomeep`) REFERENCES `callhomeep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhomeprofile` ADD CONSTRAINT `callhomeep_fk_218` FOREIGN KEY(`FK_callhomeep`) REFERENCES `callhomeep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhometestalert` ADD CONSTRAINT `callhomeep_fk_219` FOREIGN KEY(`FK_callhomeep`) REFERENCES `callhomeep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhomesource` ADD CONSTRAINT `callhomeep_fk_220` FOREIGN KEY(`FK_callhomeep`) REFERENCES `callhomeep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhomesmtp` ADD CONSTRAINT `callhomeep_fk_221` FOREIGN KEY(`FK_callhomeep`) REFERENCES `callhomeep` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhomedest` ADD CONSTRAINT `callhomeprofile_fk_222` FOREIGN KEY(`FK_callhomeprofile`) REFERENCES `callhomeprofile` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `callhomesource` CHANGE COLUMN `from` `from_from` varchar(255) DEFAULT NULL  ;
ALTER TABLE `callhometestalert` CHANGE COLUMN `group` `group_group` varchar(255) DEFAULT NULL  ;

-- Added for Configuraing backup
ALTER TABLE `mgmtbackuppolicy` ADD CONSTRAINT `orgorg_fk_223` FOREIGN KEY(`FK_orgOrg`) REFERENCES `orgorg` (`PrimaryKey`) ON DELETE CASCADE;

-- UCS Mini Support 4.0
ALTER TABLE `fabricsubgroup` CHANGE COLUMN `FK_fabricDceSwSrv` `FK_fabricDceSwSrv` INT NOT NULL  ;
ALTER TABLE `fabricsubgroup` ADD CONSTRAINT `fabricdceswsrv_fk_224` FOREIGN KEY(`FK_fabricDceSwSrv`) REFERENCES `fabricdceswsrv` (`PrimaryKey`) ON DELETE CASCADE;
ALTER TABLE `fabricdceswsrvep` CHANGE COLUMN `FK_fabricDceSwSrv` `FK_fabricDceSwSrv` INT DEFAULT NULL  ;
ALTER TABLE `fabricdceswsrvep` ADD COLUMN FK_fabricSubGroup INT DEFAULT NULL;
ALTER TABLE `fabricdceswsrvep` ADD CONSTRAINT `fabricsubgroup_fk_225` FOREIGN KEY(`FK_fabricSubGroup`) REFERENCES `fabricsubgroup` (`PrimaryKey`) ON DELETE CASCADE;