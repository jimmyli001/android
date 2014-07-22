package com.venus.carpapa.vo;

import java.util.ArrayList;

public class ChildTargeList {

	private String targetName;
	private ArrayList<ChildTargetList_secend> mChildTargetList_secends;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public ArrayList<ChildTargetList_secend> getmChildTargetList_secends() {
		return mChildTargetList_secends;
	}

	public void setmChildTargetList_secends(
			ArrayList<ChildTargetList_secend> mChildTargetList_secends) {
		this.mChildTargetList_secends = mChildTargetList_secends;
	}

	@Override
	public String toString() {
		return "ChildTargeList [targetName=" + targetName
				+ ", mChildTargetList_secends=" + mChildTargetList_secends
				+ "]";
	}


}
