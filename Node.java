public class Node
{
	public LinkedList myList;
	public Node child = null;
	public Node sibling = null;
	public char data;
	
	public Node (char c)
	{
		data = c;
	}
	
	public Node(Node copy)
	{
		sibling = copy.sibling;
		child = copy.child;
		data = copy.data;
		myList = copy.myList;
	}
	public LinkedList getCurrentList()
	{
		return myList;
	}
	public char getData()
	{
		return data;
	}
	public void setData(char c)
	{
		data = c;
	}
	
}