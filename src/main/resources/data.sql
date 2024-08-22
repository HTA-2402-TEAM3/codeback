INSERT INTO authority (id, name)
VALUES (UUID(), 'ROLE_USER'),
       (UUID(), 'ROLE_ADMIN');

INSERT INTO code_language_category (id, language_name)
VALUES (UUID(), 'Java'),
       (UUID(), 'Python'),
       (UUID(), 'JavaScript'),
       (UUID(), 'C++'),
       (UUID(), 'Ruby'),
       (UUID(), 'Swift'),
       (UUID(), 'Go'),
       (UUID(), 'Kotlin'),
       (UUID(), 'Rust'),
       (UUID(), 'TypeScript');

INSERT INTO member (email, nickname, authority_id)
VALUES ('jiwonpark@google.com', '박지원', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('seungwookim@google.com', '김승우', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('hayounglee@google.com', '이하영', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('yunachoi@google.com', '최유나', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('sooyoungkim@daum.net', '김수영', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('jisoolim@codeback.kr', '임지수', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('chaeunkang@github.com', '강채은', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('seokjinjung@github.com', '정석진', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('sangminlee@naver.com', '이상민', (SELECT id FROM authority LIMIT 1 OFFSET 0)),
       ('minjicho@naver.com', '조민지', (SELECT id FROM authority LIMIT 1 OFFSET 0));

INSERT INTO code_review (id, email, title, content, create_date, language_id)
VALUES (UUID(), 'jiwonpark@google.com', '자바 경로탐색 질문입니다.', '<h2>BFS 예제 1: 경로 탐색</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample10 {
    public List<Integer> bfsPath(List<List<Integer>> graph, int start, int end) {
        boolean[] visited = new boolean[graph.size()];
        Map<Integer, Integer> parentMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == end) {
                return constructPath(parentMap, start, end);
            }
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parentMap.put(neighbor, node);
                    queue.add(neighbor);
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Integer> constructPath(Map<Integer, Integer> parentMap, int start, int end) {
        List<Integer> path = new LinkedList<>();
        for (Integer at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 0)),
       (UUID(), 'seungwookim@google.com', '반복적인 채우기 전략 질문입니다.', '<h2>BFS 예제 2: 반복적인 채우기 전략</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample9 {
    public void bfsFloodFill(int[][] grid, int startX, int startY, int newColor) {
        int oldColor = grid[startX][startY];
        if (oldColor == newColor) return;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] pixel = queue.poll();
            int x = pixel[0], y = pixel[1];

            if (grid[x][y] == oldColor) {
                grid[x][y] = newColor;
                if (x > 0) queue.add(new int[]{x - 1, y});
                if (y > 0) queue.add(new int[]{x, y - 1});
                if (x < grid.length - 1) queue.add(new int[]{x + 1, y});
                if (y < grid[0].length - 1) queue.add(new int[]{x, y + 1});
            }
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 1)),
       (UUID(), 'hayounglee@google.com', '그래프 뎁스 검사 질문', '<h2>BFS 예제 3: 그래프 깊이 검사</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample8 {
    public void bfsDepthCheck(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                System.out.print(node + " ");
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.add(neighbor);
                    }
                }
            }
            depth++;
        }
        System.out.println("\nMax Depth: " + depth);
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 2)),
       (UUID(), 'yunachoi@google.com', '최단 경로를 위한 BFS 질문', '<h2>BFS 예제 4: 최단 경로를 위한 BFS</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample7 {
    static class Node {
        int val;
        int distance;

        Node(int val, int distance) {
            this.val = val;
            this.distance = distance;
        }
    }

    public void bfsShortestPath(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Node> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.println("Node: " + node.val + ", Distance: " + node.distance);

            for (int neighbor : graph.get(node.val)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new Node(neighbor, node.distance + 1));
                }
            }
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 3)),
       (UUID(), 'sooyoungkim@daum.net', '그래프 순회 헬프요 ㅠㅠ', '<h2>BFS 예제 5: 그래프 순회</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample6 {
    public void bfsTraverse(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }
    }

    private void bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 4)),
       (UUID(), 'jisoolim@codeback.kr', '트리 구조 질문이 있습니다', '<h2>BFS 예제 6: 트리 구조의 BFS 구현</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample5 {
    static class TreeNode {
        int val;
        List<TreeNode> children;

        TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children);
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 5)),
       (UUID(), 'chaeunkang@github.com', '최단 경로를 찾는 방법', '<h2>BFS 예제 7: 최단 경로 찾기</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample4 {
    public void bfsShortestPath(List<List<Integer>> graph, int start) {
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, -1);
        distance[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (distance[neighbor] == -1) {
                    distance[neighbor] = distance[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        System.out.println(Arrays.toString(distance));
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 6)),
       (UUID(), 'seokjinjung@github.com', '깊이 우선 탐색이랑 결합하는 법', '<h2>BFS 예제 3: 깊이 우선 탐색과 결합</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample3 {
    public void bfsWithDFS(int[][] graph, int start) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.length];

        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            System.out.print(node + " ");

            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 7)),
       (UUID(), 'sangminlee@naver.com', '1:1 파이썬 초보만', '<h2>BFS 예제 9: 배열 기반의 BFS 구현</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample2 {
    public void bfs(int[][] graph, int start) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 8)),
       (UUID(), 'minjicho@naver.com', 'BFS 인접 리스트 질문입니다.', '<h2>BFS 예제 10: 인접 리스트 기반의 BFS 구현</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample1 {
    public void bfs(int start, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 9));



INSERT INTO code_review_comment (id, comment, create_date, code_review_id, base_comment_id, email)
VALUES (UUID(), '잘 작성해 주셨습니다!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 0), NULL, 'yunachoi@google.com'),
       (UUID(), '잘하셨는데 <div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        boolean[] visited = new boolean[graph.size()];
        Map<Integer, Integer> parentMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

</code></pre></div>
이부분에서 다형성을 고려해야 할 거 같습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 1), null, 'jiwonpark@google.com'),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

</code></pre></div>이 부분은 왜 이렇게 쓰신걸까요?', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 2), NULL,
        'chaeunkang@github.com'),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }

</code></pre></div>반복문이 너무 깊습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 3), NULL,
        'seokjinjung@github.com'),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }

</code></pre></div>이런 방법으로 하면 좋을 거 같습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 4), NULL,
        'sangminlee@naver.com'),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }

</code></pre></div>이건 어떨까요?', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 5), NULL, 'minjicho@naver.com'),
       (UUID(), 'private void bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }요거는 어떨까요', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 6), NULL, 'hayounglee@google.com'),
       (UUID(), 'public void bfsTraverse(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }
    }너무 힘들어요..', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 7), NULL, 'seungwookim@google.com'),
       (UUID(), ' static class TreeNode {
        int val;
        List<TreeNode> children;

        TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }덕분에 트리 노드를 구경했습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 8), NULL, 'jisoolim@codeback.kr'),
       (UUID(), 'public void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children);
        }
    }저도요', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 9), NULL, 'seokjinjung@github.com');


INSERT INTO code_review_preference (id, sender_email, entity_id, is_Like, create_date)
VALUES (UUID(), 'chaeunkang@github.com', (SELECT id FROM code_review LIMIT 1 OFFSET 0), 1, NOW()),
       (UUID(), 'sooyoungkim@daum.net', (SELECT id FROM code_review LIMIT 1 OFFSET 1), 1, NOW()),
       (UUID(), 'sooyoungkim@daum.net', (SELECT id FROM code_review LIMIT 1 OFFSET 2), 1, NOW()),
       (UUID(), 'minjicho@naver.com', (SELECT id FROM code_review LIMIT 1 OFFSET 3), 1, NOW()),
       (UUID(), 'yunachoi@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 4), 1, NOW()),
       (UUID(), 'yunachoi@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 5), 1, NOW()),
       (UUID(), 'hayounglee@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 6), 1, NOW()),
       (UUID(), 'hayounglee@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 7), 1, NOW()),
       (UUID(), 'seungwookim@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 8), 1, NOW()),
       (UUID(), 'jiwonpark@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 9), 1, NOW());


INSERT INTO notification (id, receiver_email, entity_id, is_read, create_date)
VALUES (UUID(), 'jiwonpark@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 1), 0, NOW()),
       (UUID(), 'seungwookim@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 2), 0, NOW()),
       (UUID(), 'hayounglee@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 3), 0, NOW()),
       (UUID(), 'yunachoi@google.com', (SELECT id FROM code_review LIMIT 1 OFFSET 4), 0, NOW()),
       (UUID(), 'sooyoungkim@daum.net', (SELECT id FROM code_review LIMIT 1 OFFSET 5), 0, NOW()),
       (UUID(), 'jisoolim@codeback.kr', (SELECT id FROM code_review LIMIT 1 OFFSET 6), 0, NOW()),
       (UUID(), 'chaeunkang@github.com', (SELECT id FROM code_review LIMIT 1 OFFSET 7), 0, NOW()),
       (UUID(), 'seokjinjung@github.com', (SELECT id FROM code_review LIMIT 1 OFFSET 8), 0, NOW()),
       (UUID(), 'sangminlee@naver.com', (SELECT id FROM code_review LIMIT 1 OFFSET 9), 0, NOW()),
       (UUID(), 'minjicho@naver.com', (SELECT id FROM code_review LIMIT 1 OFFSET 0), 0, NOW());