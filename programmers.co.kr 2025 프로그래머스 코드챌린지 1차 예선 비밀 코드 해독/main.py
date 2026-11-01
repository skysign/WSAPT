import itertools


def solution(n, q, ans):
    answer = 0
    candidates = []
    for candidate in itertools.combinations(range(1, n + 1), 5):
        candidates.append(set(candidate))

    candidates_new = []
    for query, length in zip(q, ans):
        query = set(query)

        for candidate in candidates:
            if len(query.intersection(candidate)) == length:
                candidates_new.append(candidate)

        candidates = candidates_new
        candidates_new = []

    return len(candidates)
