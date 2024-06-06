package util;

import java.io.BufferedReader;

//packagepackage utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;

import model.Comment;
import model.CommentBag;
import model.User;
import model.UserBag;

public class ReadAndWriteFromTxtFile {
	private static File Userfile = new File("src\\savedSources\\savedUsers.txt");
	private static File Commentfile = new File("src\\savedSources\\savedPosts.txt");

	public static void writeUsersToTxtFile() {
		try {
			ObjectOutputStream writeUsers = new ObjectOutputStream(new FileOutputStream(Userfile));
			writeUsers.writeObject(UserBag.getBag());
			writeUsers.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeCommentsToTxtFile() {
//		try {
//			ObjectOutputStream writeComments = new ObjectOutputStream(new FileOutputStream(Commentfile));
//			writeComments.writeObject(CommentBag.getComments());
//			writeComments.close();
//		} catch (IOException e) {
//			 e.printStackTrace();
//		}
	}

	public static void ReadUsersFromTxtFile() {
		
		try (ObjectInputStream read = new ObjectInputStream(new FileInputStream(Userfile))) {
			
			TreeSet<User> holdObject = (TreeSet<User>) read.readObject();
			UserBag.setTree(holdObject);
		
		} catch (ClassNotFoundException | IOException e) {
			// Handle exception
		}

	}

	public static void ReadCommentsFromTxtFile() {
		
//	    try (ObjectInputStream read = new ObjectInputStream(new FileInputStream(Commentfile))) {
//	        LinkedList<Comment> holdObject = (LinkedList<Comment>) read.readObject();
//
//	        if (holdObject != null && UserBag.isThereSpaceForANewUser()) {
//	            for (int i = 0; i < holdObject.size(); i++) {
//	                CommentBag.addComment(holdObject.get(i));
//	            }
//	        }
//	    } catch (ClassNotFoundException | IOException e) {
//	        // Handle exception
//	    }
		
	}

	public static void ReadDictionary() throws IOException {
		BufferedReader read = new BufferedReader(new FileReader("dictionary/dictionary.txt"));
		// Hash dictionary = new Hash();
		String word;
		while ((word = read.readLine()) != null) {
			Hash.addToDictionary(word);
		}
		read.close();
	}

}
