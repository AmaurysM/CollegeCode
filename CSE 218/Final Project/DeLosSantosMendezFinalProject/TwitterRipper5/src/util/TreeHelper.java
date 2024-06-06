package util;

import java.util.LinkedList;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import model.Comment;

public class TreeHelper {

	public static TreeItem<Comment> makeLeaf(TreeItem<Comment> item) {
		LinkedList<Comment> hold = item.getValue().getAllCommentsInComment();

		for (int i = 0; i < hold.size(); i++) {

			TreeItem<Comment> newItem = new TreeItem<>(hold.get(i));

			if (hold.get(i).getAllCommentsInComment().size() > 0) {
				makeLeaf(newItem);
			}

			if (!hold.get(i).isDeleted()) {
				item.getChildren().add(newItem);
			} else {
				hold.remove(i);
				i--;
			}

		}
		item.setExpanded(true);
		return item;
	}

}
