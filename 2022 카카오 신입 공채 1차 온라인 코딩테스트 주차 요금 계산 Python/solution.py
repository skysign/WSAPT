def_time, def_fee, unit_min, unit_fee = 0, 0, 0, 0


def str_time2min_time(str_time):
    hour, minute = str_time.split(':')
    return int(minute) + (int(hour) * 60)


def calculate_parking_fee(parking_time):
    if parking_time <= 0:
        return 0

    parking_fee = def_fee
    parking_time -= def_time

    if parking_time > 0:
        parking_fee += int(parking_time / unit_min) * unit_fee

        if parking_time % unit_min:
            parking_fee += unit_fee

    return parking_fee


def solution(fees, records):
    global def_time, def_fee, unit_min, unit_fee
    def_time, def_fee, unit_min, unit_fee = fees

    car_in = {}  # key 자동차 번호판, value는 시간
    parking_time = {}
    fees = {}

    for record in records:
        time, plate, in_out = record.split()

        if in_out == "IN":
            min = str_time2min_time(time)
            car_in[plate] = min
        else:
            time_in = car_in[plate]
            del car_in[plate]
            time_out = str_time2min_time(time)

            if not parking_time.__contains__(plate):
                parking_time[plate] = 0

            parking_time[plate] += (time_out - time_in)

    plates = car_in.keys()
    for plate in plates:
        time_in = car_in[plate]
        time_out = str_time2min_time("23:59")

        if not parking_time.__contains__(plate):
            parking_time[plate] = 0

        parking_time[plate] += (time_out - time_in)


    plates = parking_time.keys()
    for plate in plates:
        parking_fee = calculate_parking_fee(parking_time[plate])
        fees[plate] = parking_fee

    plates = fees.keys()
    plates = sorted(plates)
    answer = []

    for plate in plates:
        answer.append(int(fees[plate]))

    return answer
