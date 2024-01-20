from collections import defaultdict
from typing import Dict, List

def solution(friends: List[str], gifts: List[str]):
    answer: Dict = {}
    # 선물을 보낸수/선물 받은수
    sent_received: Dict = {}
    # 선물을 주고 받은 관계를 저장함
    sender_receiver:Dict = defaultdict(int)

    for f in friends:
        sent_received[f] = [0, 0]  # (선물 보낸 수, 선물 받은 수)
        answer[f] = 0

    for sr in gifts:
        sender, receiver = sr.split(' ')
        sent_received[sender][0] += 1
        sent_received[receiver][1] += 1

        sender_receiver[(sender, receiver)] += 1

    for key in sent_received.keys():
        v = sent_received[key][0] - sent_received[key][1]
        sent_received[key] = [sent_received[key][0], sent_received[key][1], v]

    for sender in friends:
        # 선물을 주고받지 안은 친구보다, 선물지수가 높은 경우
        not_receiver_present_jisu = 0

        for receiver in friends:
            if sender == receiver:
                continue

            if ((sender, receiver) in sender_receiver.keys()\
                    or (receiver, sender) in sender_receiver.keys()):
                if sender_receiver[(sender, receiver)] > sender_receiver[(receiver, sender)]:
                    answer[sender] += 1
                elif sender_receiver[(sender, receiver)] == sender_receiver[(receiver, sender)]:
                    if sent_received[sender][2] > sent_received[receiver][2]:
                        not_receiver_present_jisu += 1
            else:
                if sent_received[sender][2] > sent_received[receiver][2]:
                    not_receiver_present_jisu += 1

        answer[sender] += not_receiver_present_jisu

    values = list(answer.values())

    return max(values)