package com.tu.sofia.bg.model;

/**
 * This class represent's a current User in the system
 * 
 * @author GPetkov
 * 
 */
public class User extends ModelBase {
	private Integer id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String role;

	/**
	 * @return User's first name
	 * 
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName
	 *            User's first name
	 * 
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return User's second name
	 * 
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName
	 *            User's last name
	 * 
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return User's user name
	 * 
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName
	 *            User's user name
	 * 
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return User's password
	 * 
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 *            User's password
	 * 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return User's role
	 * 
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role
	 *            User's role
	 * 
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return User's id
	 * 
	 */
	public Integer getId() {
		return id;
	}
}
