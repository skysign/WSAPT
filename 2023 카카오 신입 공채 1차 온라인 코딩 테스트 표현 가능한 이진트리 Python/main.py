def solution(numbers):
    answer = []

    for number in numbers:
        twojinString = twojinbum(number)
        twojinLength = len(twojinString)
        fullLength, treeLevel = getBinaryTreeLength(twojinLength)
        for _ in range(fullLength - twojinLength):
            twojinString = '0' + twojinString
        mid = int(fullLength / 2)
        tf = searchTreeInOrder(treeLevel, mid, twojinString)
        answer.append(tf)

    return answer

def searchTreeInOrder(level, mid, treedata):
    if level == 1:
        return 1

    parent = treedata[mid]
    idxLeft = int(mid) - int(2 ** (level -1) / 2)
    leftChild = treedata[idxLeft]
    idxRight = int(mid) + int(2 ** (level -1) / 2)
    rightChild = treedata[idxRight]

    if parent == '0':
        if leftChild == '1' or rightChild == '1':
            return 0

    # parent == 1
    rtnLeft = searchTreeInOrder(level -1, idxLeft, treedata)
    if rtnLeft == 0:
        return 0

    rtnRight = searchTreeInOrder(level -1, idxRight, treedata)
    if rtnRight == 0:
        return 0

    return 1


def getBinaryTreeLength(numberLength):
    binaryTreeLength = 1
    treeLevel = 1

    while numberLength > binaryTreeLength:
        treeLevel += 1
        binaryTreeLength += 2 ** (treeLevel-1)

    return binaryTreeLength, treeLevel


def twojinbum(dividend):
    divisor = 2
    remainder = dividend % divisor
    quotient = int(dividend / divisor)

    if quotient == 0:
        return str(remainder)

    return twojinbum(quotient) + str(remainder)
