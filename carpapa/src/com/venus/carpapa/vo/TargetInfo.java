package com.venus.carpapa.vo;

import java.io.Serializable;
import java.util.List;

public class TargetInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TargetInfo() {
	}

	// ----------------------------get/set Method--------------------------
	public Integer getTargetId() {
		return targetId;
	}

	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getWholeType() {
		return wholeType;
	}

	public void setWholeType(String wholeType) {
		this.wholeType = wholeType;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public Integer getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Integer delFlg) {
		this.delFlg = delFlg;
	}

	public Integer getTargetGrade() {
		return targetGrade;
	}

	public void setTargetGrade(Integer targetGrade) {
		this.targetGrade = targetGrade;
	}

	public String getCheckPara() {
		return checkPara;
	}

	public void setCheckPara(String checkPara) {
		this.checkPara = checkPara;
	}

	public void setDrawingType(String drawingType) {
		this.drawingType = drawingType;
	}

	public String getDrawingType() {
		return drawingType;
	}

	public void setDrawingColor(String drawingColor) {
		this.drawingColor = drawingColor;
	}

	public String getDrawingColor() {
		return drawingColor;
	}

	public void setCoords(String coords) {
		this.coords = coords;
	}

	public String getCoords() {
		return coords;
	}

	public Integer getCoordsPage() {
		return coordsPage;
	}

	public void setCoordsPage(Integer coordsPage) {
		this.coordsPage = coordsPage;
	}

	public String getReportNote() {
		return reportNote;
	}

	public void setReportNote(String reportNote) {
		this.reportNote = reportNote;
	}

	public Integer getCheckParts() {
		return checkParts;
	}

	public void setCheckParts(Integer checkParts) {
		this.checkParts = checkParts;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public void setColorNote(String colorNote) {
		this.colorNote = colorNote;
	}

	public String getColorNote() {
		return colorNote;
	}

	public void setBlueImgurl(String blueImgurl) {
		this.blueImgurl = blueImgurl;
	}

	public String getBlueImgurl() {
		return blueImgurl;
	}

	public void setOrangeImgurl(String orangeImgurl) {
		this.orangeImgurl = orangeImgurl;
	}

	public String getOrangeImgurl() {
		return orangeImgurl;
	}

	public void setChildTargetInfoList(List<TargetInfo> childTargetInfoList) {
		this.childTargetInfoList = childTargetInfoList;
	}

	public List<TargetInfo> getChildTargetInfoList() {
		return childTargetInfoList;
	}

	// ----------------------------parameter-------------------------------
	private Integer targetId; // 验车指标ID
	private Integer parentId; // 验车指标父ID
	private Integer level; // 数据级别
	private String targetName; // 指标名称
	private Double weight; // 权值
	private String wholeType; // 整体分类(0:外观；1:内饰)
	private String checkType; // 检测分类(0:静态；1:动态)
	private Integer delFlg; // 删除标记(0:未删除；1:已删除)
	private Integer targetGrade; // 缺陷等级
	private String checkPara; // 计算字段
	private String drawingType; // 图形类型
	private String drawingColor; // 图形颜色
	private String coords; // 坐标
	private Integer coordsPage; // 子级坐标页面
	private String reportNote; // 报告意见
	private Integer checkParts; // 检查部件
	private String imgURL; // 图片路径
	private String colorNote; // 颜色说明
	private String blueImgurl; // 蓝色图片路径
	private String orangeImgurl; // 橙色图片路径
	private List<TargetInfo> childTargetInfoList; // 子级
}
