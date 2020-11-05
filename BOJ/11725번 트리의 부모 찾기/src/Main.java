import java.util.ArrayList;

public class Main {
    public void solve() {

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }

    public class MNode<TV> extends Node {
        ArrayList<MNode<TV>> mChidren ;

        MNode(TV v) {
            super(v);
            mChidren = new ArrayList<>();
        }

        public void addChild(MNode<TV> nodeChild) {
            nodeChild.setParent(this);
            mChidren.add(nodeChild);
        }
    }

    public class Node<TV> {
        public TV mV;
        public Node<TV> mParent;

        Node(TV v) {
            setParent(null);
            set(v);
        }

        public void set(TV v) {
            mV = v;
        }

        public void setParent(Node<TV> parent) {
            mParent = parent;
        }

        public Node<TV> getParent() {
            return mParent;
        }
    }
}
