package com.example.lihang.mypractice;

/**
 * Created by lihang on 2017/10/17.
 */

public class MyFile {
	String name;
	boolean isFolder;

	public MyFile(String name, boolean isFolder) {
		this.name = name;
		this.isFolder = isFolder;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFolder() {
		return isFolder;
	}

}
