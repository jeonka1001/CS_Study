package main

import "fmt"

type Duck_Hunting struct {
	name string
	age  int
}

func main() {
	dog1 := Duck_Hunting{}
	dog2 := Duck_Hunting{}

	dog1.name = "Amy" // string 초기화 안할 시  " "
	dog2.name = "Lex"

	dog1.age = 10
	dog2.age = 11 // int 초기화 안할 시 0

	dog3 := Duck_Hunting{age: 1, name: "jade"}
	fmt.Println(dog1, dog2, dog3)
}

// https://www.youtube.com/watch?v=ImhMMPKMOPE&list=PLsGh7Wc318kjJj1NdyZG8xcIrsuBNNiWs&index=20
// #19
// go 언어에서는 struct 라는 구조체를 사용함
// 필드의 집합
// type 구조체명 struct { //... }
