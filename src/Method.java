import java.util.*;

public abstract class Method {
	protected List<String> _kb;	
	public Method(List<String> kb)
    {
    	_kb = kb;
    }
	
	public abstract void solve(NLTranslator NLT);

}
