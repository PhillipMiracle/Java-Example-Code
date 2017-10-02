//Phillip Miracle
//CSC364
//BST with Fast Stack based iterator

import java.util.Iterator;
import java.util.NoSuchElementException;

public class BSTWithFastIter<E> extends BST
{
	@Override
	public java.util.Iterator<E> iterator()
	{
		return new iterator();
	}

	private class iterator implements java.util.Iterator<E>
	 {
		private java.util.Stack<TreeNode<E>> stack;
		private TreeNode<E> current;
		private E lastReturned;

		public iterator()
		{
	      bstIter();
	    }

		private void bstIter()
		{
	      bstIter(root);
	    }
		private void  bstIter(TreeNode<E> root)
	    {
			current = root;
			stack = new java.util.Stack<>();
		}
		public boolean hasNext()
		{
			return ((current != null) || !(stack.isEmpty()));
		}

		public E next()
		{
			if (!hasNext())
				throw new NoSuchElementException();
			while (current != null)
			{
				stack.push(current);
				current = current.left;
			}
			BST.TreeNode<E> node = stack.pop();
			lastReturned = node.element;
			current = node.right;
			return lastReturned;
		}

		public void remove()
		{
			if (lastReturned == null)
				throw new IllegalStateException();
			delete((int) lastReturned);
			lastReturned = null;
		}
	 }
}
