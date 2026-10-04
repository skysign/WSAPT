def solution(dist_limit, split_limit):
    if dist_limit == 0:
        return 1

    answer = [0]
    # dist_used 현재까지 사용된 분배 노드의 개수
    # prev_split_limit 이전 depth 까지 계산된 split_limit 값
    # prev_leaf 분배노드가 아닌 leaf 노드
    # crnt_dist 분배노드가 될 수 있는 leaf 노드
    def dfs(answer, dist_used, prev_split_limit, prev_leaf, crnt_dist):
        if dist_used > dist_limit:
            return

        answer[0] = max(answer[0], crnt_dist + prev_leaf)

        for child in [2, 3]:
            if split_limit < prev_split_limit * child:
                continue

            available_leaf = dist_limit - dist_used

            new_dist = min(crnt_dist, available_leaf)

            dfs(answer,
                dist_used + new_dist,
                prev_split_limit * child,
                prev_leaf + (crnt_dist - new_dist),
                new_dist * child
                )

    dfs(answer, 0, 1, 0, 1)

    return answer[0]