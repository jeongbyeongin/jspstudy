package domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class XeDto {
	private int xeNo;
	private String xeContent;
	private Timestamp xeCreatedAt; 
}
