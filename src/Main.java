import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static String _KB;
	public static String _query;

	public static void main(String[] args) {
		if(ReadFromFile(args[1])){
			InferenceEngine engine = new InferenceEngine(_KB, _query);
			engine.Solve(args[0]);
		}
	}
	
	public static boolean ReadFromFile(String fileName) {
		List<String> inputRows = new ArrayList<String>();
		try {
			File file = new File(fileName);
			Scanner scanner = new Scanner(file);
			for(int i=0; i<4; i++) {
				inputRows.add(scanner.nextLine());
			}
			scanner.close();
		} catch (Exception e) {
		        System.out.println("Error: Couldn't locate file '"+fileName+"'.");
		        return false;
		}
		
		_KB = inputRows.get(1);
		_query = inputRows.get(3);
		return true;
	}	
}
