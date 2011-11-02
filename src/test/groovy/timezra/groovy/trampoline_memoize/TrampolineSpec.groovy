package timezra.groovy.trampoline_memoize

import spock.lang.Specification

class TrampolineSpec extends Specification {

	int count

	def fib = { n, a = 0, b = 1 ->
		count++
		if(n == 0) a
		else fib.trampoline n - 1, b, a + b
	}.trampoline()

	def "tail calls chould be optimized"() {
		when:
		def actual = fib 1000

		then:
		actual == 1556111435
		count == 1001
	}
}
