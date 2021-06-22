package main

import (
	"fmt"
	"math"
)

func main() {
	math.Max(1, 2)
	var num1 int = 8
	var num2 float64 = 1.1
	answer := num1 + int(num2)
	fmt.Printf("%d\n", answer)
}

// GO 언어에서 형변환
// type(_target_)
// var num1 float64 = 1.1;
// ex) int(num1)
