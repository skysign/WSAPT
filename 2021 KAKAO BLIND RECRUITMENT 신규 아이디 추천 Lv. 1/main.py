import re
from typing import List


def solution(new_id: str):
    # 1단계
    id = new_id.lower()

    # 2단계
    id: List = list(id)
    new_id = []
    for c in id:
        if c in '0123456789abcdefghijklmnopqrstuvwxyz-_.':
            new_id.append(c)
    id = ''.join(new_id)

    # 3단계
    id = re.sub(r'\.+', '.', id)

    # 4단계
    if id[0] == '.':
        id = id[1:]

    if len(id) > 0 and id[len(id) - 1] == '.':
        id = id[:-1]

    # 5단계
    if id == '':
        id = 'a'

    # 6단계
    if len(id) >= 16:
        id = id[:15]

    if id[len(id) - 1] == '.':
        id = id[:-1]
        
    # 7단계
    while len(id) <=2:
        id += id[len(id)-1]

    return id
