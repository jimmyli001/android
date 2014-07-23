package com.venus.carpapa.vo;

import java.util.List;

public class TargetInfoInterface {

	private Integer targetId;// 检测指标Id
	private Integer parentId;// 检测指标父Id
	private Integer level;// 数据级别
	private String targetName;// 检测指标名称
	private List<TargetInfoInterface> childTargetList;// 检测项目实体类集合
														// 只有当检测项目含有子级时返回
	private Integer checked;// 针对最后一级缺陷描述勾选情况，1：已选；0：未选。
							// 只有在最后一级缺陷描述中返回，当该车辆已勾选该缺陷描述时checked：1，反之checked：0。

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

	public List<TargetInfoInterface> getChildTargetList() {
		return childTargetList;
	}

	public void setChildTargetList(List<TargetInfoInterface> childTargetList) {
		this.childTargetList = childTargetList;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "TargetInfoInterface [targetId=" + targetId + ", parentId="
				+ parentId + ", level=" + level + ", targetName=" + targetName
				+ ", childTargetList=" + childTargetList + ", checked="
				+ checked + "]";
	}

}
