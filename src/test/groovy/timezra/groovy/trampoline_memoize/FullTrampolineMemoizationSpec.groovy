package timezra.groovy.trampoline_memoize

class FullTrampolineMemoizationSpec extends spock.lang.Specification {

	int count

	def fib_aux = { n, a, b ->
		count++
		if(n == 0) a
		else fib.trampoline n - 1, b, a + b
	}.memoize()

	def fib = { n, a = 0, b = 1 ->
		fib_aux.call n, a, b
	}.trampoline()

	def "all trampolined calls should be cached"() {
		when:
		def first = fib 1000
		def second = fib 500, 315178285, -1898383934

		then:
		count == 1001
		first == second
		second == 1556111435
	}
}
