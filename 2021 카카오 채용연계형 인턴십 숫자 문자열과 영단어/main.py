def solution(str_input):
    str_numbers = [ 'zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine' ]

    for number in range(10):
        str_input = str_input.replace(str_numbers[number], str(number))

    return int(str_input)