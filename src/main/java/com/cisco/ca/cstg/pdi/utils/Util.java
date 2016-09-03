package com.cisco.ca.cstg.pdi.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.ca.cstg.pdi.pojos.ProjectDetails;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.CollectionType;

public final class Util implements Constants{

	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

	// Hide the construtor
	private Util() {

	}

	public static void writeFile(String licenseFileLocation,
			byte[] fileContent, String fileName) throws IOException {
		File licenseFolder = new File(licenseFileLocation);
		if (!licenseFolder.exists() || licenseFolder.isFile()) {
			boolean makedir = licenseFolder.mkdirs();
			LOGGER.info("Directory creation status :" + makedir);
		}

		FileOutputStream licenseFileOutputStream = null;
		try {
			licenseFileOutputStream = new FileOutputStream(new File(
					licenseFileLocation, fileName));
			licenseFileOutputStream.write(fileContent);
		} finally {
			if (licenseFileOutputStream != null) {
				try {
					licenseFileOutputStream.close();
				} catch (IOException exception2) {
					LOGGER.info("exception in writeFile method.",exception2);
				}
			}
		}
	}
	
	/**
	 * This method constructs the query string for tables or pojos.
	 * 
	 * @author padmkris
	 * 
	 * @param pojoName is the name of the pojo for which the query needs to be make
	 * @param fieldName is the name of attribute of pojo in where clause.
	 * @param value is the value of attribute of pojo in where clause.
	 * @return String in the format ' from pojoName where fieldName = value'
	 */
	public static String constructQueryStringForField(String pojoName, String fieldName, Object value){
		StringBuilder str = new StringBuilder("from ");
		str.append(pojoName.trim()).append(" where ").append(fieldName.trim()).append(" = ");
		if(value instanceof String){
			str.append("'");
		}
		str.append(value.toString());
		if(value instanceof String){
			str.append("'");
		}
		return str.toString();
	}

	public static void json2Excel(OutputStream output, String json,
			String[] properties, String[] columnsNames) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		Row header = sheet.createRow(0);

		for (int i = 0; i < columnsNames.length; i++) {
			String string = columnsNames[i];
			Cell cell = header.createCell(i);
			RichTextString text = new XSSFRichTextString(string);
			cell.setCellValue(text);
		}

		LOGGER.info("Writing on workbook.");
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(json);

			int i = 0;
			for (JsonNode jsonNode2 : jsonNode) {
				Row row = sheet.createRow(++i);

				for (int j = 0; j < properties.length; j++) {
					String string = properties[j];
					Cell cell = row.createCell(j);
					RichTextString text = new XSSFRichTextString(jsonNode2.get(
							string).getTextValue());
					cell.setCellValue(text);
				}
			}
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage(), e);
		}

		workbook.write(output);
		workbook.close();
		LOGGER.info("Written on workbook.");
	}

	/**
	 * This method converts the entries in the Map to json string and adds it to
	 * jsonList
	 * 
	 * @author padmkris
	 * 
	 * @param myMap
	 *            contains the entry of key value pair for json object
	 * @param jsonList
	 *            List of json string, added from myMap
	 */
	public static void convertMapToJson(Map<String, Object> myMap,
			List<Object> jsonList) {
		Writer strWriter = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(strWriter, myMap);
			String pmDataJSON = strWriter.toString();
			jsonList.add(pmDataJSON);

		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method returns a json string converted from myMap
	 * 
	 * @author padmkris
	 * 
	 * @param myMap
	 *            contains the entry of key value pair for json object
	 */
	public static String convertMapToJson(Map<String, Object> myMap) {
		Writer strWriter = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		String pmDataJSON = "";
		try {
			mapper.writeValue(strWriter, myMap);
			pmDataJSON = strWriter.toString();

		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return pmDataJSON;
	}

	/**
	 * This method returns a ProjectDetails object after populating
	 * activeProjectId
	 * 
	 * @author pavkuma2
	 * 
	 */
	public static ProjectDetails getProjectDetailsObject(String projectId) {
		ProjectDetails pd = new ProjectDetails();
		if (projectId != null) {
			pd.setId(Integer.parseInt(projectId));
		}
		return pd;
	}

	/**
	 * This method is used to validate whether the String is empty or null
	 * 
	 * returns a boolean value
	 * 
	 * @author pavkuma2
	 * 
	 * @param String
	 *            is a string to be validated for empty/null
	 */
	public static boolean isStringNotEmpty(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		return true;
	}

	/**
	 * This method converts json string to pojo list
	 * 
	 * @param jSon
	 *            JSon String which is converted to Objects list
	 * @param c
	 *            is pojo class whose Object created from json string
	 * @return list of pojo objects which converted from json string
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static List<Object> convertJsonToListOfObject(String jSon, Class c) {

		ObjectMapper mapper = new ObjectMapper();
		List<Object> list = null;

		try {
			list = mapper.readValue(jSon,
					TypeFactory.collectionType(List.class, c));

		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return list;
	}

	/**
	 * This method converts json string to pojo list and ignore unnecessary
	 * fields
	 * 
	 * @param jSon
	 *            JSon String which is converted to Objects list
	 * @param c
	 *            is pojo class whose Object created from json string
	 * @return list of pojo objects which converted from json string
	 * 
	 */

	@SuppressWarnings("rawtypes")
	public static List<Object> jsonToListWithIgnoringField(String jSon, Class c) {

		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		List<Object> list = null;
		CollectionType javaType = mapper.getTypeFactory()
				.constructCollectionType(List.class, c);

		try {
			list = mapper.readValue(jSon, javaType);

		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return list;
	}

	
	@SuppressWarnings("rawtypes")
	public static Object jsonToObjectWithIgnoringField(String jSon, Class c) {

		com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		Object obj = null;
		JavaType javaType = mapper.getTypeFactory()
				.constructType(c);

		try {
			obj = mapper.readValue(jSon, javaType);

		} catch (JsonGenerationException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return obj;
	}

	
	public static String getPdiConfDataFolderPath(ProjectDetails project) {
		StringBuilder returnPath = new StringBuilder(
				PdiConfig.getProperty(Constants.PDI_HOME));
		returnPath.append(project.getProjectName()).append(File.separator)
				.append(project.getProjectName()).append("_Data");
		return returnPath.toString();
	}

	public static String getPdiConfFolderPath(ProjectDetails project) {
		StringBuilder returnPath = new StringBuilder(
				PdiConfig.getProperty(Constants.PDI_HOME));
		returnPath.append(project.getProjectName()).append(File.separator);
		return returnPath.toString();
	}

	public static String getPdiConfTemplateFolderPath(ProjectDetails project) {
		String returnPath = getPdiConfFolderPath(project)
				.concat(project.getProjectName()).concat("_Template")
				.concat(File.separator);
		return returnPath;
	}

	public static void deleteFolder(String returnPath) {
		try {
			File f = new File(returnPath);
			FileUtils.deleteDirectory(f);
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("Deleted un-zipped folder {}", returnPath);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> convertObjectToMap(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(obj, Map.class);
	}

	public static List<Map<String, Object>> convertObjectListToMapList(
			List<Object> recordsList) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(recordsList,
				TypeFactory.collectionType(List.class, Map.class));
	}
	
	public static String getCurrentDateTime(){
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyMMddhhmm");
		return df.format(date.getTime());
	}
	
	public static JsonNode getJsonNodeByName(String jsonObj, String nodeName) throws IOException,JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();		
		JsonNode readTree = mapper.readTree(jsonObj);		
		return readTree.get(nodeName);
	}
	
	public static JsonNode getJsonNodeByJson(String jsonObj) throws IOException,JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();		
		return mapper.readTree(jsonObj);
	}
	
	public static boolean jsonNodeNotNull(JsonNode node){
		if(node != null && node.size() >0) {
			return true;
		}
		return false;
	}
	
	public static boolean listNotNull(List<? extends Object> list){
		if(list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}
	/**
	 * This method validates an object and return the id 
	 * @param obj
	 * @return id, if id not available then <blank> 
	 */
	
	public static Object nullValidationAndReturnId(Object obj) {
		if(obj != null) {
			try{
				Class<?> childClass = Class.forName(obj.getClass().getName());
				Method[] childMethod = childClass.getDeclaredMethods();
		
				for(Method childM : childMethod) {
					if("getid".equalsIgnoreCase(childM.getName())){
						return childM.invoke(obj);
					}
				}
			} catch(Exception e){
				LOGGER.error("Error caught while fetching the id of object.",e);
			}
		}
		return null;
	}
	
	/**
	 * This method is used to create folder for specified path
	 * @param path
	 */
	public static void createFolder(String path) {
		File dir = new File(path);
		if (!dir.exists() && !dir.mkdirs()) {
				LOGGER.error("Error in creating the folder for path" +path);
		}
	}
	
	/**
	 * This method is used to download files from specified path
	 * @param response
	 * @param archiveFile
	 */
	public static void downloadArchiveFile(HttpServletResponse response,
			File archiveFile) {
		if (archiveFile.isFile()) {
			response.reset();
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ archiveFile.getName() + "\"");
			FileInputStream is = null;
			ServletOutputStream op = null;
			try {
				op = response.getOutputStream();
				double dLength = archiveFile.length();
				int iLength = 0;
				int num_read = 0;

				if (dLength >= Integer.MIN_VALUE
						&& dLength <= Integer.MAX_VALUE) {
					iLength = (int) dLength;
				}
				byte[] arBytes = new byte[iLength];
				is = new FileInputStream(archiveFile);

				while (num_read < iLength) {
					int count = is.read(arBytes, num_read, iLength - num_read);
					if (count < 0) {
						throw new IOException("end of stream reached");
					}
					num_read += count;
				}
				op.write(arBytes);
				op.flush();
			} catch (IOException e) {
				LOGGER.error(e.getMessage(), e);
			} finally {
				if (null != is) {
					try {
						is.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
				if (null != op) {
					try {
						op.close();
					} catch (IOException e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
		}
	}
	
	public static String getLogoutUrl(HttpServletRequest request, String logoutUrl) {
		if (null != request.getRequestURL()) {
			String requestURL = request.getRequestURL().toString();
			
			if (requestURL.toLowerCase().contains(CONTAINDEV)) {
				logoutUrl = PdiConfig.getProperty(PDI_DEV_LOGOUT);
			} else if (requestURL.toLowerCase().contains(CONTAINSTG)) {
				logoutUrl = PdiConfig.getProperty(PDI_STAGE_LOGOUT);
			} else if (requestURL.toLowerCase().contains(CONTAINPROD)) {
				logoutUrl = PdiConfig.getProperty(PDI_PROD_LOGOUT);
			}else {
				LOGGER.info("non lae logout");
				logoutUrl = "j_spring_security_logout";
			}
		}
		return logoutUrl;
	}
	
	public static Map<String, String> getVisioServerUrl(
			HttpServletRequest request) {
		Map<String, String> visioUrlMap = new HashMap<String, String>();
		if (request.getRequestURL() != null) {
			String requestURL = request.getRequestURL().toString();
			if (requestURL.contains(CONTAINPROD)) {
				LOGGER.info("Request for visio prod url....");
				visioUrlMap.put(VISIOPRIMARYURL,
						PdiConfig.getProperty(PDI_VISIO_PRI_URL_PROD));
				visioUrlMap.put(VISIOSECONDARYURL,
						PdiConfig.getProperty(PDI_VISIO_SEC_URL_PROD));
				visioUrlMap.put(VISIOHANGRESOLVERPRIMARYURL,
						PdiConfig.getProperty(PDI_VISIO_HR_PRI_URL_PROD));
				visioUrlMap.put(VISIOHANGRESOLVERSECONDARYURL,
						PdiConfig.getProperty(PDI_VISIO_HR_SEC_URL_PROD));
			} else {
				LOGGER.info("Request for visio dev/stage/local url....");
				visioUrlMap.put(VISIOPRIMARYURL,
						PdiConfig.getProperty(PDI_VISIO_PRI_URL_DEVSTAGE));
				visioUrlMap.put(VISIOSECONDARYURL,
						PdiConfig.getProperty(PDI_VISIO_SEC_URL_DEVSTAGE));
				visioUrlMap.put(VISIOHANGRESOLVERPRIMARYURL,
						PdiConfig.getProperty(PDI_VISIO_HR_PRI_URL_DEVSTAGE));
				visioUrlMap.put(VISIOHANGRESOLVERSECONDARYURL,
						PdiConfig.getProperty(PDI_VISIO_HR_SEC_URL_DEVSTAGE));
			}
		}
		return visioUrlMap;
	}
}
