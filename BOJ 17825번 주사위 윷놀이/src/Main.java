import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    String[] strs;
    int[] moves;
    int maxSum = 0;
    Mal[] mals;
    HashMap<Integer, Vertex> vtxs;
    Vertex vtxBegin = new Vertex(0, COLOR_RED);
    Vertex vtxEnd = new Vertex(0, COLOR_RED);

    static int COLOR_BLUE = 0;
    static int COLOR_RED = 1;

    public void solve() throws IOException {
        mals = new Mal[4];
        for (int i = 0; i < mals.length; ++i) {
            mals[i] = new Mal();
            mals[i].setV(vtxBegin);
        }

        Vertex vtx2 = new Vertex(2, COLOR_RED);
        new DirectedEdge(vtxBegin, vtx2, COLOR_RED);

        vtxs = new HashMap<>();
        vtxs.put(2, vtx2);

        Vertex from = vtx2;

        for (int i = 2; i <= 20; ++i) {
            Vertex to = new Vertex(i * 2, COLOR_RED);
            new DirectedEdge(from, to, COLOR_RED);
            vtxs.put(i * 2, to);
            from = to;
        }

        new DirectedEdge(from, vtxEnd, COLOR_RED);

        // 10, 20, 30에 컬러 파란색으로 변경
        vtxs.get(10).mColor = COLOR_BLUE;
        vtxs.get(20).mColor = COLOR_BLUE;
        vtxs.get(30).mColor = COLOR_BLUE;

        // 가운대 십자가
        // 13, 16, 19, 25, 26, 27, 28
        // 35, 30, 24, 22
        Vertex vtx13 = new Vertex(13, COLOR_RED);
        Vertex vtx16 = new Vertex(16, COLOR_RED);
        Vertex vtx19 = new Vertex(19, COLOR_RED);
        Vertex vtx25 = new Vertex(25, COLOR_RED);
        Vertex vtx26 = new Vertex(26, COLOR_RED);
        Vertex vtx27 = new Vertex(27, COLOR_RED);
        Vertex vtx28 = new Vertex(28, COLOR_RED);
        Vertex vtx35 = new Vertex(35, COLOR_RED);
        Vertex vtx30 = new Vertex(30, COLOR_RED);
        Vertex vtx24 = new Vertex(24, COLOR_RED);
        Vertex vtx22 = new Vertex(22, COLOR_RED);

        // 가운대 십자가, 왼쪽 -> 가운대(25)
        new DirectedEdge(vtxs.get(10), vtx13, COLOR_BLUE);
        new DirectedEdge(vtx13, vtx16, COLOR_RED);
        new DirectedEdge(vtx16, vtx19, COLOR_RED);
        new DirectedEdge(vtx19, vtx25, COLOR_RED);

        // 가운대 십자가, 아래(20) -> 가운대(25)
        new DirectedEdge(vtxs.get(20), vtx22, COLOR_BLUE);
        new DirectedEdge(vtx22, vtx24, COLOR_RED);
        new DirectedEdge(vtx24, vtx25, COLOR_RED);

        // 가운대 십자가, 으론쪽(30) -> 가운대(25)
        new DirectedEdge(vtxs.get(30), vtx28, COLOR_BLUE);
        new DirectedEdge(vtx28, vtx27, COLOR_RED);
        new DirectedEdge(vtx27, vtx26, COLOR_RED);
        new DirectedEdge(vtx26, vtx25, COLOR_RED);

        // 가운대 십자가, 가운대(25) -> 위(40)
        new DirectedEdge(vtx25, vtx30, COLOR_RED);
        new DirectedEdge(vtx30, vtx35, COLOR_RED);
        new DirectedEdge(vtx35, vtxs.get(40), COLOR_RED);

        // 40 -> 도착
        new DirectedEdge(vtxs.get(40), vtxEnd, COLOR_RED);

        strs = br.readLine().split(" ");
        moves = new int[strs.length];

        for (int i = 0; i < strs.length; ++i) {
            moves[i] = Integer.parseInt(strs[i]);
        }

        BackTracking(0, 0);

        bw.write(String.valueOf(maxSum));
        bw.newLine();
        bw.close();
    }

    public void BackTracking(int idxMove, int mysum) throws IOException {
        if (!(idxMove < strs.length)) {
            maxSum = Math.max(maxSum, mysum);
//            bw.write(String.valueOf(maxSum) + ' ' + String.valueOf(mysum));
//            bw.newLine();
//            bw.write("------------------------------------------------");
//            bw.newLine();
//            bw.flush();
            return;
        }

        for (int idxMal = 0; idxMal < mals.length; ++idxMal) {
            Vertex crntVertex = mals[idxMal].mVertex;
            if (vtxEnd == crntVertex)
                continue;

            moveMal(mals[idxMal], 0, moves[idxMove]);

            // 말이 이동을 마치는 칸에 다른 말이 있으면 그 말은 고를 수 없다. 단, 이동을 마치는 칸이 도착 칸이면 고를 수 있다.
            if (restoreMalVertex(idxMal)) {
                mals[idxMal].setV(crntVertex);
                continue;
            }

//            bw.write(String.valueOf(idxMal) + ' ' + String.valueOf(mals[idxMal].mVertex.mV));
//            bw.newLine();
            BackTracking(idxMove + 1, mysum + mals[idxMal].mVertex.mV);

            mals[idxMal].setV(crntVertex);
        }
    }

    public boolean restoreMalVertex(int idxMal) {
        if (mals[idxMal].mVertex == vtxEnd)
            return false;

        for (int i = 0; i < mals.length; ++i) {
            if (i != idxMal) {
                if (mals[i].mVertex == mals[idxMal].mVertex)
                    return true;
            }
        }

        return false;
    }

    public void moveMal(Mal mal, int idxLen, int len) {
        if (len <= 0)
            return;

        Vertex vtx = mal.mVertex;
        int colorEdge = COLOR_RED;
        DirectedEdge edge = null;

        if ((idxLen == 0) && (vtx.mColor == COLOR_BLUE)) {
            colorEdge = COLOR_BLUE;
        }

        for (DirectedEdge e : vtx.mEdges) {
            if (e.mColor == colorEdge) {
                edge = e;
                break;
            }
        }

        mal.setV(edge.mTo);

        if (edge.mTo == vtxEnd)
            return;

        moveMal(mal, idxLen + 1, len - 1);
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.solve();
    }

    public class Mal {
        public Vertex mVertex;

        void setV(Vertex v) {
            mVertex = v;
        }
    }

    public class Vertex {
        public int mV;
        public int mColor;
        public ArrayList<DirectedEdge> mEdges;

        public Vertex(int v, int color) {
            mV = v;
            mColor = color;
            mEdges = new ArrayList<DirectedEdge>();
        }
    }

    public class DirectedEdge {
        public Vertex mFm;
        public Vertex mTo;
        public int mColor;

        public DirectedEdge(Vertex from, Vertex to, int color) {
            mFm = from;
            mTo = to;
            mColor = color;
            mFm.mEdges.add(this);
        }
    }
}