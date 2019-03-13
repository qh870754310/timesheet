package com.kcfy.techservicemarket.core;

public interface Constants {

	public interface ProjectStatus {
		Long CLOSE=-1l;
		Long OPEN=1l;
	}

	public interface SEX{
		String FEMALE ="F";
		String MALE="M";
	}
	
	public interface PartyType{
		String AGENCY_ADMIN="AA";
		String AGENCY_CONTACT="AC";
		String COMMON="C";
	}
	
	interface TimesheetStatus {
		int NONE = -1;
		int DRAFT = 0;
		int COMMITTED = 1;
		int REJECTED = 2;
		int PASSED = 3;
	}
	interface RemindType {
		int NONE = -1;
		int REJECTED = 2;
	}
}
