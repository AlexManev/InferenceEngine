import java.util.*;

public class NLTranslator {
	private Map<String,String> _varSenMap;
	public NLTranslator() {
		_varSenMap = new HashMap<>();
	}
	
	public String GetNLfromVar(String var) {
		for(Map.Entry<String, String> entry : _varSenMap.entrySet()) {
			if(entry.getValue().equals(var)) {
				return entry.getKey();
			}
		}
		return var;
	}
	
	public String Translate(String nl_kb) {
		nl_kb = nl_kb.toLowerCase();
		char[] variables = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		int varsUsed = 0;
		//boolean waiting = false;
		//Map<String,String> _varSenMap = new HashMap<>();
		String kb ="";
		for(String sentence : nl_kb.split("\\.")) {
			sentence = sentence.trim();					//remove any white space.
			sentence = sentence.replaceAll("if ", "").trim();	//cut he if off because its useless.
			//Check if there is implication in the sentence.
			if(sentence.contains("then")||sentence.contains(",")) {
				String leftRule; 	
				String rightRule;
				if(sentence.contains("then")) {
					leftRule = sentence.replaceAll("then([\\s\\S]*)", "").trim();	// cut everything after "then".
					rightRule = sentence.replaceAll("^(.*?)then", "").trim();		// cut everything before "then".
				}else {
					leftRule = sentence.replaceAll(",([\\s\\S]*)", "").trim();
					rightRule = sentence.replaceAll("^(.*?),", "").trim();
				}
				String rightVariable = null;
				rightVariable = _varSenMap.get(rightRule);
				if(rightVariable == null) {
					rightVariable = Character.toString(variables[varsUsed]);
					_varSenMap.put(rightRule, Character.toString(variables[varsUsed]));
					varsUsed++;
				}
				if(sentence.contains("and")) {
					String[] leftRules = leftRule.split(" and ");
					String[] leftVars = new String[leftRules.length];
					for(int i=0; i<leftRules.length; i++) {
						leftVars[i] = _varSenMap.get(leftRules[i]);
						if(leftVars[i] == null) {
							leftVars[i] = Character.toString(variables[varsUsed]);
							_varSenMap.put(leftRules[i], Character.toString(variables[varsUsed]));
							varsUsed++;
						}
					}
					for(int i=0; i<leftVars.length; i++) {
						if (i==leftVars.length-1)
							kb = kb + leftVars[i];
						else
							kb = kb +leftVars[i]+"&";
					}
	
					kb = kb + "=>"+ rightVariable;
					
				}else {	
					String leftVariable = _varSenMap.get(leftRule);
					if(leftVariable == null) {
						leftVariable = Character.toString(variables[varsUsed]);
						_varSenMap.put(leftRule, Character.toString(variables[varsUsed]));
						varsUsed++;
					}
					kb=	kb + leftVariable + "=>" +rightVariable;		
				}
			}else {
				String variable = _varSenMap.get(sentence);
				if(variable == null) {
					variable = Character.toString(variables[varsUsed]);
					_varSenMap.put(sentence, Character.toString(variables[varsUsed]));
					varsUsed++;
				}
				kb=kb+variable;
			}
			kb=kb+";";
		}
		return kb;
	}
}
