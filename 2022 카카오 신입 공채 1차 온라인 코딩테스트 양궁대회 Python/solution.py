ryan_arrows = None
diff_a_r = 0

def solution(n, info):
    tmp = [0 for _ in range(11)]
    rec(info, 9, tmp, n)

    if diff_a_r == 0:
        return [-1]

    return ryan_arrows

def get_score(a_info, r_info):
    a_rtn = 0
    r_rtn = 0

    for i in range(10):
        s = 10 - i
        if a_info[i] > 0 and r_info[i] > 0:
            if r_info[i] > a_info[i]:
                r_rtn += s
            else:
                a_rtn += s
        elif a_info[i] > 0:
            a_rtn += s
        elif r_info[i] > 0:
            r_rtn += s

    return a_rtn, r_rtn

# 0, a is lower
# 1, b is lower
def more_lower_score(a, b):
    for i in range(10, -1, -1):
        if a[i] > b[i]:
            return 0
        if a[i] < b[i]:
            return 1

    return 0

def rec(info, depth, ryan_info, remained_arrows):
    global ryan_arrows, diff_a_r

    if depth < 0:
        apeach_score, tmp_score = get_score(info, ryan_info)
        diff_tmp =  tmp_score - apeach_score
        # print(f"apeach_score {apeach_score} tmp_score {tmp_score} diff_tmp {diff_tmp}")
        if remained_arrows > 0:
            ryan_info[10] += remained_arrows;

        if diff_a_r < diff_tmp:
            ryan_arrows = ryan_info
            diff_a_r = diff_tmp
        elif 0 < diff_a_r and diff_a_r == diff_tmp and more_lower_score(ryan_arrows, ryan_info) > 0:
            ryan_arrows = ryan_info

        return

    if remained_arrows - (info[depth] + 1) >= 0:
        new_ryan_info = ryan_info[:]
        new_ryan_info[depth] = info[depth] + 1
        new_remained_arrows = remained_arrows - (info[depth] + 1)
        rec(info, depth - 1, new_ryan_info, new_remained_arrows)

    rec(info, depth - 1, ryan_info[:], remained_arrows)