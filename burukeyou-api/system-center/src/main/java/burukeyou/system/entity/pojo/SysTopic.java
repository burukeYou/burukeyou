package com.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysTopic  implements Serializable {

	private String id;

	private String avatar;

	private Long focusCount;

	private Long boilingCount;

	private String description;

	private java.util.Date createdTime;

	private java.util.Date updatedTime;

	private String createdBy;

	private String updatedBy;

}
