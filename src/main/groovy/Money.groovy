import groovy.json.JsonSlurper
import groovy.transform.ToString

@ToString
class Money {
    final BigDecimal amount
    final String currency
    final GString url = "http://www.floatrates.com/daily/${->this.currency}.json"

    Money(BigDecimal amount, String currency) {
        this.amount = amount
        this.currency = currency.toLowerCase()
    }

    static BigDecimal getRate(String from, String to) {
        GString url = "http://www.floatrates.com/daily/${from.toLowerCase()}.json"
        new JsonSlurper().parse(new URL(url))["${to.toLowerCase()}"].rate
    }

    Money plus(Money money) {
        def rate = 1
        if (money.currency != this.currency) {
            rate = new JsonSlurper().parse(new URL(url))["${money.currency.toLowerCase()}"].rate
        }
        new Money(this.amount + (money.amount / rate as BigDecimal), this.currency)
    }
}
