package com.yc.bean;

import java.io.Serializable;

/**
 *富文本编辑器上传文件所需要的实体类
 * @author dengshuangjun
 * Created by dengshuangjun on 2016/11/20.
 */
public class UploadData implements Serializable{
	
	private static final long serialVersionUID = 706863288196112278L;
	//图片名字
	private String original;
	//图片名字
	private String title;
	//图片路径
	private String url;
	//图片大小
	private long size;
	//图片类型
	private String type;
	//上传是否成功
	private String state;
	
	public UploadData() {
	}

	public UploadData(String original, String title, String url, long size,
			String type, String state) {
		this.original = original;
		this.title = title;
		this.url = url;
		this.size = size;
		this.type = type;
		this.state = state;
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "ImageData [original=" + original + ", title=" + title
				+ ", url=" + url + ", size=" + size + ", type=" + type
				+ ", state=" + state + "]";
	}
	
	
}
