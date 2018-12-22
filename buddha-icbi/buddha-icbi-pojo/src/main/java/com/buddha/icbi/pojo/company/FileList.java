package com.buddha.icbi.pojo.company;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
public class FileList implements Serializable{
	
	private Integer uid;
	private String url;
	private String status;
	private String ossFileUrl;
}
