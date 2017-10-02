//Phillip Miracle
//CSC 364-001
//Creates a circularly double-linked list and the methods associated with it.
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements Cloneable
{
	Node<E> head = new Node<E>(null);
	public MyDoublyLinkedList()
	{
		head.next = head;
		head.prev = head;
	}
	public class Node<E>
	{
		E element;
		Node<E> next;
		Node<E> prev;

		public Node(E e)
		{
			this.element = e;
		}
	}
	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new listIterator(index);
	}
	//Equals Method
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		else if (!(o instanceof MyList))
		{
			return false;
		}
		else if (((MyList<E>)o).size() != this.size())
		{
			return false;
		}
		else
		{
			listIterator  oitr = (MyDoublyLinkedList<E>.listIterator) ((MyDoublyLinkedList<E>) o).listIterator(0);
			listIterator thitr = (MyDoublyLinkedList<E>.listIterator) this.listIterator(0);
			for(int i = 0; i < size; i++)
			{
				if(oitr.cur.element != thitr.cur.element)
					return false;
				oitr.next();
				thitr.next();
			}
			return true;
		}
	}

	//Clone Method
	@Override
	public Object clone()
	{
		try
		{
			MyDoublyLinkedList listClone = (MyDoublyLinkedList)super.clone();
			Node<E> newHead = new Node<E>(null);
			newHead.next = newHead;
			newHead.prev = newHead;
			int size = 0;
			listClone.head = newHead;
			listIterator itr = (MyDoublyLinkedList<E>.listIterator) this.listIterator(0);
			listIterator itr2 = (MyDoublyLinkedList<E>.listIterator) listClone.listIterator(0);
			while(itr.hasNext())
			{
				itr2.add(itr.cur.element);
				size++;
				itr.next();
			}
			listClone.size = size;
			return listClone;
		}
		catch(CloneNotSupportedException ex)
		{
			return null;
		}
	}

	//Add Methods
	public void add(E e)
	{
		addLast(e);
	}
	@Override
	public void add(int index, E e)
	{
		if (index == 0)
			addFirst(e);
		else if (index >= size)
			addLast(e);
		else
		{
			Node<E> cur = head.next;
			for (int i = 0; i < index; i++)
			{
				cur = cur.next;
			}
			Node<E> tmp = new Node<E>(e);
			tmp.prev = cur.prev;
			tmp.next = cur;
			cur.prev.next = tmp;
			cur.prev = tmp;
			size++;
		}
	}
	@Override
	public void addFirst(E e)
	{
		// TODO Auto-generated method stub
		Node<E> newN = new Node<>(e);
		head.next.prev = newN;
		newN.next = head.next;
		newN.prev = head;
		head.next = newN;
		size++;
	}

	@Override
	public void addLast(E e)
	{
		// TODO Auto-generated method stub
		Node<E> newN = new Node<>(e);
		if(head.next.element == null && head.prev.element == null)
		{
			head.next = newN;
			head.prev = newN;
		}
		else
		{
		newN.prev = head.prev;
		newN.next = head;
		head.prev.next = newN;
		head.prev = newN;
		}
		size++;
	}

	//Remove methods
	public boolean remove(E e)
	{
		remove(this.indexOf(e));
		return true;
	}
	@Override
	public E remove(int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0)
		{
			return removeFirst();
		}
		else if (index == size -1)
		{
			return removeLast();
		}
		else
		{
			Node<E> cur = head.next;
			for (int i = 0; i < index; i++)
			{
				cur = cur.next;
			}
			cur.prev.next = cur.next;
			cur.next.prev = cur.prev;
			size--;
			return cur.element;
		}
	}
	@Override
	public E removeFirst()
	{
		if(head.next.element == null)
		{
			throw new NoSuchElementException();
		}
		E e = head.next.element;
		head.next.next.prev = head;
		head.next = head.next.next;
		size--;
		return e;
	}
	@Override
	public E removeLast()
	{
		//Needs Finished
		if(head.prev.element == null)
		{
			throw new NoSuchElementException();
		}
		E e = head.prev.element;
		head.prev.prev.next = head;
		head.prev = head.prev.prev;
		size--;
		return e;
	}


	@Override
	public void clear()
	{
		head.next = head;
		head.prev = head;
		size = 0;
	}

	//Contains, get, set, and IndexOf methods
	@Override
	public boolean contains(E e)
	{
		//Needs to use equals
		Node<E> cur = head.next;
		for (int i = 0; i < size; i++)
		{
			if (cur.element.equals(e))
			{
				return true;
			}
			cur = cur.next;
		}
		return false;
	}
	@Override
	public E get(int index)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node<E> cur = head.next;
		for (int i = 0; i < index; i++)
		{
			cur = cur.next;
		}
		return cur.element;
	}
	@Override
	public int indexOf(E e)
	{
		Node<E> cur = head.next;
		for (int i = 0; i < size; i++)
		{
			if (e == null)
			{
				if(e == cur.element)
				{
					return i;
				}
			}
			else if (cur.element.equals(e))
			{
				return i;
			}
			cur = cur.next;
		}
		throw new RuntimeException();
	}
	@Override
	public int lastIndexOf(E e)
	{
		Node<E> cur = head.next;
		int last = -1;
		for (int i = 0; i < size; i++)
		{
			if (cur.element.equals(e))
			{
				last = i;
			}
			cur = cur.next;
		}
		if (last > -1)
		{
			return last;
		}
		return last;
	}
	@Override
	public Object set(int index, E e)
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		Node<E> cur = head.next;
		for (int i = 0; i < index; i++)
		{
			cur = cur.next;
		}
		Node<E> tmp = new Node<E>(e);
		tmp.next = cur.next;
		tmp.prev = cur.prev;
		cur.next.prev = tmp;
		cur.prev.next = tmp;
		return tmp.element;
	}
	@Override
	public E getFirst()
	{
		return head.next.element;
	}
	@Override
	public E getLast()
	{
		return head.prev.element;
	}

	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder("[");
		Node<E> cur = head.next;
		for (int i = 0; i < size; i++)
		{
			result.append(cur.element);
			if (cur.next != head)
			{
				cur = cur.next;
				result.append(", ");

			}
		}
		result.append("]");
		return result.toString();
	}

	public class listIterator implements java.util.ListIterator<E>
	{
		//Variable declarations
		int index;
		int n = 0;
		private Node<E> cur = head.next;
		private Node<E> pre = null;

		//Constructors
		public listIterator()
		{
			index = 0;
			cur = head.next;
		}
		public listIterator(int num)
		{
			index = num;
			cur = head.next;
			for(int i = 0; i < num; i++)
			{
				cur = cur.next;
			}
		}
		//Next Methods
		@Override
		public boolean hasNext()
		{
			return (index < size);
		}
		@Override
		public E next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			pre = cur;
			E e = cur.element;
			cur = cur.next;
			index++;
			return e;
		}

		//Previous Methods
		@Override
		public E previous()
		{
			if(!hasPrevious())
			{
				throw new NoSuchElementException();
			}
			cur = cur.prev;
			index--;
			pre = cur;
			return cur.element;
		}
		@Override
		public boolean hasPrevious()
		{
			return (index > 0);
		}

		public void remove()
		{
			if(pre == null)
			{
				throw new IllegalStateException();
			}
			pre.prev.next = pre.next;
			pre.next.prev = pre.prev;
			size--;
			index--;
			pre = null;
		}
		//Add method
		public void add(E e)
		{
			Node<E> tmp = new Node(e);
			Node<E> tmpPre = cur.prev;
			Node<E> tmpCur = cur;
			tmpPre.next = tmp;
			tmp.next = tmpCur;
			tmpCur.prev = tmp;
			tmp.prev = tmpPre;
			size++;
			index++;
			pre = null;
		}
		//Remove Method
		public void remove(E e)
		//notfinished
		{
			if(cur.element == e)
			{
				cur.prev.next = cur.next;
				cur.next.prev = cur.prev;
			}
			size--;
			index--;
		}

		//Set Method
		public void set(E e)
		{
			pre.element = e;
		}

		//Index Methods
		@Override
		public int nextIndex()
		{
			return index;
		}
		@Override
		public int previousIndex()
		{
			// TODO Auto-generated method stub
			return index - 1;
		}
	}








}