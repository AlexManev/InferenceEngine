import java.util.*;
/**
 * TruthTable extends Method, Initializes truth table for all given variables, 
 * checks whether KB is true and counts how manu times KB|=alpha. 
 *
 * @author Aleksandar_Manev
 */
public class TruthTable extends Method{
	public List<String> _variables;
	public String _alpha;
	private boolean _table[][];
	public TruthTable(List<String> kb,String alpha, List<String> variables) {
		super(kb);
		_alpha = alpha;
		_variables = variables;
		InitTT(_variables.size());
	}
	/**
	 * Initialize truth table in multidirectional array for given number of variables
	 *@param numberOfVar represents the number of colons in the truth table
	 */
	private void InitTT(int numberOfVar) {
		//number of table rows is equals to 2 on power of number of variables
		int ttSize = (int)Math.pow(2, numberOfVar);
		_table  = new boolean[ttSize][numberOfVar];

		for(int i=0; i<ttSize;i++)
		{
			//foreach row in the table get binary value of the row number
			String binaryRow = Integer.toBinaryString(i);
			//make sure all binary values are numberOfVar bit number
			while (binaryRow.length() < numberOfVar)
				binaryRow = '0'+binaryRow;
			//populate the table array with boolean values representing 0 and 1 
			for(int j=0; j < numberOfVar; j++)
			{
			   if( binaryRow.charAt(j) == '1')
				   _table[i][j] = true;
			   else
				   _table[i][j] = false;
			}
		}
	}
	/**
	 * Used to find which colon is assigned to which variable
	 * @param var variable name
	 * @return index number or null if the variable is unknown.
	 */
	private Integer getIndexOf(String var) {
		for(int i=0; i<_variables.size();i++) {
			if(_variables.get(i).equals(var))
				return i;
		}
		return null;
	}
	/**
	 * Used for testing purposes, this method prints the row number 
	 * and all the vairables values in this row.
	 * @param row accepts int row number that needs to be printed
	 * @param NLT accepts Natural language translator to translate variable to natural language
	 */
	private void PrintRow(int row, NLTranslator NLT) {
		System.out.print("Row:" + row);
		for(int col=0;col<_variables.size();col++) {
			String value = _table[row][col] ? "1" : "0";
			if(NLT == null)
				System.out.print(" "+_variables.get(col)+"="+value);
			else {
				System.out.print(" "+NLT.GetNLfromVar(_variables.get(col))+"="+value);
			}
				
		}
		System.out.print("\n");
	}
	
	/**
	 * The solve method is the method where the truth table solving algorithm is implemented
	 * when this method is called the program uses the pre initialized truth table to check 
	 * each rule in the KB and decide if the KB|=alpha  
	 * @param NLT Natural language translator to translate variables to natural language statements.
	 */
	@Override
	public void solve(NLTranslator NLT) {
		//number of times KB|=alpha
		int finalCount = 0;
		//is KB true for this world.
		boolean rowResult;
		//KB|=alpha
		boolean kBentailsAlpha = true;
		//for each table row.
		for(int i=0; i < _table.length; i++) {
			rowResult = true;
			//check every rule. 
			for(String rule : _kb ) {
				//split rules on left part and right part of the implication.
				String[] parts = rule.split("=>");
				//if implication exists
				if(parts.length == 2) {
					//find variable on the left side of the implication
					String[] depVars = parts[0].split("&");
					//find variable on the right side of the implication
					String[] empVars = parts[1].split("&");
					int[] varIndex = new int[depVars.length];
					int[] curIndex = new int[empVars.length];
					
					//assign index value for each variable
					for(int j=0; j < depVars.length; j++){
						varIndex[j] = getIndexOf(depVars[j]);
					}
					for(int k=0; k<curIndex.length; k++) {
						curIndex[k] = getIndexOf(empVars[k]);
					}
					//set left part as false;
					boolean partOne = false;
					//foreach variable on the left side of the implication
					for(int col : varIndex) {
						//check if any of them is false
						if(_table[i][col] == false) {
							partOne = false;
							break;
						}else {

							partOne = true;
						}
					}
					//is left part challenging the rule?
					if(partOne == true){
						for(int col : curIndex) {
							//check if any rule of the right side is false, this automaticly makes the whole right side false
							if(_table[i][col] == false) {
								rowResult = false;
							}
						}
					}
				}else{
					String[] empVars = parts[0].split("&");
					int[] curIndex = new int[empVars.length];
					for(int k=0; k<curIndex.length; k++) {
						curIndex[k] = getIndexOf(empVars[k]);
					}
					for(int col : curIndex) {
						if(_table[i][col] == false) {
							rowResult = false;
						}
					}
				}
			}
			//if KB is true for this row
			if(rowResult == true) {
				//check if KB entails query alpha if and only if alpha is true in all worlds wherein KB is true.
				if(!_alpha.contains("~")) {
					if(getIndexOf(_alpha) != null && _table[i][getIndexOf(_alpha)]) {
						PrintRow(i,NLT);
						finalCount++;
					}else{
						kBentailsAlpha = false;
						break;
					}
				}else {
					String alphaVar = _alpha.substring(1);
					if(getIndexOf(alphaVar) != null && !_table[i][getIndexOf(alphaVar)]) {
						PrintRow(i,NLT);
						finalCount++;
					}else{
						kBentailsAlpha = false;
						break;
					}
				}
					
			}
		}
		if(!kBentailsAlpha) 
			System.out.println("NO:");
		else 
			System.out.println("YES:"+finalCount);
	}
}