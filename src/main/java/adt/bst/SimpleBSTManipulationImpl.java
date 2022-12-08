package adt.bst;

import adt.bt.BTNode;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		if (tree1.size() != tree2.size()) return false;
		else if (tree1.isEmpty() && tree2.isEmpty()) return true;
		else return equalsNode(tree1.getRoot(), tree2.getRoot());
	}

	private boolean equalsNode(BTNode<T> node1, BTNode<T> node2) {
		boolean answer = node1.equals(node2);
		if (!node1.isEmpty() && !node2.isEmpty() && answer){
			return this.equalsNode(node1.getLeft(), node2.getLeft()) && this.equalsNode(node1.getRight(), node2.getRight());
		}
		return answer;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		if (tree1.size() != tree2.size()) return false;
		else if (tree1.isEmpty() && tree2.isEmpty()) return true;
		else return checkSimilarity(tree1.getRoot(), tree2.getRoot());
	}

	private boolean checkSimilarity(BTNode<T> node1, BTNode<T> node2) {
		//Caso seja um nó folha
		if(node1.isLeaf() && node2.isLeaf()) return true;
		//Caso tenha apenas um filho a esquerda
		else if (hasOnlyLeftChild((BSTNode<T>) node1) && hasOnlyLeftChild((BSTNode<T>) node2)) {
			return checkSimilarity(node1.getLeft(), node2.getLeft());
		//Caso tenha apenas um filho a direita
		} else if (hasOnlyRightChild((BSTNode<T>) node1) && hasOnlyRightChild((BSTNode<T>) node2)) {
			return checkSimilarity(node1.getRight(), node2.getRight());
		//Caso tenha dois filhos
		} else if (hasTwoChildren((BSTNode<T>) node1) && hasTwoChildren((BSTNode<T>) node2)) {
			return checkSimilarity(node1.getLeft(), node2.getLeft()) && checkSimilarity(node1.getRight(), node2.getRight());
		//Caso seja diferente
		} else return false;
	}

	public boolean hasOnlyLeftChild(BSTNode<T> node){
		return node.getRight().isEmpty() && !node.getLeft().isEmpty();
	}
	public boolean hasOnlyRightChild(BSTNode<T> node){
		return !node.getRight().isEmpty() && node.getLeft().isEmpty();
	}
	public boolean hasTwoChildren(BSTNode<T> node){return !node.getRight().isEmpty() && !node.getLeft().isEmpty();}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if (tree.isEmpty() || k > tree.size()) return null;
		return orderStastics_k(tree, k, tree.minimum().getData());
	}

	private T orderStastics_k(BST<T> tree, int k, T minimum) {
		if (k <= 1) return minimum;
		else return orderStastics_k(tree, k-1, tree.sucessor(minimum).getData());
	}

}
