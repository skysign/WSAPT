public class Solution {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode a = l1, b = l2;
        ListNode r = null;
        ListNode c = null;
        int carry = 0;

        while(a != null || b != null) {
            int va = 0, vb = 0;
            if(a != null) {
                va = a.val;
                a = a.next;
            }
            if(b != null) {
                vb = b.val;
                b = b.next;
            }

            if(null == r) {
                r = new ListNode((va+vb)%10);
                c = r;
                carry = (va+vb)/10;
            }
            else {
                c.next = new ListNode((va+vb)%10);
                c = c.next;
                if(carry > 0) {
                    c.val++;
                    carry = 0;
                }

                carry = (va+vb)/10;

                if(c.val == 10) {
                    carry = 1;
                    c.val = 0;
                }
            }
        }

        if(carry > 0) {
            c.next = new ListNode(carry);
        }

        return r;
    }

    public static void main(String[] args) {
	// write your code here
    }
}
