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