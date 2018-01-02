import java.io.*;
import java.util.HashMap;

public class SymbolTable
{
	static HashMap<String, String> passTime = new HashMap<>();
	
	public SymbolTable()
	{
		passTime = new HashMap<String, String>();
	}
	
	public void addEntry(String key, String value)
	{
		passTime.put(key, value);
	}
	
	public boolean containKey(String key)
	{
		return passTime.containsKey(key);
	}
	
	public String getValue(String value)
	{
		return passTime.get(value);
	}
	
}