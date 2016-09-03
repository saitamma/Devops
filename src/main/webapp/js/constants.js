{
	MSG_NAME = "Please make sure the field has a valid entry with allowed characters (alphanumeric, underscore and hyphen).";
	MSG_USERID = "Please make sure the field has a valid user id with comma separated.";
	MSG_NUMBER = "Please make sure the field has a valid numeric value.";
	MSG_EMPTY = "This field is required.";
	MSG_IPADDRESS = "Please make sure the field has a valid IP address.";
	MSG_IPFROM_TO_RANGE = "IP address block cannot span over multiple octets.";
	MSG_MACADDRESS = "Please make sure the field has a valid MAC address.";
	MSG_DUPLICATE_ENTRY = "Entry is duplicate.";
	MSG_DUPLICATE_NAME = "Please make sure the name is not duplicate.";
	MSG_DUPLICATE_SUBORG = "SubOrg name is already in use, please enter a different name";
	MSG_UPTO1000 = "Please make sure the field has a valid numeric value, maximum of 1000.";
	MSG_UPTO255 = "Please make sure the field has a valid numeric value, maximum of 255.";
	MSG_BET_1_8 = "Please make sure the field has a valid numeric value, between 1 and 8.";
	MSG_BET_1_20 = "Please make sure the field has a valid numeric value, between 1 and 20.";
	MSG_BET_1_30 = "Please make sure the field has a valid numeric value, between 1 and 30.";
	MSG_BET_0_23 = "Please make sure the field has a valid numeric value, between 0 and 23.";
	MSG_BET_0_59 = "Please make sure the field has a valid numeric value, between 0 and 59.";
	MSG_BET_1_48 = "Please make sure the field has a valid numeric value, between 1 and 48.";
	MSG_BET_1_5000000 = "Please make sure the field has a valid numeric value, between 1 and 5000000.";
	MSG_WWXN = "Please make sure the field has a valid pool value, e.g 20:00:00:25:B5:00:00:00.";
	MSG_INVALID = "Invalid input.";
	MSG_DELETED = "Are you sure you would like to delete the selected item(s)?";
	MSG_DELETED_ALL_RELATED = "While deleting this record will delete all related data from all stage. Are you sure?";
	MSG_DELETE_ORG_DEPENDENCY = "Please delete existing dependencies before deleting this organization.";
	MSG_INFRASTRUCTURE = "Please ensure that all fields are selected before proceeding.";
	MSG_ERROR_WHILESAVE = "Error while saving/updating record in the DB. Please try again.";
	MSG_DNS_ALLOWED = "Maximum number of DNS allowed are four.";
	MSG_NTP_ALLOWED = "Maximum number of NTP allowed are four.";
	MSG_CHASSIS_ACTION_OP = "Please delete all server ports before changing this option.";
	MSG_EQUIP_SERVER_CONFIRM_GENERATE = "Adding data would delete all existing record(s). Are you sure you want to continue?";
	MSG_NOT_ENOUGH_PORT = "There aren't enough ports available for the specified number of Chassis.";
	MSG_ODD_PORT_CONFIG = "Please choose an odd numbered port for configuring FC port(s).";
	MSG_CONFIG_FC_PORT_ERROR = "One or more port(s), starting from current selection, have been configured as server/uplink ports.";
	MSG_CONFIG_SERVER_PORT_ERROR = "This port is already used as an FC or ethernet uplink port. Please choose another port.";
	MSG_CONFIG_SERVER_PORT_INVALID = "The port value entered is not valid. Please choose another combination.";
	MSG_VLAN_GENERATE_UNIQUE = "One or more values in the specified range already exists in our records. Please remove them before proceeding.";
	MSG_VLAN_MAC_ONE_REQUIRED = "Please make sure at least one Vlan and one MAC Pool is configured before proceeding further.";
	MSG_ATLEAST_ONvNIC = "Please make sure at least one vNIC template is configured before proceeding further.";
	MSG_ATLEAST_ONvHBA = "Please make sure at least one vHBA template is configured before proceeding further.";
	MSG_FCOE_VSAN_VLAN_ID = "Please make sure the field has a valid numeric value, from 1 to 4093, except 4079.";
	MSG_SKIP_SAN = "This action will erase all the exisitng configuration, if any, related to SAN. Are you sure you want to proceed?";
	MSG_ALERT_CHECKED_SKIPSAN = 'Please uncheck "skipSAN configuration" before generating the data.';
	MSG_HEXADECIMAL_HYPHEN = "Please make sure the field has a valid entry with allowed characters (hexadecimal and hyphen), e.g. 0000-000000000001.";
	MSG_HEXADECIMAL_HYPHEN_8X4X4 = "Please make sure the field has a valid entry with allowed characters (hexadecimal and hyphen), e.g. 00000000-0000-0000.";
	MSG_PING_SUCCESS = "Ping to UCS Manager was successful.";
	MSG_PING_FAILURE = "Ping to UCS Manager was a failure.";
	MSG_ERROR_DOWNLOAD_TEMP = "An error occurred while downloading configuration file from UCS manager.";
	MSG_ERROR_CREATING_CONFIG = "An error occurred while creating configuration file to push to UCS manager.";
	MSG_ERROR_PUSH_CONFIG = "An error occurred while pushing configuration to UCS manager.";
	MSG_SUCCESS_PUSH = "Successfully pushed to UCS manager.";
	MSG_ERROR_ZIPPING = "An error occurred while zipping the data for download.";
	MSG_USERNAME = "Please enter username.";
	MSG_PASSWORD = "Please enter password.";
	MSG_VLAN_FLUSH_DATA = "Adding new Vlan(s) will delete all existing Vlans, except default.  Are you sure you want to proceed?";
	MSG_DELETING_SAN_BOOT_POLICY = "Please delete SAN target dependecies before deleting SAN boot policy.";
	MSG_ADD_TWO_SAN_TARGET_POLICY = "You can not add more then two SAN target.";
	MSG_ATLEAST_ONE_SAN_TARGET = "Please ensure that each SAN boot policy has minimum one SAN target is associated with it.";
	MSG_BOOTCONFIG_WITH_SAN_AND_LOCALDISK = "The boot configuration cannot include entries for both SAN boot and local disk or iSCSI overlay.";
	MSG_BOOTPOLICY_POPUP_REQUIRED_FIELD = "Please ensure all mandatory fields are assigned values before proceeding.";
	MSG_MINID_MAXID_GREATER = "Please ensure that chassis min id is not greater than max id.";
	MSG_DUPLICATE_NOT_ALLOWED_VLANID = "Please ensure FCoE VLAN id is not being used by the VLAN of LAN cloud.";
	MSG_BET_1_TO_3967_OR_4048_TO_4093 = "Please make sure the field has a valid numeric value, between 1 and 3967 OR 4048 to 4093.";
	MSG_DUPLICATE_FCoE_VLANID = "Please make sure FCoE VLAN id is not duplicate.";
	MSG_DUPLICATE_VSANID = "Please make sure VSAN id is not duplicate.";
	MSG_QOS_RATE = "Please ensure rate is either line-rate or in the range of 8 and 40000000.";
	MSG_BET_0_TO_65535 = "Please make sure the field has a valid numeric value, between 0 and 65535.";
	MSG_BET_1_TO_65535 = "Please make sure the field has a valid numeric value, between 1 and 65535.";
	MSG_LAN_N_C_P_WARNING="If the Action on Uplink Fail is set to Warning, the fabric will not fail over if uplink connectivity is lost.";
	MSG_FILL_MANDATORY_FIELDS = "Please fill all the mandatory fields before proceeding.";
	MSG_BET_60_TO_172800 = "Please make sure the field has a valid numeric value, between 60 and 172800.";
	MSG_BET_64_TO_128 = "Please make sure the field has a valid numeric value, between 64 and 128.";
	MSG_BET_64_TO_512 = "Please make sure the field has a valid numeric value, between 64 and 512.";
	MSG_BET_1000_TO_255000 = "Please make sure the field has a valid numeric value, between 1000 and 255000.";
	MSG_BET_0_TO_240000 = "Please make sure the field has a valid numeric value, between 0 and 240000.";
	MSG_BET_1_TO_1024 = "Please make sure the field has a valid numeric value, between 1 and 1024.";
	MSG_BET_256_TO_1024 = "Please make sure the field has a valid numeric value, between 256 and 1024.";
	MSG_CLONING_SUCCESS = "Cloning of project successful.";
	MSG_CLONING_ERROR = "There was an error while cloning the data.";
	MSG_FILE_EXT_ERROR= "Allowed extensions for the file is .zip. Please make sure a valid file is uploaded.";
	CLONE_CONF = "Please note the below information for Cloned Projects:<ol><li>Description field for Port channel will not be retained.</li><li>VMWare OS in vNIC Template will be marked as Windows in the cloned/imported project.</li><li>vNIC's,vHBA's which are only associated at BootPolicy-LAN,BootPolicy-SAN respectively will not be cloned.</li><ol>";
	IMPORT_CONF = "Please note the below information for Imported Projects:<ol><li>Chassis Id for Servers Ports will be hard coded to 0 in the new project for UCS B-Series Servers.</li><li>Description field for Port channel will not be retained.</li><li>VMWare OS in vNIC Template will be marked as Windows in the cloned/imported project.</li><li>vNIC's,vHBA's which are only associated at BootPolicy-LAN,BootPolicy-SAN respectively will not be imported.</li><ol>";
	MSG_HOSTNAME_IP = "Please ensure that value is valid IP address or hostname.";
	MSG_DUPLICATE_ORDER = "Please make sure the order is not duplicate.";
	MSG_PASSWORD_CPASSWORD_MATCH = "Please ensure that Password and Confirm Pwd are same.";
	MSG_PASSWORD_CKEY_MATCH = "Please ensure that Key and Confirm Key are same.";
	MSG_BET_0_TO_60 = "Please make sure the field has a valid numeric value, between 0 and 60.";
	MSG_FILL_PROVIDER_GROUP = "Please make sure that a Provider Group is selected before proceeding.";
	MSG_BET_0_TO_5 = "Please make sure the field has a valid numeric value, between 0 and 5.";
	MSG_REFRESH_PERIOD_VALID = "Please make sure that the Web Session Refresh Period is lesser than Web Session Timeout.";
	MSG_CHASSIS_CONF_ISSAVED = "Please ensure that the Hardware Settings and Chassis Policy are saved before proceeding.";
	MSG_NO_VSAN_EITHER_A_B = "Please fill number of VSANs either A side or B side.";
	MSG_UUIDPOOL_PREFIX_ISDEFAULT = "Kindly change the default prefix value in case of \"Other\" in prefix type, else this may result in defaulting value to \"Derived\". Are you sure you want to proceed?";
	MSG_UUIDPOOL_TABLE_PREFIX_ERROR = "Please change either prefix value or prefix type.";	
	MSG_BET_1_TO_256 = "Please make sure the field has a valid numeric value, between 1 and 256.";
	MSG_BET_1_TO_512 = "Please make sure the field has a valid numeric value, between 1 and 512.";
	MSG_BET_64_TO_4096 = "Please make sure the field has a valid numeric value, between 64 and 4096.";
	MSG_BET_1_TO_514 = "Please make sure the field has a valid numeric value, between 1 and 514.";
	MSG_BET_0_TO_600 = "Please make sure the field has a valid numeric value, between 0 and 600.";
	MSG_EMAIL_ADDERESS = "Please make sure that value is valid Email adderess";
	MSG_PHONE = "Please make sure that value is valid and start with + and followed by country code and phone number";
	MSG_TIMESTAMP = "Please make sure that value is valid";
	MSG_CANT_CHANGE_DEFAULT = "You can not change default name.";
	MSG_CANT_CHANGE_DEFAULT_ORG = "You can not change default organization."
	MSG_USER_MGMT_INFO = "Enter single or multiple IDs with comma separated.";
	MSG_USER_MGMT_INVALID_USERID = "is/are not valid. Please rectify these user id's before giving access."
	MSG_USER_MGMT_DUPLICATE_USERID = "Please contact respective admin for any change in status of the user."
	MSG_EQUP_MINICONF_AS_YES = "Please make sure that you have selected column \"Configure As\".";
	MSG_EQUP_MINICONF_AS_IF_NOT_NULL = "Please make sure that you have selected column \"Configure\"."
	/* RegEx Constant*/  
	
	REG_EX_NAME = "^[a-zA-Z0-9-_]*$";
	REG_EX_NAME_WITH_COMMA = "^[a-zA-Z0-9-_,.]*$";
	REG_EX_NUMBER = "^[0-9]*$";
	REG_EX_EITHERIP_OR_HOST = "(((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)(\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3})|^((([a-zA-Z]|[a-zA-Z][a-zA-Z0-9\-]*[a-zA-Z0-9])\.)*([A-Za-z0-9]|[A-Za-z][A-Za-z0-9\-]*[A-Za-z0-9]))$)";
	//REG_EX_DNS = "(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9][0-9]|[0-9])";
	REG_EX_NTP = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$|([0-9a-fA-F:]{1,4})+|([A-Za-z]+[.A-Za-z0-9_-]+)";
	REG_EX_MAC_ADDRESS = "([0-9A-F]{2}[:]){5}([0-9A-F]{2})$"; // for 6 slot
	REG_EX_WWXN = "([0-9A-F]{2}[:]){7}([0-9A-F]{2})$"; // for 8 slot
	//REG_EX_WWXN = "(((20)|([5]{1}[0-9A-F]{1}))[:])([0-9A-F]{2}[:]){6}([0-9A-F]{2})$"; // for 8 slot
	REG_EX_NUMBER_UPTO1000 = "(1000)|([1-9][0-9][0-9])|([1-9][0-9])|([1-9])";
	REG_EX_NUMBER_UPTO255 = "25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9]";
	REG_EX_NUMBER_1TO255 = "25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[1-9]";
	REG_EX_NUMBER_1_TO_20 = "20|1[0-9]|[1-9]";
	REG_EX_NUMBER_0_TO_23 = "2[0-3]|1[0-9]|[0-9]";
	REG_EX_NUMBER_1_TO_8 = "[1-8]";
	REG_EX_NUMBER_1_TO_48 = "4[0-8]|[1-3][0-9]|[1-9]";
	REG_EX_NUMBER_1_TO_30 = "3[0]|[1-2][0-9]|[1-9]";
	REG_EX_IPADDRESS = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	REG_EX_FCOE_VSAN_VLAN_ID = "(408[0-9])|(40[0-7][0-8])|(40[0-9][0-3])|([1-3][0-9][0-9][0-9])|([1-9][0-9][0-9])|([1-9][0-9])|([1-9])";
	REG_EX_HEXADECIMAL_HYPHEN_8X4X4 = "([0-9A-F]{8}[-])([0-9A-F]{4}[-])([0-9A-F]{4})$";
	REG_EX_HEXADECIMAL_HYPHEN_4X12 = "([0-9A-F]{4}[-])([0-9A-F]{12})$";
	REG_EX_NUMBER_1_TO_3967_OR_4048_TO_4093 = "(409[0-3])|(40[5-8][0-9])|(404[8-9])|3[0-9][0-5][0-9]|(3[0-9][6][0-7])|([1-2][0-9][0-9][0-9])|([1-9][0-9][0-9])|([1-9][0-9])|([1-9])";
	REG_EX_QOS_RATE = "((line-rate)|(40000000)|([1-3][0-9][0-9][0-9][0-9][0-9][0-9][0-9])|([1-9][0-9]{1,6})|([8-9]))";
	REG_EX_NUMBER_0_TO_65535 = "((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9][0-9])|(6[0-4][0-9][0-9][0-9])|([0-5][0-9][0-9][0-9][0-9])|([0-9][0-9]{0,3}))";
	REG_EX_NUMBER_1_TO_65535 = "((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9][0-9])|(6[0-4][0-9][0-9][0-9])|([1-5][0-9][0-9][0-9][0-9])|([1-9][0-9]{0,3}))";
	REG_EX_NUMBER_60_TO_172800 = "(172800)|(172[0-7][0-9][0-9])|(17[0-1][0-9][0-9][0-9])|(1[0-6][0-9][0-9][0-9][0-9])|([1-9][0-9]{2,4})|([6-9][0-9])";
	REG_EX_NUMBER_64_TO_128 = "12[0-8]|1[0-1][0-9]|[7-9][0-9]|6[4-9]";
	REG_EX_NUMBER_64_TO_512 = "51[0-2]|50[0-9]|[1-4][0-9][0-9]|[7-9][0-9]|6[4-9]";
	REG_EX_NUMBER_1000_TO_255000 = "255000|25[0-4][0-9]{3,4}|2[0-4][0-9]{4,5}|1[0-9]{5,6}|[1-9][0-9]{3,4}";
	REG_EX_NUMBER_0_TO_240000 = "240000|2[0-3][0-9]{4}|1[0-9]{4,5}|[0-9][0-9]{0,4}";
	REG_EX_NUMBER_1_TO_1024 = "102[0-4]|10[0-1][0-9]|[1-9][0-9]{0,2}";
	REG_EX_NUMBER_256_TO_1024 = "102[0-4]|10[0-1][0-9]|[3-9][0-9][0-9]|2[6-9][0-9]|25[6-9]";
	REG_EX_NUMBER_0_TO_60 = "(60|[1-5][0-9]|[0-9])";
	REG_EX_NUMBER_0_TO_59 = "(5[0-9]|[1-5][0-9]|[0-9])";
	REG_EX_NUMBER_0_TO_5 = "[0-5]";
	REG_EX_NUMBER_1_TO_5000000 = "((0-9)|(1[0-9]{0,6})|(2[0-9]{0,6})|(3[0-9]{0,6})|(4[0-9]{0,6})|(5[0]{0,6})|(6[0-9]{0,5})|(7[0-9]{0,5})|(8[0-9]{0,5})|(9[0]{0,5}))";
	REG_EX_NUMBER_1_TO_256 = "25[0-6]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[1-9]";
	REG_EX_NUMBER_64_TO_4096 = "(409[0-6])|(40[0-8][0-9])|[1-3][0-9][0-9][0-9]|([1-9][0-9][0-9])|([7-9][0-9])|(6[4-9])";
	REG_EX_NUMBER_1_TO_512 = "51[0-2]|50[0-9]|[1-4][0-9][0-9]|[1-9][0-9]|[1-9]";
	REG_EX_NUMBER_1_TO_514 = "51[0-4]|50[0-9]|[1-4][0-9][0-9]|[1-9][0-9]|[1-9]";
	REG_EX_NUMBER_0_TO_600 = "600|[1-5][0-9][0-9]|[1-9][0-9]|[0-9]";
	REG_EX_EMAIL_ADDERESS = "^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$";
	REG_EX_PHONE = "^\\+(?:[0-9] ?){6,14}[0-9]$";
	REG_EX_TIMESTAMP = "^(((\\d{4})(-)(0[13578]|10|12)(-)(0[1-9]|[12][0-9]|3[01]))|((\\d{4})(-)(0[469]|1‌​1)(-)([0][1-9]|[12][0-9]|30))|((\\d{4})(-)(02)(-)(0[1-9]|1[0-9]|2[0-8]))|(([02468]‌​[048]00)(-)(02)(-)(29))|(([13579][26]00)(-)(02)(-)(29))|(([0-9][0-9][0][48])(-)(0‌​2)(-)(29))|(([0-9][0-9][2468][048])(-)(02)(-)(29))|(([0-9][0-9][13579][26])(-)(02‌​)(-)(29)))([T]([0-1][0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9]))$"
	LABEL_SELECT = "--Select--";  
	
	UUIDPOOL_PREFIX_VALUE = "00000000-0000-0000";
}

 