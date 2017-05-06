import java.util.*;

public class TruthTable extends Method{
	public List<String> _variables;
	private boolean _table[][];
	public TruthTable(List<String> kb, List<String> variables) {
		super(kb);
		_variables = variables;
		InitTT(_variables.size());
	}

	private void InitTT(int numberOfVar) {
		int ttSize = (int)Math.pow(2, numberOfVar);
		_table  = new boolean[ttSize][numberOfVar];
		for(int i=0; i<ttSize;i++)
		{
			String binaryRow = Integer.toBinaryString(i);
			
			while (binaryRow.length() < numberOfVar)
				binaryRow = '0'+binaryRow;
			
			for(int j=0; j < numberOfVar; j++)
			{
			   if( binaryRow.charAt(j) == '1')
				   _table[i][j] = true;
			   else
				   _table[i][j] = false;
			}
			//System.out.println(binaryRow);
		}
	}
	
	private int getIndexOf(String var) {
		for(int i=0; i<_variables.size();i++) {
			if(_variables.get(i).equals(var))
				return i;
		}
		return 0;
	}
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
	
	@Override
	public void solve(NLTranslator NLT) {
		int finalCount = 0;
		boolean rowResult;
		for(int i=0; i < _table.length; i++) {
			rowResult = true; 
			for(String rule : _kb ) {
				String[] parts = rule.split("=>");
				if(parts.length == 2) {
					String[] depVars = parts[0].split("&");
					String[] empVars = parts[1].split("&");
					int[] varIndex = new int[depVars.length];
					int[] curIndex = new int[empVars.length];
					
					for(int j=0; j < depVars.length; j++){
						varIndex[j] = getIndexOf(depVars[j]);
					}
					for(int k=0; k<curIndex.length; k++) {
						curIndex[k] = getIndexOf(empVars[k]);
					}
					boolean partOne = false;
					for(int col : varIndex) {
						if(_table[i][col] == false) {
							partOne = false;
							break;
						}else {
							partOne = true;
						}
					}
					if(partOne == true){
						for(int col : curIndex) {
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
			if(rowResult == true) {
				PrintRow(i,NLT);
				finalCount++;
			}
		}
		
		if(finalCount < 1) 
			System.out.println("NO:");
		else 
			System.out.println("YES:"+finalCount);
	}
}
