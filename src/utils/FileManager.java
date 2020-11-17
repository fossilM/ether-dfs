package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileManager {
	
	private Map<String,	List<MinionLocation>> fileLocationMap;
	private Map<String,	 MinionLocation> filePrimaryMinionMap;
	
	public FileManager(){
		this.fileLocationMap = new HashMap<String, List<MinionLocation>>();
		this.filePrimaryMinionMap = new HashMap<String, MinionLocation>();
	}
	
	public void assignPrimaryMinionToFile(String filename, int primaryMinionIndex, List<MinionLocation> minionLocations){
		filePrimaryMinionMap.put(filename, minionLocations.get(primaryMinionIndex));
	}
	
	public void assignSelectedMinionsToFile(String filename, List<MinionLocation> selectedMinions){
		fileLocationMap.put(filename, selectedMinions);
	}
	
	public MinionLocation getPrimaryFileLocation(String fileName){
		return filePrimaryMinionMap.get(fileName);
	}
	
	public List<MinionLocation> getAllFileLocation(String fileName){
		return fileLocationMap.get(fileName);
	}
	
}