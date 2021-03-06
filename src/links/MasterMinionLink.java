package links;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author mohamf1
 *
 *         Methods the master server invokes on any individual replica server.
 *
 */
public interface MasterMinionLink extends Remote {

	boolean checkAlive() throws RemoteException;
	
	void resync() throws RemoteException;
}
