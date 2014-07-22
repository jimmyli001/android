package com.venus.carpapa.vo;

import java.util.ArrayList;

public class ChildTargetList_secend {

	private String targetName;
	private ArrayList<ChildTargeList_thread> mChildTargeList_threads;

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public ArrayList<ChildTargeList_thread> getmChildTargeList_threads() {
		return mChildTargeList_threads;
	}

	public void setmChildTargeList_threads(
			ArrayList<ChildTargeList_thread> mChildTargeList_threads) {
		this.mChildTargeList_threads = mChildTargeList_threads;
	}

	@Override
	public String toString() {
		return "ChildTargetList_secend [targetName=" + targetName
				+ ", mChildTargeList_threads=" + mChildTargeList_threads + "]";
	}

}
