package com.puterblade.jcatbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jsoup.*;

/*
 * @author ComputerBlade
 */

public class JCatbox {
	// ******************* FILE MANAGEMENT *******************
	

	
	/*
	 * Uploads files with user hash
	 * @param userHash User hash of account
	 * @param filePath Path of the file to be uploaded
	 */
	public String uploadFileToAccount(String userHash, String filePath) throws IOException {
		File file = new File(filePath);
		
		try (InputStream inputStream = new FileInputStream(file)) {
			Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
	                .data("reqtype", "fileupload")
	                .data("userhash", userHash)
	                .data("fileToUpload", file.getName(), inputStream)
	                .method(Connection.Method.POST)
	                .execute();
	        
	        return response.body();
	        
	        
		}
	}
	
	//Uploads files anonymously i.e. w/o user hash...
	public String uploadFileAnonymously(String filePath) throws IOException {
		File file = new File(filePath);
		
		try (InputStream inputStream = new FileInputStream(file)) {
			Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
	                .data("reqtype", "fileupload")
	                .data("fileToUpload", file.getName(), inputStream)
	                .method(Connection.Method.POST)
	                .execute();
	        
	        return response.body();
	        
	        
		}
	}
	
	//Uploads files W/ user hash
		public String uploadFileToAccountFromOtherURL(String userHash, String url) throws IOException {
			Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
	                .data("reqtype", "urlupload")
	                .data("userhash", userHash)
	                .data("url", url)
	                .method(Connection.Method.POST)
	                .execute();
	        
	        return response.body();
	        
	        
		}
		
		//Uploads files anonymously i.e. w/o user hash...
		public String uploadFileAnonymouslyFromOtherURL(String url) throws IOException {
			Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
	                .data("reqtype", "urlupload")
	                .data("url", url)
	                .method(Connection.Method.POST)
	                .execute();
	        
	        return response.body();
	        
	        
		}
	
	// Deletes specified files from account. NOTE: User hash is must!
	
	public String deleteFiles(String userHash, String filesToDelete) throws IOException {
		Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
                .data("reqtype", "deletefiles")
                .data("userhash", userHash) // IMPORTANT!!
                .data("files", filesToDelete)
                .method(Connection.Method.POST)
                .execute();
        
        return response.body(); // Returns 'Files successfully deleted.' if successfully done so.
	}
	
	// ******************* ALBUM MANAGEMENT *******************
	// NOTE 1: For an anonymous album, don't give a user hash. ---|
	// Albums created anonymously CANNOT be edited or deleted. <--|
	// NOTE 2: Each album can contain max. 500 files at the time of writing...
	
	// Creates anonymous album therefore it cannot be deleted.
	// NOTE 1: The 'filesToAdd' argument should be SINGLE SPACE SEPARATED FILES -----|
	// which ALREADY EXIST ON CATBOX! <----------------------------------------------|
	// NOTE 2: Duplicate file entries in the 'filesToAdd' argument will be removed, -|
	// but please don't do that anyway. <--------------------------------------------|
	
	public String createAnonymousAlbum(String title, String description, String filesToAdd) throws IOException {
		if (title.isBlank() || title.isEmpty()) {
			return "No album title present.";
		} else {
			Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
	                .data("reqtype", "createalbum")
	                .data("title", title)
	                .data("description", description)
	                .data("files", filesToAdd)
	                .method(Connection.Method.POST)
	                .execute();
	        
	        return response.body();
		}
	}
	
	public String createAlbumInAccount(String userHash, String title, String description, String filesToAdd) throws IOException {
		if (title.isBlank() || title.isEmpty()) {
			return "No album title present.";
		} else {
			Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
	                .data("reqtype", "createalbum")
	                .data("userhash", userHash)
	                .data("title", title)
	                .data("description", description)
	                .data("files", filesToAdd)
	                .method(Connection.Method.POST)
	                .execute();
	        
	        return response.body();
		}
	}
	
	// Edits an album.
	// NOTE 1: EDITALBUM IS A VERY POWERFUL REQUEST TYPE. You MUST supply every argument, or else it will be taken as "". Think of it as a direct input.
	// NOTE 2: The "shortCode" is the 6 alphanumeric characters in the URL that's generated.
	
	public String editAlbum(String userHash, String shortCode, String title, String description, String filesToAdd) throws IOException {
		if (title.isBlank() || title.isEmpty()) {
			return "No album title present.";
		} else {
			Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
	                .data("reqtype", "editalbum")
	                .data("userhash", userHash)
	                .data("short", shortCode)
	                .data("title", title)
	                .data("description", description)
	                .data("files", filesToAdd)
	                .method(Connection.Method.POST)
	                .execute();
	        
	        return response.body();
		}
	}
	
	// Adds files to album using the short code.
	// Also, user hash is required.
	
	public String addFilesToAlbum(String userHash, String shortCode, String filesToAdd) throws IOException {
		Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
                .data("reqtype", "addtoalbum")
                .data("userhash", userHash)
                .data("short", shortCode)
                .data("files", filesToAdd)
                .method(Connection.Method.POST)
                .execute();
		
		return response.body();
	}
	
	// Removes specified files from albums.
	// User hash is must!
	
	public String removeFilesFromAlbumWithUserHash(String userHash, String shortCode, String filesToRemove) throws IOException {
		Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
                .data("reqtype", "removefromalbum")
                .data("userhash", userHash)
                .data("short", shortCode)
                .data("files", filesToRemove)
                .method(Connection.Method.POST)
                .execute();
		
		return response.body();
	}
	
	// Deletes an album.
	// User hash is must!
	
	public String removeFilesFromAlbum(String userHash, String shortCode) throws IOException {
		Connection.Response response = Jsoup.connect("https://catbox.moe/user/api.php")
                .data("reqtype", "deletealbum")
                .data("userhash", userHash)
                .data("short", shortCode)
                .method(Connection.Method.POST)
                .execute();
		
		return response.body();
	}
}
