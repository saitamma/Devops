package com.cisco.ca.cstg.pdi.utils;

public interface Constants {

	String LICENSE_FILENAME = "license.txt";
	String PDI_HOME = "pdi.home";
	String PDI_VERSION = "pdi.version";
	String PDI_RELEASEDATE = "pdi.releaseDate";
	String PDI_RELEASETYPE = "pdi.releaseType";
	int DELETE_MAX_LENGTH = 150;
	String DOWNLOAD_ZIP_ENCRYPTION = "download.zip.encryption";
	String UCS_XML_SKELETON_PATH = "/UCS_XML/Real_UCS_Skeleton.xml";
	String PDI_META_DATA_FILE_NAME = "PDI_Meta_Data.xml";
	String PDI_CONFIG_FILE_NAME = "PDI_Configuration.xml";
	String PDI_TEMPLATE_FILE_NAME = "PDI_Template.xml";
	String DELAY_IN_CHASSIS_DISCOVERY = "delayBetweenChassisDiscovery";
	String PROJECT_KEY = "projectKey";
	String DEVICE_TIME_DELAY = "device.timeDelay";
	String DEVICE_CON_RETRY_INTERVAL = "deviceConnection.retry.interval";
	String DEVICE_CON_RETRY_FREQ = "deviceConnection.retry.frequency";
	/* License Value Keys */
	String LICENSE_KEY_PROJECT_NAME = "PROJECTNAME";
	String LICENSE_KEY_CUSTOMER_NAME = "CUSTOMERNAME";
	String LICENSE_KEY_CUSTOMER_DESCRIPTION = "CUSTOMERDESCRIPTION";
	String LICENSE_KEY_CUSTOMER_BUSINESS = "CUSTOMERBUSINESS";
	String LICENSE_KEY_THEATRE = "THEATRE";
	String LICENSE_KEY_CUSTOMER_VERTICALS = "CUSTOMERVERTICALS";
	String LICENSE_KEY_ASPID = "ASPID";
	String LICENSE_KEY_SITEID = "SITEID";
	String LICENSE_KEY_CREATED_BY = "CREATEDBY";
	String LICENSE_KEY_SERVICE_TYPE = "ServiceType";
	String LICENSE_KEY_SITENAME = "SITENAME";

	/* Constants for verify connection */

	String UCS_CONNECTION_TIMEOUT = "";
	String UCS_CONNECTION_TIMEOUT_PERIOD_MILLISECOND = "";

	String JSONSTRING = "json string:";
	String ACTIVEPROJECTID = "Active Project Id:";
	String ACTIVEPROJECTDETAILS = "activeProjectDetails";

	/* SANControlroller.java constants */
	String ACTIVE_PROJECT_ID = "activeProjectId";
	String ID = "id";
	String FIID = "fiId";
	String VSANNAME = "vsanName";
	String FCOEVSANID = "fcoeVsanId";
	String FCOEVLANNAME = "fcoeVlanName";
	String ORGANIZATIONS = "organizations";
	String WWNNNAME = "wwnnName";
	String WWPNNAME = "wwpnName";
	String DESCRIPTION = "description";
	String FROMADDRESS = "fromAddress";
	String TOADDRESS = "toAddress";
	String VHBANAME = "vhbaName";
	String WWPNPOOLID = "wwpnPoolId";
	String SWITCHID = "switchId";
	String TEMPLATETYPE = "templateType";
	String ADDORUPDATE = "addOrUpdate";
	String DELETED = "deleted";
	String JSONOBJ = "jsonObj";
	String VSANID = "vsanId";
	String QOSPOLICYID = "qosPolicyId";
	String NAME = "name";
	String TRANSMITQUEUES = "transmitQueues";
	String TRANSMITQUEUESRINGSIZE = "transmitQueuesRingSize";
	String RECEIVEQUEUES = "receiveQueues";
	String RECEIVEQUEUESRINGSIZE = "receiveQueuesRingSize";
	String SCSIIOQUEUES = "scsiIoQueues";
	String SCSIIOQUEUESRINGSIZE = "scsiIoQueuesRingSize";
	String FCPERRORRECOVERY = "fcpErrorRecovery";
	String FLOGIRETRIES = "flogiRetries";
	String FLOGITIMEOUT = "flogiTimeout";
	String PLOGIRETRIES = "plogiRetries";
	String PLOGITIMEOUT = "plogiTimeout";
	String PORTDOWNTIMEOUT = "portDownTimeout";
	String PORTDOWNIORETRY = "portDownIoRetry";
	String LINKDOWNTIMEOUT = "linkDownTimeout";
	String IOTHROTTLECOUNT = "ioThrottleCount";
	String LUNSPERTERGET = "lunsPerTarget";
	String INTERRUPTMODE = "interruptMode";
	String SANVHBATEMPLATE = "sanVhbaTemplate";
	String SANADAPTERPOLICY = "sanAdapterPolicies";
	String SAPDEFAULT = "sapDefault";
	String SANWWNN = "sanWwnn";
	String VHBAID = "vhbaId";
	String ASSIGNMENTORDER = "assignmentOrder";

	String SUCCESS = "success";
	String FAILURE = "failure";

	/* AdminController.java constants */
	String IPADDRESS = "ipAddress";
	String PARENTID = "parentId";
	String TIMEZONE = "timeZone";
	String COMMENTS = "comments";
	String REALM = "realm";
	String REFRESHPERIOD = "refreshPeriod";
	String SESSIONTIMEOUT = "sessionTimeout";
	String PROVIDERGROUP = "providerGroup";
	String TWOFACTOR = "twoFactor";
	String HOSTNAME = "hostname";
	String PROVIDERORDER = "providerOrder";
	String BINDN = "bindDn";
	String BASEDN = "baseDn";
	String PORT = "port";
	String ENABLESSL = "enableSsl";
	String FILTER = "filter";
	String ATTRIBUTE = "attribute";
	String PROVIDERPASSWORD = "providerPassword";
	String TIMEOUT = "timeout";
	String VENDOR = "vendor";
	String GROUPAUTHORIZAION = "groupAuthorization";
	String GROUPRECURSION = "groupRecursion";
	String TARGETATTRIBUTE = "targetAttribute";
	String USEPRIMARYGROUP = "usePrimaryGroup";
	String PROVIDER = "provider";
	String RETRIES = "retries";
	String ROLE = "ldapRoleId";
	String LOCALE = "ldapLocaleId";
	String RADIUSORDER = "radiusOrder";
	String AUTHORIZATIONPORT = "authorizationPort";
	String SSLKEY = "sslKey";
	String PROVIDERKEY = "providerKey";
	String PRIVILEGE = "privileges";
	String NULL_STR = "null";
	String PATTERN = "\\[|\\]";
	String STATE = "state";
	String SWITCHPRIORITY = "switchPriority";
	String THROTTLING = "throttling";
	String CONTACT = "contact";
	String PHONE = "phone";
	String EMAIL = "email";
	String ADDRESS = "address";
	String CUSTOMERID = "customerId";
	String CONTRACTID = "contractId";
	String SITEID = "siteId";
	String EMAILFROM = "emailFrom";
	String REPLYTO = "replyTo";
	String HOST = "host";
	String SENDPERIODICALLY = "sendPeriodically";
	String SENDINTERVALDAYS = "sendIntervalDays";
	String SENDINTERVALHOURS = "sendIntervalHours";
	String SENDINTERVALMINUTES = "sendIntervalMinutes";
	String GENERALSETTING = "generalSetting";
	String SYSTEMINVENTORY = "systemInventory";
	String MAXMSGSIZE = "maxMsgSize";
	String FORMAT = "format";
	String LEVEL = "level";
	String ALERTGROUPID = "alertGroupId";
	String CAUSE = "cause";
	String PROFILEID = "profileId";
	String BACKUPSTATUS = "backupStatus";
	String ADMINSTATE = "adminState";
	String BACKUPTYPE = "backupType";
	String PRESERVEIDENTITIES = "preserveIdentities";
	String PROTOCOL = "protocol";
	String REMOTEFILE = "remoteFile";
	String USERNAME = "userName";
	String PASSWORD = "password";
	String SANWWPN = "sanWwpn";
	String SANVSAN = "sanVsan";
	String LANQASPOLICY = "lanQosPolicy";

	/* ServersController.java constants */
	String PREFIX = "prefix";
	String ENFORCEVNICNAME = "enforceVnicName";
	String REBOOTONUPDATE = "rebootOnUpdate";
	String BOOTMODE = "bootMode";
	String SECUREBOOT = "secureBoot";
	String ORDER = "order";
	String VNICID = "vnicId";
	String LUNID = "lunId";
	String WWPNADDRESS = "wwpnAddress";
	String MODE = "mode";
	String PROTECTCONFIGURATION = "protectConfiguration";
	String CHASSISMINID = "chassisMinId";
	String CHASSISMAXID = "chassisMaxId";
	String SERVERPOOLID = "serverPoolId";
	String SERVERSSERVERPOOLQUALIFIER = "serversServerPoolQualifier";
	String QUALIFIERID = "qualifierId";
	String SERVERSBOOTPOLICYSAN = "serversBootPolicySan";
	String DEVICE = "device";
	String BOOTORDER = "bootOrder";
	String MINID = "minId";
	String MAXID = "maxId";
	String SPQID = "serversServerPoolQualifier";
	String REBOOTPOLICY = "rebootPolicy";
	String PREFIXTYPE = "prefixType";
	String REBOOTONCHANGE = "rebootOnChange";
	String QUIETBOOT = "quietBoot";
	String POSTERRORPAUSE = "postErrorPause";
	String RESUMEACONPOWERLOSS = "resumeAcOnPowerLoss";
	String FRONTPANELLOCKOUT = "frontPanelLockout";
	String TURBOBOOST = "turboBoost";
	String ENHANCEDINTELSPEEDSTEP = "enhancedIntelSpeedstep";
	String HYPERTHREADING = "hyperThreading";
	String COREMULTIPROCESSING = "coreMultiProcessing";
	String EXECUTEDISABLEDBIT = "executeDisabledBit";
	String VIRTUALIZATIONTECHNOLOGY = "virtualizationTechnology";
	String HARDWAREPREFETCHER = "hardwarePrefetcher";
	String ADJACENTCACHELINEPREFETCHER = "adjacentCacheLinePrefetcher";
	String DCUSTREAMERPREFETCH = "dcuStreamerPrefetch";
	String DCUIPPREFETCH = "dcuIpPrefetch";
	String DIRECTCACHEACCESS = "directCacheAccess";
	String PROCESSORCSTATE = "processorCState";
	String PROCESSORC1E = "processorC1e";
	String PROCESSORC3REPORT = "processorC3Report";
	String PROCESSORC6REPORT = "processorC6Report";
	String PROCESSORC7REPORT = "processorC7Report";
	String CPUPERFORMANCE = "cpuPerformance";
	String MAXVARIABLEMTRRSETTING = "maxVariableMtrrSetting";
	String LOCALX2APIC = "localX2Apic";
	String POWERTECHNOLOGY = "powerTechnology";
	String ENERGYPERFORMANCE = "energyPerformance";
	String FREQUENCYFLOOROVERRIDE = "frequencyFloorOverride";
	String PSTATECOORDINATION = "pstateCoordination";
	String DRAMCLOCKTHROTTLING = "dramClockThrottling";
	String CHANNELINTERLEAVING = "channelInterleaving";
	String RANKINTERLEAVING = "rankInterleaving";
	String DEMANDSCRUB = "demandScrub";
	String PATROLSCRUB = "patrolScrub";
	String VTFORDIRECTEDIO = "vtForDirectedIo";
	String INTERRUPTREMAP = "interruptRemap";
	String COHERENCYSUPPORT = "coherencySupport";
	String ATSSUPPORT = "atsSupport";
	String PASSTHROUGHDMASUPPORT = "passThroughDmaSupport";
	String MEMORYRASCONFIG = "memoryRasConfig";
	String NUMA = "numa";
	String LVDDRMODE = "lvDdrMode";
	String DRAMREFRESHRATE = "dramRefreshRate";
	String MIRRORINGMODE = "mirroringMode";
	String SPARINGMODE = "sparingMode";
	String SERIALPORTA = "serialPortA";
	String MAKEDEVICENONBOOTABLE = "makeDeviceNonBootable";
	String LEGACYUSBSUPPORT = "legacyUsbSupport";
	String USBSYSTEMIDLEPOWEROPTIMIZINGSETTING = "usbSystemIdlePowerOptimizingSetting";
	String USBFRONTPANELACCESSLOCK = "usbFrontPanelAccessLock";
	String PORT6064EMULATION = "port6064Emulation";
	String USBPORTFRONT = "usbPortFront";
	String USBPORTINTERNAL = "usbPortInternal";
	String USBPORTKVM = "usbPortKvm";
	String USBPORTREAR = "usbPortRear";
	String USBPORTSDCARD = "usbPortSdCard";
	String USBPORTVMEDIA = "usbPortVMedia";
	String ALLUSBDEVICES = "allUsbDevices";
	String MAXMEMORYBELOW4G = "maxMemoryBelow4g";
	String MEMORYMAPPEDIOABOVE4GBCONFIG = "memoryMappedIoAbove4gbConfig";
	String VGAPRIORITY = "vgaPriority";
	String QPILINKFREQUENCYSELECT = "qpiLinkFrequencySelect";
	String ALLONBOARDLOMPORTS = "allOnboardLomPorts";
	String LOMPORT0OPTIONROM = "lomPort0OptionRom";
	String LOMPORT1OPTIONROM = "lomPort1OptionRom";
	String LOMPORT2OPTIONROM = "lomPort2OptionRom";
	String LOMPORT3OPTIONROM = "lomPort3OptionRom";
	String PCIESLOTSASOPTIONROM = "pcieSlotSasOptionRom";
	String PCIESLOT1LINKSPEED = "pcieSlot1LinkSpeed";
	String PCIESLOT2LINKSPEED = "pcieSlot2LinkSpeed";
	String PCIESLOT3LINKSPEED = "pcieSlot3LinkSpeed";
	String PCIESLOT4LINKSPEED = "pcieSlot4LinkSpeed";
	String PCIESLOT5LINKSPEED = "pcieSlot5LinkSpeed";
	String PCIESLOT6LINKSPEED = "pcieSlot6LinkSpeed";
	String PCIESLOT7LINKSPEED = "pcieSlot7LinkSpeed";
	String PCIESLOT8LINKSPEED = "pcieSlot8LinkSpeed";
	String PCIESLOT9LINKSPEED = "pcieSlot9LinkSpeed";
	String PCIESLOT10LINKSPEED = "pcieSlot10LinkSpeed";
	String BOOTOPTIONRETRY = "bootOptionRetry";
	String INTELENTRYSASRAID = "intelEntrySasRaid";
	String INTELENTRYSASRAIDMODULE = "intelEntrySasRaidModule";
	String ONBOARDSCUSTORAGESUPPORT = "onboardScuStorageSupport";
	String ASSERTNMIONSERR = "assertNmiOnSerr";
	String ASSERTNMIONPERR = "assertNmiOnPerr";
	String OSBOOTWATCHDOGTIMER = "osBootWatchdogTimer";
	String OSBOOTWATCHDOGTIMERTIMEOUTPOLICY = "osBootWatchdogTimerTimeoutPolicy";
	String OSBOOTWATCHDOGTIMERTIMEOUT = "osBootWatchdogTimerTimeout";
	String FRB2TIMER = "frb2Timer";
	String CONSOLEREDIRECTION = "consoleRedirection";
	String FLOWCONTROL = "flowControl";
	String BAUDRATE = "baudRate";
	String TERMINALTYPE = "terminalType";
	String LEGACYOSREDIRECT = "legacyOsRedirect";
	String PUTTYKEYPAD = "puttyKeypad";

	/* ServiceTemplateController.java constants */
	String TYPE = "type";
	String SERVERSBOOTPOLICY = "serversBootPolicy";
	String SERVERSSERVERPOOL = "serversServerPool";
	String SANVHBA = "sanVhba";
	String LANVNIC = "lanVnic";
	String SERVERSUUIDPOOL = "serversUuidPool";
	String SERVERSLOCALDISC = "serversLocalDisk";
	String SERVERSSERVICEPROFILETEMPLATE = "serversServiceProfileTemplate";
	String SANCONNECTIVITYPOLICY = "sanConnectivityPolicy";
	String LANCONNECTIVITYPOLICY = "lanConnectivityPolicy";
	String HOSTFIRMWAREPACKAGE = "hostFirmwarePackage";
	String MAINTENANCEPOLICY = "maintenancePolicy";
	String BIOS_POLICY = "biosPolicy";

	/* EquipmentController.java constants */
	String SLOTID = "slotId";
	String PORTID = "portId";
	String CHASSIS = "chassis";
	String USERLABEL = "userLabel";
	String JSON_OBJ = "jsonObj";
	String IS_CONFIGURED = "isConfigured";
	String CONFIGURE_AS = "configureAs";
	String CONFIGURE_AS_OLD_VALUE = "configureAsOldValue";

	/* LANController.java constants */
	String VNICTNAME = "vnictName";
	String MACPOOLNAME = "macpoolName";
	String LANMACPOOL = "lanMacpool";
	String TARGET = "target";
	String MACPOOLDESCRIPTION = "macpoolDescription";
	String VLANNAME = "vlanName";
	String VLANID = "vlanId";
	String FINAME = "fiName";
	String DEFAULTGATEWAY = "defaultGateway";
	String SUBNETMASK = "subnetMask";
	String DNS = "dns";
	String OSTYPE = "osType";
	String VLANDEFAULT = "vlanDefault";
	String UPLINKID = "uplinkId";
	String EQUIPMENTUPLINK = "equipmentUplink";
	String NCPNAME = "ncpName";
	String CDP = "cdp";
	String MACREGMODE = "macRegisterMode";
	String UPLINKFAILACTION = "uplinkFailAction";
	String FORGE = "forge";
	String BURST = "burst";
	String HOSTCONTROL = "hostControl";
	String PRIORITY = "priority";
	String RATE = "rate";
	String LANNETWORKCONTROLPOLICY = "lanNetworkControlPolicy";
	String LANQOSPOLICY = "lanQosPolicy";
	String COMPLETIONQUEUES = "completionQueues";
	String COMPLETIONQUEUESINTERRUPTS = "completionQueuesInterrupts";
	String TRANSMITCHECKSUMOFFLOAD = "transmitChecksumOffload";
	String RECEIVECHECKSUMOFFLOAD = "receiveChecksumOffload";
	String TCPSEGMENTATIONOFFLOAD = "tcpSegmentationOffload";
	String TCPLARGERECEIVEOFFLOAD = "tcpLargeReceiveOffload";
	String RECEIVESIDESCALING = "receiveSideScaling";
	String ACCELERATEDRECEIVEFLOWSTEERING = "acceleratedReceiveFlowSteering";
	String FAILBACKTIMEOUT = "failbackTimeout";
	String INTERRUPTCOALESCINGTYPE = "interruptCoalescingType";
	String INTERRUPTTIMER = "interruptTimer";
	String LANVNICTEMPLATE = "lanVnicTemplate";
	String LANETHERNETADAPTERPOLICIES = "lanEthernetAdapterPolicies";
	String LEAPDEFAULT = "leapDefault";

	/* CommonController.java Constants */
	String PROJECTNAME = "projectName";
	String SKIP_SAN = "skipSan";
	String IP_POOL_ASSIGNMENT_ORDER = "ipPoolAssignmentOrder";
	String CHASSIS_COUNT = "CHASSIS_COUNT";
	String PDIVERSION = "pdiVersion";
	String PDIRELEASEDATE = "pdiReleaseDate";
	String PDIRELEASETYPE = "pdiReleaseType";

	/* ConfigureController.java Constants */
	String STATUS = "status";

	/* ProjectController.java Constants */
	String THEATRE = "theatre";
	String CREATEDDATETIME = "createdDate";
	String RESPONSE_FILE_UPLOAD = "project/project";
	String KEY_ERROR_MESSAGE = "errorMessage";
	String PROJECTDATA = "projectData";
	String INFRADATA = "infraData";
	String EQUIPMENTDATA = "equipmentData";
	String CREATEDDATE = "createdDate";
	String LOGIN_USER_CEC = "userCec";

	/* LicenseController.java Constant */
	String RESPONSE_LICENSE_UPLOAD = "license/license";
	String CAM_NAME = "Name";
	String CAM_THEATRE = "Theatre";
	String CUSTOMER_NAME = "Customer Name";
	String CAM_SITE = "Site";
	String AS_PDI = "AS PID";
	String CREATED_BY = "Created by";

	/* EquipmentController.java constants */
	String CDPACTION = "cdpAction";
	String CDPLINKAGG = "cdpLinkAgg";
	String PSPPOWERSUPPLY = "pspPowerSupply";

	/* InfrastructureController.java constants */
	String SOFTWAREVERSION = "softwareVersion";
	String SERVERMODEL = "serverModel";
	String IOMODULE = "ioModule";
	String FABRICINTERCONNECT = "fabricInterconnect";
	String SOFTWARERELEASE = "softwareRelease";
	String SERVERMODEL6324 = "Cisco UCS 6324";

	/* Admin roles for Logged in User */
	String ADMIN_ROLES = "admin";

	/* GenerateXMLForOrg.java */
	String DEFAULT = "default";
	String LOCAL = "local";
	String YES = "yes";
	String NO = "no";
	String DERIVED = "derived";
	String DISABLED = "disabled";
	String ANY = "any";
	String ROOT = "root";
	String LEGACY = "legacy";

	/* Common variable for Services */
	String PROJECTDETAILS_ID = "projectDetails.id";
	String PROJECTDETAILS = "projectDetails";

	/* license package */
	String UTF8 = "UTF-8";
	String MD5 = "MD5";

	String UEFI = "uefi";
	String DELETE_NON_PRESENT = "deleteNonPresent";
	String BOOT_SECURITY = "boot-security";
	String OPERATIONAL = "operational";
	String STAGED = "staged";
	String IMMEDIATE = "immediate";

	/* Topology Logging Message Constants */
	String RESPONSE_ERR_MSG = "VISIO RESPONSE WITH INVALID RESPONSE CODE :";
	String CONTENT_ERR_MSG = "INVALID CONTENT TYPE FROM VISIO SERVER ZIP NOT CREATED :";
	String MAX_RETRY_ERR_MSG = "RETRY COUNT EXCEEDED FOR VISIO TIME OUT EXCEPTION CANNOT TRY AGAIN WITH NEW TIME OUT :";
	String VISIO_SUCCESS_MSG = "VISIO REQUEST COMPLETED SUCCESSFULLY";
	String PRIMARY_CONN_ERR_MSG = "CONNECTION CREATION WITH PRIMARY VISIO SERVER FAILED TRYING TO CONNECT WITH SECONDARY SERVER";
	String NO_CONN_ERR_MSG = "CREATING CONNECTION WITH BOTH PRIMARY AND SECONDARY VISIO SERVER FAILED";
	String CONN_SUCCESS_MSG = "CONNECTION CREATED WITH ";
	String PRIMARY_SERVER_PROCESS_ERR_MSG = "EXCEPTION IN PRIMARY SERVER PROCEEDING WITH SECONDARY SERVER";
	String VISIO_GENERATION_ERR_MSG = "EXCEPTION IN SECONDARY SERVER CANNOT PROCEED FURTHER VISIO GENERATION FAILED";
	String TOTAL_CDP_NEIGHBR = "TOTAL CDP NEIGHBOURS : ";
	String TOTAL_PODS = "TOTAL PODS : ";
	String TOTAL_UCS = "TOTAL UCS FOR POD : ";
	String VISIO_FAILED_MSG = "VISIO REQUEST FAILED";
	String TIMEOUT_ERR_MSG = "TIME OUT OCCURED WHILE CONNECTING WITH VISIO SERVER.RECONNECTING WITH INCREASED TIMEOUT : ";
	String TOPOLOGY_ERROR_MSG = "Error occured during topology generation.Please re-run or email dcv-support@cisco.com";
	String TOPOLOGY_VAL_ERROR_MSG = "Please first configure server ports before generate topology";
	String TOPOLOGY_MINI_ERROR_MSG = "We are not supporting topology generation for \"UCS-Mini\" configuration.";
	String TOPOLOGY_ERROR_MSG_KEY = "Topology_Status";
	String VISIOPRIMARYURL = "visioPrimaryUrl";
	String VISIOSECONDARYURL = "visioSecondaryUrl";
	String VISIOHANGRESOLVERPRIMARYURL = "visioHangResolvePrimaryUrl";
	String VISIOHANGRESOLVERSECONDARYURL = "visioHangResolveSecondaryUrl";

	/* Check URLs for logout when session timeout */
	String CONTAINDEV = "ucsada-dev";
	String CONTAINSTG = "ucsada-stg";
	String CONTAINPROD = "ucsada.cloudapps";

	/* User management constant */
	String USER_ID = "userId";
	String MAIL_ID = "mailId";
	String USER_ROLES = "userRoles";
	String USER_ROLES_NAME = "userRolesName";
	String CREATEDBY = "createdBy";
	String CREATED_DATE = "createdDate";
	String IS_ACTIVE = "isActive";
	String USER_ROLE = "userRole";
	String ACTIVE_USERID = "activeUserID";
	String DATE_FORMAT = "yyyy-MM-dd";
	String ADMIN = "admin";
	String UCSADA_ADMIN = "ucsada_admin";
	String PRODUCT_OWNER = "product_owner";
	String SUPER_ADMIN = "super_admin";

	/* PDI properties file constants */
	String PDI_DEV_LOGOUT = "pdi.devLogout";
	String PDI_STAGE_LOGOUT = "pdi.stgLogout";
	String PDI_PROD_LOGOUT = "pdi.prodLogout";
	String PDI_VISIO_PRI_URL_DEVSTAGE = "pdi.visioPrimaryURL.devStage";
	String PDI_VISIO_SEC_URL_DEVSTAGE = "pdi.visioSecondaryURL.devStage";
	String PDI_VISIO_HR_PRI_URL_DEVSTAGE = "pdi.visioHangResolveURLPrimary.devStage";
	String PDI_VISIO_HR_SEC_URL_DEVSTAGE = "pdi.visioHangResolveURLSecondary.devStage";
	String PDI_VISIO_PRI_URL_PROD = "pdi.visioPrimaryURL.prod";
	String PDI_VISIO_SEC_URL_PROD = "pdi.visioSecondaryURL.prod";
	String PDI_VISIO_HR_PRI_URL_PROD = "pdi.visioHangResolveURLPrimary.prod";
	String PDI_VISIO_HR_SEC_URL_PROD = "pdi.visioHangResolveURLSecondary.prod";
}
