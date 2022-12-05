package adt.bst;

import adt.bt.BTNode;

import java.util.ArrayList;
import java.util.LinkedList;

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
		return recursive_height(this.root);
	}

	private int recursive_height(BSTNode<T> node) {
		if (node.isEmpty()) return -1;
		else return 1 + Math.max(recursive_height((BSTNode<T>) node.getLeft()), recursive_height((BSTNode<T>) node.getRight()));
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
			BSTNode<T> node = search(element);
			if (!node.getRight().isEmpty()){
				//Cálculo do mínimo
				BSTNode<T> minimo = (BSTNode<T>) node.getRight();
				while (!minimo.getLeft().isEmpty()){
					minimo = (BSTNode<T>) minimo.getLeft();
				}
				return minimo;
			}
			else {
				BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
				while (!nodeAux.isEmpty() && nodeAux.getData().compareTo(node.getData()) < 0){
					nodeAux = (BSTNode<T>) nodeAux.getParent();
				}
				return nodeAux;
			}
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		if (element == null || element.compareTo(minimum().getData()) == 0) return null;
		else {
			BSTNode<T> node = search(element);
			if (!node.getLeft().isEmpty()){
				//Cálculo do máximo
				BSTNode<T> maximo = (BSTNode<T>) node.getLeft();
				while (!maximo.getRight().isEmpty()){
					maximo = (BSTNode<T>) maximo.getRight();
				}
				return maximo;
			}
			else {
				BSTNode<T> nodeAux = (BSTNode<T>) node.getParent();
				while (!nodeAux.isEmpty() && nodeAux.getData().compareTo(node.getData()) > 0){
					nodeAux = (BSTNode<T>) nodeAux.getParent();
				}
				return nodeAux;
			}
		}
	}


	private boolean hasOnlyLeftChild(BSTNode<T> node){
		return node.getRight().isEmpty() && !node.getLeft().isEmpty();
	}
	private boolean hasOnlyRightChild(BSTNode<T> node){
		return !node.getRight().isEmpty() && node.getLeft().isEmpty();
	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = search(element);
			if (!node.isEmpty()) {
				//Caso o nó seja uma folha
				if (node.isLeaf()){
					if (node.getData().compareTo(this.root.getData()) == 0){
						this.root = new BSTNode<>();
					} else {
						if (node.getData().compareTo(node.getParent().getData()) < 0){
							node.getParent().setLeft(new BSTNode<>());
						} else {
							node.getParent().setRight(new BSTNode<>());
						}
					}
				//Caso o né tenha apenas um filho a esquerda
				} else if (this.hasOnlyLeftChild(node)){
					if (node.getData().compareTo(this.root.getData()) == 0){
						this.root = (BSTNode<T>) node.getLeft();
						this.root.setParent(new BSTNode<>());
					} else {
						node.getLeft().setParent(node.getParent());
						if (node.getData().compareTo(node.getParent().getData()) < 0){
							node.getParent().setLeft(node.getLeft());
						} else {
							node.getParent().setRight(node.getLeft());
						}
					}
				//Caso o nó tenha apenas um filho a direita
				} else if (this.hasOnlyRightChild(node)){
					if (node.getData().compareTo(this.root.getData()) == 0){
						this.root = (BSTNode<T>) node.getRight();
						this.root.setParent(new BSTNode<>());
					} else {
						node.getRight().setParent(node.getParent());
						if (node.getData().compareTo(node.getParent().getData()) < 0){
							node.getParent().setLeft(node.getRight());
						} else {
							node.getParent().setRight(node.getRight());
						}
					}
				//Caso o nó tenha dois filhos
				} else {
					BSTNode<T> sucessor = this.sucessor(element);
					node.setData(sucessor.getData());
					remove(sucessor.getData());
				}
			}
		}
	}


	@Override
	public T[] preOrder() {
		if (isEmpty()) return (T[]) new Comparable[0];
		LinkedList<T> array = new LinkedList<T>();
		LinkedList<T> preOrder = preOrder_recursive(this.root, array);
		T[] preOrderT = (T[]) new Comparable[preOrder.size()];
		int i = 0;
		for (T element:preOrder){
			preOrderT[i++] = element;
		}
		return preOrderT;
	}

	private LinkedList<T> preOrder_recursive(BSTNode<T> node, LinkedList<T> array) {
		if (!node.isEmpty()) {
			array.add(node.getData());
			preOrder_recursive((BSTNode<T>) node.getLeft(), array);
			preOrder_recursive((BSTNode<T>) node.getRight(), array);
		}
		return array;
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
