/**
 * <p>Description: </p>
 */
package com.lin.webMC.jsonBeans;

/**
 * @author admin
 * <p>Description: </p>
 * attachmentVars:{
 * 		"attachmentType":"";		文件类型，仅为标准，没有限制文件格式的功能
 * 		"taskId":"";				待创建附件的任务Id
 * 		"processInstanceId":""; 	待创建附件的任务所在的流程Id
 * 		"attachmentName":"";		创建的附件文件名称
 *		"attachmentDescription":""; 附件文件的概要描述
 *		"url":"";					附件文件上传后的存储地址(待上传转存之后由本控制器生成后填入)
 */
public class AttachmentVars {
	
	private String attachementType;
	
	private String taskId;
	
	private String processInstanceId;
	
	private String attachmentName;
	
	private String attachmentDescription;
	
	private String url;

	/**
	 * <p>Description: </p>
	 */
	public AttachmentVars() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>Description: </p>
	 * @param attachementType
	 * @param taskId
	 * @param processInstanceId
	 * @param attachmentName
	 * @param attachmentDescription
	 * @param url
	 */
	public AttachmentVars(String attachementType, String taskId, String processInstanceId, String attachmentName,
			String attachmentDescription, String url) {
		super();
		this.attachementType = attachementType;
		this.taskId = taskId;
		this.processInstanceId = processInstanceId;
		this.attachmentName = attachmentName;
		this.attachmentDescription = attachmentDescription;
		this.url = url;
	}

	/**
	 * @return the attachementType
	 */
	public String getAttachementType() {
		return attachementType;
	}

	/**
	 * @param attachementType the attachementType to set
	 */
	public void setAttachementType(String attachementType) {
		this.attachementType = attachementType;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the processInstanceId
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * @param processInstanceId the processInstanceId to set
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * @return the attachmentName
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * @param attachmentName the attachmentName to set
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	/**
	 * @return the attachmentDescription
	 */
	public String getAttachmentDescription() {
		return attachmentDescription;
	}

	/**
	 * @param attachmentDescription the attachmentDescription to set
	 */
	public void setAttachmentDescription(String attachmentDescription) {
		this.attachmentDescription = attachmentDescription;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
