package com.cisco.ca.cstg.pdi.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateAlterScriptsForUCS {
	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateAlterScriptsForUCS.class);
	
	private static final String CREATE_SCRIPT = "/db_scripts-2.0/ucs_db_scripts/ucs_table_scripts.sql";
	private static final String ALTER_TABLE = "ALTER TABLE";
	public void generateAlterTableScriptForTables() throws IOException {
		//reads ucs_table_scripts.sql @src/main/resources/ which contains the creation scripts for UCS table
		//These script are generated from Complete_scenario_for_xsd.xml
		//generates ucs_alter_scripts.sql @pdiHome which contains primary and foreign key alter scripts for tables
		BufferedWriter bw = null;
		Matcher m = null;
		StringBuilder returnPath = new StringBuilder(PdiConfig.getProperty(Constants.PDI_HOME));
		String createTablePatternStr = "CREATE TABLE\\s*`(\\S+)`\\s*\\((.*)\\).*";
		String foreignColPatternStr = "`FK_(\\S+)`";
		String keyWordPatternStr = "`(mode|order|share|domain|state|cos|prefix|type|count|select|from|to|drop)`\\s*varchar\\s*\\(255\\)\\s*DEFAULT\\s*NULL";

		Pattern createTablePattern = Pattern.compile(createTablePatternStr);
		Pattern foreignColPattern = Pattern.compile(foreignColPatternStr);
		Pattern keyWordPattern = Pattern.compile(keyWordPatternStr);

		try {
			StringBuilder readContent = readFile();
			String[] indivCreateTableScript = readContent.toString().split(";");
			
			File file = new File(returnPath+File.separator+"ucs_alter_scripts.sql");
			if (!file.exists()) {
				try{
					file.createNewFile();
				}catch( IOException ex){
					ex.getMessage();
					throw ex;
				}
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			int count = 0;
			for (int i = 0; i < indivCreateTableScript.length; i++) {
				m = createTablePattern.matcher(indivCreateTableScript[i]);
				while (m.find()) {
					String tableName = m.group(1).toLowerCase();
					writeFile(ALTER_TABLE+ " `" + tableName + "` ADD PRIMARY KEY(`PrimaryKey`);", bw);
					writeFile(ALTER_TABLE+" `" + tableName + "` CHANGE COLUMN `PrimaryKey` `PrimaryKey` INT AUTO INCREMENT;", bw);
				}
				
				m = createTablePattern.matcher(indivCreateTableScript[i]);
				while (m.find()) {
					String tableName = m.group(1).toLowerCase();
					String tableColumns = m.group(2);
					Matcher m1 = foreignColPattern.matcher(tableColumns);
					while (m1.find()) {
						String foreignCol = m1.group();
						String foreignTable = m1.group(1).toLowerCase();
						writeFile(ALTER_TABLE+" `" + tableName + "` CHANGE COLUMN "+foreignCol+" "+foreignCol+" INT NOT NULL;", bw);
						writeFile(ALTER_TABLE+" `" + tableName + "` ADD CONSTRAINT `" + foreignTable + "_fk_" + count++ + "` FOREIGN KEY("
								+ foreignCol + ") REFERENCES `" + foreignTable + "` (`PrimaryKey`) ON DELETE CASCADE;", bw);//PrimaryKey
					}
				}
				
				m = createTablePattern.matcher(indivCreateTableScript[i]);
				while (m.find()) {
					String tableName = m.group(1).toLowerCase();
					String tableColumns = m.group(2);
					Matcher m2 = keyWordPattern.matcher(tableColumns);
					while (m2.find()) {
						String keyWordColName = m2.group(1);
						writeFile(ALTER_TABLE+" `" + tableName + "` CHANGE COLUMN `"+keyWordColName+"` `"+keyWordColName+"_"+keyWordColName+"` varchar(255) DEFAULT NULL;", bw);
					}
				}
			}
		} catch (IOException fe) {
			throw fe;
		}finally{
			try{
				if( bw != null ){
					bw.close();
				}
			}catch(IOException e){
				LOGGER.info("exception in generateAlterTableScriptForTables method.",e);
			}
		}
	}

	private StringBuilder readFile() throws IOException {

		URL filePath = GenerateAlterScriptsForUCS.class.getResource(CREATE_SCRIPT);
		BufferedReader br = new BufferedReader(new FileReader(filePath.getPath()));
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			return sb;
		} finally {
			br.close();
		}
	}
	
	private void writeFile(String content,BufferedWriter bw) throws IOException {
		try {
			bw.write(content);
			bw.newLine();
		} catch (IOException e) {
			LOGGER.info("exception in writeFile method.",e);
		}
	}
}
