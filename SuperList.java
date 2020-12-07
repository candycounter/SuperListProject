import java.util.EmptyStackException;
import java.io.*;
public class SuperList<E>
{
	ListNode<E> root;
	ListNode<E> end;
	int size;

	public SuperList()
	{
		root = null;
		end = null;
		size = 0;
	}

	public void add(E value)                                        //ArrayLists and Queues
	{
		ListNode<E> newNode = new ListNode<E>(value);
		if(root == null)
		{
			root = newNode;
			end = root;
		}else{
			end.setNext(newNode);
			newNode.setPrevious(end);
			end = newNode;
		}
		size++;
	}

	public void add(int index, E value){
		ListNode<E> newNode = new ListNode<E>(value);
		if(index<0 || index > size){
			throw new ArrayIndexOutOfBoundsException ();
		}
		else if (index == size){
			add(value);
		}
		else if(index==0){
			newNode.setNext(root);
			root.setPrevious(newNode);
			root = newNode;
			size++;
		} else {
			try{
				ListNode<E> temp = root;

				for (int i = 0; i < index; i++){
					temp = temp.getNext();
				}
				ListNode<E> current = new ListNode<E>(value);
				ListNode<E> previous = temp.getPrevious();
				current.setPrevious(previous);
				previous.setNext(current);
				current.setNext(temp);
				temp.setPrevious(current);
				size++;
			}catch (ArrayIndexOutOfBoundsException e) {}
		}
	}

	public void push(E value)                                       //Stacks
	{
		ListNode<E> newNode = new ListNode<E>(value);
		if(root == null)
		{
			root = newNode;
			end = root;
		}else{
			end.setNext(newNode);
			newNode.setPrevious(end);
			end = newNode;
		}
		size++;
	}

	public E pop(){
		E value = null;
		if(root==null)
		{
			throw new EmptyStackException();
		}
		else if (size == 1){
			value = end.getValue();
			root = null;
			end = null;
			size--;

		} else {
			try{
				value = end.getValue();
				end = end.getPrevious();
				end.setNext(null);
				size--;
			} catch (NullPointerException e){
			}
		}
		return value;
	}

	public E poll(){
		E value = null;
		if(root==null)
		{
			throw new EmptyStackException();
		}
		else if (size == 1 && root!=null){
			value = root.getValue();
			clear();
		} else {
			try{
				value = root.getValue();
				root = root.getNext();
				root.setPrevious(null);
				size--;
			} catch (NullPointerException e){}
		}
		return value;
	}
	public E peekStack(){
		E value;
		if(root == null){
			value = null;
			return value;
		} else {
			try{
				value = end.getValue();
				return value;
			} catch (NullPointerException e){
				return root.getValue();
			}
		}
	}
	public E peekQueue(){
		E value;
		if(root == null){
			value = null;
			return value;
		} else {
			value = end.getValue();
			return value;
		}
	}

	public E remove (int index){
		E value = null;
		//ListNode<E> temp = root;
		int x = 0;
		if(index > size || index < 0){
			throw new ArrayIndexOutOfBoundsException();
		} else if (index == size-1 && root!=null){
			value = pop();

		} else if (index == 0 && root!=null){
			value = poll();
		} else {
			try{
				for(ListNode<E>temp = root; x<=index; temp = temp.getNext()){
					if(x == index){
						temp.getPrevious().setNext(temp.getNext());
						temp.getNext().setPrevious(temp.getPrevious());
						size--;
						value = temp.getValue();
					}
					x++;
				}
			} catch (ArrayIndexOutOfBoundsException e){}
		}
		return value;
	}


	public int size(){
		return size;
	}

	public void clear(){
		size = 0;
		root = null;
		end = null;
	}
	public boolean isEmpty()
	{
		return size==0;
	}

	public boolean contains(E value)
	{
		ListNode<E> temp = root;
		boolean found = false;
		for(int i = 0; i < size; i++){
			if(value == temp.getValue()){
				found = true;
			}
			temp = temp.getNext();
		}
		return found;
	}

	public String toString()
	{
		String st = "[";
		ListNode<E> temp = root;
		for(int x = 0; x < size; x++)
		{
			st+=temp.getValue();
			if(x<size-1)
			{
				st+=", ";
			}
			temp = temp.getNext();
		}
		st+="]";
		return st;

	}

	public E get(int i){
		ListNode<E> temp = root;
		E value = null;
		for(int x = 0; x < size; x++){
			if(x == i){
				value = temp.getValue();
			}
			temp = temp.getNext();
		}
		return value;
	}

	public class ListNode<E>
	{
		E value;
		ListNode<E> next;
		ListNode<E> previous;

		public ListNode(E value)
		{
			this.value = value;
			next = null;
			previous = null;
		}
		public E getValue(){ return value; }
		public ListNode<E> getNext() { return next; }
		public ListNode<E> getPrevious() { return previous; }
		public void setNext(ListNode<E> newNode)
		{
			next = newNode;
		}
		public void setPrevious(ListNode<E> newNode)
		{
			previous = newNode;
		}

		public boolean hasPrevious(){
			return previous!=null;
		}
		public boolean hasNext(){
			return next!=null;
		}
	}
}