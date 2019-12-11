package com.tistory.skysign.Heap_sort;

// heap sort의 코드가 필요하신분은 아래 링크 참고하세요.
// https://www.geeksforgeeks.org/java-program-for-heap-sort/
// 이 소스 파일도, 위의 링크 참고해서 만들었습니다.
// heap sort 알고리즘을 보다이해 하실 분은 https://www.youtube.com/watch?v=2DmK_H7IdTo
//
public class HeapSort {
    public void heapSort(int d[]) {
        // build max heap
        // 즉, binary tree로 child가 항상 parent 보다 작은 상태를 만들겠습니다.

        // N : 트리에 있는 노드의 숫자
        int N = d.length;

        // N>>1 해서 N을 2로 나누고 -1 한 인덱스 부터, root node은 0까지 heapify 합니다.
        // N/2-1 ~ 0 까지 루프를 한다는 것은, 바이너리 트리에서 sibling 을 제외 하고,
        // 나머지 노드 들에 대해서, 모두 방문해서 heapify 한다는 것입니다.
        // heapify는 child가 parent보다 작은 상태가 되도록, child와 parent를 swap하는 일을 말합니다.
        for(int i= (N>>1) -1; i>=0; --i) {
            heapify(d, N, i);
        }
        // N/2-1 ~ 0 까지 heapify가 끝나면, index 0 에 가장 큰 값이 위치 하게 됩니다.

        for(int i=N-1; i>=0; --i) {
            int t = d[0];
            d[0] = d[i];
            d[i] = t;
            // 가장 큰 값을 찾았으니, 이제 tree의 크기를 1 줄여서
            // 그 다음 큰 값을 찾기위해서, heapify 할 차례입니다.
            heapify(d, i, 0);
        }
    }

    private void heapify(int[] d, int N, int idxParent) {
        // 가장 큰 값을 가지고 있는 인덱스가 i 라고 가정하고,
        // 이후에 idxBiggest가 i 값과 다르면, 아래의 2가지 스텝을 실행 합니다.
        int idxBiggest = idxParent;
        int idxLeftChild = (idxParent<<1) +1;
        int idxRightChild = (idxParent<<1) +2;

        // binary tree이지만, full binary tree가 아닐 수 있기 때문에,
        // idxLeftChild와 idxRightChild모두 N 보다 작을 때만, 비교 해야 합니다.
        if((idxLeftChild < N) && d[idxLeftChild] > d[idxBiggest])
            idxBiggest = idxLeftChild;

        if((idxRightChild < N) && d[idxRightChild] > d[idxBiggest])
            idxBiggest = idxRightChild;

        if(idxBiggest != idxParent) {
            // idxBiggest와 idxParent 값을 swap 합니다.
            int t = d[idxParent];
            d[idxParent] = d[idxBiggest];
            d[idxBiggest] = t;

            // idxBiggest에 idxParent값이 들어 왔으므로,
            // idxBiggest에 대해서, heapify합니다.
            heapify(d, N, idxBiggest);
        }
    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
	    int d[] = new int[]{1, 3, 5, 7, 4};

        hs.heapSort(d);

        for(int n: d)
            System.out.printf("%d ", n);
    }
}
