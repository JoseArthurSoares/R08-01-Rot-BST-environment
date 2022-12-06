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
		if (!node1.isEmpty() && !node2.isEmpty() && answer == true){
			return this.equalsNode(node1.getLeft(), node2.getLeft()) && this.equalsNode(node1.getRight(), node2.getRight());
		}
		return false;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		// TODO Implement this method
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		if (tree.isEmpty() || k > tree.size()) return null;
		return orderStastics_k(tree, k, tree.minimum().getData());
	}

	private T orderStastics_k(BST<T> tree, int k, T minimum) {
		if (k < 1) return minimum;
		else return orderStastics_k(tree, k-1, tree.sucessor(minimum).getData());
	}

}
