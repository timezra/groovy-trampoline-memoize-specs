package timezra.groovy.trampoline_memoize

import spock.lang.Specification

class RecursiveMemoizationSpec extends Specification {

	int count

	def recurse(n) {
		fib n
	}

	def fib = { n ->
		count++
		if(n == 0) 0
		else if(n == 1) 1
		else recurse(n-1) + recurse(n-2)
	}.memoize()

	def "calls should be cached"() {
		when:
		def actual = fib 10

		then:
		actual == 55
		count == 11
		// count == 177 //unmemoized
	}
}
