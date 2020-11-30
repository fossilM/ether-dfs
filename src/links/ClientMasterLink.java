package links;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import utils.FileNode;

/**
 * @author mohamf1
 *
 *         Methods the client can invoke on the master server.
 *
 */

public interface ClientMasterLink extends Remote {

	int getClientCount() throws RemoteException;

	ArrayList<String> listFilesAtCWD(FileNode cwdNode) throws RemoteException;

	FileNode getRootNode() throws RemoteException;

	public String[] getRandomMinionInfo() throws RemoteException;
	
	int assignClientID() throws RemoteException;

}
