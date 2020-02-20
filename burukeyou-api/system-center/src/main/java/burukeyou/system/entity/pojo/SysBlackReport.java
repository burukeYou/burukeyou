package com.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysBlackReport  implements Serializable {

	private String id;

	private String userId;

	private String userNickname;

	private String useredId;

	private String useredNickname;

	private String reasonId;

	private String reasonName;

	private String useredObjectId;

	private String useredObjectContent;

	private String description;

	private Short state;

	private Short deleted;

	private java.util.Date createdTime;

	private java.util.Date updatedTime;

	private String createdBy;

	private String updatedBy;

}
