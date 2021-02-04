package com.roberto.hibernate.crud.app;

import java.util.Scanner;

import com.roberto.hibernate.crud.service.HbStudentService;
import com.roberto.hibernate.model.HbStudent;

public class App {

	private static final String MENU = new StringBuilder("*************** MENU ***************  ")
												.append("\n\t[1] CREATE student")
												.append("\n\t[2] READ student")
												.append("\n\t[3] UPDATE student")
												.append("\n\t[4] DELETE student")
												.append("\n\n\n Digit ESC to exit")
												.append("\n************************************  ")
												.append("\n")
												.append("Make a choice")
												.append("\n> ")
												.toString();
	
	private static final String SUBMENU_CREATE = new StringBuilder("\t Insert student in the follow format: ")
															.append("<name>, <lastname>, <email>")
															.append("\n> ")
															.toString();
	
	private static final String SUBMENU_READ = new StringBuilder("\t Insert student id to search")
														.append("\n> ")
														.toString();
	
													
	private static final String SUBMENU_1_UPDATE =  new StringBuilder("\t Choose what to change: ")
															.append("\n\t [1] to change NAME")
															.append("\n\t [2] to change LASTNAME")
															.append("\n\t [3] to change EMAL")
															.append("\n\t [4] to change ALL")
															.toString();
													
	private static final String SUBMENU_2_UPDATE = new StringBuilder("\t Insert student id to update: ")
															.append("\n> ")
															.toString();
	
	private static HbStudentService hbStudentService = new HbStudentService();
	
	public static void main(String[] args) {
		
		System.out.print(MENU);
		Scanner inputScanner = new Scanner(System.in);
		String userInput = getUserInput(inputScanner);
		while( !userInput.toUpperCase().equals("ESC") ) {

			switch(userInput) {
				case "C":
				case "c": 
				case "CREATE":
				case "create":
				case "1": {
					System.out.print(SUBMENU_CREATE);
					String createUserInput = userInput = getUserInput(inputScanner);
					String[] createUserInfo = createUserInput.split(",");
					while(createUserInfo.length < 3) {
						System.out.println("\n\t Please insert user in follow format: <name>, <lastname>, <email>");
						System.out.print("> ");
						createUserInput = userInput = getUserInput(inputScanner);
						createUserInfo = createUserInput.split(",");
					}
					String name = createUserInfo[0].trim();
					String lastName = createUserInfo[1].trim();
					String email = createUserInfo[2].trim();
					HbStudent studentInserted = hbStudentService.insertStudent(new HbStudent(name, lastName, email));
					System.out.println("Created: " + studentInserted.getName() + " " + studentInserted.getSurname() + " " + studentInserted.getEmailAddress());
					break;
				}
				
				case "R":
				case "r":
				case "READ":
				case "read":
				case "2": {
					System.out.print(SUBMENU_READ);
					String readUserInput = userInput = getUserInput(inputScanner);
					Long id = null;
					try {
						id =  Long.valueOf(readUserInput);
					} catch (NumberFormatException e) {
						System.out.println("\n\t The id provided must be number");
					}
					if(id != null) {
						HbStudent studentFound = hbStudentService.getStudent(id);
						System.out.println("Student found: " + studentFound.getName() + " " + studentFound.getSurname() + " " + studentFound.getEmailAddress());
					}
					break;
				}
				
				case "U":
				case "u":
				case "UPDATE":
				case "update":
				case "3": {
					System.out.println(SUBMENU_1_UPDATE);
					String updateUserInput = userInput = getUserInput(inputScanner);
					boolean isChoiceValid = false;
					
					HbStudent oldStudent = null;
					String newName = null;
					String newLastName = null;
					String newEmail = null;
				
					while(!isChoiceValid) {
						switch (updateUserInput) {
							case "n":
							case "N":
							case "name":
							case "NAME":
							case "1":{
								isChoiceValid = true;
								
								System.out.println("\n\t Insert a new name");
								System.out.print("\n> ");
								String newNameInput = getUserInput(inputScanner);
								newName = newNameInput;
								break;
							}
							case "l":
							case "L":
							case "lastname":
							case "LASTNAME":
							case "2":{
								isChoiceValid = true;

								System.out.println("\n\t Insert a new lastname");
								System.out.print("\n> ");
								String newLastNameInput = getUserInput(inputScanner);
								newLastName = newLastNameInput;
								break;
							}
							case "e":
							case "E":
							case "email":
							case "EMAIL":
							case "3":{
								isChoiceValid = true;

								System.out.println("\n\t Insert a new email");
								System.out.print("\n> ");
								String newEmailInput = getUserInput(inputScanner);
								newEmail = newEmailInput;
								break;
							}
							case "a":
							case "A":
							case "all":
							case "ALL":
							case "4":{
								isChoiceValid = true;

								System.out.print( new StringBuilder("\t Insert student in the follow format: ")
														.append("<name>, <lastname>, <email>")
														.append("\n> ")
														.toString()
												);
								updateUserInput = userInput = getUserInput(inputScanner);
								String[] updateUserInfo = updateUserInput.split(",");
								while(updateUserInfo.length < 3) {
									System.out.println("\n\t Please insert user in follow format: <name>, <lastname>, <email>");
									System.out.print("> ");
									updateUserInput = userInput = getUserInput(inputScanner);
									updateUserInfo = updateUserInput.split(",");
								}
								newName = updateUserInfo[0].trim();
								newLastName = updateUserInfo[1].trim();
								newEmail = updateUserInfo[2].trim();
								
								break;
							}
							default:
								System.out.println("Operation not valid. Retry ...");
								break;
							}
					}
					
					System.out.println(SUBMENU_2_UPDATE);
					String input = getUserInput(inputScanner);
					Long id = null;
					try {
						id = Long.valueOf(input);
					} catch (NumberFormatException e) {
						System.out.println("\n\t The id provided must be number");
					}
					
					oldStudent = hbStudentService.getStudent(id);
					if(id == null) {
						System.out.println("\t Student with id provided not exists");
					} else {
						oldStudent.setName(newName != null? newName: oldStudent.getName());
						oldStudent.setSurname(newLastName != null? newLastName: oldStudent.getSurname());
						oldStudent.setEmailAddress(newEmail != null? newEmail: oldStudent.getEmailAddress());
						HbStudent studentUpdated = hbStudentService.updateStudent(oldStudent);
						System.out.println("updated " + studentUpdated.getName() + " " + studentUpdated.getSurname() + " " + studentUpdated.getEmailAddress());
					}
				}
				
				case "D":
				case "d":
				case "DELETE":
				case "delete":
				case "4": {
					System.out.println("deleted...");
					break;
				}
				default: {
					System.out.println("Operation not valid. Retry ...");
				}
			}
			
			System.out.println(MENU);
			userInput = getUserInput(inputScanner);
		}
		
		inputScanner.close();
		System.out.println("Bye bye");
	}

	private static String getUserInput(Scanner input) {
		String userInput = input.nextLine();
		while(userInput.trim().length() == 0) {
			userInput = input.nextLine();
		}
		return userInput;
	}
}