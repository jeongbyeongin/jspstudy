package com.gdu.ex.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExDto {
	private int exNo;
	private String exContent;
	private Timestamp exCreatedAt;

}
