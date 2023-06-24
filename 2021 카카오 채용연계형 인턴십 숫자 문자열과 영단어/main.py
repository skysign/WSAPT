# 2021 카카오 채용연계형 인턴십 숫자 문자열과 영단어
#
# 유튜브 문제 풀이: https://youtu.be/gN6aK7l1chg
#
# 파이썬 소스: https://bit.ly/3CMOuky
#
# 문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/81301

def solution(str_input):
    str_numbers = [ 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine' ]

    for number in range(10):
        str_input = str_input.replace(str_numbers[number], str(number))

    return int(str_input)