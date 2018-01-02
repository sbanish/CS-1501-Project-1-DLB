
public class LinkedList
{
	public Node head;
	public int size;
	public LinkedList myList = this;
	public int i;
	
	public LinkedList(char c)
	{
		Node newNode = new Node(c);
		head = newNode;
		newNode.myList = this;
		size++;
	}
	
	
	public boolean contains(char c)
	{
		boolean check = false;
		Node temp = head;
		
		while (temp != null)
		{
			if (temp.getData() == c)
			{
				check = true;
				break;
			}
			else
			{
				temp = temp.sibling;
			}
		}
		return check;
	}
}