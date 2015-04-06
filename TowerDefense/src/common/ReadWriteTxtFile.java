package common;

import java.io.*;

import javax.swing.JFileChooser;

/**
 * This class groups together methods that are useful for manipulating txt
 * files.
 * 
 * @author Michael Hinton
 */
public class ReadWriteTxtFile {

	public static String[] readTxtFileAsStringArray(String txtFileName) {
		String[] s = new String[1000];
		int length = 0;
		String line = null;

		try {
			FileReader fileReader = new FileReader(txtFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			while ((line = bufferedReader.readLine()) != null) {
				length = length + 1;
				s[length - 1] = line;
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			System.err.println("Unable to open file '" + txtFileName + "'");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] r = new String[length];
		for (int i = 0; i < length; i++) {
			r[i] = s[i];
		}
		return r;
	}

	public static boolean writeTxtFileFromStringArray(
			String s) throws IOException{
		
		
		JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new File("lib/maps/custom_maps"));
	    int retrival = chooser.showSaveDialog(null);
	    if (retrival == JFileChooser.APPROVE_OPTION) {
	        try {
	        	FileWriter writer=new FileWriter(chooser.getSelectedFile()+".txt",false);
	    		PrintWriter line=new PrintWriter(writer);
	    		line.printf("%s"+"%n",s);
	    		
	    		line.close();
	    		return true;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return false;
	        }
	    }else{
	    	return false;
	    }
		
		
		
	}
}
