DROP DATABASE IF EXISTS `ucsada`;
CREATE DATABASE  IF NOT EXISTS `ucsada` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ucsada`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: dcuc-lnx    Database: ucspdi
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


--
-- Table structure for table `project_details`
--

DROP TABLE IF EXISTS `project_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(45) DEFAULT NULL,
  `created_date_time` datetime DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `createdBy` varchar(45) DEFAULT NULL,
  `isUploaded` tinyint(1) DEFAULT NULL,
  `theatre` varchar(45) DEFAULT NULL,
  `skip_san` tinyint(1) DEFAULT 0,
  `ip_pool_assignment_order` varchar(45) DEFAULT 'default',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `time_zone`
--

DROP TABLE IF EXISTS `time_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_zone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `country_code` varchar(10) DEFAULT NULL,
  `timezone` varchar(45) DEFAULT NULL,
  `comments` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dns`
--

DROP TABLE IF EXISTS `dns`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dns` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dns_project_id_fk` (`project_id`),
  CONSTRAINT `dns_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ntp`
--

DROP TABLE IF EXISTS `ntp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ntp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ntp_project_id_fk` (`project_id`),
  CONSTRAINT `ntp_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_settings`
--

DROP TABLE IF EXISTS `admin_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `time_zone` int(11) DEFAULT NULL,
  `dns_references` int(11) DEFAULT NULL,
  `ntp_references` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_project_id_fk` (`project_id`),
  KEY `admin_time_zone_fk` (`time_zone`),
  KEY `dns_fk` (`dns_references`),
  KEY `ntp_fk` (`ntp_references`),
  CONSTRAINT `admin_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `admin_time_zone_fk` FOREIGN KEY (`time_zone`) REFERENCES `time_zone` (`id`),
  CONSTRAINT `dns_fk` FOREIGN KEY (`dns_references`) REFERENCES `dns` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ntp_fk` FOREIGN KEY (`ntp_references`) REFERENCES `ntp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(250) DEFAULT NULL,
  `ping_verified` tinyint(1) DEFAULT NULL,
  `push_status` tinyint(1) DEFAULT NULL,
  `download_config_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `conf_project_id_fk` (`project_id`),
  CONSTRAINT `conf_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `credentials`
--

DROP TABLE IF EXISTS `credentials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credentials` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipment_chasis`
--

DROP TABLE IF EXISTS `equipment_chasis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_chasis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `cdp_action` varchar(45) DEFAULT NULL,
  `cdp_link_agg` varchar(45) DEFAULT NULL,
  `psp_power_supply` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `equip_chasis_project_id_fk` (`project_id`),
  CONSTRAINT `equip_chasis_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipment_server`
--

DROP TABLE IF EXISTS `equipment_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_server` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `fi_id` varchar(45) DEFAULT NULL,
  `port_id` int(11) DEFAULT NULL,
  `slot_id` int(11) DEFAULT NULL,
  `chassis` int(11) NOT NULL,
  `user_label` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `equip_server_project_id_fk` (`project_id`),
  CONSTRAINT `equip_server_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipment_uplink`
--

DROP TABLE IF EXISTS `equipment_uplink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_uplink` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `fi_id` varchar(45) DEFAULT NULL,
  `port_id` int(11) DEFAULT NULL,
  `slot_id` int(11) DEFAULT NULL,
  `user_label` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `equip_uplink_project_id_fk` (`project_id`),
  CONSTRAINT `equip_uplink_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipment_fcport`
--

DROP TABLE IF EXISTS `equipment_fcport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_fcport` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `fi_id` varchar(45) DEFAULT NULL,
  `port_id` int(11) DEFAULT NULL,
  `slot_id` int(11) DEFAULT NULL,
  `user_label` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `equip_fcport_project_id_fk` (`project_id`),
  CONSTRAINT `equip_fcport_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `infrastructure`
--

DROP TABLE IF EXISTS `infrastructure`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `infrastructure` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `server_model` int(11) DEFAULT NULL,
  `software_version` varchar(45) DEFAULT NULL,
  `io_module` varchar(45) DEFAULT NULL,
  -- Not required field suggested by John
  -- `power_adapter_type` int(11) DEFAULT NULL,
  `image` blob,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `infra_server_model_fk` (`server_model`),
  KEY `infra_project_id_fk` (`project_id`),
  CONSTRAINT `infra_server_model_fk` FOREIGN KEY (`server_model`) REFERENCES `server_model` (`id`),
  CONSTRAINT `infra_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lan_macpool`
--

DROP TABLE IF EXISTS `lan_macpool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_macpool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `macpool_name` varchar(45) DEFAULT NULL,
  `macpool_description` varchar(45) DEFAULT NULL,
  `from_address` varchar(45) DEFAULT NULL,
  `to_address` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lan_macpool_project_id_fk` (`project_id`),
  KEY `lan_macpool_org_fk` (`organization`),
  CONSTRAINT `lan_macpool_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `lan_macpool_org_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lan_portchannel`
--

DROP TABLE IF EXISTS `lan_portchannel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_portchannel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `fi_id` varchar(45) DEFAULT NULL,
  `fi_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `uplink_id` int(11) DEFAULT NULL,
  `chasis_id` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lan_portchannel_project_id_fk` (`project_id`),
  KEY `lan_portchannel_uplink_id_fk` (`uplink_id`),
  CONSTRAINT `lan_portchannel_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `lan_portchannel_uplink_id_fk` FOREIGN KEY (`uplink_id`) REFERENCES `equipment_uplink` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lan_vlan`
--

DROP TABLE IF EXISTS `lan_vlan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_vlan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `vlan_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `vlan_id` varchar(45) DEFAULT NULL,
  `vlan_default` boolean DEFAULT false,
  PRIMARY KEY (`id`),
  KEY `lan_vlan_project_id_fk` (`project_id`),
  CONSTRAINT `lan_vlan_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lan_vnic`
--

DROP TABLE IF EXISTS `lan_vnic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_vnic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `vnic_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `macpool_id` int(11) DEFAULT NULL,
  `switch_id` varchar(45) DEFAULT NULL,
  `target` varchar(45) DEFAULT NULL,
  `template_type` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `os_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lan_vnic_project_id_fk` (`project_id`),
  KEY `lan_vnic_org_id_fk` (`organization`),
  KEY `lan_vnic_macpool_id_fk` (`macpool_id`),
  CONSTRAINT `lan_vnic_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `lan_vnic_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE,
  CONSTRAINT `lan_vnic_macpool_id_fk` FOREIGN KEY (`macpool_id`) REFERENCES `lan_macpool` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `lan_mgmt_ippool`
--

DROP TABLE IF EXISTS `lan_mgmt_ippool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_mgmt_ippool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ippool_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `from_address` varchar(45) DEFAULT NULL,
  `to_address` varchar(45) DEFAULT NULL,
  `default_gateway` varchar(45) DEFAULT NULL,
  `subnet_mask` varchar(45) DEFAULT NULL,
  `dns` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `mgmt_ippool_project_id_fk` (`project_id`),
  KEY `mgmt_ippool_org_id` (`organization`),
  CONSTRAINT `mgmt_ippool_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `mgmt_ippool_org_id` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`)  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `lan_vnic_vlan_mapping`
--
DROP TABLE IF EXISTS `lan_vnic_vlan_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_vnic_vlan_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vnic_id` int(11) NOT NULL,
  `vlan_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `lvvm_vnic_id_fk` (`vnic_id`),
  KEY `lvvm_vlan_id_fk` (`vlan_id`),
  CONSTRAINT `lvvm_vnic_id_fk` FOREIGN KEY (`vnic_id`) REFERENCES `lan_vnic` (`id`) ON DELETE CASCADE,
  CONSTRAINT `lvvm_vlan_id_fk` FOREIGN KEY (`vlan_id`) REFERENCES `lan_vlan` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `license`
--

DROP TABLE IF EXISTS `license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `license` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `assessor_id` varchar(45) DEFAULT NULL,
  `assessor_name` varchar(45) DEFAULT NULL,
  `assessor_type` varchar(45) DEFAULT NULL,
  `purpose` varchar(45) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `target_start_time` datetime DEFAULT NULL,
  `target_end_time` datetime DEFAULT NULL,
  `theatre` varchar(45) DEFAULT NULL,
  `customer_name` varchar(45) DEFAULT NULL,
  `customer_business` varchar(45) DEFAULT NULL,
  `customer_description` varchar(45) DEFAULT NULL,
  `customer_vertical` varchar(45) DEFAULT NULL,
  `as_pid` varchar(45) DEFAULT NULL,
  `site` varchar(45) DEFAULT NULL,
  `createdby` varchar(45) DEFAULT NULL,
  `downloadedby` varchar(45) DEFAULT NULL,
  `site_id` varchar(45) DEFAULT NULL,
  `SERVICE_TYPE` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `main_menu_state`
--

DROP TABLE IF EXISTS `main_menu_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_menu_state` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Table structure for table `module_access`
--

DROP TABLE IF EXISTS `module_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `isAllowed` tinyint(1) DEFAULT NULL,
  `roles` int(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `module_access_roles_fk` (`roles`),
  CONSTRAINT `module_access_roles_fk` FOREIGN KEY (`roles`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `modules`
--

DROP TABLE IF EXISTS `modules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `modules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organizations`
--

DROP TABLE IF EXISTS `organizations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `organizations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `org_project_id_fk` (`project_id`),
  KEY `org_id_fk` (`parent_id`),
  CONSTRAINT `org_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `org_id_fk` FOREIGN KEY (`parent_id`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `power_adapter`
--

DROP TABLE IF EXISTS `power_adapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `power_adapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `power_adapter_project_id_fk` (`project_id`),
  CONSTRAINT `power_adapter_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `san_vhba`
--

DROP TABLE IF EXISTS `san_vhba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_vhba` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `vhba_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `wwpn_pool_id` int(11) DEFAULT NULL,
  `switch_id` varchar(45) DEFAULT NULL,
  `template_type` varchar(45) DEFAULT NULL,
  `vsan_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `san_vhba_project_id_fk` (`project_id`),
  KEY `san_vhba_org_id_fk` (`organization`),
  KEY `san_vhba_wwpn_pool_id_fk` (`wwpn_pool_id`),
  KEY `san_vhba_vsan_id_fk` (`vsan_id`),
  CONSTRAINT `san_vhba_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_vhba_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_vhba_wwpn_pool_id_fk` FOREIGN KEY (`wwpn_pool_id`) REFERENCES `san_wwpn` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_vhba_vsan_id_fk` FOREIGN KEY (`vsan_id`) REFERENCES `san_vsan` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `san_vsan`
--

DROP TABLE IF EXISTS `san_vsan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_vsan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `fi_id` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `fcoe_vlan_name` varchar(45) DEFAULT NULL,
  `vsan_name` varchar(45) DEFAULT NULL,
  `fcoe_vsan_id` int(11) DEFAULT NULL,
  `uplink_config` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `san_vsan_project_id_fk` (`project_id`),
  KEY `san_vsan_org_id_fk` (`organization`),
  CONSTRAINT `san_vsan_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_vsan_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `san_wwnn`
--

DROP TABLE IF EXISTS `san_wwnn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_wwnn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `wwnn_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `from_address` varchar(45) DEFAULT NULL,
  `to_address` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `san_wwnn_project_id_fk` (`project_id`),
  KEY `san_wwnn_org_id_fk` (`organization`),
  CONSTRAINT `san_wwnn_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_wwnn_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `san_wwpn`
--

DROP TABLE IF EXISTS `san_wwpn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_wwpn` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `wwpn_name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `from_address` varchar(45) DEFAULT NULL,
  `to_address` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `san_wwpn_project_id_fk` (`project_id`),
  KEY `san_wwpn_org_id_fk` (`organization`),
  CONSTRAINT `san_wwpn_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_wwpn_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `server_model`
--

DROP TABLE IF EXISTS `server_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `server_model` (
  `id` int(11) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  `software_version` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_boot_policy`
--

DROP TABLE IF EXISTS `servers_boot_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_boot_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `enforce_vnic_name` tinyint(1) DEFAULT NULL,
  `reboot_on_update` tinyint(1) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `boot_mode` varchar(45) DEFAULT NULL,
  `secure_boot` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_b_p_project_id_fk` (`project_id`),
  KEY `s_b_p_org_id_fk` (`organization`),
  CONSTRAINT `s_b_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_b_p_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_boot_policy_lan`
--

DROP TABLE IF EXISTS `servers_boot_policy_lan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_boot_policy_lan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `lan_order` int(11) DEFAULT NULL,
  `vnic_id` int(11) DEFAULT NULL,
  `boot_policy_type` varchar(45) DEFAULT NULL,
  `boot_policy_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `s_b_p_lan_project_id_fk` (`project_id`),
  KEY `s_b_p_vnic_id_fk` (`vnic_id`),
  KEY `s_b_p_boot_policy_id_fk` (`boot_policy_id`),
  CONSTRAINT `s_b_p_boot_policy_id_fk` FOREIGN KEY (`boot_policy_id`) REFERENCES `servers_boot_policy` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_b_p_lan_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_b_p_lan_vnic_id_fk` FOREIGN KEY (`vnic_id`) REFERENCES `lan_vnic` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_boot_policy_san`
--

DROP TABLE IF EXISTS `servers_boot_policy_san`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_boot_policy_san` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `san_order` varchar(45) DEFAULT NULL,
  `boot_policy_type` varchar(45) DEFAULT NULL,
  `vhba_id` int(11) DEFAULT NULL,
  `boot_policy_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `s_b_p_san_project_id_fk` (`project_id`),
  KEY `s_b_p_san_vhba_id_fk` (`vhba_id`),
  KEY `s_b_p_san_boot_policy_id_fk` (`boot_policy_id`),
  CONSTRAINT `s_b_p_san_boot_policy_id_fk` FOREIGN KEY (`boot_policy_id`) REFERENCES `servers_boot_policy` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_b_p_san_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_b_p_san_vhba_id_fk` FOREIGN KEY (`vhba_id`) REFERENCES `san_vhba` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_boot_policy_san_target`
--
DROP TABLE IF EXISTS `servers_boot_policy_san_target`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_boot_policy_san_target` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `target_type` varchar(45) DEFAULT NULL,
  `lun_id` int(11) DEFAULT NULL,
  `wwpn_address` varchar(45) DEFAULT NULL,
  `boot_policy_san_id` int(11) NOT NULL,
  `boot_policy_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `s_b_p_san_target_san_id_fk` (`boot_policy_san_id`),
  KEY `s_b_p_san_target_boot_policy_id_fk` (`boot_policy_id`),
  CONSTRAINT `s_b_p_san_target_san_id_fk` FOREIGN KEY (`boot_policy_san_id`) REFERENCES `servers_boot_policy_san` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_b_p_san_target_boot_policy_id_fk` FOREIGN KEY (`boot_policy_id`) REFERENCES `servers_boot_policy` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_boot_policy_local_disc`
--

DROP TABLE IF EXISTS `servers_boot_policy_local_disc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_boot_policy_local_disc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `device` varchar(45) DEFAULT NULL,
  `boot_order` int(11) DEFAULT NULL,
  `boot_policy_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `s_b_p_l_project_id_fk` (`project_id`),
  KEY `s_b_p_l_boot_policy_id_fk` (`boot_policy_id`),
  CONSTRAINT `s_b_p_l_boot_policy_id_fk` FOREIGN KEY (`boot_policy_id`) REFERENCES `servers_boot_policy` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_b_p_l_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_local_disc`
--

DROP TABLE IF EXISTS `servers_local_disc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_local_disc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `mode` varchar(45) DEFAULT NULL,
  `protect_configuration` tinyint(1) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_l_d_project_id_fk` (`project_id`),
  KEY `s_l_d_org_id_fk` (`organization`),
  CONSTRAINT `s_l_d_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_l_d_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_server_pool`
--

DROP TABLE IF EXISTS `servers_server_pool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_server_pool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_s_p_project_id_fk` (`project_id`),
  KEY `s_s_p_org_id_fk` (`organization`),
  CONSTRAINT `s_s_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_s_p_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_server_pool_policy`
--

DROP TABLE IF EXISTS `servers_server_pool_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_server_pool_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `server_pool_id` int(11) DEFAULT NULL,
  `qualifier_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_s_p_p_project_id_fk` (`project_id`),
  KEY `s_s_p_p_org_id_fk` (`organization`),
  KEY `s_s_p_p_server_pool_id_fk` (`server_pool_id`),
  KEY `s_s_p_p_qualifier_id_fk` (`qualifier_id`),
  CONSTRAINT `s_s_p_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_s_p_p_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_s_p_p_server_pool_id_fk` FOREIGN KEY (`server_pool_id`) REFERENCES `servers_server_pool` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_s_p_p_qualifier_id_fk` FOREIGN KEY (`qualifier_id`) REFERENCES `servers_server_pool_qualifier` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_server_pool_qualifier`
--

DROP TABLE IF EXISTS `servers_server_pool_qualifier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_server_pool_qualifier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `chassis_min_id` int(11) DEFAULT NULL,
  `chassis_max_id` int(11) DEFAULT NULL,
  `rack_min_id` int(11) DEFAULT NULL,
  `rack_max_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_s_p_q_project_id_fk` (`project_id`),
  KEY `s_s_p_q_org_id_fk` (`organization`),
  CONSTRAINT `s_s_p_q_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_s_p_q_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_spq_slot_mapping`
--
DROP TABLE IF EXISTS `servers_spq_slot_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_spq_slot_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `min_id` int(11) DEFAULT NULL,
  `max_id` int(11) DEFAULT NULL,
  `spq_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sssm_spq_id_fk` (`spq_id`),
  CONSTRAINT `sssm_spq_id_fk` FOREIGN KEY (`spq_id`) REFERENCES `servers_server_pool_qualifier` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `servers_service_profile_template`
--

DROP TABLE IF EXISTS `servers_service_profile_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_service_profile_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `profile_template_type` varchar(45) DEFAULT NULL,
  `boot_policy_id` int(11) DEFAULT NULL,
  `server_pool_id` int(11) DEFAULT NULL,
  `wwnn_id` int(11) DEFAULT NULL,
  `uuid_id` int(11) DEFAULT NULL,
  `local_disk_policy_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_p_t_project_id_fk` (`project_id`),
  KEY `s_p_t_org_id_fk` (`organization`),
  KEY `s_p_t_boot_policy_id_fk` (`boot_policy_id`),
  KEY `s_p_t_server_pool_id_fk` (`server_pool_id`),
  KEY `s_p_t_san_wwnn_id_fk` (`wwnn_id`),
  KEY `s_p_t_servers_uuid_id_fk` (`uuid_id`),
  KEY `s_p_t_local_disk_policy_id_fk` (`local_disk_policy_id`),
  CONSTRAINT `s_p_t_local_disk_policy_id_fk` FOREIGN KEY (`local_disk_policy_id`) REFERENCES `servers_local_disc` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_p_t_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_p_t_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_p_t_boot_policy_id_fk` FOREIGN KEY (`boot_policy_id`) REFERENCES `servers_boot_policy` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_p_t_server_pool_id_fk` FOREIGN KEY (`server_pool_id`) REFERENCES `servers_server_pool` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_p_t_san_wwnn_id_fk` FOREIGN KEY (`wwnn_id`) REFERENCES `san_wwnn` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_p_t_servers_uuid_id_fk` FOREIGN KEY (`uuid_id`) REFERENCES `servers_uuid_pool` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `servers_spt_vnic_mapping`
--
DROP TABLE IF EXISTS `servers_spt_vnic_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_spt_vnic_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vnic_id` int(11) DEFAULT NULL,
  `spt_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ssvm_vnic_id_fk` (`vnic_id`),
  KEY `ssvm_spt_id_fk` (`spt_id`),
  CONSTRAINT `ssvm_vnic_id_fk` FOREIGN KEY (`vnic_id`) REFERENCES `lan_vnic` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ssvm_spt_id_fk` FOREIGN KEY (`spt_id`) REFERENCES `servers_service_profile_template` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_spt_vhba_mapping`
--
DROP TABLE IF EXISTS `servers_spt_vhba_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_spt_vhba_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vhba_id` int(11) DEFAULT NULL,
  `spt_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `sptvm_vhba_id_fk` (`vhba_id`),
  KEY `sptvm_spt_id_fk` (`spt_id`),
  CONSTRAINT `sptvm_vhba_id_fk` FOREIGN KEY (`vhba_id`) REFERENCES `san_vhba` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sptvm_spt_id_fk` FOREIGN KEY (`spt_id`) REFERENCES `servers_service_profile_template` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_service_profile`
--

DROP TABLE IF EXISTS `servers_service_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_service_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `template_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ssp_project_id_fk` (`project_id`),
  KEY `ssp_org_id_fk` (`organization`),
  KEY `ssp_template_id_fk` (`template_id`),
  CONSTRAINT `ssp_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ssp_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ssp_template_id_fk` FOREIGN KEY (`template_id`) REFERENCES `servers_service_profile_template` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_uuid_pool`
--

DROP TABLE IF EXISTS `servers_uuid_pool`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_uuid_pool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `prefix` varchar(45) DEFAULT NULL,
  `from_address` varchar(45) DEFAULT NULL,
  `to_address` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_u_p_project_id_fk` (`project_id`),
  KEY `s_u_p_org_id_fk` (`organization`),
  CONSTRAINT `s_u_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_u_p_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sub_menu_state`
--

DROP TABLE IF EXISTS `sub_menu_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_menu_state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_menu_id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `main_menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_m_s_main_munu_id_fk` (`main_menu_id`),
  CONSTRAINT `s_m_s_main_menu_id_fk` FOREIGN KEY (`main_menu_id`) REFERENCES `main_menu_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wizard_status`
--

DROP TABLE IF EXISTS `wizard_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wizard_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `main_menu_id` int(11) DEFAULT NULL,
  `sub_menu_id` int(11) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  `isCompleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `wiz_project_id_fk` (`project_id`),
  KEY `wiz_main_menu_id_fk` (`main_menu_id`),
  KEY `wiz_sub_menu_id_fk` (`sub_menu_id`),
  CONSTRAINT `wiz_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `wiz_main_menu_id_fk` FOREIGN KEY (`main_menu_id`) REFERENCES `main_menu_state` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ucs_server_logs`
--
DROP TABLE IF EXISTS `ucs_server_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ucs_server_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `ip_address` varchar(45) DEFAULT NULL,
  `name` varchar(250) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `usl_project_id_fk` (`project_id`),
  CONSTRAINT `usl_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project_settings`
--

DROP TABLE IF EXISTS `project_settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_settings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `project_key` varchar(45) NOT NULL,
  `project_value` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ps_project_id_fk` (`project_id`),
  CONSTRAINT `ps_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for maintaining user role in table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping events for database 'ucspdi'
--

--
-- Dumping routines for database 'ucspdi'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-07 16:58:37




-- Default values for main_menu_state
insert into main_menu_state (id, name) values (0, 'Infrastructure');
insert into main_menu_state (id, name) values (1, 'Admin');
insert into main_menu_state (id, name) values (2, 'Equipment');
insert into main_menu_state (id, name) values (3, 'LAN');
insert into main_menu_state (id, name) values (4, 'SAN');
insert into main_menu_state (id, name) values (5, 'Servers');
insert into main_menu_state (id, name) values (6, 'Configure');



-- Default values for sub_menu_state

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (0, 2, 'Chasis Policy');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (1, 2, 'Server Ports');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (2, 2, 'Uplink Ports');

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (0, 3, 'Port Channel');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (1, 3, 'Vlan & Mac-pool');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (2, 3, 'vNIC Template');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (3, 3, 'Management IP Pool');

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (0, 4, 'VSAN');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (1, 4, 'WWxN');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (2, 4, 'vHBA Template');

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (0, 5, 'UUID Pool');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (1, 5, 'Boot Policy');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (2, 5, 'Local Disk Policy');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (3, 5, 'Server Pool');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (4, 5, 'Qualifier & Policy');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (5, 5, 'Service Template');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (6, 5, 'Template Instantiate');

-- Default value for parent id of root organizations

insert into organizations values(0, null, 'NA', null);

-- Default values for server_model

insert into server_model (id,description,software_version) values (1,'Cisco UCS 6296','2.2');
insert into server_model (id,description,software_version) values (2,'Cisco UCS 6248','2.2');
-- added in 4.0 release
insert into server_model (id,description,software_version) values (3,'Cisco UCS 6324','3.0');


-- Default user credentials

insert into credentials (id, username, password, isActive) VALUES ('1', 'dcaf', '6db50be6da89f7c6b4218a9d6d1ff40e5f1cdff48bf134e8ce4ff7714833e7a0', '1');
insert into credentials (id, username, password, isActive) VALUES ('2', 'admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '1');
insert into credentials (id, username, password, isActive) VALUES ('3', 'ucspdi', 'c0873c4bd90f1a2c08ca00ed56ace1b1d254f4f50d1b9c55cfca22f4da49a573', '1');
insert into credentials (id, username, password, isActive) VALUES ('4', 'config', 'b79606fb3afea5bd1609ed40b622142f1c98125abcfe89a76a661b0e8e343910', '1');


-- Default Time Zone Values

insert into time_zone (country_code, timezone, comments) values ('CI' , 'Africa/Abidjan', '');
insert into time_zone (country_code, timezone, comments) values ('GH' , 'Africa/Accra', '');
insert into time_zone (country_code, timezone, comments) values ('ET' , 'Africa/Addis_Ababa', '');
insert into time_zone (country_code, timezone, comments) values ('DZ' , 'Africa/Algiers', '');
insert into time_zone (country_code, timezone, comments) values ('ER' , 'Africa/Asmera', '');
insert into time_zone (country_code, timezone, comments) values ('ML' , 'Africa/Bamako', '');
insert into time_zone (country_code, timezone, comments) values ('CF' , 'Africa/Bangui', '');
insert into time_zone (country_code, timezone, comments) values ('GM' , 'Africa/Banjul', '');
insert into time_zone (country_code, timezone, comments) values ('GW' , 'Africa/Bissau', '');
insert into time_zone (country_code, timezone, comments) values ('MW' , 'Africa/Blantyre', '');
insert into time_zone (country_code, timezone, comments) values ('CG' , 'Africa/Brazzaville', '');
insert into time_zone (country_code, timezone, comments) values ('BI' , 'Africa/Bujumbura', '');
insert into time_zone (country_code, timezone, comments) values ('EG' , 'Africa/Cairo', '');
insert into time_zone (country_code, timezone, comments) values ('MA' , 'Africa/Casablanca', '');
insert into time_zone (country_code, timezone, comments) values ('ES' , 'Africa/Ceuta', 'Ceuta & Melilla');
insert into time_zone (country_code, timezone, comments) values ('GN' , 'Africa/Conakry', '');
insert into time_zone (country_code, timezone, comments) values ('SN' , 'Africa/Dakar', '');
insert into time_zone (country_code, timezone, comments) values ('TZ' , 'Africa/Dar_es_Salaam', '');
insert into time_zone (country_code, timezone, comments) values ('DJ' , 'Africa/Djibouti', '');
insert into time_zone (country_code, timezone, comments) values ('CM' , 'Africa/Douala', '');
insert into time_zone (country_code, timezone, comments) values ('EH' , 'Africa/El_Aaiun', '');
insert into time_zone (country_code, timezone, comments) values ('SL' , 'Africa/Freetown', '');
insert into time_zone (country_code, timezone, comments) values ('BW' , 'Africa/Gaborone', '');
insert into time_zone (country_code, timezone, comments) values ('ZW' , 'Africa/Harare', '');
insert into time_zone (country_code, timezone, comments) values ('ZA' , 'Africa/Johannesburg', '');
insert into time_zone (country_code, timezone, comments) values ('UG' , 'Africa/Kampala', '');
insert into time_zone (country_code, timezone, comments) values ('SD' , 'Africa/Khartoum', '');
insert into time_zone (country_code, timezone, comments) values ('RW' , 'Africa/Kigali', '');
insert into time_zone (country_code, timezone, comments) values ('CD' , 'Africa/Kinshasa ', 'west Dem. Rep. of Congo');
insert into time_zone (country_code, timezone, comments) values ('NG' , 'Africa/Lagos', '');
insert into time_zone (country_code, timezone, comments) values ('GA' , 'Africa/Libreville', '');
insert into time_zone (country_code, timezone, comments) values ('TG' , 'Africa/Lome', '');
insert into time_zone (country_code, timezone, comments) values ('AO' , 'Africa/Luanda', '');
insert into time_zone (country_code, timezone, comments) values ('CD' , 'Africa/Lubumbashi', 'east Dem. Rep. of Congo');
insert into time_zone (country_code, timezone, comments) values ('ZM' , 'Africa/Lusaka', '');
insert into time_zone (country_code, timezone, comments) values ('GQ' , 'Africa/Malabo', '');
insert into time_zone (country_code, timezone, comments) values ('MZ' , 'Africa/Maputo', '');
insert into time_zone (country_code, timezone, comments) values ('LS' , 'Africa/Maseru', '');
insert into time_zone (country_code, timezone, comments) values ('SZ' , 'Africa/Mbabane', '');
insert into time_zone (country_code, timezone, comments) values ('SO' , 'Africa/Mogadishu', '');
insert into time_zone (country_code, timezone, comments) values ('LR' , 'Africa/Monrovia', '');
insert into time_zone (country_code, timezone, comments) values ('KE' , 'Africa/Nairobi', '');
insert into time_zone (country_code, timezone, comments) values ('TD' , 'Africa/Ndjamena', '');
insert into time_zone (country_code, timezone, comments) values ('NE' , 'Africa/Niamey', '');
insert into time_zone (country_code, timezone, comments) values ('MR' , 'Africa/Nouakchott', '');
insert into time_zone (country_code, timezone, comments) values ('BF' , 'Africa/Ouagadougou', '');
insert into time_zone (country_code, timezone, comments) values ('BJ' , 'Africa/Porto-Novo', '');
insert into time_zone (country_code, timezone, comments) values ('ST' , 'Africa/Sao_Tome', '');
insert into time_zone (country_code, timezone, comments) values ('LY' , 'Africa/Tripoli', '');
insert into time_zone (country_code, timezone, comments) values ('TN' , 'Africa/Tunis', '');
insert into time_zone (country_code, timezone, comments) values ('NA' , 'Africa/Windhoek', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Adak', 'Aleutian Islands');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Anchorage', 'Alaska Time');
insert into time_zone (country_code, timezone, comments) values ('AI' , 'America/Anguilla', '');
insert into time_zone (country_code, timezone, comments) values ('AG' , 'America/Antigua', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Araguaina', 'Tocantins');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Buenos_Aires  ', 'Buenos Aires (BA, CF)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Catamarca', 'Catamarca (CT), Chubut (CH)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Cordoba', 'most locations (CB, CC, CN, ER, FM, LP, MN, NQ, RN, SA, SE, SF, SL)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Jujuy ', 'Jujuy (JY)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/La_Rioja', 'La Rioja (LR)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Mendoza', 'Mendoza (MZ)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Rio_Gallegos  ', 'Santa Cruz (SC)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/San_Juan', 'San Juan (SJ)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Tucuman', 'Tucuman (TM)');
insert into time_zone (country_code, timezone, comments) values ('AR' , 'America/Argentina/Ushuaia', 'Tierra del Fuego (TF)');
insert into time_zone (country_code, timezone, comments) values ('AW' , 'America/Aruba', '');
insert into time_zone (country_code, timezone, comments) values ('PY' , 'America/Asuncion', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Bahia   ', 'Bahia');
insert into time_zone (country_code, timezone, comments) values ('BB' , 'America/Barbados', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Belem   ', 'Amapa, E Para');
insert into time_zone (country_code, timezone, comments) values ('BZ' , 'America/Belize', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Boa_Vista', 'Roraima');
insert into time_zone (country_code, timezone, comments) values ('CO' , 'America/Bogota', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Boise', 'Mountain Time - south Idaho & east Oregon');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Cambridge_Bay   ', 'Central Time - west Nunavut');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Campo_Grande', 'Mato Grosso do Sul');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Cancun  ', 'Central Time - Quintana Roo');
insert into time_zone (country_code, timezone, comments) values ('VE' , 'America/Caracas', '');
insert into time_zone (country_code, timezone, comments) values ('GF' , 'America/Cayenne', '');
insert into time_zone (country_code, timezone, comments) values ('KY' , 'America/Cayman', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Chicago ', 'Central Time');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Chihuahua', 'Mountain Time - Chihuahua');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Coral_Harbour  ', 'Eastern Standard Time - Southampton Island');
insert into time_zone (country_code, timezone, comments) values ('CR' , 'America/Costa_Rica', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Cuiaba  ', 'Mato Grosso');
insert into time_zone (country_code, timezone, comments) values ('AN' , 'America/Curacao', '');
insert into time_zone (country_code, timezone, comments) values ('GL' , 'America/Danmarkshavn', 'east coast, north of Scoresbysund');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Dawson  ', 'Pacific Time - north Yukon');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Dawson_Creek', 'Mountain Standard Time - Dawson Creek & Fort Saint John, British Columbia');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Denver', 'Mountain Time');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Detroit ', 'Eastern Time - Michigan - most locations');
insert into time_zone (country_code, timezone, comments) values ('DM' , 'America/Dominica', '');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Edmonton', 'Mountain Time - Alberta, east British Columbia & west Saskatchewan');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Eirunepe', 'W Amazonas');
insert into time_zone (country_code, timezone, comments) values ('SV' , 'America/El_Salvador', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Fortaleza', 'NE Brazil (MA, PI, CE, RN, PB)');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Glace_Bay', 'Atlantic Time - Nova Scotia - places that did not observe DST 1966-1971');
insert into time_zone (country_code, timezone, comments) values ('GL' , 'America/Godthab ', 'most locations');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Goose_Bay', 'Atlantic Time - E Labrador');
insert into time_zone (country_code, timezone, comments) values ('TC' , 'America/Grand_Turk', '');
insert into time_zone (country_code, timezone, comments) values ('GD' , 'America/Grenada', '');
insert into time_zone (country_code, timezone, comments) values ('GP' , 'America/Guadeloupe', '');
insert into time_zone (country_code, timezone, comments) values ('GT' , 'America/Guatemala', '');
insert into time_zone (country_code, timezone, comments) values ('EC' , 'America/Guayaquil', 'mainland');
insert into time_zone (country_code, timezone, comments) values ('GY' , 'America/Guyana', '');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Halifax', 'Atlantic Time - Nova Scotia (most places), NB, W Labrador, E Quebec & PEI');
insert into time_zone (country_code, timezone, comments) values ('CU' , 'America/Havana', '');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Hermosillo', 'Mountain Standard Time - Sonora');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Indiana/Indianapolis', 'Eastern Standard Time - Indiana - most locations');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Indiana/Knox', 'Eastern Standard Time - Indiana - Starke County');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Indiana/Marengo ', 'Eastern Standard Time - Indiana - Crawford County');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Indiana/Vevay   ', 'Eastern Standard Time - Indiana - Switzerland County');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Inuvik  ', 'Mountain Time - west Northwest Territories');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Iqaluit ', 'Eastern Time - east Nunavut');
insert into time_zone (country_code, timezone, comments) values ('JM' , 'America/Jamaica', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Juneau', 'Alaska Time - Alaska panhandle');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Kentucky/Louisville', 'Eastern Time - Kentucky - Louisville area');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Kentucky/Monticello', 'Eastern Time - Kentucky - Wayne County');
insert into time_zone (country_code, timezone, comments) values ('BO' , 'America/La_Paz', '');
insert into time_zone (country_code, timezone, comments) values ('PE' , 'America/Lima', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Los_Angeles', 'Pacific Time');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Maceio  ', 'Alagoas, Sergipe');
insert into time_zone (country_code, timezone, comments) values ('NI' , 'America/Managua', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Manaus  ', 'E Amazonas');
insert into time_zone (country_code, timezone, comments) values ('MQ' , 'America/Martinique', '');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Mazatlan', 'Mountain Time - S Baja, Nayarit, Sinaloa');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Menominee', 'Central Time - Michigan - Wisconsin border');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Merida  ', 'Central Time - Campeche, Yucatan');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Mexico_City', 'Central Time - most locations');
insert into time_zone (country_code, timezone, comments) values ('PM' , 'America/Miquelon', '');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Monterrey', 'Central Time - Coahuila, Durango, Nuevo Leon, Tamaulipas');
insert into time_zone (country_code, timezone, comments) values ('UY' , 'America/Montevideo', '');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Montreal', 'Eastern Time - Quebec - most locations');
insert into time_zone (country_code, timezone, comments) values ('MS' , 'America/Montserrat', '');
insert into time_zone (country_code, timezone, comments) values ('BS' , 'America/Nassau', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/New_York', 'Eastern Time');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Nipigon', 'Eastern Time - Ontario & Quebec - places that did not observe DST 1967-1973');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Nome', 'Alaska Time - west Alaska');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Noronha ', 'Atlantic islands');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/North_Dakota/Center', 'Central Time - North Dakota - Oliver County');
insert into time_zone (country_code, timezone, comments) values ('PA' , 'America/Panama', '');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Pangnirtung', 'Eastern Time - Pangnirtung, Nunavut');
insert into time_zone (country_code, timezone, comments) values ('SR' , 'America/Paramaribo', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Phoenix', 'Mountain Standard Time - Arizona');
insert into time_zone (country_code, timezone, comments) values ('TT' , 'America/Port_of_Spain', '');
insert into time_zone (country_code, timezone, comments) values ('HT' , 'America/Port-au-Prince', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Porto_Velho', 'W Para, Rondonia');
insert into time_zone (country_code, timezone, comments) values ('PR' , 'America/Puerto_Rico', '');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Rainy_River', 'Central Time - Rainy River & Fort Frances, Ontario');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Rankin_Inlet', 'Central Time - central Nunavut');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Recife  ', 'Pernambuco');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Regina  ', 'Central Standard Time - Saskatchewan - most locations');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Rio_Branco', 'Acre');
insert into time_zone (country_code, timezone, comments) values ('CL' , 'America/Santiago', 'most locations');
insert into time_zone (country_code, timezone, comments) values ('DO' , 'America/Santo_Domingo', '');
insert into time_zone (country_code, timezone, comments) values ('BR' , 'America/Sao_Paulo', 'S & SE Brazil (GO, DF, MG, ES, RJ, SP, PR, SC, RS)');
insert into time_zone (country_code, timezone, comments) values ('GL' , 'America/Scoresbysund', 'Scoresbysund / Ittoqqortoormiit');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Shiprock', 'Mountain Time - Navajo');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/St_Johns', 'Newfoundland Island');
insert into time_zone (country_code, timezone, comments) values ('KN' , 'America/St_Kitts', '');
insert into time_zone (country_code, timezone, comments) values ('LC' , 'America/St_Lucia', '');
insert into time_zone (country_code, timezone, comments) values ('VI' , 'America/St_Thomas', '');
insert into time_zone (country_code, timezone, comments) values ('VC' , 'America/St_Vincent', '');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Swift_Current   ', 'Central Standard Time - Saskatchewan - midwest');
insert into time_zone (country_code, timezone, comments) values ('HN' , 'America/Tegucigalpa', '');
insert into time_zone (country_code, timezone, comments) values ('GL' , 'America/Thule   ', 'Thule / Pituffik');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Thunder_Bay', 'Eastern Time - Thunder Bay, Ontario');
insert into time_zone (country_code, timezone, comments) values ('MX' , 'America/Tijuana ', 'Pacific Time');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Toronto', 'Eastern Time - Ontario - most locations');
insert into time_zone (country_code, timezone, comments) values ('VG' , 'America/Tortola', '');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Vancouver', 'Pacific Time - west British Columbia');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Whitehorse', 'Pacific Time - south Yukon');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Winnipeg', 'Central Time - Manitoba & west Ontario');
insert into time_zone (country_code, timezone, comments) values ('US' , 'America/Yakutat', 'Alaska Time - Alaska panhandle neck');
insert into time_zone (country_code, timezone, comments) values ('CA' , 'America/Yellowknife', 'Mountain Time - central Northwest Territories');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/Casey', 'Casey Station, Bailey Peninsula');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/Davis', 'Davis Station, Vestfold Hills');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/DumontDUrville', 'Dumont-d&#39;Urville Base, Terre Adelie');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/Mawson', 'Mawson Station, Holme Bay');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/McMurdo', 'McMurdo Station, Ross Island');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/Palmer', 'Palmer Station, Anvers Island');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/Rothera', 'Rothera Station, Adelaide Island');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/South_Pole', 'Amundsen-Scott Station, South Pole');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/Syowa', 'Syowa Station, E Ongul I');
insert into time_zone (country_code, timezone, comments) values ('AQ' , 'Antarctica/Vostok', 'Vostok Station, S Magnetic Pole');
insert into time_zone (country_code, timezone, comments) values ('SJ' , 'Arctic/Longyearbyen', 'Svalbard');
insert into time_zone (country_code, timezone, comments) values ('YE' , 'Asia/Aden', '');
insert into time_zone (country_code, timezone, comments) values ('KZ' , 'Asia/Almaty', 'most locations');
insert into time_zone (country_code, timezone, comments) values ('JO' , 'Asia/Amman', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Anadyr', 'Moscow+10 - Bering Sea');
insert into time_zone (country_code, timezone, comments) values ('KZ' , 'Asia/Aqtau', 'Atyrau (Atirau, Gur&#39;yev), Mangghystau (Mankistau)');
insert into time_zone (country_code, timezone, comments) values ('KZ' , 'Asia/Aqtobe', 'Aqtobe (Aktobe)');
insert into time_zone (country_code, timezone, comments) values ('TM' , 'Asia/Ashgabat', '');
insert into time_zone (country_code, timezone, comments) values ('IQ' , 'Asia/Baghdad', '');
insert into time_zone (country_code, timezone, comments) values ('BH' , 'Asia/Bahrain', '');
insert into time_zone (country_code, timezone, comments) values ('AZ' , 'Asia/Baku', '');
insert into time_zone (country_code, timezone, comments) values ('TH' , 'Asia/Bangkok', '');
insert into time_zone (country_code, timezone, comments) values ('LB' , 'Asia/Beirut', '');
insert into time_zone (country_code, timezone, comments) values ('KG' , 'Asia/Bishkek', '');
insert into time_zone (country_code, timezone, comments) values ('BN' , 'Asia/Brunei', '');
insert into time_zone (country_code, timezone, comments) values ('IN' , 'Asia/Calcutta', '');
insert into time_zone (country_code, timezone, comments) values ('MN' , 'Asia/Choibalsan ', 'Dornod, Sukhbaatar');
insert into time_zone (country_code, timezone, comments) values ('CN' , 'Asia/Chongqing  ', 'central China - Gansu, Guizhou, Sichuan, Yunnan, etc.');
insert into time_zone (country_code, timezone, comments) values ('LK' , 'Asia/Colombo', '');
insert into time_zone (country_code, timezone, comments) values ('SY' , 'Asia/Damascus', '');
insert into time_zone (country_code, timezone, comments) values ('BD' , 'Asia/Dhaka', '');
insert into time_zone (country_code, timezone, comments) values ('TL' , 'Asia/Dili', '');
insert into time_zone (country_code, timezone, comments) values ('AE' , 'Asia/Dubai', '');
insert into time_zone (country_code, timezone, comments) values ('TJ' , 'Asia/Dushanbe', '');
insert into time_zone (country_code, timezone, comments) values ('PS' , 'Asia/Gaza', '');
insert into time_zone (country_code, timezone, comments) values ('CN' , 'Asia/Harbin', 'Heilongjiang');
insert into time_zone (country_code, timezone, comments) values ('HK' , 'Asia/Hong_Kong', '');
insert into time_zone (country_code, timezone, comments) values ('MN' , 'Asia/Hovd', 'Bayan-Olgiy, Govi-Altai, Hovd, Uvs, Zavkhan');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Irkutsk', 'Moscow+05 - Lake Baikal');
insert into time_zone (country_code, timezone, comments) values ('ID' , 'Asia/Jakarta', 'Java & Sumatra');
insert into time_zone (country_code, timezone, comments) values ('ID' , 'Asia/Jayapura   ', 'Irian Jaya & the Moluccas');
insert into time_zone (country_code, timezone, comments) values ('IL' , 'Asia/Jerusalem', '');
insert into time_zone (country_code, timezone, comments) values ('AF' , 'Asia/Kabul', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Kamchatka  ', 'Moscow+09 - Kamchatka');
insert into time_zone (country_code, timezone, comments) values ('PK' , 'Asia/Karachi', '');
insert into time_zone (country_code, timezone, comments) values ('CN' , 'Asia/Kashgar', 'southwest Xinjiang Uyghur');
insert into time_zone (country_code, timezone, comments) values ('NP' , 'Asia/Katmandu', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Krasnoyarsk', 'Moscow+04 - Yenisei River');
insert into time_zone (country_code, timezone, comments) values ('MY' , 'Asia/Kuala_Lumpur', 'peninsular Malaysia');
insert into time_zone (country_code, timezone, comments) values ('MY' , 'Asia/Kuching', 'Sabah & Sarawak');
insert into time_zone (country_code, timezone, comments) values ('KW' , 'Asia/Kuwait', '');
insert into time_zone (country_code, timezone, comments) values ('MO' , 'Asia/Macau', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Magadan', 'Moscow+08 - Magadan');
insert into time_zone (country_code, timezone, comments) values ('ID' , 'Asia/Makassar  ', 'east & south Borneo, Celebes, Bali, Nusa Tengarra, west Timor');
insert into time_zone (country_code, timezone, comments) values ('PH' , 'Asia/Manila', '');
insert into time_zone (country_code, timezone, comments) values ('OM' , 'Asia/Muscat', '');
insert into time_zone (country_code, timezone, comments) values ('CY' , 'Asia/Nicosia', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Novosibirsk', 'Moscow+03 - Novosibirsk');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Omsk', 'Moscow+03 - west Siberia');
insert into time_zone (country_code, timezone, comments) values ('KZ' , 'Asia/Oral', 'West Kazakhstan');
insert into time_zone (country_code, timezone, comments) values ('KH' , 'Asia/Phnom_Penh', '');
insert into time_zone (country_code, timezone, comments) values ('ID' , 'Asia/Pontianak ', 'west & central Borneo');
insert into time_zone (country_code, timezone, comments) values ('KP' , 'Asia/Pyongyang', '');
insert into time_zone (country_code, timezone, comments) values ('QA' , 'Asia/Qatar', '');
insert into time_zone (country_code, timezone, comments) values ('KZ' , 'Asia/Qyzylorda  ', 'Qyzylorda (Kyzylorda, Kzyl-Orda)');
insert into time_zone (country_code, timezone, comments) values ('MM' , 'Asia/Rangoon', '');
insert into time_zone (country_code, timezone, comments) values ('SA' , 'Asia/Riyadh', '');
insert into time_zone (country_code, timezone, comments) values ('VN' , 'Asia/Saigon', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Sakhalin   ', 'Moscow+07 - Sakhalin Island');
insert into time_zone (country_code, timezone, comments) values ('UZ' , 'Asia/Samarkand', 'west Uzbekistan');
insert into time_zone (country_code, timezone, comments) values ('KR' , 'Asia/Seoul', '');
insert into time_zone (country_code, timezone, comments) values ('CN' , 'Asia/Shanghai   ', 'east China - Beijing, Guangdong, Shanghai, etc.');
insert into time_zone (country_code, timezone, comments) values ('SG' , 'Asia/Singapore', '');
insert into time_zone (country_code, timezone, comments) values ('TW' , 'Asia/Taipei', '');
insert into time_zone (country_code, timezone, comments) values ('UZ' , 'Asia/Tashkent', 'east Uzbekistan');
insert into time_zone (country_code, timezone, comments) values ('GE' , 'Asia/Tbilisi', '');
insert into time_zone (country_code, timezone, comments) values ('IR' , 'Asia/Tehran', '');
insert into time_zone (country_code, timezone, comments) values ('BT' , 'Asia/Thimphu', '');
insert into time_zone (country_code, timezone, comments) values ('JP' , 'Asia/Tokyo', '');
insert into time_zone (country_code, timezone, comments) values ('MN' , 'Asia/Ulaanbaatar', 'most locations');
insert into time_zone (country_code, timezone, comments) values ('CN' , 'Asia/Urumqi', 'Tibet & most of Xinjiang Uyghur');
insert into time_zone (country_code, timezone, comments) values ('LA' , 'Asia/Vientiane', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Vladivostok', 'Moscow+07 - Amur River');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Yakutsk', 'Moscow+06 - Lena River');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Asia/Yekaterinburg', 'Moscow+02 - Urals');
insert into time_zone (country_code, timezone, comments) values ('AM' , 'Asia/Yerevan', '');
insert into time_zone (country_code, timezone, comments) values ('PT' , 'Atlantic/Azores Azores', '');
insert into time_zone (country_code, timezone, comments) values ('BM' , 'Atlantic/Bermuda', '');
insert into time_zone (country_code, timezone, comments) values ('ES' , 'Atlantic/Canary ', '');
insert into time_zone (country_code, timezone, comments) values ('CV' , 'Atlantic/Cape_Verde', '');
insert into time_zone (country_code, timezone, comments) values ('FO' , 'Atlantic/Faeroe', '');
insert into time_zone (country_code, timezone, comments) values ('SJ' , 'Atlantic/Jan_Mayen', 'Jan Mayen');
insert into time_zone (country_code, timezone, comments) values ('PT' , 'Atlantic/Madeira', 'Madeira Islands');
insert into time_zone (country_code, timezone, comments) values ('IS' , 'Atlantic/Reykjavik', '');
insert into time_zone (country_code, timezone, comments) values ('GS' , 'Atlantic/South_Georgia', '');
insert into time_zone (country_code, timezone, comments) values ('SH' , 'Atlantic/St_Helena', '');
insert into time_zone (country_code, timezone, comments) values ('FK' , 'Atlantic/Stanley', '');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Adelaide', 'South Australia');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Brisbane', 'Queensland - most locations');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Broken_Hill   ', 'New South Wales - Yancowinna');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Currie', 'Tasmania - King Island');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Darwin', 'Northern Territory');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Hobart', 'Tasmania - most locations');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Lindeman', 'Queensland - Holiday Islands');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Lord_Howe', 'Lord Howe Island');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Melbourne', 'Victoria');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Perth ', 'Western Australia');
insert into time_zone (country_code, timezone, comments) values ('AU' , 'Australia/Sydney', 'New South Wales - most locations');
insert into time_zone (country_code, timezone, comments) values ('NL' , 'Europe/Amsterdam', '');
insert into time_zone (country_code, timezone, comments) values ('AD' , 'Europe/Andorra', '');
insert into time_zone (country_code, timezone, comments) values ('GR' , 'Europe/Athens', '');
insert into time_zone (country_code, timezone, comments) values ('CS' , 'Europe/Belgrade', '');
insert into time_zone (country_code, timezone, comments) values ('DE' , 'Europe/Berlin', '');
insert into time_zone (country_code, timezone, comments) values ('SK' , 'Europe/Bratislava', '');
insert into time_zone (country_code, timezone, comments) values ('BE' , 'Europe/Brussels', '');
insert into time_zone (country_code, timezone, comments) values ('RO' , 'Europe/Bucharest', '');
insert into time_zone (country_code, timezone, comments) values ('HU' , 'Europe/Budapest', '');
insert into time_zone (country_code, timezone, comments) values ('MD' , 'Europe/Chisinau', '');
insert into time_zone (country_code, timezone, comments) values ('DK' , 'Europe/Copenhagen', '');
insert into time_zone (country_code, timezone, comments) values ('IE' , 'Europe/Dublin', '');
insert into time_zone (country_code, timezone, comments) values ('GI' , 'Europe/Gibraltar', '');
insert into time_zone (country_code, timezone, comments) values ('FI' , 'Europe/Helsinki', '');
insert into time_zone (country_code, timezone, comments) values ('TR' , 'Europe/Istanbul', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Europe/Kaliningrad', 'Moscow-01 - Kaliningrad');
insert into time_zone (country_code, timezone, comments) values ('UA' , 'Europe/Kiev', 'most locations');
insert into time_zone (country_code, timezone, comments) values ('PT' , 'Europe/Lisbon   ', 'mainland');
insert into time_zone (country_code, timezone, comments) values ('SI' , 'Europe/Ljubljana', '');
insert into time_zone (country_code, timezone, comments) values ('GB' , 'Europe/London', '');
insert into time_zone (country_code, timezone, comments) values ('LU' , 'Europe/Luxembourg', '');
insert into time_zone (country_code, timezone, comments) values ('ES' , 'Europe/Madrid  ', 'mainland');
insert into time_zone (country_code, timezone, comments) values ('MT' , 'Europe/Malta', '');
insert into time_zone (country_code, timezone, comments) values ('AX' , 'Europe/Mariehamn', '');
insert into time_zone (country_code, timezone, comments) values ('BY' , 'Europe/Minsk', '');
insert into time_zone (country_code, timezone, comments) values ('MC' , 'Europe/Monaco', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Europe/Moscow   ', 'Moscow+00 - west Russia');
insert into time_zone (country_code, timezone, comments) values ('NO' , 'Europe/Oslo', '');
insert into time_zone (country_code, timezone, comments) values ('FR' , 'Europe/Paris', '');
insert into time_zone (country_code, timezone, comments) values ('CZ' , 'Europe/Prague', '');
insert into time_zone (country_code, timezone, comments) values ('LV' , 'Europe/Riga', '');
insert into time_zone (country_code, timezone, comments) values ('IT' , 'Europe/Rome', '');
insert into time_zone (country_code, timezone, comments) values ('RU' , 'Europe/Samara   ', 'Moscow+01 - Caspian Sea');
insert into time_zone (country_code, timezone, comments) values ('SM' , 'Europe/San_Marino', '');
insert into time_zone (country_code, timezone, comments) values ('BA' , 'Europe/Sarajevo', '');
insert into time_zone (country_code, timezone, comments) values ('UA' , 'Europe/Simferopol', 'central Crimea');
insert into time_zone (country_code, timezone, comments) values ('MK' , 'Europe/Skopje', '');
insert into time_zone (country_code, timezone, comments) values ('BG' , 'Europe/Sofia', '');
insert into time_zone (country_code, timezone, comments) values ('SE' , 'Europe/Stockholm', '');
insert into time_zone (country_code, timezone, comments) values ('EE' , 'Europe/Tallinn', '');
insert into time_zone (country_code, timezone, comments) values ('AL' , 'Europe/Tirane', '');
insert into time_zone (country_code, timezone, comments) values ('UA' , 'Europe/Uzhgorod Ruthenia', '');
insert into time_zone (country_code, timezone, comments) values ('LI' , 'Europe/Vaduz', '');
insert into time_zone (country_code, timezone, comments) values ('VA' , 'Europe/Vatican', '');
insert into time_zone (country_code, timezone, comments) values ('AT' , 'Europe/Vienna', '');
insert into time_zone (country_code, timezone, comments) values ('LT' , 'Europe/Vilnius', '');
insert into time_zone (country_code, timezone, comments) values ('PL' , 'Europe/Warsaw', '');
insert into time_zone (country_code, timezone, comments) values ('HR' , 'Europe/Zagreb', '');
insert into time_zone (country_code, timezone, comments) values ('UA' , 'Europe/Zaporozhye', 'Zaporozh&#39;ye, E Lugansk');
insert into time_zone (country_code, timezone, comments) values ('CH' , 'Europe/Zurich', '');
insert into time_zone (country_code, timezone, comments) values ('MG' , 'Indian/Antananarivo', '');
insert into time_zone (country_code, timezone, comments) values ('IO' , 'Indian/Chagos', '');
insert into time_zone (country_code, timezone, comments) values ('CX' , 'Indian/Christmas', '');
insert into time_zone (country_code, timezone, comments) values ('CC' , 'Indian/Cocos', '');
insert into time_zone (country_code, timezone, comments) values ('KM' , 'Indian/Comoro', '');
insert into time_zone (country_code, timezone, comments) values ('TF' , 'Indian/Kerguelen', '');
insert into time_zone (country_code, timezone, comments) values ('SC' , 'Indian/Mahe', '');
insert into time_zone (country_code, timezone, comments) values ('MV' , 'Indian/Maldives', '');
insert into time_zone (country_code, timezone, comments) values ('MU' , 'Indian/Mauritius', '');
insert into time_zone (country_code, timezone, comments) values ('YT' , 'Indian/Mayotte', '');
insert into time_zone (country_code, timezone, comments) values ('RE' , 'Indian/Reunion', '');
insert into time_zone (country_code, timezone, comments) values ('WS' , 'Pacific/Apia', '');
insert into time_zone (country_code, timezone, comments) values ('NZ' , 'Pacific/Auckland', 'most locations');
insert into time_zone (country_code, timezone, comments) values ('NZ' , 'Pacific/Chatham ', 'Chatham Islands');
insert into time_zone (country_code, timezone, comments) values ('CL' , 'Pacific/Easter  ', 'Easter Island & Sala y Gomez');
insert into time_zone (country_code, timezone, comments) values ('VU' , 'Pacific/Efate', '');
insert into time_zone (country_code, timezone, comments) values ('KI' , 'Pacific/Enderbury', 'Phoenix Islands');
insert into time_zone (country_code, timezone, comments) values ('TK' , 'Pacific/Fakaofo', '');
insert into time_zone (country_code, timezone, comments) values ('FJ' , 'Pacific/Fiji', '');
insert into time_zone (country_code, timezone, comments) values ('TV' , 'Pacific/Funafuti', '');
insert into time_zone (country_code, timezone, comments) values ('EC' , 'Pacific/Galapagos', 'Galapagos Islands');
insert into time_zone (country_code, timezone, comments) values ('PF' , 'Pacific/Gambier ', 'Gambier Islands');
insert into time_zone (country_code, timezone, comments) values ('SB' , 'Pacific/Guadalcanal', '');
insert into time_zone (country_code, timezone, comments) values ('GU' , 'Pacific/Guam', '');
insert into time_zone (country_code, timezone, comments) values ('US' , 'Pacific/Honolulu', 'Hawaii');
insert into time_zone (country_code, timezone, comments) values ('UM' , 'Pacific/Johnston', 'Johnston Atoll');
insert into time_zone (country_code, timezone, comments) values ('KI' , 'Pacific/Kiritimati', 'Line Islands');
insert into time_zone (country_code, timezone, comments) values ('FM' , 'Pacific/Kosrae ', 'Kosrae');
insert into time_zone (country_code, timezone, comments) values ('MH' , 'Pacific/Kwajalein', 'Kwajalein');
insert into time_zone (country_code, timezone, comments) values ('MH' , 'Pacific/Majuro  ', 'most locations');
insert into time_zone (country_code, timezone, comments) values ('PF' , 'Pacific/Marquesas', 'Marquesas Islands');
insert into time_zone (country_code, timezone, comments) values ('UM' , 'Pacific/Midway  ', 'Midway Islands');
insert into time_zone (country_code, timezone, comments) values ('NR' , 'Pacific/Nauru', '');
insert into time_zone (country_code, timezone, comments) values ('NU' , 'Pacific/Niue', '');
insert into time_zone (country_code, timezone, comments) values ('NF' , 'Pacific/Norfolk', '');
insert into time_zone (country_code, timezone, comments) values ('NC' , 'Pacific/Noumea', '');
insert into time_zone (country_code, timezone, comments) values ('AS' , 'Pacific/Pago_Pago', '');
insert into time_zone (country_code, timezone, comments) values ('PW' , 'Pacific/Palau', '');
insert into time_zone (country_code, timezone, comments) values ('PN' , 'Pacific/Pitcairn', '');
insert into time_zone (country_code, timezone, comments) values ('FM' , 'Pacific/Ponape  ', 'Ponape (Pohnpei)');
insert into time_zone (country_code, timezone, comments) values ('PG' , 'Pacific/Port_Moresby', '');
insert into time_zone (country_code, timezone, comments) values ('CK' , 'Pacific/Rarotonga', '');
insert into time_zone (country_code, timezone, comments) values ('MP' , 'Pacific/Saipan', '');
insert into time_zone (country_code, timezone, comments) values ('PF' , 'Pacific/Tahiti  ', 'Society Islands');
insert into time_zone (country_code, timezone, comments) values ('KI' , 'Pacific/Tarawa  ', 'Gilbert Islands');
insert into time_zone (country_code, timezone, comments) values ('TO' , 'Pacific/Tongatapu', '');
insert into time_zone (country_code, timezone, comments) values ('FM' , 'Pacific/Truk', 'Truk (Chuuk) and Yap');
insert into time_zone (country_code, timezone, comments) values ('UM' , 'Pacific/Wake', 'Wake Island');
insert into time_zone (country_code, timezone, comments) values ('WF' , 'Pacific/Wallis', '');


-- remove unused columns
 
alter table `admin_settings` drop foreign key `dns_fk`;
alter table `admin_settings` drop foreign key `ntp_fk`;
alter table `admin_settings` drop column `dns_references`;
alter table `admin_settings` drop column `ntp_references`;
alter table `lan_portchannel` drop column `chasis_id`;
alter table `lan_portchannel` drop column `state`;
alter table `san_vsan` drop foreign key `san_vsan_org_id_fk`;
alter table `san_vsan` drop column `organization`;
alter table `san_vsan` drop column `uplink_config`;
alter table `servers_server_pool_qualifier` drop column `rack_min_id`;
alter table `servers_server_pool_qualifier` drop column `rack_max_id`;


DROP TABLE IF EXISTS `san_vhba_template`;
rename table `san_vhba` to `san_vhba_template`;

alter table `san_vhba_template` drop foreign key `san_vhba_project_id_fk`;
alter table `san_vhba_template` add CONSTRAINT `san_vhbat_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE;

alter table `san_vhba_template` drop foreign key `san_vhba_org_id_fk`;
alter table `san_vhba_template` add  CONSTRAINT `san_vhbat_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE;

alter table `san_vhba_template` drop foreign key `san_vhba_wwpn_pool_id_fk`;
alter table `san_vhba_template` add  CONSTRAINT `san_vhbat_wwpn_pool_id_fk` FOREIGN KEY (`wwpn_pool_id`) REFERENCES `san_wwpn` (`id`) ON DELETE CASCADE;

alter table `san_vhba_template` drop foreign key `san_vhba_vsan_id_fk`;
alter table `san_vhba_template` add  CONSTRAINT `san_vhbat_vsan_id_fk` FOREIGN KEY (`vsan_id`) REFERENCES `san_vsan` (`id`) ON DELETE CASCADE;

alter table `servers_boot_policy_san` drop foreign key `s_b_p_san_vhba_id_fk`;
alter table `servers_boot_policy_san` change `vhba_id` `vhbat_id` int(11) DEFAULT NULL;
alter table `servers_boot_policy_san` add CONSTRAINT `s_b_p_san_vhbat_id_fk` FOREIGN KEY (`vhbat_id`) REFERENCES `san_vhba_template` (`id`) ON DELETE CASCADE;

DROP TABLE IF EXISTS `servers_spt_vhbat_mapping`;
rename table `servers_spt_vhba_mapping` to `servers_spt_vhbat_mapping`;
alter table `servers_spt_vhbat_mapping` drop foreign key `sptvm_vhba_id_fk`;
alter table `servers_spt_vhbat_mapping` change `vhba_id` `vhbat_id` int(11) DEFAULT NULL;
alter table `servers_spt_vhbat_mapping` add CONSTRAINT `sptvm_vhbat_id_fk` FOREIGN KEY (`vhbat_id`) REFERENCES `san_vhba_template` (`id`) ON DELETE CASCADE;

--
-- Table structure for table `lan_qos_policy`
--

DROP TABLE IF EXISTS `lan_qos_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_qos_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `priority` varchar(45) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `burst` int(11) DEFAULT NULL,
  `rate` varchar(45) DEFAULT NULL,
  `host_control` varchar(45) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `qos_policy_project_id_fk` (`project_id`),
  KEY `qos_policy_org_id` (`organization`),
  CONSTRAINT `qos_policy_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `qos_policy_org_id` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`)  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lan_network_control_policy`
--

DROP TABLE IF EXISTS `lan_network_control_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_network_control_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `ncp_name` varchar(16) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `cdp` varchar(45) DEFAULT NULL,
  `mac_register_mode` varchar(45) DEFAULT NULL,
  `uplink_fail_action` varchar(45) DEFAULT NULL,
  `forge` varchar(45) DEFAULT NULL,  
  PRIMARY KEY (`id`),
  KEY `l_n_c_p_project_id_fk` (`project_id`),
  KEY `l_n_c_p_org_id_fk` (`organization`),
  CONSTRAINT `l_n_c_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `l_n_c_p_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- Add fields in lan_vnic Table
alter table `lan_vnic` add column `qos_policy_id` int(11) DEFAULT NULL;
alter table `lan_vnic` add column `network_control_policy_id` int(11) DEFAULT NULL;
alter table `lan_vnic` add CONSTRAINT `lan_vnic_q_p_id_fk` FOREIGN KEY (`qos_policy_id`) REFERENCES `lan_qos_policy` (`id`) ON DELETE CASCADE;
alter table `lan_vnic` add CONSTRAINT `lan_vnic_n_c_p_id_fk` FOREIGN KEY (`network_control_policy_id`) REFERENCES `lan_network_control_policy` (`id`) ON DELETE CASCADE;

-- Add fields in san_vhba_template table
alter table `san_vhba_template` add column `qos_policy_id` int(11) DEFAULT NULL;
alter table `san_vhba_template` add CONSTRAINT `san_vhbat_q_p_id_fk` FOREIGN KEY (`qos_policy_id`) REFERENCES `lan_qos_policy` (`id`) ON DELETE CASCADE;

--
-- Table structure for table `san_adapter_policies`
--
DROP TABLE IF EXISTS `san_adapter_policies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_adapter_policies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `transmit_queues` int(11) DEFAULT NULL,
  `transmit_queues_ring_size` int(11) DEFAULT NULL,
  `receive_queues` int(11) DEFAULT NULL,
  `receive_queues_ring_size` int(11) DEFAULT NULL,
  `scsi_io_queues` int(11) DEFAULT NULL,
  `scsi_io_queues_ring_size` int(11) DEFAULT NULL,
  `fcp_error_recovery` varchar(45) DEFAULT NULL,
  `flogi_retries` int(11) DEFAULT NULL,
  `flogi_timeout` int(11) DEFAULT NULL,
  `plogi__retries` int(11) DEFAULT NULL,
  `plogi_timeout` int(11) DEFAULT NULL,
  `port_down_timeout` int(11) DEFAULT NULL,
  `port_down_io_retry` int(11) DEFAULT NULL,
  `link_down_timeout` int(11) DEFAULT NULL,
  `io_throttle_count` int(11) DEFAULT NULL,
  `luns_per_target` int(11) DEFAULT NULL,
  `interrupt_mode` varchar(45) DEFAULT NULL,
  `sap_default` boolean DEFAULT false,
  PRIMARY KEY (`id`),
  KEY `s_adapter_policy_project_id_fk` (`project_id`),
  KEY `s_adapter_policy_org_id_fk` (`organization`),
  CONSTRAINT `s_adapter_policy_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_adapter_policy_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Table structure for table `san_vhba`
--

DROP TABLE IF EXISTS `san_vhba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_vhba` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `vhba_template_id` int(11) DEFAULT NULL,
  `adapter_policy_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `san_vhba_project_id_fk` (`project_id`),
  KEY `san_vhba_temp_id_fk` (`vhba_template_id`),
  KEY `san_vhba_adapter_policy_id_fk` (`adapter_policy_id`),
  CONSTRAINT `san_vhba_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_vhba_temp_id_fk` FOREIGN KEY (`vhba_template_id`) REFERENCES `san_vhba_template` (`id`) ON DELETE CASCADE,
  CONSTRAINT `san_vhba_adapter_policy_id_fk` FOREIGN KEY (`adapter_policy_id`) REFERENCES `san_adapter_policies` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Table structure for table `san_connectivity_policy`
--

DROP TABLE IF EXISTS `san_connectivity_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_connectivity_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `wwnn_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_c_p_project_id_fk` (`project_id`),
  KEY `s_c_p_san_wwnn_id_fk` (`wwnn_id`),
  KEY `s_c_p_org_id_fk` (`organization`),
  CONSTRAINT `s_c_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_c_p_san_wwnn_id_fk` FOREIGN KEY (`wwnn_id`) REFERENCES `san_wwnn` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_c_p_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `san_scp_vhba_mapping`
--
DROP TABLE IF EXISTS `san_scp_vhba_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `san_scp_vhba_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vhba_id` int(11) NOT NULL,
  `scp_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `scpvm_vhba_id_fk` (`vhba_id`),
  KEY `scpvm_scp_id_fk` (`scp_id`),
  CONSTRAINT `scpvm_vhba_id_fk` FOREIGN KEY (`vhba_id`) REFERENCES `san_vhba` (`id`) ON DELETE CASCADE,
  CONSTRAINT `scpvm_scp_id_fk` FOREIGN KEY (`scp_id`) REFERENCES `san_connectivity_policy` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- alter Table servers_service_profile_template to add new field 'san_connectivity_policy_id'
--

ALTER TABLE `servers_service_profile_template` ADD san_connectivity_policy_id INT(11) after local_disk_policy_id ;

ALTER TABLE `servers_service_profile_template` ADD CONSTRAINT `s_p_t_san_connectivity_policy_id_fk`
    FOREIGN KEY (`san_connectivity_policy_id`)
    REFERENCES `san_connectivity_policy` (`id`)  ON DELETE CASCADE;
    
--
-- Table structure for table `temp_san_vhba`
--
DROP TABLE IF EXISTS `temp_san_vhba`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp_san_vhba` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vhba_template_name` varchar(16) DEFAULT NULL,
  `adapter_policy_name` varchar(16) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_ldap_provider`
--
DROP TABLE IF EXISTS `admin_ldap_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host_name` varchar(64) DEFAULT NULL,
  `provider_order` int(11) DEFAULT NULL,
  `bind_dn` varchar(128) DEFAULT NULL,
  `base_dn` varchar(128) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `enable_ssl` varchar(16) DEFAULT NULL,
  `filter` varchar(45) DEFAULT NULL,
  `attribute` varchar(45) DEFAULT NULL,
  `provider_password` varchar(45) DEFAULT NULL,
  `timeout` int(11) DEFAULT NULL,
  `vendor` varchar(45) DEFAULT NULL,
  `group_authorization` varchar(16) DEFAULT NULL,
  `group_recursion` varchar(16) DEFAULT NULL,
  `target_attribute` varchar(64) DEFAULT NULL,
  `use_primary_group` varchar(16) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a_l_p_project_id_fk` (`project_id`),
  CONSTRAINT `a_l_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `ada_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ada_log` (`log_message` varchar(4000));
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_ldap_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_general` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `timeout` VARCHAR(45) NULL,
  `attribute` VARCHAR(45) NULL,
  `baseDN` VARCHAR(45) NULL,
  `filter` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  KEY `a_l_p_project_id_fk` (`project_id`),
  CONSTRAINT `fk_project_id` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_ldap_provider_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_provider_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `name` VARCHAR(128) NULL,
  PRIMARY KEY (`id`),
  KEY `project_id_fk` (`project_id`),
  CONSTRAINT `project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_ldap_group_provider_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_group_provider_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ldap_group_id` int(11) NOT NULL,
  `ldap_provider_id` int(11) NOT NULL,
  `provider_order` INT(16),
  PRIMARY KEY (`id`),
  KEY `l_g_fk` (`ldap_group_id`),
  KEY `l_p_fk` (`ldap_provider_id`),
  CONSTRAINT `l_g_fk`FOREIGN KEY (`ldap_group_id`) REFERENCES `admin_ldap_provider_group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `l_p_fk`FOREIGN KEY (`ldap_provider_id`) REFERENCES `admin_ldap_provider` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

    
DROP TABLE IF EXISTS `admin_ldap_group_rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_group_rule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authorization` VARCHAR(45) NOT NULL,
  `recursion` VARCHAR(45) NOT NULL,
  `target` VARCHAR(64) NULL,
  `primaryGroup` VARCHAR(45) NULL,
  `admin_ldap_provider_id` INT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_ldap_provider_id_fk` (`admin_ldap_provider_id`),
  CONSTRAINT `admin_ldap_provider_id_fk` FOREIGN KEY (`admin_ldap_provider_id`) REFERENCES `admin_ldap_provider` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_radius_general`;    
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_radius_general` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `timeout` varchar(45),
  `retries` varchar(45),
  PRIMARY KEY (`id`),
  KEY `fk_radius_project_id_` (`project_id`),
  CONSTRAINT `fk_radius_project_id_` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_radius_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_radius_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `hostname` varchar(64) NOT NULL,
  `radius_order` int(11) NOT NULL,
  `authorization_port` int(11) NOT NULL,
  `timeout` int(11) NOT NULL,
  `retries` int(11) NOT NULL,
  `ssl_key` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_a_r_project_id_` (`project_id`),
  CONSTRAINT `fk_a_r_project_id_` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_radius_provider_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_radius_provider_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a_r_p_g_project_id_fk` (`project_id`),
  CONSTRAINT `a_r_p_g_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_radius_group_provider_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_radius_group_provider_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `radius_group_id` int(11) NOT NULL,
  `radius_provider_id` int(11) NOT NULL,
  `radius_provider_order` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a_r_g_p_m_group_id_fk` (`radius_group_id`),
  KEY `a_r_g_p_m_provider_id_fk` (`radius_provider_id`),
  CONSTRAINT `a_r_g_p_m_group_id_fk` FOREIGN KEY (`radius_group_id`) REFERENCES `admin_radius_provider_group` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `a_r_g_p_m_provider_id_fk` FOREIGN KEY (`radius_provider_id`) REFERENCES `admin_radius_provider` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_tacacs_provider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_tacacs_provider` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `host_name` varchar(64) DEFAULT NULL,
  `provider_order` int(11) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `provider_key` varchar(64) DEFAULT NULL,
  `timeout` int(11) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a_t_p_project_id_fk` (`project_id`),
  CONSTRAINT `a_t_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_tacacs_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_tacacs_general` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `timeout` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  KEY `a_t_g_project_id` (`project_id`),
  CONSTRAINT `a_t_g_project_id` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_tacacs_provider_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_tacacs_provider_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  KEY `a_t_p_g_project_id_fk` (`project_id`),
  CONSTRAINT `a_t_p_g_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `admin_tacacs_group_provider_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_tacacs_group_provider_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tacacs_group_id` int(11) NOT NULL,
  `tacacs_provider_id` int(11) NOT NULL,
  `provider_order` INT(16),
  PRIMARY KEY (`id`),
  KEY `a_t_g_p_m_group_id_fk` (`tacacs_group_id`),
  KEY `a_t_g_p_m_provider_id_fk` (`tacacs_provider_id`),
  CONSTRAINT `a_t_g_p_m_group_id_fk` FOREIGN KEY (`tacacs_group_id`) REFERENCES `admin_tacacs_provider_group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `a_t_g_p_m_provider_id_fk` FOREIGN KEY (`tacacs_provider_id`) REFERENCES `admin_tacacs_provider` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `admin_authentication_domain`
--

DROP TABLE IF EXISTS `admin_authentication_domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_authentication_domain` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `ldap_provider_group_id` int(11) DEFAULT NULL,
  `radius_provider_group_id` int(11) DEFAULT NULL,
  `tacacs_provider_group_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `refresh_period` int(11) DEFAULT NULL,
  `session_timeout` int(11) DEFAULT NULL,
  `realm` varchar(45) DEFAULT NULL,
  `two_factor` varchar(16) default NULL,
  PRIMARY KEY (`id`),
  KEY `admin_auth_domain_project_id_fk` (`project_id`),
  KEY `admin_auth_domain_ldap_p_g_id_fk` (`ldap_provider_group_id`),
  KEY `admin_auth_domain_radius_p_g_id_fk` (`radius_provider_group_id`),
  KEY `admin_auth_domain_tacacs_p_g_id_fk` (`tacacs_provider_group_id`),
  CONSTRAINT `admin_auth_domain_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `admin_auth_domain_ldap_p_g_id_fk` FOREIGN KEY (`ldap_provider_group_id`) REFERENCES `admin_ldap_provider_group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `admin_auth_domain_radius_p_g_id_fk` FOREIGN KEY (`radius_provider_group_id`) REFERENCES `admin_radius_provider_group` (`id`) ON DELETE CASCADE,
  CONSTRAINT `admin_auth_domain_tacacs_p_g_id_fk` FOREIGN KEY (`tacacs_provider_group_id`) REFERENCES `admin_tacacs_provider_group` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

 --
 -- Create table for maps and roles locales and privileges with all mapping.
 --   

DROP TABLE IF EXISTS `admin_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


  
DROP TABLE IF EXISTS `admin_ldap_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `name` VARCHAR(16) NULL,
  PRIMARY KEY (`id`),
  KEY `ldap_role_project_id_fk` (`project_id`),
  CONSTRAINT `ldap_role_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

 --
-- Table structure for table `admin_ldap_roles_privileges_mapping`
--

DROP TABLE IF EXISTS `admin_ldap_roles_privileges_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_roles_privileges_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ldap_role_id` int(11) NOT NULL,
  `roles_privileges_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `l_r_mapping_id_fk` (`ldap_role_id`),
  KEY `ldap_privileges_id_fk` (`roles_privileges_id`),
  CONSTRAINT `l_r_mapping_id_fk` FOREIGN KEY (`ldap_role_id`) REFERENCES `admin_ldap_role` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ldap_privileges_id_fk` FOREIGN KEY (`roles_privileges_id`) REFERENCES `admin_privilege` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `admin_ldap_locale`
--

DROP TABLE IF EXISTS `admin_ldap_locale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_locale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a_l_llocale_project_id_fk` (`project_id`),
  CONSTRAINT `a_l_llocale_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_ldap_locales_org_mapping`
--

DROP TABLE IF EXISTS `admin_ldap_locales_org_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_locales_org_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ldap_locale_id` int(11) NOT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `l_locale_mapping_id_fk` (`ldap_locale_id`),
  KEY `ldap_locale_org_mapping_id_fk` (`organization`),
  CONSTRAINT `l_locale_mapping_id_fk` FOREIGN KEY (`ldap_locale_id`) REFERENCES `admin_ldap_locale` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ldap_locale_org_mapping_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `admin_ldap_group_map`
--

DROP TABLE IF EXISTS `admin_ldap_group_map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_group_map` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a_l_g_m_project_id_fk` (`project_id`),
  CONSTRAINT `a_l_g_m_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_ldap_group_map_roles_mapping`
--

DROP TABLE IF EXISTS `admin_ldap_group_map_roles_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_group_map_roles_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ldap_group_map_id` int(11) NOT NULL,
  `ldap_role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a_l_g_m_mapping_id_fk` (`ldap_group_map_id`),
  KEY `a_l_roles_mapping_id_fk` (`ldap_role_id`),
  CONSTRAINT `a_l_g_m_mapping_id_fk` FOREIGN KEY (`ldap_group_map_id`) REFERENCES `admin_ldap_group_map` (`id`) ON DELETE CASCADE,
  CONSTRAINT `a_l_roles_mapping_id_fk` FOREIGN KEY (`ldap_role_id`) REFERENCES `admin_ldap_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_ldap_group_map_locales_mapping`
--

DROP TABLE IF EXISTS `admin_ldap_group_map_locales_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_ldap_group_map_locales_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ldap_group_map_id` int(11) NOT NULL,
  `ldap_locale_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `l_g_m_mapping_id_fk` (`ldap_group_map_id`),
  KEY `l_locale_id_fk` (`ldap_locale_id`),
  CONSTRAINT `l_g_m_mapping_id_fk` FOREIGN KEY (`ldap_group_map_id`) REFERENCES `admin_ldap_group_map` (`id`) ON DELETE CASCADE,
  CONSTRAINT `l_locale_id_fk` FOREIGN KEY (`ldap_locale_id`) REFERENCES `admin_ldap_locale` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_host_firmware`
--

DROP TABLE IF EXISTS `servers_host_firmware`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_host_firmware` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_hostfware_project_id_fk` (`project_id`),
  KEY `s_hostfware_org_id_fk` (`organization`),
  CONSTRAINT `s_hostfware_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_hostfware_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- alter Table servers_service_profile_template to add new field 'servers_host_firmware_id'
--

ALTER TABLE `servers_service_profile_template` ADD servers_host_firmware_id INT(11) after san_connectivity_policy_id ;

ALTER TABLE `servers_service_profile_template` ADD CONSTRAINT `s_p_t_servers_host_firmware_id_fk` FOREIGN KEY (`servers_host_firmware_id`)
    REFERENCES `servers_host_firmware` (`id`)  ON DELETE CASCADE;

--
-- Table structure for table `servers_bios_policy`
--


DROP TABLE IF EXISTS `servers_bios_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_bios_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(24) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
 
  -- Main  
  `reboot_on_change` varchar(24) DEFAULT 'no',
  `quiet_boot` varchar(24) DEFAULT 'platform-default',
  `post_error_pause` varchar(24) DEFAULT 'platform-default',
  `resume_ac_on_power_loss` varchar(24) DEFAULT 'platform-default',
  `front_panel_lockout` varchar(24) DEFAULT 'platform-default',
  
  -- Processor
  `turbo_boost` varchar(24) DEFAULT 'platform-default',
  `enhanced_intel_speedstep` varchar(24) DEFAULT 'platform-default',
  `hyper_threading` varchar(24) DEFAULT 'platform-default',
  `core_multi_processing` varchar(24) DEFAULT 'platform-default',
  `execute_disabled_bit` varchar(24) DEFAULT 'platform-default',
  `virtualization_technology` varchar(24) DEFAULT 'platform-default',
  `hardware_prefetcher` varchar(24) DEFAULT 'platform-default',
  `adjacent_cache_line_prefetcher` varchar(24) DEFAULT 'platform-default',
  `dcu_streamer_prefetch` varchar(24) DEFAULT 'platform-default',
  `dcu_ip_prefetch` varchar(24) DEFAULT 'platform-default',
  `direct_cache_access` varchar(24) DEFAULT 'platform-default',
  `processor_c_state` varchar(24) DEFAULT 'platform-default',
  `processor_c1e` varchar(24) DEFAULT 'platform-default',
  `processor_c3_report` varchar(24) DEFAULT 'platform-default',
  `processor_c6_report` varchar(24) DEFAULT 'platform-default',
  `processor_c7_report` varchar(24) DEFAULT 'platform-default',
  `cpu_performance` varchar(24) DEFAULT 'platform-default',
  `max_variable_mtrr_setting` varchar(24) DEFAULT 'platform-default',
  `local_x2_apic` varchar(24) DEFAULT 'platform-default',
  `power_technology` varchar(24) DEFAULT 'platform-default',
  `energy_performance` varchar(24) DEFAULT 'platform-default',
  `frequency_floor_override` varchar(24) DEFAULT 'platform-default',
  `pstate_coordination` varchar(24) DEFAULT 'platform-default',
  `dram_clock_throttling` varchar(24) DEFAULT 'platform-default',
  `channel_interleaving` varchar(24) DEFAULT 'platform-default',
  `rank_interleaving` varchar(24) DEFAULT 'platform-default',
  `demand_scrub` varchar(24) DEFAULT 'platform-default',
  `patrol_scrub` varchar(24) DEFAULT 'platform-default',
  
  -- Intel Directed IO
  `vt_for_directed_io` varchar(24) DEFAULT 'platform-default',
  `interrupt_remap` varchar(24) DEFAULT 'platform-default',
  `coherency_support` varchar(24) DEFAULT 'platform-default',
  `ats_support` varchar(24) DEFAULT 'platform-default',
  `pass_through_dma_support` varchar(24) DEFAULT 'platform-default',
  
  -- RAS Memory
  `memory_ras_config` varchar(24) DEFAULT 'platform-default',
  `numa` varchar(24) DEFAULT 'platform-default',
  `lv_ddr_mode` varchar(24) DEFAULT 'platform-default',
  `dram_refresh_rate` varchar(24) DEFAULT 'platform-default',
  `mirroring_mode` varchar(24) DEFAULT 'platform-default',
  `sparing_mode` varchar(24) DEFAULT 'platform-default',
  
  -- Serial Port
  `serial_port_a` varchar(24) DEFAULT 'platform-default',
  
  -- USB
  `make_device_non_bootable` varchar(24) DEFAULT 'platform-default',
  `legacy_usb_support` varchar(24) DEFAULT 'platform-default',
  `usb_system_idle_power_optimizing_setting` varchar(24) DEFAULT 'platform-default',
  `usb_front_panel_access_lock` varchar(24) DEFAULT 'platform-default',
  `port_6064_emulation` varchar(24) DEFAULT 'platform-default',
  `usb_port_front` varchar(24) DEFAULT 'platform-default',
  `usb_port_internal` varchar(24) DEFAULT 'platform-default',
  `usb_port_kvm` varchar(24) DEFAULT 'platform-default',
  `usb_port_rear` varchar(24) DEFAULT 'platform-default',
  `usb_port_sd_card` varchar(24) DEFAULT 'platform-default',
  `usb_port_v_media` varchar(24) DEFAULT 'platform-default',
  `all_usb_devices` varchar(24) DEFAULT 'platform-default',
  
  -- PCI
  `max_memory_below_4g` varchar(24) DEFAULT 'platform-default',
  `memory_mapped_io_above_4gb_config` varchar(24) DEFAULT 'platform-default',
  `vga_priority` varchar(24) DEFAULT 'platform-default',
  
  -- QPI
  `qpi_link_frequency_select` varchar(24) DEFAULT 'platform-default',
  
  -- LOM and PCIe Slots
  `all_onboard_lom_ports` varchar(24) DEFAULT 'platform-default',
  `lom_port0_option_rom` varchar(24) DEFAULT 'disabled',
  `lom_port1_option_rom` varchar(24) DEFAULT 'disabled',
  `lom_port2_option_rom` varchar(24) DEFAULT 'disabled',
  `lom_port3_option_rom` varchar(24) DEFAULT 'disabled',
  `pcie_slot_sas_option_rom` varchar(24) DEFAULT 'disabled',
  `pcie_slot1_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot2_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot3_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot4_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot5_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot6_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot7_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot8_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot9_link_speed` varchar(24) DEFAULT 'gen1',
  `pcie_slot10_link_speed` varchar(24) DEFAULT 'gen1',
  
  -- Boot Options
  `boot_option_retry` varchar(24) DEFAULT 'platform-default',
  `intel_entry_sas_raid` varchar(24) DEFAULT 'platform-default',
  `intel_entry_sas_raid_module` varchar(24) DEFAULT 'platform-default',
  `onboard_scu_storage_support` varchar(24) DEFAULT 'platform-default',
  
  -- Server Management
  `assert_nmi_on_serr` varchar(24) DEFAULT 'platform-default',
  `assert_nmi_on_perr` varchar(24) DEFAULT 'platform-default',
  `os_boot_watchdog_timer` varchar(24) DEFAULT 'platform-default',
  `os_boot_watchdog_timer_timeout_policy` varchar(24) DEFAULT 'platform-default',
  `os_boot_watchdog_timer_timeout` varchar(24) DEFAULT 'platform-default',
  `frb2_timer` varchar(24) DEFAULT 'platform-default',
  `console_redirection` varchar(24) DEFAULT 'platform-default',
  `flow_control` varchar(24) DEFAULT 'platform-default',
  `baud_rate` varchar(24) DEFAULT 'platform-default',
  `terminal_type` varchar(24) DEFAULT 'platform-default',
  `legacy_os_redirect` varchar(24) DEFAULT 'platform-default',
  `putty_keypad` varchar(24) DEFAULT 'platform-default',
  
  PRIMARY KEY (`id`),
  KEY `servers_bios_policy_id_fk` (`project_id`),
  KEY `servers_bios_policy_org_id` (`organization`),
  CONSTRAINT `servers_bios_policy_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `servers_bios_policy_org_id` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`)  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `servers_maintenance_policy`
--

DROP TABLE IF EXISTS `servers_maintenance_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servers_maintenance_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `reboot_policy` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `s_maintenance_project_id_fk` (`project_id`),
  KEY `s_maintenance_org_id_fk` (`organization`),
  CONSTRAINT `s_maintenance_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `s_maintenance_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- Changes for user management
DROP TABLE IF EXISTS user_roles;
CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
  )ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS ada_users;

CREATE TABLE `ada_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `user_id` varchar(45) DEFAULT NULL,
  `mail_id` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `created_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `isActive` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userrole_fk_id` (`role`),
  KEY `user_createdBy_fk_id` (`createdBy`),
  CONSTRAINT `userrole_fk_id` FOREIGN KEY (`role`) REFERENCES `user_roles` (`id`) ON DELETE CASCADE,
  CONSTRAINT `user_createdBy_fk_id` FOREIGN KEY (`createdBy`) REFERENCES `ada_users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


ALTER TABLE `servers_service_profile_template` ADD servers_maintenance_policy_id INT(11) after servers_host_firmware_id ;
ALTER TABLE `servers_service_profile_template` ADD CONSTRAINT `s_p_t_servers_maintenance_policy_id_fk` FOREIGN KEY (`servers_maintenance_policy_id`)
    REFERENCES `servers_maintenance_policy` (`id`)  ON DELETE CASCADE;

ALTER TABLE `servers_service_profile_template` ADD servers_bios_policy_id INT(11) after servers_host_firmware_id ;
ALTER TABLE `servers_service_profile_template` ADD CONSTRAINT `s_p_t_servers_bios_policy_id_fk` FOREIGN KEY (`servers_bios_policy_id`)
    REFERENCES `servers_bios_policy` (`id`)  ON DELETE CASCADE;

ALTER TABLE `lan_macpool` ADD `assignmentOrder` varchar(45) default NULL after macpool_description;
ALTER TABLE `san_wwnn` ADD `assignmentOrder` varchar(45) default NULL after description;
ALTER TABLE `san_wwpn` ADD `assignmentOrder` varchar(45) default NULL after description;
ALTER TABLE `servers_uuid_pool` ADD `assignmentOrder` varchar(45) default NULL after description;
ALTER TABLE `servers_uuid_pool` ADD `prefixType` varchar(45) default NULL after prefix;


alter table `servers_boot_policy_san` drop foreign key `s_b_p_san_vhbat_id_fk`;
alter table `servers_boot_policy_san` change `vhbat_id` `vhba_id` int(11) DEFAULT NULL;
alter table `servers_boot_policy_san` add CONSTRAINT `s_b_p_san_vhba_id_fk` FOREIGN KEY (`vhba_id`) REFERENCES `san_vhba` (`id`) ON DELETE CASCADE;

DROP TABLE IF EXISTS `servers_spt_vhba_mapping`;
rename table `servers_spt_vhbat_mapping` to `servers_spt_vhba_mapping`;
alter table `servers_spt_vhba_mapping` drop foreign key `sptvm_vhbat_id_fk`;
alter table `servers_spt_vhba_mapping` change `vhbat_id` `vhba_id` int(11) DEFAULT NULL;
alter table `servers_spt_vhba_mapping` add CONSTRAINT `sptvm_vhba_id_fk` FOREIGN KEY (`vhba_id`) REFERENCES `san_vhba` (`id`) ON DELETE CASCADE;


-- Default values for User Roles
INSERT INTO `user_roles` (`id`,`user_role`) VALUES (1,'product_owner');
INSERT INTO `user_roles` (`id`,`user_role`) VALUES (2,'super_admin');
INSERT INTO `user_roles` (`id`,`user_role`) VALUES (3,'admin');
INSERT INTO `user_roles` (`id`,`user_role`) VALUES (4,'user');

-- Default insert sub_menu_state for phase 2

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (0, 1, 'Admin Setting');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (1, 1, 'Authentication');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (2, 1, 'Ldap Group Map');

update sub_menu_state set sub_menu_id = 4 where sub_menu_id =2 and main_menu_id = 3 ;
update sub_menu_state set sub_menu_id = 5 where sub_menu_id =3 and main_menu_id = 3 ;

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (2, 3, 'N/W Control Policy');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (3, 3, 'QOS Policy');

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (3, 4, 'Adapter Policy');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (4, 4, 'vHBA');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (5, 4, 'Connectivity Policy');

update sub_menu_state set sub_menu_id = 9 where sub_menu_id =6 and main_menu_id = 5 ;
update sub_menu_state set sub_menu_id = 8 where sub_menu_id =5 and main_menu_id = 5 ;
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (5, 5, 'Host Firmware');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (6, 5, 'BIOS Policy');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (7, 5, 'Maintenance Policy');



-- Insert default privileges

insert into admin_privilege (name) values ('aaa');
insert into admin_privilege (name) values ('admin');
insert into admin_privilege (name) values ('ext-lan-config');
insert into admin_privilege (name) values ('ext-lan-policy');
insert into admin_privilege (name) values ('ext-lan-qos');
insert into admin_privilege (name) values ('ext-lan-security');
insert into admin_privilege (name) values ('ext-san-config');
insert into admin_privilege (name) values ('ext-san-policy');
insert into admin_privilege (name) values ('ext-san-qos');
insert into admin_privilege (name) values ('ext-san-security');
insert into admin_privilege (name) values ('fault');
insert into admin_privilege (name) values ('ls-compute');
insert into admin_privilege (name) values ('ls-config');
insert into admin_privilege (name) values ('ls-config-policy');
insert into admin_privilege (name) values ('ls-ext-access');
insert into admin_privilege (name) values ('ls-network');
insert into admin_privilege (name) values ('ls-network-policy');
insert into admin_privilege (name) values ('ls-qos');
insert into admin_privilege (name) values ('ls-qos-policy');
insert into admin_privilege (name) values ('ls-security');
insert into admin_privilege (name) values ('ls-security-policy');
insert into admin_privilege (name) values ('ls-server');
insert into admin_privilege (name) values ('ls-server-oper');
insert into admin_privilege (name) values ('ls-server-policy');
insert into admin_privilege (name) values ('ls-storage');
insert into admin_privilege (name) values ('ls-storage-policy');
insert into admin_privilege (name) values ('operations');
insert into admin_privilege (name) values ('org-management');
insert into admin_privilege (name) values ('pn-equipment');
insert into admin_privilege (name) values ('pn-maintenance');
insert into admin_privilege (name) values ('pn-policy');
insert into admin_privilege (name) values ('pn-security');
insert into admin_privilege (name) values ('pod-config');
insert into admin_privilege (name) values ('pod-policy');
insert into admin_privilege (name) values ('pod-qos');
insert into admin_privilege (name) values ('pod-security');
insert into admin_privilege (name) values ('power-mgmt');
insert into admin_privilege (name) values ('read-only');



DROP TABLE IF EXISTS `lan_vnic_template`;
rename table `lan_vnic` to `lan_vnic_template`;
alter table `lan_vnic_template` change `vnic_name` `vnict_name` varchar(45) DEFAULT NULL;

alter table `lan_vnic_template` drop foreign key `lan_vnic_n_c_p_id_fk`;
alter table `lan_vnic_template` add CONSTRAINT `lan_vnict_n_c_p_id_fk` FOREIGN KEY (`network_control_policy_id`) REFERENCES `lan_network_control_policy` (`id`) ON DELETE CASCADE;

alter table `lan_vnic_template` drop foreign key `lan_vnic_macpool_id_fk`;
alter table `lan_vnic_template` add CONSTRAINT `lan_vnict_macpool_id_fk` FOREIGN KEY (`macpool_id`) REFERENCES `lan_macpool` (`id`) ON DELETE CASCADE;

alter table `lan_vnic_template` drop foreign key `lan_vnic_org_id_fk`;
alter table `lan_vnic_template` add CONSTRAINT `lan_vnict_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE;

alter table `lan_vnic_template` drop foreign key `lan_vnic_project_id_fk`;
alter table `lan_vnic_template` add CONSTRAINT `lan_vnict_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE;

alter table `lan_vnic_template` drop foreign key `lan_vnic_q_p_id_fk`;
alter table `lan_vnic_template` add CONSTRAINT `lan_vnict_q_p_id_fk` FOREIGN KEY (`qos_policy_id`) REFERENCES `lan_qos_policy` (`id`) ON DELETE CASCADE;

DROP TABLE IF EXISTS `lan_vnict_vlan_mapping`;
rename table `lan_vnic_vlan_mapping` to `lan_vnict_vlan_mapping`;
alter table `lan_vnict_vlan_mapping` drop foreign key `lvvm_vnic_id_fk`;
alter table `lan_vnict_vlan_mapping` change `vnic_id` `vnict_id` int(11) DEFAULT NULL;
alter table `lan_vnict_vlan_mapping` add CONSTRAINT `lvvm_vnict_id_fk` FOREIGN KEY (`vnict_id`) REFERENCES `lan_vnic_template` (`id`) ON DELETE CASCADE;


--
-- Table structure for table `lan_ethernet_adapter_policies`
--
DROP TABLE IF EXISTS `lan_ethernet_adapter_policies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_ethernet_adapter_policies` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `organization` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `transmit_queues` int(11) DEFAULT NULL,
  `transmit_queues_ring_size` int(11) DEFAULT NULL,
  `receive_queues` int(11) DEFAULT NULL,
  `receive_queues_ring_size` int(11) DEFAULT NULL,
  `completion_queues` int(11) DEFAULT NULL,
  `completion_queues_interrupts` int(11) DEFAULT NULL,
  `transmit_checksum_offload` varchar(16) DEFAULT NULL,
  `receive_checksum_offload` varchar(16) DEFAULT NULL,
  `tcp_segmentation_offload` varchar(16) DEFAULT NULL,
  `tcp_large_receive_offload` varchar(16) DEFAULT NULL,
  `receive_side_scaling` varchar(16) DEFAULT NULL,
  `accelerated_receive_flow_steering` varchar(16) DEFAULT NULL,
  `failback_timeout` int(11) DEFAULT NULL,  
  `interrupt_mode` varchar(16) DEFAULT NULL,  
  `interrupt_coalescing_type` varchar(16) DEFAULT NULL,  
  `interrupt_timer` int(11) DEFAULT NULL,
  `leap_default` boolean DEFAULT false,  
   PRIMARY KEY (`id`),
   KEY `l_e_adapter_policy_project_id_fk` (`project_id`),
   KEY `l_e_adapter_policy_org_id_fk` (`organization`),
  CONSTRAINT `l_e_adapter_policy_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
  CONSTRAINT `l_e_adapter_policy_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lan_vnic`
--
DROP TABLE IF EXISTS `lan_vnic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_vnic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `lan_vnic_template_id` int(11) DEFAULT NULL,
  `lan_ethernet_adapter_policy_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `lan_vnic_project_id_fk` (`project_id`),
   KEY `lan_vnic_temp_id_fk` (`lan_vnic_template_id`),
   KEY `lan_vnic_ethernet_adapter_policy_id_fk` (`lan_ethernet_adapter_policy_id`),
   CONSTRAINT `lan_vnic_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE,
   CONSTRAINT `lan_vnic_temp_id_fk` FOREIGN KEY (`lan_vnic_template_id`) REFERENCES `lan_vnic_template` (`id`) ON DELETE CASCADE,
   CONSTRAINT `lan_vnic_ethernet_adapter_policy_id_fk` FOREIGN KEY (`lan_ethernet_adapter_policy_id`) REFERENCES `lan_ethernet_adapter_policies` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

alter table `servers_boot_policy_lan` drop foreign key `s_b_p_lan_vnic_id_fk`;
alter table `servers_boot_policy_lan` add CONSTRAINT `s_b_p_lan_vnic_id_fk` FOREIGN KEY (`vnic_id`) REFERENCES `lan_vnic` (`id`) ON DELETE CASCADE;

alter table `servers_spt_vnic_mapping` drop foreign key `ssvm_vnic_id_fk`;
alter table `servers_spt_vnic_mapping` add CONSTRAINT `ssvm_vnic_id_fk` FOREIGN KEY (`vnic_id`) REFERENCES `lan_vnic` (`id`) ON DELETE CASCADE;

--
-- Table structure for table `lan_connectivity_policy`
--

DROP TABLE IF EXISTS `lan_connectivity_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_connectivity_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) DEFAULT NULL,
  `description` varchar(256) DEFAULT NULL,
  `project_id` int(11) DEFAULT NULL,  
  `organization` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `l_c_p_project_id_fk` (`project_id`),
  KEY `l_c_p_org_id_fk` (`organization`),
  CONSTRAINT `l_c_p_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE, 
  CONSTRAINT `l_c_p_org_id_fk` FOREIGN KEY (`organization`) REFERENCES `organizations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `lan_lcp_vnic_mapping`
--
DROP TABLE IF EXISTS `lan_lcp_vnic_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lan_lcp_vnic_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `vnic_id` int(11) NOT NULL,
  `lcp_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lcpvm_vnic_id_fk` (`vnic_id`),
  KEY `lcpvm_lcp_id_fk` (`lcp_id`),
  CONSTRAINT `lcpvm_vnic_id_fk` FOREIGN KEY (`vnic_id`) REFERENCES `lan_vnic` (`id`) ON DELETE CASCADE,
  CONSTRAINT `lcpvm_lcp_id_fk` FOREIGN KEY (`lcp_id`) REFERENCES `lan_connectivity_policy` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- alter Table servers_service_profile_template to add new field 'lan_connectivity_policy_id'
--

ALTER TABLE `servers_service_profile_template` ADD lan_connectivity_policy_id INT(11) after local_disk_policy_id ;

ALTER TABLE `servers_service_profile_template` ADD CONSTRAINT `s_p_t_lan_connectivity_policy_id_fk`
    FOREIGN KEY (`lan_connectivity_policy_id`)
    REFERENCES `lan_connectivity_policy` (`id`)  ON DELETE CASCADE;
    
    
--
-- Table structure for table `admin_callhome_general`
--
DROP TABLE IF EXISTS `admin_callhome_general`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_callhome_general` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `state` varchar(16) DEFAULT "off",
  `switch_priority` varchar(45) DEFAULT NULL,
  `throttling` varchar(16) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `customer_id` varchar(510) DEFAULT NULL,
  `contract_id` varchar(510) DEFAULT NULL,
  `site_id` varchar(510) DEFAULT NULL,
  `email_from` varchar(80) DEFAULT NULL, 
  `reply_to` varchar(80) DEFAULT NULL,
  `host` varchar(255) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `admin_callhome_g_project_id_fk` (`project_id`),
   CONSTRAINT `admin_callhome_g_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_callhome_system_inventory`
--
DROP TABLE IF EXISTS `admin_callhome_system_inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_callhome_system_inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `send_periodically` varchar(16) DEFAULT NULL,
  `send_interval_days` int(11) DEFAULT NULL,
  `send_interval_hours` int(11) DEFAULT NULL,
  `send_interval_minutes` int(11) DEFAULT NULL, 
   PRIMARY KEY (`id`),
   KEY `admin_callhome_sys_inv_project_id_fk` (`project_id`),
   CONSTRAINT `admin_callhome_sys_inv_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_callhome_profile`
--
DROP TABLE IF EXISTS `admin_callhome_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_callhome_profile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `name` varchar(16) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `level` varchar(45) DEFAULT NULL,
  `format` varchar(25) DEFAULT NULL,
  `max_msg_size` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `admin_callhome_prof_project_id_fk` (`project_id`),
   CONSTRAINT `admin_callhome_prof_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `admin_callhome_alert_group`
--
DROP TABLE IF EXISTS `admin_callhome_alert_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_callhome_alert_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `xml_value` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_callhome_profile_alert_group_mapping`
--
DROP TABLE IF EXISTS `admin_callhome_profile_alert_group_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_callhome_profile_alert_group_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_id` int(11) DEFAULT NULL,
  `alert_group_id` int(11) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `admin_cllhome_prof_id_fk` (`profile_id`),
   KEY `admin_callhome_prof_alrt_g_id_fk` (`alert_group_id`),
   CONSTRAINT `admin_cllhome_prof_id_fk` FOREIGN KEY (`profile_id`) REFERENCES `admin_callhome_profile` (`id`) ON DELETE CASCADE,
   CONSTRAINT `admin_callhome_prof_alrt_g_id_fk` FOREIGN KEY (`alert_group_id`) REFERENCES `admin_callhome_alert_group` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `admin_callhome_profile_recipient_mapping`
--
DROP TABLE IF EXISTS `admin_callhome_profile_recipient_mapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_callhome_profile_recipient_mapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `profile_id` int(11) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `admin_callhome_prof_recipient_map_id_fk` (`profile_id`),
   CONSTRAINT `admin_callhome_prof_recipient_map_id_fk` FOREIGN KEY (`profile_id`) REFERENCES `admin_callhome_profile` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `admin_callhome_policy`
--
DROP TABLE IF EXISTS `admin_callhome_policy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_callhome_policy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `state` varchar(25) DEFAULT NULL,
  `cause` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `admin_callhome_policy_project_id_fk` (`project_id`),
   CONSTRAINT `admin_callhome_policy_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `admin_backup_configuration`
--
DROP TABLE IF EXISTS `admin_backup_configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_backup_configuration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `backup_status` varchar(45) DEFAULT NULL,
  `admin_state` varchar(45) DEFAULT NULL,
  `backup_type` varchar(45) DEFAULT NULL,
  `preserve_identities` varchar(45) DEFAULT NULL,
  `protocol` varchar(45) DEFAULT NULL,
  `host_name` varchar(256) DEFAULT NULL,
  `remote_file` varchar(128) DEFAULT NULL,
  `user_name` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `admin_bckup_conf_project_id_fk` (`project_id`),
   CONSTRAINT `admin_bckup_conf_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



-- Default insert sub_menu_state for phase 3

update sub_menu_state set sub_menu_id = 8 where sub_menu_id =5 and main_menu_id = 3;

update sub_menu_state set name = 'Ethernet Adapter Policy' where sub_menu_id = 4 and main_menu_id = 3;
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (5, 3, 'vNIC Template');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (6, 3, 'vNIC');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (7, 3, 'Lan Connectivity Policy');

insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (3, 1, 'Call Home Setting');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (4, 1, 'Call Home');
insert into sub_menu_state (sub_menu_id, main_menu_id, name) values (5, 1, 'Backup Configuration');

update sub_menu_state set name = 'Adapter Policy' where sub_menu_id = 2 and main_menu_id = 4;
update sub_menu_state set name = 'vHBA Template' where sub_menu_id = 3 and main_menu_id = 4;


-- alert group table insertion

insert into admin_callhome_alert_group (name,xml_value) values ('Cisco Tac','ciscoTac');
insert into admin_callhome_alert_group (name,xml_value) values ('Diagnostic','diagnostic');
insert into admin_callhome_alert_group (name,xml_value) values ('Environmental','environmental');

alter table `project_details` drop column `created_date_time`;
alter table `project_details` add column `created_date` datetime DEFAULT NULL;
alter table `project_details` add column `updatedBy` varchar(45) DEFAULT NULL;
alter table `project_details` add column `updated_date` datetime DEFAULT NULL;



DROP TABLE IF EXISTS `sp_tmplog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sp_tmplog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `procedure_name` VARCHAR(100) NULL,
  `msg` VARCHAR(512) NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `infrastructure_ethernet_fc_mode` for 4.0 release
--
DROP TABLE IF EXISTS `infrastructure_ethernet_fc_mode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `infrastructure_ethernet_fc_mode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `ethernet_mode` varchar(45) DEFAULT NULL,
  `fc_mode` varchar(45) DEFAULT NULL,
   PRIMARY KEY (`id`),
   KEY `infrastructure_ethernet_fc_project_id_fk` (`project_id`),
   CONSTRAINT `infrastructure_ethernet_fc_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

-- created new table to support UCS MINI scalability ports release 4.0

DROP TABLE IF EXISTS `equipment_mini_scalability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment_mini_scalability` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) DEFAULT NULL,
  `fi_id` varchar(16) DEFAULT NULL,
  `port_id` int(11) DEFAULT NULL,
  `slot_id` int(11) DEFAULT NULL,
  `chassis` int(11) NOT NULL,
  `is_configured` tinyint(1) DEFAULT NULL,
  `configure_as` varchar(16) default NULL,
  PRIMARY KEY (`id`),
  KEY `equip_scalability_project_id_fk` (`project_id`),
  CONSTRAINT `equip_scalability_project_id_fk` FOREIGN KEY (`project_id`) REFERENCES `project_details` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

-- -------------------------------------------------------------------------------------------------
-- Trigger to create default Organization (root) soon after creating a project
-- -------------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS insert_default_values_for_project;
  
DELIMITER $$
CREATE TRIGGER insert_default_values_for_project AFTER INSERT ON project_details
  FOR EACH ROW
    BEGIN
	if(NEW.isUploaded = 0) then
	    insert into organizations (project_id, name, parent_id)  
		select id, 'root' , (select id from organizations where name = 'NA' and project_id is null)
		from project_details where id = NEW.id;
		insert into lan_macpool (project_id, macpool_name, macpool_description, assignmentOrder, organization)  select id, 'default' , 'default', 'default', (select id from organizations where project_id = NEW.id) from project_details where id = NEW.id ;
		insert into lan_vlan (project_id,vlan_name,vlan_id,vlan_default)  select id, 'default' , '1',true from project_details where id = NEW.id ;
		insert into servers_uuid_pool (project_id,name,description, assignmentOrder, prefixType, organization)  select id, 'default' , 'default', 'default', 'derived', (select id from organizations where project_id = NEW.id) from project_details where id = NEW.id ;
		insert into servers_server_pool (project_id, name,description, organization)  select id, 'default' , 'default', (select id from organizations where project_id = NEW.id) from project_details where id = NEW.id ;
		insert into san_wwnn (project_id, wwnn_name, description, assignmentOrder, organization)  select id, 'default' , 'default', 'default', (select id from organizations where project_id = NEW.id) from project_details where id = NEW.id ;
		insert into san_wwpn (project_id, wwpn_name, description, assignmentOrder, organization)  select id, 'default' , 'default', 'default', (select id from organizations where project_id = NEW.id) from project_details where id = NEW.id ;
		insert into san_vsan (project_id, vsan_name, fcoe_vlan_name, fcoe_vsan_id)  select id, 'default' , '4048',1 from project_details where id = NEW.id ;
		insert into san_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, scsi_io_queues, scsi_io_queues_ring_size,
			fcp_error_recovery, flogi_retries, flogi_timeout, plogi__retries, plogi_timeout, port_down_timeout, port_down_io_retry, link_down_timeout, io_throttle_count, luns_per_target, interrupt_mode, sap_default) select id, 
			(select id from organizations where project_id = NEW.id), 'default', 'default', 1, 64, 1, 64, 1, 512, 'disabled', 8, 4000, 8, 20000, 30000, 30, 30000, 256, 256, 'msi-x', true from project_details where id = NEW.id ;
		insert into san_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, scsi_io_queues, scsi_io_queues_ring_size,
			fcp_error_recovery, flogi_retries, flogi_timeout, plogi__retries, plogi_timeout, port_down_timeout, port_down_io_retry, link_down_timeout, io_throttle_count, luns_per_target, interrupt_mode, sap_default) select id, 
			(select id from organizations where project_id = NEW.id), 'Linux', 'Linux', 1, 64, 1, 64, 1, 512, 'disabled', 8, 4000, 8, 20000, 30000, 30, 30000, 256, 256, 'msi-x', true from project_details where id = NEW.id ;
		insert into san_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, scsi_io_queues, scsi_io_queues_ring_size,
			fcp_error_recovery, flogi_retries, flogi_timeout, plogi__retries, plogi_timeout, port_down_timeout, port_down_io_retry, link_down_timeout, io_throttle_count, luns_per_target, interrupt_mode, sap_default) select id, 
			(select id from organizations where project_id = NEW.id), 'Solaris', 'Solaris', 1, 64, 1, 64, 1, 512, 'disabled', 8, 4000, 8, 20000, 30000, 30, 30000, 256, 256, 'msi-x', true from project_details where id = NEW.id ;
		insert into san_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, scsi_io_queues, scsi_io_queues_ring_size,
			fcp_error_recovery, flogi_retries, flogi_timeout, plogi__retries, plogi_timeout, port_down_timeout, port_down_io_retry, link_down_timeout, io_throttle_count, luns_per_target, interrupt_mode, sap_default) select id, 
			(select id from organizations where project_id = NEW.id), 'VMWare', 'VMWare', 1, 64, 1, 64, 1, 512, 'disabled', 8, 4000, 8, 20000, 30000, 30, 30000, 256, 256, 'msi-x', true from project_details where id = NEW.id ;
		insert into san_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, scsi_io_queues, scsi_io_queues_ring_size,
			fcp_error_recovery, flogi_retries, flogi_timeout, plogi__retries, plogi_timeout, port_down_timeout, port_down_io_retry, link_down_timeout, io_throttle_count, luns_per_target, interrupt_mode, sap_default) select id, 
			(select id from organizations where project_id = NEW.id), 'Windows', 'Windows', 1, 64, 1, 64, 1, 512, 'disabled', 8, 4000, 8, 20000, 30000, 30, 30000, 256, 256, 'msi-x', true from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'aaa' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'admin' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'facility-manager' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'network' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'operations' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'read-only' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'server-compute' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'server-equipment' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'server-profile' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'server-security' from project_details where id = NEW.id ;
	insert into admin_ldap_role (project_id,name)  select id, 'storage' from project_details where id = NEW.id ;
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'aaa' and p.name in ('aaa');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'admin' and p.name in ('admin');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'facility-manager' and p.name in ('power-mgmt');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'network' and 
		p.name in ('ext-lan-config','ext-lan-policy','ext-lan-qos','ext-lan-security','ls-network','ls-network-policy','ls-qos','ls-qos-policy','pod-config','pod-policy','pod-qos','pod-security');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'operations' and p.name in ('fault','operations');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'read-only' and p.name in ('read-only');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'server-compute' and p.name in ('ls-compute','ls-server-oper','ls-server-policy');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'server-equipment' and p.name in ('pn-equipment','pn-maintenance','pn-policy');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'server-profile' and 
		p.name in ('ls-config','ls-config-policy','ls-ext-access','ls-server','ls-server-oper','ls-server-policy');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'server-security' and p.name in ('ls-security','ls-security-policy','pn-security');
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id,roles_privileges_id)
		select r.id roleid, p.id from admin_ldap_role r, admin_privilege p where r.project_id = NEW.id and r.name = 'storage' and 
		p.name in ('ext-san-config','ext-san-policy','ext-san-qos','ext-san-security','ls-storage','ls-storage-policy');							
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'Linux', 'Recommended adapter settings for linux', 1, 256, 1, 512, 2, 4, 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 'disabled', 5, 'msi-x', 'min', 125, true from project_details where id = NEW.id ;	
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'SRIOV', 'Recommended adapter settings for Win8 SRIOV-VMFEX PF', 1, 256, 4, 512, 5, 32, 'enabled', 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 5, 'msi-x', 'min', 126, true from project_details where id = NEW.id ;	
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'Solaris', 'Recommended adapter settings for Solaris', 1, 256, 1, 512, 2, 4, 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 'disabled', 5, 'msi-x', 'min', 127, true from project_details where id = NEW.id ;		
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'VMWare', 'Recommended adapter settings for VMWare', 1, 256, 1, 512, 2, 4, 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 'disabled', 5, 'msi-x', 'min', 128, true from project_details where id = NEW.id ;		
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'VMWarePassThru', 'Recommended adapter settings for VMWare pass-thru (dynamic vNIC)', 4, 256, 4, 512, 8, 12, 'enabled', 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 5, 'msi', 'min', 125, true from project_details where id = NEW.id ;		
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'Windows', 'Windows', 1, 256, 4, 512, 5, 8, 'enabled', 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 5, 'msi-x', 'min', 125, true from project_details where id = NEW.id ;		
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'default', 'default adapter policy', 1, 256, 1, 512, 2, 4, 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 'disabled', 5, 'msi-x', 'min', 125, true from project_details where id = NEW.id ;		
	insert into lan_ethernet_adapter_policies (project_id, organization, name, description, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues, completion_queues_interrupts,
		transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload,tcp_large_receive_offload, receive_side_scaling, accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default) select id, 
		(select id from organizations where project_id = NEW.id), 'usNIC', 'Recommended adapter settings for usNIC Connection', 2, 256, 2, 512, 4, 6, 'enabled', 'enabled', 'enabled', 'enabled', 'enabled', 'disabled', 0, 'msi-x', 'min', 125, true from project_details where id = NEW.id ;
	
	call insert_default_profile_when_project_creation(NEW.id);	
	
		
	END if;  
END$$
DELIMITER ;


drop view if exists organization_view_temp;

create view organization_view_temp as 
select organizations.id as id, (select count(*) from organizations o1 where o1.parent_id = organizations.id) count from organizations where organizations.parent_id is not null group by organizations.id union all
select organization id, count(*) count from admin_ldap_locales_org_mapping group by organization union all 
select organization id, count(*) count from admin_ldap_locales_org_mapping group by organization union all
select organization id, count(*) count from lan_macpool group by organization union all 
select organization id, count(*) count from lan_vnic_template group by organization union all 
select organization id, count(*) count from lan_mgmt_ippool group by organization union all 
select organization id, count(*) count from lan_qos_policy group by organization union all 
select organization id, count(*) count from lan_network_control_policy group by organization union all 
select organization id, count(*) count from lan_ethernet_adapter_policies group by organization union all 
select organization id, count(*) count from san_wwnn group by organization union all 
select organization id, count(*) count from san_wwpn group by organization union all 
select organization id, count(*) count from san_vhba_template group by organization union all 
select organization id, count(*) count from san_adapter_policies group by organization union all 
select organization id, count(*) count from san_connectivity_policy group by organization union all 
select organization id, count(*) count from servers_uuid_pool group by organization union all 
select organization id, count(*) count from servers_boot_policy group by organization union all 
select organization id, count(*) count from servers_local_disc group by organization union all 
select organization id, count(*) count from servers_server_pool group by organization union all 
select organization id, count(*) count from servers_server_pool_policy group by organization union all 
select organization id, count(*) count from servers_server_pool_qualifier group by organization union all 
select organization id, count(*) count from servers_host_firmware group by organization union all 
select organization id, count(*) count from servers_bios_policy group by organization union all 
select organization id, count(*) count from servers_maintenance_policy group by organization union all 
select organization id, count(*) count from servers_service_profile_template group by organization union all 
select organization id, count(*) count from servers_service_profile group by organization ;



drop view if exists organization_view;

create view organization_view(id, count) as(
select p.id id, sum(p.count) count from organization_view_temp p group by p.id );

 
 
 
drop procedure if exists translate_data_ucs_to_ada;
DELIMITER $$
create procedure translate_data_ucs_to_ada(in project_id int(11), out error_message varchar(2000)) 
begin
	declare exit handler for sqlexception begin
  		 rollback;
		set error_message = (select 'An error has occurred, operation rollbacked and the stored procedure was terminated.');
	end;

	start transaction;
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting ntp,dns,admin_setting--START");
		
		call insert_ethernet_fc_mode(project_id); -- inserting ethernet fc mode
		
		insert into ntp (project_id, ip_address) select project_id, name from commntpprovider;
		insert into dns (project_id, ip_address) select project_id, name from commdnsprovider;
		insert into admin_settings (project_id, time_zone) select project_id,a.id from commdatetime b left join time_zone a on (b.timezone = a.timezone);	
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting ntp,dns,admin_setting--END");
		
		call insert_admin_ldap(project_id);
		call insert_admin_tacacs(project_id);
		call insert_admin_radius(project_id);
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting admin_authentication--START");
		insert into admin_authentication_domain (project_id, name, session_timeout, refresh_period, realm, ldap_provider_group_id, two_factor) 
		select project_id, a.name, a.sessiontimeout, a.refreshperiod, camel_case(b.realm), (select l.id from admin_ldap_provider_group l where l.project_id = project_id and l.name = b.providergroup), b.use2factor from aaadomain a left join aaadomainauth b on  (a.primarykey = b.fk_aaadomain) where b.realm = 'ldap';
		insert into admin_authentication_domain (project_id, name, session_timeout, refresh_period, realm, tacacs_provider_group_id, two_factor) 
		select project_id, a.name, a.sessiontimeout, a.refreshperiod, camel_case(b.realm), (select l.id from admin_tacacs_provider_group l where l.project_id = project_id and l.name = b.providergroup), b.use2factor from aaadomain a left join aaadomainauth b on  (a.primarykey = b.fk_aaadomain) where b.realm = 'tacacs';
		insert into admin_authentication_domain (project_id, name, session_timeout, refresh_period, realm, radius_provider_group_id, two_factor) 
		select project_id, a.name, a.sessiontimeout, a.refreshperiod, camel_case(b.realm), (select l.id from admin_radius_provider_group l where l.project_id = project_id and l.name = b.providergroup), b.use2factor from aaadomain a left join aaadomainauth b on  (a.primarykey = b.fk_aaadomain) where b.realm = 'radius';
		insert into admin_authentication_domain (project_id, name, session_timeout, refresh_period, realm, two_factor) 
		select project_id, a.name, a.sessiontimeout, a.refreshperiod, camel_case(b.realm), b.use2factor from aaadomain a left join aaadomainauth b on  (a.primarykey = b.fk_aaadomain) where b.realm = 'local';
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting admin_authentication--END");
		
		call insert_call_home(project_id);
		
		call insert_call_home_default_profile_if_not_created(project_id);
		-- insert into admin_backup_configuration (project_id, backup_status, admin_state, backup_type, preserve_identities, protocol, host_name, remote_file, user_name, password)
		-- select project_id, case when a.PrimaryKey > 0 then 'enable' else 'disable' end, a.adminState, 'config-all', 'no', a.proto, a.host, a.remoteFile, a.user, a.pwd from mgmtbackuppolicy a where fk_orgOrg is not null;
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting equipment--START");
		update equipment_chasis set psp_power_supply = (select redundancy from computepsupolicy) where project_id = project_id;

		insert into equipment_server (project_id, chassis, fi_id, port_id, slot_id, user_label) select project_id, 0, q.id, p.portid, p.slotid, p.usrlbl from fabricdceswsrvep p left join fabricdceswsrv q on (p.fk_fabricdceswsrv = q.primarykey);
		insert into equipment_server (project_id, chassis, fi_id, port_id, slot_id, user_label) select project_id, 0, b.id, a.portid, a.slotid, a.usrlbl from fabricdceswsrvpcep a left join fabricdceswsrvpc c on (c.primarykey = a.fk_fabricdceswsrvpc) left join fabricdceswsrv b on (c.fk_fabricdceswsrv = b.primarykey);
		
		insert into equipment_uplink (project_id, fi_id, port_id, slot_id, user_label) select project_id, b.id, a.portid, a.slotid, a.usrlbl from fabricethlanep a left join fabricethlan b on (a.fk_fabricethlan = b.primarykey);	
		insert into equipment_uplink (project_id, fi_id, port_id, slot_id, user_label) select project_id, c.id, a.portid, a.slotid, 'Uplink' from fabricethlanpcep a left join ( fabricethlanpc b left join fabricethlan c on b.fk_fabricethlan = c.primarykey) on (a.fk_fabricethlanpc = b.primarykey);
	
		insert into equipment_fcport (project_id, fi_id, port_id, slot_id, user_label) select project_id, b.id, a.portid, a.slotid, a.usrlbl from fabricfcsanep a left join fabricfcsan b on (a.fk_fabricfcsan = b.primarykey);
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting equipment--END");
		
		call insert_or_update_scalability_ports(project_id);
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_portchannel,lan_vlan--START");
		insert into lan_portchannel (project_id, fi_name, fi_id, description, uplink_id) 
		select project_id, b.name, c.id, b.descr, (select eu.id from equipment_uplink eu where eu.port_id = a.portid and eu.slot_id = a.slotid and eu.project_id = project_id and eu.fi_id = c.id) 
		from fabricethlanpcep a left join ( fabricethlanpc b left join fabricethlan c on b.fk_fabricethlan = c.primarykey) on (a.fk_fabricethlanpc = b.primarykey);
		
		insert into lan_vlan (project_id, vlan_default, vlan_id, vlan_name, description) 
		select project_id, (select case when f.name = 'default' then 1 else 0  end), f.id, f.name,(select case when f.name = 'default' then '' else f.name end) from fabricvlan f where fk_fabricLanCloud is not null;
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_portchannel,lan_vlan--END");
		
		call orgorg_to_organizations(project_id);
		
		call insert_admin_ldap_role(project_id);
		call insert_admin_ldap_locale(project_id);
		call insert_admin_ldap_group_map(project_id);
	
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_macpool,lan_network_control_policy,lan_qos_policy--START");
		insert into lan_macpool (project_id, macpool_description, macpool_name, assignmentOrder, organization, from_address, to_address) 
		select project_id, a.descr, a.name, a.assignmentOrder, return_organization_id(project_id, a.fk_orgorg), b.from_from, b.to_to from macpoolpool a left join macpoolblock b on b.fk_macpoolpool = a.primarykey;
	
		insert into lan_network_control_policy (project_id, cdp, description, forge, mac_register_mode, ncp_name,organization, uplink_fail_action) 
		select project_id, a.cdp, a.descr, b.forge, a.macregistermode, a.name, return_organization_id(project_id, a.fk_orgorg) , a.uplinkfailaction from nwctrldefinition a inner join dpsecmac b on b.fk_nwctrldefinition = a.primarykey where a.fk_orgorg is not null;
	
		insert into lan_qos_policy (project_id, burst, host_control, name, priority, rate, organization) 
		select project_id, b.burst, b.hostcontrol, a.name, b.prio, b.rate, return_organization_id(project_id, a.fk_orgorg) from epqosdefinition a inner join epqosegress b on b.fk_epqosdefinition = a.primarykey;
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_macpool,lan_network_control_policy,lan_qos_policy--START");
		
		call insert_vnic_template(project_id);
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_ethernet_adapter_policies,lan_vnic--START");
		insert into lan_ethernet_adapter_policies (project_id, description, name, transmit_queues, transmit_queues_ring_size, receive_queues, receive_queues_ring_size, completion_queues,
			completion_queues_interrupts, transmit_checksum_offload, receive_checksum_offload, tcp_segmentation_offload, tcp_large_receive_offload, receive_side_scaling,
			accelerated_receive_flow_steering, failback_timeout, interrupt_mode, interrupt_coalescing_type, interrupt_timer, leap_default, organization) 
		select project_id, a.descr, a.name, b.count, b.ringSize, c.count, c.ringSize, d.count, e.count, f.tcpTxChecksum, f.tcpRxChecksum, 
			f.tcpSegment, f.largeReceive, g.receiveSideScaling, 'disabled', i.timeout, e.mode, e.coalescingType, e.coalescingTime, (select case when (a.name in ('default','usNIC','Windows','VMWarePassThru','VMWare','Solaris','SRIOV','Linux')) then 1 else 0  end), return_organization_id(project_id, a.fk_orgorg)
			from adaptorhostethifprofile a inner join adaptorethworkqueueprofile b on ( b.FK_adaptorhostethifprofile = a.primarykey) 
			inner join adaptorethrecvqueueprofile c on ( c.FK_adaptorhostethifprofile = a.primarykey) 
			inner join adaptorethcompqueueprofile d on ( d.FK_adaptorhostethifprofile = a.primarykey) 
			inner join adaptorethinterruptprofile e on ( e.FK_adaptorhostethifprofile = a.primarykey) 
			inner join adaptorethoffloadprofile f on ( f.FK_adaptorhostethifprofile = a.primarykey) 
			inner join adaptorrssprofile g on ( g.FK_adaptorhostethifprofile = a.primarykey) 
			-- inner join adaptoretharfsprofile h on ( h.FK_adaptorhostethifprofile = a.primarykey) this has to taken care with object factory is updated
			inner join adaptorethfailoverprofile i on ( i.FK_adaptorhostethifprofile = a.primarykey);
			
		insert into lan_vnic(project_id, name, lan_ethernet_adapter_policy_id, lan_vnic_template_id) 
		select project_id, T4.vnic, s.id adpaterprofileid, T4.templateid from (
		select T3.vnic , t.id templateid, T3.apname adpatername from (
		select T2.vnic , T2.nwTemplName,case when T2.count > 1 then (select distinct c.adaptorProfileName from vnicether c where c.name = T2.vnic and c.adaptorProfileName != '' ) else (select distinct d.adaptorProfileName from vnicether d where d.name = T2.vnic ) end as apname from ( 
		select T1.vnic, T1.nwTemplName, count(T1.adaptorProfileName) count from (
		select distinct v.name vnic,v.nwTemplName , v.adaptorProfileName from vnicether v where (v.nwTemplName is not null and v.nwTemplName != v.name ) ) T1 group by T1.vnic ) T2 ) T3 , 
		lan_vnic_template t where  t.project_id = project_id and T3.nwTemplName = t.vnict_name ) T4
		left join lan_ethernet_adapter_policies s on (s.project_id = project_id and s.name = T4.adpatername );	
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_ethernet_adapter_policies,lan_vnic--END");
		
		call insert_lan_connectivity_policy(project_id);
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_mgmt_ippool,san_vsan,san_wwnn,san_wwpn,san_vhba_template,san_adapter_policies,san_vhba--START");
		update project_details pd, ippoolpool ip set pd.ip_pool_assignment_order = ip.assignmentorder where pd.id = project_id;
		insert into lan_mgmt_ippool (project_id, default_gateway, description,dns, from_address, ippool_name, organization, subnet_mask, to_address) 
		select project_id, b.defgw, a.name, b.primdns, b.from_from, a.name, return_organization_id(project_id, a.fk_orgorg), b.subnet, b.to_to from ippoolpool a inner join ippoolblock b on b.fk_ippoolpool = a.primarykey where a.name = "ext-mgmt";
	
		insert into san_vsan (project_id, description, fcoe_vlan_name, fcoe_vsan_id, fi_id, vsan_name) 
		select project_id,b.name, b.fcoevlan, b.id, a.id, b.name from fabricvsan b left join fabricfcsan a on b.fk_fabricfcsan = a.primarykey where (b.fk_fabricsancloud is not null or b.fk_fabricfcsan is not null);
	
		insert into san_wwnn (project_id, description, organization, from_address, wwnn_name, to_address, assignmentOrder) 
		select project_id, a.descr, return_organization_id(project_id, a.fk_orgorg), b.from_from,  (select case when a.name = 'node-default' then 'default' else a.name end), b.to_to, a.assignmentOrder from fcpoolinitiators a left join fcpoolblock b  on b.fk_fcpoolinitiators = a.primarykey where a.purpose = "node-wwn-assignment";
	
		insert into san_wwpn (project_id, description, organization, from_address, wwpn_name, to_address, assignmentOrder) 
		select project_id, a.descr, return_organization_id(project_id, a.fk_orgorg), b.from_from, a.name, b.to_to, a.assignmentOrder from fcpoolinitiators a left join fcpoolblock b  on b.fk_fcpoolinitiators = a.primarykey where a.purpose = "port-wwn-assignment";
	
		insert into san_vhba_template (project_id, description, organization, qos_policy_id, switch_id,template_type, vhba_name, vsan_id, wwpn_pool_id) 
		select project_id, a.descr, return_organization_id(project_id, a.fk_orgorg), (select l.id from lan_qos_policy l where l.project_id = project_id and l.name= a.qospolicyname), a.switchid,a.templtype,a.name,(select m.id from san_vsan m where m.project_id = project_id and vsan_name= b.name), (select n.id from san_wwpn n where n.project_id = project_id and n.wwpn_name= a.identpoolname) from vnicsanconntempl a inner join vnicfcif b  on b.fk_vnicsanconntempl = a.primarykey;
		
		-- Inserting default vsan reference for all global vsan associated at vhba temapltes.
		update san_vhba_template t set t.vsan_id = (select id from san_vsan v where v.project_id = project_id and v.vsan_name = 'default') where t.project_id = project_id and t.vsan_id is null;
	
		insert into san_adapter_policies (project_id, description, name, transmit_queues, receive_queues, scsi_io_queues, scsi_io_queues_ring_size,
		plogi__retries, plogi_timeout, flogi_retries, flogi_timeout, fcp_error_recovery, link_down_timeout,  port_down_io_retry, port_down_timeout, 
		transmit_queues_ring_size, receive_queues_ring_size,io_throttle_count, luns_per_target, interrupt_mode, sap_default, organization) 
		select project_id, a.descr, a.name, 1, 1, b.count_count, b.ringsize, c.retries, c.timeout, d.retries, d.timeout, e.fcperrorrecovery, e.linkdowntimeout, 
		e.portdownioretrycount, e.portdowntimeout, f.ringsize, g.ringsize, h.iothrottlecount, h.lunspertarget, i.mode_mode, (select case when (a.name in ('default','Linux','Solaris','VMWare','Windows')) then 1 else 0  end), return_organization_id(project_id, a.fk_orgorg)
		from adaptorhostfcifprofile a inner join adaptorfccdbworkqueueprofile b on ( b.fk_adaptorhostfcifprofile = a.primarykey) 
		inner join adaptorfcportplogiprofile c on ( c.fk_adaptorhostfcifprofile = a.primarykey) 
		inner join adaptorfcportflogiprofile d on ( d.fk_adaptorhostfcifprofile = a.primarykey) 
		inner join adaptorfcerrorrecoveryprofile e on ( e.fk_adaptorhostfcifprofile = a.primarykey) 
		inner join adaptorfcworkqueueprofile f on ( f.fk_adaptorhostfcifprofile = a.primarykey) 
		inner join adaptorfcrecvqueueprofile g on ( g.fk_adaptorhostfcifprofile = a.primarykey) 
		inner join adaptorfcportprofile h on ( h.fk_adaptorhostfcifprofile = a.primarykey) 
		inner join adaptorfcinterruptprofile i on ( i.fk_adaptorhostfcifprofile = a.primarykey) ;
	
--		insert into san_vhba(project_id, name, adapter_policy_id, vhba_template_id)
--		select project_id, a.name, (select l.id from san_adapter_policies l where l.name = a.adaptorProfileName and l.project_id = project_id), (select m.id from san_vhba_template m where m.vhba_name = a.nwTemplName and m.project_id = project_id) from vnicfc a where fk_lsserver is null;

		insert into san_vhba(project_id, name, adapter_policy_id, vhba_template_id) 
		select project_id, T4.vhba, s.id adpaterprofileid, T4.templateid from (
		select T3.vhba , t.id templateid, T3.apname adpatername from (
		select T2.vhba , T2.nwTemplName,case when T2.count > 1 then (select distinct c.adaptorProfileName from vnicfc c where c.name = T2.vhba and c.adaptorProfileName != '' ) else (select distinct d.adaptorProfileName from vnicfc d where d.name = T2.vhba ) end as apname from ( 
		select T1.vhba, T1.nwTemplName, count(T1.adaptorProfileName) count from (
		select distinct v.name vhba,v.nwTemplName , v.adaptorProfileName from vnicfc v where (v.nwTemplName is not null and v.nwTemplName != v.name ) ) T1 group by T1.vhba ) T2 ) T3 , 
		san_vhba_template t where  t.project_id = project_id and T3.nwTemplName = t.vhba_name ) T4
		left join san_adapter_policies s on (s.project_id = project_id and s.name = T4.adpatername );
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting lan_mgmt_ippool,san_vsan,san_wwnn,san_wwpn,san_vhba_template,san_adapter_policies,san_vhba--END");
		
		call insert_san_connectivity_policy(project_id);
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_uuid_pool--START");
		insert into servers_uuid_pool (project_id, description, organization, name, prefix, prefixType, from_address, to_address, assignmentOrder) 
		select project_id, a.descr, return_organization_id(project_id, a.fk_orgorg), a.name, (select case when a.prefix_prefix = 'derived' then '' else a.prefix_prefix end), (select case when a.prefix_prefix = 'derived' then 'derived' else 'other' end), b.from_from, b.to_to, a.assignmentOrder from uuidpoolpool a left join uuidpoolblock b  on b.fk_uuidpoolpool = a.primarykey;
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_uuid_pool--END");
		
		call insert_boot_policy(project_id);
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_local_disc,servers_server_pool--START");
		insert into servers_local_disc (project_id, description, mode, name, organization, protect_configuration) 
		select project_id, a.descr, return_local_disk_policy_mode(a.mode_mode), a.name, return_organization_id(project_id, a.fk_orgorg),(select case when  a.protectconfig = 'yes' then 1 else 0  end) from storagelocaldiskconfigpolicy a;
		
		insert into servers_server_pool (project_id, description, name, organization) 
		select project_id, a.descr, a.name, return_organization_id(project_id, a.fk_orgorg) 
		from computepool a;
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_local_disc,servers_server_pool--END");
				
		call insert_server_pool_qualifier(project_id);	
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_server_pool_policy,servers_host_firmware,servers_maintenance_policy--START");
		insert into servers_server_pool_policy (project_id, description, name, organization, qualifier_id, server_pool_id) 
		select project_id, a.descr, a.name, return_organization_id(project_id, a.fk_orgorg), 
		(select l.id from servers_server_pool_qualifier l where l.project_id = project_id and l.name= a.qualifier), 
		(select m.id from servers_server_pool m where m.project_id = project_id and m.name = substring(a.pooldn ,locate('/compute-pool-', a.pooldn)+14))
		from computepoolingpolicy a;
		
		insert into servers_host_firmware (project_id, name, description, organization)
		select project_id, f.name, f.descr, return_organization_id(project_id, f.fk_orgorg) from firmwareComputeHostPack f;
		
		insert into servers_maintenance_policy (project_id, name, description, organization, reboot_policy)
		select project_id, f.name, f.descr, return_organization_id(project_id, f.fk_orgorg), f.uptimeDisr from lsmaintMaintPolicy f;
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_server_pool_policy,servers_host_firmware,servers_maintenance_policy--END");
		
		call insert_servers_bios_policy(project_id);
		
		call insert_service_profile_template(project_id);	
		
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_service_profile--START");
		insert into servers_service_profile (project_id, description, name, organization, template_id) 
		select project_id, a.descr, a.name, return_organization_id(project_id, a.fk_orgorg), b.id from lsserver a inner join servers_service_profile_template b on b.name = a.srctemplname where  
		b.project_id = project_id and a.type_type ="instance" and b.id is not null;
		call insert_log(project_id,"translate_data_ucs_to_ada","inserting servers_service_profile--END and success clone/import");
		
	commit;	
end$$
DELIMITER ;

drop procedure if exists insert_chassis_count;
DELIMITER $$
create procedure insert_chassis_count(in project_id_in int(11),out error_message varchar(2000))
	block1 : begin
  declare total_port_count int;
		declare chassis_link int;
		declare chassis_count int;
    
		declare exit handler for sqlexception begin
		 rollback;
  		 set error_message = (select 'An error has occurred, while inserting chassis value in project setting.');
		end;
		start transaction;
		call insert_log(project_id_in,"insert_chassis_count","inserting chassis count in project setting--START");
		
		select count(id) into total_port_count from equipment_server where project_id = project_id_in and fi_id = "A";
		select cdp_action into chassis_link from equipment_chasis where project_id = project_id_in;
		set chassis_count = CEIL(total_port_count/chassis_link);
		INSERT INTO project_settings(project_id,project_key,project_value) values(project_id_in,'CHASSIS_COUNT',chassis_count);
		call insert_log(project_id_in,"insert_chassis_count","inserting chassis count in project setting--START");
		commit;
	end block1$$
DELIMITER;

drop procedure if exists insert_admin_ldap;
DELIMITER $$
create procedure insert_admin_ldap(in project_id_in int(11)) 
block1: begin  
	call insert_log(project_id_in,"insert_admin_ldap","inserting admin_ldap--START");
	INSERT INTO admin_ldap_provider (project_id, attribute, base_dn, bind_dn, enable_ssl, filter, group_authorization, group_recursion, host_name, provider_order, port, target_attribute, timeout, use_primary_group, vendor, provider_password)
	SELECT  project_id_in,a.attribute, a.basedn, a.rootdn, a.enableSSL, a.filter, b.authorization_authorization, b.traversal, a.name, a.order_order, a.port, b.targetAttr, a.timeout, b.usePrimaryGroup, a.vendor, a.encKey FROM aaaldapprovider a INNER JOIN aaaldapgrouprule b ON b.FK_aaaldapprovider = a.PrimaryKey;
	
	CALL insert_admin_ldap_provider_group(project_id_in);
	
	INSERT INTO admin_ldap_general (project_id, attribute, baseDN, filter, timeout)
	SELECT  project_id_in,a.attribute, a.basedn, a.filter, a.timeout FROM aaaldapep a ;
	call insert_log(project_id_in,"insert_admin_ldap","inserting admin_ldap--END");
end block1$$
DELIMITER ;

drop procedure if exists orgorg_to_organizations;
DELIMITER $$
create procedure orgorg_to_organizations(in project_id int(11)) 
begin
	declare done int default false;
	declare null_organization_id int;
	declare name varchar(45) default "";
	declare parentname varchar(45) default "";	
	declare cur cursor for select o.name, po.name from orgorg o left join orgorg po on (o.fk_orgorg = po.primarykey);
	declare continue handler for not found set done = true;
  call insert_log(project_id,"orgorg_to_organizations","inserting organizations--START");
	open cur;
	read_loop: loop
		fetch cur into name,parentname;
		if done then
			leave read_loop;
		end if;
		if parentname is null then
			select id into null_organization_id from organizations where parent_id is null;
			insert into organizations (project_id, name, parent_id ) values (project_id, name, null_organization_id);	
		else	
			insert into organizations (project_id, name, parent_id ) select project_id, name, o.id from organizations o where o.name = parentname and o.project_id = project_id;	
		end if;
	end loop;
	close cur;
	call insert_log(project_id,"orgorg_to_organizations","inserting organizations--END");
end$$
DELIMITER ;

drop procedure if exists insert_san_and_target_boot_policy;
DELIMITER $$
create procedure insert_san_and_target_boot_policy(in project_id_in int(11)) 
block1: begin
	declare done int default false;
	declare boot_policy_id int;
	declare san_order varchar(45) default "";	
	declare boot_policy_type varchar(45) default "";
	declare san_name varchar(45) default "";
	declare vhba_id int;
	declare servers_boot_policy_san_id int;
	declare lsbootsancatsanimage_id int;
	declare cur cursor for select a.primarykey, concat('SAN-',camel_case(a.type_type)), (select s.id from servers_boot_policy s where s.project_id = project_id_in and s.name = c.name), b.order_order, camel_case(a.type_type), (select v.id from san_vhba v where v.project_id = project_id_in and v.name = a.vnicname) 
	from lsbootsancatsanimage a left join ( lsbootsan b  left join lsbootpolicy c on b.fk_lsbootpolicy = c.primarykey )on a.fk_lsbootsan = b.primarykey;
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_san_and_target_boot_policy","inserting servers_boot_policy_san,servers_boot_policy_san_target--START");
	open cur;
	read_loop: loop
		fetch cur into lsbootsancatsanimage_id, san_name,  boot_policy_id,san_order,boot_policy_type,vhba_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into servers_boot_policy_san (project_id, name, description, boot_policy_id, san_order, boot_policy_type, vhba_id) 
		values (project_id_in, san_name,san_name, boot_policy_id,san_order,boot_policy_type,vhba_id);
		select last_insert_id() into servers_boot_policy_san_id; 
		
		insert into servers_boot_policy_san_target( name, boot_policy_id, boot_policy_san_id,lun_id, target_type, wwpn_address) 
		select concat('SAN-Target-',camel_case(d.type_type)), boot_policy_id, servers_boot_policy_san_id, d.lun, camel_case(d.type_type), d.wwn 
		from lsbootsancatsanimagepath d inner join lsbootsancatsanimage a on d.fk_lsbootsancatsanimage = a.primarykey where a.primarykey = lsbootsancatsanimage_id;
			 
	end loop;
	close cur;
	call insert_log(project_id_in,"insert_san_and_target_boot_policy","inserting servers_boot_policy_san,servers_boot_policy_san_target--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_server_pool_qualifier;
DELIMITER $$
create procedure insert_server_pool_qualifier(in project_id_in int(11)) 
block1: begin 
	declare done int default false;
	declare name varchar(45) default "";
	declare description varchar(45) default "";	
	declare boot_policy_type varchar(45) default "";
	declare organization_id int;
	declare c_maxid int;
	declare c_minid int;
	declare computechassisqual_id int;
	declare servers_server_pool_qualifier_id int;
	declare cur cursor for select a.descr, a.name, return_organization_id(project_id_in, a.fk_orgorg), b.maxid, b.minid , b.primarykey
		from computequal a inner join computechassisqual b on b.fk_computequal = a.primarykey;		
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_server_pool_qualifier","inserting servers_server_pool_qualifier,servers_spq_slot_mapping--START");
	open cur;
	read_loop: loop
		fetch cur into description, name,organization_id,c_maxid,c_minid, computechassisqual_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into servers_server_pool_qualifier (project_id, description, name, organization, chassis_max_id, chassis_min_id ) 
		values (project_id_in, description, name, organization_id,c_maxid,c_minid);
		select last_insert_id() into servers_server_pool_qualifier_id; 
		
		insert into servers_spq_slot_mapping( max_id,min_id, spq_id) 
		select maxid, minid, servers_server_pool_qualifier_id from computeslotqual where fk_computechassisqual = computechassisqual_id;
			 
	end loop;
	close cur;
	call insert_log(project_id_in,"insert_server_pool_qualifier","inserting servers_server_pool_qualifier,servers_spq_slot_mapping--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_service_profile_template;
DELIMITER $$
create procedure insert_service_profile_template(in project_id_in int(11)) 
block1: begin
	declare done1 int default false;
	declare servers_service_profile_template_id int;
	declare lsserver_id int;
	declare cur1 cursor for select primarykey from lsserver where type_type <> 'instance';		
	declare continue handler for not found set done1 = true;
  call insert_log(project_id_in,"insert_service_profile_template","inserting servers_service_profile_template,servers_spt_vhba_mapping,servers_spt_vnic_mapping,servers_service_profile_template--START");
	open cur1;
	read_loop1: loop
		fetch cur1 into lsserver_id;
		
		if done1 then
			leave read_loop1;
		end if;
		
		insert into servers_service_profile_template (project_id, description, name, organization, profile_template_type,
		uuid_id, boot_policy_id, local_disk_policy_id, server_pool_id,wwnn_id, san_connectivity_policy_id,lan_connectivity_policy_id, servers_host_firmware_id, servers_maintenance_policy_id) 	
		select project_id_in, a.descr, a.name, return_organization_id(project_id_in, a.fk_orgorg), a.type_type,
		 (select t.id from servers_uuid_pool t where t.project_id = project_id_in and t.name = a.identpoolname),
		 (select u.id from servers_boot_policy u where u.project_id = project_id_in and u.name = a.bootpolicyname ), 
		 (select v.id from servers_local_disc v where v.project_id = project_id_in and v.name = a.localdiskpolicyname),
		  (select w.id from servers_server_pool w where w.project_id = project_id_in and w.name = b.name),
		  (select x.id from san_wwnn x where x.project_id = project_id_in and x.wwnn_name = c.identpoolname),
		  (select y.id from san_connectivity_policy y where y.project_id = project_id_in and y.name = d.sanconnpolicyname),
		  (select p.id from lan_connectivity_policy p where p.project_id = project_id_in and p.name = d.lanconnpolicyname),
		  (select z.id from servers_host_firmware z where z.project_id = project_id_in and z.name = a.hostFwPolicyName),
		  (select p.id from servers_maintenance_policy p where p.project_id = project_id_in and p.name = a.maintPolicyName)
		from lsserver a left join lsrequirement b on b.fk_lsserver = a.primarykey left join vnicfcnode c on c.fk_lsserver = a.primarykey left join vnicconndef d on d.fk_lsserver = a.primarykey
		where  a.primarykey = lsserver_id;
		select last_insert_id() into servers_service_profile_template_id; 
		
		insert into servers_spt_vhba_mapping (spt_id, vhba_id)
		select servers_service_profile_template_id, (select x.id from san_vhba x where x.project_id = project_id_in and x.name = a.name)
		from vnicfc a where fk_lsserver = lsserver_id;
		
		insert into servers_spt_vnic_mapping (spt_id, vnic_id)
		select servers_service_profile_template_id, x.id from vnicether a , lan_vnic x where a.fk_lsserver = lsserver_id and x.project_id = project_id_in and x.name = a.name;
		
	end loop;
	close cur1;
	update servers_service_profile_template set wwnn_id = null where  project_id = project_id_in and san_connectivity_policy_id is not null;
	delete a from servers_spt_vhba_mapping a left join servers_service_profile_template b on a.spt_id = b.id where b.san_connectivity_policy_id is not null;
	call insert_log(project_id_in,"insert_service_profile_template","inserting servers_service_profile_template,servers_spt_vhba_mapping,servers_spt_vnic_mapping,servers_service_profile_template--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_vnic_template;
DELIMITER $$
create procedure insert_vnic_template(in project_id_in int(11)) 
block1: begin
	declare done1 int default false;
	declare lan_vnic_id int;
	declare vniclanconntempl_id int;
	declare cur1 cursor for select primarykey from vniclanconntempl;		
	declare continue handler for not found set done1 = true;
  call insert_log(project_id_in,"insert_vnic_template","inserting lan_vnic_template,lan_vnict_vlan_mapping--START");
	open cur1;
	read_loop: loop
		fetch cur1 into vniclanconntempl_id;
		
		if done1 then
			leave read_loop;
		end if;
		
		insert into lan_vnic_template (project_id, description, macpool_id, network_control_policy_id, organization, os_type, qos_policy_id, switch_id, target, template_type, vnict_name) 
		select project_id_in, a.descr, (select u.id from lan_macpool u where u.project_id = project_id_in and u.macpool_name= a.identpoolname) , (select v.id from lan_network_control_policy v where v.project_id = project_id_in and v.ncp_name= a.nwctrlpolicyname), return_organization_id(project_id_in, a.fk_orgorg), (select case when a.name like 'eth%' then 'Linux' when a.name like 'nic%' then 'Windows' end), (select w.id from lan_qos_policy w where w.project_id = project_id_in and name= a.qospolicyname) , a.switchid, a.target, a.templtype, a.name from vniclanconntempl a
		where a.primarykey = vniclanconntempl_id;
		select last_insert_id() into lan_vnic_id; 
		
		block2: begin  
			declare done2 int default false;
			declare vnicetherif_name varchar(50) default '';
			declare lan_vlan_id int;
			declare cur2 cursor for select a.name from vnicetherif a where a.fk_vniclanconntempl = vniclanconntempl_id;		
			declare continue handler for not found set done2 = true;
			open cur2;
				read_loop2: loop
					fetch cur2 into vnicetherif_name;
		
					if done2 then
						leave read_loop2;
					end if;
			
					select x.id into lan_vlan_id from lan_vlan x where x.project_id = project_id_in and x.vlan_name = vnicetherif_name;
				
					if lan_vlan_id is not null then
						insert into lan_vnict_vlan_mapping(vnict_id, vlan_id) values (lan_vnic_id, lan_vlan_id); 
					end if;
				end loop;
			close cur2;
		end block2;
	end loop;
	close cur1;
	call insert_log(project_id_in,"insert_vnic_template","inserting lan_vnic_template,lan_vnict_vlan_mapping--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_san_connectivity_policy;
DELIMITER $$
create procedure insert_san_connectivity_policy(in project_id_in int(11)) 
block1: begin
	declare done int default false;
	declare san_connectivity_policy_id int;
	declare vnicsanconnpolicy_id int;
	declare san_vhba_id int;
	declare cur cursor for select primarykey from vnicsanconnpolicy;		
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_san_connectivity_policy","inserting san_connectivity_policy,san_scp_vhba_mapping--START");
	open cur;
	read_loop: loop
		fetch cur into vnicsanconnpolicy_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into san_connectivity_policy (project_id, description, organization, name, wwnn_id) 
		select project_id_in, a.descr, return_organization_id(project_id_in, a.fk_orgorg), a.name, (select l.id from san_wwnn l where l.project_id = project_id_in and l.wwnn_name = b.identpoolname) from vnicsanconnpolicy a inner join vnicfcnode b  on b.fk_vnicsanconnpolicy = a.primarykey
		where a.primarykey = vnicsanconnpolicy_id ;
		select last_insert_id() into san_connectivity_policy_id; 
		
		select x.id into san_vhba_id from san_vhba x where x.project_id = project_id_in limit 1;
		
		if san_vhba_id is not null then
			insert into san_scp_vhba_mapping(scp_id, vhba_id)
			select san_connectivity_policy_id, vhbaId from (select san_connectivity_policy_id, (select x.id from san_vhba x where x.project_id = project_id_in and x.name = a.name) vhbaId 
			from vnicfc a where a.fk_vnicsanconnpolicy = vnicsanconnpolicy_id ) as T1 where vhbaId is not null;
		end if;		
	end loop;
	close cur;
	update servers_service_profile_template set wwnn_id = null where  project_id = project_id_in and san_connectivity_policy_id is not null;
	delete a from servers_spt_vhba_mapping a left join servers_service_profile_template b on a.spt_id = b.id where b.san_connectivity_policy_id is not null;
	call insert_log(project_id_in,"insert_san_connectivity_policy","inserting san_connectivity_policy,san_scp_vhba_mapping--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_lan_connectivity_policy;
DELIMITER $$
create procedure insert_lan_connectivity_policy(in project_id_in int(11)) 
block1: begin
	declare done int default false;
	declare lan_connectivity_policy_id int;
	declare vniclanconnpolicy_id int;
	declare lan_vnic_id int;
	declare cur cursor for select primarykey from vniclanconnpolicy;		
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_lan_connectivity_policy","inserting lan_connectivity_policy,lan_lcp_vnic_mapping--START");
	open cur;
	read_loop: loop
		fetch cur into vniclanconnpolicy_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into lan_connectivity_policy (project_id, description, organization, name) 
		select project_id_in, a.descr, return_organization_id(project_id_in, a.fk_orgorg), a.name from vniclanconnpolicy a where a.PrimaryKey = vniclanconnpolicy_id;
		select last_insert_id() into lan_connectivity_policy_id; 
		
		select x.id into lan_vnic_id from lan_vnic x where x.project_id = project_id_in limit 1;
		
		if lan_vnic_id is not null then
			insert into lan_lcp_vnic_mapping(lcp_id, vnic_id)
			select lan_connectivity_policy_id, vnicId from (select  lan_connectivity_policy_id,(select x.id from lan_vnic x where x.project_id = project_id_in and x.name = a.name) vnicId from vnicether a  where a.fk_vniclanconnpolicy = vniclanconnpolicy_id ) T1 where vnicId is not null;
		end if;		
	end loop;
	close cur;
	delete a from servers_spt_vnic_mapping a left join servers_service_profile_template b on a.spt_id = b.id where b.lan_connectivity_policy_id is not null;
	call insert_log(project_id_in,"insert_lan_connectivity_policy","inserting lan_connectivity_policy,lan_lcp_vnic_mapping--END");
	
end block1$$
DELIMITER ;

drop procedure if exists insert_boot_policy;
DELIMITER $$
create procedure insert_boot_policy(in project_id int(11)) 
block1: begin
	declare servers_boot_policy_san_id int;
	
  call insert_log(project_id,"insert_boot_policy","inserting servers_boot_policy,servers_boot_policy_lan,servers_boot_policy_local_disc--START");
  
	insert into servers_boot_policy (project_id, description, organization, name, boot_mode, enforce_vnic_name, reboot_on_update, secure_boot ) 
	select project_id, a.descr, return_organization_id(project_id, a.fk_orgorg), a.name, a.bootmode, (select case when a.enforcevnicname = 'yes' then 1 else 0  end), (select case when a.rebootonupdate = 'yes' then 1 else 0  end),(select case when a.bootmode = 'legacy' then 'n/a' else lcase(b.secureboot) end) from lsbootpolicy a left join lsbootbootsecurity b  on b.fk_lsbootpolicy = a.primarykey;

	insert into servers_boot_policy_lan (project_id, name, description, boot_policy_id, lan_order, boot_policy_type, vnic_id) 
	select project_id,concat('LAN-',camel_case(a.type_type)), concat('LAN-',camel_case(a.type_type)), (select l.id from servers_boot_policy l where l.project_id = project_id and l.name = c.name), b.order_order, camel_case(a.type_type), (select m.id from lan_vnic m where m.project_id = project_id and m.name = a.vnicname) 
	from lsbootlanimagepath a left join ( lsbootlan b  left join lsbootpolicy c on b.fk_lsbootpolicy = c.primarykey )on a.fk_lsbootlan = b.primarykey;

	call insert_san_and_target_boot_policy(project_id);	
-- Adding condition because of ADA restriction based on best practise 		
	select x.id into servers_boot_policy_san_id from servers_boot_policy_san x where x.project_id = project_id limit 1;
	
	insert into servers_boot_policy_local_disc (project_id, boot_order, boot_policy_id, device) 
	select project_id, d.order_order, (select l.id from servers_boot_policy l where l.project_id = project_id and l.name= c.name) ,'Local_Disk'
	from lsbootdefaultlocalimage d inner join (lsbootlocalstorage a inner join ( lsbootstorage b  inner join lsbootpolicy c on b.fk_lsbootpolicy = c.primarykey ) on a.fk_lsbootstorage = b.primarykey) on d.fk_lsbootlocalstorage = a.primarykey;
	insert into servers_boot_policy_local_disc (project_id, boot_order, boot_policy_id, device) 
	select project_id, d.order_order, (select l.id from servers_boot_policy l where l.project_id = project_id and l.name= c.name) ,'Local_LUN'
	from lsbootlocalhddimage d inner join (lsbootlocalstorage a inner join ( lsbootstorage b  inner join lsbootpolicy c on b.fk_lsbootpolicy = c.primarykey ) on a.fk_lsbootstorage = b.primarykey) on d.fk_lsbootlocalstorage = a.primarykey;		
	insert into servers_boot_policy_local_disc (project_id, boot_order, boot_policy_id, device) 
	select project_id, d.order_order, (select l.id from servers_boot_policy l where l.project_id = project_id and l.name= c.name) ,'SD_Card'
	from lsbootusbflashstorageimage d inner join (lsbootlocalstorage a inner join ( lsbootstorage b  inner join lsbootpolicy c on b.fk_lsbootpolicy = c.primarykey ) on a.fk_lsbootstorage = b.primarykey) on d.fk_lsbootlocalstorage = a.primarykey;		
	insert into servers_boot_policy_local_disc (project_id, boot_order, boot_policy_id, device) 
	select project_id, d.order_order, (select l.id from servers_boot_policy l where l.project_id = project_id and l.name = c.name) ,'External_USB'
	from lsbootusbexternalimage d inner join (lsbootlocalstorage a inner join ( lsbootstorage b  inner join lsbootpolicy c on b.fk_lsbootpolicy = c.primarykey ) on a.fk_lsbootstorage = b.primarykey) on d.fk_lsbootlocalstorage = a.primarykey;		
	insert into servers_boot_policy_local_disc (project_id, boot_order, boot_policy_id, device) 
	select project_id, d.order_order, (select l.id from servers_boot_policy l where l.project_id = project_id and l.name= c.name) ,'Internal_USB'
	from lsbootusbinternalimage d inner join (lsbootlocalstorage a inner join ( lsbootstorage b  inner join lsbootpolicy c on b.fk_lsbootpolicy = c.primarykey ) on a.fk_lsbootstorage = b.primarykey) on d.fk_lsbootlocalstorage = a.primarykey;
	
	call insert_log(project_id,"insert_boot_policy","inserting servers_boot_policy,servers_boot_policy_lan,servers_boot_policy_local_disc--END");
end block1$$
DELIMITER ;

drop function if exists return_organization_id;
DELIMITER $$
create function return_organization_id(project_id int(11),orgorg_id int(11)) returns integer 
begin 
	set @organization_id = (select t.id from organizations as t inner join orgorg as r on (t.name = r.name and r.primarykey = orgorg_id) where t.project_id = project_id);
	return @organization_id;
end$$

DELIMITER ;

drop function if exists camel_case;
DELIMITER $$
create function camel_case(in_string varchar(50)) returns varchar(50)
begin
  declare result varchar(50) default '';
  set in_string = concat(lower(in_string),' ');
  while (instr(in_string, ' ') != 0 ) do
	  set result = concat(result,upper(left(in_string,1)),substr(in_string,2,position(' 'in in_string)-1));
	  set in_string = substr(in_string,position(' ' in in_string) + 1, length(in_string));
  end while;
  set result = left(result,length(result)-1);
  return result;
end$$
DELIMITER ;

drop procedure if exists insert_admin_ldap_provider_group;
DELIMITER $$
create procedure insert_admin_ldap_provider_group(in project_id_in int(11)) 
block1: begin
	declare done int default false;
	declare admin_ldap_provider_group_id int;
	declare aaaProviderGroup_id int;
	declare admin_ldap_provider_id int;
	declare cur cursor for select primarykey from aaaprovidergroup where FK_aaaldapep is not null;		
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_admin_ldap_provider_group","inserting admin_ldap_provider_group,admin_ldap_group_provider_mapping--START");
	open cur;
	read_loop: loop
		fetch cur into aaaProviderGroup_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into admin_ldap_provider_group (project_id, name) 
		select project_id_in, a.name from aaaprovidergroup a where a.primarykey = aaaProviderGroup_id;
		select last_insert_id() into admin_ldap_provider_group_id; 
		
		select x.id into admin_ldap_provider_id from admin_ldap_provider x where x.project_id = project_id_in limit 1;
		
		if admin_ldap_provider_id is not null then
			insert into admin_ldap_group_provider_mapping( ldap_group_id, ldap_provider_id, provider_order)
			select admin_ldap_provider_group_id, (select x.id from admin_ldap_provider x where x.project_id = project_id_in and x.host_name = a.name), a.order_order
			from aaaproviderref a where a.fk_aaaProviderGroup = aaaProviderGroup_id;
		end if;	
	end loop;
	close cur;
	call insert_log(project_id_in,"insert_admin_ldap_provider_group","inserting admin_ldap_provider_group,admin_ldap_group_provider_mapping--END");
end block1$$
DELIMITER ;

DROP FUNCTION IF EXISTS return_local_disk_policy_mode;
DELIMITER $$
CREATE FUNCTION return_local_disk_policy_mode(mode varchar(45)) RETURNS VARCHAR(50) 
BEGIN 
	Set @local_disk_policy_mode = (Select CASE 
							when mode = "no-local-storage" then "No Local Storage" 
							when mode = "raid-striped" then "RAID 0 Striped" 
							when mode = "raid-mirrored" then "RAID 1 Mirrored" 
							when mode = "any-configuration" then "Any Configuration" 
							when mode = "no-raid" then "No RAID" 
							when mode = "raid-striped-parity" then "RAID 5 Striped Parity" 
							when mode = "raid-striped-dual-parity" then "RAID 6 Striped Dual Parity" 
							when mode = "raid-mirrored-striped" then "RAID 10 Mirrored And Striped"
							when mode = "raid-striped-parity-striped" then "RAID 50 Striped Parity And Striped"
							when mode = "raid-striped-dual-parity-striped" then "RAID 60 Striped Dual Parity And Striped" 
						END); 
	Return @local_disk_policy_mode;
END$$
DELIMITER ;

drop procedure if exists insert_admin_ldap_group_map;
DELIMITER $$
create procedure insert_admin_ldap_group_map(in project_id_in int(11)) 
block1: begin
	declare done int default false;
	declare aaaLdapGroup_id int;
	declare admin_ldap_group_map_id int;
	declare cur cursor for select a.PrimaryKey from aaaLdapGroup a;		
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_admin_ldap_group_map","inserting admin_ldap_group_map,admin_ldap_group_map_roles_mapping,admin_ldap_group_map_locales_mapping--START");
	open cur;
	read_loop: loop
		fetch cur into aaaLdapGroup_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into admin_ldap_group_map (project_id, name)
		select project_id_in, a.name from aaaLdapGroup a where a.primarykey = aaaLdapGroup_id;
		select last_insert_id() into admin_ldap_group_map_id; 
		
		insert into admin_ldap_group_map_roles_mapping (ldap_group_map_id, ldap_role_id)
		select admin_ldap_group_map_id, (select x.id from admin_ldap_role x where x.project_id = project_id_in and x.name = a.name)
		from aaaUserRole a where a.FK_aaaLdapGroup = aaaLdapGroup_id;
		
		insert into admin_ldap_group_map_locales_mapping (ldap_group_map_id, ldap_locale_id)
		select admin_ldap_group_map_id, (select ll.id from admin_ldap_locale ll where ll.project_id = project_id_in and ll.name = a.name)
		from aaaUserLocale a where a.FK_aaaLdapGroup = aaaLdapGroup_id;
		
	end loop;
	close cur;
   call insert_log(project_id_in,"insert_admin_ldap_group_map","inserting admin_ldap_group_map,admin_ldap_group_map_roles_mapping,admin_ldap_group_map_locales_mapping--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_admin_ldap_role;
DELIMITER $$
create procedure insert_admin_ldap_role(in project_id_in int(11)) 
block1: begin
	declare done int default false;
	declare admin_ldap_role_id int;
	declare aaaRole_name varchar(45) default "";
	declare aaaRole_priv varchar(200) default "";
	   declare temp_privilege varchar(45) default "";
	declare num_of_tokens int;
	declare cur cursor for select a.name,a.priv from aaaRole a;
	declare continue handler for not found set done = true;
	   call insert_log(project_id_in,"insert_admin_ldap_role","inserting admin_ldap_role--START");
	open cur;
	read_loop: loop
	fetch cur into aaaRole_name,aaaRole_priv;
	
	if done then
	leave read_loop;
	end if;
	
	insert into admin_ldap_role (project_id, name) 
	select project_id_in, aaaRole_name;
	select last_insert_id() into admin_ldap_role_id; 
	
	Select return_number_of_tokens(aaaRole_priv, ',') into num_of_tokens;
	while (num_of_tokens != 0 ) do
	       set temp_privilege = SPLIT_STR(aaaRole_priv, ',', num_of_tokens);
	insert into admin_ldap_roles_privileges_mapping (ldap_role_id, roles_privileges_id) 
	select admin_ldap_role_id,(select x.id from admin_privilege x where name = temp_privilege);
	
	set num_of_tokens = num_of_tokens - 1;
	   end while;
	end loop;
	close cur;
	call insert_log(project_id_in,"insert_admin_ldap_role","inserting admin_ldap_role--END");
end block1$$
DELIMITER ;

DROP FUNCTION IF EXISTS SPLIT_STR;
CREATE FUNCTION SPLIT_STR( x VARCHAR(255), delim VARCHAR(12), pos INT) RETURNS VARCHAR(255)
RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
       LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1),
       delim, '');

drop function if exists return_number_of_tokens;
DELIMITER $$
CREATE FUNCTION return_number_of_tokens( in_string VARCHAR(255), delim VARCHAR(12)) RETURNS int
begin
  declare result int default 1;
  set in_string = concat(lower(in_string),' ');
  while (instr(in_string, delim) != 0 ) do
	  set result = result + 1;
	  set in_string = substr(in_string,position(delim in in_string) + 1, length(in_string));
  end while;
  return result;  	   
end$$
DELIMITER ;    

drop procedure if exists insert_admin_ldap_locale;
DELIMITER $$
create procedure insert_admin_ldap_locale(in project_id_in int(11)) 
block1: begin
	declare done1 int default false;
	declare admin_ldap_locale_id int;
	declare aaaLocale_id int;
	declare aaaLocale_name varchar(45) default "";
	declare cur1 cursor for select a.primaryKey, a.name from aaaLocale a;
	declare continue handler for not found set done1 = true;
  call insert_log(project_id_in,"insert_admin_ldap_locale","inserting admin_ldap_locale,admin_ldap_locales_org_mapping--START");
	open cur1;
	read_loop1: loop
		fetch cur1 into aaaLocale_id, aaaLocale_name;
		
		if done1 then
			leave read_loop1;
		end if;
		
		insert into admin_ldap_locale (project_id, name) 
		select project_id_in, aaaLocale_name;
		select last_insert_id() into admin_ldap_locale_id; 
		
		block2: begin  
			declare done2 int default false;
			declare org_name varchar(50) default '';
			declare cur2 cursor for select x.orgDn from aaaOrg x where x.FK_aaaLocale = aaaLocale_id;		
			declare continue handler for not found set done2 = true;
			open cur2;
				read_loop2: loop
					fetch cur2 into org_name;
		
					if done2 then
						leave read_loop2;
					end if;
			
					insert into admin_ldap_locales_org_mapping (ldap_locale_id, organization) 
					select admin_ldap_locale_id, (select x.id from organizations x where x.project_id = project_id_in and x.name = SUBSTRING_INDEX(org_name, 'org-', -1));
		
				end loop;
			close cur2;
		end block2;
		
	end loop;
	close cur1;
	call insert_log(project_id_in,"insert_admin_ldap_locale","inserting admin_ldap_locale,admin_ldap_locales_org_mapping--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_admin_radius;
DELIMITER $$
CREATE PROCEDURE insert_admin_radius(in project_id_in int(11))
block1: begin 
	call insert_log(project_id_in,"insert_admin_radius","inserting admin_radius_provider,admin_radius_general--START");
	
	INSERT INTO admin_radius_provider (project_id, authorization_port, hostname, radius_order, retries, timeout, ssl_key)
	SELECT  project_id_in, a.authPort, a.name, a.order_order, a.retries, a.timeout, a.encKey FROM aaaRadiusProvider a;
	
	CALL insert_admin_radius_provider_group(project_id_in);
	
	INSERT INTO admin_radius_general (project_id, retries, timeout)
	SELECT  project_id_in, a.retries, a.timeout FROM aaaRadiusEp a ;
call insert_log(project_id_in,"insert_admin_radius","inserting admin_radius_provider,admin_radius_general--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_admin_radius_provider_group;
DELIMITER $$
create procedure insert_admin_radius_provider_group(in project_id_in int(11)) 
block1: begin 
	declare done int default false;
	declare aaaProviderGroup_id int;
	declare admin_radius_provider_group_id int;
	declare admin_radius_provider_id int;
	declare cur cursor for select primarykey from aaaprovidergroup where FK_aaaRadiusEp is not null;		
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_admin_radius_provider_group","inserting admin_radius_provider_group--START");
	open cur;
	read_loop: loop
		fetch cur into aaaProviderGroup_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into admin_radius_provider_group (project_id, name) 
		select project_id_in, a.name from aaaprovidergroup a where a.primarykey = aaaProviderGroup_id;
		select last_insert_id() into admin_radius_provider_group_id; 
		
		select x.id into admin_radius_provider_id from admin_radius_provider x where x.project_id = project_id_in limit 1;
		
		if admin_radius_provider_id is not null then
			insert into admin_radius_group_provider_mapping( radius_group_id, radius_provider_id, radius_provider_order)
			select admin_radius_provider_group_id, (select x.id from admin_radius_provider x where x.project_id = project_id_in and x.hostname = a.name), a.order_order
			from aaaproviderref a where a.fk_aaaProviderGroup = aaaProviderGroup_id;
		end if;	
	end loop;
	close cur;
	call insert_log(project_id_in,"insert_admin_radius_provider_group","inserting admin_radius_provider_group--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_admin_tacacs;
DELIMITER $$
create procedure insert_admin_tacacs(in project_id_in int(11)) 
block1: begin 
	call insert_log(project_id_in,"insert_admin_tacacs","inserting admin_tacacs_provider,admin_tacacs_general--START");

	INSERT INTO admin_tacacs_provider (project_id, host_name, port, provider_order, timeout, provider_key)
	SELECT  project_id_in, a.name, a.port,a.order_order, a.timeout, a.encKey FROM aaaTacacsPlusProvider a;
	
	CALL insert_admin_tacacs_provider_group(project_id_in);
	
	INSERT INTO admin_tacacs_general (project_id, timeout)
	SELECT  project_id_in, a.timeout FROM aaaTacacsPlusEp a ;
	call insert_log(project_id_in,"insert_admin_tacacs","inserting admin_tacacs_provider,admin_tacacs_general--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_admin_tacacs_provider_group;
DELIMITER $$
create procedure insert_admin_tacacs_provider_group(in project_id_in int(11)) 
block1: begin 
	declare done int default false;
	declare aaaProviderGroup_id int;
	declare admin_tacacs_provider_group_id int;
	declare admin_tacacs_provider_id int;
	declare cur cursor for select primarykey from aaaprovidergroup where FK_aaaTacacsPlusEp is not null;		
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_admin_tacacs_provider_group","inserting admin_tacacs_provider_group--START");
	open cur;
	read_loop: loop
		fetch cur into aaaProviderGroup_id;
		
		if done then
			leave read_loop;
		end if;
		
		insert into admin_tacacs_provider_group (project_id, name) 
		select project_id_in, a.name from aaaprovidergroup a where a.primarykey = aaaProviderGroup_id;
		select last_insert_id() into admin_tacacs_provider_group_id; 
		
		select x.id into admin_tacacs_provider_id from admin_tacacs_provider x where x.project_id = project_id_in limit 1;
		
		if admin_tacacs_provider_id is not null then
			insert into admin_tacacs_group_provider_mapping( tacacs_group_id, tacacs_provider_id, provider_order)
			select admin_tacacs_provider_group_id, (select x.id from admin_tacacs_provider x where x.project_id = project_id_in and x.host_name = a.name), a.order_order
			from aaaproviderref a where a.fk_aaaProviderGroup = aaaProviderGroup_id;
		end if;	
	end loop;
	close cur;
	call insert_log(project_id_in,"insert_admin_tacacs_provider_group","inserting admin_tacacs_provider_group--END");
end block1$$
DELIMITER ;

drop procedure if exists insert_servers_bios_policy;
DELIMITER $$
create procedure insert_servers_bios_policy(in project_id_in int(11)) 
begin
	declare done int default false;
	declare biosvprofile_id int;
	declare servers_bios_policy_id int;
	declare name varchar(45) default "";
	declare descr varchar(45) default "";
	declare reboot_on_change varchar(3) default "";
	declare orgorg_id int;
	declare cur cursor for select b.PrimaryKey, b.name, b.descr, b.rebootOnUpdate, b.FK_orgOrg from biosVProfile b;
	declare continue handler for not found set done = true;
  call insert_log(project_id_in,"insert_servers_bios_policy","inserting servers_bios_policy--START");
	open cur;
	read_loop: loop
		fetch cur into biosvprofile_id, name, descr, reboot_on_change, orgorg_id;
		if done then
			leave read_loop;
		end if;
		
		insert into servers_bios_policy (project_id, name, description, reboot_on_change, organization) 
		values (project_id_in, name, descr, reboot_on_change, return_organization_id(project_id_in, orgorg_id));
		select last_insert_id() into servers_bios_policy_id; 
		-- Main  
		update servers_bios_policy set quiet_boot = (Select vpQuietBoot from biosVfQuietBoot where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set post_error_pause = (Select VpPOSTErrorPause from biosVfPOSTErrorPause where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set resume_ac_on_power_loss = (Select vpResumeOnACPowerLoss from biosVfResumeOnACPowerLoss where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set front_panel_lockout = (Select VpFrontPanelLockout from biosVfFrontPanelLockout where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;

		-- Processor
		update servers_bios_policy set turbo_boost = (Select vpIntelTurboBoostTech from biosVfIntelTurboBoostTech where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set enhanced_intel_speedstep = (Select vpEnhancedIntelSpeedStepTech from biosVfEnhancedIntelSpeedStepTech where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set hyper_threading = (Select vpIntelHyperThreadingTech from biosVfIntelHyperThreadingTech where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set core_multi_processing = (Select vpCoreMultiProcessing from biosVfCoreMultiProcessing where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set execute_disabled_bit = (Select vpExecuteDisableBit from biosVfExecuteDisableBit where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set virtualization_technology = (Select vpIntelVirtualizationTechnology from biosVfIntelVirtualizationTechnology where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set hardware_prefetcher = (Select VpPOSTErrorPause from biosVfPOSTErrorPause where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set adjacent_cache_line_prefetcher = (Select vpResumeOnACPowerLoss from biosVfResumeOnACPowerLoss where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set dcu_streamer_prefetch = (Select VpFrontPanelLockout from biosVfFrontPanelLockout where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;

	--	update servers_bios_policy set dcu_ip_prefetch = (Select VpPOSTErrorPause from biosVfPOSTErrorPause where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set direct_cache_access = (Select vpDirectCacheAccess from biosVfDirectCacheAccess where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set processor_c_state = (Select vpProcessorCState from biosVfProcessorCState where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;

		update servers_bios_policy set processor_c1e = (Select vpProcessorC1E from biosVfProcessorC1E where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set processor_c3_report = (Select vpProcessorC3Report from biosVfProcessorC3Report where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set processor_c7_report = (Select vpProcessorC7Report from biosVfProcessorC7Report where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set processor_c6_report = (Select vpProcessorC6Report from biosVfProcessorC6Report where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set cpu_performance = (Select vpCPUPerformance from biosVfCPUPerformance where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set max_variable_mtrr_setting = (Select vpProcessorMtrr from biosVfMaxVariableMTRRSetting where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set local_x2_apic = (Select vpLocalX2Apic from biosVfLocalX2Apic where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set power_technology = (Select VpFrontPanelLockout from biosVfFrontPanelLockout where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set energy_performance = (Select vpQuietBoot from biosvfquietBoot where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set frequency_floor_override = (Select VpPOSTErrorPause from biosVfPOSTErrorPause where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set pstate_coordination = (Select vpResumeOnACPowerLoss from biosVfResumeOnACPowerLoss where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set dram_clock_throttling = (Select VpFrontPanelLockout from biosVfFrontPanelLockout where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set channel_interleaving = (Select vpQuietBoot from biosvfquietBoot where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set rank_interleaving = (Select VpPOSTErrorPause from biosVfPOSTErrorPause where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set demand_scrub = (Select vpResumeOnACPowerLoss from biosVfResumeOnACPowerLoss where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set patrol_scrub = (Select VpFrontPanelLockout from biosVfFrontPanelLockout where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	
	-- Intel Directed IO
		update servers_bios_policy s, biosVfIntelVTForDirectedIO b set 
		s.vt_for_directed_io = b.vpIntelVTForDirectedIO, s.interrupt_remap = b.vpIntelVTDInterruptRemapping, s.coherency_support = b.vpIntelVTDCoherencySupport, s.ats_support = b.vpIntelVTDATSSupport, s.pass_through_dma_support = b.vpIntelVTDPassThroughDMASupport 
		where b.FK_biosVProfile = biosvprofile_id and s.id = servers_bios_policy_id;

	-- RAS Memory
		update servers_bios_policy set memory_ras_config = (Select vpSelectMemoryRASConfiguration from biosVfSelectMemoryRASConfiguration where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set numa = (Select vpNUMAOptimized from biosVfNUMAOptimized where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set lv_ddr_mode = (Select vpLvDDRMode from biosVfLvDIMMSupport where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set dram_refresh_rate = (Select vpDramRefreshRate from biosVfDramRefreshRate where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set mirroring_mode = (Select vpMirroringMode from biosVfMirroringMode where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set sparing_mode = (Select vpSparingMode from biosVfSparingMode where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	
	-- Serial Port
  		update servers_bios_policy set serial_port_a = (Select vpSerialPortAEnable from biosVfSerialPortAEnable where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
  		update servers_bios_policy s, biosVfUSBBootConfig b set 
		s.legacy_usb_support = b.vpLegacyUSBSupport, s.make_device_non_bootable = b.vpMakeDeviceNonBootable
		where b.FK_biosVProfile = biosvprofile_id and s.id = servers_bios_policy_id;
		
	-- USB	
	  	update servers_bios_policy set usb_system_idle_power_optimizing_setting = (Select vpUSBIdlePowerOptimizing from biosVfUSBSystemIdlePowerOptimizingSetting where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set usb_front_panel_access_lock = (Select vpUSBFrontPanelLock from biosVfUSBFrontPanelAccessLock where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set all_usb_devices = (Select vpAllUSBDevices from biosVfAllUSBDevices where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	
	--	update servers_bios_policy s, biosVfUSBPortConfiguration b set 
	--	s.port_6064_emulation = b.vpPort6064Emulation, s.usb_port_front = b.vpUSBPortFront, 
	--	s.usb_port_internal = b.vpUSBPortInternal, s.usb_port_kvm = b.vpUSBPortKVM, s.usb_port_rear = b.vpUSBPortRear,
	--	s.usb_port_sd_card = b.vpUSBPortSDCard, s.usb_port_v_media = b.vpUSBPortVMedia 
	--	where b.FK_biosVProfile = biosvprofile_id and s.id = servers_bios_policy_id;
	
	-- PCI
	  	update servers_bios_policy set max_memory_below_4g = (Select vpMaximumMemoryBelow4GB from biosVfMaximumMemoryBelow4GB where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set memory_mapped_io_above_4gb_config = (Select vpMemoryMappedIOAbove4GB from biosVfMemoryMappedIOAbove4GB where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set vga_priority = (Select vpVGAPriority from biosVfVGAPriority where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;

	-- QPI  
  		update servers_bios_policy set qpi_link_frequency_select = (Select vpQPILinkFrequencySelect from biosVfQPILinkFrequencySelect where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	
  	-- LOM and PCIe Slots
  	-- update servers_bios_policy s, biosVfLOMPortsConfiguration b set 
	-- s.all_onboard_lom_ports = b.vpAllOnboardLOMPorts, s.lom_port0_option_rom = b.vpLOMPort0OptionROM, s.lom_port1_option_rom = b.vpIntelVTDCoherencySupport,
	-- s.lom_port2_option_rom = b.vpLOMPort2OptionROM, s.lom_port3_option_rom = b.vpLOMPort3OptionROM 
	-- where b.FK_biosVProfile = biosvprofile_id and s.id = servers_bios_policy_id;

	-- update servers_bios_policy s, biosVfPCISlotLinkSpeed b set 
	-- s.pcie_slot_sas_option_rom = b.vpAllOnboardLOMPorts, s.pcie_slot1_link_speed = b.vpPCIeSlot1LinkSpeed, s.pcie_slot2_link_speed = b.vpPCIeSlot2LinkSpeed,
	-- s.pcie_slot3_link_speed = b.vpPCIeSlot3LinkSpeed, s.pcie_slot4_link_speed = b.vpPCIeSlot4LinkSpeed , s.pcie_slot5_link_speed = b.vpPCIeSlot5LinkSpeed, s.pcie_slot6_link_speed = b.vpPCIeSlot6LinkSpeed,
	-- s.pcie_slot7_link_speed = b.vpPCIeSlot7LinkSpeed , s.pcie_slot8_link_speed = b.vpPCIeSlot8LinkSpeed , s.pcie_slot9_link_speed = b.vpPCIeSlot9LinkSpeed, s.pcie_slot10_link_speed = b.vpPCIeSlot10LinkSpeed
	-- where b.FK_biosVProfile = biosvprofile_id and s.id = servers_bios_policy_id;		

  	-- Boot Options  
   		update servers_bios_policy set boot_option_retry = (Select vpBootOptionRetry from biosVfBootOptionRetry where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set onboard_scu_storage_support = (Select vpOnboardSCUStorageSupport from biosVfOnboardStorage where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
    	update servers_bios_policy s, biosVfIntelEntrySASRAIDModule b set 
			s.intel_entry_sas_raid = b.vpSASRAID, s.intel_entry_sas_raid_module = b.vpSASRAIDModule
			where b.FK_biosVProfile = biosvprofile_id and s.id = servers_bios_policy_id;

	-- Server Management
  
	   	update servers_bios_policy set assert_nmi_on_serr = (Select vpAssertNMIOnSERR from biosVfAssertNMIOnSERR where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set assert_nmi_on_perr = (Select vpAssertNMIOnPERR from biosVfAssertNMIOnPERR where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set os_boot_watchdog_timer = (Select vpOSBootWatchdogTimer from biosVfOSBootWatchdogTimer where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
		update servers_bios_policy set os_boot_watchdog_timer_timeout_policy = (Select vpOSBootWatchdogTimerPolicy from biosVfOSBootWatchdogTimerPolicy where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	    update servers_bios_policy set os_boot_watchdog_timer_timeout = (Select vpOSBootWatchdogTimerTimeout from biosVfOSBootWatchdogTimerTimeout where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	--	update servers_bios_policy set frb2_timer = (Select vpFRB2Timer from biosVfFRB2Timer where FK_biosVProfile = biosvprofile_id) where id = servers_bios_policy_id;
	    
	    update servers_bios_policy s, biosVfConsoleRedirection b set 
		s.console_redirection = b.vpConsoleRedirection, s.flow_control = b.vpFlowControl, 
		s.legacy_os_redirect = b.vpLegacyOSRedirection, s.terminal_type = b.vpTerminalType, 
	--	s.putty_keypad = b.vpPuttyKeyPad, 
		s.baud_rate = b.vpBaudRate
		where b.FK_biosVProfile = biosvprofile_id and s.id = servers_bios_policy_id;
  
	end loop;
	close cur;
	call insert_log(project_id_in,"insert_servers_bios_policy","inserting servers_bios_policy--END");
end$$
DELIMITER ;

drop procedure if exists insert_call_home;
DELIMITER $$
create procedure insert_call_home(in project_id int(11)) 
block1: begin  
	  	declare call_home_profile_id int;
	    declare callhome_profile_name varchar(40);
	    declare callhome_profile_level varchar(20);
	    declare callhome_profile_alertGroups varchar(120);
	    declare callhome_profile_format varchar(20);
	    declare callhome_profile_maxsize varchar(20);
	    declare callhome_profile_priamary_key int;
	    declare done int default false;
	    declare counter int;
	    declare alertGroup varchar(25);
	    declare num_of_tokens int;
	    declare cur_callhome cursor for select a.primarykey, a.name, a.level, a.alertgroups, a.format, a.maxsize from callhomeprofile a left join callhomeep b on b.primarykey = a.FK_callhomeep;
	    declare continue handler for not found set done = true;
    
    call insert_log(project_id,"insert_call_home","inserting admin_callhome_general,admin_callhome_system_inventory,admin_callhome_profile--START");
    
	insert into admin_callhome_general (project_id, state, switch_priority, throttling, contact, phone, email, address, customer_id, contract_id, site_id, email_from, reply_to, host, port ) 
		select project_id, a.adminState, b.urgency, a.alertThrottlingAdminState, b.contact, b.phone, b.email, b.addr,b.customer, b.contract,b.site, b.from_from, b.replyTo, c.host, c.port   from callhomeep a, callhomesource b, callhomesmtp c
		where b.FK_callhomeep = a.primarykey and c.FK_callhomeep = a.primarykey; 
        
	insert into  admin_callhome_policy (project_id, state, cause)
		select  project_id, a.adminState, a.cause from callhomepolicy a left join callhomeep b on b.primarykey = a.FK_callhomeep;
        
	insert into admin_callhome_system_inventory (project_id, send_periodically, send_interval_days, send_interval_hours, send_interval_minutes)
		select project_id, a.adminstate, a.intervalDays, a.timeOfDayHour, a.timeOfDayMinute  from callhomeperiodicsysteminventory a left join callhomeep b on b.primarykey = a.FK_callhomeep;
        
	open cur_callhome;
		read_loop1 : loop
		
			fetch cur_callhome into callhome_profile_priamary_key, callhome_profile_name, callhome_profile_level,callhome_profile_alertGroups, callhome_profile_format, callhome_profile_maxsize;
			
            if done then
				leave read_loop1;
			end if;
        
			insert into admin_callhome_profile(project_id, name, level, format, max_msg_size)
				select project_id, callhome_profile_name, callhome_profile_level, callhome_profile_format, cast(callhome_profile_maxsize as unsigned);
			
			select last_insert_id() into call_home_profile_id; 
            
            Select return_number_of_tokens(callhome_profile_alertGroups, ',') into num_of_tokens;
			
            while (num_of_tokens != 0 ) do
				select SPLIT_STR(callhome_profile_alertGroups, ",", num_of_tokens) into alertGroup;
                if(alertGroup > '') then
					insert into admin_callhome_profile_alert_group_mapping(profile_id, alert_group_id) 
						select call_home_profile_id, (select id from admin_callhome_alert_group where xml_value= alertGroup and alertGroup != 'all');
				end if;
			
				set num_of_tokens = num_of_tokens - 1;
            end while;
            
            block2: begin
				declare participant_email varchar(25);
                declare done1 int default false;
				declare cur cursor for select a.email from callhomedest a where a.FK_callhomeprofile = callhome_profile_priamary_key;
                declare continue handler for not found set done1 = true;
                
                open cur;
					read_loop2: loop
						fetch cur into participant_email;
                        if done1 then
							leave read_loop2;
						end if;
                        insert into admin_callhome_profile_recipient_mapping(profile_id, email) 
							select call_home_profile_id, participant_email;
                    end loop;
				close cur;
            end block2;
        end loop;
	close cur_callhome;
    call insert_log(project_id,"insert_call_home","inserting admin_callhome_general,admin_callhome_system_inventory,admin_callhome_profile--END");
end block1$$
DELIMITER ;


drop procedure if exists insert_default_profile_when_project_creation;
DELIMITER $$
create procedure insert_default_profile_when_project_creation(in project_id_in int(11)) 
block1: begin
	declare  callhome_profile_id int(11);
	
	insert into admin_callhome_profile (project_id, name, description, level, format, max_msg_size)
			select project_id_in, 'CiscoTAC-1' , 'Built-in ciscoTac formate for profile', 'normal','xml', 5000000 from project_details where id = project_id_in ;
		select last_insert_id() into callhome_profile_id;
		
		 insert into admin_callhome_profile_alert_group_mapping(profile_id, alert_group_id) 
							select callhome_profile_id, (select id from admin_callhome_alert_group where xml_value = "ciscoTac");
                
	
	insert into admin_callhome_profile (project_id, name,description, level, format, max_msg_size)  
		select id, 'full_txt' , 'Built-in all formate for profile', 'warning', 'fullTxt', 5000000 from project_details where id = project_id_in ;
		select last_insert_id() into callhome_profile_id;
		
		block2: begin
				declare alert_group_id int(11);
                declare done2 int default false;
				declare cur cursor for select id from admin_callhome_alert_group;
                declare continue handler for not found set done2 = true;
                
                open cur;
					read_loop2: loop
						fetch cur into alert_group_id;
                        if done2 then
							leave read_loop2;
						end if;
                        insert into admin_callhome_profile_alert_group_mapping(profile_id, alert_group_id) 
							select callhome_profile_id, alert_group_id;
                    end loop;
				close cur;
            end block2;
		
	insert into admin_callhome_profile (project_id, name,description, level, format, max_msg_size)  
		select id, 'short_txt' , 'Built-in all formate for profile', 'warning', 'shortTxt', 5000000 from project_details where id = project_id_in ;
		
		select last_insert_id() into callhome_profile_id;
		
		block3: begin
				declare alert_group_id int(11);
                declare done3 int default false;
				declare cur cursor for select id from admin_callhome_alert_group;
                declare continue handler for not found set done3 = true;
                
                open cur;
					read_loop3: loop
						fetch cur into alert_group_id;
                        if done3 then
							leave read_loop3;
						end if;
                        insert into admin_callhome_profile_alert_group_mapping(profile_id, alert_group_id) 
							select callhome_profile_id, alert_group_id;
                    end loop;
				close cur;
            end block3;
end block1$$
DELIMITER ;

drop procedure if exists insert_call_home_default_profile_if_not_created;
DELIMITER $$
create procedure insert_call_home_default_profile_if_not_created(in project_id_in int(11)) 
block1: begin  
	declare rows_count int;
	select count(*) into rows_count from admin_callhome_profile where project_id = project_id_in;
	
		if rows_count = 0 then
      		call insert_default_profile_when_project_creation(project_id_in);
     end if;
end block1$$
DELIMITER ;


drop procedure if exists insert_log;
DELIMITER $$
create procedure insert_log(in project_id_in int(11), in procedureName varchar(100), in logMsg varchar(512))
block1: begin
	declare is_project_id int(11);
	start transaction;
		select project_id from sp_tmplog where project_id = project_id_in into is_project_id;
		if is_project_id then
  			update sp_tmplog set procedure_name = procedureName, msg=logMsg where project_id = project_id_in;
  		else
  			insert into sp_tmplog (project_id,procedure_name,msg) values(project_id_in,procedureName,logMsg);
  		end if;
  	commit;
end block1$$
DELIMITER ;

-- @added procedure for 4.0 release
drop procedure if exists insert_or_update_scalability_ports;
DELIMITER $$
create procedure insert_or_update_scalability_ports(in project_id_in int(11)) 
block1: begin
	
	call insert_log(project_id_in,"insert_or_update_scalability_ports","inserting/updating scalability ports--START");

	update equipment_mini_scalability, fabricdceswsrv a 
		left join fabricsubgroup b on a.primaryKey = b.fk_fabricdceswsrv 
		left join fabricdceswsrvep c on b.primaryKey = c.fk_fabricsubgroup 
		SET is_configured = 1 where fi_id = a.id and port_id = c.portId and project_id = project_id_in; 
     
     call insert_log(project_id_in,"insert_or_update_scalability_ports","inserting/updating scalability ports--END");
end block1$$
DELIMITER ;

-- @added procedure for 4.0 release
drop procedure if exists insert_ethernet_fc_mode;
DELIMITER $$
create procedure insert_ethernet_fc_mode(in project_id_in int(11)) 
block1: begin
	
	call insert_log(project_id_in,"insert_ethernet_fc_mode","inserting ethernet_fc_mode--START");

	insert into infrastructure_ethernet_fc_mode (project_id, ethernet_mode, fc_mode)
		select project_id_in, (select a.mode_mode from fabriclancloud a), (select b.mode_mode from fabricsancloud b);
     
     call insert_log(project_id_in,"insert_ethernet_fc_mode","inserting thernet_fc_mode--END");
end block1$$
DELIMITER ;