/**
 * 
 */
package org.openkoala.common.core.constants;

/**
 * @author xiongp
 *
 */
public enum Availability {
	unavailable,available;
	
	public static Availability get(int code){
		switch (code) {
		case 0:
			return Availability.unavailable;
		case 1:
			return Availability.available;
		default:
			return null;
		}
	}
}
