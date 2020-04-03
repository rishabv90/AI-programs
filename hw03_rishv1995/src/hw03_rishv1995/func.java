package hw03_rishv1995;
import java.util.Comparator;


public class func implements Comparator<Node2> {

	@Override
	public int compare(Node2 n1, Node2 n2) {
		// TODO Auto-generated method stub
		if (n1.getfVal() > n2.getfVal()) {
			return 1;
		}
		else if(n1.getfVal() < n2.getfVal()) {
			return -1;
		}
		return 0;
	}

}
