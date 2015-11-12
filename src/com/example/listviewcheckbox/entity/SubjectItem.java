package com.example.listviewcheckbox.entity;

/**
 * 主题项目类
 * @author zoupeiyang
 *
 */
public class SubjectItem {
	/**
	 * 主题id
	 */
	private String subjectId;
	/**
	 * 主题名称
	 */
	private String subjectName;
	/**
	 * 主题id
	 */
	private String itemId;
	/**
	 * 主题名称
	 */
	private String itemName;
	/**
	 * 是否多选
	 */
	private Boolean  isMultiChoice;
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Boolean getIsMultiChoice() {
		return isMultiChoice;
	}
	public void setIsMultiChoice(Boolean isMultiChoice) {
		this.isMultiChoice = isMultiChoice;
	}
	
	

}
