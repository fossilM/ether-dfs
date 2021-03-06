package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;

public class FileContent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6709795798632515110L;
	private String filename;
	private byte[] bytes;
	private File file;
	private boolean primary; // 1 = primary; 0 = secondary

	public FileContent(String filename) {
		this.filename = filename;
		file = new File(filename);
		try {
			this.bytes = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void writeByte(String filePath) {
		File transferredFile = new File(filePath);
		if (transferredFile.getParentFile() != null) {
			System.out.println(transferredFile.getParentFile().toString());
			if (!transferredFile.getParentFile().exists()) {
				transferredFile.getParentFile().mkdirs();
			}
		}
		try {

			OutputStream os = new FileOutputStream(transferredFile);
			os.write(this.bytes);
			os.close();
		}

		catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	public void delete() {
		this.file.delete();
	}

	public String getName() {
		return filename;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	public boolean isPrimary() {
		return this.primary;
	}
}
