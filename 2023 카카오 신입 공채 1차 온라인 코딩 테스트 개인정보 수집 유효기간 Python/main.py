# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 개인정보 수집 유효기간 Python
#
# 유튜브 문제 풀이: https://youtu.be/lZa2qBb9Sts
#
# 파이썬 소스: http://bit.ly/3FbQzIc
#
# 문제 링크: http://bit.ly/3YD8XR9

def myDays(ymd):
    y, m, d = ymd.split('.')
    y = int(y)
    m = int(m)
    d = int(d)
    y -= 2000
    return (y * 12 * 28) + (m * 28) + d


def solution(today, terms, privacies):
    answer = []
    myterms = {}

    today = myDays(today)

    for term in terms:
        t, months = term.split(' ')
        myterms[t] = int(months) * 28

    for idx in range(0, len(privacies)):
        ymd, t = privacies[idx].split( )
        days = myDays(ymd)
        if days + myterms.get(t) <= today:
            answer.append(idx + 1)

    return answer