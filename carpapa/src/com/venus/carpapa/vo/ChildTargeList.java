package com.venus.carpapa.vo;

import java.util.ArrayList;

public class ChildTargeList {

	private String targetName;
	private int level;
	private ArrayList<ChildTargeList> mChildTargetList_secends;
	private int checked;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public ArrayList<ChildTargeList> getmChildTargetList_secends() {
		return mChildTargetList_secends;
	}

	public void setmChildTargetList_secends(
			ArrayList<ChildTargeList> mChildTargetList_secends) {
		this.mChildTargetList_secends = mChildTargetList_secends;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "ChildTargeList [targetName=" + targetName + ", level=" + level
				+ ", mChildTargetList_secends=" + mChildTargetList_secends
				+ ", checked=" + checked + "]";
	}

}
