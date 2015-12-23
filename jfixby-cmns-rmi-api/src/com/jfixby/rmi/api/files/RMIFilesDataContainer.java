package com.jfixby.rmi.api.files;

import com.jfixby.cmns.api.file.FileInputStream;
import com.jfixby.cmns.api.file.FileOutputStream;
import com.jfixby.rmi.api.RMIFace;

public interface RMIFilesDataContainer extends RMIFace {

	boolean delete(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	FileInputStream getInputStream(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	FileOutputStream getOutputStream(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	boolean isFolder(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	boolean isFile(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	boolean exists(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	String[] listChildren(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	boolean mkdirs(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	boolean rename(java.util.List<String> relativePath, String new_name) throws java.rmi.RemoteException;

	long lastModified(java.util.List<String> relativePath) throws java.rmi.RemoteException;

	long getSize(java.util.List<String> relativePath) throws java.rmi.RemoteException;

}
