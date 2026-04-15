
package telco.testj_0_1;

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
 * Job: testj Purpose: <br>
 * Description: <br>
 * 
 * @author izzati.zaki@quandatics.com
 * @version 8.0.1.20250319_1318-patch
 * @status
 */
public class testj implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "testj.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(testj.class);

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
	private final String jobName = "testj";
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
			"_ntyZACzGEfG6M9r5cJ5lcA", "0.1");
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
				testj.this.exception = e;
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(testj.this, new Object[] { e, currentComponent, globalMap });
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

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFilterRow_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAggregateRow_1_AGGOUT_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		tAggregateRow_1_AGGIN_error(exception, errorComponent, globalMap);

	}

	public void tAggregateRow_1_AGGIN_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class out1Struct implements routines.system.IPersistableRow<out1Struct> {
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];

		public Integer CustomerId;

		public Integer getCustomerId() {
			return this.CustomerId;
		}

		public Boolean CustomerIdIsNullable() {
			return true;
		}

		public Boolean CustomerIdIsKey() {
			return false;
		}

		public Integer CustomerIdLength() {
			return 3;
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

		public Double TotalTransaction;

		public Double getTotalTransaction() {
			return this.TotalTransaction;
		}

		public Boolean TotalTransactionIsNullable() {
			return true;
		}

		public Boolean TotalTransactionIsKey() {
			return false;
		}

		public Integer TotalTransactionLength() {
			return null;
		}

		public Integer TotalTransactionPrecision() {
			return 0;
		}

		public String TotalTransactionDefault() {

			return null;

		}

		public String TotalTransactionComment() {

			return "";

		}

		public String TotalTransactionPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalTransactionOriginalDbColumnName() {

			return "TotalTransaction";

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

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransaction = null;
					} else {
						this.TotalTransaction = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransaction = null;
					} else {
						this.TotalTransaction = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

				// Double

				if (this.TotalTransaction == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransaction);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

				// Double

				if (this.TotalTransaction == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransaction);
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
			sb.append("CustomerId=" + String.valueOf(CustomerId));
			sb.append(",TotalTransaction=" + String.valueOf(TotalTransaction));
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

			if (TotalTransaction == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalTransaction);
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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];

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

		public Integer CustomerId;

		public Integer getCustomerId() {
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

		public String ID;

		public String getID() {
			return this.ID;
		}

		public Boolean IDIsNullable() {
			return true;
		}

		public Boolean IDIsKey() {
			return false;
		}

		public Integer IDLength() {
			return 7;
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
			return 19;
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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readInteger(dis);

					this.Name = readString(dis);

					this.ID = readString(dis);

					this.Birthday = readDate(dis);

					this.State = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readInteger(dis);

					this.Name = readString(dis);

					this.ID = readString(dis);

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

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.ID, dos);

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

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.ID, dos);

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
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",Name=" + Name);
			sb.append(",ID=" + ID);
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

	public static class after_tFileInputDelimited_1Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_1Struct> {
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];

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

		public Integer CustomerId;

		public Integer getCustomerId() {
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

		public String ID;

		public String getID() {
			return this.ID;
		}

		public Boolean IDIsNullable() {
			return true;
		}

		public Boolean IDIsKey() {
			return false;
		}

		public Integer IDLength() {
			return 7;
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
			return 19;
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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readInteger(dis);

					this.Name = readString(dis);

					this.ID = readString(dis);

					this.Birthday = readDate(dis);

					this.State = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.PostalCode = readInteger(dis);

					this.HashCode = readString(dis);

					this.CustomerId = readInteger(dis);

					this.Name = readString(dis);

					this.ID = readString(dis);

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

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.ID, dos);

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

				// Integer

				writeInteger(this.CustomerId, dos);

				// String

				writeString(this.Name, dos);

				// String

				writeString(this.ID, dos);

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
			sb.append(",CustomerId=" + String.valueOf(CustomerId));
			sb.append(",Name=" + Name);
			sb.append(",ID=" + ID);
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
		public int compareTo(after_tFileInputDelimited_1Struct other) {

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

		mdc("tFileInputDelimited_1", "mQxOJB_");

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

				row1Struct row1 = new row1Struct();
				out1Struct out1 = new out1Struct();

				/**
				 * [tLogRow_1 begin ] start
				 */

				sh("tLogRow_1");

				s(currentComponent = "tLogRow_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "out1");

				int tos_count_tLogRow_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tLogRow_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tLogRow_1 = new StringBuilder();
							log4jParamters_tLogRow_1.append("Parameters:");
							log4jParamters_tLogRow_1.append("BASIC_MODE" + " = " + "true");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("TABLE_PRINT" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("VERTICAL" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("FIELDSEPARATOR" + " = " + "\"|\"");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_HEADER" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_UNIQUE_NAME" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("PRINT_COLNAMES" + " = " + "false");
							log4jParamters_tLogRow_1.append(" | ");
							log4jParamters_tLogRow_1.append("USE_FIXED_LENGTH" + " = " + "false");
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

				final String OUTPUT_FIELD_SEPARATOR_tLogRow_1 = "|";
				java.io.PrintStream consoleOut_tLogRow_1 = null;

				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
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

				int count_row4_tMap_1 = 0;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_out1_tMap_1 = 0;

				out1Struct out1_tmp = new out1Struct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				sh("tFileInputDelimited_1");

				s(currentComponent = "tFileInputDelimited_1");

				cLabel = "CustomerData";

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
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Downloads/DI/Customer Data.csv\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CSV_OPTION" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("FIELDSEPARATOR" + " = " + "\",\"");
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
							log4jParamters_tFileInputDelimited_1.append("ADVANCED_SEPARATOR" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("RANDOM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMALL" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("TRIMSELECT" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("PostalCode") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("HashCode") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("CustomerId")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Name") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ID") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("Birthday") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("State") + "}]");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("ENCODING" + " = " + "\"UTF-8\"");
							log4jParamters_tFileInputDelimited_1.append(" | ");
							log4jParamters_tFileInputDelimited_1.append("SPLITRECORD" + " = " + "false");
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
					talendJobLog.addCM("tFileInputDelimited_1", "CustomerData", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "/Users/abuzaki/Downloads/DI/Customer Data.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"/Users/abuzaki/Downloads/DI/Customer Data.csv", "UTF-8", ",", "\n", false, 1, 0,
								limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_1 - " + e.getMessage());

						System.err.println(e.getMessage());

					}

					log.info("tFileInputDelimited_1 - Retrieving records from the datasource.");

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.PostalCode = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"PostalCode", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.PostalCode = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							row1.HashCode = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 2;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.CustomerId = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"CustomerId", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.CustomerId = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.Name = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							row1.ID = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 5;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.Birthday = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Birthday", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.Birthday = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row1.State = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							log.error("tFileInputDelimited_1 - " + e.getMessage());

							System.err.println(e.getMessage());
							row1 = null;

						}

						log.debug("tFileInputDelimited_1 - Retrieving the record "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "CustomerData";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "CustomerData";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */

// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							s(currentComponent = "tMap_1");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row1", "tFileInputDelimited_1", "CustomerData", "tFileInputDelimited", "tMap_1",
									"tMap_1", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							row4Struct row4 = null;

							// ###############################
							// # Input tables (lookups)

							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row4"
							///////////////////////////////////////////////

							boolean forceLooprow4 = false;

							row4Struct row4ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_1) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_1 = false;

								row4HashKey.CustomerId = row1.CustomerId;

								row4HashKey.hashCodeDirty = true;

								tHash_Lookup_row4.lookup(row4HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4'
								// and it contains more one result from keys : row4.CustomerId = '" +
								// row4HashKey.CustomerId + "'");
							} // G 071

							row4Struct fromLookup_row4 = null;
							row4 = row4Default;

							if (tHash_Lookup_row4 != null && tHash_Lookup_row4.hasNext()) { // G 099

								fromLookup_row4 = tHash_Lookup_row4.next();

							} // G 099

							if (fromLookup_row4 != null) {
								row4 = fromLookup_row4;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								out1 = null;

// # Output table : 'out1'
								count_out1_tMap_1++;

								out1_tmp.CustomerId = row1.CustomerId;
								out1_tmp.TotalTransaction = row4.TotalTransaction;
								out1_tmp.TransactionYear = row4.TransactionYear;
								out1 = out1_tmp;
								log.debug("tMap_1 - Outputting the record " + count_out1_tMap_1
										+ " of the output table 'out1'.");

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
								 * [tLogRow_1 main ] start
								 */

								s(currentComponent = "tLogRow_1");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "out1", "tMap_1", "tMap_1", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace("out1 - " + (out1 == null ? "" : out1.toLogString()));
								}

///////////////////////		

								strBuffer_tLogRow_1 = new StringBuilder();

								if (out1.CustomerId != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(out1.CustomerId));

								} //

								strBuffer_tLogRow_1.append("|");

								if (out1.TotalTransaction != null) { //

									strBuffer_tLogRow_1.append(FormatterUtils.formatUnwithE(out1.TotalTransaction));

								} //

								strBuffer_tLogRow_1.append("|");

								if (out1.TransactionYear != null) { //

									strBuffer_tLogRow_1.append(String.valueOf(out1.TransactionYear));

								} //

								if (globalMap.get("tLogRow_CONSOLE") != null) {
									consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
								} else {
									consoleOut_tLogRow_1 = new java.io.PrintStream(
											new java.io.BufferedOutputStream(System.out));
									globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
								}
								log.info("tLogRow_1 - Content of row " + (nb_line_tLogRow_1 + 1) + ": "
										+ strBuffer_tLogRow_1.toString());
								consoleOut_tLogRow_1.println(strBuffer_tLogRow_1.toString());
								consoleOut_tLogRow_1.flush();
								nb_line_tLogRow_1++;
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

							} // End of branch "out1"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							s(currentComponent = "tMap_1");

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "CustomerData";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_1");

						cLabel = "CustomerData";

					}
				} finally {
					if (!((Object) ("/Users/abuzaki/Downloads/DI/Customer Data.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

						log.info("tFileInputDelimited_1 - Retrieved records count: "
								+ fid_tFileInputDelimited_1.getRowNumber() + ".");

					}
				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_1 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				s(currentComponent = "tMap_1");

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

// ###############################      
				log.debug("tMap_1 - Written records count in the table 'out1': " + count_out1_tMap_1 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tFileInputDelimited_1", "CustomerData", "tFileInputDelimited", "tMap_1", "tMap_1", "tMap",
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
				 * [tLogRow_1 end ] start
				 */

				s(currentComponent = "tLogRow_1");

//////
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);
				if (log.isInfoEnabled())
					log.info("tLogRow_1 - " + ("Printed row count: ") + (nb_line_tLogRow_1) + ("."));

///////////////////////    			

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "out1", 2, 0, "tMap_1",
						"tMap_1", "tMap", "tLogRow_1", "tLogRow_1", "tLogRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tLogRow_1 - " + ("Done."));

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
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
			globalMap.remove("tHash_Lookup_row4");

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_1");

				cLabel = "CustomerData";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				s(currentComponent = "tMap_1");

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				s(currentComponent = "tLogRow_1");

				/**
				 * [tLogRow_1 finally ] stop
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

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer CustomerId;

		public Integer getCustomerId() {
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

		public Double TotalTransaction;

		public Double getTotalTransaction() {
			return this.TotalTransaction;
		}

		public Boolean TotalTransactionIsNullable() {
			return true;
		}

		public Boolean TotalTransactionIsKey() {
			return false;
		}

		public Integer TotalTransactionLength() {
			return null;
		}

		public Integer TotalTransactionPrecision() {
			return 0;
		}

		public String TotalTransactionDefault() {

			return null;

		}

		public String TotalTransactionComment() {

			return "";

		}

		public String TotalTransactionPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalTransactionOriginalDbColumnName() {

			return "TotalTransaction";

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
			final row4Struct other = (row4Struct) obj;

			if (this.CustomerId == null) {
				if (other.CustomerId != null)
					return false;

			} else if (!this.CustomerId.equals(other.CustomerId))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.CustomerId = this.CustomerId;
			other.TotalTransaction = this.TotalTransaction;
			other.TransactionYear = this.TransactionYear;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.CustomerId = this.CustomerId;

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

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

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
					this.TotalTransaction = null;
				} else {
					this.TotalTransaction = dis.readDouble();
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
					this.TotalTransaction = null;
				} else {
					this.TotalTransaction = objectIn.readDouble();
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

				if (this.TotalTransaction == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransaction);
				}

				writeInteger(this.TransactionYear, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				if (this.TotalTransaction == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeDouble(this.TotalTransaction);
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
			sb.append("CustomerId=" + String.valueOf(CustomerId));
			sb.append(",TotalTransaction=" + String.valueOf(TotalTransaction));
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

			if (TotalTransaction == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalTransaction);
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
		public int compareTo(row4Struct other) {

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

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];

		public Integer CustomerId;

		public Integer getCustomerId() {
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

		public Double TotalTransaction;

		public Double getTotalTransaction() {
			return this.TotalTransaction;
		}

		public Boolean TotalTransactionIsNullable() {
			return true;
		}

		public Boolean TotalTransactionIsKey() {
			return false;
		}

		public Integer TotalTransactionLength() {
			return null;
		}

		public Integer TotalTransactionPrecision() {
			return 0;
		}

		public String TotalTransactionDefault() {

			return null;

		}

		public String TotalTransactionComment() {

			return "";

		}

		public String TotalTransactionPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalTransactionOriginalDbColumnName() {

			return "TotalTransaction";

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

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransaction = null;
					} else {
						this.TotalTransaction = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransaction = null;
					} else {
						this.TotalTransaction = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

				// Double

				if (this.TotalTransaction == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransaction);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

				// Double

				if (this.TotalTransaction == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransaction);
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
			sb.append("CustomerId=" + String.valueOf(CustomerId));
			sb.append(",TotalTransaction=" + String.valueOf(TotalTransaction));
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

			if (TotalTransaction == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalTransaction);
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
		public int compareTo(row3Struct other) {

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
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];

		public Integer CustomerId;

		public Integer getCustomerId() {
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

		public Double TotalTransaction;

		public Double getTotalTransaction() {
			return this.TotalTransaction;
		}

		public Boolean TotalTransactionIsNullable() {
			return true;
		}

		public Boolean TotalTransactionIsKey() {
			return false;
		}

		public Integer TotalTransactionLength() {
			return null;
		}

		public Integer TotalTransactionPrecision() {
			return 0;
		}

		public String TotalTransactionDefault() {

			return null;

		}

		public String TotalTransactionComment() {

			return "";

		}

		public String TotalTransactionPattern() {

			return "dd-MM-yyyy";

		}

		public String TotalTransactionOriginalDbColumnName() {

			return "TotalTransaction";

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

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransaction = null;
					} else {
						this.TotalTransaction = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TELCO_testj) {

				try {

					int length = 0;

					this.CustomerId = readInteger(dis);

					length = dis.readByte();
					if (length == -1) {
						this.TotalTransaction = null;
					} else {
						this.TotalTransaction = dis.readDouble();
					}

					this.TransactionYear = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

				// Double

				if (this.TotalTransaction == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransaction);
				}

				// Integer

				writeInteger(this.TransactionYear, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.CustomerId, dos);

				// Double

				if (this.TotalTransaction == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.TotalTransaction);
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
			sb.append("CustomerId=" + String.valueOf(CustomerId));
			sb.append(",TotalTransaction=" + String.valueOf(TotalTransaction));
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

			if (TotalTransaction == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalTransaction);
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

	public static class ExtractYearStruct implements routines.system.IPersistableRow<ExtractYearStruct> {
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];

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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_TELCO_testj) {

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

			synchronized (commonByteArrayLock_TELCO_testj) {

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
		public int compareTo(ExtractYearStruct other) {

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

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_TELCO_testj = new byte[0];
		static byte[] commonByteArray_TELCO_testj = new byte[0];

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

			return "dd-MM-yyyy";

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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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
				if (length > commonByteArray_TELCO_testj.length) {
					if (length < 1024 && commonByteArray_TELCO_testj.length == 0) {
						commonByteArray_TELCO_testj = new byte[1024];
					} else {
						commonByteArray_TELCO_testj = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TELCO_testj, 0, length);
				strReturn = new String(commonByteArray_TELCO_testj, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_TELCO_testj) {

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

			synchronized (commonByteArrayLock_TELCO_testj) {

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
		public int compareTo(row2Struct other) {

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

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tFileInputDelimited_2", "Ll7aBt_");

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

				row2Struct row2 = new row2Struct();
				ExtractYearStruct ExtractYear = new ExtractYearStruct();
				row3Struct row3 = new row3Struct();
				row4Struct row4 = new row4Struct();

				/**
				 * [tAggregateRow_1_AGGOUT begin ] start
				 */

				sh("tAggregateRow_1_AGGOUT");

				currentVirtualComponent = "tAggregateRow_1";

				s(currentComponent = "tAggregateRow_1_AGGOUT");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "ExtractYear");

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
									+ ("TotalTransaction") + ", INPUT_COLUMN=" + ("TransactionValue") + ", IGNORE_NULL="
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
					BigDecimal TotalTransaction_sum;

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

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row2");

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
				int count_row2_tMap_2 = 0;

// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				int count_ExtractYear_tMap_2 = 0;

				ExtractYearStruct ExtractYear_tmp = new ExtractYearStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				sh("tFileInputDelimited_2");

				s(currentComponent = "tFileInputDelimited_2");

				cLabel = "TransactionData1";

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
									.append("FILENAME" + " = " + "\"/Users/abuzaki/Downloads/Transaction Data.csv\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CSV_OPTION" + " = " + "true");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CSVROWSEPARATOR" + " = " + "\"\\n\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("FIELDSEPARATOR" + " = " + "\",\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ESCAPE_CHAR" + " = " + "\"\"\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("TEXT_ENCLOSURE" + " = " + "\"\"\"");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("HEADER" + " = " + "1");
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
							log4jParamters_tFileInputDelimited_2.append("TRIMALL" + " = " + "true");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CHECK_FIELDS_NUM" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("CHECK_DATE" + " = " + "false");
							log4jParamters_tFileInputDelimited_2.append(" | ");
							log4jParamters_tFileInputDelimited_2.append("ENCODING" + " = " + "\"US-ASCII\"");
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
					talendJobLog.addCM("tFileInputDelimited_2", "TransactionData1", "tFileInputDelimited");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				int footer_tFileInputDelimited_2 = 0;
				int totalLinetFileInputDelimited_2 = 0;
				int limittFileInputDelimited_2 = -1;
				int lastLinetFileInputDelimited_2 = -1;

				char fieldSeparator_tFileInputDelimited_2[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_2 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_2[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_2 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_2 = /** Start field tFileInputDelimited_2:FILENAME */
						"/Users/abuzaki/Downloads/Transaction Data.csv"/** End field tFileInputDelimited_2:FILENAME */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_2 = null;

				try {

					String[] rowtFileInputDelimited_2 = null;
					int currentLinetFileInputDelimited_2 = 0;
					int outputLinetFileInputDelimited_2 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_2 = 0;
							if (footer_value_tFileInputDelimited_2 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_2,
									fieldSeparator_tFileInputDelimited_2[0], "US-ASCII");
						} else {
							csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_2),
									fieldSeparator_tFileInputDelimited_2[0], "US-ASCII");
						}

						csvReadertFileInputDelimited_2.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_2[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_2[0] != '\r'))
							csvReadertFileInputDelimited_2.setLineEnd("" + rowSeparator_tFileInputDelimited_2[0]);

						csvReadertFileInputDelimited_2.setQuoteChar('"');

						csvReadertFileInputDelimited_2.setEscapeChar(csvReadertFileInputDelimited_2.getQuoteChar());

						if (footer_tFileInputDelimited_2 > 0) {
							for (totalLinetFileInputDelimited_2 = 0; totalLinetFileInputDelimited_2 < 1; totalLinetFileInputDelimited_2++) {
								csvReadertFileInputDelimited_2.readNext();
							}
							csvReadertFileInputDelimited_2.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_2.readNext()) {

								totalLinetFileInputDelimited_2++;

							}
							int lastLineTemptFileInputDelimited_2 = totalLinetFileInputDelimited_2
									- footer_tFileInputDelimited_2 < 0 ? 0
											: totalLinetFileInputDelimited_2 - footer_tFileInputDelimited_2;
							if (lastLinetFileInputDelimited_2 > 0) {
								lastLinetFileInputDelimited_2 = lastLinetFileInputDelimited_2 < lastLineTemptFileInputDelimited_2
										? lastLinetFileInputDelimited_2
										: lastLineTemptFileInputDelimited_2;
							} else {
								lastLinetFileInputDelimited_2 = lastLineTemptFileInputDelimited_2;
							}

							csvReadertFileInputDelimited_2.close();
							if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_2,
										fieldSeparator_tFileInputDelimited_2[0], "US-ASCII");
							} else {
								csvReadertFileInputDelimited_2 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_2),
										fieldSeparator_tFileInputDelimited_2[0], "US-ASCII");
							}
							csvReadertFileInputDelimited_2.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_2[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_2[0] != '\r'))
								csvReadertFileInputDelimited_2.setLineEnd("" + rowSeparator_tFileInputDelimited_2[0]);

							csvReadertFileInputDelimited_2.setQuoteChar('"');

							csvReadertFileInputDelimited_2.setEscapeChar(csvReadertFileInputDelimited_2.getQuoteChar());

						}

						if (limittFileInputDelimited_2 != 0) {
							for (currentLinetFileInputDelimited_2 = 0; currentLinetFileInputDelimited_2 < 1; currentLinetFileInputDelimited_2++) {
								csvReadertFileInputDelimited_2.readNext();
							}
						}
						csvReadertFileInputDelimited_2.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						log.error("tFileInputDelimited_2 - " + e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					log.info("tFileInputDelimited_2 - Retrieving records from the datasource.");

					while (limittFileInputDelimited_2 != 0 && csvReadertFileInputDelimited_2 != null
							&& csvReadertFileInputDelimited_2.readNext()) {
						rowstate_tFileInputDelimited_2.reset();

						rowtFileInputDelimited_2 = csvReadertFileInputDelimited_2.getValues();

						currentLinetFileInputDelimited_2++;

						if (lastLinetFileInputDelimited_2 > -1
								&& currentLinetFileInputDelimited_2 > lastLinetFileInputDelimited_2) {
							break;
						}
						outputLinetFileInputDelimited_2++;
						if (limittFileInputDelimited_2 > 0
								&& outputLinetFileInputDelimited_2 > limittFileInputDelimited_2) {
							break;
						}

						row2 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row2 = new row2Struct();
						try {

							char fieldSeparator_tFileInputDelimited_2_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_2_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_2.length == 1 && ("\015").equals(rowtFileInputDelimited_2[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row2.CustomerId = null;

								row2.TransactionValue = null;

								row2.PaymentMethod = null;

								row2.Date = null;

							} else {

								for (int i_tFileInputDelimited_2 = 0; i_tFileInputDelimited_2 < rowtFileInputDelimited_2.length; i_tFileInputDelimited_2++) {
									rowtFileInputDelimited_2[i_tFileInputDelimited_2] = rowtFileInputDelimited_2[i_tFileInputDelimited_2]
											.trim();
								}

								int columnIndexWithD_tFileInputDelimited_2 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_2 = 0;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row2.CustomerId = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row2.CustomerId = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 1;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row2.TransactionValue = ParserUtils.parseTo_Double(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2]);

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"TransactionValue", "row2",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row2.TransactionValue = null;

									}

								} else {

									row2.TransactionValue = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 2;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									row2.PaymentMethod = rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2];

								} else {

									row2.PaymentMethod = null;

								}

								columnIndexWithD_tFileInputDelimited_2 = 3;

								if (columnIndexWithD_tFileInputDelimited_2 < rowtFileInputDelimited_2.length) {

									if (rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2].length() > 0) {
										try {

											row2.Date = ParserUtils.parseTo_Date(
													rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
													"dd-MM-yyyy");

										} catch (java.lang.Exception ex_tFileInputDelimited_2) {
											globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
													ex_tFileInputDelimited_2.getMessage());
											rowstate_tFileInputDelimited_2.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"Date", "row2",
															rowtFileInputDelimited_2[columnIndexWithD_tFileInputDelimited_2],
															ex_tFileInputDelimited_2),
													ex_tFileInputDelimited_2));
										}
									} else {

										row2.Date = null;

									}

								} else {

									row2.Date = null;

								}

							}

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							log.error("tFileInputDelimited_2 - " + e.getMessage());

							System.err.println(e.getMessage());
							row2 = null;

							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						}

						log.debug("tFileInputDelimited_2 - Retrieving the record " + (nb_line_tFileInputDelimited_2 + 1)
								+ ".");

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "TransactionData1";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "TransactionData1";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */

// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tMap_2 main ] start
							 */

							s(currentComponent = "tMap_2");

							if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

									, "row2", "tFileInputDelimited_2", "TransactionData1", "tFileInputDelimited",
									"tMap_2", "tMap_2", "tMap"

							)) {
								talendJobLogProcess(globalMap);
							}

							if (log.isTraceEnabled()) {
								log.trace("row2 - " + (row2 == null ? "" : row2.toLogString()));
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

								Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
								// ###############################
								// # Output tables

								ExtractYear = null;

// # Output table : 'ExtractYear'
								count_ExtractYear_tMap_2++;

								ExtractYear_tmp.CustomerId = row2.CustomerId;
								ExtractYear_tmp.TransactionValue = row2.TransactionValue;
								ExtractYear_tmp.TransactionYear = TalendDate.getPartOfDate("yyyy", row2.Date);
								ExtractYear = ExtractYear_tmp;
								log.debug("tMap_2 - Outputting the record " + count_ExtractYear_tMap_2
										+ " of the output table 'ExtractYear'.");

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

// Start of branch "ExtractYear"
							if (ExtractYear != null) {

								/**
								 * [tAggregateRow_1_AGGOUT main ] start
								 */

								currentVirtualComponent = "tAggregateRow_1";

								s(currentComponent = "tAggregateRow_1_AGGOUT");

								if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

										, "ExtractYear", "tMap_2", "tMap_2", "tMap", "tAggregateRow_1_AGGOUT",
										"tAggregateRow_1_AGGOUT", "tAggregateOut"

								)) {
									talendJobLogProcess(globalMap);
								}

								if (log.isTraceEnabled()) {
									log.trace(
											"ExtractYear - " + (ExtractYear == null ? "" : ExtractYear.toLogString()));
								}

								operation_finder_tAggregateRow_1.CustomerId = ExtractYear.CustomerId;
								operation_finder_tAggregateRow_1.TransactionYear = ExtractYear.TransactionYear;

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

								if (operation_result_tAggregateRow_1.TotalTransaction_sum == null) {
									operation_result_tAggregateRow_1.TotalTransaction_sum = new BigDecimal(0)
											.setScale(0);
								}
								operation_result_tAggregateRow_1.TotalTransaction_sum = operation_result_tAggregateRow_1.TotalTransaction_sum
										.add(new BigDecimal(String.valueOf(ExtractYear.TransactionValue)));

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

							} // End of branch "ExtractYear"

							/**
							 * [tMap_2 process_data_end ] start
							 */

							s(currentComponent = "tMap_2");

							/**
							 * [tMap_2 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "TransactionData1";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						s(currentComponent = "tFileInputDelimited_2");

						cLabel = "TransactionData1";

						nb_line_tFileInputDelimited_2++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_2 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_2 != null) {
							csvReadertFileInputDelimited_2.close();
						}
					}
					if (csvReadertFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", nb_line_tFileInputDelimited_2);
					}

					log.info("tFileInputDelimited_2 - Retrieved records count: " + nb_line_tFileInputDelimited_2 + ".");

				}

				if (log.isDebugEnabled())
					log.debug("tFileInputDelimited_2 - " + ("Done."));

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				s(currentComponent = "tMap_2");

// ###############################
// # Lookup hashes releasing
// ###############################      
				log.debug(
						"tMap_2 - Written records count in the table 'ExtractYear': " + count_ExtractYear_tMap_2 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row2", 2, 0,
						"tFileInputDelimited_2", "TransactionData1", "tFileInputDelimited", "tMap_2", "tMap_2", "tMap",
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

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "ExtractYear", 2, 0,
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
				 * [tAdvancedHash_row4 begin ] start
				 */

				sh("tAdvancedHash_row4");

				s(currentComponent = "tAdvancedHash_row4");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row4");

				int tos_count_tAdvancedHash_row4 = 0;

				if (enableLogStash) {
					talendJobLog.addCM("tAdvancedHash_row4", "tAdvancedHash_row4", "tAdvancedHash");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				// connection name:row4
				// source node:tFilterRow_6 - inputs:(row3) outputs:(row4,row4) | target
				// node:tAdvancedHash_row4 - inputs:(row4) outputs:()
				// linked node: tMap_1 - inputs:(row1,row4) outputs:(out1)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
				 */

				/**
				 * [tFilterRow_6 begin ] start
				 */

				sh("tFilterRow_6");

				s(currentComponent = "tFilterRow_6");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row3");

				int tos_count_tFilterRow_6 = 0;

				if (log.isDebugEnabled())
					log.debug("tFilterRow_6 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tFilterRow_6 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tFilterRow_6 = new StringBuilder();
							log4jParamters_tFilterRow_6.append("Parameters:");
							log4jParamters_tFilterRow_6.append("LOGICAL_OP" + " = " + "&&");
							log4jParamters_tFilterRow_6.append(" | ");
							log4jParamters_tFilterRow_6.append("CONDITIONS" + " = " + "[]");
							log4jParamters_tFilterRow_6.append(" | ");
							log4jParamters_tFilterRow_6.append("USE_ADVANCED" + " = " + "true");
							log4jParamters_tFilterRow_6.append(" | ");
							log4jParamters_tFilterRow_6.append("ADVANCED_COND" + " = "
									+ "(\"ALL\".equalsIgnoreCase(context.targetYear)) || (      !\"ALL\".equalsIgnoreCase(context.targetYear)      && row3.TransactionYear == Integer.parseInt(context.targetYear)    )");
							log4jParamters_tFilterRow_6.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tFilterRow_6 - " + (log4jParamters_tFilterRow_6));
						}
					}
					new BytesLimit65535_tFilterRow_6().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tFilterRow_6", "tFilterRow_6", "tFilterRow");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tFilterRow_6 = 0;
				int nb_line_ok_tFilterRow_6 = 0;
				int nb_line_reject_tFilterRow_6 = 0;

				class Operator_tFilterRow_6 {
					private String sErrorMsg = "";
					private boolean bMatchFlag = true;
					private String sUnionFlag = "&&";

					public Operator_tFilterRow_6(String unionFlag) {
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
				 * [tFilterRow_6 begin ] stop
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
									+ ("TotalTransaction") + ", INPUT_COLUMN=" + ("TransactionValue") + ", IGNORE_NULL="
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

					String s_CustomerId_CustomerId_tAggregateRow_1 = String
							.valueOf(aggregated_row_tAggregateRow_1.CustomerId);
					row3.CustomerId = ParserUtils.parseTo_Integer(s_CustomerId_CustomerId_tAggregateRow_1);

					if (aggregated_row_tAggregateRow_1.TotalTransaction_sum != null) {
						row3.TotalTransaction = aggregated_row_tAggregateRow_1.TotalTransaction_sum.doubleValue();

					} else {

						row3.TotalTransaction = null;

					}

					if (log.isDebugEnabled())
						log.debug("tAggregateRow_1_AGGIN - "
								+ ("Operation function: 'sum' on the column 'TransactionValue'."));
					row3.TransactionYear = aggregated_row_tAggregateRow_1.TransactionYear;

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
					 * [tFilterRow_6 main ] start
					 */

					s(currentComponent = "tFilterRow_6");

					if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

							, "row3", "tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn", "tFilterRow_6",
							"tFilterRow_6", "tFilterRow"

					)) {
						talendJobLogProcess(globalMap);
					}

					if (log.isTraceEnabled()) {
						log.trace("row3 - " + (row3 == null ? "" : row3.toLogString()));
					}

					row4 = null;
					row4 = null;
					Operator_tFilterRow_6 ope_tFilterRow_6 = new Operator_tFilterRow_6("&&");
					ope_tFilterRow_6.matches(
							(("ALL".equalsIgnoreCase(context.targetYear))
									|| (!"ALL".equalsIgnoreCase(context.targetYear)
											&& row3.TransactionYear == Integer.parseInt(context.targetYear))),
							"advanced condition failed");

					if (ope_tFilterRow_6.getMatchFlag()) {
						if (row4 == null) {
							row4 = new row4Struct();
						}
						row4.CustomerId = row3.CustomerId;
						row4.TotalTransaction = row3.TotalTransaction;
						row4.TransactionYear = row3.TransactionYear;
						log.debug("tFilterRow_6 - Process the record " + (nb_line_tFilterRow_6 + 1) + ".");

						if (row4 == null) {
							row4 = new row4Struct();
						}
						row4.CustomerId = row3.CustomerId;
						row4.TotalTransaction = row3.TotalTransaction;
						row4.TransactionYear = row3.TransactionYear;
						log.debug("tFilterRow_6 - Process the record " + (nb_line_tFilterRow_6 + 1) + ".");

						nb_line_ok_tFilterRow_6++;
					} else {
						nb_line_reject_tFilterRow_6++;
					}

					nb_line_tFilterRow_6++;

					tos_count_tFilterRow_6++;

					/**
					 * [tFilterRow_6 main ] stop
					 */

					/**
					 * [tFilterRow_6 process_data_begin ] start
					 */

					s(currentComponent = "tFilterRow_6");

					/**
					 * [tFilterRow_6 process_data_begin ] stop
					 */

// Start of branch "row4"
					if (row4 != null) {

						/**
						 * [tAdvancedHash_row4 main ] start
						 */

						s(currentComponent = "tAdvancedHash_row4");

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row4", "tFilterRow_6", "tFilterRow_6", "tFilterRow", "tAdvancedHash_row4",
								"tAdvancedHash_row4", "tAdvancedHash"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row4 - " + (row4 == null ? "" : row4.toLogString()));
						}

						row4Struct row4_HashRow = new row4Struct();

						row4_HashRow.CustomerId = row4.CustomerId;

						row4_HashRow.TotalTransaction = row4.TotalTransaction;

						row4_HashRow.TransactionYear = row4.TransactionYear;

						tHash_Lookup_row4.put(row4_HashRow);

						tos_count_tAdvancedHash_row4++;

						/**
						 * [tAdvancedHash_row4 main ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_begin ] start
						 */

						s(currentComponent = "tAdvancedHash_row4");

						/**
						 * [tAdvancedHash_row4 process_data_begin ] stop
						 */

						/**
						 * [tAdvancedHash_row4 process_data_end ] start
						 */

						s(currentComponent = "tAdvancedHash_row4");

						/**
						 * [tAdvancedHash_row4 process_data_end ] stop
						 */

					} // End of branch "row4"

					/**
					 * [tFilterRow_6 process_data_end ] start
					 */

					s(currentComponent = "tFilterRow_6");

					/**
					 * [tFilterRow_6 process_data_end ] stop
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
				 * [tFilterRow_6 end ] start
				 */

				s(currentComponent = "tFilterRow_6");

				globalMap.put("tFilterRow_6_NB_LINE", nb_line_tFilterRow_6);
				globalMap.put("tFilterRow_6_NB_LINE_OK", nb_line_ok_tFilterRow_6);
				globalMap.put("tFilterRow_6_NB_LINE_REJECT", nb_line_reject_tFilterRow_6);

				log.info("tFilterRow_6 - Processed records count:" + nb_line_tFilterRow_6 + ". Matched records count:"
						+ nb_line_ok_tFilterRow_6 + ". Rejected records count:" + nb_line_reject_tFilterRow_6 + ".");

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row3", 2, 0,
						"tAggregateRow_1_AGGIN", "tAggregateRow_1_AGGIN", "tAggregateIn", "tFilterRow_6",
						"tFilterRow_6", "tFilterRow", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tFilterRow_6 - " + ("Done."));

				ok_Hash.put("tFilterRow_6", true);
				end_Hash.put("tFilterRow_6", System.currentTimeMillis());

				/**
				 * [tFilterRow_6 end ] stop
				 */

				/**
				 * [tAdvancedHash_row4 end ] start
				 */

				s(currentComponent = "tAdvancedHash_row4");

				tHash_Lookup_row4.endPut();

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row4", 2, 0,
						"tFilterRow_6", "tFilterRow_6", "tFilterRow", "tAdvancedHash_row4", "tAdvancedHash_row4",
						"tAdvancedHash", "output")) {
					talendJobLogProcess(globalMap);
				}

				ok_Hash.put("tAdvancedHash_row4", true);
				end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row4 end ] stop
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
				 * [tFileInputDelimited_2 finally ] start
				 */

				s(currentComponent = "tFileInputDelimited_2");

				cLabel = "TransactionData1";

				/**
				 * [tFileInputDelimited_2 finally ] stop
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
				 * [tFilterRow_6 finally ] start
				 */

				s(currentComponent = "tFilterRow_6");

				/**
				 * [tFilterRow_6 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row4 finally ] start
				 */

				s(currentComponent = "tAdvancedHash_row4");

				/**
				 * [tAdvancedHash_row4 finally ] stop
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
		final testj testjClass = new testj();

		int exitCode = testjClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'testj' - Done.");
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
		log.info("TalendJob: 'testj' - Start.");

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
		org.slf4j.MDC.put("_jobRepositoryId", "_ntyZACzGEfG6M9r5cJ5lcA");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2026-03-31T06:01:51.291723Z");

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
			java.io.InputStream inContext = testj.class.getClassLoader()
					.getResourceAsStream("telco/testj_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = testj.class.getClassLoader()
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
		log.info("TalendJob: 'testj' - Started.");
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
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : testj");
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
		log.info("TalendJob: 'testj' - Finished - status: " + status + " returnCode: " + returnCode);

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
 * 215744 characters generated by Talend Cloud Data Management Platform on the
 * 31 March 2026 at 2:01:51 pm MYT
 ************************************************************************************************/