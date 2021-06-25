package main

import "fmt"

func foo() {
	fmt.Println("나는 함수1")
}

func foo3(myfunc func(int) int) {
	fmt.Println(myfunc(7))
}

func returnFunction(x string) func() {
	return func() { fmt.Println(x) }
}

func main() {
	x := foo
	x()
	foo1 := func() {
		fmt.Println("나는 함수2")
	}
	foo1()

	foo2 := func(x int) int {
		return x + 2
	}(7)

	foo3_test := func(x int) int {
		return x + 10
	}

	fmt.Println(foo2)

	foo3(foo3_test)

	returnFunction("나는 함수 ")()
	// returnFunction() 을 하면 반환값이 함수이다.
	// 따라서 해당 함수를 () 실행해줘야 결과가 출력된다.

	x1 := returnFunction("x1변수할당함수")

	x1()
}

// 함수는 변수화가 가능하다.
