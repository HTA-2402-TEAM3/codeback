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

INSERT INTO member (id, email, nickname, authority_id, is_delete)
VALUES
    (UUID(), 'jiwonpark@google.com', '박지원', (SELECT id FROM authority WHERE name = 'ROLE_ADMIN'), false),
    (UUID(), 'seungwookim@google.com', '김승우', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'hayounglee@google.com', '이하영', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'yunachoi@google.com', '최유나', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'sooyoungkim@daum.net', '김수영', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'jisoolim@codeback.kr', '임지수', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'chaeunkang@github.com', '강채은', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'seokjinjung@github.com', '정석진', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'sangminlee@naver.com', '이상민', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'minjicho@naver.com', '조민지', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'joosung@google.com', '박주성', (SELECT id FROM authority WHERE name = 'ROLE_ADMIN'), false),
    (UUID(), 'park.minseo@gmail.com', '박민서', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'lee.jihoon@naver.com', '이지훈', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'kim.yuna@daum.net', '김유나', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'choi.seunghyun@yahoo.com', '최승현', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'jung.daehyun@kakao.com', '정대현', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'kang.sora@gmail.com', '강소라', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'hwang.minhyun@naver.com', '황민현', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'song.jieun@daum.net', '송지은', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'han.jimin@yahoo.com', '한지민', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'oh.sehun@kakao.com', '오세훈', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'yoon.bora@gmail.com', '윤보라', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'shin.donghyuk@naver.com', '신동혁', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'go.ara@daum.net', '고아라', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'jang.wooyoung@yahoo.com', '장우영', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'bae.joohyun@kakao.com', '배주현', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'nam.joohyuk@gmail.com', '남주혁', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'ahn.hyeji@naver.com', '안혜지', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'kwon.yuri@daum.net', '권유리', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'seo.inguk@yahoo.com', '서인국', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false),
    (UUID(), 'moon.gayoung@kakao.com', '문가영', (SELECT id FROM authority WHERE name = 'ROLE_USER'), false);

INSERT INTO code_review (id, member_id, title, content, create_date, language_id)
VALUES (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 0), '자바 경로탐색 질문입니다.', '<h2>BFS 예제 1: 경로 탐색</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 1), '반복적인 채우기 전략 질문입니다.', '<h2>BFS 예제 2: 반복적인 채우기 전략</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 2), '그래프 뎁스 검사 질문', '<h2>BFS 예제 3: 그래프 깊이 검사</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 3), '최단 경로를 위한 BFS 질문', '<h2>BFS 예제 4: 최단 경로를 위한 BFS</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 5), '그래프 순회 헬프요 ㅠㅠ', '<h2>BFS 예제 5: 그래프 순회</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 6), '트리 구조 질문이 있습니다', '<h2>BFS 예제 6: 트리 구조의 BFS 구현</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 7), '최단 경로를 찾는 방법', '<h2>BFS 예제 7: 최단 경로 찾기</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 7), '깊이 우선 탐색이랑 결합하는 법', '<h2>BFS 예제 3: 깊이 우선 탐색과 결합</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 8), '1:1 파이썬 초보만', '<h2>BFS 예제 9: 배열 기반의 BFS 구현</h2>
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
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 9), 'BFS 인접 리스트 질문입니다.', '<h2>BFS 예제 10: 인접 리스트 기반의 BFS 구현</h2>
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

INSERT INTO code_review (id, member_id, title, content, create_date, language_id)
VALUES (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 10), 'BFS를 활용한 그래프 탐색', '<h2>BFS 예제 11: 그래프 탐색</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample11 {
    public void bfsGraphTraversal(List<List<Integer>> graph, int start) {
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
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 0)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 11), '최단 경로 탐색을 위한 BFS', '<h2>BFS 예제 12: 최단 경로 탐색</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample12 {
    public int bfsShortestPath(int[][] graph, int start, int target) {
        boolean[] visited = new boolean[graph.length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start, 0});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];

            if (node == target) {
                return distance;
            }

            for (int i = 0; i < graph[node].length; i++) {
                if (graph[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.add(new int[]{i, distance + 1});
                }
            }
        }
        return -1; // target에 도달할 수 없는 경우
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 1)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 12), 'BFS를 사용한 트리 순회', '<h2>BFS 예제 13: 트리 순회</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample13 {
    static class TreeNode {
        int val;
        List<TreeNode> children;

        TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }

    public void bfsTreeTraversal(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children);
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 2)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 13), 'BFS로 미로 찾기', '<h2>BFS 예제 14: 미로 찾기</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample14 {
    public boolean bfsMazeSolver(int[][] maze, int startX, int startY, int endX, int endY) {
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int x = position[0], y = position[1];

            if (x == endX && y == endY) {
                return true;
            }

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX >= 0 && newY >= 0 && newX < maze.length && newY < maze[0].length && maze[newX][newY] == 0) {
                    queue.add(new int[]{newX, newY});
                    maze[newX][newY] = -1; // 방문 표시
                }
            }
        }
        return false;
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 3)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 15), 'BFS와 DFS를 결합하여 최적 경로 찾기', '<h2>BFS 예제 15: BFS와 DFS 결합</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample15 {
    public void bfsDfsCombined(List<List<Integer>> graph, int start) {
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[graph.size()];

        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            System.out.print(node + " ");

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 4)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 16), 'BFS로 사회 연결망 분석하기', '<h2>BFS 예제 16: 사회 연결망 분석</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample16 {
    public void bfsSocialNetwork(List<List<Integer>> network, int person) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[network.size()];
        queue.add(person);
        visited[person] = true;

        while (!queue.isEmpty()) {
            int currentPerson = queue.poll();
            System.out.println("Person " + currentPerson + " is connected to:");

            for (int friend : network.get(currentPerson)) {
                if (!visited[friend]) {
                    visited[friend] = true;
                    queue.add(friend);
                    System.out.println("  - " + friend);
                }
            }
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 5)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 17), 'BFS를 사용한 웹 크롤링', '<h2>BFS 예제 17: 웹 크롤링</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample17 {
    public void bfsWebCrawling(Map<String, List<String>> webGraph, String startUrl) {
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(startUrl);
        visited.add(startUrl);

        while (!queue.isEmpty()) {
            String url = queue.poll();
            System.out.println("Visiting: " + url);

            for (String link : webGraph.getOrDefault(url, Collections.emptyList())) {
                if (!visited.contains(link)) {
                    visited.add(link);
                    queue.add(link);
                }
            }
        }
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 6)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 18), 'BFS를 통한 경로 복원', '<h2>BFS 예제 18: 경로 복원</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample18 {
    public List<Integer> bfsRestorePath(List<List<Integer>> graph, int start, int end) {
        boolean[] visited = new boolean[graph.size()];
        int[] parent = new int[graph.size()];
        Arrays.fill(parent, -1);
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == end) break;

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    parent[neighbor] = node;
                    queue.add(neighbor);
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = parent[at]) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 7)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 19), 'BFS를 활용한 그래프 탐색 예제', '<h2>BFS 예제 19: 그래프 탐색 예제</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample19 {
    public void bfsExampleGraph(List<List<Integer>> graph, int start) {
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
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 8)),
(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 19), 'BFS를 사용한 무향 그래프 탐색', '<h2>BFS 예제 20: 무향 그래프 탐색</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.*;

public class BFSExample20 {
    public void bfsUndirectedGraph(int[][] graph, int start) {
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
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 9));

-- 코드 리뷰 데이터 삽입
INSERT INTO code_review (id, member_id, title, content, create_date, language_id)
VALUES (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 20), '자바의 옵셔널 사용법', '<h2>Java: Optional 사용법</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> optional = Optional.of("Hello");
        optional.ifPresent(System.out::println);

        String result = optional.orElse("Default Value");
        System.out.println(result);
    }
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 0)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 20), 'Python에서의 리스트 컴프리헨션', '<h2>Python: 리스트 컴프리헨션 사용 예제</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
# 리스트 컴프리헨션을 사용하여 짝수 필터링
numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
even_numbers = [n for n in numbers if n % 2 == 0]
print(even_numbers)
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 1)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 7), 'JavaScript의 비동기 처리 방법', '<h2>JavaScript: 비동기 처리</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
function fetchData(url) {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`Data from ${url}`);
        }, 1000);
    });
}

fetchData("https://example.com")
    .then(data => console.log(data))
    .catch(error => console.error(error));
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 2)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 20), 'C++에서의 스마트 포인터', '<h2>C++: 스마트 포인터 사용법</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
#include &lt;iostream&gt;
#include &lt;memory&gt;

class MyClass {
public:
    void sayHello() { std::cout &lt;&lt; "Hello from MyClass!" &lt;&lt; std::endl; }
};

int main() {
    std::unique_ptr&lt;MyClass&gt; ptr = std::make_unique&lt;MyClass&gt;();
    ptr->sayHello();
    return 0;
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 3)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 21), 'Ruby에서의 블록, 프로시저, 람다 차이', '<h2>Ruby: 블록, 프로시저, 람다 비교</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
# 블록 예제
def block_example
    yield if block_given?
end

block_example { puts "Hello from Block" }

# 프로시저 예제
proc_example = Proc.new { puts "Hello from Proc" }
proc_example.call

# 람다 예제
lambda_example = lambda { puts "Hello from Lambda" }
lambda_example.call
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 4)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 22), 'Swift에서의 클로저 기본 사용법', '<h2>Swift: 클로저 사용법</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
let closureExample = { (name: String) -> String in
    return "Hello, \\(name)"
}

let greeting = closureExample("World")
print(greeting)
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 5)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 23), 'Go에서의 고루틴 활용 예제', '<h2>Go: 고루틴 사용 예제</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
package main

import (
    "fmt"
    "time"
)

func printMessage(msg string) {
    for i := 0; i < 5; i++ {
        fmt.Println(msg)
        time.Sleep(time.Millisecond * 500)
    }
}

func main() {
    go printMessage("Hello")
    printMessage("World")
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 6)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 24), 'Kotlin의 확장 함수 활용하기', '<h2>Kotlin: 확장 함수 사용 예제</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
fun String.printWithPrefix(prefix: String) {
    println("$prefix$this")
}

fun main() {
    val message = "Hello, Kotlin!"
    message.printWithPrefix("Greeting: ")
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 7)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 25), 'Rust에서의 소유권과 빌림', '<h2>Rust: 소유권과 빌림</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
fn main() {
    let s1 = String::from("hello");
    let s2 = &s1; // 빌림
    println!("s2: {}", s2);
    println!("s1: {}", s1); // 소유권은 여전히 s1에 있음
}
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 8)),

(UUID(), (SELECT id FROM member LIMIT 1 OFFSET 25), 'TypeScript에서의 인터페이스 사용 예제', '<h2>TypeScript: 인터페이스 사용</h2>
<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>
interface User {
    name: string;
    age: number;
}

function greet(user: User): string {
    return `Hello, \\(user.name). You are \\(user.age) years old.`;
}

const user = { name: "Alice", age: 30 };
console.log(greet(user));
</code></pre></div>', NOW(), (SELECT id FROM code_language_category LIMIT 1 OFFSET 9));



INSERT INTO code_review_comment (id, comment, create_date, code_review_id, member_id)
VALUES (UUID(), '잘 작성해 주셨습니다!', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 0), (SELECT id FROM member LIMIT 1 OFFSET 20)),
       (UUID(), '잘하셨는데 <div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        boolean[] visited = new boolean[graph.size()];
        Map<Integer, Integer> parentMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);

</code></pre></div>
이부분에서 다형성을 고려해야 할 거 같습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 1), (SELECT id FROM member LIMIT 1 OFFSET 2)),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

</code></pre></div>이 부분은 왜 이렇게 쓰신걸까요?', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 2), (SELECT id FROM member LIMIT 1 OFFSET 20)),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }

</code></pre></div>반복문이 너무 깊습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 3), (SELECT id FROM member LIMIT 1 OFFSET 20)),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }

</code></pre></div>이런 방법으로 하면 좋을 거 같습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 4), (SELECT id FROM member LIMIT 1 OFFSET 22)),
       (UUID(), '<div data-language="text" class="toastui-editor-ww-code-block"><pre><code>

        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }

</code></pre></div>이건 어떨까요?', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 5), (SELECT id FROM member LIMIT 1 OFFSET 23)),
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
    }요거는 어떨까요', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 6), (SELECT id FROM member LIMIT 1 OFFSET 2)),
       (UUID(), 'public void bfsTraverse(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                bfs(i, graph, visited);
            }
        }
    }너무 힘들어요..', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 7), (SELECT id FROM member LIMIT 1 OFFSET 29)),
       (UUID(), ' static class TreeNode {
        int val;
        List<TreeNode> children;

        TreeNode(int val) {
            this.val = val;
            this.children = new ArrayList<>();
        }
    }덕분에 트리 노드를 구경했습니다.', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 8), (SELECT id FROM member LIMIT 1 OFFSET 3)),
       (UUID(), 'public void bfs(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            queue.addAll(node.children);
        }
    }저도요', NOW(), (SELECT id FROM code_review LIMIT 1 OFFSET 9), (SELECT id FROM member LIMIT 1 OFFSET 4));

INSERT INTO code_review_preference (id, sender_member_id, entity_id, is_Like, create_date)
VALUES (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 20), (SELECT id FROM code_review LIMIT 1 OFFSET 0), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 2), (SELECT id FROM code_review LIMIT 1 OFFSET 1), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 21), (SELECT id FROM code_review LIMIT 1 OFFSET 2), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 23), (SELECT id FROM code_review LIMIT 1 OFFSET 3), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 24), (SELECT id FROM code_review LIMIT 1 OFFSET 4), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 25), (SELECT id FROM code_review LIMIT 1 OFFSET 5), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 26), (SELECT id FROM code_review LIMIT 1 OFFSET 6), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 27), (SELECT id FROM code_review LIMIT 1 OFFSET 7), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 28), (SELECT id FROM code_review LIMIT 1 OFFSET 8), 1, NOW()),
       (UUID(), (SELECT id FROM member LIMIT 1 OFFSET 29), (SELECT id FROM code_review LIMIT 1 OFFSET 9), 1, NOW());

/*
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

 */