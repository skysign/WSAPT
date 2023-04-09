import copy

# 2023 카카오 신입 공채 1차 온라인 코딩 테스트 이모티콘 할인행사 Python
#
# 유튜브 문제 풀이: https://youtu.be/X80UHHe-qyA
#
# 파이썬 소스: http://bit.ly/3zJ8vH3
#
# 문제 링크: http://bit.ly/3mkbhzu

def solution(users, emoticons):
    discounts = [10, 20, 30, 40]
    products = []

    for idx in range(len(emoticons)):
        emoticon = emoticons[idx]

        if idx == 0:
            for discount in discounts:
                products.append([[discount, emoticon]])

            # print(products)
        else:
            tmp = []
            for product in products:
                for discount in discounts:
                    copy_product = copy.deepcopy(product)
                    copy_product.append([discount, emoticon])
                    tmp.append(copy_product)

            # print(tmp)
            products = tmp

    max_sum_of_price = 0
    max_subscription = 0

    for discount_price_products in products:
        tmp_sum_of_price = 0
        tmp_subscription = 0

        for user in users:
            user_discount, user_max_price = user
            user_sum_price = 0

            for discount_price in discount_price_products:
                emoticon_discount, emoticon_price = discount_price

                if emoticon_discount >= user_discount:
                    user_sum_price += (emoticon_price * (100 - emoticon_discount) / 100)

            if user_sum_price >= user_max_price:
                tmp_subscription += 1
            else:
                tmp_sum_of_price += user_sum_price

        if max_subscription < tmp_subscription:
            max_subscription = tmp_subscription
            max_sum_of_price = tmp_sum_of_price
        elif max_subscription == tmp_subscription:
            if max_sum_of_price < tmp_sum_of_price:
                max_sum_of_price = tmp_sum_of_price

    return [max_subscription, max_sum_of_price]
