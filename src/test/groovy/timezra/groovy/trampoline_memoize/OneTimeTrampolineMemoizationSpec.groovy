package timezra.groovy.trampoline_memoize

import spock.lang.Specification

class OneTimeTrampolineMemoizationSpec extends Specification {

	int count

	def fib_aux = { n, a = 0, b = 1 ->
		count++
		if(n == 0) a
		else fib_aux.trampoline n - 1, b, a + b
	}.trampoline()

	def fib = fib_aux.memoize()

	def "top-level trampolined calls should be cached"() {
		when:
		def first = fib 1000
		def second = fib 1000

		then:
		count == 1001
		first == second
		second == 1556111435
	}
}
