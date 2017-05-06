import java.util.*;

public class InferenceEngine {
	private List<String> _kb;
	private String _query;
	private List<String> _variables;
	private boolean _isNL;
	private NLTranslator NLT;
	
	public InferenceEngine(String kb, String query) {
		_kb = new ArrayList<String> ();
		_variables = new ArrayList<String> ();
		NLT = new NLTranslator();
		if(!kb.contains(";")) {
			_isNL = true;
			kb = NLT.Translate(kb);
			_query = NLT.Translate(query.replaceAll("\\.", "")).replaceAll(";", "");
		}else {
			_isNL = false;
			kb = removeWhiteSpace(kb);
			_query = removeWhiteSpace(query);
		}
		String[] parts_kb = kb.split(";");
		for(String part : parts_kb) {
			_kb.add(new String(part));
		}
		
		
		InitVariables();
		
		//String nls = "If I'm sad, I'm crying. If I'm sad and I'm sick then I'm sleepy. If i'm sleepy and I'm crying and you're sick then I'm jumping. You're sick.";
		//System.out.println(nls);
		//System.out.println(NLT.Translate(nls));
	}
	
	private void InitVariables() {
		for(String rule : _kb) {
			for(String part : rule.split("=>")) {
				for(String variable : part.split("&")) {
					if(_variables.contains(variable))
						continue;
					_variables.add(variable);
				}
			}
		}
	}
	
	private String removeWhiteSpace(String thisString)
	{
		String[] parts = thisString.split(" ");
		String result = "";
		for(String part : parts)
		{
			result += part;
		}
		return result;
	}

	public void Solve (String method) {
		Method engine;
		switch(method)
		{
			case "TT":	
				engine = new TruthTable(_kb, _variables);
				break;
			case "FC":
				engine = new ForwardChaining(_kb,_query);
				break;
			case "BC":
				engine = new BackwardsChaining(_kb,_query);
				break;
			default:
				engine = null;
				System.out.println("Enter TT, FC or BC as first argument.");
		}
		if(_isNL)
			engine.solve(NLT);
		else
			engine.solve(null);
	}
}
