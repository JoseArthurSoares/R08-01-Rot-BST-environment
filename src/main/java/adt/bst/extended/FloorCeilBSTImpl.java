package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		for (int i : array){
			tree.insert(i);
		}
		if (tree.minimum().getData() > numero) return null;
		else if (tree.maximum().getData() <= numero ) return tree.maximum().getData();
		return calculateFloor(tree, tree.minimum(), numero);
	}

	private Integer calculateFloor(BSTImpl<Integer> tree, BSTNode<Integer> node, double numero) {
		if (node.getData() == numero) return node.getData();
		else if (node.getData() > numero) return tree.predecessor(node.getData()).getData();
		else {
			return calculateFloor(tree, tree.sucessor(node.getData()), numero);
		}
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		BSTImpl<Integer> tree = new BSTImpl<>();
		for (int i : array){
			tree.insert(i);
		}
		if (tree.maximum().getData() < numero) return null;
		else if (tree.minimum().getData() >= numero ) return tree.minimum().getData();
		return calculateCeil(tree, tree.maximum(), numero);
	}

	private Integer calculateCeil(BSTImpl<Integer> tree, BSTNode<Integer> node, double numero) {
		if (node.getData() == numero) return node.getData();
		else if (node.getData() < numero) return tree.sucessor(node.getData()).getData();
		else {
			return calculateFloor(tree, tree.predecessor(node.getData()), numero);
		}
	}
}
