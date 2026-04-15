
package telco.telcojob1_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.MDM;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.SQLike;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: TelcoJob1 Purpose: <br>
 * Description: <br>
 * 
 * @author izzati.zaki@quandatics.com
 * @version 8.0.1.20250319_1318-patch
 * @status
 */
public class TelcoJob1 implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "TelcoJob1.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(TelcoJob1.class);

	static {
		if (isSLF4JBridgeHandlerPresent()) {
			useSLF4JBridgeHandler();
		}
	}

	protected static void useSLF4JBridgeHandler() {
		try {
			java.lang.StringBuilder config = new java.lang.StringBuilder();
			config.append("handlers = org.slf4j.bridge.SLF4JBridgeHandler\n"); // jul-to-slf4j
			config.append(".level = FINEST");

			try (java.io.ByteArrayInputStream is = new java.io.ByteArrayInputStream(config.toString().getBytes())) {
				java.util.logging.LogManager.getLogManager().readConfiguration(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static boolean isSLF4JBridgeHandlerPresent() {
		boolean isSLF4JBridgeHandlerPresent = false;
		try {
			Class.forName("org.slf4j.bridge.SLF4JBridgeHandler");
			isSLF4JBridgeHandlerPresent = true;
		} catch (ClassNotFoundException e) {
		}
		return isSLF4JBridgeHandlerPresent;
	}

	protected static boolean isCBPClientPresent() {
		boolean isCBPClientPresent = false;
		try {
			Class.forName("org.talend.metrics.CBPClient");
			isCBPClientPresent = true;
		} catch (java.lang.ClassNotFoundException e) {
		}
		return isCBPClientPresent;
	}

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	public static String taskExecutionId = null;

	public static String jobExecutionId = java.util.UUID.randomUUID().toString();;

	private final static boolean isCBPClientPresent = isCBPClientPresent();

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (targetYear != null) {

				this.setProperty("targetYear", targetYear.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String targetYear;

		public String getTargetYear() {
			return this.targetYear;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	protected java.util.Map<String, String> defaultProperties = new java.util.HashMap<String, String>();
	protected java.util.Map<String, String> additionalProperties = new java.util.HashMap<String, String>();

	public java.util.Map<String, String> getDefaultProperties() {
		return this.defaultProperties;
	}

	public java.util.Map<String, String> getAdditionalProperties() {
		return this.additionalProperties;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "TelcoJob1";
	private final String projectName = "TELCO";
	public Integer errorCode = null;
	private String currentComponent = "";
	public static boolean isStandaloneMS = Boolean.valueOf("false");

	private void s(final String component) {
		try {
			org.talend.metrics.DataReadTracker.setCurrentComponent(jobName, component);
		} catch (Exception | NoClassDefFoundError e) {
			// ignore
		}
	}

	private void mdc(final String subJobName, final String subJobPidPrefix) {
		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", subJobName);
		org.slf4j.MDC.put("_subJobPid", subJobPidPrefix + subJobPidCounter.getAndIncrement());
	}

	private void sh(final String componentId) {
		ok_Hash.put(componentId, false);
		start_Hash.put(componentId, System.currentTimeMillis());
	}

	{
		s("none");
	}

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName,
			"_BJyGECDxEfGaG-ujHcrDsQ", "0.1");
	private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
				}
			}
			if (!(e instanceof TalendException)) {
				TelcoJob1.this.exception = e;
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(TelcoJob1.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						if (enableLogStash) {
							talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
							talendJobLogProcess(globalMap);
						}
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_18_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_14_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_22_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tPrejob_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tPrejob_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tSetGlobalVar_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tSetGlobalVar_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row12_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_1_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_1_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_1_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tPrejob_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tSetGlobalVar_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row8Struct implements routines.system.IPersistableRow<row8Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return null;
		}

		public Integer NamePrecision() {
			return null;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Name=" + Name);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row6Struct implements routines.system.IPersistableRow<row6Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return null;
		}

		public Integer NamePrecision() {
			return null;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Name=" + Name);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row9Struct implements routines.system.IPersistableRow<row9Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return null;
		}

		public Integer CustomerIdPrecision() {
			return null;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return 11;
		}

		public Integer NamePrecision() {
			return 0;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "dd-MM-yyyy";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		public String HashCode;

		public String getHashCode() {
			return this.HashCode;
		}

		public Boolean HashCodeIsNullable() {
			return true;
		}

		public Boolean HashCodeIsKey() {
			return false;
		}

		public Integer HashCodeLength() {
			return null;
		}

		public Integer HashCodePrecision() {
			return null;
		}

		public String HashCodeDefault() {

			return null;

		}

		public String HashCodeComment() {

			return "";

		}

		public String HashCodePattern() {

			return "";

		}

		public String HashCodeOriginalDbColumnName() {

			return "HashCode";

		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public Boolean genderIsNullable() {
			return true;
		}

		public Boolean genderIsKey() {
			return false;
		}

		public Integer genderLength() {
			return null;
		}

		public Integer genderPrecision() {
			return null;
		}

		public String genderDefault() {

			return null;

		}

		public String genderComment() {

			return "";

		}

		public String genderPattern() {

			return "";

		}

		public String genderOriginalDbColumnName() {

			return "gender";

		}

		public String email;

		public String getEmail() {
			return this.email;
		}

		public Boolean emailIsNullable() {
			return true;
		}

		public Boolean emailIsKey() {
			return false;
		}

		public Integer emailLength() {
			return null;
		}

		public Integer emailPrecision() {
			return null;
		}

		public String emailDefault() {

			return null;

		}

		public String emailComment() {

			return "";

		}

		public String emailPattern() {

			return "";

		}

		public String emailOriginalDbColumnName() {

			return "email";

		}

		public String Birthday;

		public String getBirthday() {
			return this.Birthday;
		}

		public Boolean BirthdayIsNullable() {
			return true;
		}

		public Boolean BirthdayIsKey() {
			return false;
		}

		public Integer BirthdayLength() {
			return null;
		}

		public Integer BirthdayPrecision() {
			return null;
		}

		public String BirthdayDefault() {

			return null;

		}

		public String BirthdayComment() {

			return "";

		}

		public String BirthdayPattern() {

			return "dd-MM-yyyy";

		}

		public String BirthdayOriginalDbColumnName() {

			return "Birthday";

		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public Boolean AgeIsNullable() {
			return true;
		}

		public Boolean AgeIsKey() {
			return false;
		}

		public Integer AgeLength() {
			return null;
		}

		public Integer AgePrecision() {
			return null;
		}

		public String AgeDefault() {

			return null;

		}

		public String AgeComment() {

			return "";

		}

		public String AgePattern() {

			return "";

		}

		public String AgeOriginalDbColumnName() {

			return "Age";

		}

		public Integer PostalCode;

		public Integer getPostalCode() {
			return this.PostalCode;
		}

		public Boolean PostalCodeIsNullable() {
			return true;
		}

		public Boolean PostalCodeIsKey() {
			return false;
		}

		public Integer PostalCodeLength() {
			return null;
		}

		public Integer PostalCodePrecision() {
			return null;
		}

		public String PostalCodeDefault() {

			return null;

		}

		public String PostalCodeComment() {

			return "";

		}

		public String PostalCodePattern() {

			return "";

		}

		public String PostalCodeOriginalDbColumnName() {

			return "PostalCode";

		}

		public String State;

		public String getState() {
			return this.State;
		}

		public Boolean StateIsNullable() {
			return true;
		}

		public Boolean StateIsKey() {
			return false;
		}

		public Integer StateLength() {
			return null;
		}

		public Integer StatePrecision() {
			return null;
		}

		public String StateDefault() {

			return null;

		}

		public String StateComment() {

			return "";

		}

		public String StatePattern() {

			return "";

		}

		public String StateOriginalDbColumnName() {

			return "State";

		}

		public String UserStatus;

		public String getUserStatus() {
			return this.UserStatus;
		}

		public Boolean UserStatusIsNullable() {
			return true;
		}

		public Boolean UserStatusIsKey() {
			return false;
		}

		public Integer UserStatusLength() {
			return null;
		}

		public Integer UserStatusPrecision() {
			return null;
		}

		public String UserStatusDefault() {

			return null;

		}

		public String UserStatusComment() {

			return "";

		}

		public String UserStatusPattern() {

			return "";

		}

		public String UserStatusOriginalDbColumnName() {

			return "UserStatus";

		}

		public String ChurnDate;

		public String getChurnDate() {
			return this.ChurnDate;
		}

		public Boolean ChurnDateIsNullable() {
			return true;
		}

		public Boolean ChurnDateIsKey() {
			return false;
		}

		public Integer ChurnDateLength() {
			return null;
		}

		public Integer ChurnDatePrecision() {
			return null;
		}

		public String ChurnDateDefault() {

			return null;

		}

		public String ChurnDateComment() {

			return "";

		}

		public String ChurnDatePattern() {

			return "";

		}

		public String ChurnDateOriginalDbColumnName() {

			return "ChurnDate";

		}

		public Double TotalTransactionValue;

		public Double getTotalTransactionValue() {
			return this.TotalTransactionValue;
		}

		public Boolean TotalTransactionValueIsNullable() {
			return true;
		}

		public Boolean TotalTransactionValueIsKey() {
			return false;
		}

		public Integer TotalTransactionValueLength() {
			return null;
		}

		public Integer TotalTransactionValuePrecision() {
			return null;
		}

		public String TotalTransactionValueDefault() {

			return null;

		}

		public String TotalTransactionValueComment() {

			return "";

		}

		public String TotalTransactionValuePattern() {

			return "";

		}

		public String TotalTransactionValueOriginalDbColumnName() {

			return "TotalTransactionValue";

		}

		public Integer TransactionYear;

		public Integer getTransactionYear() {
			return this.TransactionYear;
		}

		public Boolean TransactionYearIsNullable() {
			return true;
		}

		public Boolean TransactionYearIsKey() {
			return false;
		}

		public Integer TransactionYearLength() {
			return null;
		}

		public Integer TransactionYearPrecision() {
			return null;
		}

		public String TransactionYearDefault() {

			return null;

		}

		public String TransactionYearComment() {

			return "";

		}

		public String TransactionYearPattern() {

			return "";

		}

		public String TransactionYearOriginalDbColumnName() {

			return "TransactionYear";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.HashCode = readString(dis);

					this.gender = readString(dis);

					this.email = readString(dis);

					this.Birthday = readString(dis);

					this.Age = readInteger(dis);

					this.PostalCode = readInteger(dis);

					this.State = readString(dis);

					this.UserStatus = readString(dis);

					this.ChurnDate = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransactionValue = null;
					} else {
						this.TotalTransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.HashCode = readString(dis);

					this.gender = readString(dis);

					this.email = readString(dis);

					this.Birthday = readString(dis);

					this.Age = readInteger(dis);

					this.PostalCode = readInteger(dis);

					this.State = readString(dis);

					this.UserStatus = readString(dis);

					this.ChurnDate = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransactionValue = null;
					} else {
						this.TotalTransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.email, dos);

				// String

				writeString(this.Birthday, dos);

				// Integer

				writeInteger(this.Age, dos);

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.State, dos);

				// String

				writeString(this.UserStatus, dos);

				// String

				writeString(this.ChurnDate, dos);

				// Double

				if (this.TotalTransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.email, dos);

				// String

				writeString(this.Birthday, dos);

				// Integer

				writeInteger(this.Age, dos);

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.State, dos);

				// String

				writeString(this.UserStatus, dos);

				// String

				writeString(this.ChurnDate, dos);

				// Double

				if (this.TotalTransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",Name=" + Name);
			sb.append(",HashCode=" + HashCode);
			sb.append(",gender=" + gender);
			sb.append(",email=" + email);
			sb.append(",Birthday=" + Birthday);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",PostalCode=" + String.valueOf(PostalCode));
			sb.append(",State=" + State);
			sb.append(",UserStatus=" + UserStatus);
			sb.append(",ChurnDate=" + ChurnDate);
			sb.append(",TotalTransactionValue=" + String.valueOf(TotalTransactionValue));
			sb.append(",TransactionYear=" + String.valueOf(TransactionYear));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			if (HashCode == null) {
				sb.append("<null>");
			} else {
				sb.append(HashCode);
			}

			sb.append("|");

			if (gender == null) {
				sb.append("<null>");
			} else {
				sb.append(gender);
			}

			sb.append("|");

			if (email == null) {
				sb.append("<null>");
			} else {
				sb.append(email);
			}

			sb.append("|");

			if (Birthday == null) {
				sb.append("<null>");
			} else {
				sb.append(Birthday);
			}

			sb.append("|");

			if (Age == null) {
				sb.append("<null>");
			} else {
				sb.append(Age);
			}

			sb.append("|");

			if (PostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(PostalCode);
			}

			sb.append("|");

			if (State == null) {
				sb.append("<null>");
			} else {
				sb.append(State);
			}

			sb.append("|");

			if (UserStatus == null) {
				sb.append("<null>");
			} else {
				sb.append(UserStatus);
			}

			sb.append("|");

			if (ChurnDate == null) {
				sb.append("<null>");
			} else {
				sb.append(ChurnDate);
			}

			sb.append("|");

			if (TotalTransactionValue == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalTransactionValue);
			}

			sb.append("|");

			if (TransactionYear == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionYear);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return null;
		}

		public Integer CustomerIdPrecision() {
			return null;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return 11;
		}

		public Integer NamePrecision() {
			return 0;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "dd-MM-yyyy";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		public String HashCode;

		public String getHashCode() {
			return this.HashCode;
		}

		public Boolean HashCodeIsNullable() {
			return true;
		}

		public Boolean HashCodeIsKey() {
			return false;
		}

		public Integer HashCodeLength() {
			return null;
		}

		public Integer HashCodePrecision() {
			return null;
		}

		public String HashCodeDefault() {

			return null;

		}

		public String HashCodeComment() {

			return "";

		}

		public String HashCodePattern() {

			return "";

		}

		public String HashCodeOriginalDbColumnName() {

			return "HashCode";

		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public Boolean genderIsNullable() {
			return true;
		}

		public Boolean genderIsKey() {
			return false;
		}

		public Integer genderLength() {
			return null;
		}

		public Integer genderPrecision() {
			return null;
		}

		public String genderDefault() {

			return null;

		}

		public String genderComment() {

			return "";

		}

		public String genderPattern() {

			return "";

		}

		public String genderOriginalDbColumnName() {

			return "gender";

		}

		public String email;

		public String getEmail() {
			return this.email;
		}

		public Boolean emailIsNullable() {
			return true;
		}

		public Boolean emailIsKey() {
			return false;
		}

		public Integer emailLength() {
			return null;
		}

		public Integer emailPrecision() {
			return null;
		}

		public String emailDefault() {

			return null;

		}

		public String emailComment() {

			return "";

		}

		public String emailPattern() {

			return "";

		}

		public String emailOriginalDbColumnName() {

			return "email";

		}

		public String Birthday;

		public String getBirthday() {
			return this.Birthday;
		}

		public Boolean BirthdayIsNullable() {
			return true;
		}

		public Boolean BirthdayIsKey() {
			return false;
		}

		public Integer BirthdayLength() {
			return null;
		}

		public Integer BirthdayPrecision() {
			return null;
		}

		public String BirthdayDefault() {

			return null;

		}

		public String BirthdayComment() {

			return "";

		}

		public String BirthdayPattern() {

			return "dd-MM-yyyy";

		}

		public String BirthdayOriginalDbColumnName() {

			return "Birthday";

		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public Boolean AgeIsNullable() {
			return true;
		}

		public Boolean AgeIsKey() {
			return false;
		}

		public Integer AgeLength() {
			return null;
		}

		public Integer AgePrecision() {
			return null;
		}

		public String AgeDefault() {

			return null;

		}

		public String AgeComment() {

			return "";

		}

		public String AgePattern() {

			return "";

		}

		public String AgeOriginalDbColumnName() {

			return "Age";

		}

		public Integer PostalCode;

		public Integer getPostalCode() {
			return this.PostalCode;
		}

		public Boolean PostalCodeIsNullable() {
			return true;
		}

		public Boolean PostalCodeIsKey() {
			return false;
		}

		public Integer PostalCodeLength() {
			return null;
		}

		public Integer PostalCodePrecision() {
			return null;
		}

		public String PostalCodeDefault() {

			return null;

		}

		public String PostalCodeComment() {

			return "";

		}

		public String PostalCodePattern() {

			return "";

		}

		public String PostalCodeOriginalDbColumnName() {

			return "PostalCode";

		}

		public String State;

		public String getState() {
			return this.State;
		}

		public Boolean StateIsNullable() {
			return true;
		}

		public Boolean StateIsKey() {
			return false;
		}

		public Integer StateLength() {
			return null;
		}

		public Integer StatePrecision() {
			return null;
		}

		public String StateDefault() {

			return null;

		}

		public String StateComment() {

			return "";

		}

		public String StatePattern() {

			return "";

		}

		public String StateOriginalDbColumnName() {

			return "State";

		}

		public String UserStatus;

		public String getUserStatus() {
			return this.UserStatus;
		}

		public Boolean UserStatusIsNullable() {
			return true;
		}

		public Boolean UserStatusIsKey() {
			return false;
		}

		public Integer UserStatusLength() {
			return null;
		}

		public Integer UserStatusPrecision() {
			return null;
		}

		public String UserStatusDefault() {

			return null;

		}

		public String UserStatusComment() {

			return "";

		}

		public String UserStatusPattern() {

			return "";

		}

		public String UserStatusOriginalDbColumnName() {

			return "UserStatus";

		}

		public String ChurnDate;

		public String getChurnDate() {
			return this.ChurnDate;
		}

		public Boolean ChurnDateIsNullable() {
			return true;
		}

		public Boolean ChurnDateIsKey() {
			return false;
		}

		public Integer ChurnDateLength() {
			return null;
		}

		public Integer ChurnDatePrecision() {
			return null;
		}

		public String ChurnDateDefault() {

			return null;

		}

		public String ChurnDateComment() {

			return "";

		}

		public String ChurnDatePattern() {

			return "";

		}

		public String ChurnDateOriginalDbColumnName() {

			return "ChurnDate";

		}

		public Double TotalTransactionValue;

		public Double getTotalTransactionValue() {
			return this.TotalTransactionValue;
		}

		public Boolean TotalTransactionValueIsNullable() {
			return true;
		}

		public Boolean TotalTransactionValueIsKey() {
			return false;
		}

		public Integer TotalTransactionValueLength() {
			return null;
		}

		public Integer TotalTransactionValuePrecision() {
			return null;
		}

		public String TotalTransactionValueDefault() {

			return null;

		}

		public String TotalTransactionValueComment() {

			return "";

		}

		public String TotalTransactionValuePattern() {

			return "";

		}

		public String TotalTransactionValueOriginalDbColumnName() {

			return "TotalTransactionValue";

		}

		public Integer TransactionYear;

		public Integer getTransactionYear() {
			return this.TransactionYear;
		}

		public Boolean TransactionYearIsNullable() {
			return true;
		}

		public Boolean TransactionYearIsKey() {
			return false;
		}

		public Integer TransactionYearLength() {
			return null;
		}

		public Integer TransactionYearPrecision() {
			return null;
		}

		public String TransactionYearDefault() {

			return null;

		}

		public String TransactionYearComment() {

			return "";

		}

		public String TransactionYearPattern() {

			return "";

		}

		public String TransactionYearOriginalDbColumnName() {

			return "TransactionYear";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.HashCode = readString(dis);

					this.gender = readString(dis);

					this.email = readString(dis);

					this.Birthday = readString(dis);

					this.Age = readInteger(dis);

					this.PostalCode = readInteger(dis);

					this.State = readString(dis);

					this.UserStatus = readString(dis);

					this.ChurnDate = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransactionValue = null;
					} else {
						this.TotalTransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.HashCode = readString(dis);

					this.gender = readString(dis);

					this.email = readString(dis);

					this.Birthday = readString(dis);

					this.Age = readInteger(dis);

					this.PostalCode = readInteger(dis);

					this.State = readString(dis);

					this.UserStatus = readString(dis);

					this.ChurnDate = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransactionValue = null;
					} else {
						this.TotalTransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.email, dos);

				// String

				writeString(this.Birthday, dos);

				// Integer

				writeInteger(this.Age, dos);

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.State, dos);

				// String

				writeString(this.UserStatus, dos);

				// String

				writeString(this.ChurnDate, dos);

				// Double

				if (this.TotalTransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.email, dos);

				// String

				writeString(this.Birthday, dos);

				// Integer

				writeInteger(this.Age, dos);

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.State, dos);

				// String

				writeString(this.UserStatus, dos);

				// String

				writeString(this.ChurnDate, dos);

				// Double

				if (this.TotalTransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",Name=" + Name);
			sb.append(",HashCode=" + HashCode);
			sb.append(",gender=" + gender);
			sb.append(",email=" + email);
			sb.append(",Birthday=" + Birthday);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",PostalCode=" + String.valueOf(PostalCode));
			sb.append(",State=" + State);
			sb.append(",UserStatus=" + UserStatus);
			sb.append(",ChurnDate=" + ChurnDate);
			sb.append(",TotalTransactionValue=" + String.valueOf(TotalTransactionValue));
			sb.append(",TransactionYear=" + String.valueOf(TransactionYear));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			if (HashCode == null) {
				sb.append("<null>");
			} else {
				sb.append(HashCode);
			}

			sb.append("|");

			if (gender == null) {
				sb.append("<null>");
			} else {
				sb.append(gender);
			}

			sb.append("|");

			if (email == null) {
				sb.append("<null>");
			} else {
				sb.append(email);
			}

			sb.append("|");

			if (Birthday == null) {
				sb.append("<null>");
			} else {
				sb.append(Birthday);
			}

			sb.append("|");

			if (Age == null) {
				sb.append("<null>");
			} else {
				sb.append(Age);
			}

			sb.append("|");

			if (PostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(PostalCode);
			}

			sb.append("|");

			if (State == null) {
				sb.append("<null>");
			} else {
				sb.append(State);
			}

			sb.append("|");

			if (UserStatus == null) {
				sb.append("<null>");
			} else {
				sb.append(UserStatus);
			}

			sb.append("|");

			if (ChurnDate == null) {
				sb.append("<null>");
			} else {
				sb.append(ChurnDate);
			}

			sb.append("|");

			if (TotalTransactionValue == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalTransactionValue);
			}

			sb.append("|");

			if (TransactionYear == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionYear);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return null;
		}

		public Integer CustomerIdPrecision() {
			return null;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return 11;
		}

		public Integer NamePrecision() {
			return 0;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "dd-MM-yyyy";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		public String HashCode;

		public String getHashCode() {
			return this.HashCode;
		}

		public Boolean HashCodeIsNullable() {
			return true;
		}

		public Boolean HashCodeIsKey() {
			return false;
		}

		public Integer HashCodeLength() {
			return null;
		}

		public Integer HashCodePrecision() {
			return null;
		}

		public String HashCodeDefault() {

			return null;

		}

		public String HashCodeComment() {

			return "";

		}

		public String HashCodePattern() {

			return "";

		}

		public String HashCodeOriginalDbColumnName() {

			return "HashCode";

		}

		public String gender;

		public String getGender() {
			return this.gender;
		}

		public Boolean genderIsNullable() {
			return true;
		}

		public Boolean genderIsKey() {
			return false;
		}

		public Integer genderLength() {
			return null;
		}

		public Integer genderPrecision() {
			return null;
		}

		public String genderDefault() {

			return null;

		}

		public String genderComment() {

			return "";

		}

		public String genderPattern() {

			return "";

		}

		public String genderOriginalDbColumnName() {

			return "gender";

		}

		public String email;

		public String getEmail() {
			return this.email;
		}

		public Boolean emailIsNullable() {
			return true;
		}

		public Boolean emailIsKey() {
			return false;
		}

		public Integer emailLength() {
			return null;
		}

		public Integer emailPrecision() {
			return null;
		}

		public String emailDefault() {

			return null;

		}

		public String emailComment() {

			return "";

		}

		public String emailPattern() {

			return "";

		}

		public String emailOriginalDbColumnName() {

			return "email";

		}

		public String Birthday;

		public String getBirthday() {
			return this.Birthday;
		}

		public Boolean BirthdayIsNullable() {
			return true;
		}

		public Boolean BirthdayIsKey() {
			return false;
		}

		public Integer BirthdayLength() {
			return null;
		}

		public Integer BirthdayPrecision() {
			return null;
		}

		public String BirthdayDefault() {

			return null;

		}

		public String BirthdayComment() {

			return "";

		}

		public String BirthdayPattern() {

			return "dd-MM-yyyy";

		}

		public String BirthdayOriginalDbColumnName() {

			return "Birthday";

		}

		public Integer Age;

		public Integer getAge() {
			return this.Age;
		}

		public Boolean AgeIsNullable() {
			return true;
		}

		public Boolean AgeIsKey() {
			return false;
		}

		public Integer AgeLength() {
			return null;
		}

		public Integer AgePrecision() {
			return null;
		}

		public String AgeDefault() {

			return null;

		}

		public String AgeComment() {

			return "";

		}

		public String AgePattern() {

			return "";

		}

		public String AgeOriginalDbColumnName() {

			return "Age";

		}

		public Integer PostalCode;

		public Integer getPostalCode() {
			return this.PostalCode;
		}

		public Boolean PostalCodeIsNullable() {
			return true;
		}

		public Boolean PostalCodeIsKey() {
			return false;
		}

		public Integer PostalCodeLength() {
			return null;
		}

		public Integer PostalCodePrecision() {
			return null;
		}

		public String PostalCodeDefault() {

			return null;

		}

		public String PostalCodeComment() {

			return "";

		}

		public String PostalCodePattern() {

			return "";

		}

		public String PostalCodeOriginalDbColumnName() {

			return "PostalCode";

		}

		public String State;

		public String getState() {
			return this.State;
		}

		public Boolean StateIsNullable() {
			return true;
		}

		public Boolean StateIsKey() {
			return false;
		}

		public Integer StateLength() {
			return null;
		}

		public Integer StatePrecision() {
			return null;
		}

		public String StateDefault() {

			return null;

		}

		public String StateComment() {

			return "";

		}

		public String StatePattern() {

			return "";

		}

		public String StateOriginalDbColumnName() {

			return "State";

		}

		public String UserStatus;

		public String getUserStatus() {
			return this.UserStatus;
		}

		public Boolean UserStatusIsNullable() {
			return true;
		}

		public Boolean UserStatusIsKey() {
			return false;
		}

		public Integer UserStatusLength() {
			return null;
		}

		public Integer UserStatusPrecision() {
			return null;
		}

		public String UserStatusDefault() {

			return null;

		}

		public String UserStatusComment() {

			return "";

		}

		public String UserStatusPattern() {

			return "";

		}

		public String UserStatusOriginalDbColumnName() {

			return "UserStatus";

		}

		public String ChurnDate;

		public String getChurnDate() {
			return this.ChurnDate;
		}

		public Boolean ChurnDateIsNullable() {
			return true;
		}

		public Boolean ChurnDateIsKey() {
			return false;
		}

		public Integer ChurnDateLength() {
			return null;
		}

		public Integer ChurnDatePrecision() {
			return null;
		}

		public String ChurnDateDefault() {

			return null;

		}

		public String ChurnDateComment() {

			return "";

		}

		public String ChurnDatePattern() {

			return "";

		}

		public String ChurnDateOriginalDbColumnName() {

			return "ChurnDate";

		}

		public Double TotalTransactionValue;

		public Double getTotalTransactionValue() {
			return this.TotalTransactionValue;
		}

		public Boolean TotalTransactionValueIsNullable() {
			return true;
		}

		public Boolean TotalTransactionValueIsKey() {
			return false;
		}

		public Integer TotalTransactionValueLength() {
			return null;
		}

		public Integer TotalTransactionValuePrecision() {
			return null;
		}

		public String TotalTransactionValueDefault() {

			return null;

		}

		public String TotalTransactionValueComment() {

			return "";

		}

		public String TotalTransactionValuePattern() {

			return "";

		}

		public String TotalTransactionValueOriginalDbColumnName() {

			return "TotalTransactionValue";

		}

		public Integer TransactionYear;

		public Integer getTransactionYear() {
			return this.TransactionYear;
		}

		public Boolean TransactionYearIsNullable() {
			return true;
		}

		public Boolean TransactionYearIsKey() {
			return false;
		}

		public Integer TransactionYearLength() {
			return null;
		}

		public Integer TransactionYearPrecision() {
			return null;
		}

		public String TransactionYearDefault() {

			return null;

		}

		public String TransactionYearComment() {

			return "";

		}

		public String TransactionYearPattern() {

			return "";

		}

		public String TransactionYearOriginalDbColumnName() {

			return "TransactionYear";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.HashCode = readString(dis);

					this.gender = readString(dis);

					this.email = readString(dis);

					this.Birthday = readString(dis);

					this.Age = readInteger(dis);

					this.PostalCode = readInteger(dis);

					this.State = readString(dis);

					this.UserStatus = readString(dis);

					this.ChurnDate = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransactionValue = null;
					} else {
						this.TotalTransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.HashCode = readString(dis);

					this.gender = readString(dis);

					this.email = readString(dis);

					this.Birthday = readString(dis);

					this.Age = readInteger(dis);

					this.PostalCode = readInteger(dis);

					this.State = readString(dis);

					this.UserStatus = readString(dis);

					this.ChurnDate = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransactionValue = null;
					} else {
						this.TotalTransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.email, dos);

				// String

				writeString(this.Birthday, dos);

				// Integer

				writeInteger(this.Age, dos);

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.State, dos);

				// String

				writeString(this.UserStatus, dos);

				// String

				writeString(this.ChurnDate, dos);

				// Double

				if (this.TotalTransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.gender, dos);

				// String

				writeString(this.email, dos);

				// String

				writeString(this.Birthday, dos);

				// Integer

				writeInteger(this.Age, dos);

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.State, dos);

				// String

				writeString(this.UserStatus, dos);

				// String

				writeString(this.ChurnDate, dos);

				// Double

				if (this.TotalTransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",Name=" + Name);
			sb.append(",HashCode=" + HashCode);
			sb.append(",gender=" + gender);
			sb.append(",email=" + email);
			sb.append(",Birthday=" + Birthday);
			sb.append(",Age=" + String.valueOf(Age));
			sb.append(",PostalCode=" + String.valueOf(PostalCode));
			sb.append(",State=" + State);
			sb.append(",UserStatus=" + UserStatus);
			sb.append(",ChurnDate=" + ChurnDate);
			sb.append(",TotalTransactionValue=" + String.valueOf(TotalTransactionValue));
			sb.append(",TransactionYear=" + String.valueOf(TransactionYear));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			if (HashCode == null) {
				sb.append("<null>");
			} else {
				sb.append(HashCode);
			}

			sb.append("|");

			if (gender == null) {
				sb.append("<null>");
			} else {
				sb.append(gender);
			}

			sb.append("|");

			if (email == null) {
				sb.append("<null>");
			} else {
				sb.append(email);
			}

			sb.append("|");

			if (Birthday == null) {
				sb.append("<null>");
			} else {
				sb.append(Birthday);
			}

			sb.append("|");

			if (Age == null) {
				sb.append("<null>");
			} else {
				sb.append(Age);
			}

			sb.append("|");

			if (PostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(PostalCode);
			}

			sb.append("|");

			if (State == null) {
				sb.append("<null>");
			} else {
				sb.append(State);
			}

			sb.append("|");

			if (UserStatus == null) {
				sb.append("<null>");
			} else {
				sb.append(UserStatus);
			}

			sb.append("|");

			if (ChurnDate == null) {
				sb.append("<null>");
			} else {
				sb.append(ChurnDate);
			}

			sb.append("|");

			if (TotalTransactionValue == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalTransactionValue);
			}

			sb.append("|");

			if (TransactionYear == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionYear);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(out1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class NoGenderStruct implements routines.system.IPersistableRow<NoGenderStruct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return null;
		}

		public Integer NamePrecision() {
			return null;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Name=" + Name);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(NoGenderStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class NoStateStruct implements routines.system.IPersistableRow<NoStateStruct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return null;
		}

		public Integer NamePrecision() {
			return null;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Name=" + Name);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(NoStateStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public Integer PostalCode;

		public Integer getPostalCode() {
			return this.PostalCode;
		}

		public Boolean PostalCodeIsNullable() {
			return true;
		}

		public Boolean PostalCodeIsKey() {
			return false;
		}

		public Integer PostalCodeLength() {
			return 5;
		}

		public Integer PostalCodePrecision() {
			return 0;
		}

		public String PostalCodeDefault() {

			return null;

		}

		public String PostalCodeComment() {

			return "";

		}

		public String PostalCodePattern() {

			return "dd-MM-yyyy";

		}

		public String PostalCodeOriginalDbColumnName() {

			return "PostalCode";

		}

		public String HashCode;

		public String getHashCode() {
			return this.HashCode;
		}

		public Boolean HashCodeIsNullable() {
			return true;
		}

		public Boolean HashCodeIsKey() {
			return false;
		}

		public Integer HashCodeLength() {
			return 8;
		}

		public Integer HashCodePrecision() {
			return 0;
		}

		public String HashCodeDefault() {

			return null;

		}

		public String HashCodeComment() {

			return "";

		}

		public String HashCodePattern() {

			return "dd-MM-yyyy";

		}

		public String HashCodeOriginalDbColumnName() {

			return "HashCode";

		}

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 2;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return 11;
		}

		public Integer NamePrecision() {
			return 0;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "dd-MM-yyyy";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		public Integer ID;

		public Integer getID() {
			return this.ID;
		}

		public Boolean IDIsNullable() {
			return true;
		}

		public Boolean IDIsKey() {
			return false;
		}

		public Integer IDLength() {
			return 2;
		}

		public Integer IDPrecision() {
			return 0;
		}

		public String IDDefault() {

			return null;

		}

		public String IDComment() {

			return "";

		}

		public String IDPattern() {

			return "dd-MM-yyyy";

		}

		public String IDOriginalDbColumnName() {

			return "ID";

		}

		public java.util.Date Birthday;

		public java.util.Date getBirthday() {
			return this.Birthday;
		}

		public Boolean BirthdayIsNullable() {
			return true;
		}

		public Boolean BirthdayIsKey() {
			return false;
		}

		public Integer BirthdayLength() {
			return null;
		}

		public Integer BirthdayPrecision() {
			return 0;
		}

		public String BirthdayDefault() {

			return null;

		}

		public String BirthdayComment() {

			return "";

		}

		public String BirthdayPattern() {

			return "dd-MM-yyyy";

		}

		public String BirthdayOriginalDbColumnName() {

			return "Birthday";

		}

		public String State;

		public String getState() {
			return this.State;
		}

		public Boolean StateIsNullable() {
			return true;
		}

		public Boolean StateIsKey() {
			return false;
		}

		public Integer StateLength() {
			return 2;
		}

		public Integer StatePrecision() {
			return 0;
		}

		public String StateDefault() {

			return null;

		}

		public String StateComment() {

			return "";

		}

		public String StatePattern() {

			return "dd-MM-yyyy";

		}

		public String StateOriginalDbColumnName() {

			return "State";

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.ID = readInteger(dis);

					this.Birthday = readDate(dis);

					this.State = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.ID = readInteger(dis);

					this.Birthday = readDate(dis);

					this.State = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// Integer

				writeInteger(this.ID, dos);

				// java.util.Date

				writeDate(this.Birthday, dos);

				// String

				writeString(this.State, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// Integer

				writeInteger(this.ID, dos);

				// java.util.Date

				writeDate(this.Birthday, dos);

				// String

				writeString(this.State, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PostalCode=" + String.valueOf(PostalCode));
			sb.append(",HashCode=" + HashCode);
			sb.append(",CustomerId=" + CustomerId);
			sb.append(",Name=" + Name);
			sb.append(",ID=" + String.valueOf(ID));
			sb.append(",Birthday=" + String.valueOf(Birthday));
			sb.append(",State=" + State);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(PostalCode);
			}

			sb.append("|");

			if (HashCode == null) {
				sb.append("<null>");
			} else {
				sb.append(HashCode);
			}

			sb.append("|");

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			if (ID == null) {
				sb.append("<null>");
			} else {
				sb.append(ID);
			}

			sb.append("|");

			if (Birthday == null) {
				sb.append("<null>");
			} else {
				sb.append(Birthday);
			}

			sb.append("|");

			if (State == null) {
				sb.append("<null>");
			} else {
				sb.append(State);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputExcel_1Struct
			implements routines.system.IPersistableRow<after_tFileInputExcel_1Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public Integer PostalCode;

		public Integer getPostalCode() {
			return this.PostalCode;
		}

		public Boolean PostalCodeIsNullable() {
			return true;
		}

		public Boolean PostalCodeIsKey() {
			return false;
		}

		public Integer PostalCodeLength() {
			return 5;
		}

		public Integer PostalCodePrecision() {
			return 0;
		}

		public String PostalCodeDefault() {

			return null;

		}

		public String PostalCodeComment() {

			return "";

		}

		public String PostalCodePattern() {

			return "dd-MM-yyyy";

		}

		public String PostalCodeOriginalDbColumnName() {

			return "PostalCode";

		}

		public String HashCode;

		public String getHashCode() {
			return this.HashCode;
		}

		public Boolean HashCodeIsNullable() {
			return true;
		}

		public Boolean HashCodeIsKey() {
			return false;
		}

		public Integer HashCodeLength() {
			return 8;
		}

		public Integer HashCodePrecision() {
			return 0;
		}

		public String HashCodeDefault() {

			return null;

		}

		public String HashCodeComment() {

			return "";

		}

		public String HashCodePattern() {

			return "dd-MM-yyyy";

		}

		public String HashCodeOriginalDbColumnName() {

			return "HashCode";

		}

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 2;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return 11;
		}

		public Integer NamePrecision() {
			return 0;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "dd-MM-yyyy";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		public Integer ID;

		public Integer getID() {
			return this.ID;
		}

		public Boolean IDIsNullable() {
			return true;
		}

		public Boolean IDIsKey() {
			return false;
		}

		public Integer IDLength() {
			return 2;
		}

		public Integer IDPrecision() {
			return 0;
		}

		public String IDDefault() {

			return null;

		}

		public String IDComment() {

			return "";

		}

		public String IDPattern() {

			return "dd-MM-yyyy";

		}

		public String IDOriginalDbColumnName() {

			return "ID";

		}

		public java.util.Date Birthday;

		public java.util.Date getBirthday() {
			return this.Birthday;
		}

		public Boolean BirthdayIsNullable() {
			return true;
		}

		public Boolean BirthdayIsKey() {
			return false;
		}

		public Integer BirthdayLength() {
			return null;
		}

		public Integer BirthdayPrecision() {
			return 0;
		}

		public String BirthdayDefault() {

			return null;

		}

		public String BirthdayComment() {

			return "";

		}

		public String BirthdayPattern() {

			return "dd-MM-yyyy";

		}

		public String BirthdayOriginalDbColumnName() {

			return "Birthday";

		}

		public String State;

		public String getState() {
			return this.State;
		}

		public Boolean StateIsNullable() {
			return true;
		}

		public Boolean StateIsKey() {
			return false;
		}

		public Integer StateLength() {
			return 2;
		}

		public Integer StatePrecision() {
			return 0;
		}

		public String StateDefault() {

			return null;

		}

		public String StateComment() {

			return "";

		}

		public String StatePattern() {

			return "dd-MM-yyyy";

		}

		public String StateOriginalDbColumnName() {

			return "State";

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.ID = readInteger(dis);

					this.Birthday = readDate(dis);

					this.State = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readString(dis);

					this.Name = readString(dis);

					this.ID = readInteger(dis);

					this.Birthday = readDate(dis);

					this.State = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// Integer

				writeInteger(this.ID, dos);

				// java.util.Date

				writeDate(this.Birthday, dos);

				// String

				writeString(this.State, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.PostalCode, dos);

				// String

				writeString(this.HashCode, dos);

				// String

				writeString(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// Integer

				writeInteger(this.ID, dos);

				// java.util.Date

				writeDate(this.Birthday, dos);

				// String

				writeString(this.State, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("PostalCode=" + String.valueOf(PostalCode));
			sb.append(",HashCode=" + HashCode);
			sb.append(",CustomerId=" + CustomerId);
			sb.append(",Name=" + Name);
			sb.append(",ID=" + String.valueOf(ID));
			sb.append(",Birthday=" + String.valueOf(Birthday));
			sb.append(",State=" + State);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (PostalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(PostalCode);
			}

			sb.append("|");

			if (HashCode == null) {
				sb.append("<null>");
			} else {
				sb.append(HashCode);
			}

			sb.append("|");

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			if (ID == null) {
				sb.append("<null>");
			} else {
				sb.append(ID);
			}

			sb.append("|");

			if (Birthday == null) {
				sb.append("<null>");
			} else {
				sb.append(Birthday);
			}

			sb.append("|");

			if (State == null) {
				sb.append("<null>");
			} else {
				sb.append(State);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputExcel_1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputExcel_1", "3557Aw_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_2Process(globalMap);
				tFileInputDelimited_4Process(globalMap);
				tFileInputDelimited_3Process(globalMap);
				tFileInputDelimited_1Process(globalMap);

				row1Struct row1 = new row1Struct();
				out1Struct out1 = new out1Struct();
				row7Struct row7 = new row7Struct();
				row7Struct row9 = row7;
				NoGenderStruct NoGender = new NoGenderStruct();
				row6Struct row6 = new row6Struct();
				NoStateStruct NoState = new NoStateStruct();
				row8Struct row8 = new row8Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				sh("tLogRow_1");

				s(currentComponent = "tLogRow_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row9");

				int tos_count_tLogRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
							log4jParamters_tLogRow_1.append("Parameters:");
							log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_CONTENT_WITH_LOG4J" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tLogRow_1 - " + (log4jParamters_tLogRow_1));
						}
					}
					new BytesLimit65535_tLogRow_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tLogRow_1", "tLogRow_1", "tLogRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[13];

					public void addRow(String[] row) {

						for (int i = 0; i < 13; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 12 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 12 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[12] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "CustomerId", "Name", "HashCode", "gender", "email", "Birthday",
						"Age", "PostalCode", "State", "UserStatus", "ChurnDate", "TotalTransactionValue",
						"TransactionYear", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 begin ] start
				 */

				sh("tFileOutputDelimited_3");

				s(currentComponent = "tFileOutputDelimited_3");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row7");

				int tos_count_tFileOutputDelimited_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputDelimited_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputDelimited_3 = new StringBuilder();
							log4jParamters_tFileOutputDelimited_3.append("Parameters:");
							log4jParamters_tFileOutputDelimited_3.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Desktop/LAB/FinalResult.csv\"");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("APPEND" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("INCLUDEHEADER" + " = " + "true");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("COMPRESS" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("SPLIT" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("ROW_MODE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							log4jParamters_tFileOutputDelimited_3.append("FILE_EXIST_EXCEPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputDelimited_3 - " + (log4jParamters_tFileOutputDelimited_3));
						}
					}
					new BytesLimit65535_tFileOutputDelimited_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputDelimited_3", "tFileOutputDelimited_3", "tFileOutputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				String fileName_tFileOutputDelimited_3 = "";
				fileName_tFileOutputDelimited_3 = (new java.io.File("/Users/abuzaki/Desktop/LAB/FinalResult.csv"))
						.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_3 = null;
				String extension_tFileOutputDelimited_3 = null;
				String directory_tFileOutputDelimited_3 = null;
				if ((fileName_tFileOutputDelimited_3.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") < fileName_tFileOutputDelimited_3
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
							fileName_tFileOutputDelimited_3.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					}
					directory_tFileOutputDelimited_3 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_3 = true;
				java.io.File filetFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);
				int nb_line_tFileOutputDelimited_3 = 0;
				int splitedFileNo_tFileOutputDelimited_3 = 0;
				int currentRow_tFileOutputDelimited_3 = 0;

				final String OUT_DELIM_tFileOutputDelimited_3 = /** Start field tFileOutputDelimited_3:FIELDSEPARATOR */
						","/** End field tFileOutputDelimited_3:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_3 = /**
																		 * Start field
																		 * tFileOutputDelimited_3:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_3:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_3 != null && directory_tFileOutputDelimited_3.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_3 = new java.io.File(directory_tFileOutputDelimited_3);
					if (!dir_tFileOutputDelimited_3.exists()) {
						log.info("tFileOutputDelimited_3 - Creating directory '"
								+ dir_tFileOutputDelimited_3.getCanonicalPath() + "'.");
						dir_tFileOutputDelimited_3.mkdirs();
						log.info("tFileOutputDelimited_3 - The directory '"
								+ dir_tFileOutputDelimited_3.getCanonicalPath() + "' has been created successfully.");
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_3 = null;

				java.io.File fileToDelete_tFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				if (fileToDelete_tFileOutputDelimited_3.exists()) {
					fileToDelete_tFileOutputDelimited_3.delete();
				}
				outtFileOutputDelimited_3 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_3, false), "ISO-8859-15"));
				resourceMap.put("out_tFileOutputDelimited_3", outtFileOutputDelimited_3);
				if (filetFileOutputDelimited_3.length() == 0) {
					outtFileOutputDelimited_3.write("CustomerId");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("Name");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("HashCode");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("gender");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("email");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("Birthday");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("Age");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("PostalCode");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("State");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("UserStatus");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("ChurnDate");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("TotalTransactionValue");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("TransactionYear");
					outtFileOutputDelimited_3.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.flush();
				}

				resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

				/**
				 * [tFileOutputDelimited_3 begin ] stop
				 */

				/**
				 * [tFilterRow_18 begin ] start
				 */

				sh("tFilterRow_18");

				s(currentComponent = "tFilterRow_18");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out1");

				int tos_count_tFilterRow_18 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_18 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_18 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_18 = new StringBuilder();
							log4jParamters_tFilterRow_18.append("Parameters:");
							log4jParamters_tFilterRow_18.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_18.append(" | ");
							log4jParamters_tFilterRow_18
									.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("!=") + ", RVALUE=" + ("\"\"")
											+ ", INPUT_COLUMN=" + ("TransactionYear") + ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_18.append(" | ");
							log4jParamters_tFilterRow_18.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_18.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_18 - " + (log4jParamters_tFilterRow_18));
						}
					}
					new BytesLimit65535_tFilterRow_18().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_18", "tFilterRow_18", "tFilterRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tFilterRow_18 = 0;
				int nb_line_ok_tFilterRow_18 = 0;
				int nb_line_reject_tFilterRow_18 = 0;

				class Operator_tFilterRow_18 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_18(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_18 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				sh("tFileOutputDelimited_1");

				s(currentComponent = "tFileOutputDelimited_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row6");

				int tos_count_tFileOutputDelimited_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputDelimited_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputDelimited_1 = new StringBuilder();
							log4jParamters_tFileOutputDelimited_1.append("Parameters:");
							log4jParamters_tFileOutputDelimited_1.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Desktop/LAB/NoGender.csv\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("APPEND" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("INCLUDEHEADER" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("COMPRESS" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("SPLIT" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ROW_MODE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							log4jParamters_tFileOutputDelimited_1.append("FILE_EXIST_EXCEPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputDelimited_1 - " + (log4jParamters_tFileOutputDelimited_1));
						}
					}
					new BytesLimit65535_tFileOutputDelimited_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputDelimited_1", "tFileOutputDelimited_1", "tFileOutputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File("/Users/abuzaki/Desktop/LAB/NoGender.csv"))
						.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						","/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						log.info("tFileOutputDelimited_1 - Creating directory '"
								+ dir_tFileOutputDelimited_1.getCanonicalPath() + "'.");
						dir_tFileOutputDelimited_1.mkdirs();
						log.info("tFileOutputDelimited_1 - The directory '"
								+ dir_tFileOutputDelimited_1.getCanonicalPath() + "' has been created successfully.");
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "ISO-8859-15"));
				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);

				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tFilterRow_14 begin ] start
				 */

				sh("tFilterRow_14");

				s(currentComponent = "tFilterRow_14");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "NoGender");

				int tos_count_tFilterRow_14 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_14 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_14 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_14 = new StringBuilder();
							log4jParamters_tFilterRow_14.append("Parameters:");
							log4jParamters_tFilterRow_14.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_14.append(" | ");
							log4jParamters_tFilterRow_14.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("!=")
									+ ", RVALUE=" + ("null") + ", INPUT_COLUMN=" + ("Name") + ", FUNCTION=" + ("")
									+ "}, {OPERATOR=" + ("!=") + ", RVALUE=" + ("\"\"") + ", INPUT_COLUMN=" + ("Name")
									+ ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_14.append(" | ");
							log4jParamters_tFilterRow_14.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_14.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_14 - " + (log4jParamters_tFilterRow_14));
						}
					}
					new BytesLimit65535_tFilterRow_14().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_14", "tFilterRow_14", "tFilterRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tFilterRow_14 = 0;
				int nb_line_ok_tFilterRow_14 = 0;
				int nb_line_reject_tFilterRow_14 = 0;

				class Operator_tFilterRow_14 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_14(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_14 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 begin ] start
				 */

				sh("tFileOutputDelimited_2");

				s(currentComponent = "tFileOutputDelimited_2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row8");

				int tos_count_tFileOutputDelimited_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileOutputDelimited_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileOutputDelimited_2 = new StringBuilder();
							log4jParamters_tFileOutputDelimited_2.append("Parameters:");
							log4jParamters_tFileOutputDelimited_2.append("USESTREAM" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Desktop/LAB/NoState.csv\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("APPEND" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("INCLUDEHEADER" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("COMPRESS" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("CREATE" + " = " + "true");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("SPLIT" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("FLUSHONROW" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ROW_MODE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("DELETE_EMPTYFILE" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							log4jParamters_tFileOutputDelimited_2.append("FILE_EXIST_EXCEPTION" + " = " + "false");
							log4jParamters_tFileOutputDelimited_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileOutputDelimited_2 - " + (log4jParamters_tFileOutputDelimited_2));
						}
					}
					new BytesLimit65535_tFileOutputDelimited_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileOutputDelimited_2", "tFileOutputDelimited_2", "tFileOutputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				String fileName_tFileOutputDelimited_2 = "";
				fileName_tFileOutputDelimited_2 = (new java.io.File("/Users/abuzaki/Desktop/LAB/NoState.csv"))
						.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_2 = null;
				String extension_tFileOutputDelimited_2 = null;
				String directory_tFileOutputDelimited_2 = null;
				if ((fileName_tFileOutputDelimited_2.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") < fileName_tFileOutputDelimited_2
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
							fileName_tFileOutputDelimited_2.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					}
					directory_tFileOutputDelimited_2 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_2 = true;
				java.io.File filetFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);
				int nb_line_tFileOutputDelimited_2 = 0;
				int splitedFileNo_tFileOutputDelimited_2 = 0;
				int currentRow_tFileOutputDelimited_2 = 0;

				final String OUT_DELIM_tFileOutputDelimited_2 = /** Start field tFileOutputDelimited_2:FIELDSEPARATOR */
						","/** End field tFileOutputDelimited_2:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_2 = /**
																		 * Start field
																		 * tFileOutputDelimited_2:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_2:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_2 != null && directory_tFileOutputDelimited_2.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_2 = new java.io.File(directory_tFileOutputDelimited_2);
					if (!dir_tFileOutputDelimited_2.exists()) {
						log.info("tFileOutputDelimited_2 - Creating directory '"
								+ dir_tFileOutputDelimited_2.getCanonicalPath() + "'.");
						dir_tFileOutputDelimited_2.mkdirs();
						log.info("tFileOutputDelimited_2 - The directory '"
								+ dir_tFileOutputDelimited_2.getCanonicalPath() + "' has been created successfully.");
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_2 = null;

				java.io.File fileToDelete_tFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				if (fileToDelete_tFileOutputDelimited_2.exists()) {
					fileToDelete_tFileOutputDelimited_2.delete();
				}
				outtFileOutputDelimited_2 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_2, false), "ISO-8859-15"));
				resourceMap.put("out_tFileOutputDelimited_2", outtFileOutputDelimited_2);

				resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

				/**
				 * [tFileOutputDelimited_2 begin ] stop
				 */

				/**
				 * [tFilterRow_10 begin ] start
				 */

				sh("tFilterRow_10");

				s(currentComponent = "tFilterRow_10");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "NoState");

				int tos_count_tFilterRow_10 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_10 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_10 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_10 = new StringBuilder();
							log4jParamters_tFilterRow_10.append("Parameters:");
							log4jParamters_tFilterRow_10.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_10.append(" | ");
							log4jParamters_tFilterRow_10.append("CONDITIONS" + " = " + "[{OPERATOR=" + ("!=")
									+ ", RVALUE=" + ("null") + ", INPUT_COLUMN=" + ("Name") + ", FUNCTION=" + ("")
									+ "}, {OPERATOR=" + ("!=") + ", RVALUE=" + ("\"\"") + ", INPUT_COLUMN=" + ("Name")
									+ ", FUNCTION=" + ("") + "}]");
							log4jParamters_tFilterRow_10.append(" | ");
							log4jParamters_tFilterRow_10.append("USE_ADVANCED" + " = " + "false");
							log4jParamters_tFilterRow_10.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_10 - " + (log4jParamters_tFilterRow_10));
						}
					}
					new BytesLimit65535_tFilterRow_10().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_10", "tFilterRow_10", "tFilterRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tFilterRow_10 = 0;
				int nb_line_ok_tFilterRow_10 = 0;
				int nb_line_reject_tFilterRow_10 = 0;

				class Operator_tFilterRow_10 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_10(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_10 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				sh("tMap_1");

				s(currentComponent = "tMap_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tMap_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_1 = new StringBuilder();
							log4jParamters_tMap_1.append("Parameters:");
							log4jParamters_tMap_1.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_1.append(" | ");
							log4jParamters_tMap_1.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_1 - " + (log4jParamters_tMap_1));
						}
					}
					new BytesLimit65535_tMap_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_1", "tMap_1", "tMap");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

// ###############################
// # Lookup's keys initialization
				int count_row1_tMap_1 = 0;

				int count_row3_tMap_1 = 0;

				int count_row2_tMap_1 = 0;

				int count_row12_tMap_1 = 0;

				int count_row5_tMap_1 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct>) globalMap
						.get("tHash_Lookup_row3"));

				row3Struct row3HashKey = new row3Struct();
				row3Struct row3Default = new row3Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct>) globalMap
						.get("tHash_Lookup_row2"));

				row2Struct row2HashKey = new row2Struct();
				row2Struct row2Default = new row2Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) globalMap
						.get("tHash_Lookup_row12"));

				row12Struct row12HashKey = new row12Struct();
				row12Struct row12Default = new row12Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) globalMap
						.get("tHash_Lookup_row5"));

				row5Struct row5HashKey = new row5Struct();
				row5Struct row5Default = new row5Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
					String NameTrim;
					String vCleanName;
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out1_tMap_1 = 0;

				out1Struct out1_tmp = new out1Struct();
				int count_NoGender_tMap_1 = 0;

				NoGenderStruct NoGender_tmp = new NoGenderStruct();
				int count_NoState_tMap_1 = 0;

				NoStateStruct NoState_tmp = new NoStateStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				sh("tFileInputExcel_1");

				s(currentComponent = "tFileInputExcel_1");

				cLabel = "Customer_Data";

				int tos_count_tFileInputExcel_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputExcel_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputExcel_1 = new StringBuilder();
							log4jParamters_tFileInputExcel_1.append("Parameters:");
							log4jParamters_tFileInputExcel_1.append("VERSION_2007" + " = " + "true");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Downloads/DI/Customer Data.xlsx\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("PASSWORD" + " = "
									+ String.valueOf(
											"enc:routine.encryption.key.v1:Udq6aGtDVaefC60qND6vosN1dGNbYGaLcH1B0A==")
											.substring(0, 4)
									+ "...");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ALL_SHEETS" + " = " + "true");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("AFFECT_EACH_SHEET" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("FIRST_COLUMN" + " = " + "1");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("LAST_COLUMN" + " = " + "");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("PostalCode") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("HashCode") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("CustomerId")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Name") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ID") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Birthday") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("State") + "}]");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("CONVERTDATETOSTRING" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("ENCODING" + " = " + "\"UTF-8\"");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("STOPREAD_ON_EMPTYROW" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("GENERATION_MODE" + " = " + "USER_MODE");
							log4jParamters_tFileInputExcel_1.append(" | ");
							log4jParamters_tFileInputExcel_1.append("CONFIGURE_INFLATION_RATIO" + " = " + "false");
							log4jParamters_tFileInputExcel_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputExcel_1 - " + (log4jParamters_tFileInputExcel_1));
						}
					}
					new BytesLimit65535_tFileInputExcel_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputExcel_1", "Customer_Data", "tFileInputExcel");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:8CH34QHFUeTsANtZHfHhbGvLn/bfxtvdrpMzvw==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				class RegexUtil_tFileInputExcel_1 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

				Object source_tFileInputExcel_1 = "/Users/abuzaki/Downloads/DI/Customer Data.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof String) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_1), password_tFileInputExcel_1,
									true);
				} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_1, password_tFileInputExcel_1);
				} else {
					workbook_tFileInputExcel_1 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.ss.usermodel.Sheet sheet_tFileInputExcel_1 : workbook_tFileInputExcel_1) {
						sheetList_tFileInputExcel_1
								.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet_tFileInputExcel_1);
					}
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1 != null
								&& sheetList_FilterNull_tFileInputExcel_1.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
					int nb_line_tFileInputExcel_1 = 0;
					if (sheetList_tFileInputExcel_1.size() > 0) {

						int begin_line_tFileInputExcel_1 = 1;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += (sheet_tFileInputExcel_1.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = -1;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
								.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();
						log.debug("tFileInputExcel_1 - Retrieving records from the datasource.");

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getSheetName());
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							}
							row1 = null;
							int tempRowLength_tFileInputExcel_1 = 7;

							int columnIndex_tFileInputExcel_1 = 0;

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int excel_end_column_tFileInputExcel_1;
							if (row_tFileInputExcel_1 == null) {
								excel_end_column_tFileInputExcel_1 = 0;
							} else {
								excel_end_column_tFileInputExcel_1 = row_tFileInputExcel_1.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_1;
							if (end_column_tFileInputExcel_1 == -1) {
								actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
							} else {
								actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > excel_end_column_tFileInputExcel_1
										? excel_end_column_tFileInputExcel_1
										: end_column_tFileInputExcel_1;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {
								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1
											.getCell(i + start_column_tFileInputExcel_1);
									if (cell_tFileInputExcel_1 != null) {
										switch (cell_tFileInputExcel_1.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_1)) {
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1
														.format(cell_tFileInputExcel_1.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_1[i] = String
													.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_1)) {
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_1.getNumericCellValue());
													temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_1[i] = String
														.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_1[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_1[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_1[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_1 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "PostalCode";

									row1.PostalCode = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.PostalCode = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "HashCode";

									row1.HashCode = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.HashCode = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "CustomerId";

									row1.CustomerId = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.CustomerId = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Name";

									row1.Name = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Name = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "ID";

									row1.ID = ParserUtils.parseTo_Integer(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.ID = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Birthday";

									if (5 < actual_end_column_tFileInputExcel_1) {
										try {
											if (row_tFileInputExcel_1
													.getCell(columnIndex_tFileInputExcel_1
															+ start_column_tFileInputExcel_1)
													.getCellType() == org.apache.poi.ss.usermodel.CellType.NUMERIC
													&& org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(
															row_tFileInputExcel_1.getCell(columnIndex_tFileInputExcel_1
																	+ start_column_tFileInputExcel_1))) {
												row1.Birthday = row_tFileInputExcel_1.getCell(
														columnIndex_tFileInputExcel_1 + start_column_tFileInputExcel_1)
														.getDateCellValue();
											} else {
												java.util.Date tempDate_tFileInputExcel_1 = ParserUtils.parseTo_Date(
														temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1],
														"dd-MM-yyyy");
												if (tempDate_tFileInputExcel_1
														.after((new SimpleDateFormat("yyyy/MM/dd hh:mm:ss.SSS"))
																.parse("9999/12/31 23:59:59.999"))
														|| tempDate_tFileInputExcel_1
																.before((new SimpleDateFormat("yyyy/MM/dd"))
																		.parse("1900/01/01"))) {
													throw new RuntimeException("The cell format is not Date in ( Row. "
															+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
															+ curColNum_tFileInputExcel_1 + " )");
												} else {
													row1.Birthday = tempDate_tFileInputExcel_1;
												}
											}
										} catch (java.lang.Exception e) {
											globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());

											throw new RuntimeException("The cell format is not Date in ( Row. "
													+ (nb_line_tFileInputExcel_1 + 1) + " and ColumnNum. "
													+ curColNum_tFileInputExcel_1 + " )");
										}
									}

								} else {
									row1.Birthday = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "State";

									row1.State = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.State = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

								log.debug("tFileInputExcel_1 - Retrieving the record " + (nb_line_tFileInputExcel_1)
										+ ".");

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								log.error("tFileInputExcel_1 - " + e.getMessage());

								System.err.println(e.getMessage());
								row1 = null;
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							s(currentComponent = "tFileInputExcel_1");

							cLabel = "Customer_Data";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							s(currentComponent = "tFileInputExcel_1");

							cLabel = "Customer_Data";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
							 */

// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tMap_1 main ] start
								 */

								s(currentComponent = "tMap_1");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "row1", "tFileInputExcel_1", "Customer_Data", "tFileInputExcel", "tMap_1",
										"tMap_1", "tMap"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

								row3Struct row3 = null;

								row2Struct row2 = null;

								row12Struct row12 = null;

								row5Struct row5 = null;

								// ###############################
								// # Input tables (lookups)

								boolean rejectedInnerJoin_tMap_1 = false;
								boolean mainRowRejected_tMap_1 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "row3"
								///////////////////////////////////////////////

								boolean forceLooprow3 = false;

								row3Struct row3ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_1 = false;

									row3HashKey.stateshort = row1.State.trim().toUpperCase();

									row3HashKey.hashCodeDirty = true;

									tHash_Lookup_row3.lookup(row3HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row3 != null && tHash_Lookup_row3.getCount(row3HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row3'
									// and it contains more one result from keys : row3.stateshort = '" +
									// row3HashKey.stateshort + "'");
								} // G 071

								row3Struct fromLookup_row3 = null;
								row3 = row3Default;

								if (tHash_Lookup_row3 != null && tHash_Lookup_row3.hasNext()) { // G 099

									fromLookup_row3 = tHash_Lookup_row3.next();

								} // G 099

								if (fromLookup_row3 != null) {
									row3 = fromLookup_row3;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "row2"
								///////////////////////////////////////////////

								boolean forceLooprow2 = false;

								row2Struct row2ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_1 = false;

									row2HashKey.Name = row1.Name.trim();

									row2HashKey.hashCodeDirty = true;

									tHash_Lookup_row2.lookup(row2HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row2 != null && tHash_Lookup_row2.getCount(row2HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row2'
									// and it contains more one result from keys : row2.Name = '" + row2HashKey.Name
									// + "'");
								} // G 071

								row2Struct fromLookup_row2 = null;
								row2 = row2Default;

								if (tHash_Lookup_row2 != null && tHash_Lookup_row2.hasNext()) { // G 099

									fromLookup_row2 = tHash_Lookup_row2.next();

								} // G 099

								if (fromLookup_row2 != null) {
									row2 = fromLookup_row2;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "row12"
								///////////////////////////////////////////////

								boolean forceLooprow12 = false;

								row12Struct row12ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_1 = false;

									row12HashKey.CustomerId = row1.CustomerId;

									row12HashKey.hashCodeDirty = true;

									tHash_Lookup_row12.lookup(row12HashKey);

								} // G_TM_M_020

								if (tHash_Lookup_row12 != null && tHash_Lookup_row12.getCount(row12HashKey) > 1) { // G
																													// 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
									// 'row12' and it contains more one result from keys : row12.CustomerId = '" +
									// row12HashKey.CustomerId + "'");
								} // G 071

								row12Struct fromLookup_row12 = null;
								row12 = row12Default;

								if (tHash_Lookup_row12 != null && tHash_Lookup_row12.hasNext()) { // G 099

									fromLookup_row12 = tHash_Lookup_row12.next();

								} // G 099

								if (fromLookup_row12 != null) {
									row12 = fromLookup_row12;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "row5"
								///////////////////////////////////////////////

								boolean forceLooprow5 = false;

								row5Struct row5ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_1 = false;

									row5HashKey.CustomerId = row1.CustomerId;

									row5HashKey.hashCodeDirty = true;

									tHash_Lookup_row5.lookup(row5HashKey);

									if (!tHash_Lookup_row5.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_1 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_row5 != null && tHash_Lookup_row5.getCount(row5HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row5'
									// and it contains more one result from keys : row5.CustomerId = '" +
									// row5HashKey.CustomerId + "'");
								} // G 071

								row5Struct fromLookup_row5 = null;
								row5 = row5Default;

								if (tHash_Lookup_row5 != null && tHash_Lookup_row5.hasNext()) { // G 099

									fromLookup_row5 = tHash_Lookup_row5.next();

								} // G 099

								if (fromLookup_row5 != null) {
									row5 = fromLookup_row5;
								}

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;
									Var.NameTrim = row1.Name != null ? row1.Name.trim() : "";
									Var.vCleanName = row1.Name != null
											? row1.Name.trim().replaceAll("[()]", "").replaceAll("\\s+", "")
											: "";// ###############################
													// ###############################
													// # Output tables

									out1 = null;
									NoGender = null;
									NoState = null;

									if (!rejectedInnerJoin_tMap_1) {

// # Output table : 'out1'
										count_out1_tMap_1++;

										out1_tmp.CustomerId = row1.CustomerId;
										out1_tmp.Name = Var.NameTrim;
										out1_tmp.HashCode = row1.HashCode;
										out1_tmp.gender = row2.Gender == null ? "Unknown"
												: (row2.Gender.trim().equalsIgnoreCase("mannlich") ? "M" : "F");
										out1_tmp.email = Var.vCleanName.length() < 7
												? Var.vCleanName.toLowerCase() + "@gmail.com"
												: Var.vCleanName.length() == 7
														? Var.vCleanName.toLowerCase() + "@msn.com"
														: Var.vCleanName.length() > 7
																? Var.vCleanName.toLowerCase() + "@msn.com"
																: "";
										out1_tmp.Birthday = TalendDate.formatDate("yyyy-MM-dd", row1.Birthday);
										out1_tmp.Age = TalendDate.getPartOfDate("YEAR", TalendDate.getCurrentDate())
												- TalendDate.getPartOfDate("YEAR", row1.Birthday);
										out1_tmp.PostalCode = row1.PostalCode;
										out1_tmp.State = row3.statefull == null ? "Unknown State" : row3.statefull;
										out1_tmp.UserStatus = row12.DateChurn != null ? "N" : "Y";
										out1_tmp.ChurnDate = row12.DateChurn != null
												? TalendDate.formatDateInUTC("dd/MM/yyyy HH:mm:ss", row12.DateChurn)
												: "Active";
										out1_tmp.TotalTransactionValue = row5.YearlyTotal;
										out1_tmp.TransactionYear = row5.TransactionYear;
										out1 = out1_tmp;
										log.debug("tMap_1 - Outputting the record " + count_out1_tMap_1
												+ " of the output table 'out1'.");

// # Output table : 'NoGender'
										count_NoGender_tMap_1++;

										NoGender_tmp.Name = row2.Gender == null
												? (row1.Name != null ? row1.Name.trim() : "")
												: null;
										NoGender = NoGender_tmp;
										log.debug("tMap_1 - Outputting the record " + count_NoGender_tMap_1
												+ " of the output table 'NoGender'.");

// # Output table : 'NoState'
										count_NoState_tMap_1++;

										NoState_tmp.Name = row3.stateshort == null
												? (row1.Name != null ? row1.Name.trim() : "")
												: null;
										NoState = NoState_tmp;
										log.debug("tMap_1 - Outputting the record " + count_NoState_tMap_1
												+ " of the output table 'NoState'.");

									} // closing inner join bracket (2)
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								s(currentComponent = "tMap_1");

								/**
								 * [tMap_1 process_data_begin ] stop
								 */

// Start of branch "out1"
								if (out1 != null) {

									/**
									 * [tFilterRow_18 main ] start
									 */

									s(currentComponent = "tFilterRow_18");

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "out1", "tMap_1", "tMap_1", "tMap", "tFilterRow_18", "tFilterRow_18",
											"tFilterRow"

									)) {
										talendJobLogProcess(globalMap);
									}

									if (log.isTraceEnabled()) {
										log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
									}

									row7 = null;
									Operator_tFilterRow_18 ope_tFilterRow_18 = new Operator_tFilterRow_18("&&");
									ope_tFilterRow_18
											.matches(
													(out1.TransactionYear == null ? false
															: out1.TransactionYear.compareTo(ParserUtils
																	.parseTo_Integer(String.valueOf(""))) != 0),
													"TransactionYear.compareTo(\"\") != 0 failed");

									if (ope_tFilterRow_18.getMatchFlag()) {
										if (row7 == null) {
											row7 = new row7Struct();
										}
										row7.CustomerId = out1.CustomerId;
										row7.Name = out1.Name;
										row7.HashCode = out1.HashCode;
										row7.gender = out1.gender;
										row7.email = out1.email;
										row7.Birthday = out1.Birthday;
										row7.Age = out1.Age;
										row7.PostalCode = out1.PostalCode;
										row7.State = out1.State;
										row7.UserStatus = out1.UserStatus;
										row7.ChurnDate = out1.ChurnDate;
										row7.TotalTransactionValue = out1.TotalTransactionValue;
										row7.TransactionYear = out1.TransactionYear;
										log.debug("tFilterRow_18 - Process the record " + (nb_line_tFilterRow_18 + 1)
												+ ".");

										nb_line_ok_tFilterRow_18++;
									} else {
										nb_line_reject_tFilterRow_18++;
									}

									nb_line_tFilterRow_18++;

									tos_count_tFilterRow_18++;

									/**
									 * [tFilterRow_18 main ] stop
									 */

									/**
									 * [tFilterRow_18 process_data_begin ] start
									 */

									s(currentComponent = "tFilterRow_18");

									/**
									 * [tFilterRow_18 process_data_begin ] stop
									 */

// Start of branch "row7"
									if (row7 != null) {

										/**
										 * [tFileOutputDelimited_3 main ] start
										 */

										s(currentComponent = "tFileOutputDelimited_3");

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row7", "tFilterRow_18", "tFilterRow_18", "tFilterRow",
												"tFileOutputDelimited_3", "tFileOutputDelimited_3",
												"tFileOutputDelimited"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row7 - " + (row7 == null ? "" : row7.toLogString()));
										}

										StringBuilder sb_tFileOutputDelimited_3 = new StringBuilder();
										if (row7.CustomerId != null) {
											sb_tFileOutputDelimited_3.append(row7.CustomerId);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.Name != null) {
											sb_tFileOutputDelimited_3.append(row7.Name);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.HashCode != null) {
											sb_tFileOutputDelimited_3.append(row7.HashCode);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.gender != null) {
											sb_tFileOutputDelimited_3.append(row7.gender);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.email != null) {
											sb_tFileOutputDelimited_3.append(row7.email);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.Birthday != null) {
											sb_tFileOutputDelimited_3.append(row7.Birthday);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.Age != null) {
											sb_tFileOutputDelimited_3.append(row7.Age);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.PostalCode != null) {
											sb_tFileOutputDelimited_3.append(row7.PostalCode);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.State != null) {
											sb_tFileOutputDelimited_3.append(row7.State);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.UserStatus != null) {
											sb_tFileOutputDelimited_3.append(row7.UserStatus);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.ChurnDate != null) {
											sb_tFileOutputDelimited_3.append(row7.ChurnDate);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.TotalTransactionValue != null) {
											sb_tFileOutputDelimited_3.append(row7.TotalTransactionValue);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
										if (row7.TransactionYear != null) {
											sb_tFileOutputDelimited_3.append(row7.TransactionYear);
										}
										sb_tFileOutputDelimited_3.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);

										nb_line_tFileOutputDelimited_3++;
										resourceMap.put("nb_line_tFileOutputDelimited_3",
												nb_line_tFileOutputDelimited_3);

										outtFileOutputDelimited_3.write(sb_tFileOutputDelimited_3.toString());
										log.debug("tFileOutputDelimited_3 - Writing the record "
												+ nb_line_tFileOutputDelimited_3 + ".");

										row9 = row7;

										tos_count_tFileOutputDelimited_3++;

										/**
										 * [tFileOutputDelimited_3 main ] stop
										 */

										/**
										 * [tFileOutputDelimited_3 process_data_begin ] start
										 */

										s(currentComponent = "tFileOutputDelimited_3");

										/**
										 * [tFileOutputDelimited_3 process_data_begin ] stop
										 */

										/**
										 * [tLogRow_1 main ] start
										 */

										s(currentComponent = "tLogRow_1");

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row9", "tFileOutputDelimited_3", "tFileOutputDelimited_3",
												"tFileOutputDelimited", "tLogRow_1", "tLogRow_1", "tLogRow"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row9 - " + (row9 == null ? "" : row9.toLogString()));
										}

///////////////////////		

										String[] row_tLogRow_1 = new String[13];

										if (row9.CustomerId != null) { //
											row_tLogRow_1[0] = String.valueOf(row9.CustomerId);

										} //

										if (row9.Name != null) { //
											row_tLogRow_1[1] = String.valueOf(row9.Name);

										} //

										if (row9.HashCode != null) { //
											row_tLogRow_1[2] = String.valueOf(row9.HashCode);

										} //

										if (row9.gender != null) { //
											row_tLogRow_1[3] = String.valueOf(row9.gender);

										} //

										if (row9.email != null) { //
											row_tLogRow_1[4] = String.valueOf(row9.email);

										} //

										if (row9.Birthday != null) { //
											row_tLogRow_1[5] = String.valueOf(row9.Birthday);

										} //

										if (row9.Age != null) { //
											row_tLogRow_1[6] = String.valueOf(row9.Age);

										} //

										if (row9.PostalCode != null) { //
											row_tLogRow_1[7] = String.valueOf(row9.PostalCode);

										} //

										if (row9.State != null) { //
											row_tLogRow_1[8] = String.valueOf(row9.State);

										} //

										if (row9.UserStatus != null) { //
											row_tLogRow_1[9] = String.valueOf(row9.UserStatus);

										} //

										if (row9.ChurnDate != null) { //
											row_tLogRow_1[10] = String.valueOf(row9.ChurnDate);

										} //

										if (row9.TotalTransactionValue != null) { //
											row_tLogRow_1[11] = FormatterUtils
													.formatUnwithE(row9.TotalTransactionValue);

										} //

										if (row9.TransactionYear != null) { //
											row_tLogRow_1[12] = String.valueOf(row9.TransactionYear);

										} //

										util_tLogRow_1.addRow(row_tLogRow_1);
										nb_line_tLogRow_1++;
										log.info("tLogRow_1 - Content of row " + nb_line_tLogRow_1 + ": "
												+ TalendString.unionString("|", row_tLogRow_1));
//////

//////                    

///////////////////////    			

										tos_count_tLogRow_1++;

										/**
										 * [tLogRow_1 main ] stop
										 */

										/**
										 * [tLogRow_1 process_data_begin ] start
										 */

										s(currentComponent = "tLogRow_1");

										/**
										 * [tLogRow_1 process_data_begin ] stop
										 */

										/**
										 * [tLogRow_1 process_data_end ] start
										 */

										s(currentComponent = "tLogRow_1");

										/**
										 * [tLogRow_1 process_data_end ] stop
										 */

										/**
										 * [tFileOutputDelimited_3 process_data_end ] start
										 */

										s(currentComponent = "tFileOutputDelimited_3");

										/**
										 * [tFileOutputDelimited_3 process_data_end ] stop
										 */

									} // End of branch "row7"

									/**
									 * [tFilterRow_18 process_data_end ] start
									 */

									s(currentComponent = "tFilterRow_18");

									/**
									 * [tFilterRow_18 process_data_end ] stop
									 */

								} // End of branch "out1"

// Start of branch "NoGender"
								if (NoGender != null) {

									/**
									 * [tFilterRow_14 main ] start
									 */

									s(currentComponent = "tFilterRow_14");

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "NoGender", "tMap_1", "tMap_1", "tMap", "tFilterRow_14", "tFilterRow_14",
											"tFilterRow"

									)) {
										talendJobLogProcess(globalMap);
									}

									if (log.isTraceEnabled()) {
										log.trace("NoGender - " + (NoGender == null ? "" : NoGender.toLogString()));
									}

									row6 = null;
									Operator_tFilterRow_14 ope_tFilterRow_14 = new Operator_tFilterRow_14("&&");
									ope_tFilterRow_14.matches((NoGender.Name != null), "Name!=null failed");
									ope_tFilterRow_14.matches(
											(NoGender.Name == null ? false : NoGender.Name.compareTo("") != 0),
											"Name.compareTo(\"\") != 0 failed");

									if (ope_tFilterRow_14.getMatchFlag()) {
										if (row6 == null) {
											row6 = new row6Struct();
										}
										row6.Name = NoGender.Name;
										log.debug("tFilterRow_14 - Process the record " + (nb_line_tFilterRow_14 + 1)
												+ ".");

										nb_line_ok_tFilterRow_14++;
									} else {
										nb_line_reject_tFilterRow_14++;
									}

									nb_line_tFilterRow_14++;

									tos_count_tFilterRow_14++;

									/**
									 * [tFilterRow_14 main ] stop
									 */

									/**
									 * [tFilterRow_14 process_data_begin ] start
									 */

									s(currentComponent = "tFilterRow_14");

									/**
									 * [tFilterRow_14 process_data_begin ] stop
									 */

// Start of branch "row6"
									if (row6 != null) {

										/**
										 * [tFileOutputDelimited_1 main ] start
										 */

										s(currentComponent = "tFileOutputDelimited_1");

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row6", "tFilterRow_14", "tFilterRow_14", "tFilterRow",
												"tFileOutputDelimited_1", "tFileOutputDelimited_1",
												"tFileOutputDelimited"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row6 - " + (row6 == null ? "" : row6.toLogString()));
										}

										StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
										if (row6.Name != null) {
											sb_tFileOutputDelimited_1.append(row6.Name);
										}
										sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

										nb_line_tFileOutputDelimited_1++;
										resourceMap.put("nb_line_tFileOutputDelimited_1",
												nb_line_tFileOutputDelimited_1);

										outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());
										log.debug("tFileOutputDelimited_1 - Writing the record "
												+ nb_line_tFileOutputDelimited_1 + ".");

										tos_count_tFileOutputDelimited_1++;

										/**
										 * [tFileOutputDelimited_1 main ] stop
										 */

										/**
										 * [tFileOutputDelimited_1 process_data_begin ] start
										 */

										s(currentComponent = "tFileOutputDelimited_1");

										/**
										 * [tFileOutputDelimited_1 process_data_begin ] stop
										 */

										/**
										 * [tFileOutputDelimited_1 process_data_end ] start
										 */

										s(currentComponent = "tFileOutputDelimited_1");

										/**
										 * [tFileOutputDelimited_1 process_data_end ] stop
										 */

									} // End of branch "row6"

									/**
									 * [tFilterRow_14 process_data_end ] start
									 */

									s(currentComponent = "tFilterRow_14");

									/**
									 * [tFilterRow_14 process_data_end ] stop
									 */

								} // End of branch "NoGender"

// Start of branch "NoState"
								if (NoState != null) {

									/**
									 * [tFilterRow_10 main ] start
									 */

									s(currentComponent = "tFilterRow_10");

									if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

											, "NoState", "tMap_1", "tMap_1", "tMap", "tFilterRow_10", "tFilterRow_10",
											"tFilterRow"

									)) {
										talendJobLogProcess(globalMap);
									}

									if (log.isTraceEnabled()) {
										log.trace("NoState - " + (NoState == null ? "" : NoState.toLogString()));
									}

									row8 = null;
									Operator_tFilterRow_10 ope_tFilterRow_10 = new Operator_tFilterRow_10("&&");
									ope_tFilterRow_10.matches((NoState.Name != null), "Name!=null failed");
									ope_tFilterRow_10.matches(
											(NoState.Name == null ? false : NoState.Name.compareTo("") != 0),
											"Name.compareTo(\"\") != 0 failed");

									if (ope_tFilterRow_10.getMatchFlag()) {
										if (row8 == null) {
											row8 = new row8Struct();
										}
										row8.Name = NoState.Name;
										log.debug("tFilterRow_10 - Process the record " + (nb_line_tFilterRow_10 + 1)
												+ ".");

										nb_line_ok_tFilterRow_10++;
									} else {
										nb_line_reject_tFilterRow_10++;
									}

									nb_line_tFilterRow_10++;

									tos_count_tFilterRow_10++;

									/**
									 * [tFilterRow_10 main ] stop
									 */

									/**
									 * [tFilterRow_10 process_data_begin ] start
									 */

									s(currentComponent = "tFilterRow_10");

									/**
									 * [tFilterRow_10 process_data_begin ] stop
									 */

// Start of branch "row8"
									if (row8 != null) {

										/**
										 * [tFileOutputDelimited_2 main ] start
										 */

										s(currentComponent = "tFileOutputDelimited_2");

										if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

												, "row8", "tFilterRow_10", "tFilterRow_10", "tFilterRow",
												"tFileOutputDelimited_2", "tFileOutputDelimited_2",
												"tFileOutputDelimited"

										)) {
											talendJobLogProcess(globalMap);
										}

										if (log.isTraceEnabled()) {
											log.trace("row8 - " + (row8 == null ? "" : row8.toLogString()));
										}

										StringBuilder sb_tFileOutputDelimited_2 = new StringBuilder();
										if (row8.Name != null) {
											sb_tFileOutputDelimited_2.append(row8.Name);
										}
										sb_tFileOutputDelimited_2.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);

										nb_line_tFileOutputDelimited_2++;
										resourceMap.put("nb_line_tFileOutputDelimited_2",
												nb_line_tFileOutputDelimited_2);

										outtFileOutputDelimited_2.write(sb_tFileOutputDelimited_2.toString());
										log.debug("tFileOutputDelimited_2 - Writing the record "
												+ nb_line_tFileOutputDelimited_2 + ".");

										tos_count_tFileOutputDelimited_2++;

										/**
										 * [tFileOutputDelimited_2 main ] stop
										 */

										/**
										 * [tFileOutputDelimited_2 process_data_begin ] start
										 */

										s(currentComponent = "tFileOutputDelimited_2");

										/**
										 * [tFileOutputDelimited_2 process_data_begin ] stop
										 */

										/**
										 * [tFileOutputDelimited_2 process_data_end ] start
										 */

										s(currentComponent = "tFileOutputDelimited_2");

										/**
										 * [tFileOutputDelimited_2 process_data_end ] stop
										 */

									} // End of branch "row8"

									/**
									 * [tFilterRow_10 process_data_end ] start
									 */

									s(currentComponent = "tFilterRow_10");

									/**
									 * [tFilterRow_10 process_data_end ] stop
									 */

								} // End of branch "NoState"

								/**
								 * [tMap_1 process_data_end ] start
								 */

								s(currentComponent = "tMap_1");

								/**
								 * [tMap_1 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							s(currentComponent = "tFileInputExcel_1");

							cLabel = "Customer_Data";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							s(currentComponent = "tFileInputExcel_1");

							cLabel = "Customer_Data";

						}

						log.debug("tFileInputExcel_1 - Retrieved records count: " + nb_line_tFileInputExcel_1 + " .");

					}

					globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);
				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.getPackage().revert();
					}

				}

				if (log.isDebugEnabled())
					log.debug("tFileInputExcel_1 - " + ("Done."));

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				s(currentComponent = "tMap_1");

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row3 != null) {
					tHash_Lookup_row3.endGet();
				}
				globalMap.remove("tHash_Lookup_row3");

				if (tHash_Lookup_row2 != null) {
					tHash_Lookup_row2.endGet();
				}
				globalMap.remove("tHash_Lookup_row2");

				if (tHash_Lookup_row12 != null) {
					tHash_Lookup_row12.endGet();
				}
				globalMap.remove("tHash_Lookup_row12");

				if (tHash_Lookup_row5 != null) {
					tHash_Lookup_row5.endGet();
				}
				globalMap.remove("tHash_Lookup_row5");

// ###############################      
				log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");
				log.debug("tMap_1 - Written records count in the table 'NoGender': " + count_NoGender_tMap_1 + ".");
				log.debug("tMap_1 - Written records count in the table 'NoState': " + count_NoState_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tFileInputExcel_1", "Customer_Data", "tFileInputExcel", "tMap_1", "tMap_1", "tMap",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_1 - " + ("Done."));

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFilterRow_18 end ] start
				 */

				s(currentComponent = "tFilterRow_18");

				globalMap.put("tFilterRow_18_NB_LINE", nb_line_tFilterRow_18);
				globalMap.put("tFilterRow_18_NB_LINE_OK", nb_line_ok_tFilterRow_18);
				globalMap.put("tFilterRow_18_NB_LINE_REJECT", nb_line_reject_tFilterRow_18);

				log.info("tFilterRow_18 - Processed records count:" + nb_line_tFilterRow_18 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_18 + ". Rejected records count:" + nb_line_reject_tFilterRow_18 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out1", 2, 0, "tMap_1",
						"tMap_1", "tMap", "tFilterRow_18", "tFilterRow_18", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_18 - " + ("Done."));

				ok_Hash.put("tFilterRow_18", true);
				end_Hash.put("tFilterRow_18", System.currentTimeMillis());

				/**
				 * [tFilterRow_18 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 end ] start
				 */

				s(currentComponent = "tFileOutputDelimited_3");

				if (outtFileOutputDelimited_3 != null) {
					outtFileOutputDelimited_3.flush();
					outtFileOutputDelimited_3.close();
				}

				globalMap.put("tFileOutputDelimited_3_NB_LINE", nb_line_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);

				resourceMap.put("finish_tFileOutputDelimited_3", true);

				log.debug("tFileOutputDelimited_3 - Written records count: " + nb_line_tFileOutputDelimited_3 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row7", 2, 0,
						"tFilterRow_18", "tFilterRow_18", "tFilterRow", "tFileOutputDelimited_3",
						"tFileOutputDelimited_3", "tFileOutputDelimited", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_3 - " + ("Done."));

				ok_Hash.put("tFileOutputDelimited_3", true);
				end_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_3 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				s(currentComponent = "tLogRow_1");

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
				if (log.isInfoEnabled())
					log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row9", 2, 0,
						"tFileOutputDelimited_3", "tFileOutputDelimited_3", "tFileOutputDelimited", "tLogRow_1",
						"tLogRow_1", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Done."));

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tFilterRow_14 end ] start
				 */

				s(currentComponent = "tFilterRow_14");

				globalMap.put("tFilterRow_14_NB_LINE", nb_line_tFilterRow_14);
				globalMap.put("tFilterRow_14_NB_LINE_OK", nb_line_ok_tFilterRow_14);
				globalMap.put("tFilterRow_14_NB_LINE_REJECT", nb_line_reject_tFilterRow_14);

				log.info("tFilterRow_14 - Processed records count:" + nb_line_tFilterRow_14 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_14 + ". Rejected records count:" + nb_line_reject_tFilterRow_14 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "NoGender", 2, 0,
						"tMap_1", "tMap_1", "tMap", "tFilterRow_14", "tFilterRow_14", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_14 - " + ("Done."));

				ok_Hash.put("tFilterRow_14", true);
				end_Hash.put("tFilterRow_14", System.currentTimeMillis());

				/**
				 * [tFilterRow_14 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				s(currentComponent = "tFileOutputDelimited_1");

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				log.debug("tFileOutputDelimited_1 - Written records count: " + nb_line_tFileOutputDelimited_1 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row6", 2, 0,
						"tFilterRow_14", "tFilterRow_14", "tFilterRow", "tFileOutputDelimited_1",
						"tFileOutputDelimited_1", "tFileOutputDelimited", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tFilterRow_10 end ] start
				 */

				s(currentComponent = "tFilterRow_10");

				globalMap.put("tFilterRow_10_NB_LINE", nb_line_tFilterRow_10);
				globalMap.put("tFilterRow_10_NB_LINE_OK", nb_line_ok_tFilterRow_10);
				globalMap.put("tFilterRow_10_NB_LINE_REJECT", nb_line_reject_tFilterRow_10);

				log.info("tFilterRow_10 - Processed records count:" + nb_line_tFilterRow_10 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_10 + ". Rejected records count:" + nb_line_reject_tFilterRow_10 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "NoState", 2, 0,
						"tMap_1", "tMap_1", "tMap", "tFilterRow_10", "tFilterRow_10", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_10 - " + ("Done."));

				ok_Hash.put("tFilterRow_10", true);
				end_Hash.put("tFilterRow_10", System.currentTimeMillis());

				/**
				 * [tFilterRow_10 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 end ] start
				 */

				s(currentComponent = "tFileOutputDelimited_2");

				if (outtFileOutputDelimited_2 != null) {
					outtFileOutputDelimited_2.flush();
					outtFileOutputDelimited_2.close();
				}

				globalMap.put("tFileOutputDelimited_2_NB_LINE", nb_line_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);

				resourceMap.put("finish_tFileOutputDelimited_2", true);

				log.debug("tFileOutputDelimited_2 - Written records count: " + nb_line_tFileOutputDelimited_2 + " .");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row8", 2, 0,
						"tFilterRow_10", "tFilterRow_10", "tFilterRow", "tFileOutputDelimited_2",
						"tFileOutputDelimited_2", "tFileOutputDelimited", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFileOutputDelimited_2 - " + ("Done."));

				ok_Hash.put("tFileOutputDelimited_2", true);
				end_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row3");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row12");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row2");

			// free memory for "tMap_1"
			globalMap.remove("tHash_Lookup_row5");

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				s(currentComponent = "tFileInputExcel_1");

				cLabel = "Customer_Data";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				s(currentComponent = "tMap_1");

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFilterRow_18 finally ] start
				 */

				s(currentComponent = "tFilterRow_18");

				/**
				 * [tFilterRow_18 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 finally ] start
				 */

				s(currentComponent = "tFileOutputDelimited_3");

				if (resourceMap.get("finish_tFileOutputDelimited_3") == null) {

					java.io.Writer outtFileOutputDelimited_3 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_3");
					if (outtFileOutputDelimited_3 != null) {
						outtFileOutputDelimited_3.flush();
						outtFileOutputDelimited_3.close();
					}

				}

				/**
				 * [tFileOutputDelimited_3 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				s(currentComponent = "tLogRow_1");

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tFilterRow_14 finally ] start
				 */

				s(currentComponent = "tFilterRow_14");

				/**
				 * [tFilterRow_14 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				s(currentComponent = "tFileOutputDelimited_1");

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tFilterRow_10 finally ] start
				 */

				s(currentComponent = "tFilterRow_10");

				/**
				 * [tFilterRow_10 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 finally ] start
				 */

				s(currentComponent = "tFileOutputDelimited_2");

				if (resourceMap.get("finish_tFileOutputDelimited_2") == null) {

					java.io.Writer outtFileOutputDelimited_2 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_2");
					if (outtFileOutputDelimited_2 != null) {
						outtFileOutputDelimited_2.flush();
						outtFileOutputDelimited_2.close();
					}

				}

				/**
				 * [tFileOutputDelimited_2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public static class row3Struct implements routines.system.IPersistableComparableLookupRow<row3Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String stateshort;

		public String getStateshort() {
			return this.stateshort;
		}

		public Boolean stateshortIsNullable() {
			return true;
		}

		public Boolean stateshortIsKey() {
			return false;
		}

		public Integer stateshortLength() {
			return 2;
		}

		public Integer stateshortPrecision() {
			return 0;
		}

		public String stateshortDefault() {

			return null;

		}

		public String stateshortComment() {

			return "";

		}

		public String stateshortPattern() {

			return "dd-MM-yyyy";

		}

		public String stateshortOriginalDbColumnName() {

			return "stateshort";

		}

		public String statefull;

		public String getStatefull() {
			return this.statefull;
		}

		public Boolean statefullIsNullable() {
			return true;
		}

		public Boolean statefullIsKey() {
			return false;
		}

		public Integer statefullLength() {
			return 20;
		}

		public Integer statefullPrecision() {
			return 0;
		}

		public String statefullDefault() {

			return null;

		}

		public String statefullComment() {

			return "";

		}

		public String statefullPattern() {

			return "dd-MM-yyyy";

		}

		public String statefullOriginalDbColumnName() {

			return "statefull";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.stateshort == null) ? 0 : this.stateshort.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row3Struct other = (row3Struct) obj;

			if (this.stateshort == null) {
				if (other.stateshort != null)
					return false;

			} else if (!this.stateshort.equals(other.stateshort))

				return false;

			return true;
		}

		public void copyDataTo(row3Struct other) {

			other.stateshort = this.stateshort;
			other.statefull = this.statefull;

		}

		public void copyKeysDataTo(row3Struct other) {

			other.stateshort = this.stateshort;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.stateshort = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.stateshort = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.stateshort, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.stateshort, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.statefull = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.statefull = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.statefull, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.statefull, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("stateshort=" + stateshort);
			sb.append(",statefull=" + statefull);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (stateshort == null) {
				sb.append("<null>");
			} else {
				sb.append(stateshort);
			}

			sb.append("|");

			if (statefull == null) {
				sb.append("<null>");
			} else {
				sb.append(statefull);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.stateshort, other.stateshort);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_2", "hWOnP7_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row3Struct row3 = new row3Struct();

				/**
				 * [tAdvancedHash_row3 begin ] start
				 */

				sh("tAdvancedHash_row3");

				s(currentComponent = "tAdvancedHash_row3");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

				int tos_count_tAdvancedHash_row3 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row3", "tAdvancedHash_row3", "tAdvancedHash");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				// connection name:row3
				// source node:tFileInputDelimited_2 - inputs:(after_tFileInputExcel_1)
				// outputs:(row3,row3) | target node:tAdvancedHash_row3 - inputs:(row3)
				// outputs:()
				// linked node: tMap_1 - inputs:(row1,row3,row12,row2,row5)
				// outputs:(out1,NoGender,NoState)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row3 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row3Struct> tHash_Lookup_row3 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row3Struct>getLookup(matchingModeEnum_row3);

				globalMap.put("tHash_Lookup_row3", tHash_Lookup_row3);

				/**
				 * [tAdvancedHash_row3 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				sh("tFileInputDelimited_2");

				s(currentComponent = "tFileInputDelimited_2");

				cLabel = "state";

				int tos_count_tFileInputDelimited_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_2 = new StringBuilder();
							log4jParamters_tFileInputDelimited_2.append("Parameters:");
							log4jParamters_tFileInputDelimited_2.append("USE_EXISTING_DYNAMIC" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Downloads/DI/States.txt\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("FIELDSEPARATOR" + " = " + "\";\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("HEADER" + " = " + "0");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("REMOVE_EMPTY_ROW" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append(
									"TRIMSELECT" + " = " + "[{TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("stateshort")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("statefull") + "}]");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ENCODING" + " = " + "\"US-ASCII\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("USE_HEADER_AS_IS" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_2 - " + (log4jParamters_tFileInputDelimited_2));
						}
					}
					new BytesLimit65535_tFileInputDelimited_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_2", "state", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "/Users/abuzaki/Downloads/DI/States.txt";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"/Users/abuzaki/Downloads/DI/States.txt", "US-ASCII", ";", "\n", false, 0, 0,
								limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_2 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_2 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row3 = null;

						row3 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row3 = new row3Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							columnIndexWithD_tFileInputDelimited_2 = 0;

							row3.stateshort = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 1;

							row3.statefull = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							log.error("tFileInputDelimited_2 - " + e.getMessage());

							System.err.println(e.getMessage());
							row3 = null;

						}

						log.debug("tFileInputDelimited_2 - Retrieving the record "
								+ fid_tFileInputDelimited_2.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "state";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "state";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */

// Start of branch "row3"
						if (row3 != null) {

							/**
							 * [tAdvancedHash_row3 main ] start
							 */

							s(currentComponent = "tAdvancedHash_row3");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row3", "tFileInputDelimited_2", "state", "tFileInputDelimited",
									"tAdvancedHash_row3", "tAdvancedHash_row3", "tAdvancedHash"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
							}

							row3Struct row3_HashRow = new row3Struct();

							row3_HashRow.stateshort = row3.stateshort;

							row3_HashRow.statefull = row3.statefull;

							tHash_Lookup_row3.put(row3_HashRow);

							tos_count_tAdvancedHash_row3++;

							/**
							 * [tAdvancedHash_row3 main ] stop
							 */

							/**
							 * [tAdvancedHash_row3 process_data_begin ] start
							 */

							s(currentComponent = "tAdvancedHash_row3");

							/**
							 * [tAdvancedHash_row3 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row3 process_data_end ] start
							 */

							s(currentComponent = "tAdvancedHash_row3");

							/**
							 * [tAdvancedHash_row3 process_data_end ] stop
							 */

						} // End of branch "row3"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "state";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "state";

					}
				} finally {
					if (!((Object) ("/Users/abuzaki/Downloads/DI/States.txt") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

						log.info("tFileInputDelimited_2 - Retrieved records count: "
								+ fid_tFileInputDelimited_2.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_2 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_row3 end ] start
				 */

				s(currentComponent = "tAdvancedHash_row3");

				tHash_Lookup_row3.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tFileInputDelimited_2", "state", "tFileInputDelimited", "tAdvancedHash_row3",
						"tAdvancedHash_row3", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row3", true);
				end_Hash.put("tAdvancedHash_row3", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row3 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_2");

				cLabel = "state";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row3 finally ] start
				 */

				s(currentComponent = "tAdvancedHash_row3");

				/**
				 * [tAdvancedHash_row3 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public static class row12Struct implements routines.system.IPersistableComparableLookupRow<row12Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 8;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public java.util.Date DateChurn;

		public java.util.Date getDateChurn() {
			return this.DateChurn;
		}

		public Boolean DateChurnIsNullable() {
			return true;
		}

		public Boolean DateChurnIsKey() {
			return false;
		}

		public Integer DateChurnLength() {
			return 19;
		}

		public Integer DateChurnPrecision() {
			return 0;
		}

		public String DateChurnDefault() {

			return null;

		}

		public String DateChurnComment() {

			return "";

		}

		public String DateChurnPattern() {

			return "yyyy-MM-dd HH:mm:ss";

		}

		public String DateChurnOriginalDbColumnName() {

			return "DateChurn";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.CustomerId == null) ? 0 : this.CustomerId.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row12Struct other = (row12Struct) obj;

			if (this.CustomerId == null) {
				if (other.CustomerId != null)
					return false;

			} else if (!this.CustomerId.equals(other.CustomerId))

				return false;

			return true;
		}

		public void copyDataTo(row12Struct other) {

			other.CustomerId = this.CustomerId;
			other.DateChurn = this.DateChurn;

		}

		public void copyKeysDataTo(row12Struct other) {

			other.CustomerId = this.CustomerId;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.DateChurn = readDate(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.DateChurn = readDate(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeDate(this.DateChurn, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeDate(this.DateChurn, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",DateChurn=" + String.valueOf(DateChurn));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (DateChurn == null) {
				sb.append("<null>");
			} else {
				sb.append(DateChurn);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.CustomerId, other.CustomerId);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_4", "fXj2Hc_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row12Struct row12 = new row12Struct();

				/**
				 * [tAdvancedHash_row12 begin ] start
				 */

				sh("tAdvancedHash_row12");

				s(currentComponent = "tAdvancedHash_row12");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row12");

				int tos_count_tAdvancedHash_row12 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row12", "tAdvancedHash_row12", "tAdvancedHash");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				// connection name:row12
				// source node:tFileInputDelimited_4 - inputs:(after_tFileInputExcel_1)
				// outputs:(row12,row12) | target node:tAdvancedHash_row12 - inputs:(row12)
				// outputs:()
				// linked node: tMap_1 - inputs:(row1,row3,row12,row2,row5)
				// outputs:(out1,NoGender,NoState)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row12 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row12Struct>getLookup(matchingModeEnum_row12);

				globalMap.put("tHash_Lookup_row12", tHash_Lookup_row12);

				/**
				 * [tAdvancedHash_row12 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_4 begin ] start
				 */

				sh("tFileInputDelimited_4");

				s(currentComponent = "tFileInputDelimited_4");

				cLabel = "ChurnData";

				int tos_count_tFileInputDelimited_4 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_4 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_4 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_4 = new StringBuilder();
							log4jParamters_tFileInputDelimited_4.append("Parameters:");
							log4jParamters_tFileInputDelimited_4.append("USE_EXISTING_DYNAMIC" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("FILENAME" + " = "
									+ "\"/Users/abuzaki/Downloads/Churn Data - RapidMiner Data.csv\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("REMOVE_EMPTY_ROW" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("TRIMALL" + " = " + "true");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ENCODING" + " = " + "\"US-ASCII\"");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							log4jParamters_tFileInputDelimited_4.append("USE_HEADER_AS_IS" + " = " + "false");
							log4jParamters_tFileInputDelimited_4.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_4 - " + (log4jParamters_tFileInputDelimited_4));
						}
					}
					new BytesLimit65535_tFileInputDelimited_4().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_4", "ChurnData", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_4 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_4 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4 = null;
				int limit_tFileInputDelimited_4 = -1;
				try {

					Object filename_tFileInputDelimited_4 = "/Users/abuzaki/Downloads/Churn Data - RapidMiner Data.csv";
					if (filename_tFileInputDelimited_4 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_4 = 0, random_value_tFileInputDelimited_4 = -1;
						if (footer_value_tFileInputDelimited_4 > 0 || random_value_tFileInputDelimited_4 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_4 = new org.talend.fileprocess.FileInputDelimited(
								"/Users/abuzaki/Downloads/Churn Data - RapidMiner Data.csv", "US-ASCII", ",", "\n",
								false, 1, 0, limit_tFileInputDelimited_4, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_4 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_4 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_4 != null && fid_tFileInputDelimited_4.nextRecord()) {
						rowstate_tFileInputDelimited_4.reset();

						row12 = null;

						row12 = null;

						boolean whetherReject_tFileInputDelimited_4 = false;
						row12 = new row12Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_4 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_4 = 0;

							row12.CustomerId = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4)
									.trim();

							columnIndexWithD_tFileInputDelimited_4 = 1;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4).trim();
							if (temp.length() > 0) {

								try {

									row12.DateChurn = ParserUtils.parseTo_Date(temp, "yyyy-MM-dd HH:mm:ss");

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"DateChurn", "row12", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								row12.DateChurn = null;

							}

							if (rowstate_tFileInputDelimited_4.getException() != null) {
								throw rowstate_tFileInputDelimited_4.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_4 = true;

							log.error("tFileInputDelimited_4 - " + e.getMessage());

							System.err.println(e.getMessage());
							row12 = null;

						}

						log.debug("tFileInputDelimited_4 - Retrieving the record "
								+ fid_tFileInputDelimited_4.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_4 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_4 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

						cLabel = "ChurnData";

						tos_count_tFileInputDelimited_4++;

						/**
						 * [tFileInputDelimited_4 main ] stop
						 */

						/**
						 * [tFileInputDelimited_4 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

						cLabel = "ChurnData";

						/**
						 * [tFileInputDelimited_4 process_data_begin ] stop
						 */

// Start of branch "row12"
						if (row12 != null) {

							/**
							 * [tAdvancedHash_row12 main ] start
							 */

							s(currentComponent = "tAdvancedHash_row12");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row12", "tFileInputDelimited_4", "ChurnData", "tFileInputDelimited",
									"tAdvancedHash_row12", "tAdvancedHash_row12", "tAdvancedHash"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row12 - " + (row12 == null ? "" : row12.toLogString()));
							}

							row12Struct row12_HashRow = new row12Struct();

							row12_HashRow.CustomerId = row12.CustomerId;

							row12_HashRow.DateChurn = row12.DateChurn;

							tHash_Lookup_row12.put(row12_HashRow);

							tos_count_tAdvancedHash_row12++;

							/**
							 * [tAdvancedHash_row12 main ] stop
							 */

							/**
							 * [tAdvancedHash_row12 process_data_begin ] start
							 */

							s(currentComponent = "tAdvancedHash_row12");

							/**
							 * [tAdvancedHash_row12 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row12 process_data_end ] start
							 */

							s(currentComponent = "tAdvancedHash_row12");

							/**
							 * [tAdvancedHash_row12 process_data_end ] stop
							 */

						} // End of branch "row12"

						/**
						 * [tFileInputDelimited_4 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

						cLabel = "ChurnData";

						/**
						 * [tFileInputDelimited_4 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_4 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_4");

						cLabel = "ChurnData";

					}
				} finally {
					if (!((Object) ("/Users/abuzaki/Downloads/Churn Data - RapidMiner Data.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_4 != null) {
							fid_tFileInputDelimited_4.close();
						}
					}
					if (fid_tFileInputDelimited_4 != null) {
						globalMap.put("tFileInputDelimited_4_NB_LINE", fid_tFileInputDelimited_4.getRowNumber());

						log.info("tFileInputDelimited_4 - Retrieved records count: "
								+ fid_tFileInputDelimited_4.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_4 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_4", true);
				end_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_4 end ] stop
				 */

				/**
				 * [tAdvancedHash_row12 end ] start
				 */

				s(currentComponent = "tAdvancedHash_row12");

				tHash_Lookup_row12.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row12", 2, 0,
						"tFileInputDelimited_4", "ChurnData", "tFileInputDelimited", "tAdvancedHash_row12",
						"tAdvancedHash_row12", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row12", true);
				end_Hash.put("tAdvancedHash_row12", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row12 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_4 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_4");

				cLabel = "ChurnData";

				/**
				 * [tFileInputDelimited_4 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row12 finally ] start
				 */

				s(currentComponent = "tAdvancedHash_row12");

				/**
				 * [tAdvancedHash_row12 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 1);
	}

	public static class row2Struct implements routines.system.IPersistableComparableLookupRow<row2Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Name;

		public String getName() {
			return this.Name;
		}

		public Boolean NameIsNullable() {
			return true;
		}

		public Boolean NameIsKey() {
			return false;
		}

		public Integer NameLength() {
			return null;
		}

		public Integer NamePrecision() {
			return null;
		}

		public String NameDefault() {

			return null;

		}

		public String NameComment() {

			return "";

		}

		public String NamePattern() {

			return "";

		}

		public String NameOriginalDbColumnName() {

			return "Name";

		}

		public String Gender;

		public String getGender() {
			return this.Gender;
		}

		public Boolean GenderIsNullable() {
			return true;
		}

		public Boolean GenderIsKey() {
			return false;
		}

		public Integer GenderLength() {
			return null;
		}

		public Integer GenderPrecision() {
			return null;
		}

		public String GenderDefault() {

			return null;

		}

		public String GenderComment() {

			return "";

		}

		public String GenderPattern() {

			return "";

		}

		public String GenderOriginalDbColumnName() {

			return "Gender";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Name == null) ? 0 : this.Name.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row2Struct other = (row2Struct) obj;

			if (this.Name == null) {
				if (other.Name != null)
					return false;

			} else if (!this.Name.equals(other.Name))

				return false;

			return true;
		}

		public void copyDataTo(row2Struct other) {

			other.Name = this.Name;
			other.Gender = this.Gender;

		}

		public void copyKeysDataTo(row2Struct other) {

			other.Name = this.Name;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.Name = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Name, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.Gender = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Gender = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.Gender, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.Gender, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Name=" + Name);
			sb.append(",Gender=" + Gender);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (Name == null) {
				sb.append("<null>");
			} else {
				sb.append(Name);
			}

			sb.append("|");

			if (Gender == null) {
				sb.append("<null>");
			} else {
				sb.append(Gender);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Name, other.Name);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_3", "iLhhy7_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();

				/**
				 * [tAdvancedHash_row2 begin ] start
				 */

				sh("tAdvancedHash_row2");

				s(currentComponent = "tAdvancedHash_row2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

				int tos_count_tAdvancedHash_row2 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row2", "tAdvancedHash_row2", "tAdvancedHash");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				// connection name:row2
				// source node:tFileInputDelimited_3 - inputs:(after_tFileInputExcel_1)
				// outputs:(row2,row2) | target node:tAdvancedHash_row2 - inputs:(row2)
				// outputs:()
				// linked node: tMap_1 - inputs:(row1,row3,row12,row2,row5)
				// outputs:(out1,NoGender,NoState)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row2 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row2Struct> tHash_Lookup_row2 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row2Struct>getLookup(matchingModeEnum_row2);

				globalMap.put("tHash_Lookup_row2", tHash_Lookup_row2);

				/**
				 * [tAdvancedHash_row2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				sh("tFileInputDelimited_3");

				s(currentComponent = "tFileInputDelimited_3");

				int tos_count_tFileInputDelimited_3 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_3 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_3 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_3 = new StringBuilder();
							log4jParamters_tFileInputDelimited_3.append("Parameters:");
							log4jParamters_tFileInputDelimited_3.append("USE_EXISTING_DYNAMIC" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append(
									"FILENAME" + " = " + "\"/Users/abuzaki/Downloads/DI/Firstname Mapping.csv\"");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("HEADER" + " = " + "0");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("REMOVE_EMPTY_ROW" + " = " + "true");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3
									.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Name")
											+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Gender") + "}]");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("ENCODING" + " = " + "\"ISO-8859-15\"");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("SPLITRECORD" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							log4jParamters_tFileInputDelimited_3.append("USE_HEADER_AS_IS" + " = " + "false");
							log4jParamters_tFileInputDelimited_3.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_3 - " + (log4jParamters_tFileInputDelimited_3));
						}
					}
					new BytesLimit65535_tFileInputDelimited_3().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_3", "tFileInputDelimited_3", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_3 = null;
				int limit_tFileInputDelimited_3 = -1;
				try {

					Object filename_tFileInputDelimited_3 = "/Users/abuzaki/Downloads/DI/Firstname Mapping.csv";
					if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_3 = 0, random_value_tFileInputDelimited_3 = -1;
						if (footer_value_tFileInputDelimited_3 > 0 || random_value_tFileInputDelimited_3 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_3 = new org.talend.fileprocess.FileInputDelimited(
								"/Users/abuzaki/Downloads/DI/Firstname Mapping.csv", "ISO-8859-15", ",", "\n", true, 0,
								0, limit_tFileInputDelimited_3, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_3 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_3 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_3 != null && fid_tFileInputDelimited_3.nextRecord()) {
						rowstate_tFileInputDelimited_3.reset();

						row2 = null;

						row2 = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						row2 = new row2Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_3 = 0;

							columnIndexWithD_tFileInputDelimited_3 = 0;

							row2.Name = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 1;

							row2.Gender = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_3 = true;

							log.error("tFileInputDelimited_3 - " + e.getMessage());

							System.err.println(e.getMessage());
							row2 = null;

						}

						log.debug("tFileInputDelimited_3 - Retrieving the record "
								+ fid_tFileInputDelimited_3.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_3");

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */

						/**
						 * [tFileInputDelimited_3 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_3");

						/**
						 * [tFileInputDelimited_3 process_data_begin ] stop
						 */

// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tAdvancedHash_row2 main ] start
							 */

							s(currentComponent = "tAdvancedHash_row2");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row2", "tFileInputDelimited_3", "tFileInputDelimited_3", "tFileInputDelimited",
									"tAdvancedHash_row2", "tAdvancedHash_row2", "tAdvancedHash"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
							}

							row2Struct row2_HashRow = new row2Struct();

							row2_HashRow.Name = row2.Name;

							row2_HashRow.Gender = row2.Gender;

							tHash_Lookup_row2.put(row2_HashRow);

							tos_count_tAdvancedHash_row2++;

							/**
							 * [tAdvancedHash_row2 main ] stop
							 */

							/**
							 * [tAdvancedHash_row2 process_data_begin ] start
							 */

							s(currentComponent = "tAdvancedHash_row2");

							/**
							 * [tAdvancedHash_row2 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row2 process_data_end ] start
							 */

							s(currentComponent = "tAdvancedHash_row2");

							/**
							 * [tAdvancedHash_row2 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_3 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_3");

						/**
						 * [tFileInputDelimited_3 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_3");

					}
				} finally {
					if (!((Object) ("/Users/abuzaki/Downloads/DI/Firstname Mapping.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_3 != null) {
							fid_tFileInputDelimited_3.close();
						}
					}
					if (fid_tFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE", fid_tFileInputDelimited_3.getRowNumber());

						log.info("tFileInputDelimited_3 - Retrieved records count: "
								+ fid_tFileInputDelimited_3.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_3 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_row2 end ] start
				 */

				s(currentComponent = "tAdvancedHash_row2");

				tHash_Lookup_row2.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
						"tFileInputDelimited_3", "tFileInputDelimited_3", "tFileInputDelimited", "tAdvancedHash_row2",
						"tAdvancedHash_row2", "tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row2", true);
				end_Hash.put("tAdvancedHash_row2", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row2 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_3 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_3");

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row2 finally ] start
				 */

				s(currentComponent = "tAdvancedHash_row2");

				/**
				 * [tAdvancedHash_row2 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
	}

	public static class row5Struct implements routines.system.IPersistableComparableLookupRow<row5Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 8;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public Double YearlyTotal;

		public Double getYearlyTotal() {
			return this.YearlyTotal;
		}

		public Boolean YearlyTotalIsNullable() {
			return true;
		}

		public Boolean YearlyTotalIsKey() {
			return false;
		}

		public Integer YearlyTotalLength() {
			return 11;
		}

		public Integer YearlyTotalPrecision() {
			return 0;
		}

		public String YearlyTotalDefault() {

			return null;

		}

		public String YearlyTotalComment() {

			return "";

		}

		public String YearlyTotalPattern() {

			return "dd-MM-yyyy";

		}

		public String YearlyTotalOriginalDbColumnName() {

			return "YearlyTotal";

		}

		public Integer TransactionYear;

		public Integer getTransactionYear() {
			return this.TransactionYear;
		}

		public Boolean TransactionYearIsNullable() {
			return true;
		}

		public Boolean TransactionYearIsKey() {
			return false;
		}

		public Integer TransactionYearLength() {
			return null;
		}

		public Integer TransactionYearPrecision() {
			return null;
		}

		public String TransactionYearDefault() {

			return null;

		}

		public String TransactionYearComment() {

			return "";

		}

		public String TransactionYearPattern() {

			return "";

		}

		public String TransactionYearOriginalDbColumnName() {

			return "TransactionYear";

		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.CustomerId == null) ? 0 : this.CustomerId.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row5Struct other = (row5Struct) obj;

			if (this.CustomerId == null) {
				if (other.CustomerId != null)
					return false;

			} else if (!this.CustomerId.equals(other.CustomerId))

				return false;

			return true;
		}

		public void copyDataTo(row5Struct other) {

			other.CustomerId = this.CustomerId;
			other.YearlyTotal = this.YearlyTotal;
			other.TransactionYear = this.TransactionYear;

		}

		public void copyKeysDataTo(row5Struct other) {

			other.CustomerId = this.CustomerId;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				length = dis.readByte();
				if (length == -1) {
					this.YearlyTotal = null;
				} else {
					this.YearlyTotal = dis.readDouble();
				}

				this.TransactionYear = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				length = objectIn.readByte();
				if (length == -1) {
					this.YearlyTotal = null;
				} else {
					this.YearlyTotal = objectIn.readDouble();
				}

				this.TransactionYear = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				if (this.YearlyTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.YearlyTotal);
				}

				writeInteger(this.TransactionYear, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				if (this.YearlyTotal == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.YearlyTotal);
				}

				writeInteger(this.TransactionYear, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",YearlyTotal=" + String.valueOf(YearlyTotal));
			sb.append(",TransactionYear=" + String.valueOf(TransactionYear));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (YearlyTotal == null) {
				sb.append("<null>");
			} else {
				sb.append(YearlyTotal);
			}

			sb.append("|");

			if (TransactionYear == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionYear);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.CustomerId, other.CustomerId);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 8;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public Double YearlyTotal;

		public Double getYearlyTotal() {
			return this.YearlyTotal;
		}

		public Boolean YearlyTotalIsNullable() {
			return true;
		}

		public Boolean YearlyTotalIsKey() {
			return false;
		}

		public Integer YearlyTotalLength() {
			return 11;
		}

		public Integer YearlyTotalPrecision() {
			return 0;
		}

		public String YearlyTotalDefault() {

			return null;

		}

		public String YearlyTotalComment() {

			return "";

		}

		public String YearlyTotalPattern() {

			return "dd-MM-yyyy";

		}

		public String YearlyTotalOriginalDbColumnName() {

			return "YearlyTotal";

		}

		public String TransactionYear;

		public String getTransactionYear() {
			return this.TransactionYear;
		}

		public Boolean TransactionYearIsNullable() {
			return true;
		}

		public Boolean TransactionYearIsKey() {
			return false;
		}

		public Integer TransactionYearLength() {
			return null;
		}

		public Integer TransactionYearPrecision() {
			return null;
		}

		public String TransactionYearDefault() {

			return null;

		}

		public String TransactionYearComment() {

			return "";

		}

		public String TransactionYearPattern() {

			return "";

		}

		public String TransactionYearOriginalDbColumnName() {

			return "TransactionYear";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.YearlyTotal = null;
					} else {
						this.YearlyTotal = dis.readDouble();
					}

					this.TransactionYear = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.YearlyTotal = null;
					} else {
						this.YearlyTotal = dis.readDouble();
					}

					this.TransactionYear = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.YearlyTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.YearlyTotal);
				}

				// String

				writeString(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.YearlyTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.YearlyTotal);
				}

				// String

				writeString(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",YearlyTotal=" + String.valueOf(YearlyTotal));
			sb.append(",TransactionYear=" + TransactionYear);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (YearlyTotal == null) {
				sb.append("<null>");
			} else {
				sb.append(YearlyTotal);
			}

			sb.append("|");

			if (TransactionYear == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionYear);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class OnRowsEndStructtAggregateRow_1
			implements routines.system.IPersistableRow<OnRowsEndStructtAggregateRow_1> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 8;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public Double YearlyTotal;

		public Double getYearlyTotal() {
			return this.YearlyTotal;
		}

		public Boolean YearlyTotalIsNullable() {
			return true;
		}

		public Boolean YearlyTotalIsKey() {
			return false;
		}

		public Integer YearlyTotalLength() {
			return 11;
		}

		public Integer YearlyTotalPrecision() {
			return 0;
		}

		public String YearlyTotalDefault() {

			return null;

		}

		public String YearlyTotalComment() {

			return "";

		}

		public String YearlyTotalPattern() {

			return "dd-MM-yyyy";

		}

		public String YearlyTotalOriginalDbColumnName() {

			return "YearlyTotal";

		}

		public String TransactionYear;

		public String getTransactionYear() {
			return this.TransactionYear;
		}

		public Boolean TransactionYearIsNullable() {
			return true;
		}

		public Boolean TransactionYearIsKey() {
			return false;
		}

		public Integer TransactionYearLength() {
			return null;
		}

		public Integer TransactionYearPrecision() {
			return null;
		}

		public String TransactionYearDefault() {

			return null;

		}

		public String TransactionYearComment() {

			return "";

		}

		public String TransactionYearPattern() {

			return "";

		}

		public String TransactionYearOriginalDbColumnName() {

			return "TransactionYear";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.YearlyTotal = null;
					} else {
						this.YearlyTotal = dis.readDouble();
					}

					this.TransactionYear = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.YearlyTotal = null;
					} else {
						this.YearlyTotal = dis.readDouble();
					}

					this.TransactionYear = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.YearlyTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.YearlyTotal);
				}

				// String

				writeString(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.YearlyTotal == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.YearlyTotal);
				}

				// String

				writeString(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",YearlyTotal=" + String.valueOf(YearlyTotal));
			sb.append(",TransactionYear=" + TransactionYear);
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (YearlyTotal == null) {
				sb.append("<null>");
			} else {
				sb.append(YearlyTotal);
			}

			sb.append("|");

			if (TransactionYear == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionYear);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(OnRowsEndStructtAggregateRow_1 other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class TransactionYearStruct implements routines.system.IPersistableRow<TransactionYearStruct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 8;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public Double TransactionValue;

		public Double getTransactionValue() {
			return this.TransactionValue;
		}

		public Boolean TransactionValueIsNullable() {
			return true;
		}

		public Boolean TransactionValueIsKey() {
			return false;
		}

		public Integer TransactionValueLength() {
			return 11;
		}

		public Integer TransactionValuePrecision() {
			return 0;
		}

		public String TransactionValueDefault() {

			return null;

		}

		public String TransactionValueComment() {

			return "";

		}

		public String TransactionValuePattern() {

			return "dd-MM-yyyy";

		}

		public String TransactionValueOriginalDbColumnName() {

			return "TransactionValue";

		}

		public Integer TransactionYear;

		public Integer getTransactionYear() {
			return this.TransactionYear;
		}

		public Boolean TransactionYearIsNullable() {
			return true;
		}

		public Boolean TransactionYearIsKey() {
			return false;
		}

		public Integer TransactionYearLength() {
			return null;
		}

		public Integer TransactionYearPrecision() {
			return null;
		}

		public String TransactionYearDefault() {

			return null;

		}

		public String TransactionYearComment() {

			return "";

		}

		public String TransactionYearPattern() {

			return "";

		}

		public String TransactionYearOriginalDbColumnName() {

			return "TransactionYear";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TransactionValue = null;
					} else {
						this.TransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TransactionValue = null;
					} else {
						this.TransactionValue = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.TransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.TransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TransactionValue);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",TransactionValue=" + String.valueOf(TransactionValue));
			sb.append(",TransactionYear=" + String.valueOf(TransactionYear));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (TransactionValue == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionValue);
			}

			sb.append("|");

			if (TransactionYear == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionYear);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(TransactionYearStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_TELCO_TelcoJob1 = new byte[0];
		static byte[] commonByteArray_TELCO_TelcoJob1 = new byte[0];

		public String CustomerId;

		public String getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 7;
		}

		public Integer CustomerIdPrecision() {
			return 0;
		}

		public String CustomerIdDefault() {

			return null;

		}

		public String CustomerIdComment() {

			return "";

		}

		public String CustomerIdPattern() {

			return "dd-MM-yyyy";

		}

		public String CustomerIdOriginalDbColumnName() {

			return "CustomerId";

		}

		public Double TransactionValue;

		public Double getTransactionValue() {
			return this.TransactionValue;
		}

		public Boolean TransactionValueIsNullable() {
			return true;
		}

		public Boolean TransactionValueIsKey() {
			return false;
		}

		public Integer TransactionValueLength() {
			return null;
		}

		public Integer TransactionValuePrecision() {
			return 0;
		}

		public String TransactionValueDefault() {

			return null;

		}

		public String TransactionValueComment() {

			return "";

		}

		public String TransactionValuePattern() {

			return "dd-MM-yyyy";

		}

		public String TransactionValueOriginalDbColumnName() {

			return "TransactionValue";

		}

		public String PaymentMethod;

		public String getPaymentMethod() {
			return this.PaymentMethod;
		}

		public Boolean PaymentMethodIsNullable() {
			return true;
		}

		public Boolean PaymentMethodIsKey() {
			return false;
		}

		public Integer PaymentMethodLength() {
			return 11;
		}

		public Integer PaymentMethodPrecision() {
			return 0;
		}

		public String PaymentMethodDefault() {

			return null;

		}

		public String PaymentMethodComment() {

			return "";

		}

		public String PaymentMethodPattern() {

			return "dd-MM-yyyy";

		}

		public String PaymentMethodOriginalDbColumnName() {

			return "PaymentMethod";

		}

		public java.util.Date Date;

		public java.util.Date getDate() {
			return this.Date;
		}

		public Boolean DateIsNullable() {
			return true;
		}

		public Boolean DateIsKey() {
			return false;
		}

		public Integer DateLength() {
			return 19;
		}

		public Integer DatePrecision() {
			return 0;
		}

		public String DateDefault() {

			return null;

		}

		public String DateComment() {

			return "";

		}

		public String DatePattern() {

			return "yyyy-MM-dd HH:mm:ss";

		}

		public String DateOriginalDbColumnName() {

			return "Date";

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TELCO_TelcoJob1.length) {
					if (length < 1024 && commonByteArray_TELCO_TelcoJob1.length == 0) {
						commonByteArray_TELCO_TelcoJob1 = new byte[1024];
					} else {
						commonByteArray_TELCO_TelcoJob1 = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_TelcoJob1, 0, length);
				strReturn = new String(commonByteArray_TELCO_TelcoJob1, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TransactionValue = null;
					} else {
						this.TransactionValue = dis.readDouble();
					}

					this.PaymentMethod = readString(dis);

					this.Date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_TelcoJob1) {

				try {

					int length = 0;

					this.CustomerId = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TransactionValue = null;
					} else {
						this.TransactionValue = dis.readDouble();
					}

					this.PaymentMethod = readString(dis);

					this.Date = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.TransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TransactionValue);
				}

				// String

				writeString(this.PaymentMethod, dos);

				// java.util.Date

				writeDate(this.Date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.CustomerId, dos);

				// Double

				if (this.TransactionValue == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TransactionValue);
				}

				// String

				writeString(this.PaymentMethod, dos);

				// java.util.Date

				writeDate(this.Date, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("CustomerId=" + CustomerId);
			sb.append(",TransactionValue=" + String.valueOf(TransactionValue));
			sb.append(",PaymentMethod=" + PaymentMethod);
			sb.append(",Date=" + String.valueOf(Date));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			if (CustomerId == null) {
				sb.append("<null>");
			} else {
				sb.append(CustomerId);
			}

			sb.append("|");

			if (TransactionValue == null) {
				sb.append("<null>");
			} else {
				sb.append(TransactionValue);
			}

			sb.append("|");

			if (PaymentMethod == null) {
				sb.append("<null>");
			} else {
				sb.append(PaymentMethod);
			}

			sb.append("|");

			if (Date == null) {
				sb.append("<null>");
			} else {
				sb.append(Date);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_1", "sbKhSo_");

		String currentVirtualComponent = null;

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();
				TransactionYearStruct TransactionYear = new TransactionYearStruct();
				row11Struct row11 = new row11Struct();
				row5Struct row5 = new row5Struct();

				/**
				 * [tAggregateRow_1_AGGOUT begin ] start
				 */

				sh("tAggregateRow_1_AGGOUT");

				currentVirtualComponent = "tAggregateRow_1";

				s(currentComponent = "tAggregateRow_1_AGGOUT");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "TransactionYear");

				int tos_count_tAggregateRow_1_AGGOUT = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGOUT - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_1_AGGOUT {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_1_AGGOUT = new StringBuilder();
							log4jParamters_tAggregateRow_1_AGGOUT.append("Parameters:");
							log4jParamters_tAggregateRow_1_AGGOUT.append("DESTINATION" + " = " + "tAggregateRow_1");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN="
									+ ("CustomerId") + ", INPUT_COLUMN=" + ("CustomerId") + "}, {OUTPUT_COLUMN="
									+ ("TransactionYear") + ", INPUT_COLUMN=" + ("TransactionYear") + "}]");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("YearlyTotal") + ", INPUT_COLUMN=" + ("TransactionValue") + ", IGNORE_NULL="
									+ ("false") + ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							log4jParamters_tAggregateRow_1_AGGOUT.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGOUT.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_1_AGGOUT - " + (log4jParamters_tAggregateRow_1_AGGOUT));
						}
					}
					new BytesLimit65535_tAggregateRow_1_AGGOUT().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_1_AGGOUT", "tAggregateRow_1_AGGOUT", "tAggregateOut");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

// ------------ Seems it is not used

				java.util.Map hashAggreg_tAggregateRow_1 = new java.util.HashMap();

// ------------

				class UtilClass_tAggregateRow_1 { // G_OutBegin_AggR_144

					public double sd(Double[] data) {
						final int n = data.length;
						if (n < 2) {
							return Double.NaN;
						}
						double d1 = 0d;
						double d2 = 0d;

						for (int i = 0; i < data.length; i++) {
							d1 += (data[i] * data[i]);
							d2 += data[i];
						}

						return Math.sqrt((n * d1 - d2 * d2) / n / (n - 1));
					}

					public void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {
						byte r = (byte) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'short/Short'", "'byte/Byte'"));
						}
					}

					public void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {
						short r = (short) (a + b);
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'int/Integer'", "'short/Short'"));
						}
					}

					public void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {
						int r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'long/Long'", "'int/Integer'"));
						}
					}

					public void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {
						long r = a + b;
						if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'long/Long'"));
						}
					}

					public void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							float minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b),
										"'double' or 'BigDecimal'", "'float/Float'"));
							}
						}

						if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE)
								|| ((double) a + (double) b < (double) -Float.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'double' or 'BigDecimal'", "'float/Float'"));
						}
					}

					public void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, short b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, int b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					public void checkedIADD(double a, float b, boolean checkTypeOverFlow, boolean checkUlp) {

						if (checkUlp) {
							double minAddedValue = Math.ulp(a);
							if (minAddedValue > Math.abs(b)) {
								throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a),
										"'BigDecimal'", "'double/Double'"));
							}
						}

						if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE)) {
							throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b),
									"'BigDecimal'", "'double/Double'"));
						}
					}

					private String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {
						return "Type overflow when adding " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

					private String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {
						return "The double precision is unsufficient to add the value " + b + " to " + a
								+ ", to resolve this problem, increase the precision by using " + advicedTypes
								+ " type in place of " + originalType + ".";
					}

				} // G_OutBegin_AggR_144

				UtilClass_tAggregateRow_1 utilClass_tAggregateRow_1 = new UtilClass_tAggregateRow_1();

				class AggOperationStruct_tAggregateRow_1 { // G_OutBegin_AggR_100

					private static final int DEFAULT_HASHCODE = 1;
					private static final int PRIME = 31;
					private int hashCode = DEFAULT_HASHCODE;
					public boolean hashCodeDirty = true;

					String CustomerId;
					Integer TransactionYear;
					BigDecimal YearlyTotal_sum;

					@Override
					public int hashCode() {
						if (this.hashCodeDirty) {
							final int prime = PRIME;
							int result = DEFAULT_HASHCODE;

							result = prime * result + ((this.CustomerId == null) ? 0 : this.CustomerId.hashCode());

							result = prime * result
									+ ((this.TransactionYear == null) ? 0 : this.TransactionYear.hashCode());

							this.hashCode = result;
							this.hashCodeDirty = false;
						}
						return this.hashCode;
					}

					@Override
					public boolean equals(Object obj) {
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						final AggOperationStruct_tAggregateRow_1 other = (AggOperationStruct_tAggregateRow_1) obj;

						if (this.CustomerId == null) {
							if (other.CustomerId != null)
								return false;
						} else if (!this.CustomerId.equals(other.CustomerId))
							return false;

						if (this.TransactionYear == null) {
							if (other.TransactionYear != null)
								return false;
						} else if (!this.TransactionYear.equals(other.TransactionYear))
							return false;

						return true;
					}

				} // G_OutBegin_AggR_100

				AggOperationStruct_tAggregateRow_1 operation_result_tAggregateRow_1 = null;
				AggOperationStruct_tAggregateRow_1 operation_finder_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();
				java.util.Map<AggOperationStruct_tAggregateRow_1, AggOperationStruct_tAggregateRow_1> hash_tAggregateRow_1 = new java.util.HashMap<AggOperationStruct_tAggregateRow_1, AggOperationStruct_tAggregateRow_1>();

				/**
				 * [tAggregateRow_1_AGGOUT begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				sh("tMap_2");

				s(currentComponent = "tMap_2");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row4");

				int tos_count_tMap_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tMap_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tMap_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tMap_2 = new StringBuilder();
							log4jParamters_tMap_2.append("Parameters:");
							log4jParamters_tMap_2.append("LINK_STYLE" + " = " + "AUTO");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("TEMPORARY_DATA_DIRECTORY" + " = " + "");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("ROWS_BUFFER_SIZE" + " = " + "2000000");
							log4jParamters_tMap_2.append(" | ");
							log4jParamters_tMap_2.append("CHANGE_HASH_AND_EQUALS_FOR_BIGDECIMAL" + " = " + "true");
							log4jParamters_tMap_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tMap_2 - " + (log4jParamters_tMap_2));
						}
					}
					new BytesLimit65535_tMap_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tMap_2", "tMap_2", "tMap");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

// ###############################
// # Lookup's keys initialization
				int count_row4_tMap_2 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
					int var1;
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_TransactionYear_tMap_2 = 0;

				TransactionYearStruct TransactionYear_tmp = new TransactionYearStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				sh("tFileInputDelimited_1");

				s(currentComponent = "tFileInputDelimited_1");

				cLabel = "TransactionData1";

				int tos_count_tFileInputDelimited_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFileInputDelimited_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFileInputDelimited_1 = new StringBuilder();
							log4jParamters_tFileInputDelimited_1.append("Parameters:");
							log4jParamters_tFileInputDelimited_1.append("USE_EXISTING_DYNAMIC" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Downloads/Transaction Data.csv\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CSV_OPTION" + " = " + "true");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CSVROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ESCAPE_CHAR" + " = " + "\"\"\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TEXT_ENCLOSURE" + " = " + "\"\"\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("HEADER" + " = " + "1");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FOOTER" + " = " + "0");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("LIMIT" + " = " + "");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("REMOVE_EMPTY_ROW" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("UNCOMPRESS" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "true");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("THOUSANDS_SEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("DECIMAL_SEPARATOR" + " = " + "\".\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append(
									"TRIMSELECT" + " = " + "[{TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("CustomerId")
											+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("TransactionValue")
											+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("PaymentMethod")
											+ "}, {TRIM=" + ("true") + ", SCHEMA_COLUMN=" + ("Date") + "}]");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENCODING" + " = " + "\"US-ASCII\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENABLE_DECODE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("USE_HEADER_AS_IS" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFileInputDelimited_1 - " + (log4jParamters_tFileInputDelimited_1));
						}
					}
					new BytesLimit65535_tFileInputDelimited_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFileInputDelimited_1", "TransactionData1", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				int footer_tFileInputDelimited_1 = 0;
				int totalLinetFileInputDelimited_1 = 0;
				int limittFileInputDelimited_1 = -1;
				int lastLinetFileInputDelimited_1 = -1;

				char fieldSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_1 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_1 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_1 = /** Start field tFileInputDelimited_1:FILENAME */
						"/Users/abuzaki/Downloads/Transaction Data.csv"/** End field tFileInputDelimited_1:FILENAME */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_1 = null;

				try {

					String[] rowtFileInputDelimited_1 = null;
					int currentLinetFileInputDelimited_1 = 0;
					int outputLinetFileInputDelimited_1 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_1 = 0;
							if (footer_value_tFileInputDelimited_1 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_1,
									fieldSeparator_tFileInputDelimited_1[0], "US-ASCII");
						} else {
							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_1),
									fieldSeparator_tFileInputDelimited_1[0], "US-ASCII");
						}

						csvReadertFileInputDelimited_1.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
							csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

						csvReadertFileInputDelimited_1.setQuoteChar('"');

						csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						if (footer_tFileInputDelimited_1 > 0) {
							for (totalLinetFileInputDelimited_1 = 0; totalLinetFileInputDelimited_1 < 1; totalLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
							csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_1.readNext()) {

								totalLinetFileInputDelimited_1++;

							}
							int lastLineTemptFileInputDelimited_1 = totalLinetFileInputDelimited_1
									- footer_tFileInputDelimited_1 < 0 ? 0
											: totalLinetFileInputDelimited_1 - footer_tFileInputDelimited_1;
							if (lastLinetFileInputDelimited_1 > 0) {
								lastLinetFileInputDelimited_1 = lastLinetFileInputDelimited_1 < lastLineTemptFileInputDelimited_1
										? lastLinetFileInputDelimited_1
										: lastLineTemptFileInputDelimited_1;
							} else {
								lastLinetFileInputDelimited_1 = lastLineTemptFileInputDelimited_1;
							}

							csvReadertFileInputDelimited_1.close();
							if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_1,
										fieldSeparator_tFileInputDelimited_1[0], "US-ASCII");
							} else {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_1),
										fieldSeparator_tFileInputDelimited_1[0], "US-ASCII");
							}
							csvReadertFileInputDelimited_1.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
								csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

							csvReadertFileInputDelimited_1.setQuoteChar('"');

							csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						}

						if (limittFileInputDelimited_1 != 0) {
							for (currentLinetFileInputDelimited_1 = 0; currentLinetFileInputDelimited_1 < 1; currentLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
						}
						csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_1 - " + e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					log.info("tFileInputDelimited_1 - Retrieving records from the datasource.");

					while (limittFileInputDelimited_1 != 0 && csvReadertFileInputDelimited_1 != null
							&& csvReadertFileInputDelimited_1.readNext()) {
						rowstate_tFileInputDelimited_1.reset();

						rowtFileInputDelimited_1 = csvReadertFileInputDelimited_1.getValues();

						currentLinetFileInputDelimited_1++;

						if (lastLinetFileInputDelimited_1 > -1
								&& currentLinetFileInputDelimited_1 > lastLinetFileInputDelimited_1) {
							break;
						}
						outputLinetFileInputDelimited_1++;
						if (limittFileInputDelimited_1 > 0
								&& outputLinetFileInputDelimited_1 > limittFileInputDelimited_1) {
							break;
						}

						row4 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row4 = new row4Struct();
						try {

							char fieldSeparator_tFileInputDelimited_1_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_1_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_1.length == 1 && ("\015").equals(rowtFileInputDelimited_1[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row4.CustomerId = null;

								row4.TransactionValue = null;

								row4.PaymentMethod = null;

								row4.Date = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_1 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_1 = 0;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1] = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]
											.trim();

									row4.CustomerId = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row4.CustomerId = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 1;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1] = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]
											.trim();

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row4.TransactionValue = ParserUtils
													.parseTo_Double(ParserUtils.parseTo_Number(
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															',', '.'));

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"TransactionValue", "row4",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row4.TransactionValue = null;

									}

								} else {

									row4.TransactionValue = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 2;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1] = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]
											.trim();

									row4.PaymentMethod = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row4.PaymentMethod = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 3;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1] = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]
											.trim();

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row4.Date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
													"yyyy-MM-dd HH:mm:ss");

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Date", "row4",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row4.Date = null;

									}

								} else {

									row4.Date = null;

								}

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							log.error("tFileInputDelimited_1 - " + e.getMessage());

							System.err.println(e.getMessage());
							row4 = null;

							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						}

						log.debug("tFileInputDelimited_1 - Retrieving the record " + (nb_line_tFileInputDelimited_1 + 1)
								+ ".");

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "TransactionData1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "TransactionData1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */

// Start of branch "row4"
						if (row4 != null) {

							/**
							 * [tMap_2 main ] start
							 */

							s(currentComponent = "tMap_2");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row4", "tFileInputDelimited_1", "TransactionData1", "tFileInputDelimited",
									"tMap_2", "tMap_2", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_2 = false;
							boolean mainRowRejected_tMap_2 = false;
							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_2__Struct Var = Var__tMap_2;
								Var.var1 = TalendDate.getPartOfDate("yyyy", row4.Date);// ###############################
								// ###############################
								// # Output tables

								TransactionYear = null;

// # Output table : 'TransactionYear'
								count_TransactionYear_tMap_2++;

								TransactionYear_tmp.CustomerId = row4.CustomerId;
								TransactionYear_tmp.TransactionValue = row4.TransactionValue;
								TransactionYear_tmp.TransactionYear = Var.var1;
								TransactionYear = TransactionYear_tmp;
								log.debug("tMap_2 - Outputting the record " + count_TransactionYear_tMap_2
										+ " of the output table 'TransactionYear'.");

// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_2 = false;

							tos_count_tMap_2++;

							/**
							 * [tMap_2 main ] stop
							 */

							/**
							 * [tMap_2 process_data_begin ] start
							 */

							s(currentComponent = "tMap_2");

							/**
							 * [tMap_2 process_data_begin ] stop
							 */

// Start of branch "TransactionYear"
							if (TransactionYear != null) {

								/**
								 * [tAggregateRow_1_AGGOUT main ] start
								 */

								currentVirtualComponent = "tAggregateRow_1";

								s(currentComponent = "tAggregateRow_1_AGGOUT");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "TransactionYear", "tMap_2", "tMap_2", "tMap", "tAggregateRow_1_AGGOUT",
										"tAggregateRow_1_AGGOUT", "tAggregateOut"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("TransactionYear - "
											+ (TransactionYear == null ? "" : TransactionYear.toLogString()));
								}

								operation_finder_tAggregateRow_1.CustomerId = TransactionYear.CustomerId;
								operation_finder_tAggregateRow_1.TransactionYear = TransactionYear.TransactionYear;

								operation_finder_tAggregateRow_1.hashCodeDirty = true;

								operation_result_tAggregateRow_1 = hash_tAggregateRow_1
										.get(operation_finder_tAggregateRow_1);

								if (operation_result_tAggregateRow_1 == null) { // G_OutMain_AggR_001

									operation_result_tAggregateRow_1 = new AggOperationStruct_tAggregateRow_1();

									operation_result_tAggregateRow_1.CustomerId = operation_finder_tAggregateRow_1.CustomerId;
									operation_result_tAggregateRow_1.TransactionYear = operation_finder_tAggregateRow_1.TransactionYear;

									hash_tAggregateRow_1.put(operation_result_tAggregateRow_1,
											operation_result_tAggregateRow_1);

								} // G_OutMain_AggR_001

								if (operation_result_tAggregateRow_1.YearlyTotal_sum == null) {
									operation_result_tAggregateRow_1.YearlyTotal_sum = new BigDecimal(0).setScale(0);
								}
								operation_result_tAggregateRow_1.YearlyTotal_sum = operation_result_tAggregateRow_1.YearlyTotal_sum
										.add(new BigDecimal(String.valueOf(TransactionYear.TransactionValue)));

								tos_count_tAggregateRow_1_AGGOUT++;

								/**
								 * [tAggregateRow_1_AGGOUT main ] stop
								 */

								/**
								 * [tAggregateRow_1_AGGOUT process_data_begin ] start
								 */

								currentVirtualComponent = "tAggregateRow_1";

								s(currentComponent = "tAggregateRow_1_AGGOUT");

								/**
								 * [tAggregateRow_1_AGGOUT process_data_begin ] stop
								 */

								/**
								 * [tAggregateRow_1_AGGOUT process_data_end ] start
								 */

								currentVirtualComponent = "tAggregateRow_1";

								s(currentComponent = "tAggregateRow_1_AGGOUT");

								/**
								 * [tAggregateRow_1_AGGOUT process_data_end ] stop
								 */

							} // End of branch "TransactionYear"

							/**
							 * [tMap_2 process_data_end ] start
							 */

							s(currentComponent = "tMap_2");

							/**
							 * [tMap_2 process_data_end ] stop
							 */

						} // End of branch "row4"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "TransactionData1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "TransactionData1";

						nb_line_tFileInputDelimited_1++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_1 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_1 != null) {
							csvReadertFileInputDelimited_1.close();
						}
					}
					if (csvReadertFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", nb_line_tFileInputDelimited_1);
					}

					log.info("tFileInputDelimited_1 - Retrieved records count: " + nb_line_tFileInputDelimited_1 + ".");

				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				s(currentComponent = "tMap_2");

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug("tMap_2 - Written records count in the table 'TransactionYear': "
						+ count_TransactionYear_tMap_2 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
						"tFileInputDelimited_1", "TransactionData1", "tFileInputDelimited", "tMap_2", "tMap_2", "tMap",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tMap_2 - " + ("Done."));

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGOUT end ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				s(currentComponent = "tAggregateRow_1_AGGOUT");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "TransactionYear", 2, 0,
						"tMap_2", "tMap_2", "tMap", "tAggregateRow_1_AGGOUT", "tAggregateRow_1_AGGOUT", "tAggregateOut",
						"output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGOUT - " + ("Done."));

				ok_Hash.put("tAggregateRow_1_AGGOUT", true);
				end_Hash.put("tAggregateRow_1_AGGOUT", System.currentTimeMillis());

				/**
				 * [tAggregateRow_1_AGGOUT end ] stop
				 */

				/**
				 * [tAdvancedHash_row5 begin ] start
				 */

				sh("tAdvancedHash_row5");

				s(currentComponent = "tAdvancedHash_row5");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row5");

				int tos_count_tAdvancedHash_row5 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row5", "tAdvancedHash_row5", "tAdvancedHash");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				// connection name:row5
				// source node:tFilterRow_22 - inputs:(row11) outputs:(row5,row5) | target
				// node:tAdvancedHash_row5 - inputs:(row5) outputs:()
				// linked node: tMap_1 - inputs:(row1,row3,row12,row2,row5)
				// outputs:(out1,NoGender,NoState)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct>getLookup(matchingModeEnum_row5);

				globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);

				/**
				 * [tAdvancedHash_row5 begin ] stop
				 */

				/**
				 * [tFilterRow_22 begin ] start
				 */

				sh("tFilterRow_22");

				s(currentComponent = "tFilterRow_22");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row11");

				int tos_count_tFilterRow_22 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_22 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_22 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_22 = new StringBuilder();
							log4jParamters_tFilterRow_22.append("Parameters:");
							log4jParamters_tFilterRow_22.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_22.append(" | ");
							log4jParamters_tFilterRow_22.append("CONDITIONS" + " = " + "[]");
							log4jParamters_tFilterRow_22.append(" | ");
							log4jParamters_tFilterRow_22.append("USE_ADVANCED" + " = " + "true");
							log4jParamters_tFilterRow_22.append(" | ");
							log4jParamters_tFilterRow_22.append("ADVANCED_COND" + " = "
									+ "\"All\".equalsIgnoreCase((String)globalMap.get(\"targetYear\")) || (row11.TransactionYear != null     && row11.TransactionYear.equals((String)globalMap.get(\"targetYear\")))");
							log4jParamters_tFilterRow_22.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_22 - " + (log4jParamters_tFilterRow_22));
						}
					}
					new BytesLimit65535_tFilterRow_22().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_22", "tFilterRow_22", "tFilterRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tFilterRow_22 = 0;
				int nb_line_ok_tFilterRow_22 = 0;
				int nb_line_reject_tFilterRow_22 = 0;

				class Operator_tFilterRow_22 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_22(String unionFlag) {
						sUnionFlag = unionFlag;
						bMatchFlag = "||".equals(unionFlag) ? false : true;
					}

					public String getErrorMsg() {
						if (sErrorMsg != null && sErrorMsg.length() > 1)
							return sErrorMsg.substring(1);
						else
							return null;
					}

					public boolean getMatchFlag() {
						return bMatchFlag;
					}

					public void matches(boolean partMatched, String reason) {
						// no need to care about the next judgement
						if ("||".equals(sUnionFlag) && bMatchFlag) {
							return;
						}

						if (!partMatched) {
							sErrorMsg += "|" + reason;
						}

						if ("||".equals(sUnionFlag))
							bMatchFlag = bMatchFlag || partMatched;
						else
							bMatchFlag = bMatchFlag && partMatched;
					}
				}

				/**
				 * [tFilterRow_22 begin ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGIN begin ] start
				 */

				sh("tAggregateRow_1_AGGIN");

				currentVirtualComponent = "tAggregateRow_1";

				s(currentComponent = "tAggregateRow_1_AGGIN");

				int tos_count_tAggregateRow_1_AGGIN = 0;

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGIN - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tAggregateRow_1_AGGIN {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tAggregateRow_1_AGGIN = new StringBuilder();
							log4jParamters_tAggregateRow_1_AGGIN.append("Parameters:");
							log4jParamters_tAggregateRow_1_AGGIN.append("ORIGIN" + " = " + "tAggregateRow_1");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("GROUPBYS" + " = " + "[{OUTPUT_COLUMN="
									+ ("CustomerId") + ", INPUT_COLUMN=" + ("CustomerId") + "}, {OUTPUT_COLUMN="
									+ ("TransactionYear") + ", INPUT_COLUMN=" + ("TransactionYear") + "}]");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("OPERATIONS" + " = " + "[{OUTPUT_COLUMN="
									+ ("YearlyTotal") + ", INPUT_COLUMN=" + ("TransactionValue") + ", IGNORE_NULL="
									+ ("false") + ", FUNCTION=" + ("sum") + "}]");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("LIST_DELIMITER" + " = " + "\",\"");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("USE_FINANCIAL_PRECISION" + " = " + "true");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("CHECK_TYPE_OVERFLOW" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							log4jParamters_tAggregateRow_1_AGGIN.append("CHECK_ULP" + " = " + "false");
							log4jParamters_tAggregateRow_1_AGGIN.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tAggregateRow_1_AGGIN - " + (log4jParamters_tAggregateRow_1_AGGIN));
						}
					}
					new BytesLimit65535_tAggregateRow_1_AGGIN().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				java.util.Collection<AggOperationStruct_tAggregateRow_1> values_tAggregateRow_1 = hash_tAggregateRow_1
						.values();

				globalMap.put("tAggregateRow_1_NB_LINE", values_tAggregateRow_1.size());

				if (log.isInfoEnabled())
					log.info("tAggregateRow_1_AGGIN - " + ("Retrieving the aggregation results."));
				for (AggOperationStruct_tAggregateRow_1 aggregated_row_tAggregateRow_1 : values_tAggregateRow_1) { // G_AggR_600

					/**
					 * [tAggregateRow_1_AGGIN begin ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN main ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					s(currentComponent = "tAggregateRow_1_AGGIN");

					row11.CustomerId = aggregated_row_tAggregateRow_1.CustomerId;

					if (aggregated_row_tAggregateRow_1.YearlyTotal_sum != null) {
						row11.YearlyTotal = aggregated_row_tAggregateRow_1.YearlyTotal_sum.doubleValue();

					} else {

						row11.YearlyTotal = null;

					}

					if (log.isDebugEnabled())
						log.debug("tAggregateRow_1_AGGIN - "
								+ ("Operation function: 'sum' on the column 'TransactionValue'."));
					String s_TransactionYear_TransactionYear_tAggregateRow_1 = String
							.valueOf(aggregated_row_tAggregateRow_1.TransactionYear);
					row11.TransactionYear = s_TransactionYear_TransactionYear_tAggregateRow_1;

					tos_count_tAggregateRow_1_AGGIN++;

					/**
					 * [tAggregateRow_1_AGGIN main ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN process_data_begin ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					s(currentComponent = "tAggregateRow_1_AGGIN");

					/**
					 * [tAggregateRow_1_AGGIN process_data_begin ] stop
					 */

					/**
					 * [tFilterRow_22 main ] start
					 */

					s(currentComponent = "tFilterRow_22");

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row11", "tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn",
							"tFilterRow_22", "tFilterRow_22", "tFilterRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row11 - " + (row11 == null ? "" : row11.toLogString()));
					}

					row5 = null;
					row5 = null;
					Operator_tFilterRow_22 ope_tFilterRow_22 = new Operator_tFilterRow_22("&&");
					ope_tFilterRow_22.matches(
							("All".equalsIgnoreCase((String) globalMap.get("targetYear"))
									|| (row11.TransactionYear != null
											&& row11.TransactionYear.equals((String) globalMap.get("targetYear")))),
							"advanced condition failed");

					if (ope_tFilterRow_22.getMatchFlag()) {
						if (row5 == null) {
							row5 = new row5Struct();
						}
						row5.CustomerId = row11.CustomerId;
						row5.YearlyTotal = row11.YearlyTotal;
						row5.TransactionYear = row11.TransactionYear;
						log.debug("tFilterRow_22 - Process the record " + (nb_line_tFilterRow_22 + 1) + ".");

						if (row5 == null) {
							row5 = new row5Struct();
						}
						row5.CustomerId = row11.CustomerId;
						row5.YearlyTotal = row11.YearlyTotal;
						row5.TransactionYear = row11.TransactionYear;
						log.debug("tFilterRow_22 - Process the record " + (nb_line_tFilterRow_22 + 1) + ".");

						nb_line_ok_tFilterRow_22++;
					} else {
						nb_line_reject_tFilterRow_22++;
					}

					nb_line_tFilterRow_22++;

					tos_count_tFilterRow_22++;

					/**
					 * [tFilterRow_22 main ] stop
					 */

					/**
					 * [tFilterRow_22 process_data_begin ] start
					 */

					s(currentComponent = "tFilterRow_22");

					/**
					 * [tFilterRow_22 process_data_begin ] stop
					 */

// Start of branch "row5"
					if (row5 != null) {

						/**
						 * [tAdvancedHash_row5 main ] start
						 */

						s(currentComponent = "tAdvancedHash_row5");

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row5", "tFilterRow_22", "tFilterRow_22", "tFilterRow", "tAdvancedHash_row5",
								"tAdvancedHash_row5", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row5 - " + (row5 == null ? "" : row5.toLogString()));
						}

						row5Struct row5_HashRow = new row5Struct();

						row5_HashRow.CustomerId = row5.CustomerId;

						row5_HashRow.YearlyTotal = row5.YearlyTotal;

						row5_HashRow.TransactionYear = row5.TransactionYear;

						tHash_Lookup_row5.put(row5_HashRow);

						tos_count_tAdvancedHash_row5++;

						/**
						 * [tAdvancedHash_row5 main ] stop
						 */

						/**
						 * [tAdvancedHash_row5 process_data_begin ] start
						 */

						s(currentComponent = "tAdvancedHash_row5");

						/**
						 * [tAdvancedHash_row5 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row5 process_data_end ] start
						 */

						s(currentComponent = "tAdvancedHash_row5");

						/**
						 * [tAdvancedHash_row5 process_data_end ] stop
						 */

					} // End of branch "row5"

					/**
					 * [tFilterRow_22 process_data_end ] start
					 */

					s(currentComponent = "tFilterRow_22");

					/**
					 * [tFilterRow_22 process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN process_data_end ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					s(currentComponent = "tAggregateRow_1_AGGIN");

					/**
					 * [tAggregateRow_1_AGGIN process_data_end ] stop
					 */

					/**
					 * [tAggregateRow_1_AGGIN end ] start
					 */

					currentVirtualComponent = "tAggregateRow_1";

					s(currentComponent = "tAggregateRow_1_AGGIN");

				} // G_AggR_600

				if (log.isDebugEnabled())
					log.debug("tAggregateRow_1_AGGIN - " + ("Done."));

				ok_Hash.put("tAggregateRow_1_AGGIN", true);
				end_Hash.put("tAggregateRow_1_AGGIN", System.currentTimeMillis());

				/**
				 * [tAggregateRow_1_AGGIN end ] stop
				 */

				/**
				 * [tFilterRow_22 end ] start
				 */

				s(currentComponent = "tFilterRow_22");

				globalMap.put("tFilterRow_22_NB_LINE", nb_line_tFilterRow_22);
				globalMap.put("tFilterRow_22_NB_LINE_OK", nb_line_ok_tFilterRow_22);
				globalMap.put("tFilterRow_22_NB_LINE_REJECT", nb_line_reject_tFilterRow_22);

				log.info("tFilterRow_22 - Processed records count:" + nb_line_tFilterRow_22 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_22 + ". Rejected records count:" + nb_line_reject_tFilterRow_22 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row11", 2, 0,
						"tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn", "tFilterRow_22",
						"tFilterRow_22", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_22 - " + ("Done."));

				ok_Hash.put("tFilterRow_22", true);
				end_Hash.put("tFilterRow_22", System.currentTimeMillis());

				/**
				 * [tFilterRow_22 end ] stop
				 */

				/**
				 * [tAdvancedHash_row5 end ] start
				 */

				s(currentComponent = "tAdvancedHash_row5");

				tHash_Lookup_row5.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row5", 2, 0,
						"tFilterRow_22", "tFilterRow_22", "tFilterRow", "tAdvancedHash_row5", "tAdvancedHash_row5",
						"tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row5", true);
				end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row5 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			te.setVirtualComponentName(currentVirtualComponent);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tAggregateRow_1_AGGIN"
			globalMap.remove("tAggregateRow_1");

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_1");

				cLabel = "TransactionData1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				s(currentComponent = "tMap_2");

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGOUT finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				s(currentComponent = "tAggregateRow_1_AGGOUT");

				/**
				 * [tAggregateRow_1_AGGOUT finally ] stop
				 */

				/**
				 * [tAggregateRow_1_AGGIN finally ] start
				 */

				currentVirtualComponent = "tAggregateRow_1";

				s(currentComponent = "tAggregateRow_1_AGGIN");

				/**
				 * [tAggregateRow_1_AGGIN finally ] stop
				 */

				/**
				 * [tFilterRow_22 finally ] start
				 */

				s(currentComponent = "tFilterRow_22");

				/**
				 * [tFilterRow_22 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row5 finally ] start
				 */

				s(currentComponent = "tAdvancedHash_row5");

				/**
				 * [tAdvancedHash_row5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public void tPrejob_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tPrejob_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tPrejob_1", "tyGq5g_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tPrejob_1 begin ] start
				 */

				sh("tPrejob_1");

				s(currentComponent = "tPrejob_1");

				int tos_count_tPrejob_1 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tPrejob_1", "tPrejob_1", "tPrejob");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				/**
				 * [tPrejob_1 begin ] stop
				 */

				/**
				 * [tPrejob_1 main ] start
				 */

				s(currentComponent = "tPrejob_1");

				tos_count_tPrejob_1++;

				/**
				 * [tPrejob_1 main ] stop
				 */

				/**
				 * [tPrejob_1 process_data_begin ] start
				 */

				s(currentComponent = "tPrejob_1");

				/**
				 * [tPrejob_1 process_data_begin ] stop
				 */

				/**
				 * [tPrejob_1 process_data_end ] start
				 */

				s(currentComponent = "tPrejob_1");

				/**
				 * [tPrejob_1 process_data_end ] stop
				 */

				/**
				 * [tPrejob_1 end ] start
				 */

				s(currentComponent = "tPrejob_1");

				ok_Hash.put("tPrejob_1", true);
				end_Hash.put("tPrejob_1", System.currentTimeMillis());

				if (execStat) {
					runStat.updateStatOnConnection("OnComponentOk1", 0, "ok");
				}
				tSetGlobalVar_1Process(globalMap);

				/**
				 * [tPrejob_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tPrejob_1 finally ] start
				 */

				s(currentComponent = "tPrejob_1");

				/**
				 * [tPrejob_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tPrejob_1_SUBPROCESS_STATE", 1);
	}

	public void tSetGlobalVar_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tSetGlobalVar_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tSetGlobalVar_1", "g7sW12_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [tSetGlobalVar_1 begin ] start
				 */

				sh("tSetGlobalVar_1");

				s(currentComponent = "tSetGlobalVar_1");

				int tos_count_tSetGlobalVar_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tSetGlobalVar_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tSetGlobalVar_1 = new StringBuilder();
							log4jParamters_tSetGlobalVar_1.append("Parameters:");
							log4jParamters_tSetGlobalVar_1.append("VARIABLES" + " = " + "[{VALUE="
									+ ("context.targetYear") + ", KEY=" + ("\"targetYear\"") + "}]");
							log4jParamters_tSetGlobalVar_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tSetGlobalVar_1 - " + (log4jParamters_tSetGlobalVar_1));
						}
					}
					new BytesLimit65535_tSetGlobalVar_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tSetGlobalVar_1", "tSetGlobalVar_1", "tSetGlobalVar");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				/**
				 * [tSetGlobalVar_1 begin ] stop
				 */

				/**
				 * [tSetGlobalVar_1 main ] start
				 */

				s(currentComponent = "tSetGlobalVar_1");

				globalMap.put("targetYear", context.targetYear);

				tos_count_tSetGlobalVar_1++;

				/**
				 * [tSetGlobalVar_1 main ] stop
				 */

				/**
				 * [tSetGlobalVar_1 process_data_begin ] start
				 */

				s(currentComponent = "tSetGlobalVar_1");

				/**
				 * [tSetGlobalVar_1 process_data_begin ] stop
				 */

				/**
				 * [tSetGlobalVar_1 process_data_end ] start
				 */

				s(currentComponent = "tSetGlobalVar_1");

				/**
				 * [tSetGlobalVar_1 process_data_end ] stop
				 */

				/**
				 * [tSetGlobalVar_1 end ] start
				 */

				s(currentComponent = "tSetGlobalVar_1");

				if (log.isDebugEnabled())
					log.debug("tSetGlobalVar_1 - " + ("Done."));

				ok_Hash.put("tSetGlobalVar_1", true);
				end_Hash.put("tSetGlobalVar_1", System.currentTimeMillis());

				/**
				 * [tSetGlobalVar_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tSetGlobalVar_1 finally ] start
				 */

				s(currentComponent = "tSetGlobalVar_1");

				/**
				 * [tSetGlobalVar_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tSetGlobalVar_1_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				sh("talendJobLog");

				s(currentComponent = "talendJobLog");

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder
							.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
							.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid)
							.custom("father_pid", fatherPid).custom("root_pid", rootPid);
					org.talend.logging.audit.Context log_context_talendJobLog = null;

					if (jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.sourceId(jcm.sourceId)
								.sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
								.targetId(jcm.targetId).targetLabel(jcm.targetLabel)
								.targetConnectorType(jcm.targetComponentName).connectionName(jcm.current_connector)
								.rows(jcm.row_count).duration(duration).build();
						auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
						auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).duration(duration)
								.status(jcm.status).build();
						auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
								.connectorType(jcm.component_name).connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {// log current component
																							// input line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {// log current component
																								// output/reject line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
						java.lang.Exception e_talendJobLog = jcm.exception;
						if (e_talendJobLog != null) {
							try (java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();
									java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
								e_talendJobLog.printStackTrace(pw_talendJobLog);
								builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,
										java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
							}
						}

						if (jcm.extra_info != null) {
							builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
						}

						log_context_talendJobLog = builder_talendJobLog
								.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
								.connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label)
								.build();

						auditLogger_talendJobLog.exception(log_context_talendJobLog);
					}

				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				s(currentComponent = "talendJobLog");

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				s(currentComponent = "talendJobLog");

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;
	private boolean enableLineage;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();
	private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();

	public static void main(String[] args) {
		final TelcoJob1 TelcoJob1Class = new TelcoJob1();

		int exitCode = TelcoJob1Class.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'TelcoJob1' - Done.");
		}

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
				jobInfo.load(is);
			} catch (IOException e) {

				log.debug("Read jobInfo.properties file fail: " + e.getMessage());

			}
		}
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s", projectName,
				jobName, jobInfo.getProperty("gitCommitId"), "8.0.1.20250319_1318-patch"));

	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		final boolean enableCBP = false;
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (!inOSGi && isCBPClientPresent) {
			if (org.talend.metrics.CBPClient.getInstanceForCurrentVM() == null) {
				try {
					org.talend.metrics.CBPClient.startListenIfNotStarted(enableCBP, true);
				} catch (java.lang.Exception e) {
					errorCode = 1;
					status = "failure";
					e.printStackTrace();
					return 1;
				}
			}
		}

		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (!"".equals(log4jLevel)) {

			if ("trace".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.OFF);
			}
			org.apache.logging.log4j.core.config.Configurator
					.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());

		}

		getjobInfo();
		log.info("TalendJob: 'TelcoJob1' - Start.");

		java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
		for (Object jobInfoKey : jobInfoKeys) {
			org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
		}
		org.slf4j.MDC.put("_pid", pid);
		org.slf4j.MDC.put("_rootPid", rootPid);
		org.slf4j.MDC.put("_fatherPid", fatherPid);
		org.slf4j.MDC.put("_projectName", projectName);
		org.slf4j.MDC.put("_startTimestamp", java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC)
				.format(java.time.format.DateTimeFormatter.ISO_INSTANT));
		org.slf4j.MDC.put("_jobRepositoryId", "_BJyGECDxEfGaG-ujHcrDsQ");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2026-04-01T01:56:34.736642Z");

		java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
		String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
		if (mxNameTable.length == 2) {
			org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
		} else {
			org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
		}

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()),
							System.getProperty(key)));

			org.apache.logging.log4j.core.config.Configurator
					.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		org.slf4j.MDC.put("_pid", pid);

		if (rootPid == null) {
			rootPid = pid;
		}

		org.slf4j.MDC.put("_rootPid", rootPid);

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}
		org.slf4j.MDC.put("_fatherPid", fatherPid);

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}

				if (jobProperties != null && jobProperties.get("taskExecutionId") != null) {
					taskExecutionId = (String) jobProperties.get("taskExecutionId");
				}

				// extract ids from parent route
				if (null == taskExecutionId || taskExecutionId.isEmpty()) {
					for (String arg : args) {
						if (arg.startsWith("--context_param")
								&& (arg.contains("taskExecutionId") || arg.contains("jobExecutionId"))) {

							String keyValue = arg.replace("--context_param", "");
							String[] parts = keyValue.split("=");
							String[] cleanParts = java.util.Arrays.stream(parts).filter(s -> !s.isEmpty())
									.toArray(String[]::new);
							if (cleanParts.length == 2) {
								String key = cleanParts[0];
								String value = cleanParts[1];
								if ("taskExecutionId".equals(key.trim()) && null != value) {
									taskExecutionId = value.trim();
								} else if ("jobExecutionId".equals(key.trim()) && null != value) {
									jobExecutionId = value.trim();
								}
							}
						}
					}
				}
			}

			// first load default key-value pairs from application.properties
			if (isStandaloneMS) {
				context.putAll(this.getDefaultProperties());
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = TelcoJob1.class.getClassLoader()
					.getResourceAsStream("telco/telcojob1_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = TelcoJob1.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
					if (isStandaloneMS) {
						// override context key-value pairs if provided using --context=contextName
						defaultProps.load(inContext);
						context.putAll(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}
			// override key-value pairs if provided via --config.location=file1.file2 OR
			// --config.additional-location=file1,file2
			if (isStandaloneMS) {
				context.putAll(this.getAdditionalProperties());
			}

			// override key-value pairs if provide via command line like
			// --key1=value1,--key2=value2
			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("targetYear", "id_String");
					if (context.getStringValue("targetYear") == null) {
						context.targetYear = null;
					} else {
						context.targetYear = (String) context.getProperty("targetYear");
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("targetYear")) {
				context.targetYear = (String) parentContextMap.get("targetYear");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		org.slf4j.MDC.put("_context", contextStr);
		log.info("TalendJob: 'TelcoJob1' - Started.");
		java.util.Optional.ofNullable(org.slf4j.MDC.getCopyOfContextMap()).ifPresent(mdcInfo::putAll);

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		try {
			errorCode = null;
			tPrejob_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tPrejob_1) {
			globalMap.put("tPrejob_1_SUBPROCESS_STATE", -1);

			e_tPrejob_1.printStackTrace();

		}

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : TelcoJob1");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		if (!inOSGi && isCBPClientPresent) {
			if (org.talend.metrics.CBPClient.getInstanceForCurrentVM() != null) {
				s("none");
				org.talend.metrics.CBPClient.getInstanceForCurrentVM().sendData();
			}
		}

		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		org.slf4j.MDC.remove("_subJobName");
		org.slf4j.MDC.remove("_subJobPid");
		org.slf4j.MDC.remove("_systemPid");
		log.info("TalendJob: 'TelcoJob1' - Finished - status: " + status + " returnCode: " + returnCode);

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {
		// add CBP code for OSGI Executions
		if (null != taskExecutionId && !taskExecutionId.isEmpty()) {
			try {
				org.talend.metrics.DataReadTracker.setExecutionId(taskExecutionId, jobExecutionId, false);
				org.talend.metrics.DataReadTracker.sealCounter();
				org.talend.metrics.DataReadTracker.reset();
			} catch (Exception | NoClassDefFoundError e) {
				// ignore
			}
		}

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--context_file")) {
			String keyValue = arg.substring(15);
			String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
			java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
			try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
				String line;
				while ((line = reader.readLine()) != null) {
					int index = -1;
					if ((index = line.indexOf('=')) > -1) {
						if (line.startsWith("--context_param")) {
							if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
								context_param.put(line.substring(16, index),
										routines.system.PasswordEncryptUtil.decryptPassword(line.substring(index + 1)));
							} else {
								context_param.put(line.substring(16, index), line.substring(index + 1));
							}
						} else {// --context_type
							context_param.setContextType(line.substring(15, index), line.substring(index + 1));
						}
					}
				}
			} catch (java.io.IOException e) {
				System.err.println("Could not load the context file: " + filePath);
				e.printStackTrace();
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 474405 characters generated by Talend Cloud Data Management Platform on the 1
 * April 2026 at 9:56:34 am MYT
 ************************************************************************************************/