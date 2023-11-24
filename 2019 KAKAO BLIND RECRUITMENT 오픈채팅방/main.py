def solution(records):
    histories = []
    uid2nick = {}

    for record in records:
        tmp = record.split()

        if tmp[0] == 'Enter':
            cmd, uid, nick = tmp
            histories.append((cmd, uid))
            uid2nick[uid] = nick
        elif tmp[0] == 'Leave':
            cmd, uid = tmp
            histories.append((cmd, uid))
        else:   # Change
            cmd, uid, nick = tmp
            uid2nick[uid] = nick

    answer = []

    for history in histories:
        cmd, uid = history

        if cmd == 'Enter':
            nick = uid2nick[uid]
            answer.append(nick + '님이 들어왔습니다.')
        else:
            nick = uid2nick[uid]
            answer.append(nick + '님이 나갔습니다.')

    return answer