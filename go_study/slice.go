package main

import "fmt"

func main() {
	var x [5]int = [5]int{1, 2, 3, 4, 5}
	var s []int = x[0:4]
	var s1 []int = x[1:3]
	fmt.Println(s)
	fmt.Println(s1)
	fmt.Println(len(s))
	fmt.Println(cap(s))

}

/*
length : 배열의 길이
capacity : 배열의 용량
*/
