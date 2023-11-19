def solution(arr1, arr2):
    r1, c1 = len(arr1), len(arr1[0])
    r2, c2 = len(arr2), len(arr2[0])

    # c1 == r2는 같아야 행렬을 서로 곱할 수 있음
    # 결과 행렬의 크기, r1, c2
    answer = [[0] * c2 for _ in range(r1)]

    for i in range(r1):
        for j in range(c2):
            for k in range(c1): # or r2
                answer[i][j] += (arr1[i][k] * arr2[k][j])

    return answer