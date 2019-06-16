package net.besttoolbars.gdeslon

import net.besttoolbars.gdeslon.response.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GdeslonTest {
    @Test
    fun couponTest() {
        val stream = this.javaClass.classLoader.getResourceAsStream("testdata/coupons/sample.xml")!!

        val couponCategories = listOf(
            GdeslonCategory(id = 1, name = "Подарки, сувениры, цветы"),
            GdeslonCategory(id = 41, name = "Мебель")
        )

        val categories = listOf(
            GdeslonCategory(id = 21, name = "Товары для детей"),
            GdeslonCategory(id = 25, name = "Подарки")
        )

        val merchants = listOf(
            GdeslonMerchant(
                categories = listOf(
                    GdeslonCategory(id = 45, name = null)
                ),
                logoFileName = GdeslonLogoFileName(
                    big = "https://www.gdeslon.ru/uploads/users/84818/logos/big.jpg",
                    regular = "https://www.gdeslon.ru/uploads/users/84818/logos/regular.jpg",
                    small = "https://www.gdeslon.ru/uploads/users/84818/logos/small.jpg"
                ),
                name = "101tea.ru",
                id = 84818
            ),
            GdeslonMerchant(
                categories = listOf(
                    GdeslonCategory(id = 43, name = null),
                    GdeslonCategory(id = 15, name = null)
                ),
                logoFileName = GdeslonLogoFileName(
                    big = "https://www.gdeslon.ru/uploads/users/70597/logos/big.jpg",
                    regular = "https://www.gdeslon.ru/uploads/users/70597/logos/regular.jpg",
                    small = "https://www.gdeslon.ru/uploads/users/70597/logos/small.jpg"
                ),
                name = "123.ru",
                id = 70597
            )
        )

        val kinds = listOf(
            GdeslonKind(name = "скидка на заказ", id = 0),
            GdeslonKind(name = "подарок к заказу", id = 1)
        )

        val coupons = listOf(
            GdeslonCoupon(
                merchantId = 82975,
                startAt = "2017-07-05 16:38:00",
                finishAt = "2020-01-01 23:55:00",
                urlWithCode = "http://xf.gdeslon.ru/ck/b7b354bd2ff6ac04f155daa20cbd0abbef47154e/223550?kc=",
                kind = 3,
                couponCategoriesIds = listOf(GdeslonCategory(id = 1111, name = null)),
                description = "Каждый пользователь, зарегистрированный на cashback24.online по партнёрской ссылке, становится Вашим рефералом.\n" +
                        "                Дополнительно Вы получаете 20% от всех кэшбэк начислений на аккаунт друга. Данный бонус является бессрочным, начисления осуществляются на протяжении всей активности друга на сайте.",
                id = 223550,
                code = null,
                url = "http://xf.gdeslon.ru/ck/b7b354bd2ff6ac04f155daa20cbd0abbef47154e/223550",
                instruction = "Каждый пользователь, зарегистрированный на cashback24.online по партнёрской ссылке, становится Вашим рефералом. Данный бонус является бессрочным, начисления осуществляются на протяжении всей активности друга на сайте.",
                name = "Приведи друга и получи 20% от суммы каждого кэшбэка"
            ),
            GdeslonCoupon(
                merchantId = 72571,
                startAt = "2018-05-22 15:42:00",
                finishAt = "2201-01-31 23:55:00",
                urlWithCode = "http://xf.gdeslon.ru/ck/b7b354bd2ff6ac04f155daa20cbd0abbef47154e/236522?kc=nozh3",
                kind = 0,
                couponCategoriesIds = listOf(GdeslonCategory(id = 495, name = null)),
                description = "Скидка 3% на заказ от 5 000руб.",
                id = 236522,
                code = "nozh3",
                url = "http://xf.gdeslon.ru/ck/b7b354bd2ff6ac04f155daa20cbd0abbef47154e/236522",
                instruction = "Скидка 3% на заказ от 5 000руб.",
                name = "Скидка 3% на заказ от 5 000руб."
            )
        )

        val expected = GdeslonCouponsResponse(
            couponCategories,
            categories,
            merchants,
            kinds,
            coupons
        )
        val gdeslon = Gdeslon(httpHandler = MockGdeslon(stream))
        val response = gdeslon.coupons("").get()
        println(response)
        assertEquals(expected, response)
    }
}
