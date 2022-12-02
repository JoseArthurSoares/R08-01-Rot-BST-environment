package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null) {
			return recusive_search(this.root, element);
		} else {
			return new BSTNode<>();
		}
	}

	private BSTNode<T> recusive_search(BSTNode<T> node, T element) {
		if (node.isEmpty()) return new BSTNode<>();
		else if (element == node.getData()) return node;
		else if(element.compareTo(node.getData()) < 0) return recusive_search((BSTNode<T>) node.getLeft(), element);
		else return recusive_search((BSTNode<T>) node.getRight(), element);
	}

	@Override
	public void insert(T element) {
		if (element != null){
			if (isEmpty()){
				this.root = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.build();
			} else {
				recursive_insert(this.root, element);
			}
		}
	}

	private void recursive_insert(BSTNode<T> node, T element) {
		if (element.compareTo(node.getData()) < 0){
			if (node.getLeft().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.parent(node)
						.build();
				node.setLeft(newNode);
				return;
			}
			recursive_insert((BSTNode<T>) node.getLeft(), element);
		}else {
			if (node.getRight().isEmpty()){
				BSTNode<T> newNode = (BSTNode<T>) new BSTNode.Builder<T>()
						.data(element)
						.right((BSTNode<T>) new BSTNode<T>())
						.left((BSTNode<T>) new BSTNode<T>())
						.parent(node)
						.build();
				node.setRight(newNode);
				return;
			}
			recursive_insert((BSTNode<T>) node.getRight(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (!isEmpty()){
			BSTNode<T> maximo = this.root;
			while (!maximo.getRight().isEmpty()){
				maximo = (BSTNode<T>) maximo.getRight();
			}
			return maximo;
		}else return null;
	}

	@Override
	public BSTNode<T> minimum() {
		if (!isEmpty()){
			BSTNode<T> minimo = this.root;
			while (!minimo.getLeft().isEmpty()){
				minimo = (BSTNode<T>) minimo.getLeft();
			}
			return minimo;
		}else return null;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		if (element == null) return null;
		else {
			BSTNode<T> sucessor = (BSTNode<T>) search(element).getRight();
			if (!sucessor.isEmpty()) return sucessor;
			else return null;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element == null) return null;
		else {
			BSTNode<T> antecessor = (BSTNode<T>) search(element).getParent();
			if (!antecessor.isEmpty()) return antecessor;
			else return null;
		}
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] order() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
