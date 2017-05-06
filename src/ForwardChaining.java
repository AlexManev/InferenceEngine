import java.util.*;

public class ForwardChaining extends Method{
	private String _query;
	private List<String> _trueValues;
	public ForwardChaining(List<String> kb, String query) {
		super(kb);
		_trueValues = new ArrayList<String>();
		_query = query;
		InitTrueVals();
	}
	
	private void InitTrueVals() {
		for(String rule : _kb) {
			if(rule.split("=>").length == 1) {
				_trueValues.add(rule);
			}
		}
	}

	private void End(boolean result, NLTranslator NLT) {
		if (result) {
			System.out.print("YES:");
			for (String element : _trueValues) {
				if (NLT == null)
					System.out.print(" " + element);
				else
					System.out.print(" " + NLT.GetNLfromVar(element)+".");
			}
		} else
			System.out.println("NO:");
	}

	@Override
	public void solve(NLTranslator NLT) {
		boolean result = false;
		boolean validRule = true;
		while (!result) {
			boolean newAdded = false;
			for(String rule : _kb) {
				String[] parts = rule.split("=>");
				if(parts.length == 2){
					validRule = true;
					for(String depVar : parts[0].split("&")) {
						if(!_trueValues.contains(depVar)) {
							validRule = false;
							break;
						}
					}
					if(validRule) {
						if(!_trueValues.contains(parts[1])) {
							newAdded = true;
							_trueValues.add(parts[1]);
						}
						if(parts[1].equals(_query)) {
							result = true;
							break;
						}
					}
				}
			}
			if(!newAdded)
				break;
		}
		End(result,NLT);
	}
	
	
}
