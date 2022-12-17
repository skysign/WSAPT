# 2022 KAKAO TECH INTERNSHIP 성격 유형 검사하기 python
#
# 유튜브 문제 풀이: https://youtu.be/9sImoRvqxlI

def solution(survey, choices):
    dt = {'R': 0, 'T': 0, 'C': 0, 'F': 0, 'J': 0, 'M': 0, 'A': 0, 'N': 0}
    # 1 	매우 비동의
    # 2 	비동의
    # 3 	약간 비동의
    # 4 	모르겠음
    # 5 	약간 동의
    # 6 	동의
    # 7 	매우 동의
    mapping = {1: 3, 2: 2, 3: 1, 4: 0, 5: 1, 6: 2, 7: 3}

    for i in range(0, len(choices)):
        choice = choices[i]

        if choice < 4:
            key = survey[i][0]
            dt[key] += mapping[choice]
        elif choice > 4:
            key = survey[i][1]
            dt[key] += mapping[choice]

    answer = ''

    # 1번 지표 	라이언형(R), 튜브형(T)
    # 2번 지표 	콘형(C), 프로도형(F)
    # 3번 지표 	제이지형(J), 무지형(M)
    # 4번 지표 	어피치형(A), 네오형(N)
    jipyo = [['R', 'T'], ['C', 'F'], ['J', 'M'], ['A', 'N']]

    for j in jipyo:
        c1 = j[0]
        c2 = j[1]

        if dt[c1] == dt[c2]:
            if c1 < c2:
                answer += c1
            else:
                answer += c2
        elif dt[c1] > dt[c2]:
            answer += c1
        else:
            answer += c2

    return answer
