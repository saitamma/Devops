package com.cisco.ca.cstg.pdi.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author avinasin
 * 
 */
@Component
public class HttpConnectionHelper {

	@Autowired
	@Value("${pdi.docOutputLocation}")
	private String outputLocation;

	@Autowired
	@Value("${pdi.topologyOutputFilename}")
	private String fileName;

	@Autowired
	@Value("${pdi.timeoutRetryLimt}")
	private String maxRetryCount;

	private int connectionRetryCount = 0;
	private int requestRetryCount = 0;
	private HttpURLConnection connection;
	public static final String DESIRED_CONTENT_TYPE = "application/zip";
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HttpConnectionHelper.class);

	public HttpURLConnection getConnection(String targetUrl, int timeout,
			String method, String content_type) {
		try {
			URL urlString = new URL(targetUrl);
			connection = (HttpURLConnection) urlString.openConnection();
			/* CONNECTION PROPERTIES */
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout); // same as connection timeout 60000 ms i.e, 1 Minute
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-Type", content_type);
			/* CONNECTION PROPERTIES */
			connection.connect();
		} catch (MalformedURLException e) {
			LOGGER.error("Exception", e);
			connection = null;
		} catch (SocketTimeoutException e) {
			LOGGER.error("Exception", e);
			LOGGER.error(Constants.TIMEOUT_ERR_MSG);
			LOGGER.info("**** Curreent time out ***** :"
					+ connection.getConnectTimeout());
			LOGGER.info("**** Increased time out ***** :"
					+ connection.getConnectTimeout() * 4);
			connection.setConnectTimeout(connection.getConnectTimeout() * 4);
			if (connectionRetryCount < Integer.parseInt(maxRetryCount)) {
				connectionRetryCount++;
				getConnection(targetUrl, connection.getConnectTimeout(),
						method, content_type);
			} else {
				LOGGER.error(Constants.MAX_RETRY_ERR_MSG + connectionRetryCount);
			}
		} catch (IOException e) {
			LOGGER.error("Exception", e);
			connection = null;
		} catch (Exception e) {
			LOGGER.error("Exception", e);
			connection = null;
		} finally {
			connectionRetryCount = 0;
		}
		return connection;
	}

	public int request(HttpURLConnection connection, String payload,
			long siteid, String hangresolveurl) {
		DataOutputStream outputstream = null;
		int responsecode = 0;
		try {
			outputstream = new DataOutputStream(connection.getOutputStream());
			outputstream.writeBytes(payload);
			outputstream.flush();
			outputstream.close();
			responsecode = connection.getResponseCode();
			if (responsecode != HttpURLConnection.HTTP_OK) {
				System.out.println(Constants.RESPONSE_ERR_MSG
						+ connection.getResponseCode() + " "
						+ connection.getResponseMessage());
				LOGGER.error(Constants.RESPONSE_ERR_MSG
						+ connection.getResponseCode() + " "
						+ connection.getResponseMessage());
			} else {
				if (!connection.getContentType().equals(DESIRED_CONTENT_TYPE)) {
					LOGGER.error(Constants.CONTENT_ERR_MSG
							+ IOUtils.toString(connection.getInputStream()));

				} else {
					writeResponse(connection, siteid);
				}
			}
		} catch (SocketTimeoutException e) {
			LOGGER.error("Exception occured in request method,", e);
			clearPreviousRequest(hangresolveurl);
			LOGGER.info("**** Curreent time out ***** :"
					+ connection.getConnectTimeout());
			LOGGER.info("**** Increased time out ***** :"
					+ connection.getConnectTimeout() * 4);
			connection.setConnectTimeout(connection.getConnectTimeout() * 4);
			if (requestRetryCount < Integer.parseInt(maxRetryCount)) {
				requestRetryCount++;
				request(connection, payload, siteid, hangresolveurl);
			} else {
				LOGGER.error(Constants.MAX_RETRY_ERR_MSG + requestRetryCount);
			}
		} catch (Exception e) {
			LOGGER.error("Exception occured in request method,", e);
		} finally {
			requestRetryCount = 0;
			if (outputstream != null && connection != null) {
				try {
					outputstream.close();
					connection.disconnect();
				} catch (IOException e) {
					LOGGER.error(
							"Exception occured in request method during resource cleanup,",
							e);
				}
			}
		}
		return responsecode;
	}

	private void clearPreviousRequest(String url) {
		try {
			new URL(url).openConnection().getContent();
		} catch (MalformedURLException e) {
			LOGGER.error("Exception in clearPreviousRequest method,", e);
		} catch (IOException e) {
			LOGGER.error("Exception in clearPreviousRequest method,", e);
		}
	}

	public void writeResponse(HttpURLConnection connection, long siteid) {
		InputStream reader = null;
		try {
			String filePath = outputLocation + siteid + File.separator
					+ fileName;
			reader = connection.getInputStream();
			try (FileOutputStream writer = new FileOutputStream(filePath)) {
				IOUtils.write(IOUtils.toByteArray(reader), writer);
				if (new File(filePath).exists()) {
					LOGGER.info(Constants.VISIO_SUCCESS_MSG);
				} else {
					LOGGER.error(Constants.VISIO_FAILED_MSG);
				}					
			}

		} catch (Exception e) {
			LOGGER.error("Exception in writeResponse method,", e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					LOGGER.error(
							"Exception in writeResponse method during resource cleanup,",
							e);
				}
			}
		}
	}
}
