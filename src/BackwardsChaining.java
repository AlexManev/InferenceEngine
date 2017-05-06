import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BackwardsChaining extends Method{
	private String _query;
	private List<String> _trueValues;
	private List<String> _frontier;
	public BackwardsChaining(List<String> kb, String query) {
		super(kb);
		_trueValues = new ArrayList<String>();
		_frontier = new ArrayList<String>();
		_query = query;
		_frontier.add(_query);
	}
	
	@Override
	public void solve(NLTranslator NLT) {
		boolean result = false;
		while (!_frontier.isEmpty()){
			String current = _frontier.get(0);
			for(String rule : _kb) {
				String[] parts = rule.split("=>");
				if(parts.length == 2){
					if(current.equals(parts[1])) {
						for(String var : parts[0].split("&")) {
							if(!_frontier.contains(var) && !_trueValues.contains(var)){
								_frontier.add(var);
							}
						}
					}
				}
			}
			_trueValues.add(current);
			_frontier.remove(current);
		}
		if(_trueValues.size() > 1)
			result = true;
		
		End(result,NLT);
	}

	private void End(boolean result, NLTranslator NLT) {
		if (result) {
			System.out.print("YES:");
			Collections.reverse(_trueValues);
			for (String element : _trueValues) {
				if (NLT == null)
					System.out.print(" " + element);
				else
					System.out.print(" " + NLT.GetNLfromVar(element)+".");
			}
		} else
			System.out.println("NO:");
		
	}
}
