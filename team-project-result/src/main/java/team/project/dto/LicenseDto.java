package team.project.dto;

import lombok.Data;

@Data
public class LicenseDto {
	private int userIndex;
	private String licenseType;
	private String licenseNum;
	private String licensePassDate;
}
