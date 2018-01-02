public class DLB
{
    private int LLCount;
    public boolean empty;
    protected LinkedList root;
    int size;
    final char sent ='&';
    private Node iterator;
    private LinkedList iteratorList;


    public DLB(String s)
    {
        LLCount = 0;
        empty=true;
        add(s);
        size = 1;
    }

    public DLB (char c)
    {
        empty=true;
        String s = new String(new Character(c).toString());
        add(s);
        size = 1;
    }

    public DLB()
    {
        empty=true;
        LLCount = 0;
        size = 0;
    }
    public DLB(StringBuilder s)
    {
        size = 1;
        LLCount = 0;
        empty=true;
        add(s.toString());
    }

    public int size() {
        return size;
    }

    public boolean add(String s)
    {
        size+=1;
        int pos=-1;
        if (empty==true)
        {
            LinkedList prevList = new LinkedList(s.charAt(0));
            root = prevList;
            for (int i = 1; i < s.length(); i++)
            {
                LinkedList curList = new LinkedList(s.charAt(i));
                prevList.head.child = curList.head;
                prevList = curList;
            }
            LinkedList sentinelList = new LinkedList(sent);
            prevList.head.child=sentinelList.head;
            empty=false;
            return true;
        }
        else
        {
            int letterFound = 0;
            int temp=0;
            Node curNode = root.head;
            for (int i = 0; i<=s.length()-1; i++)
            {

                if ((i==s.length()-1) && curNode.data==s.charAt(i))
                {

                    if (curNode.child.data!=sent)
                    {
                        curNode = curNode.child;
                        if (curNode.sibling==null)
                        {
                            curNode.sibling = new Node(sent);

                            return true;
                        }
                        else
                        {
                            Node prevNode=curNode;
                            while (curNode.sibling!=null)
                            {

                                if (curNode.data==sent)
                                {

                                    size--;
                                    return false;
                                }

                                curNode = curNode.sibling;
                            }
                            curNode.sibling=new Node(sent);
                            curNode=curNode.sibling;

                            return true;
                        }
                    }
                    else
                    {

                        size--;
                        return false;
                    }
                }
                if (curNode.data!=s.charAt(i))
                {
                    if (curNode.sibling!=null)
                    {
                        while (curNode.sibling!=null)
                        {

                            curNode = curNode.sibling;
                            if (curNode.data==s.charAt(i))
                            {

                                letterFound=1;
                                break;
                            }

                        }
                        if (letterFound==0)
                        {

                            curNode.sibling = new Node(s.charAt(i));
                            curNode = curNode.sibling;
                            temp = i+1;
                            break;
                        }
                        else
                        {
                            letterFound=0;
                        }
                    }
                    else
                    {

                        curNode.sibling = new Node(s.charAt(i));
                        curNode=curNode.sibling;
                        temp = i+1;
                        break;
                    }
                }

                curNode = curNode.child;
            }

            if (temp>s.length()-1)
            {
                LinkedList endList = new LinkedList(sent);
                curNode.child=endList.head;
                return true;
            }
            else if (temp==s.length()-1)
            {
                LinkedList newList = new LinkedList(s.charAt(temp));
                curNode.child = newList.head;
                curNode = curNode.child;
                LinkedList endList = new LinkedList(sent);
                curNode.child=endList.head;
                return true;
            }
            else
            {

                for (int i = temp; i<=s.length()-1; i++) 
                {
                    LinkedList newList = new LinkedList(s.charAt(i));
                    curNode.child=newList.head;
                    curNode=curNode.child;
                }
                LinkedList endList = new LinkedList(sent);
                curNode.child=endList.head;
                return true;
            }
        }
    }


    public int searchPrefix(String s) {
        return searchPrefix(new StringBuilder(s));
    }

    public int searchPrefix(StringBuilder s)
    {
        iteratorList = root;
        iterator = root.head;
        boolean isPrefix = false;
        boolean isWord = false;
        for (int i = 0; i<=s.length()-1; i++) 
        {
            if ((i==s.length()-1) && (searchList(iteratorList, s.charAt(i))==true)) 
            {
                isPrefix=true;
                iterator = iterator.child;
                iteratorList= iterator.myList;
                int iteratorCount=0;
                while (iterator.sibling!=null)
                {
                    iteratorCount++;
                    iterator = iterator.sibling;
                }
                if(findSentinel(iteratorList)==true)
                {
                    isWord=true;
                    if(iteratorCount<1)
                        isPrefix=false;
                    break;
                }
            }
            else if (searchList(iteratorList, s.charAt(i))==true) 
            {
                iterator = iterator.child;
                iteratorList = iterator.myList;
            }
        }
        if (isWord==true && isPrefix==true)
        {
            return 3;
        }
        else if (isWord==true && isPrefix==false)
        {
            return 2;
        }
        else if (isWord==false && isPrefix==true)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }



    public boolean contains(String target) {
        boolean ret = false;
        LinkedList curList = root;
        Node iter = curList.head;
        int targetIndex = 0;
        while (iter != null) {
            if (iter.data == target.toCharArray()[targetIndex]) {
                if (targetIndex == target.length()-1 && iter.data == target.toCharArray()[target.length()-1] && isLastLetterInWord(iter)) {
                    ret = true;
                    break;
                }
                else if (targetIndex == target.length()-1 &&
                        iter.data == target.toCharArray()[target.length()-1]) {
                    break;
                }
                else {

                    iter = iter.child;
                    curList = iter.getCurrentList();
                    targetIndex++;
                }
            }
            else {
                iter = iter.sibling;
            }

        }
        return ret;
    }

    private boolean isLastLetterInWord(Node node) {
        Node iter = new Node(node.child);
        boolean ret = false;
        while (iter != null) {
            if (iter.data == sent) {
                ret = true;
                break;
            }
            else {
                iter = iter.sibling;
            }
        }
        return ret;
    }

    protected boolean findSentinel(LinkedList curList)
    {
        iteratorList = curList;
        iterator = curList.head;
        if (searchList(curList, sent)!=true)
        {
            return false;
        }
        else
        {
            return true;
        }

    }
    protected boolean searchList(LinkedList curList, char c)
    {
        if(iteratorList.head.data==c)
        {
            return true;
        }
        else
        {
            while (iterator !=null)
            {
                if(iterator.data==c)
                {
                    return true;
                }
                iterator = iterator.sibling;
            }
            return false;
        }
    }
}